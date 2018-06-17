/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Interface;

import Grafico.Clase;

/**
 *Lista enlazada simple 
 * @author Daniel Camacho
 */
public class ClassList 
{
    private int len;
    private ClassNode Head;
    
    public ClassList()
    {
        this.Head=null;
        this.len=0;
    }
    /**
     * Metodo encargado de anadir elementos a la lista.
     */
    public void Add(Clase clase)
    {
        if(isEmpty())
        {
            this.Head=new ClassNode(clase);
            len++;
        }
        else
        {
            ClassNode temp=this.Head;
            while(temp.getNext()!=null)
            {
                temp=temp.getNext();
            }
            temp.setNext(new ClassNode(clase));
            len++;
        }
    }
    /**
     * Metodo para saber si la lista esta vacia;
     * @return Valor booleano
     */
    public Boolean isEmpty()
    {
       if (len==0)
       {
           return true;
       }
       return false;
    }
    /**
     * Metodo para vaciar la lista.
     */
    public void clear()
    {
        this.Head=null;
        this.len=0;
    }
    /**
     * Metodo para visualizar la clase en consola.
     */
    public void printAll()
    {
        ClassNode temp= this.Head;
        while(temp!=null)
        {
            System.out.println(temp.getValue().getName());
            temp=temp.getNext();
        }
    }/**
     * Metodo para obtener la longitud de la lista.
     * @return int Longitud
     */
    public int getLen()
    {
        return this.len;
    }
    /**
     * Metodo para obtener un elemento de la lista en una posicion en especifico de la lista.
     * @param ind
     * @return Elemento buscado.
     */
    public Clase Get(int ind)
    {
        if(ind>=this.len)
        {
            return null;
        }
        ClassNode temp= this.Head;
        int i=0;
        while(i!=ind)
        {
            temp=temp.getNext();
            i++;
        }
        return temp.getValue();
    }
}
