package com.leilaodequadrinhos.api.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SendObjectToAWSS3 {

    public static URL execute(File file) throws MalformedURLException {
        return execute(file, file.getName());
    }

    public static URL execute(File file, String toRemotePath) throws MalformedURLException {

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAUTDKZQWZFLJBXBS7",
                "/vhVgDsYC5ur4Z/GhqJ7HFkh1rtua/7T7i5vQsto"
        );
        String bucketName = "public.images.leilaodequadrinhos.com"; // After send URL looks like: https://s3.amazonaws.com/public.images.leilaodequadrinhos.com/hello.jpg

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();

        s3client.putObject(
                bucketName,
                toRemotePath,
                file
        );

        return new URL("https://s3.amazonaws.com/" + bucketName + "/" + file.getName());
    }
}