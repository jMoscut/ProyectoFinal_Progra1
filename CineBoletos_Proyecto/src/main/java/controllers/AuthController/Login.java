/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers.AuthController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.mycompany.cineboletos_proyecto.App;
import com.mycompany.cineboletos_proyecto.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import models.DUser;
import models.User;

/**
 * FXML Controller class
 *
 * @author jackiesanchez
 */
public class Login implements Initializable {

    /**
     * Initializes the controller class.
     */
     @FXML
    public TextField txtUserSignIn, txtPasswordSignInMask;
    
    @FXML
    public PasswordField txtPasswordSignIn;
    
    @FXML
    public CheckBox checkViewPassSignIn;
    
    @FXML
    public Button btnSignIn, btnClean;
    
    
    private DUser _usuarios = new DUser();
    
     public void cleanFields(){
        txtPasswordSignIn.setText("");
        txtPasswordSignInMask.setText("");
        txtUserSignIn.setText("");        
    }
    
    @FXML
    public void eventKey(KeyEvent e){
        
        String c = e.getCharacter();
        
        if(c.equalsIgnoreCase(" ")){
            e.consume();
        }
    }
    
    @FXML
    public void actionEvent(ActionEvent e){
        
        Object evt = e.getSource();
        
        if(btnSignIn.equals(evt)){                    
                         
            if(txtUserSignIn.getText().isEmpty() && txtPasswordSignIn.getText().isEmpty()){
                System.out.println("campos vacios");
                return;            
            } 
            
            User usuario = _usuarios.UserByPasswordAndEmail(txtPasswordSignIn.getText(), txtUserSignIn.getText());
            
            if (usuario == null) {
                System.out.println("Usuario y/o contraseña invalidos");
                return;
            }
            
            Context.LogIn(usuario);
            
            App.setRoot("Home");
                        
        }else if(btnClean.equals(evt)){        
            cleanFields();
        }
        
         System.out.println();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maskPassword(txtPasswordSignIn, txtPasswordSignInMask, checkViewPassSignIn);        
    }  
    
     public void maskPassword(PasswordField pass, TextField text, CheckBox check){
    
        text.setVisible(false);
        text.setManaged(false);
            
        text.managedProperty().bind(check.selectedProperty());
        text.visibleProperty().bind(check.selectedProperty());
        
        text.textProperty().bindBidirectional(pass.textProperty());
    
    }
    
}
