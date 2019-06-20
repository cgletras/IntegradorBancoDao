package com.leilaodequadrinhos.api.model.task.bid;

        import com.leilaodequadrinhos.api.model.dao.LanceDao;
        import com.leilaodequadrinhos.api.model.dao.LeilaoDao;
        import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LanceDAO;
        import com.leilaodequadrinhos.api.model.dao.impl.jdbc.LeilaoDAO;
        import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
        import com.leilaodequadrinhos.api.model.entities.Lance;
        import com.leilaodequadrinhos.api.model.entities.Leilao;
        import com.leilaodequadrinhos.api.model.entities.User;
        import com.leilaodequadrinhos.api.model.task.Task;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.text.SimpleDateFormat;

public class InsertBid implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LanceDao lanceDao = new LanceDAO();
        UserDAO userDao = new UserDAO();
        LeilaoDao leilaoDAO = new LeilaoDAO();
        String format = "dd/MM/yyyy";
        Lance lance = new Lance();
        lance.setDataLance(new SimpleDateFormat(format).parse(request.getParameter("dateOfBid")));
        Long userID = Long.parseLong(request.getParameter("userID"));
        User user = userDao.findById(userID);
        lance.setUser(user);
        Long auctionID = Long.parseLong(request.getParameter("auctionID"));
        Leilao leilao = (Leilao) leilaoDAO.findById(auctionID);
        lance.setValorLance(leilao.getLancePadrao());
        lance.setLeilao(leilao);
        lanceDao.insert(lance);
        return "Lance inserido com sucesso";
    }
}