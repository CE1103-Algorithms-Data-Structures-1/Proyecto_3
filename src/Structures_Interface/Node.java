/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Interface;

import Statics.Rank;

/**
 *Clase que instancia objetos nodo;
 * @author Daniel Camacho 
 */
public class Node 
{
    private Rank value;
    private Node next;
    /**
     * Contructor de la clase Node.
     * @param val Objeto estadistica..
     */
    public Node(Rank val)
    {
        this.value=val;
        this.next=null;
    }
    /**
     * Metodo para obtener el siguiente nodo.
     * @return Nodo siguiente.
     */
    public Node getNext()
    {
        return this.next;
    }
    /**
     * Metodo para establecer el valor del siguiente nodo.
     * @param nex Nodo siguiente.
     */
    public void setNext(Node nex)
    {
        this.next=nex;
    }
    /**
     * Metodo para obtener el valor almacenado en el nodo.
     * @return Rank valor.
     */
    public Rank getValue()
    {
        return this.value;
    }
}
