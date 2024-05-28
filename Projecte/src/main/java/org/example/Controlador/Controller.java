package org.example.Controlador;


import org.example.Model.entities.*;
import org.example.Model.impls.OracleImpl;
import org.example.Vista.Vista;
import org.example.Model.exceptions.ChException;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Controller implements PropertyChangeListener {

    public static final String PROP_EXCEPCIO = "excepcio";
    private ChException excepcio;

    public ChException getExcepcio() {
        return excepcio;
    }

    PropertyChangeSupport canvis = new PropertyChangeSupport(this);


    /**
     * Setter de l'atribut excepcio
     * @param excepcio Excepció a assignar.
     * */
    public void setExcepcio(ChException excepcio) {
        ChException valorVell = this.excepcio;
        this.excepcio = excepcio;
        canvis.firePropertyChange(PROP_EXCEPCIO, valorVell, excepcio);
    }

    /**
     * Controla els canvis de propietats de l'aplicació
     * @param evt Event que conté la propietat que ha canviat i el valor anterior i el nou valor de la propietat.
     * */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ChException rebuda = (ChException) evt.getNewValue();
        try {
            throw rebuda;
        } catch (ChException e) {
            //Aquí farem el tractament de les excepcions de l'aplicació
            switch (evt.getPropertyName()) {
                case PROP_EXCEPCIO:
                default:
                    JOptionPane.showMessageDialog(null, rebuda.getMissatge());
                    break;
            }
        }
    }
    private Model model;
    private Vista view;

    /**
     * Constructor de la classe Controller
     * @param model Model de l'aplicació
     * @param view Vista de l'aplicacio.
     * */
    public Controller(Model model, Vista view) {
        this.model = model;
        this.view = view;

        lligarVistaModel();

        assignarCodiListeners();

        canvis.addPropertyChangeListener(this);
    }

    /**
     * Assigna els listeners als botons de la vista
     * */
    private void assignarCodiListeners() {

        OracleImpl oracle = new OracleImpl();

        List<Producte> productes;
        try{
            productes  = oracle.getAll();
        } catch (ChException e) {
            productes = null;
            setExcepcio(e);
        }

        if(productes != null){
            for (int i = 0; i < productes.size(); i++) {
                model.getModel().addRow(new Object[]{
                        productes.get(i).getNom(),
                        productes.get(i).getPreu(),
                        productes.get(i).getCalories(),
                        productes.get(i).getCategoria(),
                        productes.get(i).isDisponible()});
            }
        }

        Model modelo = this.model;


        DefaultTableModel model=this.model.getModel();

        JButton insertarButton=view.getInsertarButton();
        JButton borrarButton=view.getBorrarButton();
        JButton modificarButton=view.getModificarButton();
        JButton buidarButton=view.getBuidarButton();

        JTextField campNom=view.getCampNom();
        JTextField campPreu=view.getCampPreu();
        JTextField campCalories=view.getCampCalories();
        JCheckBox disponibleCheckBox=view.getDisponibleCheckBox();
        JComboBox categoriaSelector=view.getCategoriaSelector();

        JTable taula=view.getTaula();

        categoriaSelector.addItem("Selecciona una categoria");

        for (int i = 0; i < Producte.Categoria.values().length; i++) {
            categoriaSelector.addItem(Producte.Categoria.values()[i].toString());
        }

        insertarButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(Metodes.estaBuit(view)){
                            setExcepcio(new ChException(1));
                        }
                        else {
                            try{
                                if(Metodes.potAfegir(view) ){

                                    model.addRow(new Object[]{campNom.getText().trim(),Metodes.getPreu(),Metodes.getCalories(), categoriaSelector.getSelectedItem(), disponibleCheckBox.isSelected()});
                                    try{
                                        oracle.insert(new Producte(campNom.getText().trim(),Metodes.getPreu(),Metodes.getCalories(),  disponibleCheckBox.isSelected(), (String) categoriaSelector.getSelectedItem()));
                                    } catch (ChException ex){
                                        setExcepcio(ex);
                                    }
                                    Metodes.buidarCamps(view);
                                }
                            } catch (ChException ex){
                                setExcepcio(ex);
                            }
                        };
                    }
                }
        );

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSel=taula.getSelectedRow();

                if(filaSel!=-1){
                    if(Metodes.estaBuit(view)){
                        setExcepcio(new ChException(1));
                    }
                    else {
                        try {
                            if(Metodes.potAfegir(view)){
                                String id = (String) model.getValueAt(filaSel, 0);
                                model.removeRow(filaSel);
                                model.insertRow(filaSel, new Object[]{campNom.getText().trim(),Metodes.getPreu(),campCalories.getText(),categoriaSelector.getSelectedItem(),disponibleCheckBox.isSelected()});

                                try{
                                    oracle.modify(new Producte(campNom.getText().trim(),Metodes.getPreu(),Integer.parseInt(campCalories.getText()),disponibleCheckBox.isSelected(), (String) categoriaSelector.getSelectedItem()), id);
                                } catch (ChException ex){
                                    setExcepcio(ex);
                                }
                                Metodes.buidarCamps(view);
                            };
                        } catch (ChException ex){
                            setExcepcio(ex);
                        }
                    }
                }
                else {
                    setExcepcio(new ChException(5));
                }
            }
        });

        borrarButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int filaSel=taula.getSelectedRow();

                        if(filaSel!=-1){
                            String nom = (String) model.getValueAt(filaSel, 0);
                            model.removeRow(filaSel);
                            Metodes.buidarCamps(view);
                            try{
                                oracle.delete(nom);
                            } catch (ChException ex){
                                setExcepcio(ex);
                            }
                        }
                        else {
                            setExcepcio(new ChException(6));
                        }
                    }
                }
        );

        taula.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int filaSel=taula.getSelectedRow();

                if(filaSel!=-1){
                    campNom.setText(model.getValueAt(filaSel,0).toString());
                    campPreu.setText(model.getValueAt(filaSel,1).toString());
                    campCalories.setText(model.getValueAt(filaSel,2).toString());
                    categoriaSelector.setSelectedItem(model.getValueAt(filaSel,3).toString().trim());
                    disponibleCheckBox.setSelected((boolean)model.getValueAt(filaSel,4));
                }else{
                    Metodes.buidarCamps(view);

                }
            }
        });

        buidarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Metodes.buidarCamps(view);
            }
        });

        view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try{
                    oracle.save();
                } catch (ChException ex){
                    setExcepcio(ex);
                }
            }

        });
    }

    /**
     * Lliga la vista amb el model
     * */
    private void lligarVistaModel() {
        JTable taula=view.getTaula();
        DefaultTableModel model=this.model.getModel();
        taula.setModel(model);
        taula.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

}
