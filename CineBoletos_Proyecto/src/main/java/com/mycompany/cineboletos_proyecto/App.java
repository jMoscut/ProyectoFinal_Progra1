package com.mycompany.cineboletos_proyecto;

import models.Params;
import javafx.application.Application;
import javafx.scene.Scene;
   import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

      private static Scene scene;

   @Override
    public void start(Stage stage) {            
        scene = new Scene(Location.loadFXML("Auth"),640, 480);
        scene.getStylesheets().add(Location.loadCSS("Global").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    
    public static void setRoot(String controller) {
        scene.setRoot(Location.loadFXML(controller));
    }
    
    public static void setRoot(String controller, Params inf) {
        scene.setRoot(Location.loadFXML(controller, inf));
    }

    
    public static void setRoot(String controller, String action) {
        scene.setRoot(Location.loadFXML(controller,action));
    }    
    
    public static void setRoot(String controller, String action, Params inf) {
        scene.setRoot(Location.loadFXML(controller,action,inf));
    }
     
    public static void view(String action){
        scene.setRoot(Location.loadView(action));
    }
    
    public static void view(String action, Params inf){
        scene.setRoot(Location.loadView(action, inf));
    }

    public static void main(String[] args) {
        launch();
    }

}