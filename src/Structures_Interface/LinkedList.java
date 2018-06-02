
package Structures_Interface;

import Statics.Rank;

/**
 *Clase encargada de instanciar listas enlazadas.
 * @author Daniel Camacho 
 */
public class LinkedList 
{
    private Node head;
    private int len;
    
    /**
     * Constructor de la clase LinkedList
     */
    public LinkedList()
    {
        this.head=null;
        this.len=0;
    }
    /**
     * Metodo para anadir elementos a la lista. 
     * @param rnk valor del nuevo nodo.
     */
    public void add(Rank rnk)
    {
      if(isEmpty())
      {
          this.head=new Node(rnk);
          this.len++;
      } 
      else
      {
       Node temp= this.head;
       while(temp.getNext()!=null)
       {
           temp=temp.getNext();
       }
       temp.setNext(new Node(rnk));
       this.len++;
      }
    }
    /**
     * Metodo para saber si la lista esta vacia.
     * @return Booleano condicion.
     */
    public Boolean isEmpty()
    {
        if(this.len==0)
        {
            return true;
        }
        return false;
    }
    /**
     * Metodo para imprimir toda la lista.
     */
    public void printAll()
    {
        Node temp= this.head;
        while(temp!=null)
        {
            System.out.println(temp.getValue().getName());
            temp=temp.getNext();
        }
        System.out.println(this.len);
    }
}
