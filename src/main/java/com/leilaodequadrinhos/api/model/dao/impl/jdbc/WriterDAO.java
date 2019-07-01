package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.WriterDao;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.entities.Writer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriterDAO implements WriterDao {

    @Override
    public Object findById(Long id) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Escritor "
                            + "WHERE id_escritor = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Writer obj = new Writer();
                obj.setWriterID(rs.getInt("id_escritor"));
                obj.setName(rs.getString("nome"));
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

    @Override
    public List<Writer> findByProduct(Product product) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT ep.id_escritor, e.nome " +
                            "FROM escritor_produto ep INNER JOIN Escritor e " +
                            "ON ep.id_escritor = e.id_escritor " +
                            "WHERE id_produto = ?");

            st.setInt(1, product.getProductID());

            rs = st.executeQuery();

            List<Writer> list = new ArrayList<>();
            Map<Integer, Writer> map = new HashMap<>();

            while (rs.next()) {
                Writer obj = new Writer();
                obj.setWriterID(rs.getInt("id_escritor"));
                obj.setName(rs.getString("nome"));

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
    public void insert(Object entity) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO Escritor (nome) " + "VALUES " + "(?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            Writer obj = (Writer) entity;
            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setWriterID(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            // DB.closeConnection();
        }
    }

    @Override
    public List findAll() {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id_escritor, nome "
                            + "FROM Escritor "
                            + "ORDER BY nome");

            rs = st.executeQuery();

            List<Writer> list = new ArrayList<>();
            Map<Integer, Writer> map = new HashMap<>();

            while (rs.next()) {
                Writer obj = new Writer();
                obj.setWriterID(rs.getInt("id_escritor"));
                obj.setName(rs.getString("nome"));

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
    public void relateWriterToProduct(Writer writer, Product product) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO escritor_produto (id_escritor, id_produto) " + "VALUES " + "(?, ?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, writer.getWriterID());
            st.setInt(2, product.getProductID());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();

                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            // DB.closeConnection();
        }
    }

    // TODO: This method has not been implemented because it will only be used with functionality used in the report formulation and administration of the site, which is not in this scope.
    @Override
    public void deleteById(Long id) {
    }

    @Override
    public void update(Object entity) {
        Connection conn = DB.getConnection();

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Escritor " +
                            "SET nome= ? " +
                            "WHERE id_usuario= ?");

            Writer obj = (Writer) entity;
            st.setString(1, obj.getName());
            st.setInt(2, obj.getWriterID());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            // DB.closeConnection();
        }
    }
}