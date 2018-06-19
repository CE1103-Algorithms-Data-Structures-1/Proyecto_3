/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Statics.Rank;
import Structures_Interface.RankList;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private JList refList;
    private RankList depends;
    private RankList refers;
    private DefaultListModel dep;
    private DefaultListModel ref;
    public Statics(String title, Image icon, Gestor gestor)
    {
        this.icon= icon;
        this.title= title;
        this.gestor= gestor;
        this.dep = new DefaultListModel();
        this.ref= new DefaultListModel();
        this.depList= new JList(dep);
        this.refList= new JList(ref);
        this.depends= new RankList();
        this.refers= new RankList();
        this.Init();
    }
    /**
     * Metodo que inicializa las caracteristicas de la ventana.
     */
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
        //title.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
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
            gestor.showMainS();
        });
        this.add(goBack,BorderLayout.NORTH);
        
        JPanel mostDep= new JPanel();
        mostDep.setBounds(50,100,420,560);
        //mostDep.setBorder(BorderFactory.createMatteBorder(4,4,4,4,new Color(57,61,63)));
        mostDep.setBackground(Color.DARK_GRAY);
        mostDep.setLayout(null);
        this.add(mostDep);
        
        JLabel mostDepL= new JLabel(" Analyzed JARS with the Most Dependencies:");
        mostDepL.setBackground(new Color(0,184,0));
        mostDepL.setFont(mostDepL.getFont().deriveFont(Font.BOLD,18));
        mostDepL.setForeground(new Color(31,31,31));
        mostDepL.setOpaque(true);
        mostDepL.setBounds(0,0,420,60);
        //mostDepL.setBorder(BorderFactory.createMatteBorder(4,4,4,4,new Color(57,61,63)));
        mostDep.add(mostDepL,BorderLayout.LINE_START);
        
        JPanel mostRef= new JPanel();
        mostRef.setBounds(528,100,420,560);
        //mostRef.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        mostRef.setBackground(Color.DARK_GRAY);
        mostRef.setLayout(null);
        this.add(mostRef);
        
        JLabel mostRefL= new JLabel(" Analyzed JARS with the Most References:");
        mostRefL.setBackground(new Color(0,184,0));
        mostRefL.setFont(mostRefL.getFont().deriveFont(Font.BOLD,18));
        mostRefL.setForeground(new Color(31,31,31));
        mostRefL.setOpaque(true);
        mostRefL.setBounds(0,0,420,60);
        //mostRefL.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        mostRef.add(mostRefL,BorderLayout.LINE_START);
        
        
        depList.setBackground(Color.DARK_GRAY);
        depList.setBounds(4,60,412,496);
        depList.setFont(depList.getFont().deriveFont(Font.BOLD,28));
        depList.setForeground(Color.BLACK);
        mostDep.add(depList);
        
        refList.setBackground(Color.DARK_GRAY);
        refList.setBounds(4,60,412,496);
        refList.setFont(depList.getFont().deriveFont(Font.BOLD,28));
        refList.setForeground(Color.BLACK);
        mostRef.add(refList);
        
        
    }
    /**
     * Metodo para generar las listas de ranking.
     * @throws FileNotFoundException 
     */
    public void generate() throws FileNotFoundException
   {
        File file= new File("src\\Resources\\Estadisticas\\Dependencias.txt");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) 
        {
            String linea= input.nextLine();
            String [] array= linea.split("@");
            if(!linea.equals(""))
            {
            Rank elemento= new Rank(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),array[3]);
            depends.add(elemento);
            dep.addElement(elemento.getValue()+". "+elemento.getName()+": "+ elemento.getDep());
            }
        }
        
        File filer= new File("src\\Resources\\Estadisticas\\Referencias.txt");
        Scanner inputr = new Scanner(filer);
        while (inputr.hasNextLine()) 
        {
            String linea= inputr.nextLine();
            String [] array= linea.split("@");
            if(!linea.equals(""))
            {
            Rank elemento= new Rank(Integer.parseInt(array[0]),Integer.parseInt(array[1]),Integer.parseInt(array[2]),array[3]);
            refers.add(elemento);
            ref.addElement(elemento.getValue()+". "+elemento.getName()+": "+ elemento.getRef());
            }
            
        }
        try 
        {
            save();
        } catch (IOException ex) 
        {
            Logger.getLogger(Statics.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
   }
    /**
     * Metodo para reiniciar los datos de las listas de estadistica. 
     */
    public void refresh()
    {
        dep.removeAllElements();
        ref.removeAllElements();
        depends.clear();
        refers.clear();
    }
    /**
     * Metodo para guardar el ranking.
     */
    public void save() throws FileNotFoundException, IOException
    {
        File file= new File("src/Resources/Estadisticas/Dependencias.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        writer.print("");
        writer.close();
        FileOutputStream fos = new FileOutputStream(file);
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	int ind=0;
        while(ind!=depends.getLen())
        {
            bw.write(depends.get(ind));
            bw.newLine();
            ind++;
        }
	bw.close();
        
        file= new File("src/Resources/Estadisticas/Referencias.txt");
        writer = new PrintWriter(new FileWriter(file));
        writer.print("");
        writer.close();
        fos = new FileOutputStream(file);
	bw = new BufferedWriter(new OutputStreamWriter(fos));
	ind=0;
        while(ind!=refers.getLen())
        {
            bw.write(refers.get(ind));
            bw.newLine();
            ind++;
        }
	bw.close();
    }
    
}
