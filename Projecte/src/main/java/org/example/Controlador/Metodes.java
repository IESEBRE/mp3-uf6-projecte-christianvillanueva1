package org.example.Controlador;

import org.example.Vista.Vista;
import org.example.Model.exceptions.ChException;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Pattern;

public class Metodes {

    private static double preu;

    private static int calories;

    /**
     * Getter del preu
     * */
    public static double getPreu() {
        return preu;
    }

    /**
     * Getter de les calories
     * */
    public static int getCalories() {
        return calories;
    }
    /**
     * Mètode que comprova si es pot afegir un producte
     * @param view Vista de la aplicació
     * @return true si es pot afegir, false si no
     * @throws ChException Excepció que es llençarà si no es compleixen les condicions
     * */
    public static boolean potAfegir(Vista view) throws ChException{

        for(int i=0; i<view.getTaula().getRowCount(); i++){
            if(!(i == view.getTaula().getSelectedRow())){
                if(view.getTaula().getValueAt(i, 0).equals(view.getCampNom().getText().trim())){
                    throw new ChException(2);
                }
            }
        }

        if (Pattern.compile("^[^A-Z]").matcher(String.valueOf(view.getCampNom().getText().charAt(0))).find()) {
            throw new ChException(8);
        }

        if (Pattern.compile("\\p{Punct}").matcher(String.valueOf(view.getCampNom().getText())).find()) {
            throw new ChException(8);
        }

        try {
            preu = NumberFormat.getInstance(Locale.FRANCE).parse(view.getCampPreu().getText()).doubleValue();
        } catch (ParseException ex){
            throw new ChException(3);
        }

        try{
            calories = Integer.parseInt(view.getCampCalories().getText());
        } catch (NumberFormatException ex){
            throw new ChException(4);
        }

        return true;
    }

    /**
     * Mètode que buida els camps de la vista
     * @param view Vista de la aplicació
     * */
    public static void buidarCamps (Vista view){
        view.getCampNom().setText("");
        view.getCampPreu().setText("");
        view.getCampCalories().setText("");
        view.getDisponibleCheckBox().setSelected(false);
        view.getCategoriaSelector().setSelectedIndex(0);
    }

    /**
     * Mètode que comprova si els camps de la vista estan buits
     * @param view Vista de la aplicació
     * @return true si estan buits, false si no
     * */
    public static boolean estaBuit(Vista view){
        return view.getCampNom().getText().isBlank()
                || view.getCampPreu().getText().isBlank()
                || view.getCampCalories().getText().isBlank()
                || view.getCategoriaSelector().getSelectedIndex()==0;
    }
}
