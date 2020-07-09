/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto1estructuras;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sergio
 */
public class DatosObtencion {
    //Clase con el método para obtener que tipo de dato se encuentra en el código
    public static void ObtencionDatos(ArrayList<Linea> datos, ArrayList<String> etiquetas,ArrayList <String> directiva, boolean correcto, boolean END,ArrayList <String> error) throws IOException{
        
        for(int i=0; i<datos.size();i++){
            
            
            if(etiquetas.contains(datos.get(i).etiqueta)){
                System.out.print("\tEtiqueta "+datos.get(i).etiqueta);
                error.add("");
            }else if (!etiquetas.contains(datos.get(i).etiqueta)&&(datos.get(i).mnemonico.toUpperCase().equals("EQU")||datos.get(i).mnemonico.toUpperCase().equals("ORG")||datos.get(i).etiqueta.toUpperCase().equals("END")||datos.get(i).etiqueta.toUpperCase().equals("FCB"))){
                System.out.print("\tDirectiva "+datos.get(i).mnemonico);
                if(datos.get(i).mnemonico.toUpperCase().equals("END")){
                    END=true;
                }
                error.add("");
                
            }else if(!etiquetas.contains(datos.get(i).etiqueta)&&excel.nmonico(datos.get(i).mnemonico.toUpperCase(),directiva)){
                /*
                System.out.println("\t\tNmonico: "+datos.get(i).mnemonico);
                System.out.println("\t\tDirectivas Soportadas: "+directiva);
                */
                //Directivas
                //Casos especiales (BCLR, BRCLR, BSET, )
                
                if(datos.get(i).mnemonico.toUpperCase().equals("BCLR")||datos.get(i).mnemonico.toUpperCase().equals("BSET")||datos.get(i).mnemonico.toUpperCase().equals("BRCLR")||datos.get(i).mnemonico.toUpperCase().equals("BRSET")){
                    System.out.println("Dato "+datos.get(i).dato);
                    if(datos.get(i).dato.toUpperCase().contains(",X,#")&&directiva.contains("INDX")){
                            System.out.print("\t"+datos.get(i).mnemonico+"\t INDEXADO EN X");
                            datos.get(i).directiva = "INDX";
                            error.add("");
                            
                    }else if (datos.get(i).dato.toUpperCase().contains(",Y,#")&&directiva.contains("INDY")){
                            System.out.print("\t"+datos.get(i).mnemonico+"\t INDEXADO EN Y");
                            datos.get(i).directiva = "INDY";
                            error.add("");
                    }else if(directiva.contains("DIR")&&!datos.get(i).dato.toUpperCase().contains("X")&&!datos.get(i).dato.toUpperCase().contains("Y")){
                        System.out.print("\t"+datos.get(i).mnemonico+"\t DIRECTO");
                        datos.get(i).directiva = "DIR";
                        error.add("");
                        
                    }
                    
                }else{
                    if(datos.get(i).dato.contains("$")&&!datos.get(i).dato.contains("#")){
                        if(datos.get(i).dato.toUpperCase().contains("X") || datos.get(i).dato.toUpperCase().contains("Y"))
                        {
                            if((datos.get(i).dato.contains("X")||datos.get(i).dato.contains("x"))&&directiva.contains("INDX")){
                                System.out.print("\t"+datos.get(i).mnemonico+"\t INDEXADO EN X");
                                datos.get(i).directiva = "INDX";
                                error.add("");
                                
                            }else if ((datos.get(i).dato.contains("Y")||datos.get(i).dato.contains("y"))&&directiva.contains("INDY")){
                                System.out.print("\t"+datos.get(i).mnemonico+"\t INDEXADO EN Y");
                                datos.get(i).directiva = "INDY";
                                error.add("");
                                
                            }

                        }else{
                            if(((datos.get(i).dato.length()<5))&&directiva.contains("DIR")&&!datos.get(i).dato.contains("#")){
                                System.out.print("\t"+datos.get(i).mnemonico+"\t DIRECTO");
                                datos.get(i).directiva ="DIR";
                                error.add("");
                                
                            }else if((datos.get(i).dato.length()==5)&&directiva.contains("EXT")&&!datos.get(i).dato.contains("#")){
                                System.out.print("\t"+datos.get(i).mnemonico+"\t EXTENDIDO");
                                datos.get(i).directiva= "EXT";
                                error.add("");
                            }else if((datos.get(i).dato.length()==3)&&directiva.contains("EXT")&&!datos.get(i).dato.contains("#")){
                                System.out.print("\t"+datos.get(i).mnemonico+"\t EXTENDIDO");
                                datos.get(i).directiva= "EXT";
                                datos.get(i).dato=datos.get(i).dato.replace("$", "$00");
                                error.add("");
                            }
                        }
                    }else if(datos.get(i).dato.contains("$")&&datos.get(i).dato.contains("#")&&directiva.contains("IMM")){
                        System.out.print("\t"+datos.get(i).mnemonico+"\t INMEDIATO");
                        datos.get(i).directiva = "IMM";
                        error.add("");
                    
                    
                    
                    }else if(!datos.get(i).dato.contains("$")&&directiva.contains("INH")&&!etiquetas.contains(datos.get(i).dato)){
                        System.out.print("\t"+datos.get(i).mnemonico+"\t INHERENTE");
                        datos.get(i).directiva = "INH";
                        error.add("");
                    }else if(!datos.get(i).dato.contains("$")&&directiva.contains("REL")&&etiquetas.contains(datos.get(i).dato)){
                        System.out.print("\t"+datos.get(i).mnemonico+"\t RELATIVO");
                        datos.get(i).directiva = "REL";
                        error.add("");
                        
                    }else if(datos.get(i).mnemonico.toUpperCase().equals("JSR")||datos.get(i).mnemonico.toUpperCase().equals("JMP")){
                        System.out.print("\t"+datos.get(i).mnemonico+"\t EXTENDIDO");
                        datos.get(i).directiva = "EXT";
                        error.add("");
                    }else{
                        //Errores posibles
                        
                        //Etiqueta inexiste
                        if(directiva.contains("REL")&&!etiquetas.contains(datos.get(i).dato)){
                            System.out.print("003\t"+datos.get(i).mnemonico+"\t Relativo no contiene etiqueta válida");
                            error.add("\t"+datos.get(i).mnemonico+"\t Relativo no contiene etiqueta válida");
                            
                        //Instruccion carece de operandos
                        }else if(!directiva.contains("INH")&&(datos.get(i).dato.contains("$")||datos.get(i).dato.contains("#"))){
                            System.out.print("005\t"+datos.get(i).mnemonico+"\t Necesita de dato");
                            error.add("\t"+datos.get(i).mnemonico+"\t Necesita de dato");
                            
                        //Constante inexistente
                        }else if(!directiva.contains("INH")&&!etiquetas.contains(datos.get(i).dato)){
                            System.out.print("001\t"+datos.get(i).mnemonico+"\t constante inexistente");
                            error.add("001\t"+datos.get(i).mnemonico+"\t constante inexistente");
                            
                        //Instruccion no lleva  operadores
                        }else if(datos.get(i).dato.contains("$")&&directiva.contains("INH")){
                            System.out.print("006\t"+datos.get(i).mnemonico+"\t Inherente contiene valores");
                            error.add("\t"+datos.get(i).mnemonico+"\t Inherente contiene valores");
                            
                        //Mnemonico inexistente
                        }else{
                            System.out.print("004\t"+datos.get(i).mnemonico+"\t no existe");
                            error.add("\t"+datos.get(i).mnemonico+"\t no existe");
                        }
                        correcto=false;
                        
                    }
                }
                
                directiva.clear();
                
            }else{
                System.out.print("Datos: "+datos.get(i).dato);
            }
            System.out.println("");
        }
        
    }

    public static void PrimeraPasada(ArrayList<Linea> datos,ArrayList<DireccionMemoria> DirMem,ArrayList<LocalizacionEtiqueta> LocEti, ArrayList<String> etiquetas, boolean END, ArrayList<Integer> ListaOrg,ArrayList <String> error){
        int cont, org=0 , firstOrg=0;
        boolean PrimerOrg=false;
        
        for(int m=0; m<datos.size();m++){
            //Inicio para el guardado
            if(etiquetas.contains(datos.get(m).etiqueta)){
                LocEti.add(new LocalizacionEtiqueta(org,datos.get(m).etiqueta));
            }
            //Caso de la directiva ORG
            if(datos.get(m).mnemonico.toUpperCase().equals("ORG")){
                datos.get(m).dato=datos.get(m).dato.replace("$","0x");
                org=(Integer.decode(datos.get(m).dato)).intValue();
                if(PrimerOrg==false){
                    firstOrg=(Integer.decode(datos.get(m).dato)).intValue();
                    PrimerOrg=true;
                }
                ListaOrg.add(org);
                //System.out.println("Org String: "+datos.get(m).dato);
                //System.out.println("Org Dato:"+org);
                
            //Caso de la Directiva END
            }else if(datos.get(m).mnemonico.toUpperCase().equals("END")){
                END=true;
                datos.get(m).dato=datos.get(m).dato.replace("$","0x");
                if(!datos.get(m).dato.equals("")){
                    org=(Integer.decode(datos.get(m).dato)).intValue();
                    if(org==firstOrg&&END){
                        System.out.println("Con END");
                    }else{
                        System.out.println("Sin END");
                        error.get(m).concat("SIN END");
                    }
                }else{
                    System.out.println("Con END");
                }
            //Caso directiva FCB
            }else if(datos.get(m).mnemonico.toUpperCase().equals("FCB")){
                String dSep=datos.get(m).dato.replace("$", "");
                String dSepArr[]=dSep.split(",");
                DirMem.add(new DireccionMemoria(org,dSepArr[0],"DATO",0,datos.get(m).mnemonico));
                System.out.println(Integer.toHexString(org)+" "+dSepArr[0]);
                org++;
                DirMem.add(new DireccionMemoria(org,dSepArr[1],"DATO",0,datos.get(m).mnemonico));
                System.out.println(Integer.toHexString(org)+" "+dSepArr[1]);
                org++;
            //Caso directiva ORG
            }else if(!datos.get(m).mnemonico.toUpperCase().equals("ORG")&&!etiquetas.contains(datos.get(m).dato.toUpperCase())){
                
                cont=0;
                String []Opcode;
                Opcode=datos.get(m).opcode.split(" ");
                String dSep=datos.get(m).dato.replace("$", "");
                dSep=dSep.replace("", "");
                dSep=dSep.replace("#", "");
                dSep=dSep.replace(",X","");
                dSep=dSep.replace(",Y","");
                dSep=dSep.replace(",x","");
                dSep=dSep.replace(",y","");
                dSep=dSep.replace(",","");
                char [] dSepArry=dSep.toCharArray();
                //System.out.println("DSEP "+dSep);
                //Diferentes tipos de escritura de los códigos operacionales y de los datos
                while(cont<datos.get(m).TamBite){
                    if (cont==0 && Opcode.length==1){
                        DirMem.add(new DireccionMemoria(org,""+Opcode[0],"OPCODE",datos.get(m).TamBite,datos.get(m).mnemonico));
                        System.out.println(Integer.toHexString(org)+" "+Opcode[0]);
                        cont++;
                        org++;
                    }else if((cont==0 && Opcode.length>=2)){
                        DirMem.add(new DireccionMemoria(org,Opcode[0],"OPCODE",datos.get(m).TamBite,datos.get(m).mnemonico));
                        System.out.println(Integer.toHexString(org)+" "+Opcode[0]);
                        org++;
                        cont++;
                        DirMem.add(new DireccionMemoria(org,Opcode[1],"OPCODE",datos.get(m).TamBite-1,datos.get(m).mnemonico));
                        System.out.println(Integer.toHexString(org)+" "+Opcode[1]);
                        org++;
                        cont++;
                    }else if(cont!=0 && dSepArry.length==1){
                        String junto1="0"+dSepArry[0];
                        DirMem.add(new DireccionMemoria(org,junto1,"DATO",0));
                        System.out.println(Integer.toHexString(org)+" "+junto1);
                        org++;
                        cont++;
                    }else if(cont!=0 && dSepArry.length==2&& !datos.get(m).directiva.equals("EXT")){
                        String junto1=""+dSepArry[0]+dSepArry[1];
                        DirMem.add(new DireccionMemoria(org,junto1,"DATO",0));
                        System.out.println(Integer.toHexString(org)+" "+junto1);
                        org++;
                        cont++;
                    }else if(cont!=0 && dSepArry.length==2&& datos.get(m).directiva.equals("EXT")){
                        DirMem.add(new DireccionMemoria(org,"00","DATO",0));
                        String junto1=""+dSepArry[0]+dSepArry[1];
                        org++;
                        cont++;
                        DirMem.add(new DireccionMemoria(org,junto1,"DATO",0));
                        System.out.println(Integer.toHexString(org)+" "+junto1);
                        org++;
                        cont++;
                    }else if(cont!=0 && dSepArry.length==3){
                        
                        DirMem.add(new DireccionMemoria(org,"0"+dSepArry[0],"DATO",0));
                        String junto1=""+dSepArry[1]+dSepArry[2];
                        org++;
                        cont++;
                        DirMem.add(new DireccionMemoria(org,junto1,"DATO",0));
                        System.out.println(Integer.toHexString(org)+" "+junto1);
                        org++;
                        cont++;
                    }else if(cont!=0 && dSepArry.length==4){
                        String junto1=""+dSepArry[0]+dSepArry[1];
                        DirMem.add(new DireccionMemoria(org,junto1,"DATO",0));
                        System.out.println(Integer.toHexString(org)+" "+junto1);
                        org++;
                        cont++;
                        String junto2=""+dSepArry[2]+dSepArry[3];
                        System.out.println(Integer.toHexString(org)+" "+junto2);
                        DirMem.add(new DireccionMemoria(org,junto2,"DATO",0));
                        org++;
                        cont++;
                    }else if(cont!=0 && dSepArry.length>4){
                        String junto1=""+dSepArry[0]+dSepArry[1];
                        DirMem.add(new DireccionMemoria(org,junto1,"DATO",0));
                        System.out.println(Integer.toHexString(org)+" "+junto1);
                        org++;
                        cont++;
                        String junto2=""+dSepArry[2]+dSepArry[3];
                        System.out.println(Integer.toHexString(org)+" "+junto2);
                        DirMem.add(new DireccionMemoria(org,junto2,"DATO",0));
                        org++;
                        cont++;
                        String junto3="";
                        for(int i=4;i<dSepArry.length;i++){
                            junto3=junto3+dSepArry[i];
                        }
                        DirMem.add(new DireccionMemoria(org,junto3,"DATO",0));
                        System.out.println(Integer.toHexString(org)+" "+junto3);
                        org++;
                        cont++;
                        
                    }
                }
                //caso que los datos tengan etiquetas
            }else if(!datos.get(m).mnemonico.toUpperCase().equals("ORG")&&etiquetas.contains(datos.get(m).etiqueta.toUpperCase())){
                org++;
                
                
                //caso de las etiquetas con las directivas relativas
            }else if(!datos.get(m).mnemonico.toUpperCase().equals("ORG")&&etiquetas.contains(datos.get(m).dato.toUpperCase())&&datos.get(m).directiva.toUpperCase().equals("REL")){
                DirMem.add(new DireccionMemoria(org,datos.get(m).opcode,"OPCODE",datos.get(m).TamBite,datos.get(m).mnemonico));
                System.out.println(Integer.toHexString(org)+" "+datos.get(m).opcode);
                org++;
                DirMem.add(new DireccionMemoria(org,datos.get(m).dato,"DATO",0));
                System.out.println(Integer.toHexString(org)+" "+datos.get(m).dato);
                org++;
            
                //caso de las etiquetas que son JSR Y JMP
            }else if(!datos.get(m).mnemonico.toUpperCase().equals("ORG")&&etiquetas.contains(datos.get(m).dato.toUpperCase())){
                DirMem.add(new DireccionMemoria(org,datos.get(m).opcode,"OPCODE",datos.get(m).TamBite,datos.get(m).mnemonico));
                System.out.println(Integer.toHexString(org)+" "+datos.get(m).opcode);
                org++;
                DirMem.add(new DireccionMemoria(org,datos.get(m).dato,"DATO",0));
                System.out.println(Integer.toHexString(org)+" "+datos.get(m).dato);
                org++;
                DirMem.add(new DireccionMemoria(org,datos.get(m).dato,"DATO",0));
                System.out.println(Integer.toHexString(org)+" "+datos.get(m).dato);
                org++;
            //caso de las instrucciones inherentes
            }else if(!datos.get(m).mnemonico.toUpperCase().equals("ORG")&&datos.get(m).dato.equals("")){
                DirMem.add(new DireccionMemoria(org,datos.get(m).opcode,"OPCODE",datos.get(m).TamBite,datos.get(m).mnemonico));
                System.out.println(Integer.toHexString(org)+" "+datos.get(m).dato);
                org++;
            }
            
            
            
        }
        
    }
    
    public static void SegundaPasada(ArrayList<DireccionMemoria> DirMem,ArrayList<LocalizacionEtiqueta> LocEti, ArrayList<String>error){
        for(int i=0; i<DirMem.size();i++){
            
            for(int k=0; k<LocEti.size();k++){
                if(LocEti.get(k).etiqueta.equals(DirMem.get(i).dato)){
                    if((DirMem.get(i+1).espacio>LocEti.get(k).espacio)&&((DirMem.get(i+1).espacio-LocEti.get(k).espacio)<127)){
                        //System.out.println(DirMem.get(i).dato+"\t"+DirMem.get(i+1).espacio);
                        //System.out.println(LocEti.get(k).etiqueta+"\t"+LocEti.get(k).espacio);
                        int res=DirMem.get(i).espacio-LocEti.get(k).espacio;
                        res=255-res;
                        String dato=Integer.toHexString(res);
                        //System.out.println("1Valor "+res);
                        if(dato.length()==1){
                            DirMem.get(i).dato="0"+dato;
                        }else{
                            DirMem.get(i).dato=dato;
                        }
                    }else if((DirMem.get(i+1).espacio<LocEti.get(k).espacio)&&(DirMem.get(i+1).espacio-LocEti.get(k).espacio)<128){
                        //System.out.println(DirMem.get(i).dato+"\t"+DirMem.get(i+1).espacio);
                        //System.out.println(LocEti.get(k).etiqueta+"\t"+LocEti.get(k).espacio);
                        int res=LocEti.get(k).espacio-DirMem.get(i+1).espacio+DirMem.get(i+1).sizeOp;
                        String dato=""+res;
                        if(dato.length()==1){
                            DirMem.get(i).dato="0"+dato;
                        }else{
                            DirMem.get(i).dato=dato;
                        }
                        //System.out.println("2Valor "+res);
                    }else{
                        //Salto relativo lejano
                        error.add("Salto en "+DirMem.get(i+1).espacio+" fuera de rango");
                    }
                }
                
            }
        }
    }
    
}
