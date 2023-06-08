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
    
        private static final String url = "jdbc:postgresql://localhost:5432/Prueba";
    private static final String user = "postgres";
    private static final String password = "andrea2911";
    
   
    public static Connection getConection(){
       try{
           Connection connection = DriverManager.getConnection(url,user,password);            
           System.out.println("conexion exitosa");
           return connection;
       }
       catch (SQLException e) {
           System.out.println(e.toString());
           return null;
       }
    }
    
}
