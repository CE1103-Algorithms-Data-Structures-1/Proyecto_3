/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures_Logic;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import org.apache.bcel.classfile.ClassFormatException;

/**
 * Clase Graph es la clase en donde se agregan los vertices para
 * la creacion del grafo
 * @author dgarcia
 */

public class Graph {
    
    private int size; //numero de vertices en el grafo
    private final LinkedList lisfOFVertex; //lista de vertices en el grafo
    private LinkedClass graphListOfClass;  // lista de clases con referencias
    private final List jars; //lista de jars
    private final List clases; // lista de clases
    private ZipFile zipFile; // archivo del que sse sacan los jars y clases
    /**
     * Constructor de la clase Graph
     */
    public Graph(){
        
        this.lisfOFVertex = new LinkedList(); // se inicia la lista de vertices
        this.graphListOfClass = new LinkedClass(); // iniciando lista de clases
        this.size = 0; // se inicia el numero de vertices en 0
        jars = new ArrayList<>();
        clases =  new ArrayList<>();
        this.zipFile = null;
    }
    /**
     * Inicializa el grafo utilizando un archivo de extencion jar
     *
     * @param path, ruta del archivo
     * @param name, id del vertice a agregar
     * @throws java.io.IOException 
     * @throws java.lang.ClassNotFoundException 
     */
    public void init(String path ,String name) 
            throws IOException, ClassFormatException, ClassNotFoundException{
        
        
        File firstJar = new File(path);
        this.insert(firstJar.getName());
        zipFile = new ZipFile(path);
        ZipInputStream stream = new ZipInputStream(new BufferedInputStream(new FileInputStream(path)));
        ZipEntry entry;
        
        
        JarFile f = new JarFile(path);
        ReferenceFinder rf = new ReferenceFinder();
        rf.findReferences(f.getName(), f);               
        this.setListClass(rf.getLinkedClass());     
        
        
        //Inicializando listas de dependencias de cada clase
        ObjectClass tempClass = this.graphListOfClass.getHead();
        while(tempClass != null){
            
            tempClass.setListOfDep(this.generateListofDep(tempClass.getName()));
//            tempClass.getListOfDep().setSaliente(size);
            tempClass = tempClass.getNext();
        }
        
        while((entry = stream.getNextEntry())!=null){

            
            if(entry.getName().endsWith(".jar")){
                
                this.insert(entry.getName(), this.lisfOFVertex.getHead().getVertex().getID());
                
                jars.add(entry.getName());
                
                
            }else if(entry.getName().endsWith(".class")){
                    
                    clases.add(entry.getName());
            }
          
        }
        System.out.println("");
        this.showGraph();        
    }
    /**
     * Da valor al atributo graphListOfClass añadiendole una nueva lista 
     * @param l LinkedClass a añadir al parametro
     */
    public void setListClass(LinkedClass l){
        this.graphListOfClass = l;
    }
    /**
     * Metodo para obtener la lista de clases del grafo.
     * @return 
     */
    public LinkedClass getListClass()
    {
        return this.graphListOfClass;
    }
    /**
     * Insert con un parametro agrega el verticeinicial
     * @param name 
     */
    
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
     * 
     * Obtiene todos los nombres de los jars que se encuentren dentro del actual
     * @return string con los nombres separados por un "@"
     */
    public String getAllJars()
    {
        String result = "";
        for(int i = 0 ; i<jars.size();i++){
            result += jars.get(i)+"@";
            
        }
        return result;
    }
    /**
     * Obtiene todos los nombres de las clases que se encuentren dentro del jar
     * actual
     * @return string con los nombres separados por un "@"
     */
    public String getALLclases(){
        String result = "";
        for(int i = 0 ; i<clases.size();i++){
            result += clases.get(i)+"@";
            
        }
        return result;
    }
    
    public Graph makeSubGraph(String name) 
            throws IOException, ClassFormatException, ClassNotFoundException{
        
        return this.createNewGraph(name);
    }
    
    /**
     * Retorna un string separado con "@" con todos los nombres de clases dentro
     * de todos los archivos jar
     * @return r
     */
//    public String findAllClases(){
//        String r = "";
//        Node temp = this.lisfOFVertex.getHead();
//        
//        while(temp != null ){
//            
//            if(!temp.getVertex().getClassList().isEmpty()){
//                
//                ObjectClass obj = temp.getVertex().getClassList().getHead();
//                while(obj != null){
//                    r+=obj.getName()+"@";
//                    obj = obj.getNext();
//                }
//                temp = temp.getNext();
//            }
//        }
//        
//        return r;
//    }
    /**
     * Retorna un string separado con "@" con todos los nombres de los jars junto
     * con todos los nombre de las clases dentro de ellos
     * @return r
     */
//    public String findAllFiles(){
//        String r = "";
//        Node temp = this.lisfOFVertex.getHead();
//        while(temp != null ){
//            r += temp.getVertex().getID()+"@";
//            if(!temp.getVertex().getClassList().isEmpty()){
//                ObjectClass obj = temp.getVertex().getClassList().getHead();
//                while(obj != null){
//                    r+=obj.getName()+"@";
//                    obj = obj.getNext();
//                }
//                temp = temp.getNext();
//            }
//        }        
//        return r;
//    }
    
    
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
    
    private Graph createNewGraph(String n) 
            throws IOException,ClassFormatException, ClassNotFoundException{
        
        
        String newPath = this.zipFile.getClass().getClassLoader().getResource(n).toExternalForm();
        Graph g = new Graph();
        g.init(newPath, n);
        
        return g;
    }
    
    /**
     * Crea una LinkedList de vertices que contienen los nombres de las clases
     * que poseen este vertice en su lista de referencia 
     * @param id nombre del vertice a buscar en las listas de las demmás clases
     * @return Lista enlazada
     */
    private LinkedList generateListofDep(String id){
        
        LinkedList listDep = new LinkedList();
        
        ObjectClass temp = this.graphListOfClass.getHead();
        
        while(temp != null){
            if(temp.getListOfRef().findVertex(id) != null){
                
                Vertex v = new Vertex(temp.getName());
                listDep.add(v);
                
            } 
            temp = temp.getNext();
        }
        
        return listDep;
    }
    
    private Long[][] makeAdjacencyMatrix(){
        Long[][] adjMat = new Long[this.graphListOfClass.getSize()][this.graphListOfClass.getSize()]; 
        return adjMat;
    }

    
}
