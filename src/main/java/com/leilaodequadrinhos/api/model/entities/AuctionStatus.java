package com.leilaodequadrinhos.api.model.entities;

import java.io.Serializable;

public class AuctionStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer auctionStatusID;
    private String status;

    public AuctionStatus() {
    }

    public AuctionStatus(Integer auctionStatusID, String status) {
        super();
        this.auctionStatusID = auctionStatusID;
        this.status = status;
    }

    public Integer getAuctionStatusID() {
        return auctionStatusID;
    }

    public void setAuctionStatusID(Integer auctionStatusID) {
        this.auctionStatusID = auctionStatusID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((auctionStatusID == null) ? 0 : auctionStatusID.hashCode());
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
        AuctionStatus other = (AuctionStatus) obj;
        if (auctionStatusID == null) {
            if (other.auctionStatusID != null)
                return false;
        } else if (!auctionStatusID.equals(other.auctionStatusID))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "AuctionStatus [auctionStatusID=" + auctionStatusID + ", status=" + status + "]";
    }
}