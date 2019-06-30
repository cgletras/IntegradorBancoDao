package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.task.Task;
import com.leilaodequadrinhos.api.model.task.product.ChangeProductStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
@param: UserInput:
auctionID: Recebe do leilao que ser quer INATIVAR
productID: Recebe do produto ligado ao leilao
productStateID: 1 (ATIVO) Pois um leilao inativado muda o estado do produto de EM_LEILAO para ATIVO
 */

public class DeleteAuctionByID implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        Long auctionID = Long.parseLong(request.getParameter("auctionID"));
        AuctionDao auctionDao = new AuctionDAO();
        auctionDao.deleteById(auctionID);
        String statusOfProduct = new ChangeProductStatus().execute(request, response);
        return "This auction has been deleted";
    }
}