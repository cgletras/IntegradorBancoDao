package com.leilaodequadrinhos.api.model.task.bid;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.BidDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.BidDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.Bid;
import com.leilaodequadrinhos.api.model.entities.User;
import com.leilaodequadrinhos.api.model.task.Task;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InsertBid implements Task {

    private static final String API_KEY = "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";

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BidDao bidDao = new BidDAO();
        UserDAO userDao = new UserDAO();
        AuctionDao auctionDAO = new AuctionDAO();
        Bid bid = new Bid();
        bid.setBidDate(new Date());
        User user = ((User) request.getSession().getAttribute("user"));

        bid.setUser(user);
        Long auctionID = Long.parseLong(request.getParameter("auctionID"));
        Auction auction = (Auction) auctionDAO.findById(auctionID);

        bid.setBidValue(auction.getDefaultBid());
        bid.setAuction(auction);

        switch (auction.getAuctionStatus().getStatus()) {
            case "ATIVO":
                bidDao.insert(bid);
                List<User> usersInThisAuction = userDao.findBidsUsersByAuctionId(auctionID);
                new Thread(() -> {
                    try {
                        SendEmailToThoseWhoBid(auction, usersInThisAuction, user, bid.getBidValue());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
                return "Bid successfully entered";
            default:
                return "Bids could only be placed on active auctions";
        }
    }

    private void SendEmailToThoseWhoBid(Auction auction, List<User> usersInThisAuction, User bidUser, Double bidValue) throws IOException {

        Double currentValue = auction.getCurrentValue() + bidValue;

        for (User user : usersInThisAuction) {
            if (!user.getUserID().equals(bidUser.getUserID())) {
                Email from = new Email("notification@leilaoquadrinhos.com");
                String subject = "Notificação de Lance no Leilão do Quadrinho " + auction.getProduct().getTitle();
                Email to = new Email(user.getEmail());
                Content content = new Content("text/html", "<p><strong><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">Ol&aacute; " + user.getName() + ",</span></span></strong></p>\n" +
                        "\n" +
                        "<p>&nbsp;</p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">Informamos que outro usu&aacute;rio cobriu seu&nbsp;lance no quadrinho " + auction.getProduct().getTitle() + ".&nbsp;</span></span></p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">Para continuar na disputa acesse nossa p&aacute;gina:&nbsp;</span></span></p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">O valor atual deste leilão é de: R$ " + currentValue + "</span></span></p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\"><a href=\"https://leilaodequadrinhos.com/leilao/" + auction.getAuctionID() + "\">https://leilaodequadrinhos.com/leilao/" + auction.getAuctionID() + "</a></span></span></p>\n" +
                        "\n" +
                        "<p>&nbsp;</p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">Agradecemos sua participa&ccedil;&atilde;o!</span></span></p>\n" +
                        "\n" +
                        "<p>&nbsp;</p>\n" +
                        "\n" +
                        "<p><span style=\"font-size:16px\"><span style=\"font-family:comic sans ms,cursive\">Atenciosamente,</span></span></p>\n" +
                        "\n" +
                        "<p><strong><font face=\"comic sans ms, cursive\"><span style=\"font-size:16px\">Equipe Leil&atilde;o de Quadrinhos</span></font></strong></p>\n");

                Mail mail = new Mail(from, subject, to, content);

                SendGrid sg = new SendGrid(API_KEY);
                Request requestSG = new Request();
                try {
                    requestSG.setMethod(Method.POST);
                    requestSG.setEndpoint("mail/send");
                    requestSG.setBody(mail.build());
                    Response responseSG = sg.api(requestSG);
                    System.out.println(responseSG.getStatusCode());
                    System.out.println(responseSG.getBody());
                    System.out.println(responseSG.getHeaders());
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
    }
}