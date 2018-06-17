/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import Structures_Interface.ClassList;

/**
 *Clase encargada de lamacensar los datos de cada clase en el jar
 * @author Daniel Camacho
 */
public class Clase 
{
    private final String name;
    private final int Xcoord;
    private final int Ycoord;
    private int depNum;
    private int refNum;
    private final int GrdEnt;
    private final int GrdSal;
    private final ClassList deps;
    private final ClassList refs;
    
    public Clase(ClassList ref, ClassList dep,String name, int x, int y,int ent,int sal)
    {
        this.deps=dep;
        this.refs=ref;
        try
        { 
            this.depNum=dep.getLen();
            this.refNum=ref.getLen();
        }
        catch(Exception e)
        {
            this.depNum=0;
            this.refNum=0;
        }
        this.name=name;
        this.Xcoord=x;
        this.Ycoord=y;
        this.GrdEnt=ent;
        this.GrdSal=sal;
    }
    /**
     * Metodo para obtener el nombre de la clase.
     * @return String nombre
     */
    public String getName()
    {
        return this.name;
    }
    /**
     * Metodo para obtener el numero de dependencias
     * @return int Dependencias
     */
    public int getDepNumb()
    {
        return this.depNum;
    }
    public ClassList getDeps()
    {
        return this.deps;
    }
    /**
     * Metodo para obtener el numero de referencias
     * @return int Referencias
     */
    public int getRefs()
    {
        return this.refNum;
    }
    /**
     * Metodo para obtener las coordenadas en x de la clase.
     * @return int x
     */
    public int getX()
    {
        return this.Xcoord;
    }
    /**
     * Metodo para obtener las coordenadas en y de la clase.
     * @return int x
     */
    public int getY()
    {
        return this.Ycoord;
    }
    /**
     * Metodo para obtener el grado entrante de la clase.
     * @return int grado entrante;
     */
    public int grdEnt()
    {
        return this.GrdEnt;
    }
    /**
     * Metodo para obtener el grado saliente de la clase.
     * @return 
     */
    public int grdSal()
    {
        return this.GrdSal;
    }
}
