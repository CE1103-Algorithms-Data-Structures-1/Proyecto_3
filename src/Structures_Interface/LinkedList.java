
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
            System.out.println("Posicion: "+temp.getValue().getRank());
            temp=temp.getNext();
        }
        System.out.println(this.len);
    }
    /**
     * Metodo quer borra la lista actual.
     */
    public void clear()
    {
        this.head=null;
        this.len=0;
    }
    /**
     * Metodo para obtener un nodo en una posicion especifica.
     * @param pos Entero posicion
     * @return String atributos
     */
    public String get(int pos)
    {
        int ind=0;
        if(pos<len)
        {
            Node temp=this.head;
            while(pos!=ind)
            {
                temp=temp.getNext();
                ind++;
            }
            return temp.getValue().getDep()+"@"+temp.getValue().getRef()+"@"+temp.getValue().getRank()+"@"+temp.getValue().getName();
        }
        return null;
    }
    /**
     * Metodo para obtener el tamano de la lista en cuestion.
     * @return Entero longitud
     */
    public int getLen()
    {
        return this.len;
    }
    /**
     * Metodo para agregar un uevo eleemento en la posicion que le corresponde.
     * @param rnk eleemento a insertar
     */
    public void addInorderDep(Rank rnk)
    {
        Node temp= this.head;
        Boolean found= false;
        while(temp.getNext()!=null)
        {
            if(!found)
            {
                if(temp.getValue().getDep()<rnk.getDep())
                {
                    if(this.head==temp)
                    {
                        Node nuevo= new Node(rnk);
                        this.head=nuevo;
                        nuevo.setNext(temp);
                        nuevo.getValue().chnRank(1);
                        found=true;
                        len++;
                    }
                }
                else if(temp.getNext().getValue().getDep()<rnk.getDep())
                {
                    Node nuevo= new Node(rnk);
                    nuevo.setNext(temp.getNext());
                    temp.setNext(nuevo);
                    nuevo.getValue().chnRank(temp.getValue().getRank()+1);
                    temp=temp.getNext().getNext();
                    found=true;
                    len++;
                }
                else
                {
                    temp=temp.getNext();
                }
            }
            else
            {
                if(temp.getNext().getNext()==null)
                {
                    temp.getValue().chnRank(temp.getValue().getRank()+1);
                    temp.getNext().getValue().chnRank(temp.getValue().getRank()+1);
                    temp=temp.getNext();
                }
                else
                {
                    temp.getValue().chnRank(temp.getValue().getRank()+1);
                    temp=temp.getNext();
                }
            }
        }
        if(!found)
        {
            rnk.chnRank(temp.getValue().getRank()+1);
            temp.setNext(new Node(rnk));
            len++;
            
        }
    }
    /**
     * Metodo para insertar un nuevo elemento por orden de referencias.
     * @param rnk 
     */
    public void addInorderRef(Rank rnk)
    {
        Node temp= this.head;
        Boolean found= false;
        while(temp.getNext()!=null)
        {
            if(!found)
            {
                if(temp.getValue().getRef()<rnk.getRef())
                {
                    if(this.head==temp)
                    {
                        Node nuevo= new Node(rnk);
                        this.head=nuevo;
                        nuevo.setNext(temp);
                        nuevo.getValue().chnRank(1);
                        found=true;
                        len++;
                    }
                }
                else if(temp.getNext().getValue().getRef()<rnk.getRef())
                {
                    Node nuevo= new Node(rnk);
                    nuevo.setNext(temp.getNext());
                    temp.setNext(nuevo);
                    nuevo.getValue().chnRank(temp.getValue().getRank()+1);
                    temp=temp.getNext().getNext();
                    found=true;
                    len++;
                }
                else
                {
                    temp=temp.getNext();
                }
            }
            else
            {
                if(temp.getNext().getNext()==null)
                {
                    temp.getValue().chnRank(temp.getValue().getRank()+1);
                    temp.getNext().getValue().chnRank(temp.getValue().getRank()+1);
                    temp=temp.getNext();
                }
                else
                {
                    temp.getValue().chnRank(temp.getValue().getRank()+1);
                    temp=temp.getNext();
                }
            }
        }
        if(!found)
        {
            rnk.chnRank(temp.getValue().getRank()+1);
            temp.setNext(new Node(rnk));
            len++;
            
        }
    }
}
