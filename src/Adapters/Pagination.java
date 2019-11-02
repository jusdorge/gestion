/*
 * la licence de ce projet est accorder 
 * a l'entreprise bbs benhaddou brother's software
 * marque deposer aupr�s des autorit�s responsable * 
 */
package Adapters;

/**
 *
 * @author DELL
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import javax.swing.table.TableModel;

public class Pagination implements Printable {
    int [] columns ={10,25,250,280,310,340};
    String [] buttomTitles = {"TOTAL ", "VERSER ", "SOLDE ", "N.SOLDE "};
    int[] pageBreaks;  // array of page break line positions.
    int entete = 3;
    int pied = 4;
    int nbrPage;
    int titleLine;
    HeaderPrint headerPrint;
    /* Synthesise some sample lines of text */
    Object[][] textLines;
    TableModel model;
    private Object[] buttomVariables;
    private Object mode;
    
    public Pagination(TableModel m, int e, int p, HeaderPrint hp, String[] v){
        model = m;
        entete = e;
        pied = p;
        headerPrint = hp;
        buttomVariables = v;
        mode = v[4];
    }
    
    private void initTextLines(int ppb) {
        if (textLines == null) {

            if (model.getRowCount() > ppb){
                nbrPage = (int)(model.getRowCount() - entete + pied) /
                                   (ppb - entete);
                int numLines = model.getRowCount() + 
                                entete * (nbrPage - 1) +
                                pied ;
                textLines = new Object[numLines][columns.length];
                headerDocument();
                headerTable(entete+1);
                int ii = 0;
                int p = 1;
                for (int i=0;i<numLines;i++) {
                    if (((i > 2) || ((i % ppb) > 2)) && ( p < nbrPage)){
                        fillLine(i,ii);
                        ii += 1;
                    }else if (p == nbrPage){
                        //remplir l'entete
                    }
                    if (i %  ppb  == 0){
                        p += 1;
                    }
                }
            }else{
                int numLines = model.getRowCount() + entete + pied + 3;
                textLines = new Object[numLines][columns.length];
                headerDocument();
                headerTable(entete + 1);
                for (int i = 0; i < model.getRowCount(); i++){
                    fillLine(entete + 2 + i, i);   
                }
                buttomTable(entete + 2 + model.getRowCount());
            }
        }
    }

    public int print(Graphics g, PageFormat pf, int pageIndex)
             throws PrinterException {

        Font font = new Font("Serif", Font.PLAIN, 8);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        
        int lineHeight = metrics.getHeight();
        int linesPerPage = (int)(pf.getImageableHeight()/lineHeight);
        if (pageBreaks == null) {
            
            initTextLines(linesPerPage);
            int numBreaks = (textLines.length-1)/linesPerPage;
            pageBreaks = new int[numBreaks];
            for (int b=0; b<numBreaks; b++) {
                pageBreaks[b] = (b+1)*linesPerPage; 
            }
        }

        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         * Since we are drawing text we
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Draw each line that is on this page.
         * Increment 'y' position by lineHeight for each line.
         */
        int y = 0; 
        if (linesPerPage < model.getRowCount()){
            int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
            int end   = (pageIndex == pageBreaks.length)
                             ? textLines.length : pageBreaks[pageIndex];
            for (int line=start; line<end; line++) {
                y += lineHeight;
                for (int c = 0; c < columns.length ; c++){
                    g.drawString(textLines[line][c].toString(), 0 + columns[c], y);
                }
            }
        }else{
            for (int i = 0; i < textLines.length ;i++){
                y += lineHeight;
                if (i == entete + 1){
                    g.drawLine(0, y + 2, (int)pf.getImageableWidth(), y + 2);
                    g.drawLine(0, y - lineHeight + 2, (int)pf.getImageableWidth(), y - lineHeight + 2);
                }
                for (int c = 0; c < columns.length ; c++){
                    if(textLines[i][c] == null){
                        g.drawString("", 0 + columns[c], y);
                    }else{
                        g.drawString(textLines[i][c].toString(), 0 + columns[c], y);
                    }
                }
            }
            g.drawLine(0, (model.getRowCount() + entete + 2) * lineHeight + 2,
                        (int)pf.getImageableWidth(), 
                        (model.getRowCount() +  entete + 2) * lineHeight + 2);
        }
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

   

    public static void main(String args[]) {

    }

    private void headerTable(int i) {
        //remplir la l'entete du tableau
        textLines [i][0] = "N°";
        textLines [i][1] = "| DESIGNATION";
        textLines [i][2] = "| QTE";
        textLines [i][3] = "| QTE_U";
        textLines [i][4] = "| PRIX";
        textLines [i][5] = "| MONT";
    }

    private void fillLine(int i, int ii) {
        textLines[i][0] = ii + 1;
        textLines[i][1] = "| " + model.getValueAt(ii, 0);
        textLines[i][2] = "| " + model.getValueAt(ii, 1);
        textLines[i][3] = "| " + model.getValueAt(ii, 2);
        textLines[i][4] = "| " + model.getValueAt(ii, 3);
        textLines[i][5] = "| " + model.getValueAt(ii, 4);
    }

    private void headerDocument() {
        textLines[1][0] = headerPrint.getTitleDocument();
        textLines[1][2] = headerPrint.getInfoOperator();
        textLines[2][0] = headerPrint.getOperatorName();
        textLines[3][0] = headerPrint.getDate();
        textLines[3][2] = headerPrint.getTime();
        textLines[3][4] = headerPrint.getPageNumber();
    }

    private void buttomTable(int j) {
    
        textLines[j][1] = "MODE DE PAIMENT : " + mode;
        for (int i = 0; i < 4; i ++){
            textLines[i + j][3] = buttomTitles[i];
            textLines[i + j][5] = buttomVariables[i];
        }
    }
}