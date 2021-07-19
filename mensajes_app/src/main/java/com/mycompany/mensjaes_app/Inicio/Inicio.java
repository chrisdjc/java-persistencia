/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensjaes_app.Inicio;

import java.sql.Connection;
import java.util.Scanner;

/**
 *
 * @author c.can.castillo
 */
public class Inicio {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int opcion = 0;
        
        do{
            System.out.println("- - - - - - - - - - - - - - - - - ");
            System.out.println("APLICACIÓN DE MENSAJES");
            System.out.println("1. CREAR MENAJE");
            System.out.println("2. LISTAR MENSAJES");
            System.out.println("3. EDITAR MENSAJE");
            System.out.println("4. BORRAR MENSAJE");
            System.out.println("5. SALIR");
            
            //LEER LA OPCION CAPTURADA
            opcion = sc.nextInt();
            
            switch (opcion){
                case 1:
                    MensajesService.crearMensaje();
                    break;
                case 2:
                    MensajesService.listarMensajes();
                    break;
                case 3:
                    MensajesService.editarMensaje();
                    break;
                case 4:
                    MensajesService.borrarMensaje();
                    break;
                default:
                    break;
            }
            
        }while(opcion!=5);
        
        /*Conexion conexion = new Conexion();
        try(Connection cnx = conexion.get_Connection()){
        }catch(Exception e){
            System.out.println(e);
        }*/
    }
}
