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
    private Canvas canvas;
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
    
    
    
  }
  /**
   * Metodo encargado de dibujar las clases en pantalla.
   */
  public void Generate(ClassList lista)
  {
    this.canvas= new ClassCanvas(lista);
    canvas.setBackground(back);
    canvas.setBounds(0,0,1300,700);
    this.getContentPane().add(canvas);
  }
  public class ClassCanvas extends Canvas
  {
      private ClassList lista;
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
        while(lista.Get(indC)!=null)
        {
            Graphics2D g2d = (Graphics2D) g.create();
            actual=lista.Get(indC);
            while(actual.getDeps().Get(indD)!=null)
            {
                Clase depAct=actual.getDeps().Get(indD);
                g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2d.setColor(Color.BLACK);
                g2d.drawLine(actual.getX()+100,actual.getY()+50,depAct.getX(),depAct.getY());
                indD++;
                //this.repaint();
            }
            g2d.setColor(Color.WHITE);
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.fillRect(actual.getX(),actual.getY(),100,100);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(actual.getX(),actual.getY(),100,100);
            indC++;
            indD=0;
        }
        
      }
  }
}
