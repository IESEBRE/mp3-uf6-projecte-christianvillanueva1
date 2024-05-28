package org.example.Model.impls;

import org.example.Model.entities.Producte;
import org.example.model.daos.DAO;
import org.example.Model.entities.Producte;
import org.example.Model.exceptions.ChException;
import org.example.model.impls.DBProperties;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


/**
 * Classe que implementa la interficie DAO per a la connexió amb la base de dades Oracle
 * */
public class OracleImpl implements DAO<Producte> {

    DBProperties connection = new DBProperties();

    /**
     * Mètode que retorna tots els productes de la base de dades
     * @return Llista de productes
     * @throws ChException
     * */
    @Override
    public List<Producte> getAll() throws ChException {
        create();
        List<Producte> productes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(
                connection.getUrl(),
                connection.getUsername(),
                connection.getPassword()
        )) {
            // Consultar los datos de la tabla
            try (PreparedStatement st = con.prepareStatement("SELECT * FROM Productes");
                 ResultSet rs = st.executeQuery()) {

                while (rs.next()) {
                    Producte producte = new Producte();
                    producte.setNom(rs.getString("nom"));
                    producte.setPreu(rs.getDouble("preu"));
                    producte.setCalories(rs.getInt("calories"));
                    producte.setCategoriaStr(rs.getString("categoria"));
                    producte.setDisponible(rs.getInt("disponible") == 1);
                    productes.add(producte);
                }
                con.close();
            }
        } catch (SQLException throwables) {
            switch(throwables.getErrorCode()){

                case 17002:
                    throw new ChException(13);
                default:
                    throw new ChException(12);
            }
        }
        return productes;
    }

    /**
     * Mètode que guarda els canvis fets a la base de dades
     * @throws ChException
     * */
    @Override
    public void save() throws ChException {
        try{
            Connection con = DriverManager.getConnection(
                    connection.getUrl(),
                    connection.getUsername(),
                    connection.getPassword()
            );
            con.setAutoCommit(false);
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            switch(throwables.getErrorCode()){

                case 17002:
                    throw new ChException(13);
                default:
                    throw new ChException(12);
            }
        }
    }

    /**
     * Mètode que insereix un producte a la base de dades
     * @param producte Producte a inserir
     * @throws ChException
     * */
    @Override
    public void insert(Producte producte) throws ChException {
        create();
        try{
            Connection con = DriverManager.getConnection(
                    connection.getUrl(),
                    connection.getUsername(),
                    connection.getPassword()
            );
            try (PreparedStatement st = con.prepareStatement("INSERT INTO Productes (nom, preu, calories, " +
                    "categoria, disponible) VALUES (?, ?, ?, ?, ?)")) {
                st.setString(1, producte.getNom());
                st.setDouble(2, producte.getPreu());
                st.setInt(3, producte.getCalories());
                st.setString(4, producte.getCategoria().toString());
                st.setInt(5, producte.isDisponible() ? 1 : 0);
                st.executeUpdate();
                con.close();
            }
        } catch (SQLException throwables) {
            switch(throwables.getErrorCode()){

                case 17002:
                    throw new ChException(13);
                default:
                    throw new ChException(12);
            }
        }
    }

    /**
     * Mètode que modifica un producte de la base de dades
     * @param producte Producte a modificar
     * @param id ID del producte a modificar
     * @throws ChException
     * */
    @Override
    public void modify(Producte producte, String id) throws ChException {
        create();
        try{
            Connection con = DriverManager.getConnection(
                    connection.getUrl(),
                    connection.getUsername(),
                    connection.getPassword()
            );

            try (PreparedStatement st = con.prepareStatement("UPDATE Productes SET nom = ?, preu = ?, calories = ?, categoria = ?, disponible = ? WHERE nom = ?")) {
                st.setString(1, producte.getNom());
                st.setDouble(2, producte.getPreu());
                st.setInt(3, producte.getCalories());
                st.setString(4, producte.getCategoria().toString());
                st.setInt(5, producte.isDisponible() ? 1 : 0);
                st.setString(6, id);
                st.executeUpdate();
                con.close();
            }
        } catch (SQLException throwables) {
            switch(throwables.getErrorCode()){

                case 17002:
                    throw new ChException(13);
                default:
                    throw new ChException(12);
            }
        }
    }

    /**
     * Mètode que elimina un producte de la base de dades
     * @param id ID del producte a eliminar
     * @throws ChException
     * */
    @Override
    public void delete(String id) throws ChException {

        create();

        try{
            Connection con = DriverManager.getConnection(
                    connection.getUrl(),
                    connection.getUsername(),
                    connection.getPassword()
            );

            try (PreparedStatement st = con.prepareStatement("DELETE FROM Productes WHERE nom = ?")) {
                System.out.println(id);
                st.setString(1, id);
                st.executeUpdate();
                con.close();
            }
        } catch (SQLException throwables) {
            switch(throwables.getErrorCode()){

                case 17002:
                    throw new ChException(13);
                default:
                    throw new ChException(12);
            }
        }
    }

    /**
     * Mètode que crea la taula de productes a la base de dades
     * @throws ChException
     * */
    @Override
    public void create() throws ChException{
        try (Connection con = DriverManager.getConnection(
                connection.getUrl(),
                connection.getUsername(),
                connection.getPassword()
        )) {
            DatabaseMetaData dbm = con.getMetaData();
            try (ResultSet tables = dbm.getTables(null, null,
                    "PRODUCTES", null)) {
                if (!tables.next()) {
                    try (PreparedStatement st = con.prepareStatement("BEGIN creartaula; END;")) {
                        st.executeUpdate();
                    }
                }
            }
        } catch (SQLException throwables) {
            switch(throwables.getErrorCode()){

                case 17002:
                    throw new ChException(13);
                default:
                    throw new ChException(12);
            }
        }
    }
}