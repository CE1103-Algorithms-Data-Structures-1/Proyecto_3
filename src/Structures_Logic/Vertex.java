/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Logic;

/**
 *
 * @author dgarcia
 */

public class Vertex{
        
    private final String name; //nombre que identifica al nodo
    private LinkedList refList; //Lista de referencias a otros nodos
    private LinkedClass classList; //Lista de clases dentro del jar
        
    public Vertex(String n){
            
        this.refList = new LinkedList(); //se crea la lista de referencias
        this.name = n; // se asigna el id del nodo 
            
    }
        
    /**
     * Retorna la lista de referencias del vertice
     * @return 
     */
    public LinkedList getListofRef(){
            return this.refList;
    }
    /**
     * Agrega una lista completa de referencias al vertice
     * @param l 
     */
    public void setListifRef(LinkedList l){
        this.refList = l;
    }
    
     /**
      * Retorna el atributo name del vertice
      * @return atributo name
      */    
    public String getID(){
        return this.name;
    }
    /**
     * Retorna una lista enlazada con los vertices de clases y estos vertices
     * poseen una lista de referencias a otras clases , además de los métodos
     * y atributos de la clase 
     * @return 
     */
    public LinkedClass getClassList(){
        return this.classList;
    }
    /**
     * Añade una lista de clases al atributo classList
     * @param l Lista añadir 
     */
    public void setClassList(LinkedClass l){
        this.classList = l;
        
    }
        
        
}
