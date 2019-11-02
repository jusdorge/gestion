package CommerceApp;
import Adapters.DateAdapter;
import Adapters.FrameAdapter;
import Adapters.JDBCAdapter;
import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import objects.TableModelList;
import util.Operation;
import util.Utilities;
import java.lang.Integer;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import objects.DateRenderer;
import util.FileProcess;

public class ListOperationFrame extends javax.swing.JDialog {
    static JDialog parentDialog;
    private final String operationTableName;
    
    public ListOperationFrame(JFrame frm,Operation op) {
        super(frm, true);
        initComponents();
        FrameAdapter.centerFrame(this);
        setIconImage(Utilities.setIconImage(this));
        operation = op;
        operationName = op.getFrameTitle();
        operationTableName = op.getTableName();
        operandeName = op.getOperand().getTableName();
        operatorName = op.getOperator().getTableName();
        tableName = op.getTableName();
        columnNames = op.getColumnNames();
        detailColumnNames = op.getDetailColumnNames();
        initLocals();
        parentDialog = this;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu = new javax.swing.JPopupMenu();
        Nouveau = new javax.swing.JMenuItem();
        Modifier = new javax.swing.JMenuItem();
        Supprimer = new javax.swing.JMenuItem();
        consulter = new javax.swing.JMenuItem();
        print = new javax.swing.JMenuItem();
        titre = new javax.swing.JLabel();
        clientComboBox = new javax.swing.JComboBox<>();
        produitComboBox = new javax.swing.JComboBox<>();
        paimentComboBox = new javax.swing.JComboBox<>();
        ordreTriComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
        dateLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        clientLabel = new javax.swing.JLabel();
        produitLabel = new javax.swing.JLabel();
        ordreTriLabel = new javax.swing.JLabel();
        paimentLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        dateDebutChooserCombo = new datechooser.beans.DateChooserCombo();
        dateFinChooserCombo = new datechooser.beans.DateChooserCombo();
        searchButton = new javax.swing.JButton();
        versementTotalLabel = new javax.swing.JLabel();

        Nouveau.setMnemonic('N');
        Nouveau.setText("Nouveau");
        Nouveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NouveauActionPerformed(evt);
            }
        });
        popupMenu.add(Nouveau);

        Modifier.setMnemonic('M');
        Modifier.setText("Modifier");
        Modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModifierActionPerformed(evt);
            }
        });
        Modifier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ModifierKeyPressed(evt);
            }
        });
        popupMenu.add(Modifier);

        Supprimer.setMnemonic('S');
        Supprimer.setText("Supprimer");
        Supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SupprimerActionPerformed(evt);
            }
        });
        popupMenu.add(Supprimer);

        consulter.setMnemonic('C');
        consulter.setText("Consultation");
        consulter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consulterActionPerformed(evt);
            }
        });
        popupMenu.add(consulter);

        print.setText("Imprimer");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });
        popupMenu.add(print);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(1000, 700));
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        titre.setBackground(new java.awt.Color(0, 51, 255));
        titre.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        titre.setForeground(new java.awt.Color(255, 255, 153));
        titre.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titre.setText("   Liste des ");
        titre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        titre.setName("titre"); // NOI18N
        titre.setOpaque(true);

        clientComboBox.setEditable(true);
        clientComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        clientComboBox.setMinimumSize(new java.awt.Dimension(120, 30));
        clientComboBox.setPreferredSize(new java.awt.Dimension(120, 30));

        produitComboBox.setEditable(true);
        produitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        produitComboBox.setMinimumSize(new java.awt.Dimension(120, 30));
        produitComboBox.setPreferredSize(new java.awt.Dimension(120, 30));

        paimentComboBox.setEditable(true);
        paimentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "aucun", "espece", "versement", "credit" }));
        paimentComboBox.setMinimumSize(new java.awt.Dimension(150, 30));
        paimentComboBox.setPreferredSize(new java.awt.Dimension(150, 30));

        ordreTriComboBox.setEditable(true);
        ordreTriComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "croissant", "décroissant" }));
        ordreTriComboBox.setMinimumSize(new java.awt.Dimension(150, 30));
        ordreTriComboBox.setPreferredSize(new java.awt.Dimension(150, 30));

        jScrollPane1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        resultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                resultTableMouseReleased(evt);
            }
        });
        resultTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                resultTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(resultTable);

        dateLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        dateLabel.setText("jLabel2");

        timeLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        timeLabel.setText("jLabel2");

        clientLabel.setText("Client");
        clientLabel.setName("clientLabel"); // NOI18N

        produitLabel.setText("Produit");

        ordreTriLabel.setText("Ordre de tri");

        paimentLabel.setText("Payment");

        totalLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        totalLabel.setText("jLabel6");
        totalLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        dateDebutChooserCombo.setCurrentView(new datechooser.view.appearance.AppearancesList("Bordered",
            new datechooser.view.appearance.ViewAppearance("custom",
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    true,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 255),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(128, 128, 128),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(0, 0, 255),
                    false,
                    true,
                    new datechooser.view.appearance.swing.LabelPainter()),
                new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                    new java.awt.Color(0, 0, 0),
                    new java.awt.Color(255, 0, 0),
                    false,
                    false,
                    new datechooser.view.appearance.swing.ButtonPainter()),
                (datechooser.view.BackRenderer)null,
                false,
                true)));
    dateDebutChooserCombo.setWeekStyle(datechooser.view.WeekDaysStyle.SHORT);
    dateDebutChooserCombo.setCurrentNavigateIndex(0);

    dateFinChooserCombo.setCurrentView(new datechooser.view.appearance.AppearancesList("Bordered",
        new datechooser.view.appearance.ViewAppearance("custom",
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                true,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 255),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.ButtonPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(128, 128, 128),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(0, 0, 255),
                false,
                true,
                new datechooser.view.appearance.swing.LabelPainter()),
            new datechooser.view.appearance.swing.SwingCellAppearance(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11),
                new java.awt.Color(0, 0, 0),
                new java.awt.Color(255, 0, 0),
                false,
                false,
                new datechooser.view.appearance.swing.ButtonPainter()),
            (datechooser.view.BackRenderer)null,
            false,
            true)));
dateFinChooserCombo.setCurrentNavigateIndex(0);

searchButton.setText("Cherche");
searchButton.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        searchButtonActionPerformed(evt);
    }
    });

    versementTotalLabel.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
    versementTotalLabel.setText("jLabel1");
    versementTotalLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addComponent(titre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(dateLabel)
                .addComponent(timeLabel)))
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1)
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(clientLabel)
                                .addComponent(produitLabel)))
                        .addComponent(produitComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clientComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(120, 120, 120)
                            .addComponent(paimentLabel)
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addGap(112, 112, 112)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(paimentComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(ordreTriComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                    .addComponent(dateDebutChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(24, 24, 24)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dateFinChooserCombo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addContainerGap(156, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(ordreTriLabel)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(versementTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
    );

    layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clientComboBox, ordreTriComboBox, paimentComboBox, produitComboBox});

    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(titre, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(timeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(clientLabel)
                .addComponent(ordreTriLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clientComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ordreTriComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(dateDebutChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(dateFinChooserCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(produitLabel)
                .addComponent(paimentLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produitComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paimentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(searchButton))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(versementTotalLabel)
                .addComponent(totalLabel))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        initLocals();
    }//GEN-LAST:event_formWindowActivated

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        searchProcess();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        selectionProcess();
    }//GEN-LAST:event_formWindowGainedFocus

    private void SupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SupprimerActionPerformed
        if((resultTable.getSelectedColumn() > -1)&&
                (resultTable.getSelectedRow() > -1)){
            int idOperation = (int)resultTable.
                                getValueAt(resultTable.getSelectedRow()
                                         , 0);
            OperationDialog f = new OperationDialog(parentDialog,operation, 
                                                FileProcess.DELETE,
                                                idOperation);
            f.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "aucun élément n'est choisi");
        }
    }//GEN-LAST:event_SupprimerActionPerformed

    private void resultTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultTableMouseReleased
        if(evt.isPopupTrigger()){
            popupMenu.show(this, evt.getX() , evt.getYOnScreen());
        }
    }//GEN-LAST:event_resultTableMouseReleased

    private void NouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NouveauActionPerformed
        OperationWindow f = new OperationWindow(this,operation,FileProcess.CREATE);
        f.setVisible(true);
    }//GEN-LAST:event_NouveauActionPerformed

    private void ModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModifierActionPerformed
        modifyOperation();
    }//GEN-LAST:event_ModifierActionPerformed

    private void consulterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consulterActionPerformed
        consultOperation();
    }//GEN-LAST:event_consulterActionPerformed

    private void resultTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_resultTableKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    OperationWindow f = new OperationWindow(parentDialog,operation,FileProcess.CREATE);
                    f.setVisible(true);
                }
            });
        }else if (evt.getKeyCode() == KeyEvent.VK_F2){
            consultOperation();
        }else if(evt.getKeyCode() == KeyEvent.VK_F4){
            modifyOperation();
        }else if(evt.getKeyCode() == KeyEvent.VK_F8){
            java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        int idOperation = (int)resultTable.getValueAt(resultTable.getSelectedRow(),0);
                        OperationWindow f = new OperationWindow(parentDialog,operation,FileProcess.CONSULT,idOperation);
                        f.print();
                    }
            });
        }
    }//GEN-LAST:event_resultTableKeyPressed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    int idOperation = (int)resultTable.getValueAt(resultTable.getSelectedRow(),0);
                    OperationWindow f = new OperationWindow(parentDialog,operation,FileProcess.CONSULT,idOperation);
                    f.print();
                }
        });
          
    }//GEN-LAST:event_printActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            dispose();
        }
    }//GEN-LAST:event_formKeyPressed

    private void ModifierKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ModifierKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModifierKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Modifier;
    private javax.swing.JMenuItem Nouveau;
    private javax.swing.JMenuItem Supprimer;
    private javax.swing.JComboBox<String> clientComboBox;
    private javax.swing.JLabel clientLabel;
    private javax.swing.JMenuItem consulter;
    private datechooser.beans.DateChooserCombo dateDebutChooserCombo;
    private datechooser.beans.DateChooserCombo dateFinChooserCombo;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> ordreTriComboBox;
    private javax.swing.JLabel ordreTriLabel;
    private javax.swing.JComboBox<String> paimentComboBox;
    private javax.swing.JLabel paimentLabel;
    private javax.swing.JPopupMenu popupMenu;
    private javax.swing.JMenuItem print;
    private javax.swing.JComboBox<String> produitComboBox;
    private javax.swing.JLabel produitLabel;
    private javax.swing.JTable resultTable;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel titre;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JLabel versementTotalLabel;
    // End of variables declaration//GEN-END:variables
    
    private TableModelList jdbc;
    private Operation operation;
    private String operationName;
    private String operandeName;
    private String operatorName;
    private String tableName;
    private String[] columnNames;
    private String[] detailColumnNames;
    
 
    private void initLocals(){
        Date aujourdhui = new Date();
        String datePattern = "dd/MM/YYYY";
        String timePattern = "hh:mm";
        SimpleDateFormat formatter;
	formatter = new SimpleDateFormat(datePattern);
	String output = formatter.format(aujourdhui);
        dateLabel.setText(output);
        formatter = new SimpleDateFormat(timePattern);
        output = formatter.format(aujourdhui);
        timeLabel.setText(output);
        
        fillCombo(produitComboBox,"SELECT DESIG FROM produit ORDER BY DESIG");
        fillCombo(clientComboBox, "SELECT NOM FROM " + operatorName 
                                                +" ORDER BY NOM");
        titre.setText(" LISTE DES " + operationName + "S");
        resultTable.setDefaultRenderer(java.sql.Date.class, 
                                        new DateRenderer(datePattern));
        searchProcess();
        formatColumns(resultTable);
    }

    private TableModel makeSQL(String sql) {
        
        try{
            connect();
            jdbc.executeQuery(sql);
            jdbc.close();
        }catch (NullPointerException e){
            return new AbstractTableModel(){
                @Override
                public String getColumnName(int col) {return columnNames[col];}
                @Override
                public int getColumnCount() { return columnNames.length; }
                @Override
                public int getRowCount() { return 0;}
                @Override
                public Object getValueAt(int row, int col) { return new Integer(0); } 
            };
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur dans la requete");
        }
        return jdbc;
    }
   
    private void connect() {
        jdbc = TableModelList.connect();
        String[] colomnNames = {"N°", "DATE", "HEURE", "NOM", "MODE", "TOTAL", "VERSEMENT"};
        jdbc.setColumnNames(colomnNames);
    }

    private void searchProcess() {
        // intDate & lastDate must be valid dates
        // and initDate mustbe after lastDate it mean 
        // lastDate before initDate
        
        String initDate = DateAdapter.revertDate(dateDebutChooserCombo.getText());
        String lastDate = DateAdapter.revertDate(dateFinChooserCombo.getText());
        String clientName = (String)clientComboBox.getSelectedItem();
        String productName = (String)produitComboBox.getSelectedItem();
        String paimentMode = (String)paimentComboBox.getSelectedItem();
        String order = (String)ordreTriComboBox.getSelectedItem();
        boolean condition;
        condition = (clientName != "");
        String whereString;
        condition = (order == "croissant");
        String orderChoice = (condition ? "" : " DESC");
        String sql;
        String totalSQL;
        switch(operation){
            case BUY:
                whereString = (condition ? "" : " AND f.nom ='" + 
                                clientName + "'");
                condition = (paimentMode == "aucun");
                whereString += (condition ? "" : " AND a.mode ='" + 
                                paimentMode + "'");
                sql = "SELECT a.ida,a.d,a.t,f.nom,a.mode,a.total, "
                     + "CASE WHEN a.mode='ESPECE' THEN a.total " 
                     +      "WHEN a.mode='CREDIT' THEN 0.00 " 
                     +                "ELSE b.mont END as VERSEMENT "
                     + "FROM achat a INNER JOIN four f "
                     + "on a.id = f.id "
                     + "LEFT JOIN versf b "
                     + "ON a.ida = b.ida "
                     + "WHERE a.d >='"+ initDate 
                     + "' AND a.d <='" + lastDate + "'"
                     // the other where clauses
                     + whereString
                     + " ORDER BY a.ida "
                     + orderChoice;
                totalSQL = "SELECT SUM(total_sum) FROM "
                        + "( SELECT SUM(total) as total_sum FROM achat "
                        + "WHERE d>='" + initDate + "' AND d<='" + lastDate
                        + "' GROUP BY d ORDER BY ida) as t1";
            break;    
            case SELL:
                whereString = (condition ? "" : " AND c.nom ='" + 
                                        clientName + "'");
                condition = (paimentMode == "aucun");
                whereString += (condition ? "" : " AND v.mode ='" + 
                                        paimentMode + "'");
        
                sql = "SELECT v.ida,v.d,v.t,c.nom,v.mode,v.total, "
                     + "CASE WHEN v.mode='ESPECE' THEN v.total " 
                     +      "WHEN v.mode='CREDIT' THEN 0.00 " 
                     +                "ELSE a.mont END as VERSEMENT "
                     + "FROM vente v INNER JOIN client c "
                     + "on v.id = c.id "
                     + "LEFT JOIN versc a "
                     + "ON v.ida = a.ida "
                     +"WHERE v.d >='"+ initDate 
                     + "' AND v.d <='" + lastDate + "'"
                     // the other where clauses
                     + whereString
                     + " ORDER BY v.ida "
                     + orderChoice;
                totalSQL = "SELECT SUM(total_sum) FROM "
                        + "( SELECT SUM(total) as total_sum FROM vente "
                        + "WHERE d>='" + initDate + "' AND d<='" + lastDate
                        + "' GROUP BY d ORDER BY ida) as t1";
            break;
            default:
                sql = "";
                totalSQL = "";
                
            break;
        }               
        TableModel resultModel = makeSQL(sql); 
        resultTable.setModel(resultModel);
        totalLabel.setText(getSum(2));
        versementTotalLabel.setText(getSum(1));
        formatColumns(resultTable);
    }

    private void formatColumns(JTable table) {
        int tableWidth = 0;
        TableColumn column = null;
        for (int i = 0; i < table.getColumnCount(); i++) {
            column = table.getColumnModel().getColumn(i);
            
            switch (i) {
                case 0:
                    //third column is bigger
                    column.setMinWidth(0);
                    column.setMaxWidth(80);
                    column.setPreferredWidth(80);
                    break;
                case 1:
                    column.setMinWidth(0);
                    column.setMaxWidth(150);
                    column.setPreferredWidth(150);
                    break;
                case 2:
                    column.setMinWidth(0);
                    column.setMaxWidth(100);
                    column.setPreferredWidth(100);
                    break;
                case 3: 
                    column.setMinWidth(0);
                    column.setMaxWidth(250);
                    column.setPreferredWidth(250);
                    break;
                case 4:
                    column.setMinWidth(0);
                    column.setMaxWidth(150);
                    column.setPreferredWidth(150);
                    break;
                case 5:
                    column.setPreferredWidth(100);
                    break;               
            }
            tableWidth += column.getPreferredWidth();
        }
        table.setSize(table.getPreferredSize().height,tableWidth);
    }

    private void selectionProcess() {
        resultTable.requestFocusInWindow();
        resultTable.changeSelection(
        resultTable.getRowCount() - 1,1, false, false);
    }

    private void setObjectFonts() {
        resultTable.setFont(Utilities.tableFont());
        resultTable.getTableHeader().setFont(Utilities.headTablFont());
        resultTable.setRowHeight(Utilities.fontHeight());
        resultTable.setSelectionBackground(Color.red);
        resultTable.setSelectionForeground(Color.white);
        totalLabel.setFont(Utilities.headTablFont());
        clientLabel.setFont(Utilities.headTablFont());
        clientComboBox.setFont(Utilities.tableFont());
        produitLabel.setFont(Utilities.headTablFont());
        produitComboBox.setFont(Utilities.tableFont());
        ordreTriLabel.setFont(Utilities.headTablFont());
        ordreTriComboBox.setFont(Utilities.headTablFont());
        paimentLabel.setFont(Utilities.headTablFont());
        paimentComboBox.setFont(Utilities.headTablFont());
        dateDebutChooserCombo.setFieldFont(Utilities.headTablFont());
        dateFinChooserCombo.setFieldFont(Utilities.headTablFont());
        searchButton.setFont(Utilities.headTablFont());
    }

    private String getSum(int j) {
    BigDecimal result = new BigDecimal(0);
        int res = 0;
        for(int i=0; i < resultTable.getRowCount(); i++){
            result = ((BigDecimal)resultTable.getValueAt(i, 
                            resultTable.getColumnCount()- j));
            res += result.doubleValue();           
        }
        return Double.toString(res);    
    }

    private void fillCombo(JComboBox<String> comboBox, String sql) {
        JDBCAdapter table = JDBCAdapter.connect();
        comboBox.removeAllItems();
        table.executeQuery(sql);
        for (int i = 0; i < table.getRowCount(); i++){
            comboBox.addItem((String)table.getValueAt(i,0));
        }
    }

    private void modifyOperation() {
                if((resultTable.getSelectedColumn() > -1)&&
                (resultTable.getSelectedRow() > -1)){
            int idOperation = (int)resultTable.
                                getValueAt(resultTable.getSelectedRow()
                                         , 0);
            OperationWindow f = new OperationWindow(this,operation, 
                                                FileProcess.MODIFY, 
                                                idOperation);
            f.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "aucun élément n'est choisi");
        }
    }

    private void consultOperation() {
                if((resultTable.getSelectedColumn() > -1)&&
                (resultTable.getSelectedRow() > -1)){    
            int idOperation = (int)resultTable.
                                getValueAt(resultTable.getSelectedRow()
                                         , 0);
            OperationDialog f = new OperationDialog(this,operation,
                                                FileProcess.CONSULT,
                                                idOperation);
            f.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "aucun élément n'est choisi");
        }
    }

}
