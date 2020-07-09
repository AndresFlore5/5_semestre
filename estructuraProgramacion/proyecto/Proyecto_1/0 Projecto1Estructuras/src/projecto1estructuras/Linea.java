/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto1estructuras;

/**
 *
 * @author Sergio
 */
public class Linea {
    public String etiqueta="", mnemonico="", dato="", directiva="", opcode=""; 
    public int TamBite=0;
    boolean espacioInicial=false;
    //Constructor para la lectura de una linea con 3 datos
    public Linea (String etiqu, String mnemo, String dato, boolean esp){
        this.etiqueta=etiqu;
        this.mnemonico=mnemo;
        this.dato=dato;
        this.espacioInicial=esp;
    
    }
    
    //Constructor para la lectura de una linea con 2 datos
    public Linea(String mnemo, String dato, boolean esp){
        this.mnemonico=mnemo;
        this.dato=dato;
        this.espacioInicial=esp;
    }
    //Constructor con 1 dato
    public Linea(String etiq, boolean esp ){
        this.etiqueta=etiq;
        this.espacioInicial=esp;
    }
    public Linea(String mnemo){
        this.mnemonico=mnemo;
        this.espacioInicial=true;
    }
}
