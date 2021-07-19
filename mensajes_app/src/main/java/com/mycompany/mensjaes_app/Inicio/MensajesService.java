/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mensjaes_app.Inicio;

import java.util.Scanner;

/**
 *
 * @author c.can.castillo
 */
public class MensajesService {
    
    public static void crearMensaje(){
    Scanner sc = new Scanner(System.in);
        System.out.println("ESCRIBE TU MENSAJE: ");
        String mensaje = sc.nextLine();
        
        System.out.println("TU NOMBRE: ");
        String nombre = sc.nextLine();
        
        Mensajes registro  = new Mensajes();
        registro.setMensaje(mensaje);
        registro.setAutor_mensaje(nombre);
        
        MensajesDAO.crearMensajeDB(registro);
    }
    
    public static void listarMensajes(){
        MensajesDAO.leerMensajesDB();
    }
    
    public static void borrarMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("INDICA EL ID DEL MENSAJE QUE QUIERAS BORRAR");
        int id_mensaje = sc.nextInt();
        MensajesDAO.borrarMensajeDB(id_mensaje);
    }
    
    public static void editarMensaje(){
        Scanner sc = new Scanner(System.in);
        System.out.println("ESCRIBE TU NUEVO MENSAJE");
        String mensaje = sc.nextLine();
        
        System.out.println("INDICA EL ID DEL MENSAJE A EDITAR");
        int id_mensaje = sc.nextInt();
        Mensajes actualizacion = new Mensajes();
        actualizacion.setId_mensaje(id_mensaje);
        actualizacion.setMensaje(mensaje);
        
        MensajesDAO.actualizarMensajeDB(actualizacion);
    
    }
    
}
