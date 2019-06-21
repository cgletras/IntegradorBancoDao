package com.leilaodequadrinhos.api.model.dao;

import com.leilaodequadrinhos.api.model.entities.AuctionStatus;

import java.util.List;

public interface AuctionStatusDao {

	AuctionStatus findById(Long auctionStatusID);
	List<AuctionStatus> findAll();
}