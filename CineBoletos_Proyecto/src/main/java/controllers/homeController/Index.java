/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers.homeController;

import com.mycompany.cineboletos_proyecto.Context;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;


/**
 * FXML Controller class
 *
 * @author jackiesanchez
 */
public class Index implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML
    public Button btnc;
    
    @FXML
    public GridPane panelGrid;
    
    @FXML 
    public Label title;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // String titulo = String.format("Bienvenido %s", Context.getUser().getName());
        //title.setText(titulo);
        
//        btnc.setOnAction(e -> {
//            Context.LogOut();            
//        });
   } 
    
}
