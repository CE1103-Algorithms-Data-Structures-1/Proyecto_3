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
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import org.apache.bcel.classfile.ClassFormatException;

/**
 * Clase Graph es la clase en donde se agreganlos vertices para 
 * la creacion en si del grafo
 * @author dgarcia
 */
public class Graph {
    
    private int size; //numero de vertices en el grafo
    private final LinkedList lisfOFVertex; //lista de vertices en el grafo
    private final List jars;
    private final List clases;
    /**
     * Constructor de la clase Graph
     */
    public Graph(){
        
        this.lisfOFVertex = new LinkedList(); // se inicia la lista de vertices
        this.size = 0; // se inicia el numero de vertices en 0
        jars = new ArrayList<>();
        clases =  new ArrayList<>();
    }
    /**
     * Inicializa el grafo utilizando un archivo de extencion jar
     * @param p ruta del archivo
     * @param name id del vertice a agregar 
     * @throws java.io.IOException 
     * @throws java.lang.ClassNotFoundException 
     */
    public void init(String p ,String name) throws IOException, ClassFormatException, ClassNotFoundException{
        this.insert(name);
        String path = p;
        File firstJar = new File(path);
        this.insert(firstJar.getName());
        
        ZipFile zipFile = new ZipFile(path);
        ZipInputStream stream = new ZipInputStream(new BufferedInputStream(new FileInputStream(path)));
        ZipEntry entry = null;
        
        
        String lastname = "" , fnam = "";
        
        while((entry = stream.getNextEntry())!=null){
            lastname = fnam;
            fnam = entry.getName();
            
            if(entry.getName().endsWith(".jar")){
                
//                this.insert(entry.getName(), this.lisfOFVertex.getHead().getVertex().getID());
//                this.init(path+"\\" + entry.getName(), entry.getName());
                
                jars.add(entry.getName());
                
            }else if(entry.getName().endsWith(".class")){
                

                
//                    JarFile f = new JarFile(path+entry.getName());
//                    ReferenceFinder rf = new ReferenceFinder();
//                    rf.findReferences(entry.getName(), f);               
//                    
//                    Vertex m = lisfOFVertex.findVertex(lastname);
//                    m.setClassList(rf.getLinkedClass());
                    
                    clases.add(entry.getName());
            }
            
//            InputStream input = zipFile.getInputStream(entry);
            
            
            
            
        }
               
        System.out.println("");
        

        
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
    public String getAllJars()
    {
        String result = "";
        for(int i = 0 ; i<jars.size();i++){
            result += jars.get(i)+"@";
            
        }
        return result;
    }
    public String getALLclases(){
        String result = "";
        for(int i = 0 ; i<clases.size();i++){
            result += clases.get(i)+"@";
            
        }
        return result;
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
