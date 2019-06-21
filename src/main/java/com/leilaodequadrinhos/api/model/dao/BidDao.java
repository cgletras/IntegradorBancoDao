package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.Bid;

import java.util.List;

public interface BidDao extends DAO {

	List<Bid> findBidsByUser(Long userID);
	List<Bid> findBidsByAuction(Long auctionID);
	Long BidCount(Long auctionID);
}