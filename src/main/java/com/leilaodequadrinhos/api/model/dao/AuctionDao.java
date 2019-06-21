package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.AuctionStatus;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.User;

import java.util.List;

public interface AuctionDao extends DAO {

	void updateInitialValue(Auction obj);
	void changesAuctionStatus(Integer id, AuctionStatus auctionStatus);
	List<Auction> findByUser(User obj);
}