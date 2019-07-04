package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.Bid;
import com.leilaodequadrinhos.api.model.entities.User;

import java.util.List;

public interface BidDao extends DAO {

	List<Bid> findBidsByUser(Long userID);
	List<Bid> findBidsByAuction(Long auctionID);
	Long BidCount(Long auctionID);
	List<User> findBidsUsersByAuctionId(Long auctionID);
}