package org.example.Vista;

import javax.swing.*;

public class Vista extends JFrame {
    private JTable taula;
    private JButton insertarButton;
    private JButton modificarButton;
    private JButton borrarButton;
    private JTextField campNom;
    private JCheckBox disponibleCheckBox;
    private JComboBox categoriaSelector;
    private JTextField campPreu;
    private JTextField campCalories;
    private JPanel panel2;
    private JButton buidarButton;

    /**
     * Getter de la taula
     * */
    public JTable getTaula() {
        return taula;
    }

    /**
     * Getter del botó d'inserció
     * */
    public JButton getInsertarButton() {
        return insertarButton;
    }

    /**
     * Getter del botó de modificació
     * */
    public JButton getModificarButton() {
        return modificarButton;
    }

    /**
     * Getter del botó d'eliminació
     * */
    public JButton getBorrarButton() {
        return borrarButton;
    }

    /**
     * Getter del camp de nom
     * */
    public JTextField getCampNom() {
        return campNom;
    }

    /**
     * Getter del camp de disponibilitat
     * */
    public JCheckBox getDisponibleCheckBox() {
        return disponibleCheckBox;
    }

    /**
     * Getter del selector de categoria
     * */
    public JComboBox getCategoriaSelector() {
        return categoriaSelector;
    }

    /**
     * Getter del camp de preu
     * */
    public JTextField getCampPreu() {
        return campPreu;
    }

    /**
     * Getter del camp de calories
     * */
    public JTextField getCampCalories() {
        return campCalories;
    }


    /**
     * Getter del botó de buidar
     * */
    public JButton getBuidarButton() {
        return buidarButton;
    }


    /**
     * Constructor de la vista
     * */
    public Vista(){
        this.setContentPane(panel2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
