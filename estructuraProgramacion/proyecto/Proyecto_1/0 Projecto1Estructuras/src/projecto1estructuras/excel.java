/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecto1estructuras;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
//Importanciones de la Api APACHE POI
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook; 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;


public class excel {
    
    public static void arhivo() throws IOException{
	//Archivo de Nmónicos
	FileInputStream file = new FileInputStream(new File("../0 Projecto1Estructuras\\src\\projecto1estructuras\\resources\\Nmonicos.xls"));
	HSSFWorkbook workbook = new HSSFWorkbook(file);	
	HSSFSheet sheet = workbook.getSheetAt(0);
	
	Iterator<Row> rowIterator = sheet.iterator();
	
	
	Row row;
	
	//Ciclo de la fila
	while (rowIterator.hasNext()){
	    row = rowIterator.next();
	
	
	    Iterator<Cell> cellIterator = row.cellIterator();
	
	    Cell celda;
	
 
            //Ciclo de la celda
	    while (cellIterator.hasNext()){
	
		celda = cellIterator.next();
		
		switch(celda.getCellType()) {
	
		case NUMERIC:
	
		
		    System.out.print(celda.getNumericCellValue());	
		    break;
	
		case STRING:
	
		    System.out.print(celda.getStringCellValue());
	
		    break;
	
		case BOOLEAN:
	
		    System.out.print(celda.getBooleanCellValue());

		    break;
	
		}
                System.out.print("\t");
	
	    }
            System.out.println("");
	
	}
	
 
	
	// cerramos el libro excel
	
	workbook.close();
	
    }
    //Función que detecta si el nmonico existe y el tipo de nmonico
    public  static boolean nmonico(String nmonico, ArrayList <String> directiva)throws IOException{
        FileInputStream file = new FileInputStream(new File("../0 Projecto1Estructuras\\src\\projecto1estructuras\\resources\\Nmonicos.xls"));
	HSSFWorkbook workbook = new HSSFWorkbook(file);	
	HSSFSheet sheet = workbook.getSheetAt(0);
	
	Iterator<Row> ri = sheet.iterator();
	Row row;
        int contador=0;
        while (ri.hasNext()){
            row = ri.next();
            Iterator<Cell> cellIterator = row.cellIterator();
	
	    Cell celda;
	
 
            //Ciclo de la celda
	    while (cellIterator.hasNext()){
	
		celda = cellIterator.next();
		
		switch(celda.getCellType()) {
                    case STRING:
                        //Obtiene si existe el mnemonico en la lista de excel
                        if(celda.getStringCellValue().toUpperCase().equals(nmonico))
                        {
                            //Empieza con -1 por la lectura de la primera celda, celda del mnemonico
                            contador=-1;
                            
                            
                            while(cellIterator.hasNext()&& contador<21){
                                celda = cellIterator.next();
                                    //Switch que lee la columna del mnemonico
                                    switch(celda.getCellType()){
                                            //Caso del OPCODE HEXADECEMAL
                                            case STRING:
                                                //System.out.println(contador+"\t"+celda.getStringCellValue());
                                                
                                                if(!celda.getStringCellValue().contains("--")){
                                                    switch(contador){
                                                    case 0: 
                                                        directiva.add("IMM");
                                                        break;
                                                    case 3:
                                                        directiva.add("DIR");
                                                        break;
                                                    case 6:
                                                        directiva.add("INDX");
                                                        break;
                                                    case 9:
                                                        directiva.add("INDY");
                                                        break;
                                                    case 12:
                                                        directiva.add("EXT");
                                                        break;
                                                    case 15:
                                                        directiva.add("INH");
                                                        break;
                                                    case 18:
                                                        directiva.add("REL");
                                                        break;
                                                    default:
                                                        break;

                                                    }
                                                
                                                }
                                            break;
                                        case NUMERIC:
                                            //Caso de OPCODE en digito
                                                //System.out.println(contador+"\t"+celda.getNumericCellValue());
                                                switch(contador){
                                                    case 0: 
                                                        directiva.add("IMM");
                                                        break;
                                                    case 3:
                                                        directiva.add("DIR");
                                                        break;
                                                    case 6:
                                                        directiva.add("INDX");
                                                        break;
                                                    case 9:
                                                        directiva.add("INDY");
                                                        break;
                                                    case 12:
                                                        directiva.add("EXT");
                                                        break;
                                                    case 15:
                                                        directiva.add("INH");
                                                        break;
                                                    case 18:
                                                        directiva.add("REL");
                                                        break;
                                                    default:
                                                        break;

                                                    }
                                            break;
                                            
                                        default:
                                            break;
                                    }
                                    
                                    contador++;
                                }
                                
                                    
                            
                            workbook.close();
                            return true;
                        }
                        break;

                    default:
                        break;

                }
                
	    }
            
            
        }
        workbook.close();
        return false;
        
    }
	
    
    
    public static void ObtenerOpCode(Linea dato)throws IOException{
        FileInputStream file = new FileInputStream(new File("C:\\Users\\Sergio\\Desktop\\0 Projecto1Estructuras\\src\\projecto1estructuras\\resources\\Nmonicos.xls"));
        
	HSSFWorkbook workbook = new HSSFWorkbook(file);	
	HSSFSheet sheet = workbook.getSheetAt(0);
	
	Iterator<Row> ri = sheet.iterator();
	Row row;
        int caso=0;
        int contador;
        while (ri.hasNext()){
            row = ri.next();
            Iterator<Cell> cellIterator = row.cellIterator();
	
	    Cell celda;
	
 
            //Ciclo de la celda
	    while (cellIterator.hasNext()){
	
		celda = cellIterator.next();
		
		switch(celda.getCellType()) {
                    case STRING:
                        //Obtiene si existe el mnemonico en la lista de excel
                        if(celda.getStringCellValue().toUpperCase().equals(dato.mnemonico))
                        {
                            
                            contador=-1;
                            
                            if(dato.directiva.equals("IMM")){
                                caso=0;
                            }else if(dato.directiva.equals("DIR")){
                                caso=3;
                            }else if(dato.directiva.equals("INDX")){
                                caso=6;
                            }else if(dato.directiva.equals("INDY")){
                                caso=9;
                            }else if(dato.directiva.equals("EXT")){
                                caso=12;
                            }else if(dato.directiva.equals("INH")){
                                caso=15;
                            }else if(dato.directiva.equals("REL")){
                                caso=18;
                            }
                            //System.out.println("\t"+caso+"\t"+celda.getStringCellValue());
                            while(cellIterator.hasNext()&& contador<=(caso+2)){
                                celda = cellIterator.next();
                                    //Switch que lee la columna del mnemonico
                                    if(contador>=caso){
                                        switch(celda.getCellType()){
                                            //Caso del OPCODE HEXADECEMAL
                                            case STRING:
                                                //System.out.println(contador+"\t"+celda.getStringCellValue());
                                                if(contador==caso){
                                                    dato.opcode=celda.getStringCellValue();
                                                }
                                            break;
                                            case NUMERIC:
                                                //System.out.println(contador+"\t"+celda.getNumericCellValue());
                                                
                                                if(contador==caso){
                                                    dato.opcode=""+celda.getNumericCellValue();
                                                }
                                                else if(contador==caso+2){
                                                    dato.TamBite=(int)celda.getNumericCellValue();
                                                }
                                            break;
                                            
                                            default:
                                                break;
                                        }
                                    
                                    
                                    }
                                    
                                    contador++;
                                }
                                
                                    
                            
                            workbook.close();
                            
                        }
                        break;
                    
                        
                    default:
                        break;

                }
                
	    }
            
            
        }
        workbook.close();
    
    
    }
 /*
    switch(contador){
                                        case 1: 
                                            directiva.add("IMM");
                                            break;
                                        case 3:
                                            directiva.add("DIR");
                                            break;
                                        case 6:
                                            directiva.add("INDX");
                                            break;
                                        case 9:
                                            directiva.add("INDY");
                                            break;
                                        case 12:
                                            directiva.add("EXT");
                                            break;
                                        case 15:
                                            directiva.add("INH");
                                            break;
                                        case 18:
                                            directiva.add("REL");
                                            break;
                                        default:
                                            break;
    
    }
    */
	
}
