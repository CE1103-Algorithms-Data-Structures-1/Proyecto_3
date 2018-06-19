/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Logic;

import java.util.ArrayList;
import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

/**
 *
 * @author dgarcia
 */
public class ObjectClass {
    
    private final ArrayList<String> methods;
    private final ArrayList<String> atributes;
    private final JavaClass valClass;
    private final String name;
    private ObjectClass next;
    private final LinkedList listofRef;
    private LinkedList listofDep;
    
    
    /**
     * Constructor de la clase ObjectClass
     * asigna los valores iniciales a los
     * atributos de la clase
     * @param n
     * @param c 
     * @param l 
     */
    public ObjectClass(String n , JavaClass c , LinkedList l){
        this.valClass = c;
        this.name = n;
        this.methods = new ArrayList<>();
        this.atributes = new ArrayList<>();
        this.listofRef = l;
        this.listofDep = null;
        Method []mt = c.getMethods();
        Attribute[] at = c.getAttributes();
        
        for (Method mt1 : mt) {
            methods.add(mt1.getName());
        }
        for (Attribute at1 : at) {
            atributes.add(at1.toString());
        }
        
        
        
        
    }
    /**
     * Añade una LinkedList al atributo listofDep 
     * @param l LinkedList a añadir
     */
    public void setListOfDep(LinkedList l){
        if(this.listofDep == null){
            
            this.listofDep = l;
        }
        
    }
    
    /**
     * Retorna el la clase que almacena el nodo
     * @return clase a retornar
     */
    public JavaClass getValClass(){
        return this.valClass;
    }
    /**
     * Retorna el nombre de la clase que almacena
     * @return String con el nombre
     */
    public String getName() {
        return name;
    }
    /**
     * Retorna una lista enlazada con los vertices a los que referencia la clase
     * @return 
     */
    public LinkedList getListOfRef(){
        return this.listofRef;
    }
    /**
     * Retorna el arrayList con los metodos de la clase 
     * @return arraylist con los metodos de la clase
     */
    public ArrayList getMethods(){
        return this.methods;
    }
    /**
     * Retorna el arrayList con los atributos de la clase 
     * @return arraylist con los atributos de la clase
     */
    public ArrayList getAtributes(){
        return this.atributes;
    }
    /**
     * Conecta el nodo con otro
     * @param n nodo a conectar
     */
    public void setNext(ObjectClass n){
        this.next = n;
    }
    /**
     * Obtiene el nodo que le sigue a este
     * @return atributo next
     */
    public ObjectClass getNext(){
        return this.next;
    }
    /**
     * Retorna la lista de metodos pero en una cadena de strings separados por
     * un "@"
     * @return String con todos los nombres 
     */
    public String getMethodsToString(){
        String result = "";
        for(int i = 0 ; i < this.methods.size() ;i++){
            result += this.methods.indexOf(i)+"@";
        }
        return result;
    } 
    /**
     * Retorna un String con todos los nombres de atributos de la clase ]
     * los nombres separados  con "@"
     * @return String con los nombres de los atributos
     */
    public String getAtributesToString(){
        String result = "";
        for(int i = 0 ; i < this.atributes.size() ;i++){
            result += this.atributes.indexOf(i)+"@";
        }
        return result;
    }

}
