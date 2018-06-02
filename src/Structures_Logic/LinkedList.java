/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Logic;



/**
 * Clase Linked List para almacenamiento de datos
 * @author dgarcia
 */
public class LinkedList {
    private int size; // largo de la lista
    private Node head,tail; // cabeza de la lista
    /**
     * Constructor de la clase 
     */
    public LinkedList(){
        this.size = 0; // iniciando largo en 0
        this.head = this.tail = null; // iniciando la cabeza en nulo
    }
    /**
     * Retorna la cabeza de la lista enlazada
     * @return 
     */
    public Node getHead(){
        return this.head;
    }
    /**
     * Retorna true si la lista esta vacia en otro caso false
     * @return valor booleano
     */
    public boolean isEmpty(){
        return this.head == null;
    }
    /**
     * Retorna el largo de la lista
     * @return atributo size
     */
    public int getSize(){
        return this.size;
    }
    /**
     * Añade un nuevo Nodo a la lista con un vertice dentro
     * @param v vertice a añadir a la lista
     */
    public void add(Vertex v){
        if(this.isEmpty()){
            this.head = this.tail = new Node(v);
        }else{
            Node temp = this.head;
            while(temp.getNext()!=null){
                temp = temp.getNext();
            }
            Node n = new Node(v);
            this.tail.setNext(n);
            this.tail = n;
        }
       this.size++;
    }
    public Vertex findVertex(String ver){
        if(this.isEmpty()){
            return null;
        }else{
            Node temp = this.head;
            while(temp!=null){
                if(temp.getVertex().getID().equals(ver)){
                    return temp.getVertex();
                }
                temp = temp.getNext();
            }
        }
        return null;
    }
    /**
     * 
     */
    public void showList(){
       if(isEmpty()){
           System.out.println("[]");
       }else{
           System.out.print("[");
           Node temp = this.head;
           while(temp.getNext() != null){
               System.out.print(" "+temp.getVertex().getID()+", ");
               temp = temp.getNext();
           }System.out.print(" "+temp.getVertex().getID()+" ]");
           System.out.println("");
       }
    }
}
