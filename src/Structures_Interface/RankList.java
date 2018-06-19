
package Structures_Interface;

import Statics.Rank;
import javax.swing.DefaultListModel;

/**
 *Clase encargada de instanciar listas enlazadas.
 * @author Daniel Camacho 
 */
public class RankList 
{
    private RankNode head;
    private int len;
    
    /**
     * Constructor de la clase LinkedList
     */
    public RankList()
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
          this.head=new RankNode(rnk);
          this.len++;
      } 
      else
      {
       RankNode temp= this.head;
       while(temp.getNext()!=null)
       {
           temp=temp.getNext();
       }
       temp.setNext(new RankNode(rnk));
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
        RankNode temp= this.head;
        while(temp!=null)
        {
            System.out.println(temp.getValue().getName());
            System.out.println("Dependencias: "+ temp.getValue().getDep());
            System.out.println("Referencias: "+temp.getValue().getRef());
            System.out.println("Posicion: "+temp.getValue().getValue());
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
            RankNode temp=this.head;
            while(pos!=ind)
            {
                temp=temp.getNext();
                ind++;
            }
            return temp.getValue().getDep()+"@"+temp.getValue().getRef()+"@"+temp.getValue().getValue()+"@"+temp.getValue().getName();
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
     * Metodo para ordenar las dependencias.
     */
    public RankList orderDep()
    {
       RankList nueva= new RankList();
       while(this.head!=null)
       {
           nueva.add(getBig("d"));
       }
       return nueva;
    }
    /**
     * Metodo para insertar un nuevo elemento por orden de referencias.
     * @param rnk 
     */
    public RankList orderRef()
    {
       RankList nueva= new RankList();
       while(this.head!=null)
       {
           nueva.add(getBig("r"));
       }
       return nueva;
    }
    public Rank getBig(String t)
    {
        int max=0;
        RankNode temp= this.head;
        while(temp!=null)
        {
            if(t=="d")
            {
                if(temp.getValue().getDep()>max)
                {
                    max=temp.getValue().getDep();
                }
                
            }
            else if(t=="r")
            {
                if(temp.getValue().getRef()>max)
                {
                    max=temp.getValue().getRef();
                }
            }
            temp=temp.getNext();
        }
        temp= this.head;
        Rank found=null;
        while(temp!=null)
        {
            if(t=="d")
            {
                if(this.head.getValue().getDep()==max)
                {
                    found=this.head.getValue();
                    this.head=head.getNext();
                    return found;
                }
                else if(temp.getNext().getValue().getDep()==max)
                {
                    found=temp.getNext().getValue();
                    temp.setNext(temp.getNext().getNext());
                    return found;
                }
            }
            else if(t=="r")
            {
                if(this.head.getValue().getRef()==max)
                {
                    found=this.head.getValue();
                    this.head=head.getNext();
                    return found;
                }
                else if(temp.getNext().getValue().getRef()==max)
                {
                    found=temp.getNext().getValue();
                    temp.setNext(temp.getNext().getNext());
                    return found;
                } 
            }
            temp=temp.getNext();
            
        }
        return found;
    }
    public DefaultListModel toList(String t)
    {
       DefaultListModel l= new DefaultListModel();
       RankNode temp= this.head;
       while(temp!=null)
       {
           if(t=="d")
           {
                l.addElement(temp.getValue().getPosition()+"."+" "+temp.getValue().getName()+": "+temp.getValue().getDep());
           }
           else if(t=="r")
           {
               l.addElement(temp.getValue().getPosition()+"."+" "+temp.getValue().getName()+": "+temp.getValue().getRef());
           }
           temp=temp.getNext();
       }
       return l;
    }
    public void assignPosition()
    {
        int p=1;
        RankNode temp= this.head;
        while(temp!=null)
        {
            temp.getValue().setPosition(p);
            p++;
            temp=temp.getNext();
        }
    }
    
}
