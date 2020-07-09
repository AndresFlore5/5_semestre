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
public class DireccionMemoria{
    public int espacio,sizeOp=0;
    public String dato, tipoDato;
    public String nmemo="";
    public DireccionMemoria(int espacio, String dato, String tipoDato, int sizeOp, String nmemo){
        this.espacio=espacio;
        this.dato=dato;
        this.tipoDato=tipoDato;
        this.sizeOp=sizeOp;
        this.nmemo=nmemo;
    }
    public DireccionMemoria(int espacio, String dato, String tipoDato, int sizeOp){
        this.espacio=espacio;
        this.dato=dato;
        this.tipoDato=tipoDato;
        this.sizeOp=sizeOp;
    }
    
}
