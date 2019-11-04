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
import CommerceApp.OperationWindow;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.print.*;
import javax.swing.table.TableModel;
import util.FileProcess;
import util.Operation;

public class Pagination implements Printable {
    int [] columns ={10,25,250,280,310,340};
    String [] buttomTitles = {"TOTAL ", "VERSER ", "SOLDE ", "N.SOLDE "};
    int[] pageBreaks;  // array of page break line positions.
    int headerLines = 3;
    int footerLines = 4;
    int pageCount;
    int titleLine;
    HeaderPrint headerPrint;
    /* Synthesise some sample lines of text */
    Object[][] textLines;
    TableModel model;
    private Object[] buttomVariables;
    private Object mode;
    
    public Pagination(TableModel m, int e, int p, HeaderPrint hp, String[] v){
        model = m;
        headerLines = e;
        footerLines = p;
        headerPrint = hp;
        buttomVariables = v;
        mode = v[4];
    }
    
    private void initTextLines(int linesPerPage) {
        if (textLines == null) {
            // the model is bigger then the page length
            if (model.getRowCount() > linesPerPage){
                pageCount = model.getRowCount() /
                                   (linesPerPage - headerLines);
                int textLinesCount = model.getRowCount() + 
                                headerLines * (pageCount + 1) +
                                footerLines + 5;
                textLines = new Object[textLinesCount][columns.length];
                int numBreaks = (textLinesCount-1)/linesPerPage;
                pageBreaks = new int[numBreaks];
                for (int b=0; b<numBreaks; b++) {
                    pageBreaks[b] = (b+1)*linesPerPage; 
                } 
                int lineNumber = 0;
                for (int pageIndex = 0; pageIndex < pageCount + 1; pageIndex++){
                    int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
                    int end   = (pageIndex == pageBreaks.length)    
                             ? textLinesCount - 3 : pageBreaks[pageIndex];                            
                    headerDocument(start);
                    headerTable(headerLines + 1 + start);
                    for (int line=start + headerLines + 3; line<end; line++){
                        fillLine(line-1,lineNumber);
                        lineNumber += 1;
                    }
                }
                buttomTable(textLinesCount - 4 );
                
            }else{
                int numLines = model.getRowCount() + headerLines 
                                + footerLines + 3;
                textLines = new Object[numLines][columns.length];
                headerDocument(0);
                headerTable(headerLines + 1);
                for (int i = 0; i < model.getRowCount(); i++){
                    fillLine(headerLines + 2 + i, i);   
                }
                buttomTable(headerLines + 1 + model.getRowCount());
            }
            printMatrix(textLines);
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
            //case model must be printed in several pages
            int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
            int end   = (pageIndex == pageBreaks.length)
                             ? textLines.length : pageBreaks[pageIndex];
            for (int line=start; line<end; line++) {
                y += lineHeight;
                if (line == headerLines + 1 + start){
                    g.drawLine(0, y + 2, (int)pf.getImageableWidth(), y + 2);
                    g.drawLine(0, y - lineHeight + 2, (int)pf.getImageableWidth(), y - lineHeight + 2);                    
                }
                for (int c = 0; c < columns.length ; c++){
                    if(textLines[line][c] == null){
                        g.drawString("", 0 + columns[c], y);
                    }else{
                        g.drawString(textLines[line][c].toString(), 0 + columns[c], y);
                    }
                }
            }
            if (pageIndex == pageBreaks.length){
                g.drawLine(0, y -(4 *lineHeight) + 2, 
                    (int)pf.getImageableWidth(), y -(4 *lineHeight) + 2);
            }
        }else{
            //case model is printed in one page
            for (int line = 0; line < textLines.length ; line++){
                y += lineHeight;
                if (line == headerLines + 1){
                    g.drawLine(0, y + 2, (int)pf.getImageableWidth(), y + 2);
                    g.drawLine(0, y - lineHeight + 2, (int)pf.getImageableWidth(), y - lineHeight + 2);
                }
                for (int c = 0; c < columns.length ; c++){
                    if(textLines[line][c] == null){
                        g.drawString("", 0 + columns[c], y);
                    }else{
                        g.drawString(textLines[line][c].toString(), 0 + columns[c], y);
                    }
                }
            }
            g.drawLine(0, (model.getRowCount() + headerLines + 2) * lineHeight + 2,
                        (int)pf.getImageableWidth(), 
                        (model.getRowCount() +  headerLines + 2) * lineHeight + 2);
        }
        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
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

    private void headerDocument(int par) {
        textLines[1 + par][0] = headerPrint.getTitleDocument();
        textLines[1 + par][2] = headerPrint.getInfoOperator();
        textLines[2 + par][0] = headerPrint.getOperatorName();
        textLines[3 + par][0] = headerPrint.getDate();
        textLines[3 + par][2] = headerPrint.getTime();
        textLines[3 + par][4] = headerPrint.getPageNumber();
    }

    private void buttomTable(int j) {
    
        textLines[j][1] = "MODE DE PAIMENT : " + mode;
        for (int i = 0; i < 4; i ++){
            textLines[i + j][3] = buttomTitles[i];
            textLines[i + j][5] = buttomVariables[i];
        }
    }
    
    private void printMatrix(Object [][] textLines){
        for(int i = 0; i < textLines.length; i++){
            System.out.print(i + "\t");
            for (int j = 0; j < textLines[i].length; j++){
                System.out.print(textLines[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args){
                java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    int idOperation = 2758;
                    OperationWindow f = new OperationWindow(
                            new JDialog(),
                            Operation.SELL,
                            FileProcess.CONSULT,
                            idOperation);
                    f.print();
                }
        });
    }
}