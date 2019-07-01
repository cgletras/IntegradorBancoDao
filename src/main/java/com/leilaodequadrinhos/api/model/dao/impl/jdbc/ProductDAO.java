package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.ProductStatusDao;
import com.leilaodequadrinhos.api.model.dao.ProductDao;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.entities.ProductStatus;
import com.leilaodequadrinhos.api.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO implements ProductDao {

    @Override
    public Product findById(Long id) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Produto "
                            + "WHERE id_produto = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Product obj = new Product();
                obj.setProductID(rs.getInt("id_produto"));
                obj.setPublisher(rs.getString("editora"));
                obj.setTitle(rs.getString("titulo"));
                obj.setComicFormat(rs.getString("formato_do_quadrinho"));
                obj.setPagesNumber(rs.getInt("numero_paginas"));
                obj.setWeight(rs.getInt("peso"));
                obj.setCoverImage(rs.getString("capa_imagem"));

                ProductStatusDao productStatusDao = new ProductStatusDAO();
                ProductStatus productStatus = productStatusDao.findById(rs.getLong("id_estado_produto"));
                obj.setProductStatus(productStatus);

                DAO dao = new UserDAO();
                User user = (User) dao.findById(rs.getLong("id_usuario"));
                obj.setUser(user);

                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            // DB.closeStatement(st);
            // DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }

    // TODO: This method has not been implemented because it will only be used with functionality used in the report formulation and administration of the site, which is not in this scope.
    @Override
    public List findAll() {
        return null; //There is no need for a method to list all products on the site. The list in this scope is per user and is implemented in another role.
    }

    @Override
    public void deleteById(Long id) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Produto " +
                            "SET id_estado_produto= ? " +
                            "WHERE id_produto= ?");

            st.setInt(1, 2);
            st.setLong(2, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            // DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public void update(Object entity) {
        Connection conn = DB.getConnection();

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Produto "
                            + "SET editora= ?, titulo= ?, formato_do_quadrinho= ?, numero_paginas= ?, peso= ?, capa_imagem= ?, id_estado_produto= ?, id_usuario= ? "
                            + "WHERE id_produto= ?",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            Product obj = (Product) entity;
            st.setString(1, obj.getPublisher());
            st.setString(2, obj.getTitle());
            st.setString(3, obj.getComicFormat());
            st.setInt(4, obj.getPagesNumber());
            st.setInt(5, obj.getWeight());
            st.setString(6, obj.getCoverImage());
            st.setInt(7, obj.getProductStatus().getProductStatusID());
            st.setInt(8, obj.getUser().getUserID());
            st.setInt(9, obj.getProductID());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setProductID(id);
                }
                // DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            // DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public void insert(Object entity) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO Produto (editora, titulo, formato_do_quadrinho, numero_paginas, peso, capa_imagem, id_estado_produto, id_usuario) " + "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            Product obj = (Product) entity;
            st.setString(1, obj.getPublisher());
            st.setString(2, obj.getTitle());
            st.setString(3, obj.getComicFormat());
            st.setInt(4, obj.getPagesNumber());
            st.setInt(5, obj.getWeight());
            st.setString(6, obj.getCoverImage());
            st.setInt(7, obj.getProductStatus().getProductStatusID());
            st.setInt(8, obj.getUser().getUserID());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setProductID(id);
                }
                // DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            // DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public void changeStatusProduct(Long id, ProductStatus productStatus) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Produto " +
                            "SET id_estado_produto= ? " +
                            "WHERE id_produto= ?");

            st.setInt(1, productStatus.getProductStatusID());
            st.setLong(2, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            // DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public List<Product> findAllByUser(Long UserId) {
        Connection conn = DB.getConnection();

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Produto "
                            + "WHERE id_usuario = ?");

            st.setLong(1, UserId);
            rs = st.executeQuery();

            List<Product> products = new ArrayList<>();
            Map<Integer, Product> map = new HashMap<>();

            while (rs.next()) {
                Product obj = new Product();
                obj.setProductID(rs.getInt("id_produto"));
                obj.setPublisher(rs.getString("editora"));
                obj.setTitle(rs.getString("titulo"));
                obj.setComicFormat(rs.getString("formato_do_quadrinho"));
                obj.setPagesNumber(rs.getInt("numero_paginas"));
                obj.setWeight(rs.getInt("peso"));
                obj.setCoverImage(rs.getString("capa_imagem"));

                ProductStatusDao productStatusDao = new ProductStatusDAO();
                ProductStatus productStatus = productStatusDao.findById(rs.getLong("id_estado_produto"));
                obj.setProductStatus(productStatus);

                DAO dao = new UserDAO();
                User user = (User) dao.findById(rs.getLong("id_usuario"));
                obj.setUser(user);

                products.add(obj);
            }
            return products;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            // DB.closeStatement(st);
            // DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }
}