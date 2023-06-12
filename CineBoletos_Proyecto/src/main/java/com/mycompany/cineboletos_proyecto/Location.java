/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cineboletos_proyecto;

import models.Params;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 *
 * @author jackiesanchez
 */
public class Location {
    
    private static String controller;
    private static String action;
    private static Params params = new Params<String>();
    
     /**
     * @return the controller
     */
    public static String getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    private static void setController(String controller) {
        Location.controller = controller;
    }

    /**
     * @return the action
     */
    public static String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    private static void setAction(String action) {
        Location.action = action;
    }
    
    /**
     * @return the params
     */
    public static Params getParams() {
        return params;
    }

    /**
     * @param aParams the params to set
     */
    public static void setParams(Params aParams) {
        params = aParams;
    }
    
    public static URL loadPath(String path){
         String projectDir = System.getProperty("user.dir");
        try {            
            URL ProjectRoot = Paths.get(projectDir+"/src/main/java/"+path).toUri().toURL();
            System.out.println(ProjectRoot.toString());
            return ProjectRoot;
        } catch (IOException ex) {
           return null;
        }
    }
    
    protected static URL loadCSS(String name) {
        String projectDir = System.getProperty("user.dir");
        try {            
            URL ProjectRoot = Paths.get(projectDir+"/src/main/java/styles/"+name+".css").toUri().toURL();
            return ProjectRoot;
        } catch (IOException ex) {
           return null;
        }
    }
    
    protected static Parent loadFXML(String controller, String action) {
        String projectDir = System.getProperty("user.dir");        
        try {            
            URL ProjectRoot = Paths.get(projectDir+"/src/main/java/views/"+controller+"/"+action+".fxml").toUri().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
            setAction(action);
            setController(controller);
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("No Existe el controlador o la vista");
                URL ProjectRoot;
            try {
                ProjectRoot = Paths.get(projectDir+"/src/main/java/views/home/Index.fxml").toUri().toURL();
                FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
                setAction("Index");
                setController("home");
                return fxmlLoader.load();
            } catch (MalformedURLException ex1) {
                 System.out.print( ex1.toString());
            } catch (IOException ex1) {
               System.out.print( ex1.toString());
            }         
        }
        return null;
    }
    
    protected static Parent loadFXML(String controller, String action, Params info) {
        String projectDir = System.getProperty("user.dir");        
        try {            
            URL ProjectRoot = Paths.get(projectDir+"/src/main/java/views/"+controller+"/"+action+".fxml").toUri().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
            setAction(action);
            setController(controller);
            setParams(info);
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("No Existe el controllador o la vista");
                URL ProjectRoot;
            try {
                ProjectRoot = Paths.get(projectDir+"/src/main/java/views/home/Index.fxml").toUri().toURL();
                FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
                setAction("Index");
                setController("home");
                return fxmlLoader.load();
            } catch (MalformedURLException ex1) {
                 System.out.print( ex1.toString());
            } catch (IOException ex1) {
               System.out.print( ex1.toString());
            }         
        }
        return null;
    }
    
    protected static Parent loadFXML(String controller) {
        String projectDir = System.getProperty("user.dir");
        try {      
            URL ProjectRoot = Paths.get(projectDir+"/src/main/java/views/"+controller+"/Index.fxml").toUri().toURL();
            System.out.println(ProjectRoot.toString());
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
            setAction("Index");
            setController(controller);
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("No Existe el controlador o la vista");
                URL ProjectRoot;
            try {
                ProjectRoot = Paths.get(projectDir+"/src/main/java/views/home/Index.fxml").toUri().toURL();
                FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
                setAction("Index");
                setController("home");
                return fxmlLoader.load();
            } catch (MalformedURLException ex1) {
                 System.out.print( ex1.toString());
            } catch (IOException ex1) {
               System.out.print( ex1.toString());
            }         
        }
        return null;
    }
    
    protected static Parent loadFXML(String controller, Params info) {
        String projectDir = System.getProperty("user.dir");
        try {            
            URL ProjectRoot = Paths.get(projectDir+"/src/main/java/views/"+controller+"/Index.fxml").toUri().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
            setAction("Index");
            setController(controller);
            setParams(info);
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("No Existe el controllador o la vista");
                URL ProjectRoot;
            try {
                ProjectRoot = Paths.get(projectDir+"/src/main/java/views/home/Index.fxml").toUri().toURL();
                FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
                setAction("Index");
                setController("home");
                return fxmlLoader.load();
            } catch (MalformedURLException ex1) {
                 System.out.print( ex1.toString());
            } catch (IOException ex1) {
               System.out.print( ex1.toString());
            }         
        }
        return null;
    }
    
    protected static Parent loadView(String view) {
        String projectDir = System.getProperty("user.dir");
        try {            
            URL ProjectRoot = Paths.get(projectDir+"/src/main/java/views/"+Location.controller+"/"+view+".fxml").toUri().toURL();
            FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
            setAction(view);
            setController(Location.controller);
            return fxmlLoader.load();
        } catch (IOException ex) {
            System.out.println("No Existe el controllador o la vista");
                URL ProjectRoot;
            try {
                ProjectRoot = Paths.get(projectDir+"/src/main/java/views/home/Index.fxml").toUri().toURL();
                FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
                setAction("Index");
                setController("home");
                return fxmlLoader.load();
            } catch (MalformedURLException ex1) {
                 System.out.print( ex1.toString());
            } catch (IOException ex1) {
               System.out.print( ex1.toString());
            }         
        }
        return null;
    }
    
    protected static Parent loadView(String view, Params inf) {
       String projectDir = System.getProperty("user.dir");
       try {            
           URL ProjectRoot = Paths.get(projectDir+"/src/main/java/views/"+Location.controller+"/"+view+".fxml").toUri().toURL();
           FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
           setAction(view);
           setController(Location.controller);
           setParams(inf);
           return fxmlLoader.load();
       } catch (IOException ex) {
           System.out.println("No Existe el controllador o la vista");
               URL ProjectRoot;
           try {
               ProjectRoot = Paths.get(projectDir+"/src/main/java/views/home/Index.fxml").toUri().toURL();
               FXMLLoader fxmlLoader = new FXMLLoader(ProjectRoot);
               setAction("Index");
               setController("home");
               return fxmlLoader.load();
           } catch (MalformedURLException ex1) {
                System.out.print( ex1.toString());
           } catch (IOException ex1) {
              System.out.print( ex1.toString());
           }         
       }
       return null;
   }
    
}
