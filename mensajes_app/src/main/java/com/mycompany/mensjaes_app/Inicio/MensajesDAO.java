/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensjaes_app.Inicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author c.can.castillo
 */
public class MensajesDAO {
    
    public static void crearMensajeDB(Mensajes mensaje){
        Conexion db_connect = new Conexion();
        
        try(Connection conexion = db_connect.get_Connection() ){
            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO mensajes( mensaje, autor_mensaje) VALUES (?,?)";
                ps = conexion.prepareStatement(query);
                ps.setString(1, mensaje.getMensaje());
                ps.setString(2, mensaje.getAutor_mensaje());
                ps.executeUpdate();
                System.out.println("MENSAJE CREADO");
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public static void leerMensajesDB() {
        Conexion db_connect = new Conexion();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try(Connection conexion = db_connect.get_Connection() ){
            String query = "SELECT * FROM mensajes";
            ps = conexion.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                System.out.println("ID: " + rs.getInt("id_mensaje"));
                System.out.println("MENSAJE: " + rs.getString("mensaje"));
                System.out.println("AUTOR: " + rs.getString("autor_mensaje"));
                System.out.println("FECHA: " + rs.getString("fecha_mensaje"));
                System.out.println("");
            }
        }catch(SQLException e){
            System.out.println("NO SE PUDIERON RECUPERAR LOS MENSAJES");
            System.out.println(e);
        }
    }
    
    public static void borrarMensajeDB(int id_mensaje) {
        Conexion db_connect = new Conexion();
        
        try(Connection conexion = db_connect.get_Connection() ){
            PreparedStatement ps = null;
            
            try {
                String query = "DELETE FROM mensajes WHERE id_mensaje = ?";
                ps = conexion.prepareStatement(query);
                ps.setInt(1, id_mensaje);
                ps.executeUpdate();
                System.out.println("EL MENSAJE HA SIDO BORRADO");
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("NO SE PUDO BORRAR EL MENSAJE");
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public static void actualizarMensajeDB(Mensajes mensaje) {
        Conexion db_connect = new Conexion();
        
        try(Connection conexion = db_connect.get_Connection() ){
            PreparedStatement ps = null;
            
            try {
                String query = "UPDATE mensajes SET mensaje = ? WHERE id_mensaje = ?" ;
                ps = conexion.prepareStatement(query);
                ps.setString(1, mensaje.getMensaje());
                ps.setInt(2, mensaje.getId_mensaje());
                ps.executeUpdate();
                System.out.println("MENSAJE ACTUALIZADO CORRECTAMENTE");
                
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
}
