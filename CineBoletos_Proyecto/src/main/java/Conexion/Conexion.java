/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jackiesanchez
 */
public class Conexion {
    
  private static final String URL = "jdbc:postgresql://boletosproyecto1.postgres.database.azure.com:5432/adelanto_database";
    private static final String USER = "adminjackeline";
    private static final String PASSWORD = "Abuelita2019";
    
    
 
    public Conexion() {
        // Código del constructor
    }    
    
   /**
      *
      * @return la conexion.
      */
    public static Connection getConection(){
       try{
           Connection connection = DriverManager.getConnection(URL,USER, PASSWORD);            
           return connection;
       }
       catch (SQLException e) {
           System.out.println(e.toString());
           return null;
       }
    }
    
}
