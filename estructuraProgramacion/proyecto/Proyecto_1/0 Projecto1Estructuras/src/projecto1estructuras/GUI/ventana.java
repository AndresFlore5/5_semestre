/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto1estructuras.GUI;
import java.awt.Color;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import projecto1estructuras.DatosObtencion;
import projecto1estructuras.DireccionMemoria;
import projecto1estructuras.EQU;
import projecto1estructuras.LecturaArchivos;
import projecto1estructuras.Linea;
import projecto1estructuras.LocalizacionEtiqueta;
import projecto1estructuras.excel;

/**
 *
 * @author murryFly
 */
public class ventana extends javax.swing.JFrame {
    
    JFileChooser seleccion = new JFileChooser(); //Para seleccionar
    File archivo; //Archivo
    metodosFiles case1 = new metodosFiles();
    JPanel topPanel=new JPanel();
    JTextPane tPane;
    //LecturaArchivos LArch= new LecturaArchivos();
    
    public ventana() {
        initComponents();
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        btn_editar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Murrieta_Valdespino_Reza");

        btn_editar.setText("Examinar y Compilar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel1.setText("Ensamblador TeaMancos");

        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 661, Short.MAX_VALUE)
                        .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(325, 325, 325))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_editar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        if(seleccion.showDialog(null, "Cargar Archivo")==JFileChooser.APPROVE_OPTION){
            archivo = seleccion.getSelectedFile();
            if(archivo.canRead()){
                if(archivo.getName().toUpperCase().endsWith("ASC") ){
                    //Colocar código ya del análisis del archivo -> ENSAMBLADOR
                    //LArch.Direccionarchivo(archivo);
                    
                    try{
                        OperandoArchivo();
                    }catch (IOException e){
                        System.out.println("IO");
                    }
                    
                    
                }else{
                    JOptionPane.showMessageDialog(null, "Selecciona un archivo asc");
                }
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventana().setVisible(true);
                
            }
        });
    }
    public void OperandoArchivo() throws IOException{
        
        
        ArrayList<String> etiquetas= new ArrayList<String>();
        ArrayList<String> equEtiqueta= new ArrayList<String>();
        ArrayList<EQU> equ=new ArrayList<EQU>();
        ArrayList<String> listaArch=new ArrayList<String>();
        ArrayList<Linea> datos= new ArrayList<Linea>();
        ArrayList <String> directiva=new ArrayList<String>();
        ArrayList<DireccionMemoria> DirMem= new ArrayList<DireccionMemoria>();
        ArrayList<LocalizacionEtiqueta> LocEti= new ArrayList<LocalizacionEtiqueta>();
        ArrayList<Integer> ListaOrg=new ArrayList<Integer>();
        
        
        ArrayList<String>error= new ArrayList<String>();
        Boolean correcto=true;
        Boolean END=false;
        int org=0x0;
        String aux;
        
        
        
        //txt_datos.setText("Obtención de datos del archivo");
        
        LecturaArchivos.obtencionDatosArchivo(this.archivo,etiquetas, datos,listaArch);
        
        /*
        System.out.println("\nETIQUETAS");
        for(int y=0; y<etiquetas.size(); y++)
            System.out.println(etiquetas.get(y));
        
        System.out.println("\nNMONICOS");
        for(int y=0; y<datos.size(); y++){
                System.out.print(datos.get(y).etiqueta);
                System.out.print("\t"+datos.get(y).mnemonico);
                System.out.println("\t"+datos.get(y).dato);
        }
        
        */
        
        
        
        //Obtención de las EQU
        for(int i=0; i<datos.size();i++){
            if(datos.get(i).mnemonico.equals("EQU")){
                if(datos.get(i).dato.contains("$00")&&(!datos.get(i).dato.equals("$00")||!datos.get(i).dato.equals("#$00"))){
                    datos.get(i).dato=datos.get(i).dato.replace("$00", "$");
                }
                equ.add(new EQU(datos.get(i).etiqueta,datos.get(i).dato));
                
            }
        }
        /*
        for(int i=0; i<equ.size();i++){
            System.out.println(equ.get(i).etiqueta+"\tEQU\t"+equ.get(i).valor);
            
        }
        */
        //Cambio de las directivas 
        //System.out.println("Sustituciones");
        for(int i=0;i<equ.size();i++){
            for(int j=0;j<datos.size();j++){
                //Cambio de las etiquetas que usan EQU por el valor.
                if(datos.get(j).dato.equals(equ.get(i).etiqueta)&&!datos.get(j).mnemonico.toUpperCase().equals("EQU")&&!datos.get(j).dato.contains("#")){
                datos.get(j).dato = equ.get(i).valor;
                    
                }else if(datos.get(j).dato.equals("#"+equ.get(i).etiqueta)&&!datos.get(j).mnemonico.toUpperCase().equals("EQU")){
                    datos.get(j).dato ="#"+equ.get(i).valor;
                }
                
            }
        }
        for (int i = 0; i < datos.size(); i++) {
            if(datos.get(i).dato.contains("'")){
                
                datos.get(i).dato=datos.get(i).dato.replace("'", "$");
            }
            
        }
        /*
        for(int y=0; y<datos.size(); y++){
                System.out.print(datos.get(y).etiqueta);
                System.out.print("\t"+datos.get(y).mnemonico);
                System.out.println("\t"+datos.get(y).dato);
        }*/
        /*txt_datos.setForeground(Color.RED);
        txt_datos.setText("\nFin de Obtención de datos");
        txt_datos.setForeground(Color.BLUE);
        txt_datos.setText("\nFin de Obtención de datos2");
        */
        //Obtención de los tipos de datos y de las directivas
        DatosObtencion.ObtencionDatos(datos, etiquetas, directiva, correcto, END,error);
        
        
        //Obtecnión de OPCODE Y tamaño 
        
        for(int m=0; m<datos.size();m++){
            excel.ObtenerOpCode(datos.get(m));
        }
        /*
        for(int m=0; m<datos.size();m++){
            System.out.print("\t"+datos.get(m).mnemonico+"\t");
            System.out.print("\t"+datos.get(m).opcode+"\t");
            System.out.print("\t"+datos.get(m).dato+"\t");
            System.out.println("\t"+datos.get(m).TamBite+"\t");
        }*/
        System.out.println("Primera pasada");
        DatosObtencion.PrimeraPasada(datos, DirMem,LocEti, etiquetas, END,ListaOrg,error);
        if(END=false){
            System.out.println("Sin Directiva END");
        }
        
        
        
        System.out.println("Segunda Pasada");
        //Caso de las directivas JMP Y JSR
        for(int i=0; i<DirMem.size();i++){
            int k=0;
            
            
            if((DirMem.get(i).dato.contains("BD")||DirMem.get(i).dato.contains("7E"))&&DirMem.get(i).tipoDato.contains("OPCODE")){
                while(!LocEti.get(k).etiqueta.toUpperCase().contains(DirMem.get(i+1).dato)){
                    k++;
                }
                String vad=Integer.toHexString(LocEti.get(k).espacio);
                char []dato=vad.toCharArray();
                DirMem.get(i+1).dato=""+dato[0]+dato[1];
                DirMem.get(i+2).dato=""+dato[2]+dato[3];
                //System.out.println("\t\t"+DirMem.get(i+1).dato);
                //System.out.println("\t\t"+DirMem.get(i+2).dato);
            }
        }
        for(int i=0;i<DirMem.size();i++){
            DirMem.get(i).dato=DirMem.get(i).dato.replace(" ","");
            DirMem.get(i).dato=DirMem.get(i).dato.replace(".0","");
            if(DirMem.get(i).dato.length()==1){
                DirMem.get(i).dato="0"+DirMem.get(i).dato;
                
            }
        }
        //Caso de las directivas relativas junto
        DatosObtencion.SegundaPasada(DirMem, LocEti,error);
        
        //Escritura de archivos
        EscrituraArchivo.escritoHEXA(DirMem, ListaOrg);
        
        EscrituraArchivo.escritoLST(archivo, DirMem,etiquetas, equ,listaArch,ListaOrg,error,this.jTextPane1);
        
        EscrituraArchivo.escrtioMotorola(DirMem, ListaOrg);
        
        
        for(int i=0; i<DirMem.size();i++){
            System.out.print("\u001B[0m"+Integer.toHexString(DirMem.get(i).espacio)+"  ");
            if(DirMem.get(i).tipoDato.equals("OPCODE")){
                System.out.println("\u001B[31m"+DirMem.get(i).dato);
            }else{
                System.out.println("\u001B[34m"+DirMem.get(i).dato);
            }
                
            
        }
        
        
        
       
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_editar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

   
}
/*
String dSep=datos.get(m).dato.replace(",X,#", "-");
                dSep=dSep.replace(",x,#", "-");
                dSep=dSep.replace(",Y,#", "-");
                dSep=dSep.replace(",y,#", "-");
                dSep=dSep.replace("$", "");
                dSep=dSep.replace(" ", "-");
                String []Opcode;
                Opcode=datos.get(m).opcode.split(" ");
                cont=0;
                while(cont<datos.get(m).TamBite){
                    if (cont==0 && Opcode.length<=2){
                        DirMem.add(new DireccionMemoria(org,Opcode[0]));
                        System.out.println(Integer.toHexString(org)+" "+Opcode[0]);
                        cont++;
                        org++;
                    }else if((cont==0 && Opcode.length>2)){
                        DirMem.add(new DireccionMemoria(org,Opcode[0]));
                        System.out.println(Integer.toHexString(org)+" "+Opcode[0]);
                        org++;
                        cont++;
                        DirMem.add(new DireccionMemoria(org,Opcode[1]));
                        System.out.println(Integer.toHexString(org)+" "+Opcode[1]);
                        org++;
                        cont++;
                    }else if(cont!=0 ){
                        
                        
                    }
                }

*/