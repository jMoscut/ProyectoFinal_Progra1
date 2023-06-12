/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers.homeController;

import com.mycompany.cineboletos_proyecto.App;
import com.mycompany.cineboletos_proyecto.Context;
import com.mycompany.cineboletos_proyecto.Validations;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import models.Events;
import models.Params;

/**
 * FXML Controller class
 *
 * @author jackiesanchez
 */
public class Cantidad implements Initializable {

    /**
     * Initializes the controller class.
     */
  
    @FXML
    public ComboBox<Integer> cantidad;

    @FXML
    public Button regresar;
    
    @FXML
    public Button continuar;


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        for (int i = 0; i <= 10; i++) {
            cantidad.getItems().add(i);
        }        
        cantidad.setValue(0);
        
        continuar.setOnMouseClicked(event -> {
            int value = cantidad.getValue();
            if (value > 0) {
                Params<Integer> cant = new Params<>();
                cant.setDato(value);
                App.view("Asientos", cant);
            }else{
                Validations.AlertMessage("Debes Seleccionar una cantidad de boletos antes de continuar.", Alert.AlertType.ERROR, "Error");
            }
        });
        
        regresar.setOnMouseClicked(event -> {
            Params<Events> evento = new Params<>();
            evento.setDato(Context.getEvent());
            App.view("EventDetail", evento);
        });
    }    
     
    
}
