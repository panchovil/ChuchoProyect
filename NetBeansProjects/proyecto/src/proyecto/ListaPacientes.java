/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author panchovil
 */
public abstract class ListaPacientes {
    private static Paciente[] pacientes = new Paciente[0];
    
    private static String archivo = "pacientes.db";
    private static FileInputStream fis = null;
    private static ObjectInputStream ois = null;
    private static FileOutputStream fos = null;
    private static ObjectOutputStream oos = null;
    
    public static void addPaciente(Paciente p){
        Paciente[] pacientesAux = new Paciente[pacientes.length+1];
        for(int i=0; i<pacientes.length; i++){
                if(pacientes[i].getDni()==p.getDni()){
                    System.out.println("MISMO DNI");
                    break;    
                }
                else{
                pacientesAux[i]=pacientes[i];       
 

                    System.out.println("TODO JOIA ");
                }
        }
        pacientesAux[pacientes.length]=p;
        pacientes = pacientesAux;
    }
    
    public static void addTurnoPaciente(int posicion,String turno){
        pacientes[posicion].setTurno(turno);
    }
    
    public static void addHistorialPaciente(int posicion,String historial){
        pacientes[posicion].setHistorial(historial);
    }
    
    public static void addRecetaPaciente(int posicion,String receta){
        pacientes[posicion].setReceta(receta);
    }
    
    public static Paciente[] getPacientes(){
        return pacientes;
    }
    public static boolean saveLista() {

        boolean saved = false;
        
        try {
            fos = new FileOutputStream(archivo);
            oos = new ObjectOutputStream(fos);
            //Esta es otra forma de hacer un for, la sintaxis es la siguiente:
            //for (Clase aux:array), recorre el array y se lo asigna al auxiliar.
            oos.writeObject(pacientes);

        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo");
            return false;
        } catch (IOException ex) {
            System.out.println("Error al guardar el archivo");
            return false;
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                    oos = null;
                }
                if (fos != null) {
                    fos.close();
                    fos = null;
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo");
            }
        }        

        System.out.println("Guardado Correctamente");
        return true;
    }

    /*
     * Devuelve un arraylist con las ventas en disco
     */
    public static boolean levantarObjetoEntero() {
        Paciente[] aux=null;

        try {
            fis = new FileInputStream(archivo);
            ois = new ObjectInputStream(fis);

            aux = (Paciente[]) ois.readObject();
            System.out.println(aux.length);

        }  catch (Exception e1) {
            System.out.println("Error!!!" + e1);
            return false;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                    ois = null;
                }
                if (fis != null) {
                    fis.close();
                    fis = null;
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo");
            }
        }
        pacientes = aux;
        return true;
    }
    
}
