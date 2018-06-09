/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Logic;

import org.apache.bcel.classfile.JavaClass;

/**
 *
 * @author dgarcia
 */
public class LinkedClass {
    private int size;
    private ObjectClass head,tail;
    
    /**
     * Constructor de la clase ListClass
     * inicializa los atributos size, head y tail
     */
    public LinkedClass(){
        this.size = 0;
        this.head = this.tail = null;
    }
    
    
    /**
     * Retorna true si la lista esta vacia si no false
     * @return valor booleano a retornar
     */
    public boolean isEmpty(){
        return this.head == null;
    }
    
    /**
     * Añade un nuevo Nodo a la lista con un vertice dentro
     * @param na nombre de la clase a agregar
     * @param c clase a añadir a la lista
     */
    public void add(String na , JavaClass c ,LinkedList l){
        
        if(this.isEmpty()){
            this.head = this.tail = new ObjectClass(na,c,l);
        }else{
            ObjectClass temp = this.head;
            while(temp.getNext()!=null){
                temp = temp.getNext();
            }
            ObjectClass n = new ObjectClass(na,c,l);
            this.tail.setNext(n);
            this.tail = n;
        }
       this.size++;
    }    
    
}
