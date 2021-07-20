/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gatos_app;

import com.google.gson.Gson;
import static com.google.gson.internal.bind.TypeAdapters.URL;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author c.can.castillo
 */
public class GatosService {
    
    public static void verGatos() throws IOException{
        
        //TRAEMOS LOS DATOS DE ESTA API
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder()
        .url("https://api.thecatapi.com/v1/images/search").method("GET", null).build();
        Response response = client.newCall(request).execute();
        
        String elJson = response.body().string();
        
        //ELIMINAR LOS CORCHETES
        elJson = elJson.substring(1,elJson.length());
        elJson = elJson.substring(0,elJson.length()-1);
        
        //CREAR UN OBJETO DE LA CLASE GSON
        Gson gson = new Gson();
        Gatos gatos = gson.fromJson(elJson, Gatos.class);
        
        //REDIMENSIONAR EN CASO DE SER NECESARIO
        Image image = null;
        try {
            URL url = new URL(gatos.getUrl());
            image = ImageIO.read(url);
            
            ImageIcon fondoGato = new ImageIcon(image);
            
            if(fondoGato.getIconWidth() > 800){
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            } 
            
            String menu = "OPCIONES: \n"
                    + "1. VER OTRA IMAGEN\n"
                    + "2. FAVORITO\n"
                    + "3. VOLVER \n";
            
            String[] botones = {"VER OTRA IMAGEN", "FAVORITO", "VOLVER"};
            String id_gato = gatos.getId();
            String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato, JOptionPane.INFORMATION_MESSAGE,fondoGato, botones, botones[0]);
            
            int seleccion = -1;
            for(int i=0; i<botones.length;i++){
                if(opcion.equals(botones[i])){
                    seleccion = i;
                }
            }
            
            switch(seleccion){
                case 0: 
                    verGatos();
                    break;
                case 1:
                    favoritoGato(gatos);
                    break;
                default:
                    break;  
            }
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public static void favoritoGato(Gatos gato){
        try {
            OkHttpClient client = new OkHttpClient();//.newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\":\""+gato.getId()+"\"\r\n}");
            Request request = new Request.Builder()
            .url("https://api.thecatapi.com/v1/favourites")
            .method("POST", body)
            .addHeader("Content-Type", "application/json")
            .addHeader("x-api-key", gato.getApikey())
            .build();
            Response response = client.newCall(request).execute();
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public static void verFavoritos(String apikey) throws IOException{
        
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder()
        .url("https://api.thecatapi.com/v1/favourites")
        .get()
        .addHeader("Content-Type", "application/json")
        .addHeader("x-api-key", apikey)
        .build();
        
        Response response = client.newCall(request).execute();
        
        //guardamos el string con la respuesta
        String elJson = response.body().string();
        
        //creamos el objeto gson
        Gson gson = new Gson();
        
        GatosFav[] gatosArray = gson.fromJson(elJson, GatosFav[].class);
        
        if(gatosArray.length >0){
            int min = 1;
            int max = gatosArray.length;
            int aleatorio = (int) (Math.random() * ((max-min)+1)) + min;
            int indice = aleatorio - 1;
            
            GatosFav gatofav = gatosArray[indice];
            
            //REDIMENSIONAR EN CASO DE SER NECESARIO
            Image image = null;
            try {
                URL url = new URL(gatofav.image.getUrl());
                image = ImageIO.read(url);

                ImageIcon fondoGato = new ImageIcon(image);

                if(fondoGato.getIconWidth() > 800){
                    Image fondo = fondoGato.getImage();
                    Image modificada = fondo.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);
                } 

                String menu = "OPCIONES: \n"
                        + "1. VER OTRA IMAGEN\n"
                        + "2. ELIMINAR FAVORITO\n"
                        + "3. VOLVER \n";

                String[] botones = {"VER OTRA IMAGEN", "ELIMINAR FAVORITO", "VOLVER"};
                String id_gato = gatofav.getId();
                String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato, JOptionPane.INFORMATION_MESSAGE,fondoGato, botones, botones[0]);

                int seleccion = -1;
                for(int i=0; i<botones.length;i++){
                    if(opcion.equals(botones[i])){
                        seleccion = i;
                    }
                }

                switch(seleccion){
                    case 0: 
                        verFavoritos(apikey);
                        break;
                    case 1:
                        borrarFavorito(gatofav);
                        break;
                    default:
                        break;  
                }

            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
    public static void borrarFavorito(GatosFav gatofav){
        try {
            OkHttpClient client = new OkHttpClient();
            
            Request request = new Request.Builder()
             .url("https://api.thecatapi.com/v1/favourites/"+gatofav.getId()+"")
             .delete(null)
             .addHeader("Content-Type", "application/json")
             .addHeader("x-api-key", gatofav.getApikey())
             .build();
            
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
