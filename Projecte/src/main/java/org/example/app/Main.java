package org.example.app;

import org.example.Controlador.Controller;
import org.example.Model.entities.Model;
import org.example.Vista.Vista;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Controller(new Model(), new Vista());
            }
        });
    }
}