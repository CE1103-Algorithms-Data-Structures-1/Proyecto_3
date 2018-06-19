/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Grafico.Clase;
import Structures_Interface.ClassList;
import java.awt.*;
import javax.swing.*;

/**
 * Clase encargada de instanciar el Display donde se muestra el diagrama.
 * @author Daniel Camacho 
 */
public class Display extends JFrame
{
    private final Gestor gestor;
    private final String title;
    private final Image icon;
    private JComponent canvas;
    private final Color back;
    
    
  public Display(String title,Image icon,Gestor gest)
  {
      this.gestor= gest;
      this.title=title;
      this.icon=icon;
      this.back= new Color(75,81,93);
      this.Init();
  }
  /**
   * Metodo para inicializar la interfaz del Display 
   */
  public void Init()
  {
    this.setTitle(this.title);
    this.setSize(1300,700);
    this.setIconImage(icon);
    this.setResizable(false);
    this.setUndecorated(true);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    
    JPanel Bar= new JPanel();
        Bar.setSize(new Dimension(1300,25));
        Bar.setBackground(new Color(57,61,63));
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
        
        JButton goBack= new JButton();
        goBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Icons/arrow.png"))));
        goBack.setBounds(0,0,24,24);
        goBack.setFocusPainted(false);
        goBack.setBackground(new Color(57,61,63));
        goBack.setBorder(null);
        goBack.addActionListener(e->{
            
            this.dispose();
            gestor.showMain();
        });
        Bar.add(goBack,BorderLayout.NORTH);
    
    
  }
  /**
   * Metodo encargado de dibujar las clases en pantalla.
   */
  public void Generate(ClassList lista)
  {
    this.canvas= new ClassCanvas(lista);
    canvas.setBounds(0,0,1300,700);
    this.getContentPane().add(canvas);
  }
  public class ClassCanvas extends JComponent
  {
      private ClassList lista;
      private Graphics graphics;
      ClassCanvas(ClassList lista)
      {
        this.lista=lista;
      }
      @Override 
      public void paint(Graphics g)
      {
        int indC=0;
        int indD=0;
        Clase actual=null;
        this.graphics=g;
        canvas.setBackground(back);
        while(lista.Get(indC)!=null)
        {
            Graphics2D g2d = (Graphics2D) g.create();
            actual=lista.Get(indC);
            while(actual.getDeps().Get(indD)!=null)
            {
                Clase depAct=actual.getDeps().Get(indD);
                g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2d.setColor(new Color(235,60,1));
                g2d.drawLine(actual.getX()+actual.getXRcoord(),actual.getY()+15,depAct.getX(),depAct.getY());
                indD++;
                //this.repaint();
            }
            JLabel label= new JLabel();
            label.setText(actual.getName());
            label.setFont(label.getFont().deriveFont(Font.ITALIC,12));
            label.setForeground(Color.BLACK);
            label.setBounds(actual.getX(),actual.getY(),actual.getXRcoord(),30);
            label.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
            label.setBackground(Color.GREEN);
            label.setOpaque(true);
            canvas.add(label);
            label.repaint();
            indC++;
            indD=0;
        }
        
      }
      @Override
      public void setBackground(Color c)
      {
          Graphics2D g2d = (Graphics2D) graphics.create();
          g2d.setColor(c);
          g2d.fillRect(0,0,1300,700);
      }
  }
}
