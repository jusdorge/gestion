/*
 * la licence de ce projet est accorder 
 * a l'entreprise bbs benhaddou brother's software
 * marque deposer aupr�s des autorit�s responsable * 
 */
package Adapters;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.print.PageFormat;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author DELL
 */
public class PrintingAdapter {
    
    public PrintingAdapter(){
        JDialog f = new JDialog(new JFrame(),true);
        PageFormat pf = new PageFormat();
        int WIDTH = (int)pf.getWidth();
        int HEIGHT = (int)pf.getHeight();
        f.setSize(WIDTH, HEIGHT);
        f.add(new MyPanel());
        f.pack();
        FrameAdapter.centerFrame(f);
        f.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }


        });
    }   
    
    static private void createAndShowGUI() {
        PrintingAdapter f = new PrintingAdapter();
    }
}
class MyPanel extends JPanel {
    int iWidth;
    int width;
    int iHeight;
    int height;
    PageFormat pf = new PageFormat();
    public MyPanel() {        
        
        iWidth = (int)pf.getImageableWidth();
        iHeight = (int)pf.getImageableHeight();
        width = (int)pf.getImageableX();
        height = (int)pf.getImageableY();
        //setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Dimension getPreferredSize() {
        return new Dimension(iWidth,iHeight);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        
        // 1ere ligne de l'entête
        Font font = new Font("sans Serif",Font.BOLD,14);
        g.setFont(font);
        g.drawString("BON VENTE N°45676" ,0,20);
        Font font1 = new Font("Ariel",Font.BOLD,12);
        g.setFont(font1);
        g.drawString(".ANONYME", 0, 40);
        g.drawString("11/02/2019",0,60);
        g.drawString("11:15:50",150,60);
        g.drawString("-------------------------------------------------------------------------------------------------------------------------", 0, 70);
        g.drawString("N° !", 0, 80);
        g.drawString("Designation", 25, 80);
        g.drawString("! Qte !", 250, 80);
        g.drawString("Qte.U !", 290, 80);
        g.drawString("Prix.U !", 350,80);
        g.drawString("Montant", 420, 80);
        g.drawString("-------------------------------------------------------------------------------------------------------------------------", 0, 87);
    }  
}