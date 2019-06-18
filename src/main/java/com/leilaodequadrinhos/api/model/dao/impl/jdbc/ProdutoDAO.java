package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.EstadoProdutoDao;
import com.leilaodequadrinhos.api.model.dao.ProdutoDao;
import com.leilaodequadrinhos.api.model.entities.EstadoProduto;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoDAO implements ProdutoDao {

    Connection conn = DB.getConnection();

    @Override
    public Produto findById(Long id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Produto "
                            + "WHERE id_produto = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Produto obj = new Produto();
                obj.setIdProduto(rs.getInt("id_produto"));
                obj.setEditora(rs.getString("editora"));
                obj.setTitulo(rs.getString("titulo"));
                obj.setFormatoDoQuadrinho(rs.getString("formato_do_quadrinho"));
                obj.setNumeroPaginas(rs.getInt("numero_paginas"));
                obj.setPeso(rs.getInt("peso"));
                obj.setCapaImagem(rs.getString("capa_imagem"));

                EstadoProdutoDao estadoProdutoDao = new EstadoProdutoDAO();
                EstadoProduto estadoProduto = estadoProdutoDao.findById(rs.getInt("id_estado_produto"));
                obj.setEstado(estadoProduto);

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
        }
    }

    // TODO Este metodo não foi implementado pois será somente utilizado com funcionalidade utilizada na formaulação relatorio e adminsitração do site, o que nãi esta no scope atual.
    @Override
    public List findAll() {
        return null; // Não é necessario um metodo pra listar todos os produtos no site. A lista nesse escopo é por usuario e esta implementada em outra função
    }

    // TODO Este metodo não foi implementado pois será somente utilizado com funcionalidade utilizada na formaulação relatorio e adminsitração do site, o que nãi esta no scope atual.
    @Override
    public Long count() {
        return null;
    }

    @Override
    public void deleteById(Long id) {
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
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Object entity) {

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Produto "
                            + "SET editora= ?, titulo= ?, formato_do_quadrinho= ?, numero_paginas= ?, peso= ?, capa_imagem= ?, id_estado_produto= ?, id_usuario= ? "
                            + "WHERE id_produto= ?",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            Produto obj = (Produto) entity;
            st.setString(1, obj.getEditora());
            st.setString(2, obj.getTitulo());
            st.setString(3, obj.getFormatoDoQuadrinho());
            st.setInt(4, obj.getNumeroPaginas());
            st.setInt(5, obj.getPeso());
            st.setString(6, obj.getCapaImagem());
            st.setInt(7, obj.getEstado().getIdEstadoProduto());
            st.setInt(8, obj.getUser().getUserID());
            st.setInt(9, obj.getIdProduto());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setIdProduto(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void insert(Object entity) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO Produto (editora, titulo, formato_do_quadrinho, numero_paginas, peso, capa_imagem, id_estado_produto, id_usuario) " + "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            Produto obj = (Produto) entity;
            st.setString(1, obj.getEditora());
            st.setString(2, obj.getTitulo());
            st.setString(3, obj.getFormatoDoQuadrinho());
            st.setInt(4, obj.getNumeroPaginas());
            st.setInt(5, obj.getPeso());
            st.setString(6, obj.getCapaImagem());
            st.setInt(7, obj.getEstado().getIdEstadoProduto());
            st.setInt(8, obj.getUser().getUserID());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setIdProduto(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void changeStatusProduct(Integer id, EstadoProduto estado) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Produto " +
                            "SET id_estado_produto= ? " +
                            "WHERE id_produto= ?");

            st.setInt(1, estado.getIdEstadoProduto());
            st.setInt(2, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Produto> findAllByUser(Long UserId) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Produto "
                            + "WHERE id_usuario = ?");

            st.setLong(1, UserId);
            rs = st.executeQuery();

            List<Produto> produtos = new ArrayList<>();
            Map<Integer, Produto> map = new HashMap<>();

            while (rs.next()) {
                Produto obj = new Produto();
                obj.setIdProduto(rs.getInt("id_produto"));
                obj.setEditora(rs.getString("editora"));
                obj.setTitulo(rs.getString("titulo"));
                obj.setFormatoDoQuadrinho(rs.getString("formato_do_quadrinho"));
                obj.setNumeroPaginas(rs.getInt("numero_paginas"));
                obj.setPeso(rs.getInt("peso"));
                obj.setCapaImagem(rs.getString("capa_imagem"));

                EstadoProdutoDao estadoProdutoDao = new EstadoProdutoDAO();
                EstadoProduto estadoProduto = estadoProdutoDao.findById(rs.getInt("id_estado_produto"));
                obj.setEstado(estadoProduto);

                DAO dao = new UserDAO();
                User user = (User) dao.findById(rs.getLong("id_usuario"));
                obj.setUser(user);

                produtos.add(obj);
            }
            return produtos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
