
package Statics;

/**
 *Clase encargada de instanciar el objeto estadistica para cada archivo.
 * @author Daniel Camacho
 */
public class Rank 
{
    private int dependencias;
    private int referencias;
    private int posicion;
    private String name;
    /**
     * Constructor de la clase Rank
     * @param dep numero de dependencias del archivo.
     * @param ref numero de referencias del archivo.
     * @param pos posicion en el ranking.
     */
    public Rank(int dep, int ref,int pos,String nam)
    {
        this.dependencias=dep;
        this.referencias=ref;
        this.posicion=pos;
        this.name=nam;
    }
    /**
     * Metodo que retorna el numero de referncias.
     * @return Entero referencias.
     */
    public int getRef()
    {
        return this.referencias;
    }
    /**
     * Metodo que retorna el numero de dependencias.
     * @return Entero dependencias.
     */
    public int getDep()
    {
        return this.dependencias;
    }
    /**
     * Metodo que cambia la cantidad de referencias de un archivo.
     * @param ref Nuevo numero de referencias 
     */
    public void chnRef(int ref)
    {
        this.referencias=ref;
    }
    /**
     * Metodo que cambia la cantidad de dependencias de un archivo.
     * @param dep Nuevo numero de dependencias.
     */
    public void chnDep(int dep)
    {
        this.dependencias=dep;
    }
    /**
     * Metodo que retorna la posicion en el ranking del archivo.
     * @return Entero posicion.
     */
    public int getRank()
    {
        return this.posicion;
    }
    /**
     * Metodo que modifica la posicion en el ranking del archivo.
     * @param pos 
     */
    public void chnRank(int pos)
    {
        this.posicion= pos;
    }
    /**
     * Metodo para obtener el nombre del elemento en el ranking.
     * @return String nombre.
     */
    public String getName()
    {
        return this.name;
    }
}
