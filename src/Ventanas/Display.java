/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

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
    private final Canvas canvas;
    private final Color back;
    
  public Display(String title,Image icon,Gestor gest)
  {
      this.gestor= gest;
      this.title=title;
      this.icon=icon;
      this.canvas= new Canvas();
      this.back= new Color(75,81,93);
      this.Init();
  }
  public void Init()
  {
    this.setTitle(this.title);
    this.setSize(1300,700);
    this.setIconImage(icon);
    this.setResizable(false);
    this.setUndecorated(true);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    
    canvas.setBackground(back);
    canvas.setBounds(0,0,1300,700);
    this.getContentPane().add(canvas);
  }
}
