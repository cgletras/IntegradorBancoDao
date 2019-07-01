package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.ProductStatusDao;
import com.leilaodequadrinhos.api.model.entities.ProductStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductStatusDAO implements ProductStatusDao {

    @Override
    public List findAll() {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id_estado_produto, estado "
                            + "FROM Estado_produto "
                            + "ORDER BY estado");

            rs = st.executeQuery();

            List<ProductStatus> list = new ArrayList<>();
            Map<Integer, ProductStatus> map = new HashMap<>();

            while (rs.next()) {
                ProductStatus obj = new ProductStatus();
                obj.setProductStatusID(rs.getInt("id_estado_produto"));
                obj.setStatus(rs.getString("estado"));

                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            // DB.closeConnection();
        }
    }

    @Override
    public ProductStatus findById(Long id) {
        Connection conn = DB.getConnection();

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Estado_produto "
                            + "WHERE id_estado_produto = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                ProductStatus obj = new ProductStatus();
                obj.setProductStatusID(rs.getInt("id_estado_produto"));
                obj.setStatus(rs.getString("estado"));
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            // DB.closeConnection();
        }
    }
}