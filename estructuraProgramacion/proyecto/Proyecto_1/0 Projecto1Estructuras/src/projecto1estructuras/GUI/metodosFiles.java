
package projecto1estructuras.GUI;
import java.io.*;
/**
 *
 * @author murryFly
 */
 
public class metodosFiles {
    FileInputStream entrada;
    FileOutputStream salida;
    File archivo;
    
    
    public String openFile(File arch){
        String texto="";
        try{
            entrada = new FileInputStream(arch);
            int tx;
            while((tx = entrada.read())!= -1){
                char car = (char)tx;
                texto += car;
            }   
        }
        catch(Exception xx){ }
        
    return texto;    
    }
    
    public String createFile(File arch){
        String isOkay = null;
        try{
            salida = new FileOutputStream(arch);
            isOkay = "ARCHIVO GUARDADO";
        }
        catch(Exception xx){
        }
    
    return isOkay;
    }
}
