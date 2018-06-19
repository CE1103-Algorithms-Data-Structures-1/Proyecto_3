/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Interface;

import Grafico.Clase;
import Statics.Rank;

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
    /**
     * Metodo para saber si un elemento con un nombre en especifico está en la lista.
     * @param name String nombre
     * @return 
     */
    public Boolean inList(String name)
    {
        ClassNode temp=this.Head;
        while(temp!=null)
        {
            if(temp.getValue().getName().equals(name))
            {
                return true;
            }
            temp=temp.getNext();
        }
        return false;
    }
    /**
     * Metodo para obtener coordenadas por nombre de clase.
     * @param name String nombre de la clase.
     * @param type String tipo de la coordenada.
     * @return int coordenada.
     */
    public int getCoordByName(String name,String type)
    {
        if(type.equals("x"))
        {
            ClassNode temp=this.Head;
            while(temp!=null)
            {
                if(temp.getValue().getName().equals(name))
                {
                    return temp.getValue().getX();
                }
                temp=temp.getNext();
            }
        }
        else if(type.equals("y"))
        {
            ClassNode temp=this.Head;
            while(temp!=null)
            {
                if(temp.getValue().getName().equals(name))
                {
                    return temp.getValue().getY();
                }
                temp=temp.getNext();
            }
            
        }
        return 0;
    }
    public void printDepsCoords()
    {
        ClassNode temp= this.Head;
        while(temp!=null)
        {
            System.out.println("-------------------------------------------------------");
            System.out.println(temp.getValue().getName());
            System.out.println("Coordenadas: ("+temp.getValue().getX()+","+temp.getValue().getY()+")");
            System.out.println("");
            System.out.println("Dependencias: ");
            ClassList depList=temp.getValue().getDeps();
            int ind=0;
            while(depList.Get(ind)!=null)
            {
                Clase temp2=depList.Get(ind);
                System.out.println(temp2.getName());
                System.out.println("Coordenadas: ("+temp2.getX()+","+temp2.getY()+")");
                System.out.println("");
                ind++;
            }
            temp=temp.getNext();
            System.out.println("-------------------------------------------------------");
        }
    }
    public RankList ConvertToRankList()
    {
         RankList rank= new RankList();
         ClassNode temp=this.Head;
         while(temp!=null)
         {
             Rank rnk= new Rank(temp.getValue().getDepNumb(),temp.getValue().getRefNumb(),temp.getValue().getName());
             rank.add(rnk);
             temp=temp.getNext();
         }
         return rank;
    }
}
