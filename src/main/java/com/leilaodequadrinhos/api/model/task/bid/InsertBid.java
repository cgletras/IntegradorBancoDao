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

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.text.SimpleDateFormat;

public class InsertBid implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BidDao bidDao = new BidDAO();
        UserDAO userDao = new UserDAO();
        AuctionDao auctionDAO = new AuctionDAO();
        String format = "dd/MM/yyyy";
        Bid bid = new Bid();
        bid.setBidDate(new SimpleDateFormat(format).parse(request.getParameter("dateOfBid")));
        Long userID = Long.parseLong(request.getParameter("userID"));
        User user = userDao.findById(userID);
        bid.setUser(user);
        Long auctionID = Long.parseLong(request.getParameter("auctionID"));
        Auction auction = (Auction) auctionDAO.findById(auctionID);
        bid.setBidValue(auction.getDefaultBid());
        bid.setAuction(auction);

        switch (auction.getAuctionStatus().getStatus()) {
            case "ATIVO":
                bidDao.insert(bid);
                return "Bid successfully entered";
            default:
                return "Bids could only be placed on active auctions";
        }
    }
}