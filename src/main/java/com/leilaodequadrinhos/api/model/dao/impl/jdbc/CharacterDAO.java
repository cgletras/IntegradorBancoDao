package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.CharacterDao;
import com.leilaodequadrinhos.api.model.entities.Character;
import com.leilaodequadrinhos.api.model.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterDAO implements CharacterDao {

    @Override
    public Object findById(Long id) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Personagem "
                            + "WHERE id_personagem = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Character obj = new Character();
                obj.setCharacterID(rs.getInt("id_personagem"));
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
    public List<Character> findAllByProduct(Product product) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT pp.id_personagem, p.nome " +
                            "FROM personagem_produto pp INNER JOIN Personagem p " +
                            "ON pp.id_personagem = p.id_personagem " +
                            "WHERE id_produto = ?");

            st.setInt(1, product.getProductID());

            rs = st.executeQuery();

            List<Character> list = new ArrayList<>();
            Map<Integer, Character> map = new HashMap<>();

            while (rs.next()) {
                Character obj = new Character();
                obj.setCharacterID(rs.getInt("id_personagem"));
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
    public List findAll() {
        Connection conn = DB.getConnection();

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id_personagem, nome "
                            + "FROM Personagem "
                            + "ORDER BY nome");

            rs = st.executeQuery();

            List<Character> list = new ArrayList<>();
            Map<Integer, Character> map = new HashMap<>();

            while (rs.next()) {
                Character obj = new Character();
                obj.setCharacterID(rs.getInt("id_personagem"));
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
                    "INSERT INTO Personagem (nome) " + "VALUES " + "(?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            Character obj = (Character) entity;
            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setCharacterID(id);
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
    public void relateCharacterToProduct(Character character, Product product) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO personagem_produto (id_personagem, id_produto) " + "VALUES " + "(?, ?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            st.setInt(1, character.getCharacterID());
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
                    "UPDATE Personagem " +
                            "SET nome= ? " +
                            "WHERE id_personagem= ?");

            Character obj = (Character) entity;
            st.setString(1, obj.getName());
            st.setInt(2, obj.getCharacterID());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            // DB.closeConnection();
        }
    }
}