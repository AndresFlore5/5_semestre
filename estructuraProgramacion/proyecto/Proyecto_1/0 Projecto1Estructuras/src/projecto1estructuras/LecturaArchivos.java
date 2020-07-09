/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto1estructuras;

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

//Clase para la lectura del archivo de entrada .asc
public class LecturaArchivos {
        //
        
    /*    
    public void Direccionarchivo(File archivo){
        this.archivo=archivo;
    }*/
    public static void obtencionDatosArchivo(File archivo,ArrayList<String> etiqueta, ArrayList<Linea> linea3, ArrayList<String>lineasArch) {
        //File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        
        String dato1="",dato2="",dato3="";
        int contador=0;
        boolean espacio=false;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            //archivo = new File (archivo2.getAbsolutePath());
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            String [] linea2;
            
            
            //Deteccion de Etiquetas y de NMONICOS
            while((linea=br.readLine())!=null){
                    
                    linea=linea.replaceAll("\\s"," ");
                    lineasArch.add(linea);
                    linea2=linea.split(" ");
                    dato1="";
                    dato2="";
                    dato3="";
                    contador=0;
                    for(int x=0; x<linea2.length; x++){
                        //System.out.println(x+linea2[x]);
                        if(linea2[x].contains("*"))
                        {
                            break;
                        }else if(!linea2[x].equals("") && x==0){
                            //System.out.println("Etiqueta["+linea2[x]+"]");
                            dato1=linea2[x];
                            etiqueta.add(linea2[x]);
                            System.out.println(dato1);
                            contador++;
                            espacio=false;
                        }else if(!linea2[x].equals("") && x!=0){
                            //System.out.println("Obtenido["+linea2[x]+"]");
                            if(!dato1.equals("")&&dato2.equals("")){
                                dato2=linea2[x];
                                System.out.println(dato2);
                                contador++;
                                espacio=false;
                            }else if(dato1.equals("")&&dato2.equals("")){
                                dato2=linea2[x];
                                System.out.println(dato2);
                                contador++;
                                espacio=true;
                            }
                            else if(!dato2.equals("")&&dato3.equals("")){
                                
                                
                                dato3=linea2[x];
                                
                                
                                //System.out.println(dato3);
                                contador++;
                            }else if(!dato3.equals("")){
                                dato3=dato3+" "+linea2[x];
                               
                                //System.out.println(dato3);
                                contador++;
                            }
                            
                            
                            
                        }
                        

                    }
                        
                    //System.out.println("contador"+contador);
                        if(contador==1 && espacio==false){
                            linea3.add(new Linea(dato1,espacio));
                        }else if(contador==1 && espacio==true){
                            linea3.add(new Linea(dato2));
                        }else if(contador==2){
                            linea3.add(new Linea(dato2,dato3,espacio));
                        }else if(contador==3){
                            linea3.add(new Linea(dato1,dato2,dato3,espacio));
                        }
                                    
                
                
            }
            
            
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try{                    
               if( null != fr ){   
                  fr.close();     
               }                  
            }catch (Exception e2){ 
               e2.printStackTrace();
            }
        }
    }
}