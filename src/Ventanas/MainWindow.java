/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Ventana principal de la aplicacion.
 * @author Daniel Camacho
 */
public class MainWindow extends JFrame
{
    private String title;
    private Image icon;
    private Color backColor;
    private Color borderColor;
    private Font font;
    private Image returt;
    private JFileChooser fileChooser;
    private File lastFile;
    private Gestor gestor;
    public MainWindow(String title,Image icono,Gestor gestor)
    {
        this.title=title;
        this.icon=icono;
        this.backColor= new Color(31,31,31);
        this.borderColor= new Color(57,61,63);
        this.font= font;
        this.returt=Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Icons/beet.png"));
        this.gestor= gestor;
        this.fileChooser= new JFileChooser();
        FileNameExtensionFilter JARfilter = new FileNameExtensionFilter("Jar Files","jar");
        FileNameExtensionFilter JAVAfilter = new FileNameExtensionFilter("Java Files","java");
        fileChooser.setFileFilter(JARfilter);
        fileChooser.addChoosableFileFilter(JAVAfilter);
        fileChooser.setAcceptAllFileFilterUsed(false);
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
        Add.setFont(Add.getFont().deriveFont(font.BOLD,28));
        Add.setForeground(Color.BLACK);
        Add.setBounds(0,100,300,65);
        Add.setFocusPainted(false);
        Add.addActionListener(e->{
        
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
            {
                lastFile = fileChooser.getSelectedFile();
                System.out.println(lastFile.getName());
                System.out.println(lastFile);

            }
        });
        Panel1.add(Add);
        
        JButton Stats= new JButton("JAR Statistics");
        Stats.setBackground(new Color(0,184,0));
        Stats.setFont(Stats.getFont().deriveFont(font.BOLD,28));
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
        Generate.setBackground(new Color(0,184,0));
        Generate.setFont(Generate.getFont().deriveFont(font.BOLD,28));
        Generate.setForeground(Color.BLACK);
        Generate.setBounds(0,360,300,65);
        Generate.setBorder(BorderFactory.createMatteBorder(4,4,4,0,backColor));
        Generate.setFocusPainted(false);
        Generate.addActionListener(e->{
                Generate.setBackground(new Color(235,60,1));
                Generate.setForeground(Color.BLACK);
                Generate.setText("Generating...");
                //Generate.setBorder(null);
                Generate.setEnabled(false);
            
        });
        Panel1.add(Generate);
        
        JLabel returtL= new JLabel();
        returtL.setIcon(new ImageIcon(this.returt));
        returtL.setBounds(10,610,100,100);
        Panel1.add(returtL);
        
        JLabel Copyright= new JLabel("® 2018 Powered by Returt Technologies");
        Copyright.setFont(Copyright.getFont().deriveFont(Font.ITALIC,12));
        Copyright.setForeground(Color.BLACK);
        Copyright.setBounds(60,620,1000,100);
        Panel1.add(Copyright);
    }
  
}
