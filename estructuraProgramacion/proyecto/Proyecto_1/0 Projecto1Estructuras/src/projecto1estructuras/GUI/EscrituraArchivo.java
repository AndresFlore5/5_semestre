/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto1estructuras.GUI;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import projecto1estructuras.DireccionMemoria;
import projecto1estructuras.EQU;
import projecto1estructuras.Linea;
import projecto1estructuras.formato;

/**
 *
 * @author Sergio
 */
public class EscrituraArchivo {
    
    
    public static void escrtioMotorola(ArrayList<DireccionMemoria> DirMem,ArrayList<Integer> Org){
        FileWriter fichero = null;
        PrintWriter pw = null;
        int contadorOrg=0;
        int contadorLista=0;
        int cont=0;
        ArrayList<String>cadenacion= new ArrayList<String>();
        try
        {
        fichero = new FileWriter("../0 Projecto1Estructuras\\motorola.hex");
            pw = new PrintWriter(fichero);
            pw.println("S00B00002020202020202020F4");
            String concad="S121";
            int dato=Org.get(contadorOrg);
            if(Org.size()!=1){
                contadorOrg++;
                for (int i = 0; i < DirMem.size(); i++) {

                    if(cont<31&&DirMem.get(i).espacio!=Org.get(contadorOrg)){
                        if(cont==0){
                           concad+=Integer.toHexString(dato)+DirMem.get(i).dato;
                           cont+=2;
                        }else{
                            concad+=DirMem.get(i).dato;
                            cont++;
                        }
                    }else if(cont==31&&DirMem.get(i).espacio!=Org.get(contadorOrg)){
                        pw.println(concad+"00");
                        cont=0;
                        concad="";
                        if(Org.contains(DirMem.get(i).espacio)){

                            contadorOrg++;
                            dato=Org.get(contadorOrg);
                        }else{
                            dato+=30;
                        }
                        concad="S121"+Integer.toHexString(dato)+DirMem.get(i).dato;
                        cont+=2;

                    }else if(DirMem.get(i).espacio==Org.get(contadorOrg)){
                        concad=concad.replace("S121","");
                        concad=concad+"00";
                        int tamanio=concad.length()/2;
                        dato=Org.get(contadorOrg);
                        String tam=Integer.toHexString(tamanio);
                        if(tam.length()==1){
                            tam="0"+tam;
                        }
                        if(contadorOrg!=Org.size()-1){
                            contadorOrg++;
                        }
                        concad="S1"+tam+concad;
                        pw.println(concad);
                        concad="";
                        cont=0;

                    }

                }
                String tam=Integer.toHexString(concad.length()/2);
                if(tam.length()==1){
                    pw.println("S10"+Integer.toHexString(concad.length()/2)+concad);
                }else{
                    pw.println("S10"+Integer.toHexString(concad.length()/2)+concad);
                }
            }else{
                for (int i = 0; i < DirMem.size(); i++) {

                    if(cont<31){
                        if(cont==0){
                           concad+=Integer.toHexString(dato)+DirMem.get(i).dato;
                           cont+=2;
                        }else{
                            concad+=DirMem.get(i).dato;
                            cont++;
                        }
                    }else if(cont==31){
                        pw.println(concad+"00");
                        cont=0;
                        concad="";
                        if(Org.contains(DirMem.get(i).espacio)){
                            dato=Org.get(contadorOrg);
                        }else{
                            dato+=30;
                        }
                        concad="S121"+Integer.toHexString(dato)+DirMem.get(i).dato;
                        cont+=2;

                    }else if(DirMem.get(i).espacio==Org.get(contadorOrg)){
                        concad=concad.replace("S121","");
                        concad=concad+"00";
                        int tamanio=concad.length()/2;
                        dato=Org.get(contadorOrg);
                        String tam=Integer.toHexString(tamanio);
                        if(tam.length()==1){
                            tam="0"+tam;
                        }
                        
                        concad="S1"+tam+concad;
                        pw.println(concad);
                        concad="";
                        cont=0;

                    }

                }
                concad=concad.replace("21", Integer.toHexString(concad.length()/2));
                
                pw.println(concad);
                
            }
                    
            
            pw.print("S90380007C");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    //Impresión del foramto del archivo hexadecimal
    
    
    
    public static void escritoHEXA(ArrayList<DireccionMemoria> DirMem,ArrayList<Integer> Org){
        FileWriter fichero = null;
        PrintWriter pw = null;
        int contadorOrg=0;
        int contadorLista=0;
        int cont=0;
        ArrayList<String>cadenacion= new ArrayList<String>();
        try
        {
            
            fichero = new FileWriter("../0 Projecto1Estructuras\\prueba.hex");
            pw = new PrintWriter(fichero);
            
            for(int i=0; i<DirMem.size();i++){
                if(DirMem.get(i).espacio==Org.get(contadorOrg)&&contadorOrg==0){
                    
                    pw.print("<"+Integer.toHexString(DirMem.get(i).espacio)+">   ");
                    if(Org.size()!=1){
                        contadorOrg++;
                    }
                    contadorLista=DirMem.get(i).espacio;
                }else if(DirMem.get(i).espacio==Org.get(contadorOrg)&&contadorOrg!=0&&contadorOrg!=Org.size()){
                    pw.print("\n\n<"+Integer.toHexString(DirMem.get(i).espacio)+">   ");
                    if(contadorOrg!=Org.size()-1){
                        contadorOrg++;
                        cont=0;
                    }
                    contadorLista=DirMem.get(i).espacio;
                }
                
                if(cont!=15){
                    
                    pw.print(DirMem.get(i).dato+" ");
                    
                    cont++;
                }else
                {
                    pw.println(DirMem.get(i).dato+" ");
                    cont=0;
                    contadorLista+=16;
                    pw.print("\n<"+Integer.toHexString(contadorLista)+">   ");
                }
                
               
            }
            
             
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    
    }

    
    
    public static void escritoLST(File archivo, ArrayList<DireccionMemoria> DirMem,ArrayList<String> etiquetas,ArrayList<EQU> equ, ArrayList<String> lineasArch, ArrayList<Integer> org,ArrayList <String> error,javax.swing.JTextPane tp) throws FileNotFoundException{
        //Lectura
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        
        int contador=1;
        int conEqu=0;
        int conDir=0;
        int conOrg=0;
        int conEti=0;
        boolean espacio=false;
        try {
            

            fichero = new FileWriter("../0 Projecto1Estructuras\\lista.lst");
            pw = new PrintWriter(fichero);
            
            String linea;
            /*
            for(int j=0;j<lineasArch.size();j++){
                pw.println(j+"  "+lineasArch.get(j));
                
            }*/
            pw.println("M68HC11 Absolute Assembler  Version 1.0.0 "+archivo.getAbsolutePath());
            for(int j=0;j<lineasArch.size();j++){
                
                pw.print("\n"+j+"\tA\t");
                appendToPane(tp,"\n"+j+"\tA\t",Color.DARK_GRAY);
                if(lineasArch.get(j).startsWith("*")){
                    pw.print("\t\t\t\t\t");
                    pw.println(lineasArch.get(j));
                    appendToPane(tp,"\t\t"+lineasArch.get(j),Color.GREEN);
                }else if(lineasArch.get(j).toUpperCase().contains("EQU")){
                    pw.print(String.format("%-10s",equ.get(conEqu).valor).replace("$","")+"\t\t\t\t\t");          
                    pw.println(lineasArch.get(j));
                    appendToPane(tp,String.format("%-10s",equ.get(conEqu).valor).replace("$","")+"\t\t",Color.RED);
                    appendToPane(tp,lineasArch.get(j),Color.BLACK);
                    conEqu++;
                }else if(lineasArch.get(j).toUpperCase().contains("ORG")){
                    
                    pw.print(String.format("%-10s",Integer.toHexString(org.get(conOrg))));
                    pw.println(lineasArch.get(j));
                    appendToPane(tp,String.format("%-10s",Integer.toHexString(org.get(conOrg))),Color.RED);
                    appendToPane(tp,"\t"+lineasArch.get(j),Color.BLACK);
                    conOrg++;
                }else if(lineasArch.get(j).toUpperCase().contains("END")){
                    
                    pw.print("\t\t\t\t\t");
                    pw.println(lineasArch.get(j));
                    appendToPane(tp,"\t"+lineasArch.get(j),Color.BLACK);
                    
                }else if(!lineasArch.get(j).equals("")){
                    if(lineasArch.get(j).contains(DirMem.get(conDir).nmemo)){
                        pw.print(Integer.toHexString(DirMem.get(conDir).espacio)+"  ");
                        appendToPane(tp,Integer.toHexString(DirMem.get(conDir).espacio)+"  ",Color.RED);
                        String cadena="";
                        for(int i=0; i<DirMem.get(conDir).sizeOp;i++){
                            
                            if(i!=DirMem.get(conDir).sizeOp-1){
                                cadena=cadena+DirMem.get(conDir+i).dato;
                                pw.print(DirMem.get(conDir+i).dato+" ");
                                if(DirMem.get(conDir+i).tipoDato.equals("DATO"))
                                appendToPane(tp,DirMem.get(conDir+i).dato+" ",Color.orange);
                                else{appendToPane(tp,DirMem.get(conDir+i).dato+" ",Color.BLUE);}
                            }else if(i==DirMem.get(conDir).sizeOp-1){
                                cadena=cadena+DirMem.get(conDir+i).dato;
                                pw.print(DirMem.get(conDir+i).dato+"\t");
                                appendToPane(tp,DirMem.get(conDir+i).dato+"\t",Color.orange);
                            }

                        }
                        pw.println(lineasArch.get(j));
                        appendToPane(tp,lineasArch.get(j),Color.BLACK);
                        if(conDir+DirMem.get(conDir).sizeOp>=DirMem.size())
                            conDir=conDir+DirMem.get(conDir).sizeOp-1;
                        else
                            conDir=conDir+DirMem.get(conDir).sizeOp;
                    }else{
                        
                    //pw.print("\t\t\t\t\t");
                        for (int i = 0; i < etiquetas.size(); i++) {
                            if(lineasArch.get(j).contains(etiquetas.get(i))){
                                //appendToPane(tp,String.valueOf(i),Color.GREEN); //si descomentan se daran cuenta que lo tiene en dos partes del arreglo es decir repetido
                                pw.print(String.format("%-10s",Integer.toHexString(DirMem.get(conDir).espacio)));
                                pw.println(lineasArch.get(j));
                                appendToPane(tp,String.format("%-10s",Integer.toHexString(DirMem.get(conDir).espacio)),Color.RED); //CHECAR se repite dos veces no hay tanta importancia pero es raro
                                appendToPane(tp,lineasArch.get(j),Color.BLACK);
                            }
                        }
                        
                    
                    }    
                        
                }else{
                    pw.println(Integer.toHexString(DirMem.get(conDir).espacio)+"\t\t\t\t\t");
                    appendToPane(tp,Integer.toHexString(DirMem.get(conDir).espacio)+"\t\t",Color.RED);
                }
                
                
                
                    
            }
            
            for (int i = 0; i < error.size(); i++) {
                pw.println(error.get(i));
                appendToPane(tp,error.get(i),Color.MAGENTA);
            }
            
            
        
        
    } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    
    
    
    
     public static void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }
}