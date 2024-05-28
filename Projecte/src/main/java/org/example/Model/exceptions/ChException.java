package org.example.Model.exceptions;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ChException extends Exception{

    private static String missatge;
    private static int codi;


    Map<Integer, String> mapa = new HashMap<Integer, String>(){
        {
            put(1, "Hi ha camps buits. Omple'ls tots.");
            put(2, "Ja existeix un producte amb aquest nom. Canvia'l.");
            put(3, "El preu introduit es incorrecte. Asegurat d'introduir-lo en un format correcte.");
            put(4, "Les calories introduides son incorrectes. Asegurat d'introduir-les en un format correcte.");
            put(5, "No hi ha cap casella seleccioanda. Selecciona'n una per a poder modificar un producte.");
            put(6, "No hi ha cap casella seleccioanda. Selecciona'n una per a poder eliminar un producte.");
            put(7, "Ha hagut un error relacionat amb el fitxer.");
            put(8, "El nom introduit es incorrecte. Asegurat d'introduir-lo en un format correcte.");
            put(9, "El ID introduit es incorrecte. Asegurat d'introduir-lo en un format correcte. (XX0000)");
            put(10, "Ja existeix un supermercat amb aquesta ID. Canvia-la.");
            put(11, "El codi postal introduit es incorrecte. Asegurat d'introduir-lo en un format correcte. (00000)");
            put(12, "Hi ha hagut un problema relacionat amb la base de dades.");
            put(13, "Hi ha hagut un problema de connexio amb la base de dades.");
        }
    };

    /**
     * Creador de la classe ChException
     * @param codi Codi de l'excepció
     * */
    public ChException(int codi) {
        this.codi = codi;
        missatge = mapa.get(codi);
    }

    /**
     * Getter del missatge de l'excepció
     * @return missatge Missatge de l'excepció
     * */
    public String getMissatge() {
        return missatge;
    }

    /**
     * Getter del codi de l'excepció
     * @return codi Codi de l'excepció
     * */
    public int getCodi() {
        return codi;
    }

}
