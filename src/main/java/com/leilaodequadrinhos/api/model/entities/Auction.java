package com.leilaodequadrinhos.api.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Auction implements Serializable {

    private static final long serialVersionUID = 1L;

    private int duration;
    private Long auctionID;
    private Date initialDate;
    private double initialValue, currentValue, defaultBid;

    private AuctionStatus auctionStatus;
    private Product product;
    private User user;

    public Auction() {
    }

    public Auction(Long auctionID, int duration, Date initialDate, double initialValue, double currentValue,
                   double defaultBid, AuctionStatus auctionStatus, Product product, User user) {
        super();
        this.auctionID = auctionID;
        this.duration = duration;
        this.initialDate = initialDate;
        this.initialValue = initialValue;
        this.currentValue = currentValue;
        this.defaultBid = defaultBid;
        this.auctionStatus = auctionStatus;
        this.product = product;
        this.user = user;
    }

    public AuctionStatus getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(AuctionStatus auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(Long auctionID) {
        this.auctionID = auctionID;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(double initialValue) {
        this.initialValue = initialValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getDefaultBid() {
        return defaultBid;
    }

    public void setDefaultBid(double defaultBid) {
        this.defaultBid = defaultBid;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return auctionID.equals(auction.auctionID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(auctionID);
    }

    @Override
    public String toString() {
        return "Auction [auctionID=" + auctionID + ", duration=" + duration + ", initialDate=" + initialDate
                + ", initialValue=" + initialValue + ", currentValue=" + currentValue + ", defaultBid=" + defaultBid
                + ", product=" + product + ", user=" + user + "]";
    }
}