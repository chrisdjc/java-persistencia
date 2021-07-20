/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gatos_app;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author c.can.castillo
 */
public class Inicio {
    
    public static void main(String[] args) throws IOException {
        int opcion_menu = -1;
        String[] botones = {
            "1. VER GATOS",
            "2. VER FAVORITOS",
            "3. SALIR"
        };
        
        do{
            //MENU PRINCIPAL
            String opcion = (String) JOptionPane.showInputDialog(null, "GATITOS JAVA", "MENU PRINCIPAL", JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);
            
            //VALIDAMOS QUÉ OPCIÓN SELECCIONA EL USUARIO
            for(int i=0; i<botones.length;i++){
                if(opcion.equals(botones[i])){
                    opcion_menu = i;
                }
            }
            
            switch(opcion_menu){
                case 0:
                    GatosService.verGatos();
                    break;
                case 1:
                    Gatos gato = new Gatos();
                    GatosService.verFavoritos(gato.getApikey());
                    break;
                default:
                    break;
            }
            
        }while(opcion_menu!=1);
    }
}
