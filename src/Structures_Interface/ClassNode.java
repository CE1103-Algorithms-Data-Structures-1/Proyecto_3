/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Interface;

import Grafico.Clase;
import Statics.Rank;

/**
 * Nodo para lista enlazada simple
 *
 * @author Daniel Camacho
 */
public class ClassNode 
{
    private ClassNode next;
    private Clase value;
    
    public ClassNode(Clase val)
    {
        this.value=val;
        this.next=null;
    }

    /**
     * Metodo para obtener el siguiente nodo.
     * @return Nodo siguiente.
     */
    public ClassNode getNext()
    {
        return this.next;
    }

    /**
     * Metodo para establecer el valor del siguiente nodo.
     * @param nex Nodo siguiente.
     */
    public void setNext(ClassNode nex)
    {
        this.next=nex;
    }

    /**
     * Metodo para obtener el valor almacenado en el nodo.
     * @return Clase valor.
     */
    public Clase getValue()
    {
        return this.value;
    }
}
