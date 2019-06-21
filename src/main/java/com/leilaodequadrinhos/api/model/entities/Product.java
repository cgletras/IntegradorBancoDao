package com.leilaodequadrinhos.api.model.entities;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private int productID, pagesNumber, weight;
    private String publisher, title, comicFormat, coverImage;
    private ProductStatus productStatus;

    private User user;

    public Product() {
        super();
    }

    public Product(int productID, int pagesNumber, int weight, String publisher, String title, String comicFormat,
                   String coverImage, ProductStatus productStatus, User user) {
        super();
        this.productID = productID;
        this.pagesNumber = pagesNumber;
        this.weight = weight;
        this.publisher = publisher;
        this.title = title;
        this.comicFormat = comicFormat;
        this.coverImage = coverImage;
        this.productStatus = productStatus;
        this.user = user;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComicFormat() {
        return comicFormat;
    }

    public void setComicFormat(String comicFormat) {
        this.comicFormat = comicFormat;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Product [productID=" + productID + ", pagesNumber=" + pagesNumber + ", weight=" + weight + ", publisher="
                + publisher + ", title=" + title + ", comicFormat=" + comicFormat + ", coverImage="
                + coverImage + ", productStatus=" + productStatus + ", user=" + user + "]";
    }

}
