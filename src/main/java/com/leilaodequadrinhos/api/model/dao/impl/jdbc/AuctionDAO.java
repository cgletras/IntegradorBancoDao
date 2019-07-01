package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.*;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.AuctionStatus;
import com.leilaodequadrinhos.api.model.entities.Product;
import com.leilaodequadrinhos.api.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionDAO implements AuctionDao {

    Connection conn = DB.getConnection();

    @Override
    public void insert(Object entity) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO Leilao (data_inicio, duracao, valor_inicial, valor_atual, lance_padrao, id_estado_leilao, id_usuario, id_produto) " + "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            Auction obj = (Auction) entity;
            st.setDate(1, new java.sql.Date(obj.getInitialDate().getTime()));
            st.setInt(2, obj.getDuration());
            st.setDouble(3, obj.getInitialValue());
            st.setDouble(4, obj.getInitialValue());
            st.setDouble(5, obj.getDefaultBid());
            st.setInt(6, obj.getAuctionStatus().getAuctionStatusID());
            st.setInt(7, obj.getUser().getUserID());
            System.out.println(obj.getProduct());
            st.setInt(8, obj.getProduct().getProductID());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    obj.setAuctionID(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public void update(Object entity) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Leilao " +
                            "SET data_inicio= ?, duracao= ?, valor_inicial= ?, valor_atual= ?, lance_padrao= ?, id_estado_leilao= ?, id_usuario= ?, id_produto= ? " +
                            "WHERE id_leilao= ?");

            Auction obj = (Auction) entity;
            st.setDate(1, new java.sql.Date(obj.getInitialDate().getTime()));
            st.setInt(2, obj.getDuration());
            st.setDouble(3, obj.getInitialValue());
            st.setDouble(4, obj.getInitialValue());
            st.setDouble(5, obj.getDefaultBid());
            st.setInt(6, obj.getAuctionStatus().getAuctionStatusID());
            st.setInt(7, obj.getUser().getUserID());
            st.setInt(8, obj.getProduct().getProductID());
            st.setLong(9, obj.getAuctionID());

            st.executeUpdate();
            System.out.println("Successfully updated auction");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public void updateInitialValue(Auction obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Leilao " +
                            "SET valor_atual= (valor_atual + ?) " +
                            "WHERE id_leilao= ?");

            st.setDouble(1, obj.getDefaultBid());
            st.setLong(2, obj.getAuctionID());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public Object findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Leilao "
                            + "WHERE id_leilao = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Auction obj = new Auction();

                obj.setAuctionID(rs.getLong("id_leilao"));
                obj.setInitialDate(new java.sql.Date(rs.getDate("data_inicio").getTime()));
                obj.setDuration(rs.getInt("duracao"));
                obj.setInitialValue(rs.getDouble("valor_inicial"));
                obj.setCurrentValue(rs.getDouble("valor_atual"));
                obj.setDefaultBid(rs.getDouble("lance_padrao"));

                AuctionStatusDao auctionStatusDAO = new AuctionStatusDAO();
                AuctionStatus auctionStatus = auctionStatusDAO.findById(rs.getLong("id_estado_leilao"));
                obj.setAuctionStatus(auctionStatus);

                DAO dao = new UserDAO();
                User user = (User) dao.findById(rs.getLong("id_usuario"));
                obj.setUser(user);

                DAO dao2 = new ProductDAO();
                Product product = (Product) dao2.findById(rs.getLong("id_produto"));
                obj.setProduct(product);

                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }

    @Override
    public List<Auction> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id_leilao, data_inicio, duracao, valor_inicial, valor_atual, lance_padrao, id_estado_leilao, id_usuario, id_produto "
                            + "FROM Leilao ");

            rs = st.executeQuery();

            List<Auction> list = new ArrayList<>();
            Map<Integer, Auction> map = new HashMap<>();

            while (rs.next()) {
                Auction obj = new Auction();

                obj.setAuctionID(rs.getLong("id_leilao"));
                obj.setInitialDate(new java.sql.Date(rs.getDate("data_inicio").getTime()));
                obj.setDuration(rs.getInt("duracao"));
                obj.setInitialValue(rs.getDouble("valor_inicial"));
                obj.setCurrentValue(rs.getDouble("valor_atual"));
                obj.setDefaultBid(rs.getDouble("lance_padrao"));

                AuctionStatusDao auctionStatusDAO = new AuctionStatusDAO();
                AuctionStatus auctionStatus = auctionStatusDAO.findById(rs.getLong("id_estado_leilao"));
                obj.setAuctionStatus(auctionStatus);

                DAO dao = new UserDAO();
                User user = (User) dao.findById(rs.getLong("id_usuario"));
                obj.setUser(user);

                DAO dao2 = new ProductDAO();
                Product product = (Product) dao2.findById(rs.getLong("id_produto"));
                obj.setProduct(product);

                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }

    @Override
    public void deleteById(Long id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Leilao " +
                            "SET id_estado_leilao= ? " +
                            "WHERE id_leilao= ?");

            st.setInt(1, 2);
            st.setLong(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Auction> findByUser(User user) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id_leilao, data_inicio, duracao, valor_inicial, valor_atual, lance_padrao, id_estado_leilao, id_usuario, id_produto "
                            + "FROM Leilao "
                            + "WHERE id_usuario = ?");

            st.setInt(1, user.getUserID());
            rs = st.executeQuery();

            List<Auction> list = new ArrayList<>();
            Map<Integer, Auction> map = new HashMap<>();

            while (rs.next()) {
                Auction auction = new Auction();

                auction.setAuctionID(rs.getLong("id_leilao"));
                auction.setInitialDate(new java.sql.Date(rs.getDate("data_inicio").getTime()));
                auction.setDuration(rs.getInt("duracao"));
                auction.setInitialValue(rs.getDouble("valor_inicial"));
                auction.setCurrentValue(rs.getDouble("valor_atual"));
                auction.setDefaultBid(rs.getDouble("lance_padrao"));

                AuctionStatusDao auctionStatusDAO = new AuctionStatusDAO();
                AuctionStatus auctionStatus = auctionStatusDAO.findById(rs.getLong("id_estado_leilao"));
                auction.setAuctionStatus(auctionStatus);

                DAO dao = new UserDAO();
                user = (User) dao.findById(rs.getLong("id_usuario"));
                auction.setUser(user);

                DAO dao2 = new ProductDAO();
                Product product = (Product) dao2.findById(rs.getLong("id_produto"));
                auction.setProduct(product);

                list.add(auction);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }

    @Override
    public void changesAuctionStatus(Long id, AuctionStatus auctionStatus) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Leilao " +
                            "SET id_estado_leilao= ? " +
                            "WHERE id_leilao= ?");

            st.setInt(1, auctionStatus.getAuctionStatusID());
            st.setLong(2, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public void setAuctionDateNow(Long id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Leilao " +
                            "SET data_inicio = now() " +
                            "WHERE id_leilao= ?");

            st.setLong(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}