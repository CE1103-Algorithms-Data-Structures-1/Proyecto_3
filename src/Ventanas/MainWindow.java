/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Grafico.Clase;
import Structures_Interface.ClassList;
import Structures_Logic.Graph;
import Structures_Logic.LinkedClass;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.bcel.classfile.ClassFormatException;

/**
 * Ventana principal de la aplicacion.
 * @author Daniel Camacho
 */
public class MainWindow extends JFrame
{
    private final String title;
    private final Image icon;
    private final Color backColor;
    private final Color borderColor;
    private final Image returt;
    private final JFileChooser fileChooser;
    private final JLayeredPane mainPanel;
    private final JPanel statusBar;
    private final JLabel fileName;
    private final JLabel Status;
    private final JLabel Completeness;
    private final JButton Complete;
    private final JButton classTab;
    private final JButton jarTab;
    private final JButton allTab;
    private final JButton missingTab;
    private File lastFile;
    private final Gestor gestor;
    private DefaultListModel classList;
    private DefaultListModel jarList;
    private DefaultListModel allFileList;
    private DefaultListModel missingList;
    private JList mainList;
    private JScrollPane scrollPane;
    private Graph grafo;
    
    public MainWindow(String title,Image icono,Gestor gestor)
    {
        this.title=title;
        this.icon=icono;
        this.backColor= new Color(31,31,31);
        this.borderColor= new Color(57,61,63);
        this.returt=Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Icons/beet.png"));
        this.gestor= gestor;
        this.fileChooser= new JFileChooser();
        FileNameExtensionFilter JARfilter = new FileNameExtensionFilter("Jar Files","jar");
        FileNameExtensionFilter JAVAfilter = new FileNameExtensionFilter("Java Files","java");
        fileChooser.setFileFilter(JARfilter);
        fileChooser.addChoosableFileFilter(JAVAfilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        statusBar= new JPanel();
        fileName= new JLabel();
        Status= new JLabel();
        classTab= new JButton();
        jarTab= new JButton();
        allTab= new JButton();
        missingTab= new JButton();
        Completeness= new JLabel();
        this.mainList= new JList();
        this.scrollPane = new JScrollPane(mainList);
        this.mainList.setBackground(backColor);
        this.classList= new DefaultListModel();
        this.jarList= new DefaultListModel();
        this.allFileList= new DefaultListModel();
        this.missingList= new DefaultListModel();
        this.Complete= new JButton("Complete using Maven™");
        this.mainPanel= new JLayeredPane();
        Init();   
    }
    /**
     * Metodo encargado de inicializar los componentes de la ventana.
     */
    public void Init()
    {
        //Caracteristicas de la ventana principal.
        this.setTitle(this.title);
        this.setSize(1300,700);
        this.setIconImage(icon);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(backColor);
        this.setLayout(null);
        
        
        mainPanel.setBackground(backColor);
        mainPanel.setBounds(300,53,1000,646);
        mainPanel.setOpaque(true);
        mainPanel.setLayout(null);
        mainList.setLayoutOrientation(JList.VERTICAL);
        this.scrollPane.setBounds(0,0,1000,646);
        this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setBorder(null);
        mainPanel.add(scrollPane,1,0);
        this.add(mainPanel);
        
        
        JPanel Bar= new JPanel();
        Bar.setSize(new Dimension(1300,25));
        Bar.setBackground(borderColor);
        Bar.setLocation(0,0);
        Bar.setLayout(null);
        this.add(Bar);
        
        JButton exit= new JButton();
        exit.setBackground(Color.RED);
        exit.setForeground(Color.WHITE);
        exit.setBorder(null);
        exit.setText("X");
        exit.setBounds(1270,0,30,25);
        exit.setFocusPainted(false);
        exit.addActionListener(e->{
                System.exit(0);
            
        });
        Bar.add(exit);
        
        JButton minimize= new JButton();
        minimize.setBackground(Color.GRAY);
        minimize.setForeground(Color.BLACK);
        minimize.setBorder(null);
        minimize.setText("---");
        minimize.setBounds(1240,0,30,25);
        minimize.setFocusPainted(false);
        minimize.addActionListener(e -> {
            setState(Frame.ICONIFIED);
        });
        Bar.add(minimize);
        
        JPanel Panel1= new JPanel();
        Panel1.setSize(new Dimension(300,700));
        Panel1.setBackground(Color.DARK_GRAY);
        Panel1.setLayout(null);
        this.add(Panel1,BorderLayout.WEST);
        
        JButton Add= new JButton("Add JAR file");
        Add.setBackground(new Color(0,184,0));
        Add.setBorder(BorderFactory.createMatteBorder(4,4,4,0,backColor));
        Add.setFont(Add.getFont().deriveFont(Font.BOLD,28));
        Add.setForeground(Color.BLACK);
        Add.setBounds(0,100,300,65);
        Add.setFocusPainted(false);
        Add.addActionListener(e->{
        
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
            {
                lastFile = fileChooser.getSelectedFile();
                File []array= lastFile.listFiles();
                this.grafo= new Graph();
                try 
                {
                    grafo.init(lastFile.toString(),lastFile.getName());
                } catch (IOException ex) 
                {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassFormatException ex) 
                {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) 
                {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.refresh();
                
                System.out.println(lastFile.getName());
                System.out.println(lastFile);

            }
        });
        Panel1.add(Add);
        
        JButton Stats= new JButton("JAR Statistics");
        Stats.setBackground(new Color(0,184,0));
        Stats.setFont(Stats.getFont().deriveFont(Font.BOLD,28));
        Stats.setForeground(Color.BLACK);
        Stats.setBounds(0,165+65,300,65);
        Stats.setBorder(BorderFactory.createMatteBorder(4,4,4,0,backColor));
        Stats.setFocusPainted(false);
        Stats.addActionListener(e->{
            dispose();
            gestor.showStatics();
        });
        Panel1.add(Stats);
        
        JButton Generate= new JButton("Generate!");
        Generate.setBackground(Color.DARK_GRAY);
        Generate.setFont(Generate.getFont().deriveFont(Font.BOLD,28));
        Generate.setForeground(Color.BLACK);
        Generate.setBounds(0,360,300,65);
        Generate.setBorder(BorderFactory.createMatteBorder(4,4,4,0,backColor));
        Generate.setFocusPainted(false);
        Generate.setEnabled(true);
        Generate.addActionListener(e->{
                Generate.setBackground(new Color(235,60,1));
                Generate.setForeground(Color.BLACK);
                Generate.setText("Generating...");
                
                ClassList a=new ClassList();
                
                a.Add(new Clase(null,null,"Prueba 3",10,600,10,10));
                a.Add(new Clase(null,null,"Prueba 3",10,600,10,10));
                
                ClassList b=new ClassList();
                
                b.Add(new Clase(null,null,"Prueba 3",100,150,300,30));
                b.Add(new Clase(null,null,"Prueba 3",500,550,300,230));
                
                ClassList c=new ClassList();
                
                c.Add(new Clase(null,null,"Prueba 3",400,250,10,10));
                c.Add(new Clase(null,null,"Prueba 3",100,150,10,10));
                
                ClassList d=new ClassList();
                
                d.Add(new Clase(null,null,"Prueba 3",400,250,300,230));
                d.Add(new Clase(null,null,"Prueba 3",500,550,300,230));
                
                ClassList lista= new ClassList();
                
                lista.Add(new Clase(a,b,"Prueba 1",400,200,10,10));
                lista.Add(new Clase(c,d,"Prueba 2",100,100,10,10));
                lista.Add(new Clase(d,c,"Prueba 2",500,500,10,10));
                gestor.Generate(lista);
                Generate.setEnabled(false);
            
        });
        Panel1.add(Generate);
        
        
        Complete.setBackground(Color.DARK_GRAY);
        Complete.setFont(Complete.getFont().deriveFont(Font.BOLD,20));
        Complete.setForeground(Color.BLACK);
        Complete.setBounds(0,490,300,65);
        Complete.setBorder(BorderFactory.createMatteBorder(4,4,4,0,backColor));
        Complete.setFocusPainted(false);
        Complete.setEnabled(false);
        Complete.addActionListener(e->{
                
                
            
        });
        Panel1.add(Complete);
        
        JLabel returtL= new JLabel();
        returtL.setIcon(new ImageIcon(this.returt));
        returtL.setBounds(10,610,100,100);
        Panel1.add(returtL);
        
        JLabel Copyright= new JLabel("® 2018 Powered by Returt Technologies");
        Copyright.setFont(Copyright.getFont().deriveFont(Font.ITALIC,12));
        Copyright.setForeground(Color.BLACK);
        Copyright.setBounds(60,620,1000,100);
        Panel1.add(Copyright);
        
        
        statusBar.setBounds(300,25,1000,28);
        statusBar.setBackground(new Color(0,184,0));
        this.add(statusBar);
        
        
        fileName.setText("NO FILE SELECTED");
        fileName.setBounds(0,0,800,28);
        fileName.setFont(fileName.getFont().deriveFont(Font.BOLD,15));
        statusBar.add(fileName);
        
        Status.setFont(Status.getFont().deriveFont(Font.BOLD,15));
       
        Completeness.setFont(Completeness.getFont().deriveFont(Font.BOLD,15));
        
        classTab.addActionListener(e->{
                classTab.setBackground(new Color(235,60,1));
                classTab.setForeground(Color.BLACK);
                classTab.setEnabled(false);
                
                jarTab.setEnabled(true);
                jarTab.setBackground(new Color(0,184,0));
                
                allTab.setEnabled(true);
                allTab.setBackground(new Color(0,184,0));
                
                missingTab.setEnabled(true);
                missingTab.setBackground(new Color(0,184,0));
                
                fileName.setText(lastFile.getName()+"/Classes");
                mainList.setModel(classList);
        });
        
        jarTab.addActionListener(e->{
                jarTab.setBackground(new Color(235,60,1));
                jarTab.setForeground(Color.BLACK);
                jarTab.setEnabled(false);
                
                classTab.setEnabled(true);
                classTab.setBackground(new Color(0,184,0));
                
                allTab.setEnabled(true);
                allTab.setBackground(new Color(0,184,0));
                
                missingTab.setEnabled(true);
                missingTab.setBackground(new Color(0,184,0));
                
                fileName.setText(lastFile.getName()+"/Jars");
                mainList.setModel(jarList);
        });
        allTab.addActionListener(e->{
                allTab.setBackground(new Color(235,60,1));
                allTab.setForeground(Color.BLACK);
                allTab.setEnabled(false);
                
                jarTab.setEnabled(true);
                jarTab.setBackground(new Color(0,184,0));
                
                classTab.setEnabled(true);
                classTab.setBackground(new Color(0,184,0));
                
                missingTab.setEnabled(true);
                missingTab.setBackground(new Color(0,184,0));
                
                fileName.setText(lastFile.getName()+"/All Files");
                mainList.setModel(allFileList);
            
        });
        
        missingTab.addActionListener(e->{
                missingTab.setBackground(new Color(235,60,1));
                missingTab.setForeground(Color.BLACK);
                missingTab.setEnabled(false);
                
                jarTab.setEnabled(true);
                jarTab.setBackground(new Color(0,184,0));
                
                classTab.setEnabled(true);
                classTab.setBackground(new Color(0,184,0));
                
                allTab.setEnabled(true);
                allTab.setBackground(new Color(0,184,0));
                
                fileName.setText(lastFile.getName()+"/Missing dependencies");
                mainList.setModel(missingList);
                
            
        });
        
    
        
        
    }
    /**
     * Metodo para actualizar la pantalla.
     */
    public void refresh()
    {
        clean();
        statusBar.setLayout(null);
        statusBar.add(Status);
        statusBar.add(Completeness);
        
        fileName.setText(lastFile.getName()+"/Classes");
        fileName.setBounds(0,0,800,28);
        
        Completeness.setBounds(880,0,147,28);
        Completeness.setText("UNKNOWN");
        
        Status.setBounds(800,0,53,28);
        Status.setText("Status: ");
        
        this.mainPanel.add(classTab,2,0);
        classTab.setBounds(755,620,85,26);
        classTab.setBackground(new Color(235,60,1));
        classTab.setBorder(BorderFactory.createMatteBorder(2,2,2,2,borderColor));
        classTab.setText("Classes");
        classTab.setFocusPainted(false);
        classTab.setForeground(Color.BLACK);
        
        this.mainPanel.add(jarTab,2,0);
        jarTab.setBounds(835,620,85,26);
        jarTab.setBackground(new Color(0,184,0));
        jarTab.setBorder(BorderFactory.createMatteBorder(2,2,0,2,borderColor));
        jarTab.setText("Jars");
        jarTab.setFocusPainted(false);
        jarTab.setForeground(Color.BLACK);
       
        this.mainPanel.add(allTab,2,0);
        allTab.setBounds(915,620,85,26);
        allTab.setBackground(new Color(0,184,0));
        allTab.setBorder(BorderFactory.createMatteBorder(2,2,0,2,borderColor));
        allTab.setText("All Files");
        allTab.setFocusPainted(false);
        allTab.setForeground(Color.BLACK);
        
        this.mainPanel.add(missingTab,2,0);
        missingTab.setBounds(625,620,135,26);
        missingTab.setBackground(new Color(0,184,0));
        missingTab.setBorder(BorderFactory.createMatteBorder(2,2,0,2,borderColor));
        missingTab.setText("Missing Dependencies");
        missingTab.setFocusPainted(false);
        
        missingTab.setForeground(Color.BLACK);
        
        mainList.setBackground(backColor);
        mainList.setFont(mainList.getFont().deriveFont(Font.PLAIN,20));
        mainList.setForeground(Color.WHITE);
        mainList.setBounds(0,0,1000,646);
        
        //Complete.setBackground(new Color(84,19,136));
        
        classList.addElement(" -----------------------------------------------------------------Classes----------------------------------------------------------------");
        setList(grafo.getALLclases(),classList,"classes");
        
        jarList.addElement(" -----------------------------------------------------------------Jars--------------------------------------------------------------------");
        setList(grafo.getAllJars(),jarList,"jar");
        
        allFileList.addElement(" ----------------------------------------------------------------All Files-----------------------------------------------------------------");
        allFileList.addElement(" >>>CLASSES");
        setList(grafo.getALLclases(),allFileList,"all");
        allFileList.addElement(" >>>JARS");
        setList(grafo.getAllJars(),allFileList,"all");

        
        missingList.addElement(" --------------------------------------------------------Missing Dependencies-------------------------------------------------------");
        setList("",missingList,"miss");
        
        mainList.setModel(classList);
        
    }
    /**
     * Metodo para inicializar las listas 
     * @param lista Lista de elementos
     * @param model Lista recorrible
     */
    public void setList(String lista,DefaultListModel model,String id)
    {
        if(lista!="")
        {
            String[] clases= lista.split("@");
            for(int i=0;i<clases.length;i++)
            {
                model.addElement(clases[i]);
            }
        }
        else
        {
            if(id.equals("jar"))
            {
                model.addElement(">>NO JARS FOUND");
            }
            else if(id.equals("classes"))
            {
                 model.addElement(">>NO CLASSES FOUND");
            }
            else if(id.equals("all"))
            {
                 model.addElement(">>NO FILES FOUND");
            }
            else if(id.equals("miss"))
            {
                model.addElement(">>ONLY MY WILL TO LIVE IS MISSING :)");
            }
        }
    }
    public void clean()
    {
        classList.clear();
        jarList.clear();
        missingList.clear();
        allFileList.clear();
    }
  
}
