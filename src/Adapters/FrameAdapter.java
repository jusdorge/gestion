/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapters;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author DELL
 */
public class FrameAdapter {
    private static Toolkit kit = Toolkit.getDefaultToolkit();
    private static Dimension screenSize = kit.getScreenSize();
    private static int screenWidth = screenSize.width;
    private static int screenHeight = screenSize.height;
    static public void centerFrame(JFrame fr){
        int frameWidth = fr.getWidth();
	int frameHeight = fr.getHeight();
	int frameX = (screenWidth - frameWidth) / 2;
	int frameY = (screenHeight - frameHeight) / 2;
        fr.setLocation(frameX, frameY);
    }
    
    static public void centerFrame(JDialog dialog){
        int frameWidth = dialog.getPreferredSize().width;
	int frameHeight = dialog.getPreferredSize().height;
	int frameX = (screenWidth - frameWidth) / 2;
	int frameY = (screenHeight - frameHeight) / 2;
        dialog.setLocation(frameX, frameY);
    }
    
    static public void setPercentInsets(JFrame fr, double percent){
        double per = (2 * percent / 100);
        double percentWidth = per * screenWidth;
        double percentHeight = per * screenHeight;
        int width = screenWidth - (int)percentWidth;
        int height = screenHeight - (int)percentHeight;
        fr.setSize(width, height);
        fr.setLocation((int)(percentWidth / 2),(int)(percentHeight / 2));
    }
}
