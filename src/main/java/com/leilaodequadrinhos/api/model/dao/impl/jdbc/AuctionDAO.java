package com.leilaodequadrinhos.api.model.dao.impl.jdbc;

import com.leilaodequadrinhos.api.db.DB;
import com.leilaodequadrinhos.api.db.DbException;
import com.leilaodequadrinhos.api.model.dao.*;
import com.leilaodequadrinhos.api.model.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AuctionDAO implements AuctionDao {

    @Override
    public void insert(Object entity) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO Leilao (data_inicio, data_fim, valor_inicial, valor_atual, lance_padrao, id_estado_leilao, id_usuario, id_produto) " + "VALUES " + "(?, ?, ?, ?, ?, ?, ?, ?)",
                    java.sql.Statement.RETURN_GENERATED_KEYS);

            Auction obj = (Auction) entity;
            st.setDate(1, new java.sql.Date(obj.getInitialDate().getTime()));
            // Incrementa a duração em dias a data inicial para enviar a data_fim para o banco de dados.
            Date dataInicio = obj.getInitialDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataInicio);
            cal.add(Calendar.DAY_OF_MONTH, obj.getDuration());
            Date dataFim = cal.getTime();
            //
            st.setDate(2, new java.sql.Date(dataFim.getTime()));
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
            // DB.closeConnection();
        }
    }

    @Override
    public void update(Object entity) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Leilao " +
                            "SET data_inicio= ?, data_fim= ?, valor_inicial= ?, valor_atual= ?, lance_padrao= ?, id_estado_leilao= ?, id_usuario= ?, id_produto= ? " +
                            "WHERE id_leilao= ?");

            Auction obj = (Auction) entity;
            st.setDate(1, new java.sql.Date(obj.getInitialDate().getTime()));
            // Incrementa a duração em dias a data inicial para enviar a data_fim para o banco de dados.
            Date dataInicio = obj.getInitialDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataInicio);
            cal.add(Calendar.DAY_OF_MONTH, obj.getDuration());
            Date dataFim = cal.getTime();
            //
            st.setDate(2, new java.sql.Date(dataFim.getTime()));
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
            // DB.closeConnection();
        }
    }

    @Override
    public void updateInitialValue(Auction obj) {
        Connection conn = DB.getConnection();
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
            // DB.closeConnection();
        }
    }

    @Override
    public Object findById(Long id) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT l.id_leilao, data_inicio, DATEDIFF(data_fim, now()) duracao, valor_inicial, valor_atual, lance_padrao, l.id_estado_leilao, l.id_usuario, l.id_produto, " +
                            "el.estado estado_leilao, " +
                            "nome, email, senha, cidade, u.estado, data_nascimento, ativo, " +
                            "editora, titulo, formato_do_quadrinho, numero_paginas, peso, capa_imagem, " +
                            "p.id_estado_produto, ep.estado estado_produto " +
                            "FROM Leilao l INNER JOIN Usuario u ON l.id_usuario = u.id_usuario " +
                            "INNER JOIN Estado_leilao el ON l.id_estado_leilao = el.id_estado_leilao " +
                            "INNER JOIN Produto p ON l.id_produto = p.id_produto " +
                            "INNER JOIN Estado_produto ep ON p.id_estado_produto = ep.id_estado_produto " +
                            "WHERE id_leilao = ? ");

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

                AuctionStatus auctionStatus = new AuctionStatus();
                auctionStatus.setAuctionStatusID(rs.getInt("id_estado_leilao"));
                auctionStatus.setStatus(rs.getString("estado_leilao"));
                obj.setAuctionStatus(auctionStatus);

                User user = new User();
                user.setUserID(rs.getInt("id_usuario"));
                user.setName(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setState(rs.getString("estado"));
                user.setCity(rs.getString("cidade"));
                user.setPassword(null);
                user.setDateOfBirth(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
                user.setStatus(rs.getBoolean("ativo"));
                obj.setUser(user);

                ProductStatus productStatus = new ProductStatus();
                productStatus.setProductStatusID(rs.getInt("id_estado_produto"));
                productStatus.setStatus(rs.getString("estado_produto"));

                Product product = new Product();
                product.setProductID(rs.getInt("id_produto"));
                product.setPublisher(rs.getString("editora"));
                product.setTitle(rs.getString("titulo"));
                product.setComicFormat(rs.getString("formato_do_quadrinho"));
                product.setPagesNumber(rs.getInt("numero_paginas"));
                product.setWeight(rs.getInt("peso"));
                product.setCoverImage(rs.getString("capa_imagem"));
                product.setUser(user);
                product.setProductStatus(productStatus);
                obj.setProduct(product);

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
    public List<Auction> findAll() {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT l.id_leilao, data_inicio, DATEDIFF(data_fim, now()) duracao, valor_inicial, valor_atual, lance_padrao, l.id_estado_leilao, l.id_usuario, l.id_produto, " +
                            "el.estado estado_leilao, " +
                            "nome, email, senha, cidade, u.estado, data_nascimento, ativo, " +
                            "editora, titulo, formato_do_quadrinho, numero_paginas, peso, capa_imagem, " +
                            "p.id_estado_produto, ep.estado estado_produto " +
                            "FROM Leilao l INNER JOIN Usuario u ON l.id_usuario = u.id_usuario " +
                            "INNER JOIN Estado_leilao el ON l.id_estado_leilao = el.id_estado_leilao " +
                            "INNER JOIN Produto p ON l.id_produto = p.id_produto " +
                            "INNER JOIN Estado_produto ep ON p.id_estado_produto = ep.id_estado_produto " +
                            "GROUP BY id_leilao");

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

                AuctionStatus auctionStatus = new AuctionStatus();
                auctionStatus.setAuctionStatusID(rs.getInt("id_estado_leilao"));
                auctionStatus.setStatus(rs.getString("estado_leilao"));
                obj.setAuctionStatus(auctionStatus);

                User user = new User();
                user.setUserID(rs.getInt("id_usuario"));
                user.setName(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setState(rs.getString("estado"));
                user.setCity(rs.getString("cidade"));
                user.setPassword(null);
                user.setDateOfBirth(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
                user.setStatus(rs.getBoolean("ativo"));
                obj.setUser(user);

                ProductStatus productStatus = new ProductStatus();
                productStatus.setProductStatusID(rs.getInt("id_estado_produto"));
                productStatus.setStatus(rs.getString("estado_produto"));

                Product product = new Product();
                product.setProductID(rs.getInt("id_produto"));
                product.setPublisher(rs.getString("editora"));
                product.setTitle(rs.getString("titulo"));
                product.setComicFormat(rs.getString("formato_do_quadrinho"));
                product.setPagesNumber(rs.getInt("numero_paginas"));
                product.setWeight(rs.getInt("peso"));
                product.setCoverImage(rs.getString("capa_imagem"));
                product.setUser(user);
                product.setProductStatus(productStatus);
                obj.setProduct(product);

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
    public List<Auction> findAllPaginate(Integer limit, Integer offset, String columnToOrderBy, String directionToOrderBy, String titleToSearch, List<String> publishingCompanys) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {

            st = conn.prepareStatement(
                    "SELECT * FROM (SELECT l.id_leilao, data_inicio, DATEDIFF(data_fim, now()) duracao, valor_inicial, valor_atual, lance_padrao, l.id_estado_leilao, l.id_usuario, l.id_produto, " +
                            "el.estado estado_leilao, " +
                            "nome, email, senha, cidade, u.estado, data_nascimento, ativo, " +
                            "editora, titulo, formato_do_quadrinho, numero_paginas, peso, capa_imagem, " +
                            "p.id_estado_produto, ep.estado estado_produto " +
                            "FROM Leilao l INNER JOIN Usuario u ON l.id_usuario = u.id_usuario " +
                            "INNER JOIN Estado_leilao el ON l.id_estado_leilao = el.id_estado_leilao " +
                            "INNER JOIN Produto p ON l.id_produto = p.id_produto " +
                            "INNER JOIN Estado_produto ep ON p.id_estado_produto = ep.id_estado_produto " +
                            "WHERE l.id_estado_leilao IN (1) AND p.id_estado_produto = 3 " +
                            "AND p.titulo LIKE ('%" + titleToSearch + "%') " +
                            "AND p.editora IN ('" + String.join("','", publishingCompanys) + "') " +
                            "GROUP BY id_leilao ) AS result " +
                            "ORDER BY " + columnToOrderBy + " " + directionToOrderBy + " " +
                            "LIMIT " + limit +
                            " OFFSET " + offset
            );

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

                AuctionStatus auctionStatus = new AuctionStatus();
                auctionStatus.setAuctionStatusID(rs.getInt("id_estado_leilao"));
                auctionStatus.setStatus(rs.getString("estado_leilao"));
                obj.setAuctionStatus(auctionStatus);

                User user = new User();
                user.setUserID(rs.getInt("id_usuario"));
                user.setName(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setState(rs.getString("estado"));
                user.setCity(rs.getString("cidade"));
                user.setPassword(null);
                user.setDateOfBirth(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
                user.setStatus(rs.getBoolean("ativo"));
                obj.setUser(user);

                ProductStatus productStatus = new ProductStatus();
                productStatus.setProductStatusID(rs.getInt("id_estado_produto"));
                productStatus.setStatus(rs.getString("estado_produto"));

                Product product = new Product();
                product.setProductID(rs.getInt("id_produto"));
                product.setPublisher(rs.getString("editora"));
                product.setTitle(rs.getString("titulo"));
                product.setComicFormat(rs.getString("formato_do_quadrinho"));
                product.setPagesNumber(rs.getInt("numero_paginas"));
                product.setWeight(rs.getInt("peso"));
                product.setCoverImage(rs.getString("capa_imagem"));
                product.setUser(user);
                product.setProductStatus(productStatus);
                obj.setProduct(product);

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
    public List<Auction> findAllActiveAuctions() {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT l.id_leilao, data_inicio, DATEDIFF(data_fim, now()) duracao, valor_inicial, valor_atual, lance_padrao, l.id_estado_leilao, l.id_usuario, l.id_produto, " +
                            "el.estado estado_leilao, " +
                            "nome, email, senha, cidade, u.estado, data_nascimento, ativo, " +
                            "editora, titulo, formato_do_quadrinho, numero_paginas, peso, capa_imagem, " +
                            "p.id_estado_produto, ep.estado estado_produto " +
                            "FROM Leilao l INNER JOIN Usuario u ON l.id_usuario = u.id_usuario " +
                            "INNER JOIN Estado_leilao el ON l.id_estado_leilao = el.id_estado_leilao " +
                            "INNER JOIN Produto p ON l.id_produto = p.id_produto " +
                            "INNER JOIN Estado_produto ep ON p.id_estado_produto = ep.id_estado_produto " +
                            "WHERE l.id_estado_leilao = 1 " +
                            "GROUP BY id_leilao");

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

                AuctionStatus auctionStatus = new AuctionStatus();
                auctionStatus.setAuctionStatusID(rs.getInt("id_estado_leilao"));
                auctionStatus.setStatus(rs.getString("estado_leilao"));
                obj.setAuctionStatus(auctionStatus);

                User user = new User();
                user.setUserID(rs.getInt("id_usuario"));
                user.setName(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setState(rs.getString("estado"));
                user.setCity(rs.getString("cidade"));
                user.setPassword(null);
                user.setDateOfBirth(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
                user.setStatus(rs.getBoolean("ativo"));
                obj.setUser(user);

                ProductStatus productStatus = new ProductStatus();
                productStatus.setProductStatusID(rs.getInt("id_estado_produto"));
                productStatus.setStatus(rs.getString("estado_produto"));

                Product product = new Product();
                product.setProductID(rs.getInt("id_produto"));
                product.setPublisher(rs.getString("editora"));
                product.setTitle(rs.getString("titulo"));
                product.setComicFormat(rs.getString("formato_do_quadrinho"));
                product.setPagesNumber(rs.getInt("numero_paginas"));
                product.setWeight(rs.getInt("peso"));
                product.setCoverImage(rs.getString("capa_imagem"));
                product.setUser(user);
                product.setProductStatus(productStatus);
                obj.setProduct(product);

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
    public List<Auction> listAllActiveAuctionOrderedBy(String OrderBy) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT l.id_leilao, data_inicio, DATEDIFF(data_fim, now()) duracao, valor_inicial, valor_atual, lance_padrao, l.id_estado_leilao, l.id_usuario, l.id_produto, " +
                            "el.estado estado_leilao, " +
                            "nome, email, senha, cidade, u.estado, data_nascimento, ativo, " +
                            "editora, titulo, formato_do_quadrinho, numero_paginas, peso, capa_imagem, " +
                            "p.id_estado_produto, ep.estado estado_produto " +
                            "FROM Leilao l INNER JOIN Usuario u ON l.id_usuario = u.id_usuario " +
                            "INNER JOIN Estado_leilao el ON l.id_estado_leilao = el.id_estado_leilao " +
                            "INNER JOIN Produto p ON l.id_produto = p.id_produto " +
                            "INNER JOIN Estado_produto ep ON p.id_estado_produto = ep.id_estado_produto " +
                            "WHERE l.id_estado_leilao = 1 " +
                            "GROUP BY id_leilao");

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

                AuctionStatus auctionStatus = new AuctionStatus();
                auctionStatus.setAuctionStatusID(rs.getInt("id_estado_leilao"));
                auctionStatus.setStatus(rs.getString("estado_leilao"));
                obj.setAuctionStatus(auctionStatus);

                User user = new User();
                user.setUserID(rs.getInt("id_usuario"));
                user.setName(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setState(rs.getString("estado"));
                user.setCity(rs.getString("cidade"));
                user.setPassword(null);
                user.setDateOfBirth(new java.sql.Date(rs.getDate("data_nascimento").getTime()));
                user.setStatus(rs.getBoolean("ativo"));
                obj.setUser(user);

                ProductStatus productStatus = new ProductStatus();
                productStatus.setProductStatusID(rs.getInt("id_estado_produto"));
                productStatus.setStatus(rs.getString("estado_produto"));

                Product product = new Product();
                product.setProductID(rs.getInt("id_produto"));
                product.setPublisher(rs.getString("editora"));
                product.setTitle(rs.getString("titulo"));
                product.setComicFormat(rs.getString("formato_do_quadrinho"));
                product.setPagesNumber(rs.getInt("numero_paginas"));
                product.setWeight(rs.getInt("peso"));
                product.setCoverImage(rs.getString("capa_imagem"));
                product.setUser(user);
                product.setProductStatus(productStatus);
                obj.setProduct(product);

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
    public void deleteById(Long id) {
        Connection conn = DB.getConnection();
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
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT id_leilao, data_inicio, DATEDIFF(data_fim, now()) duracao, valor_inicial, valor_atual, lance_padrao, id_estado_leilao, id_usuario, id_produto "
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
            // DB.closeConnection();
        }
    }

    @Override
    public Integer returnAuctionDuration(Long id) {

        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT DATEDIFF(data_fim, now()) duracao FROM Leilao "
                            + "WHERE id_leilao = ?");

            st.setLong(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                return rs.getInt("duracao");
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
    public void changesAuctionStatus(Long id, AuctionStatus auctionStatus) {
        Connection conn = DB.getConnection();
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
            // DB.closeConnection();
        }
    }

    @Override
    public void setAuctionDateNow(Long id) {
        Connection conn = DB.getConnection();
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
            // DB.closeConnection();
        }
    }

    @Override
    public void closeActiveAuction(Long id) {
        Connection conn = DB.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Leilao " +
                            "SET id_estado_leilao= ? " +
                            "WHERE id_leilao= ?");

            st.setInt(1, 4);
            st.setLong(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}