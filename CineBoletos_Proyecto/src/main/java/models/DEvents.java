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
import java.sql.Timestamp;
import java.util.ArrayList;


/**
 * clase que manipula la data de los eventos
 * @author jackiesanchez
 */
public class DEvents {
   
    /**
    * constructor por defecto de la clase DEvents
    * 
    */
    public DEvents(){
    
    }   
    
        /**
     * Devuelve una lista de eventos.
     * 
     * @return Una lista de eventos.
     */
    public ArrayList<Events> ListEvents(){
        
         ArrayList<Events> listEvents = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             StringBuilder query = new StringBuilder();
             query.append("select idevent, e.namae, resume, startdate, endate, ");
             query.append("picture, visible, visibledate, hidedate, idcharge, ");
             query.append("vip, aplant, bplant, vip_mg, status, r.namae");
             query.append("from events as e inner join incharge as r on e.idevent = r.idcharge");
             ResultSet resultado = sql.executeQuery(query.toString());
             
             while(resultado.next()){
                 Events events = new Events();
                 events.setId(resultado.getInt("idevent"));
                 events.setName(resultado.getString("namae"));  
                 events.setSynopsis(resultado.getString("resume"));
                 events.setInitialDate(resultado.getTimestamp("startdate").toLocalDateTime());
                 events.setFinalDate(resultado.getTimestamp("endate").toLocalDateTime());
                 events.setImage(resultado.getString("picture"));
                 events.setVisible(resultado.getBoolean("visible"));
                 events.setVisibleDate(resultado.getTimestamp("visiblesate").toLocalDateTime());
                 events.setHiddenDate(resultado.getTimestamp("hidedate").toLocalDateTime());
                 events.setIdResponsible(resultado.getInt("idcharge"));
                 events.setVip(resultado.getDouble("vip"));
                 events.setPlanta_a(resultado.getDouble("aplant"));
                 events.setPlanta_b(resultado.getDouble("bplant"));
                 events.setVip_mg(resultado.getDouble("vip_mg"));
                 Responsible responsible = new Responsible();
                 responsible.setId(resultado.getInt("idcharge"));
                 responsible.setName(resultado.getString("incharge"));
                 events.setResponsible(responsible);
                 listEvents.add(events);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listEvents;
    }
    
     
      /**
     * Devuelve un eventos.
     * 
     * @param id es el id del evento
     * @return Una evento.
     */
     public Events EventById(int id){
        
         Events model = null;
         
        try (Statement sql = Conexion.getConection().createStatement()) {
             StringBuilder query = new StringBuilder();
             query.append("select idevent, e.namae, resume, startdate, endate, ");
             query.append("picture, visible,visibledate, hidedate, e.idcharge, ");
             query.append("vip, aplant, bplant, vip_mg, status, r.namae as incharge");
             query.append("from events as e inner join incharge as r on e.idevent = r.idcharge ");
             query.append(String.format("where idevento = %s", id));
             ResultSet resultado = sql.executeQuery(query.toString());
             
             while(resultado.next()){
                 Events events = new Events();
                 events.setId(resultado.getInt("idevent"));
                 events.setName(resultado.getString("namae"));  
                 events.setSynopsis(resultado.getString("resume"));
                 events.setInitialDate(resultado.getTimestamp("startdate").toLocalDateTime());
                 events.setFinalDate(resultado.getTimestamp("endate").toLocalDateTime());
                 events.setImage(resultado.getString("picture"));
                 events.setVisible(resultado.getBoolean("visible"));
                 events.setVisibleDate(resultado.getTimestamp("visibledate").toLocalDateTime());
                 events.setHiddenDate(resultado.getTimestamp("hidedate").toLocalDateTime());
                 events.setIdResponsible(resultado.getInt("idcharge"));
                 events.setVip(resultado.getDouble("vip"));
                 events.setPlanta_a(resultado.getDouble("aplant"));
                 events.setPlanta_b(resultado.getDouble("bplant"));
                 events.setVip_mg(resultado.getDouble("vip_mg"));
                 Responsible responsible = new Responsible();
                 responsible.setId(resultado.getInt("idcharge"));
                 responsible.setName(resultado.getString("incharge"));
                 events.setResponsible(responsible);
                 model = events;
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return model;
    }
     
      /**
     * Devuelve una booleano que indica si se creo el evento o no.
     * 
     * @param event recibe un evento para luego crearlo
     * @return Un boleano.
     */
    public boolean CreateEvent(Events event){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("insert into events(namae,resume,startdate,endate,picture,visible,visibledate,hidedate,idcharge,vip,aplant,bplant,vip_mg)");
             query.append("VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, event.getName());
             sql.setString(2, event.getSynopsis());
             sql.setTimestamp(3, Timestamp.valueOf(event.getInitialDate()));
             sql.setTimestamp(4, Timestamp.valueOf(event.getFinalDate()));
             sql.setString(5,event.getImage());
             sql.setBoolean(6, event.isVisible());
             sql.setTimestamp(7, Timestamp.valueOf(event.getVisibleDate()));
             sql.setTimestamp(8, Timestamp.valueOf(event.getHiddenDate()));
             sql.setInt(9, event.getIdResponsible());
             sql.setDouble(10, event.getVip());
             sql.setDouble(11, event.getPlanta_a());
             sql.setDouble(12, event.getPlanta_b());
             sql.setDouble(13, event.getVip_mg());
             sql.setString(14, event.getDuration());
             
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
    
     /**
     * Devuelve una booleano que indica si se modifico el evento o no.
     * 
     * @param event recibe un evento para luego modificarlo
     * @return Un boleano.
     */
    public boolean ModifyEvent(Events event){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("update eventos set namae = ?, resume = ?, startdate = ?, endate = ?, picture = ?, visible = ?, visibledate = ?, hidedate = ?, idcharge = ?,");
             query.append("vip = ?,aplant = ?,bplant = ?,vip_mg = ? ");
             query.append("where idevent = ?");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, event.getName());
             sql.setString(2, event.getSynopsis());
             sql.setTimestamp(3, Timestamp.valueOf(event.getInitialDate()));
             sql.setTimestamp(4, Timestamp.valueOf(event.getFinalDate()));
             sql.setString(5,event.getImage());
             sql.setBoolean(6, event.isVisible());
             sql.setTimestamp(7, Timestamp.valueOf(event.getVisibleDate()));
             sql.setTimestamp(8, Timestamp.valueOf(event.getHiddenDate()));
             sql.setInt(9, event.getIdResponsible());
             sql.setDouble(10, event.getVip());
             sql.setDouble(11, event.getPlanta_a());
             sql.setDouble(12, event.getPlanta_b());
             sql.setDouble(13, event.getVip_mg());
             sql.setString(14, event.getDuration());
             sql.setInt(15, event.getId());
            
             
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }

}
