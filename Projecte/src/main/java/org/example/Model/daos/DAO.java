package org.example.model.daos;


import org.example.Model.entities.Producte;
import org.example.Model.exceptions.ChException;

import java.util.List;


/**
 * Interfície DAO que defineix els mètodes per a la connexió amb la base de dades
 * */
public interface DAO <T>{

    /**
     * Mètode que retorna tots els productes de la base de dades
     * @return Llista de productes
     * @throws ChException
     * */
    List<T> getAll() throws ChException;

    /**
     * Mètode que guarda els canvis fets a la base de dades
     * @throws ChException
     * */
    void save() throws ChException;

    /**
     * Mètode que insereix un producte a la base de dades
     * @param producte Producte a inserir
     * @throws ChException
     * */
    void insert (Producte producte) throws ChException;

    /**
     * Mètode que modifica un producte de la base de dades
     * @param producte Producte a modificar
     * @param id ID del producte a modificar
     * @throws ChException
     * */
    void modify (Producte producte, String id) throws ChException;

    /**
     * Mètode que elimina un producte de la base de dades
     * @param id ID del producte a eliminar
     * @throws ChException
     * */
    void delete (String id) throws ChException;

    /**
     * Mètode que crea la connexió amb la base de dades
     * @throws ChException
     * */
    void create() throws ChException;

}