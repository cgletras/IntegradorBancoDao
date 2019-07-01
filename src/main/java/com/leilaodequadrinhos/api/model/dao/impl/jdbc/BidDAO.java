package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.*;
import com.leilaodequadrinhos.api.model.entities.Auction;
import com.leilaodequadrinhos.api.model.entities.Bid;
import com.leilaodequadrinhos.api.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BidDAO implements BidDao {

    Connection conn = DB.getConnection();

    @Override
    public void insert(Object entity) {

        PreparedStatement st = null;
        try {

            conn.setAutoCommit(false);
            st = conn.prepareStatement(
                    "INSERT INTO Lance (valor_lance, id_leilao, id_usuario) " + "VALUES " + "(?, ?, ?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            Bid obj = (Bid) entity;
            st.setDouble(1, obj.getBidValue());
            st.setLong(2, obj.getAuction().getAuctionID());
            st.setInt(3, obj.getUser().getUserID());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setBidID(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }

            //Increases the current value of the auction
            AuctionDao auctionDao = new AuctionDAO();
            Auction auction = (Auction) auctionDao.findById(obj.getAuction().getAuctionID());
            auctionDao.updateInitialValue(auction);
            // --

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Any operations failed: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Failed the rollback");
            }
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
                    "SELECT * FROM Lance "
                            + "WHERE id_lance = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Bid obj = new Bid();
                obj.setBidID(rs.getInt("id_lance"));
                obj.setBidValue(rs.getDouble("valor_lance"));
                obj.setBidDate(new java.sql.Date(rs.getDate("data_lance").getTime()));
                //
                AuctionDao auctionDao = new AuctionDAO();
                Auction auction = (Auction) auctionDao.findById(rs.getLong("id_leilao"));
                obj.setAuction(auction);
                //
                DAO dao = new UserDAO();
                User user = (User) dao.findById(rs.getLong("id_usuario"));
                obj.setUser(user);

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
    public List<Bid> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id_lance, valor_lance, data_lance, id_leilao, id_usuario "
                            + "FROM Lance "
                            + "ORDER BY id_lance");

            rs = st.executeQuery();

            List<Bid> list = new ArrayList<>();
            Map<Integer, Bid> map = new HashMap<>();

            while (rs.next()) {
                Bid obj = new Bid();
                obj.setBidID(rs.getInt("id_lance"));
                obj.setBidValue(rs.getDouble("valor_lance"));
                obj.setBidDate(new java.sql.Date(rs.getDate("data_lance").getTime()));
                //
                AuctionDao auctionDao = new AuctionDAO();
                Auction auction = (Auction) auctionDao.findById(rs.getLong("id_leilao"));
                obj.setAuction(auction);
                //
                DAO dao = new UserDAO();
                User user = (User) dao.findById(rs.getLong("id_usuario"));
                obj.setUser(user);

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

    //This method has not been implemented because it will only be used with functionality used in the report formulation and administration of the site, which is not in this scope.
    @Override
    public void deleteById(Long id) {

    }

    //This method has not been implemented because it will only be used with functionality used in the report formulation and administration of the site, which is not in this scope.
    @Override
    public void update(Object entity) {

    }

    @Override
    public List<Bid> findBidsByUser(Long id_user) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id_lance, valor_lance, data_lance, id_leilao, id_usuario "
                            + "FROM Lance "
                            + "WHERE id_usuario = ? "
                            + "ORDER BY id_lance");

            st.setLong(1, id_user);
            rs = st.executeQuery();

            List<Bid> list = new ArrayList<>();
            Map<Integer, Bid> map = new HashMap<>();

            while (rs.next()) {
                Bid obj = new Bid();
                obj.setBidID(rs.getInt("id_lance"));
                obj.setBidValue(rs.getDouble("valor_lance"));
                obj.setBidDate(new java.sql.Date(rs.getDate("data_lance").getTime()));
                //
                AuctionDao auctionDao = new AuctionDAO();
                Auction auction = (Auction) auctionDao.findById(rs.getLong("id_leilao"));
                obj.setAuction(auction);
                //
                DAO dao = new UserDAO();
                User user = (User) dao.findById(rs.getLong("id_usuario"));
                obj.setUser(user);

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
    public List<Bid> findBidsByAuction(Long auctionID) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id_lance, valor_lance, data_lance, id_leilao, id_usuario "
                            + "FROM Lance "
                            + "WHERE id_leilao = ? "
                            + "ORDER BY id_lance");

            st.setLong(1, auctionID);
            rs = st.executeQuery();

            List<Bid> list = new ArrayList<>();
            Map<Integer, Bid> map = new HashMap<>();

            while (rs.next()) {
                Bid obj = new Bid();
                obj.setBidID(rs.getInt("id_lance"));
                obj.setBidValue(rs.getDouble("valor_lance"));
                obj.setBidDate(new java.sql.Date(rs.getDate("data_lance").getTime()));
                //
                AuctionDao auctionDao = new AuctionDAO();
                Auction auction = (Auction) auctionDao.findById(rs.getLong("auctionID"));
                obj.setAuction(auction);
                //
                DAO dao = new UserDAO();
                User user = (User) dao.findById(rs.getLong("id_usuario"));
                obj.setUser(user);

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
    public Long BidCount(Long auctionID) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT COUNT(id_lance) count "
                            + "FROM Lance "
                            + "WHERE id_leilao = ? ");

            st.setLong(1, auctionID);
            rs = st.executeQuery();

            Long count = null;
            Map<Integer, User> map = new HashMap<>();

            while (rs.next()) {
                count = rs.getLong("count");
            }
            return count;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }
}