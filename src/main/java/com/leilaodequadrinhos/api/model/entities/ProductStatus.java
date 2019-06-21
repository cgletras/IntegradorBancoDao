package com.leilaodequadrinhos.api.model.entities;

import java.io.Serializable;

public class ProductStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productStatusID;
    private String status;

    public ProductStatus() {

    }

    public ProductStatus(Integer productStatusID, String status) {
        super();
        this.productStatusID = productStatusID;
        this.status = status;
    }

    public Integer getProductStatusID() {
        return productStatusID;
    }

    public void setProductStatusID(Integer productStatusID) {
        this.productStatusID = productStatusID;
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
        result = prime * result + productStatusID;
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
        ProductStatus other = (ProductStatus) obj;
        return productStatusID.equals(other.productStatusID);
    }

    @Override
    public String toString() {
        return "ProductStatus [productStatusID=" + productStatusID + ", status=" + status + "]";
    }
}