/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Logic;

import java.io.File;

/**
 * Clase Graph es la clase en donde se agreganlos vertices para 
 * la creacion en si del grafo
 * @author dgarcia
 */
public class Graph {
    
    private int size; //numero de vertices en el grafo
    private final LinkedList lisfOFVertex; //lista de vertices en el grafo

    /**
     * Constructor de la clase Graph
     */
    public Graph(){
        
        this.lisfOFVertex = new LinkedList(); // se inicia la lista de vertices
        this.size = 0; // se inicia el numero de vertices en 0
    }
    /**
     * Inicializa el grafo con el Vertice inicial
     * @param name id del vertice a agregar
     */
    public void init(String name){
        this.insert(name);
        
        File[] array;
        File f = new File(name);
        
        array = f.listFiles();
        if(array!=null){
        for(int i = 0;i < array.length; i++){
            this.insert(array[i].getName(),this.lisfOFVertex.getHead().getVertex().getID());
        }}else{
            System.out.println("el archivo no posee contenido");
        }
        this.showGraph();        
    }
    private void insert(String name){
        insert(name,"");
    }
    /**
     * INSERT
     * metodo que inserta un nuevo vertices al grafo
     * @param name nombre del nuevo nodo
     * @param ref  nombre del nodo a referenciar
     */
    public void insert(String name , String ref){
        this.add(name , ref );
    }
    /**
     * Imprime los nodos del grafo y sus referencias
     */
    public void showGraph(){
        this.see();
    }
    /**
     * Retorna la lista enlazada de vertices del grafo
     * @return atributo listOFVertex
     */
    public LinkedList getListOFVertex(){
        return this.lisfOFVertex;
    }
    
    
    
    /**
     * INSERT metodo privado
     * @param name 
     * @param ref 
     */
    private void add(String name , String ref){
        Vertex v = new Vertex(name);
        if(ref.equals("")){
                    this.lisfOFVertex.add(v);
        }else{
                v.getListofRef().add(this.lisfOFVertex.findVertex(ref));
                this.lisfOFVertex.add(v);
        }
        this.size++;
    }
    /**
     * Imprimir el grafo
     */
    private void see(){
        if(this.lisfOFVertex.isEmpty()){
            System.out.println("[]");
        }else{
            Node temp = this.lisfOFVertex.getHead();
            
            while(temp!=null){
                
                System.out.println("Vertex: "+temp.getVertex().getID());
                System.out.print("List of ref:  ");
                temp.getVertex().getListofRef().showList();
                System.out.println("");
                temp = temp.getNext();
                
            } 
            System.out.println("\nTerminado");
        }
    }
    
    

    
}
