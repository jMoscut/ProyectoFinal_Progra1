/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers.homeController;

import com.mycompany.cineboletos_proyecto.App;
import com.mycompany.cineboletos_proyecto.Context;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author jackiesanchez
 */
public class Dashboard implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML
    public ImageView events;

    @FXML
    public Button logout;

    @FXML
    public ImageView menu;

    @FXML
    public ImageView responsibles;

    @FXML
    public ImageView seating;

    @FXML
    public ImageView tickets;

    @FXML
    public Label user;

    @FXML
    public ImageView users;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Context.isIsLogIn()) {
            user.setText(Context.getUser().getName());
        }
        
        logout.setOnMouseClicked(event -> {
            Context.LogOut();
        });
        
        menu.setOnMouseClicked(event -> {
            App.setRoot("Home");
        });
        
        responsibles.setOnMouseClicked(event -> {
            App.setRoot("Responsible");
        });
        
        events.setOnMouseClicked(event -> {
            App.setRoot("Events");
        });
        
        users.setOnMouseClicked(event -> {
            App.setRoot("Users");
        });        
        
        seating.setOnMouseClicked(event -> {
            App.setRoot("Seat");
        });
    }    
    
}
