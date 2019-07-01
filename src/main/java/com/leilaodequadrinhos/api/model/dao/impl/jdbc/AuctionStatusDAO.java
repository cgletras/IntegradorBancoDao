package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.AuctionStatusDao;
import com.leilaodequadrinhos.api.model.entities.AuctionStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuctionStatusDAO implements AuctionStatusDao {

    @Override
    public AuctionStatus findById(Long id) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Estado_leilao "
                            + "WHERE id_estado_leilao = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                AuctionStatus obj = new AuctionStatus();
                obj.setAuctionStatusID(rs.getInt("id_estado_leilao"));
                obj.setStatus(rs.getString("estado"));
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            // DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }

    @Override
    public List<AuctionStatus> findAll() {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id_estado_leilao, estado "
                            + "FROM Estado_leilao "
                            + "ORDER BY estado");

            rs = st.executeQuery();

            List<AuctionStatus> list = new ArrayList<>();
            Map<Integer, AuctionStatus> map = new HashMap<>();

            while (rs.next()) {
                AuctionStatus obj = new AuctionStatus();
                obj.setAuctionStatusID(rs.getInt("id_estado_leilao"));
                obj.setStatus(rs.getString("estado"));

                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            // DB.closeResultSet(rs);
            DB.closeConnection();
        }
    }
}