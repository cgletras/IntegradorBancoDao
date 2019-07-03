package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.task.Task;
import com.leilaodequadrinhos.api.util.HashGenerator;
import com.leilaodequadrinhos.api.util.SendObjectToAWSS3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReceiveProductImage {

    public String execute(HttpServletRequest request) throws IOException, ServletException {

        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        String fileName = filePart.getSubmittedFileName();

        if (!validateFileExtension(fileName)) return "Invalid file format";

        String absoluteTempPath = new File("").getAbsolutePath();
        String absoluteCompleteTempPath = absoluteTempPath + File.separator + HashGenerator.hashGenerator() + fileName;

        filePart.write(absoluteCompleteTempPath);

        return SendObjectToAWSS3.execute(new File(absoluteCompleteTempPath)).toString();

    }

    private Boolean validateFileExtension(String fileName) {

        List<String> validExtensions = new ArrayList<>(Arrays.asList(
                ".jpg", ".jpeg", ".bmp", ".png"
        ));

        for (String $ : validExtensions) {
            if (fileName.substring(fileName.lastIndexOf(".")).contains($)) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }
}