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
 *
 * @author jackiesanchez
 */
public class DDatesEvent {
    
    
    public ArrayList<DatesEvent> ListDateEvents(int idEvento){
        
         ArrayList<DatesEvent> listEvents = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             StringBuilder query = new StringBuilder();
             query.append(String.format("select * from event_date where idevent = %s", idEvento));
             ResultSet resultado = sql.executeQuery(query.toString());
             
             while(resultado.next()){
                 DatesEvent events = new DatesEvent();
                 events.setIdfecha(resultado.getInt("iddate"));
                 events.setDuration(resultado.getString("event_duration"));
                 events.setFecha_evento(resultado.getTimestamp("event_date").toLocalDateTime());
                 events.setIdevento(resultado.getInt("idevent"));
                 events.setActive(resultado.getBoolean("active"));
                 listEvents.add(events);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listEvents;
    }
    
       /**
     * Devuelve una lista de fechas de un evento.
     * 
     * @param idEvento id del evento
     * @return Una lista de fechas de un evento.
     */
    public ArrayList<DatesEvent> ListDateEvents(int idEvento, boolean active){
        
         ArrayList<DatesEvent> listEvents = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             StringBuilder query = new StringBuilder();
             query.append(String.format("select * from event_dates where idevent = %s and activo = %s order by event_date asc", idEvento,active));
             ResultSet resultado = sql.executeQuery(query.toString());
             
             while(resultado.next()){
                 DatesEvent events = new DatesEvent();
                 events.setIdfecha(resultado.getInt("iddate"));
                 events.setDuration(resultado.getString("event_duration"));
                 events.setFecha_evento(resultado.getTimestamp("event_date").toLocalDateTime());
                 events.setIdevento(resultado.getInt("idevent"));
                 events.setActive(resultado.getBoolean("active"));
                 listEvents.add(events);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listEvents;
    }    
   
    
      /**
     * Devuelve una lista de fechas de un evento.
     * 
     * @param date recibe una fecha evento
     * @return si fue creada o no exitosamente.
     */
    public boolean CreateDateEvent(DatesEvent date){
        
         boolean result = false;
         
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("insert into event_dates(event_date, event_duration, idevent)");
             query.append("values(?,?,?)");
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setTimestamp(1, Timestamp.valueOf(date.getFecha_evento()));
             sql.setString(2, date.getDuration());
             sql.setInt(3, date.getIdevento());  
             
             result = sql.executeUpdate() != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return result;
    }
    
      /**
     * Devuelve una lista de fechas de un evento.
     * 
     * @param date recibe una fecha evento
     * @return si fue creada o no exitosamente.
     */
    public boolean ModifyDateEvent(DatesEvent date){
        
         boolean result = false;
         
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("update event_dates set event_date = ?, event_duration = ?, idevent = ?, active = ? ");
             query.append("where idfecha = ?");
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setTimestamp(1, Timestamp.valueOf(date.getFecha_evento()));
             sql.setString(2, date.getDuration());
             sql.setInt(3, date.getIdevento());  
             sql.setBoolean(4, date.getActive());
             sql.setInt(5, date.getIdfecha());
             
             result = sql.executeUpdate() != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return result;
    }
    
}
