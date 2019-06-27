package com.leilaodequadrinhos.api.model.task.product;

import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.dao.ProductStatusDao;
import com.leilaodequadrinhos.api.model.dao.UserDao;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.ProductStatusDAO;
import com.leilaodequadrinhos.api.model.dao.impl.jdbc.UserDAO;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.task.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class AddProductImage extends BaseProductTask implements Task {

    private static final long ACTIVE = 1;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Part> fileParts = null; // Retrieves <input type="file" name="file" multiple="true">
        try {
            fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        for (Part filePart : fileParts) {
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            try {
                InputStream fileContent = filePart.getInputStream();
                File file = new File(String.valueOf(fileContent));

            } catch (IOException e) {
                e.printStackTrace();
            }
            // ... (do your job here)
        }
        return "Product picture inserted successfully";



    }
}