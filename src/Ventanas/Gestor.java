/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Structures_Interface.ClassList;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Calse encargada de instanciar el gestor de ventanas.
 * @author Daniel Camacho
 */
public class Gestor 
{
    private MainWindow main;
    private Statics statics;
    private Display display;
    private final String title;
    private final Image icon;

    public Gestor()
    {
        this.title="JAR Analyzer";
        this.icon=Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Icons/jar.png"));
        this.main= new MainWindow(title,icon,this);
        this.statics= new Statics(title,icon,this,false);
        this.display= new Display(title,icon,this);
    }
    public void showMainS()
    {
        this.main.setVisible(true);
    }
    public void showMain()
    {
        this.main.setVisible(true);
        this.main.goingBack();
    }
    public void showStatics(ClassList c,Boolean r)
    {
        this.statics.refresh();
        this.statics.generate(c,r);
        this.statics.setVisible(true);
    }
    public void showDisplay()
    {
        this.display.setVisible(true);
    }
    public void Generate(ClassList lista)
    {
        this.display= new Display(title,icon,this);
        main.dispose();
        this.showDisplay();
        this.display.Generate(lista);
    }
    public void generateDisplay()
    {
        this.display= new Display(title,icon,this);
    }
}
