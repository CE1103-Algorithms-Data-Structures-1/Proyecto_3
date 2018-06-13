/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Structures_Logic.Graph;
import java.io.IOException;
import org.apache.bcel.classfile.ClassFormatException;

/**
 *
 * @author Daniel Camacho
 */
public class Main
{

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassFormatException, ClassNotFoundException 
    {
        Graph g = new Graph();
        g.init("C:\\Users\\Oficina TI - i5\\Desktop\\pruebajar\\bcel-5.2.jar", "bcel-5.2.jar");
        
        
        System.out.println(g.findAllClases());
    }
    
}
