/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Statics.Rank;
import Structures_Interface.LinkedList;
import java.awt.*;
import java.io.BufferedReader;
import java.io.*;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.swing.*;

/**
 * Clase encargada de instanciar la ventana de estadisticas de la aplicacion.
 * @author Daniel Camacho
 */
public class Statics extends JFrame
{
    private String title;
    private Image icon;
    private Gestor gestor;
    private JList depList;
    private LinkedList depends;
    DefaultListModel dep;
    public Statics(String title, Image icon, Gestor gestor)
    {
        this.icon= icon;
        this.title= title;
        this.gestor= gestor;
        this.dep = new DefaultListModel();
        this.depList= new JList(dep);
        this.depends= new LinkedList();
        this.Init();
    }
    public void Init()
    {
        this.setSize(new Dimension(1000,700));
        this.setTitle(title);
        this.setIconImage(icon);
        this.setUndecorated(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(31,31,31));
        this.setLayout(null);
        
        JLabel title= new JLabel("    JAR Analyzer Statistics");
        title.setFont(title.getFont().deriveFont(Font.BOLD,30));
        title.setBounds(294,30,410,50);
        title.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        title.setForeground(new Color(0,184,0));
        this.add(title,BorderLayout.NORTH);
        
        JButton goBack= new JButton();
        goBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Icons/arrow.png"))));
        goBack.setBounds(0,0,24,24);
        goBack.setFocusPainted(false);
        goBack.setBackground(new Color(31,31,31));
        goBack.setBorder(null);
        goBack.addActionListener(e->{
            
            this.dispose();
            gestor.showMain();
        });
        this.add(goBack,BorderLayout.NORTH);
        
        JPanel mostDep= new JPanel();
        mostDep.setBounds(50,100,420,560);
        mostDep.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        mostDep.setBackground(Color.DARK_GRAY);
        mostDep.setLayout(null);
        this.add(mostDep);
        
        JLabel mostDepL= new JLabel(" Analyzed JARS with the Most Dependencies:");
        mostDepL.setBackground(new Color(0,184,0));
        mostDepL.setFont(mostDepL.getFont().deriveFont(Font.BOLD,18));
        mostDepL.setForeground(new Color(31,31,31));
        mostDepL.setOpaque(true);
        mostDepL.setBounds(0,0,420,60);
        mostDepL.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        mostDep.add(mostDepL,BorderLayout.LINE_START);
        
        JPanel mostRef= new JPanel();
        mostRef.setBounds(528,100,420,560);
        mostRef.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        mostRef.setBackground(Color.DARK_GRAY);
        mostRef.setLayout(null);
        this.add(mostRef);
        
        JLabel mostRefL= new JLabel(" Analyzed JARS with the Most Referenes:");
        mostRefL.setBackground(new Color(0,184,0));
        mostRefL.setFont(mostRefL.getFont().deriveFont(Font.BOLD,18));
        mostRefL.setForeground(new Color(31,31,31));
        mostRefL.setOpaque(true);
        mostRefL.setBounds(0,0,420,60);
        mostRefL.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        mostRef.add(mostRefL,BorderLayout.LINE_START);
        
        
        depList.setBackground(Color.DARK_GRAY);
        depList.setBounds(4,60,412,496);
        depList.setFont(depList.getFont().deriveFont(Font.BOLD,28));
        depList.setForeground(Color.BLACK);
        mostRef.add(depList);
        
        
    }
    /**
     * Metodo para generar las listas de ranking.
     * @throws FileNotFoundException 
     */
    public void generate() throws FileNotFoundException
   {
        File file= new File("src/Resources/Estadisticas/Dependencias.txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) 
        {
            String linea= input.nextLine();
            String [] array= linea.split("@");
            Rank elemento= new Rank(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),array[3]);
            depends.add(elemento);
            dep.addElement(elemento.getRank()+". "+elemento.getName()+": "+ elemento.getDep());
        }
        depends.printAll();
   }
    public void refresh()
    {
        dep.removeAllElements();
    }
}
