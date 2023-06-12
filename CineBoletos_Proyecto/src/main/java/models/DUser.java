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
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author jackiesanchez
 */
public class DUser {
  
     public ArrayList<User> ListadoDeUsuarios(){
        
         ArrayList<User> listaUsuarios = new ArrayList<>();
         
         try (Statement sql = Conexion.getConection().createStatement()) {
             String query = "select * from users";
             ResultSet resultado = sql.executeQuery(query);
             
             while(resultado.next()){
                 User usuario = new User();
                 usuario.setId(resultado.getInt("idclient"));
                 usuario.setName(resultado.getString("namae"));  
                 usuario.setEmail(resultado.getString("email"));
                 usuario.setPassword(resultado.getString("passwprds"));
                 usuario.setActive(resultado.getBoolean("active"));
                 usuario.setIdRol(resultado.getInt("idf"));
              
                 
                 listaUsuarios.add(usuario);
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error" + ex.toString());
         }      
         
         return listaUsuarios;
    }
    
    /**
     * metodo para consultar usuario existente
     * para iniciar sesion
     * 
     * @param password contraseña del usuario
     * @param email correo del usuario
     * @return a User
     */
    @SuppressWarnings("empty-statement")
    public User UserByPasswordAndEmail(String password, String email){
        
         User model = null;
         
         try (Connection conn = Conexion.getConection()) {
             String query = "SELECT * FROM users where email = ? and passwords = ? and active = true LIMIT 1";
             PreparedStatement sql = conn.prepareStatement(query);
             sql.setString(1, email);
             sql.setString(2,password);
             ResultSet resultado = sql.executeQuery();
             
             while(resultado.next()){;
                   User usuario = new User();
                 usuario.setId(resultado.getInt("idclient"));
                 usuario.setName(resultado.getString("namae"));  
                 usuario.setEmail(resultado.getString("email"));
                 usuario.setPassword(resultado.getString("passwords"));
                 usuario.setActive(resultado.getBoolean("active"));
                 usuario.setIdRol(resultado.getInt("idf"));
                 model = usuario;
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error "+ex.toString());
         }      
         
         return model;
    }
    
    public User UserById(int Id){
        
         User model = null;
         
         try (Connection conn = Conexion.getConection()) {
             String query = "SELECT * FROM users where idclient = ?";
             PreparedStatement sql = conn.prepareStatement(query);
             sql.setInt(1, Id);
             ResultSet resultado = sql.executeQuery();
             
             while(resultado.next()){;
                    User usuario = new User();
                 usuario.setId(resultado.getInt("idclient"));
                 usuario.setName(resultado.getString("namae"));  
                 usuario.setEmail(resultado.getString("email"));
                 usuario.setPassword(resultado.getString("passwords"));
                 usuario.setActive(resultado.getBoolean("active"));
                 usuario.setIdRol(resultado.getInt("idf"));
                 model = usuario;
             }
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error "+ex.toString());
         }      
         
         return model;
    }
    
    public boolean CreateUser(User model){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("insert into users(namae,email,passwords,active,idf)");
             query.append("VALUES (?,?,?,?,?,?,?)");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, model.getName());
             sql.setString(2, model.getEmail());
             sql.setString(3, model.getPassword());
             sql.setBoolean(4, model.isActive());
             sql.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
             sql.setInt(6,model.getIdRol());             
             sql.setString(7, model.getNumber());
             
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
    
    public boolean ModifyUser(User model){
        boolean results = false;
        
         try (Connection conn = Conexion.getConection()) {
             StringBuilder query = new StringBuilder();
             query.append("update users set namae = ?, email = ?, passwords = ?, active = ?, idf = ?");
             query.append("where idcliente = ?");
             
             PreparedStatement sql = conn.prepareStatement(query.toString());
             sql.setString(1, model.getName());
             sql.setString(2, model.getEmail());
             sql.setString(3, model.getPassword());
             sql.setBoolean(4, model.isActive());
             sql.setInt(5,model.getIdRol());                       
             sql.setString(6, model.getNumber());
             sql.setInt(7, model.getId());
             
             int rowAffected = sql.executeUpdate();
             
             results = rowAffected != 0;
             
             System.out.println(rowAffected);
             
         } catch (SQLException ex) { 
             System.out.println("hubo un error " + ex.toString());
         }      
         
         return results;        
    }
    
}
