package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.AuctionStatus;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.User;

import java.text.DateFormat;
import java.util.List;

public interface AuctionDao extends DAO {

	void updateInitialValue(Auction obj);
	void changesAuctionStatus(Long id, AuctionStatus auctionStatus);
	void setAuctionDateNow(Long id);
	List<Auction> findByUser(User obj);
	List<Auction> findAllPaginate(Integer limit, Integer offset);
	Integer returnAuctionDuration(Long id);

}