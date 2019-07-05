package com.leilaodequadrinhos.api.model.task.auction;

import com.leilaodequadrinhos.api.model.dao.AuctionDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.AuctionDAO;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAuctionsPaginate implements Task {

    @Override
    public Object execute(HttpServletRequest request, HttpServletResponse response) {
        AuctionDao auctionDao = new AuctionDAO();
        Integer limit = request.getParameter("limit") != null ? Integer.parseInt(request.getParameter("limit")) : 100;
        Integer offset = request.getParameter("offset") != null ? Integer.parseInt(request.getParameter("offset")) : 0;
        String columnToOrderBy = "data_inicio";
        String directionToOrderBy = "DESC";
        String titleToSearch = "";
        List<String> publishingCompanys = new ArrayList<>(Arrays.asList("Marvel", "DC", "Outras"));

        if (request.getParameter("columnToOrderBy") != null &&
                (request.getParameter("columnToOrderBy").equalsIgnoreCase("data_inicio") ||
                        request.getParameter("columnToOrderBy").equalsIgnoreCase("valor_atual"))) {
            columnToOrderBy = request.getParameter("columnToOrderBy").toLowerCase();
        }

        if (request.getParameter("directionToOrderBy") != null &&
                (request.getParameter("directionToOrderBy").equalsIgnoreCase("desc") ||
                        request.getParameter("directionToOrderBy").equalsIgnoreCase("asc"))) {
            directionToOrderBy = request.getParameter("directionToOrderBy").toLowerCase();
        }

        if (request.getParameter("titleToSearch") != null) {
            titleToSearch = request.getParameter("titleToSearch").toLowerCase();
        }

        if (request.getParameter("publishingCompanys") != null) {
            List<String> publishingCompanysTMP = new ArrayList<>(Arrays.asList(request.getParameter("publishingCompanys").split(",")));
            publishingCompanysTMP.retainAll(publishingCompanys);

            if (publishingCompanysTMP.size() > 0) {
                publishingCompanys = publishingCompanysTMP;
            }
        }


        List auctions = auctionDao.findAllPaginate(limit, offset, columnToOrderBy, directionToOrderBy, titleToSearch, publishingCompanys);
        request.setAttribute("auctions", auctions);
        return auctions;
    }
}