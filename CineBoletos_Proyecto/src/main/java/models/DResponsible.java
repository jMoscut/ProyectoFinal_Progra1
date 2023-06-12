/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author cgalv
 */
public class DResponsible {
    
     /**
    * constructor por defecto de la clase DResponsible
    * 
    */
    public DResponsible(){
    
    }   
    
        /**
     * metodo de la lista de responsables.
     * 
     * @return lista de responsables.
     */
    public ArrayList<Responsible> ListResponsible(){
        
         ArrayList<Responsible> listResponsible = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             String query = "select * from responsables";
             ResultSet resultado = sql.executeQuery(query);
             
             while(resultado.next()){
                 
                Responsible responsible = new Responsible();
                responsible.setId(resultado.getInt("idcharge"));
                responsible.setName(resultado.getString("namae"));  
                 
                listResponsible.add(responsible);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listResponsible;
    }
    
     /**
     * Devuelve un eventos.
     * 
     * @param id es el id del responsable
     * @return Un responsable.
     */
     public Responsible ResponsibleById(int id){
        
         Responsible model = null;
         
         try (Connection conn = Conexion.getConection()) {
             String query = "select * from incharge where id = ?";
             PreparedStatement sql = conn.prepareStatement(query);
             sql.setInt(1, id);
             ResultSet resultado = sql.executeQuery();
             
             while(resultado.next()){
                 
                 Responsible responsible = new Responsible();
                 responsible.setId(resultado.getInt("id"));
                 responsible.setName(resultado.getString("namae"));  

                 
                 model = responsible;
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return model;
    }
    
      /**
     * Devuelve una booleano que indica si se creo el responsable o no.
     * 
     * @param  responsible recibe un responsable para luego crearlo
     * @return Un boleano.
     */
    public boolean CreateResponsible(Responsible responsible){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("insert into incharge(namae)");
             query.append("VALUES (?,?)");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, responsible.getName());
             sql.setInt(2, responsible.getType());
             
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
    
      /**
     * Devuelve una booleano que indica si se modifico el responsable o no.
     * 
     * @param  responsible recibe un responsable para luego modificarlo
     * @return Un boleano.
     */
    public boolean ModifyResponsible(Responsible responsible){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("update incharge set namae = ? ");
             query.append("where id = ?");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, responsible.getName());
             sql.setInt(2, responsible.getType());
             sql.setInt(3,responsible.getId());
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
}
