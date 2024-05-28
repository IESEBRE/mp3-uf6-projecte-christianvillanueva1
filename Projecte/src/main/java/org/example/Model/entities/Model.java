package org.example.Model.entities;

import javax.swing.table.DefaultTableModel;

public class Model {

    private DefaultTableModel model;

    /**
     * Getter de model
     * */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     * Constructor de la classe Model
     * */
    public Model() {

        model=new DefaultTableModel(new Object[]{"Nom","Preu","Calories","Categoria", "Disponible"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==1) return true;
                return false;
            }

            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return Double.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return String.class;
                    case 4:
                        return Boolean.class;
                    default:
                        return Object.class;

                }
            }
        };

    }

}



