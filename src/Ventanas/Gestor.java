/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calse encargada de instanciar el gestor de ventanas.
 * @author Daniel Camacho
 */
public class Gestor 
{
    private MainWindow main;
    private String title;
    private Image icon;

    public Gestor()
    {
        this.title="JAR Analyzer";
        this.icon=Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Resources/Icons/jar.png"));
        this.main= new MainWindow(title,icon);
    }
    public void showMain()
    {
        this.main.setVisible(true);
    }
}
