package com.leilaodequadrinhos.api.model.entities;

import java.io.Serializable;
import java.util.Date;


public class Bid implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer bidID;
    private double bidValue;
    private Date bidDate;

    private Auction auction;
    private User user;

    public Bid() {
        super();
    }

    public Bid(Integer bidID, double bidValue, Date bidDate, Auction auction, User user) {
        super();
        this.bidID = bidID;
        this.bidValue = bidValue;
        this.bidDate = bidDate;
        this.auction = auction;
        this.user = user;
    }

    public Integer getBidID() {
        return bidID;
    }

    public void setBidID(Integer bidID) {
        this.bidID = bidID;
    }

    public double getBidValue() {
        return bidValue;
    }

    public void setBidValue(double bidValue) {
        this.bidValue = bidValue;
    }

    public Date getBidDate() {
        return bidDate;
    }

    public void setBidDate(Date bidDate) {
        this.bidDate = bidDate;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bidID == null) ? 0 : bidID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bid other = (Bid) obj;
        if (bidID == null) {
            if (other.bidID != null)
                return false;
        } else if (!bidID.equals(other.bidID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Bid [bidID=" + bidID + ", bidValue=" + bidValue + ", bidDate=" + bidDate + ", auction="
                + auction + ", user=" + user + "]";
    }
}