package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.DAO;
import com.leilaodequadrinhos.api.model.dao.DaoFactory;
import com.leilaodequadrinhos.api.model.dao.EstadoProdutoDao;
import com.leilaodequadrinhos.api.model.entities.EstadoProduto;
import com.leilaodequadrinhos.api.model.entities.Produto;
import com.leilaodequadrinhos.api.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProdutoDAO implements DAO {

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

                EstadoProdutoDao estadoProdutoDao = DaoFactory.createEstadoProdutoDao();
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

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Object entity) {

    }


//    @Override
//    public Produto findById(Integer id) {
//        PreparedStatement st = null;
//        ResultSet rs = null;
//        try {
//            st = conn.prepareStatement(
//                    "SELECT * FROM Produto "
//                            + "WHERE id_produto = ?");
//
//            st.setInt(1, id);
//            rs = st.executeQuery();
//
//            if (rs.next()) {
//                Produto obj = new Produto();
//                obj.setIdProduto(rs.getInt("id_produto"));
//                obj.setEditora(rs.getString("editora"));
//                obj.setTitulo(rs.getString("titulo"));
//                obj.setFormatoDoQuadrinho(rs.getString("formato_do_quadrinho"));
//                obj.setNumeroPaginas(rs.getInt("numero_paginas"));
//                obj.setPeso(rs.getInt("peso"));
//                obj.setCapaImagem(rs.getString("capa_imagem"));
//
//                EstadoProdutoDao estadoProdutoDao = DaoFactory.createEstadoProdutoDao();
//                EstadoProduto estadoProduto = estadoProdutoDao.findById(rs.getInt("id_estado_produto"));
//                obj.setEstado(estadoProduto);
//
////				UserDao userDao = DaoFactory.createUsuarioDao();
////				User user = userDao.findById(rs.getInt("id_usuario"));
////				obj.setUser(user);
//
//                return obj;
//            }
//            return null;
//        } catch (SQLException e) {
//            throw new DbException(e.getMessage());
//        } finally {
//            DB.closeStatement(st);
//            DB.closeResultSet(rs);
//        }
//    }
//
//    @Override
//    public List<Produto> findAllByUser(User usuario) {
//        PreparedStatement st = null;
//        ResultSet rs = null;
//        try {
//            st = conn.prepareStatement(
//                    "SELECT * FROM Produto "
//                            + "WHERE id_usuario = ?");
//
//            st.setInt(1, usuario.getUserID());
//            rs = st.executeQuery();
//            List<Produto> produtos = new ArrayList<Produto>();
//
//            while (rs.next()) {
//                Produto obj = new Produto();
//                obj.setIdProduto(rs.getInt("id_produto"));
//                obj.setEditora(rs.getString("editora"));
//                obj.setTitulo(rs.getString("titulo"));
//                obj.setFormatoDoQuadrinho(rs.getString("formato_do_quadrinho"));
//                obj.setNumeroPaginas(rs.getInt("numero_paginas"));
//                obj.setPeso(rs.getInt("peso"));
//                obj.setCapaImagem(rs.getString("capa_imagem"));
//
//                EstadoProdutoDao estadoProdutoDao = DaoFactory.createEstadoProdutoDao();
//                EstadoProduto estadoProduto = estadoProdutoDao.findById(rs.getInt("id_estado_produto"));
//                obj.setEstado(estadoProduto);
//
////				UserDao userDao = DaoFactory.createUsuarioDao();
////				User user = userDao.findById(rs.getInt("id_usuario"));
////				obj.setUser(user);
//
//                produtos.add(obj);
//            }
//            return produtos;
//        } catch (SQLException e) {
//            throw new DbException(e.getMessage());
//        } finally {
//            DB.closeStatement(st);
//            DB.closeResultSet(rs);
//        }
//    }
//
//    @Override
//    public void insertProduct(Produto obj) {
//        PreparedStatement st = null;
//        try {
//            st = conn.prepareStatement(
//                    "INSERT INTO Produto (editora, titulo, formato_do_quadrinho, numero_paginas, peso, capa_imagem, id_estado_produto, id_usuario) " + "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)",
//                    java.sql.Statement.RETURN_GENERATED_KEYS);
//
//            st.setString(1, obj.getEditora());
//            st.setString(2, obj.getTitulo());
//            st.setString(3, obj.getFormatoDoQuadrinho());
//            st.setInt(4, obj.getNumeroPaginas());
//            st.setInt(5, obj.getPeso());
//            st.setString(6, obj.getCapaImagem());
//            st.setInt(7, obj.getEstado().getIdEstadoProduto());
//            st.setInt(8, obj.getUser().getUserID());
//
//            int rowsAffected = st.executeUpdate();
//
//            if (rowsAffected > 0) {
//                ResultSet rs = st.getGeneratedKeys();
//                if (rs.next()) {
//                    int id = rs.getInt(1);
//                    obj.setIdProduto(id);
//                }
//                DB.closeResultSet(rs);
//            } else {
//                throw new DbException("Unexpected error! No rows affected!");
//            }
//        } catch (SQLException e) {
//            throw new DbException(e.getMessage());
//        } finally {
//            DB.closeStatement(st);
//        }
//    }
//
//    @Override
//    public void changeStatusProduct(Integer id, EstadoProduto estado) {
//        PreparedStatement st = null;
//        try {
//            st = conn.prepareStatement(
//                    "UPDATE Produto " +
//                            "SET id_estado_produto= ? " +
//                            "WHERE id_produto= ?");
//
//            st.setInt(1, estado.getIdEstadoProduto());
//            st.setInt(2, id);
//
//            st.executeUpdate();
//        } catch (SQLException e) {
//            throw new DbException(e.getMessage());
//        } finally {
//            DB.closeStatement(st);
//        }
//
//    }
//
//    @Override
//    public void updateProduct(Produto obj) {
//        PreparedStatement st = null;
//        try {
//            st = conn.prepareStatement(
//                    "UPDATE Produto "
//                            + "SET editora= ?, titulo= ?, formato_do_quadrinho= ?, numero_paginas= ?, peso= ?, capa_imagem= ?, id_estado_produto= ?, id_usuario= ? "
//                            + "WHERE id_produto= ?",
//                    java.sql.Statement.RETURN_GENERATED_KEYS);
//
//            st.setString(1, obj.getEditora());
//            st.setString(2, obj.getTitulo());
//            st.setString(3, obj.getFormatoDoQuadrinho());
//            st.setInt(4, obj.getNumeroPaginas());
//            st.setInt(5, obj.getPeso());
//            st.setString(6, obj.getCapaImagem());
//            st.setInt(7, obj.getEstado().getIdEstadoProduto());
//            st.setInt(8, obj.getUser().getUserID());
//            st.setInt(9, obj.getIdProduto());
//
//            int rowsAffected = st.executeUpdate();
//
//            if (rowsAffected > 0) {
//                ResultSet rs = st.getGeneratedKeys();
//                if (rs.next()) {
//                    int id = rs.getInt(1);
//                    obj.setIdProduto(id);
//                }
//                DB.closeResultSet(rs);
//            } else {
//                throw new DbException("Unexpected error! No rows affected!");
//            }
//        } catch (SQLException e) {
//            throw new DbException(e.getMessage());
//        } finally {
//            DB.closeStatement(st);
//        }
//
//    }
}
