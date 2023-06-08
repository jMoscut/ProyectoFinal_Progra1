/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers.homeController;

import com.mycompany.cineboletos_proyecto.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.mycompany.cineboletos_proyecto.Location;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import models.Params;

/**
 * FXML Controller class
 *
 * @author jackiesanchez
 */
public class Create implements Initializable {

   @FXML
    public Button btnc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnc.setOnMouseClicked(e -> {
            Integer params = (Integer)Location.getParams().getDato();
            System.out.println("Estabas en la vista: " + Location.getAction() + " del controlador: " + Location.getController() + " params: "+ params);
            cambiarVista("Index");
        });        
    } 
    
    private void cambiarVista(String action){
        Params<String> informacion = new Params();
        informacion.setDato("Prueba");
        App.view(action, informacion);
    }
}
