package org.example.model.impls;

import java.io.InputStream;
import java.util.Properties;


/**
 * Classe que carrega les propietats de la base de dades
 * */
public class DBProperties {
    private Properties properties = new Properties();


    /**
     * Constructor de la classe DBProperties
     * */
    public DBProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                System.out.println("Lo siento, no se pudo encontrar el archivo database.properties");
                return;
            }
            // Cargar el archivo properties
            properties.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Getter de la url de la base de dades
     * @return url de la base de dades
     * */
    public String getUrl() {
        return properties.getProperty("db.url");
    }

    /**
     * Getter de l'usuari de la base de dades
     * @return usuari de la base de dades
     * */
    public String getUsername() {
        return properties.getProperty("db.username");
    }

    /**
     * Getter de la contrasenya de la base de dades
     * @return contrasenya de la base de dades
     * */
    public String getPassword() {
        return properties.getProperty("db.password");
    }

    /**
     * Getter del driver de la base de dades
     * @return driver de la base de dades
     * */
    public String getDriver() {
        return properties.getProperty("db.driver");
    }
}