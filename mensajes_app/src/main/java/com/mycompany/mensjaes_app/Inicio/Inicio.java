/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensjaes_app.Inicio;

import java.sql.Connection;

/**
 *
 * @author c.can.castillo
 */
public class Inicio {
    
    public static void main(String[] args) {
        
        Conexion conexion = new Conexion();
        
        try(Connection cnx = conexion.get_Connection()){
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
