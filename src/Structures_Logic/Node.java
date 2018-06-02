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
public class Node {
    private Node next;
    private Vertex ver;
    
    public Node(Vertex v){
        this.ver = v;
        this.next = null;
    }
    /**
     * Retorna el nodo siguiente
     * @return retorna el atributo next
     */
    public Node getNext(){
        return this.next;
    }
    /**
     * Asigna valor al atributo next del nodo
     * @param n nodo a asignar
     */
    public void setNext(Node n){
        this.next = n;
    }
    /**
     * Retorna el vertice que se encuentra dentro del nodo
     * @return retorna el atributo ver
     */
    public Vertex getVertex(){
        return this.ver;
    }
    
}
