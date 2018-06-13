/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Logic;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;
import org.apache.bcel.classfile.ClassFormatException;

/**
 * Clase Graph es la clase en donde se agreganlos vertices para 
 * la creacion en si del grafo
 * @author dgarcia
 */
public class Graph {
    
    private int size; //numero de vertices en el grafo
    private final LinkedList lisfOFVertex; //lista de vertices en el grafo

    /**
     * Constructor de la clase Graph
     */
    public Graph(){
        
        this.lisfOFVertex = new LinkedList(); // se inicia la lista de vertices
        this.size = 0; // se inicia el numero de vertices en 0
    }
    /**
     * Inicializa el grafo utilizando un archivo de extencion jar
     * @param p ruta del archivo
     * @param name id del vertice a agregar 
     */
    public void init(String p ,String name) throws IOException, ClassFormatException, ClassNotFoundException{
        this.insert(name);
        String path = p;
        File firstJar = new File(path);
        this.insert(firstJar.getName());
        
        File[] array = firstJar.listFiles();   
        
        
        if(array!=null){
            
            if(array[0].getName().toLowerCase().endsWith(".jar")){

                for(int i = 0;i < array.length; i++){
                    this.insert(array[i].getName(),this.lisfOFVertex.getHead().getVertex().getID());
                    this.init(path+"\\"+array[i].getName(),array[i].getName());

                        }            
            }else if(array[0].getName().toLowerCase().endsWith(".class")){
                
                for (File array1 : array) {
                    
                    JarFile f = new JarFile(path+name);
                    ReferenceFinder rf = new ReferenceFinder();
                    rf.findReferences(path+name, f);
                    Vertex newVer = new Vertex(array1.getName());
                    Vertex m = lisfOFVertex.findVertex(array[1].getName());
                    m.setClassList(rf.getLinkedClass());
                    
                }
                   
            }   
        
        }else{
            System.out.println("el archivo no posee contenido");
        }
        
        this.showGraph();        
    }
    
    public void insert(String name){
        insert(name,"");
    }
    /**
     * INSERT
     * metodo que inserta un nuevo vertices al grafo
     * @param name nombre del nuevo nodo
     * @param ref  nombre del nodo a referenciar
     */
    public void insert(String name , String ref){
        this.add(name , ref );
    }
    /**
     * Metodo que agrega el vertice a la lista de vertices , pero estos nodos ya
     * tienen la lista de referencias hecha y esto para luego poder relacionar 
     * todos los nodos
     * @param name 
     */
    public void addTotalVerex(String name){
        Vertex v = new Vertex(name);
        this.lisfOFVertex.add(v);
        
    }
    /**
     * Imprime los nodos del grafo y sus referencias
     */
    public void showGraph(){
        this.see();
    }
    /**
     * Retorna la lista enlazada de vertices del grafo
     * @return atributo listOFVertex
     */
    public LinkedList getListOFVertex(){
        return this.lisfOFVertex;
    }
    /**
     * Retorna el nombre del vertice con el mayor numero de referencias
     * @return 
     */
    public String getMaxofRank(){
        return this.maxOfR();
    }
    
    /**
     * Retorna un string separado por "@" con todos los nombres de los jars que
     * contiene el primer archivo jar incluyendolo a el
     * @return r
     */
    public String findAllJars(){
        String r = "";
        Node temp = this.lisfOFVertex.getHead();
        while(temp!=null){
            r+=temp.getVertex().getID()+"@";
            temp = temp.getNext();
            
        }
        
        return r;
    }
    /**
     * Retorna un string separado con "@" con todos los nombres de clases dentro
     * de todos los archivos jar
     * @return r
     */
    public String findAllClases(){
        String r = "";
        Node temp = this.lisfOFVertex.getHead();
        while(temp != null ){
            
            if(!temp.getVertex().getClassList().isEmpty()){
                ObjectClass obj = temp.getVertex().getClassList().getHead();
                while(obj != null){
                    r+=obj.getName()+"@";
                    obj = obj.getNext();
                }
                temp = temp.getNext();
            }
        }
        
        return r;
    }
    /**
     * Retorna un string separado con "@" con todos los nombres de los jars junto
     * con todos los nombre de las clases dentro de ellos
     * @return r
     */
    public String findAllFiles(){
        String r = "";
        Node temp = this.lisfOFVertex.getHead();
        while(temp != null ){
            r += temp.getVertex().getID()+"@";
            if(!temp.getVertex().getClassList().isEmpty()){
                ObjectClass obj = temp.getVertex().getClassList().getHead();
                while(obj != null){
                    r+=obj.getName()+"@";
                    obj = obj.getNext();
                }
                temp = temp.getNext();
            }
        }        
        return r;
    }
    
    
    ///// Metodos privados //////
    
    
    
    /**
     * INSERT 
     * @param name 
     * @param ref 
     */
    private void add(String name , String ref){
        Vertex v = new Vertex(name);
        if(ref.equals("")){
                    this.lisfOFVertex.add(v);
        }else{
                v.getListofRef().add(this.lisfOFVertex.findVertex(ref));
                this.lisfOFVertex.add(v);
        }
        this.size++;
    }
    
    /**
     * Imprimir el grafo
     */
    private void see(){
        if(this.lisfOFVertex.isEmpty()){
            System.out.println("[]");
        }else{
            Node temp = this.lisfOFVertex.getHead();
            
            while(temp!=null){
                
                System.out.println("Vertex: "+temp.getVertex().getID());
                System.out.print("List of ref:  ");
                temp.getVertex().getListofRef().showList();
                System.out.println("");
                temp = temp.getNext();
                
            } 
            System.out.println("\nTerminado");
        }
    }
    
    /**
     * Retorna el nombre del vertice con mayor numero de referencias
     * @return 
     */
    private String maxOfR(){
        if(this.lisfOFVertex.isEmpty()){
            return "";
        }else{
            
        if(this.lisfOFVertex.getSize() == 1){
            return this.lisfOFVertex.getHead().getVertex().getID();
        }else{
            
        
        int max = this.lisfOFVertex.getHead().getVertex().getListofRef().getSize();
        
        Node n = this.lisfOFVertex.getHead().getNext();
        
        for(int i = 0 ; i < this.lisfOFVertex.getSize()-1; i++){
            if(max <= n.getVertex().getListofRef().getSize() ){
                max = n.getVertex().getListofRef().getSize();
            }
            n = n.getNext();
        }
        n = this.lisfOFVertex.getHead();
        for(int j = 0 ; j<this.lisfOFVertex.getSize() ; j++){
                    if(n.getVertex().getListofRef().getSize() == max){
                        return n.getVertex().getID();
                    }
                    n = n.getNext();
                    
            
                }
            }
        }
        return "";
    }
    
    

    
}
