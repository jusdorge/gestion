package CommerceApp;
import Adapters.Buttom;
import Adapters.DateAdapter;
import Adapters.DoubleAdapter;
import Adapters.FrameAdapter;
import static Adapters.FrameAdapter.centerFrame;
import Adapters.Header;
import Adapters.HeaderPrint;
import Adapters.JDBCAdapter;
import Adapters.Pagination;
import Adapters.RecordOperation;
import Adapters.RecordPayment;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.DefaultCellEditor;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import util.FileProcess;
import util.Operation;
import util.Utilities;
import objects.MyTableModel;
import objects.TableProduct;
import objects.DoubleRenderer;
import objects.IntegerRenderer;
import objects.TFEditor;
import objects.TableModelDetail;
import objects.Validation;


//
/**
 * la classe dialogue operation commerciale realise differente 
 * actions (création, modification, suppression) 
 * d'  (achat, vente, retour d'achat, retour de vente,
 * commande, facture, devis).
 * 
 * @author BENHADDOU MOHAMED AMINE
 */
public class OperationWindow extends javax.swing.JDialog implements KeyListener,                      
                                                           TableModelListener,                                    
                                                           Validation{
    int TAB, OPE;
    Header head, head1;
    Buttom buttom;
    String mode;
    double versement;
    RecordPayment oldRecordVersement;
    RecordOperation oldRecordOperation;
    private static final int windowWidth = 1025;
    private static final int windowHeight = 730;
    private static final Dimension textFieldSize = new Dimension(10,100);
    private JDialog parentFrame;
    private Operation operation;
    private FileProcess process;
    private String customer;
    private ChoiceWindow clientChoice;
    private ChoiceWindow productChoice;
    private BigDecimal customerSolde;
    private int rowTableSelected;
    private int columnTableSelected;
    private ListSelectionModel listSelectionModel;
    private Dimension textFieldPrefferedSize;
    private ArrayList<String> allProducts = new ArrayList<String>();
    private Map<String, Integer> productMap = new TreeMap<String, Integer>();
    private int titleFrameHeight;
    private Container contentPane;
    private int row;
    private String headRecord1,headRecord2;
    private ArrayList buttomRecords1,buttomRecords2;
    private double newCredit;
    private Object idVersement;
    private String f;
    
    /**
     * constructeur de la classe operation window 
     * prend en charge trois paramètres.
     * @param frm le dialog ou la fenetre qui a declencher cette fenetre.
     * @param op énumeration pour le type d'operation (vente, achat, retour d'achat,
     * retour de vente.
     * @param pr paramètre énumération du type de processus sur 
     * l'operation a savoir (création, modification, supression).
     */
    public OperationWindow(JFrame frm,Operation op, FileProcess pr) {
        super(frm,true);
        operation = op;
        process = pr;
        initComponents();
        formatSubTitleLabel();
        init();
        formatDateLabel();
    }
    
    public OperationWindow(JDialog dlg,Operation op, FileProcess pr) {
        super(dlg,true);
        operation = op;
        process = pr;
        initComponents();
        formatSubTitleLabel();
        init();
        formatDateLabel();
    }
    
    public OperationWindow(JDialog dlg, Operation op, FileProcess pr, int ida){
        super(dlg,true);
        operation = op;
        process = pr;
        row = ida;
        initComponents();
        formatSubTitleLabel(ida);
        fillTable();
        fillOperator();
        fillAncientDate();
        fillSolde(); 
        init();
        formatDateLabel(ida);
        initModeLabel();
        if (process == FileProcess.MODIFY){
            //enregistrement de la modification dans la table suppvente ou suppachat
            head1 = new Header(arrayListHeader());
            RecordOperation ro2 = new RecordOperation(TAB,4,head1,table.getModel());
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run() {
                    ro2.record_head();
                    ro2.getAllRecordButtoms();
                    //ro2.deleteAllButtoms();
                    //ro2.deleteHead();
                    oldRecordOperation = ro2;
                    if (mode.equals("VERSEMENT")){
                        oldRecordVersement  = getRecordVersement();
                    }     
                }
            
            });
        }
    }
    
    private void init(){
        parentFrame = this;
        mode = "ESPECE";
        switch (operation){
            case BUY:
                TAB = 1;
                break;
            case SELL:
                TAB = 2;
                break;
            case BUYBACK:
                TAB = 3;
                break;
            case SELLBACK:
                TAB = 4;
                break;
            case ORDER:
                TAB = 5;
                break;
            case QUOTE:
                TAB = 6;
                break;
            case BILL:
                TAB = 7;
                break;
            case LOSS:
                TAB = 8;
                break;
        }     
        
        switch (process){
            case CREATE:
                OPE = 1;
                break;
            case MODIFY:
                OPE = 2;        
                break;
            case DELETE:
                OPE = 3;
                break;
            case RESTORE:
                OPE = 1;
                break;
         }   
        textField.requestFocusInWindow();
        setIconImage(Utilities.setIconImage(this));
        formatTable();
        formatTitleLabel();
        //formatSubTitleLabel();
        clientChoice = new ChoiceWindow(operation.getOperator());
        productChoice = new ChoiceWindow(operation.getOperand());
        contentPane = this.getContentPane();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField = new javax.swing.JTextField();
        lastVisitTextField = new javax.swing.JTextField();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        totalTextField = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        soldeTextField = new javax.swing.JTextField();
        clientLabel = new javax.swing.JLabel();
        lastVisitLabel = new javax.swing.JLabel();
        totalLabel = new javax.swing.JLabel();
        clientButton = new javax.swing.JButton();
        numeroLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        tableInfoLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        annulerButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        insertButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        beforeButton = new javax.swing.JButton();
        lastButton = new javax.swing.JButton();
        firstButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        modeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(process.getProcessTitle() + " " + operation.getFrameTitle());
        setResizable(false);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        textField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textFieldKeyTyped(evt);
            }
        });

        lastVisitTextField.setEditable(false);

        table.setModel(new MyTableModel());
        table.setFocusable(false);
        table.setRowSelectionAllowed(false);
        table.setSelectionBackground(new java.awt.Color(255, 0, 0));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        scrollPane.setViewportView(table);

        totalTextField.setEditable(false);
        totalTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalTextField.setText("000000.00");
        totalTextField.setFocusable(false);

        titleLabel.setText("jLabel1");

        soldeTextField.setEditable(false);

        clientLabel.setText("Client");

        lastVisitLabel.setText("Derniere visite ");

        totalLabel.setText("Total");

        clientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientButtonActionPerformed(evt);
            }
        });

        numeroLabel.setText("jLabel1");

        jLabel1.setText("solde");

        dateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateLabel.setText("date");

        tableInfoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setText("F8 - Valider en versement");

        jLabel2.setText("F2 - Supprimer une ligne");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel4.setText("F10 - Valider en espèce");

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        annulerButton.setText("Annuler");
        annulerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("F3 - Ajouter une ligne");

        jLabel6.setText("F6 - Valider en credit");

        jLabel7.setText("F4 - Changer le prix de vente");

        addButton.setText("Ajouter");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Supprimer");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        insertButton.setText("Inserer");

        nextButton.setText("Suivant");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        beforeButton.setText("Precedent");
        beforeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beforeButtonActionPerformed(evt);
            }
        });

        lastButton.setText("Dernier");
        lastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastButtonActionPerformed(evt);
            }
        });

        firstButton.setText("Premier");
        firstButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("MODE PAIMENT");

        modeLabel.setBackground(new java.awt.Color(255, 255, 255));
        modeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        modeLabel.setText("ESPECE");
        modeLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        modeLabel.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(okButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(annulerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(insertButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(beforeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nextButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(totalLabel)
                                .addGap(18, 18, 18)
                                .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(clientLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(modeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(clientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(soldeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(61, 61, 61)
                                        .addComponent(lastVisitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addComponent(jLabel1)
                                        .addGap(145, 145, 145)
                                        .addComponent(lastVisitLabel)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(193, 193, 193)
                        .addComponent(tableInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {addButton, annulerButton, beforeButton, deleteButton, firstButton, insertButton, lastButton, nextButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(numeroLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clientLabel)
                            .addComponent(jLabel1)
                            .addComponent(lastVisitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(modeLabel)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lastVisitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(soldeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton)
                        .addGap(0, 0, 0)
                        .addComponent(annulerButton)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addButton)
                        .addGap(6, 6, 6)
                        .addComponent(deleteButton)
                        .addGap(6, 6, 6)
                        .addComponent(insertButton)
                        .addGap(161, 161, 161)
                        .addComponent(firstButton)
                        .addGap(6, 6, 6)
                        .addComponent(beforeButton)
                        .addGap(6, 6, 6)
                        .addComponent(nextButton)
                        .addGap(4, 4, 4)
                        .addComponent(lastButton)))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(tableInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {clientButton, lastVisitTextField, soldeTextField, textField});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   /**
     * event handeler for 
     * text field for client choice
     * managing the show of client choice frame.
     * @param evt 
     */
    private void textFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            showClientChoice();
        }else if(evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            escapeKeyPressed();
        }
    }//GEN-LAST:event_textFieldKeyPressed

    private void clientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientButtonActionPerformed
        showClientChoice();
    }//GEN-LAST:event_clientButtonActionPerformed

    private void textFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textFieldKeyTyped
        char keyChar = evt.getKeyChar();
        if (Character.isLowerCase(keyChar)) {
            evt.setKeyChar(Character.toUpperCase(keyChar));
        }
    }//GEN-LAST:event_textFieldKeyTyped

    private void annulerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerButtonActionPerformed
        dispose();
    }//GEN-LAST:event_annulerButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        addTableRow();
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        removeTableRow();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        
    }//GEN-LAST:event_formInternalFrameActivated

    private void formInternalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameDeactivated
        
    }//GEN-LAST:event_formInternalFrameDeactivated

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        ArrayList<Object> result;
        if (process == FileProcess.CREATE){
            result = clientChoice.getResult(); 
            try{
                if (result.size() != 0){
                                table.setFocusable(true);
                                textField.setText((String)result.get(0));
                                customerSolde = (BigDecimal) result.get(1);
                                fillSoldeTextField();
                                fillLastVisitTextField();
                                table.requestFocusInWindow(); 
                }
                formatResultTable(evt);  
            }catch(NullPointerException ex){
            }   	
        }else{
            result  = new ArrayList();
            result.add(textField.getText());
            if (result.size() != 0){
                                table.setFocusable(true);
                                table.requestFocusInWindow(); 
            }
            formatResultTable(evt);  
        }		
    }//GEN-LAST:event_formWindowGainedFocus

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        if (evt.getOppositeWindow() != null){
		if (evt.getOppositeWindow().getName() == "produit"){
                	rowTableSelected = table.getSelectedRow();
			columnTableSelected = table.getSelectedColumn();
            	}
	}
    }//GEN-LAST:event_formWindowLostFocus

    private void firstButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstButtonActionPerformed
        String sql = "SELECT IDA, D, T, MODE, TOTAL, NOM, SOLDE FROM " + operation.getTableName()
                    + " a LEFT JOIN " + operation.getOperator().getTableName()
                    + " b ON a.ID = b.ID LIMIT 1";
        updateFrame(sql);
        
    }//GEN-LAST:event_firstButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        String id = numeroLabel.getText().substring(2);
        int idOperation = Integer.parseInt(id) + 1;
        String sql = "SELECT IDA, D, T, MODE, TOTAL, NOM, SOLDE FROM " + operation.getTableName()
                    + " a LEFT JOIN " + operation.getOperator().getTableName()
                    + " b ON a.ID = b.ID WHERE IDA=" + idOperation;
        updateFrame(sql);
    }//GEN-LAST:event_nextButtonActionPerformed

    private void beforeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beforeButtonActionPerformed
        String id = numeroLabel.getText().substring(2);
        int idOperation = Integer.parseInt(id) - 1;
        String sql = "SELECT IDA, D, T, MODE, TOTAL, NOM, SOLDE FROM " + operation.getTableName()
                    + " a LEFT JOIN " + operation.getOperator().getTableName()
                    + " b ON a.ID = b.ID WHERE IDA=" + idOperation;
        updateFrame(sql);
    }//GEN-LAST:event_beforeButtonActionPerformed

    private void lastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastButtonActionPerformed
        String sql = "SELECT IDA, D, T, MODE, TOTAL, NOM, SOLDE FROM " 
                    + operation.getTableName()
                    + " a LEFT JOIN " + operation.getOperator().getTableName()
                    + " b ON a.ID = b.ID ORDER BY IDA DESC LIMIT 1";
        updateFrame(sql);
    }//GEN-LAST:event_lastButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if ((process == FileProcess.MODIFY)&&(mode.equals("VERSEMENT"))){
            //look for the payment of this operation in vers table
            JDBCAdapter vers = JDBCAdapter.connect();
            String sql = "SELECT MONT FROM vers" + f() + 
                    " WHERE IDA=" + numeroLabel.getText().substring(2); 
            vers.executeQuery(sql);
            versement = Double.parseDouble(vers.getValueAt(0, 0).toString());
        }
        if (process != FileProcess.CONSULT){
            output();
        }else{
            dispose();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE){
            escapeKeyPressed();
        }else if (evt.getKeyCode() == KeyEvent.VK_F10){
            output();
        }else if (evt.getKeyCode() == KeyEvent.VK_F8){
            mode = "VERSEMENT";
            modeLabel.setText(mode);
            Versement v = new Versement(parentFrame, 
                            Double.parseDouble(totalTextField.getText()),
                            Double.parseDouble(soldeTextField.getText()));
            v.setVisible(true);
            versement = v.getVersement();
            newCredit = v.getNewCredit();
            dispose();
            RecordPayment rv = getRecordVersement();
            rv.recordPayment();
            output();
        }else if (evt.getKeyCode() == KeyEvent.VK_F6){
            mode = "CREDIT";
            modeLabel.setText(mode);
            output();
        }else if (evt.getKeyCode() == KeyEvent.VK_F3){
            addTableRow();
        }
    }//GEN-LAST:event_tableKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton addButton;
    protected javax.swing.JButton annulerButton;
    private javax.swing.JButton beforeButton;
    private javax.swing.JButton clientButton;
    private javax.swing.JLabel clientLabel;
    protected javax.swing.JLabel dateLabel;
    protected javax.swing.JButton deleteButton;
    private javax.swing.JButton firstButton;
    protected javax.swing.JButton insertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JButton lastButton;
    private javax.swing.JLabel lastVisitLabel;
    protected javax.swing.JTextField lastVisitTextField;
    private javax.swing.JLabel modeLabel;
    private javax.swing.JButton nextButton;
    protected javax.swing.JLabel numeroLabel;
    protected javax.swing.JButton okButton;
    private javax.swing.JScrollPane scrollPane;
    protected javax.swing.JTextField soldeTextField;
    protected javax.swing.JTable table;
    private javax.swing.JLabel tableInfoLabel;
    protected javax.swing.JTextField textField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel totalLabel;
    protected javax.swing.JTextField totalTextField;
    // End of variables declaration//GEN-END:variables
    public void setOperator(){
        ArrayList<Object> result = clientChoice.getResult(); 
        try{
            if (result.size() != 0){
                            table.setFocusable(true);
                            textField.setText((String)result.get(0));
                            customerSolde = (BigDecimal) result.get(1);
                            fillSoldeTextField();
                            fillLastVisitTextField();
                            table.requestFocusInWindow(); 
            }        
        }catch(NullPointerException ex){

        }
    }
    public void setTableResult(){
        try{
            ArrayList<Object> resultTable = productChoice.getResult();
            if (resultTable != null){
                if (table.getSelectedRow() == -1){
                    table.changeSelection(0,table.getSelectedColumn(),false,false);
                }
                if (table.getSelectedColumn() == -1){
                    table.changeSelection(table.getSelectedRow(), 0, false, false);
                }
                if (resultTable.size() != 0){
                String productName =(String) resultTable.get(0);
                Number unitPrice = (Number) resultTable.get(1);
                Number price = (Number) resultTable.get(2);
                if (!productAlreadyInTable(productName)){
                    table.setValueAt(productName, rowTableSelected, 0);
                    table.setValueAt(unitPrice.doubleValue(), rowTableSelected, 2);
                    table.setValueAt(price.doubleValue(), rowTableSelected, 3);
                    table.changeSelection(rowTableSelected, columnTableSelected + 1, false, false);
                }else {
                    JOptionPane.showMessageDialog(parentFrame, "Ce produit est déjà inseré");
                    resultTable = null;
                    table.changeSelection (getTableProducts().indexOf(productName),columnTableSelected,false,false);
                }
            }
        }else{
            table.changeSelection(rowTableSelected, columnTableSelected , false, false);
        }			
        }catch(NullPointerException ex){

        }        
    }
    /**
     * formats the Title Label at the top of the frame
     * containing the operation type and process type.
     */
    private void formatTitleLabel() {
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setText(this.getTitle());
        titleLabel.setFont(Utilities.getTitleLabelFont());
        titleLabel.setForeground(Utilities.getTitleLabelForeground());
        titleLabel.setBackground(Utilities.getTitleLabelBackGround());   
    }  

    private void showClientChoice() {
        clientChoice.show(textField.getLocationOnScreen().x, 
                          textField.getLocationOnScreen().y + 
                                  textField.getHeight(),
                          400, 
                          textField.getText());
    }

    private boolean productAlreadyInTable(String productName) {
        if ((getTableProducts().indexOf(productName) > - 1) && (productName != "")){
			return true;
		}
		return false;
    }

    private ArrayList<String> getTableProducts() {
        ArrayList<String> tableProducts = new ArrayList<String>();
        for (int i = 0; i < table.getRowCount(); i++){
                tableProducts.add((String)table.getValueAt(i,0));
        }
        return tableProducts;
    }

    private void formatComponents() {
        for (Component c:this.getContentPane().getComponents()){
            if (c.getClass().getName() == "javax.swing.JTextField"){
                c.setFont(Utilities.tableFont());
            }else if(c.getClass().getName() == "javax.swing.JLabel"){
                if (c != titleLabel){
                    c.setFont(Utilities.tableFont());
                }
            }
        }
    }

    private void formatSubTitleLabel() {
        numeroLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numeroLabel.setOpaque(true);
        numeroLabel.setText("N°" + getNumero());
        numeroLabel.setFont(Utilities.getTitleLabelFont());
        numeroLabel.setForeground(Utilities.getSubTitleLabelForeground());
        numeroLabel.setBackground(Utilities.getSubTitleLabelBackGround());          
    }

    private String getNumero() {
        String sql;
        sql = "SELECT IDA FROM " + operation.getTableName()+
                " ORDER BY IDA DESC";
        JDBCAdapter result = JDBCAdapter.connect();
        result.executeQuery(sql);
        int lastRow;
        lastRow = result.getRowCount();
        int numero = 0;
        if (lastRow >= 1){
            numero = (int)result.getValueAt(0, 0);
        }
        return Integer.toString(numero + 1);
    }

    private void formatDateLabel() {
        Date today = new Date();
        String pattern = "dd/MM/YYYY--H:mm";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String output = formatter.format(today);
        dateLabel.setText(output);
    }
    
    private void formatDateLabel(int ida){
        String sql = "SELECT D,T FROM " + 
                operation.getFrameTitle() +
                " WHERE ida=" + row;
        JDBCAdapter dateQuery = JDBCAdapter.connect();
        dateQuery.executeQuery(sql);
        String d = dateQuery.getValueAt(0, 0).toString();
        String t = dateQuery.getValueAt(0, 1).toString();
        String pattern = "dd/MM/YYYY--H:mm";
        SimpleDateFormat myFormat = new SimpleDateFormat(pattern);
        
        dateLabel.setText(DateAdapter.convertDate(d) +"--"+ t);
        
    }

    private void formatTable() {
        table.setRowHeight(Utilities.fontHeight());
        table.getColumnModel().getColumn(0).setPreferredWidth(500);
        for (int i = 0; i < table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellEditor(
				new TFEditor());
        }
        table.setDefaultRenderer(Integer.class, new IntegerRenderer());
        table.setDefaultRenderer(Double.class, new DoubleRenderer());
        table.addKeyListener(this);
        // adds a list selection model to the selectionmodel of the table.
        listSelectionModel = table.getSelectionModel();
        listSelectionModel.addListSelectionListener(new SharedListSelectionHandler());
        
        // if the client text field is empty desable the focus under the table.
        if (textField.getText() != null){
                table.setFocusable(true);
        }else{
                table.setFocusable(false);
                textField.requestFocusInWindow();
        }
        
        // defines a new input map for the Enter KeyStroke "ENTER" in the table.
        table.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"check");
        table.getActionMap().put("check", new AbstractAction() {
        // Overrides the default enter key event of the table.
        public void actionPerformed(ActionEvent ex) {tableActionPerformed(ex);}
        });
				
        //adds a tableModellistener to the frame.
        table.getModel().addTableModelListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();
        if (Character.isLowerCase(keyChar)) {
            e.setKeyChar(Character.toUpperCase(keyChar));
        }
        if (e.getSource() == table){
            int column = table.getSelectedColumn();
            if (column == 1){
                    if (!Character.isDigit(keyChar)){
                            e.setKeyChar(new Character(KeyEvent.CHAR_UNDEFINED));
                    }
            }else if ((column == 2)||(column == 3)){
                    Character point = Character.valueOf('.');
                    if ((!Character.isDigit(keyChar))&&(!point.equals(keyChar))){
                            e.setKeyChar(new Character(KeyEvent.CHAR_UNDEFINED));
                    }
            }
        }    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_F2){
            removeTableRow();
        }
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int column = e.getColumn();
        if ((column > 0)||(column < 4)){
                MyTableModel model = (MyTableModel)table.getModel();
                Double sum = model.getSum();
                String somme = sum.toString();
                if (sum != 0.0){
                    totalTextField.setText(somme);
                    if (DoubleAdapter.isFormatable(sum)){
                        totalTextField.setText(DoubleAdapter.getFormatToString());
                    }
                }
        }  
    }
    
    /**
    *	actions performed when the table receive an anter keypressed event.
    */
    private void tableActionPerformed(ActionEvent e){
        int r = table.getSelectedRow();
        int c = table.getSelectedColumn();
        MyTableModel model = (MyTableModel) table.getModel();
        TableCellEditor editor = table.getCellEditor();
        String productName = (String)table.getValueAt(r,0);
        if ((editor != null) && (c == 0)){
            productName = (String) editor.getCellEditorValue();
            editor.stopCellEditing();
        }
        if (productName == null){
            productName = (String) table.getValueAt(r, c);
        }
        if ((r != -1) && (c != -1)){
            if ((c == 0) && (table.isFocusable()) && 
                        productDoesNotExist(productName)){
                titleFrameHeight = this.getHeight() - contentPane.getHeight();
                int y = scrollPane.getLocationOnScreen().y;
                int x = table.getLocationOnScreen().x;
                int width = table.getColumnModel().getColumn(0).getWidth();
                productChoice.show(x, y, 350, productName);		
            }else{
                tableStopEditing();
                int cc = c + 1;
                if (cc >= table.getColumnCount() - 1){
                    if (table.getSelectedRow() == table.getRowCount() - 1){
                        cc = 0;
                        r ++;
                        model.add(new TableProduct());
                    }else{
                        cc = 0;
                    }
                }
                table.clearSelection();
                table.changeSelection(r,cc,false,false);
            }	
        }
    }

    private boolean productDoesNotExist(String product) {
        boolean result = true;
        for (int i = 0; i < allProducts.size(); i++){
            String productName = allProducts.get(i);
            if (productName.equals(product)){
                return false;
            }
        }
        return result;
    }
    
    /**
    **	some private variable .
    */
    private ArrayList<String> getAllProducts (){
        JDBCAdapter productNames;
        String [] productColumnNames = {"DESIG","IDP"};
        String productTableName = operation.getOperand().getTableName();// "product";
        productNames = JDBCAdapter.connect();
        productNames.executeQuery(productColumnNames, productTableName);
        ArrayList <String> productCollection = new ArrayList<String>();
        for (int i = 0; i < productNames.getRowCount(); i++){
            String productName = (String) productNames.getValueAt(i,0);
            Integer id = (Integer) productNames.getValueAt(i,1);
            productCollection.add(productName);
            productMap.put(productName, id);
        }
        return productCollection;
    }
/**
 * stops the editing of the table
 */
    private void tableStopEditing() {
        DefaultCellEditor editor = (DefaultCellEditor) table.getCellEditor();
        if (editor != null){
            editor.stopCellEditing();
        }

    }

    private void fillSoldeTextField() {
        soldeTextField.setText(getSoldeClient());
    }
//Editor
    protected String getSoldeClient() {
        String result="";
        String client = textField.getText();
        String tableName = operation.getOperator().getTableName();
        String sql = "SELECT (SOLDE2 + SOLDE) AS CREDIT FROM " + tableName
                    + " WHERE nom ='" + client + "'";
        JDBCAdapter resultTable = JDBCAdapter.connect();
        resultTable.executeQuery(sql);
        double solde;
        solde = ((BigDecimal)resultTable.getValueAt(0,0)).doubleValue();
        result = Double.toString(solde);
        return result;
    }

    private void fillLastVisitTextField() {
        lastVisitTextField.setText(getLastVisit());
    }

    protected String getLastVisit() {
        String tableName = operation.get().getTableName();
        String tableJoinName = operation.getOperator().getTableName();
        String operatorName = textField.getText();
        String sql = "SELECT D FROM "+ tableName + " LEFT JOIN "
                + tableJoinName + " ON " + tableJoinName 
                + ".id = " + tableName + ".id"
                + " WHERE "  
                + tableJoinName 
                + ".NOM = '" + operatorName + "'";
        String result = "";
        JDBCAdapter tableResult = JDBCAdapter.connect();
        tableResult.executeQuery(sql);
        if (tableResult.getRowCount() > 0){
            int lastRowNumber = tableResult.getRowCount() - 1;
            Date res = (Date)tableResult.getValueAt(lastRowNumber, 0);
            result = res.toString();
        }
        return result;
    }

    private void addTableRow() {
        MyTableModel tm = (MyTableModel) table.getModel();
        tm.add(new TableProduct());
        table.changeSelection(table.getRowCount()-1, 0, false, false);
    }

    private void removeTableRow() {
        if (table.getSelectedRow() >= 0){
            int selectedRow = table.getSelectedRow();
            int selectedColumn = table.getSelectedColumn();
            MyTableModel tm = (MyTableModel) table.getModel();
            tableStopEditing();
            tm.remove(selectedRow);
            changeTableSelection(selectedRow, selectedColumn, tm);
        }else{
            JOptionPane.showMessageDialog(this, "aucun enregistrement n'est selectionné");
        }
    }

    private void changeTableSelection(int row, int column, MyTableModel model) {
        int modelRowCount = model.getRowCount();
        if (row > modelRowCount){
            table.changeSelection(modelRowCount, column, false, false);
        }else{
            table.changeSelection(row, column, false, false);
        }
    }

    private void updateFrame(String sql) {
        JDBCAdapter result = JDBCAdapter.connect();
        result.executeQuery(sql);
        if (result.getRowCount()>0){  
            //Numero de la pièce
            int idOperation = (int)result.getValueAt(0, 0);
            numeroLabel.setText("N°" + Integer.toString(idOperation));

            //solde client
            BigDecimal solde = (BigDecimal)result.getValueAt(0, 6);
            soldeTextField.setText(solde.toString());
            //nom client
            textField.setText((String)result.getValueAt(0, 5));
            //total
            BigDecimal total = (BigDecimal)result.getValueAt(0, 4);
            totalTextField.setText(total.toString());
            if (DoubleAdapter.isFormatable(total.doubleValue())){
                totalTextField.setText(DoubleAdapter.getFormatToString());
            }
            //Last visit date
            Date date = (Date)result.getValueAt(0, 1);
            lastVisitTextField.setText(date.toString());
            dateLabel.setText(date.toString());
            // fill detail table
            String query = "SELECT b.DESIG, a.QTEA, a.QTUA, a.PRIXA, "
                    + "a.QTEA * a.QTUA * a.PRIXA FROM " 
                    + operation.getDetailTableName() + " a LEFT JOIN "
                    + operation.getOperand().getTableName() +" b ON a.IDP = b.IDP"
                    + " WHERE a.IDA = " + idOperation;
            JDBCAdapter tableResult = JDBCAdapter.connect();
            tableResult.executeQuery(query);
            // load the table detail
            MyTableModel tm = new MyTableModel();
            tm.remove(0);
            table.setModel(tm);

            for (int i = 0; i < tableResult.getRowCount(); i++){
                String designation = (String)tableResult.getValueAt(i,0);
                int quantite_unitaire = (int)tableResult.getValueAt(i,1);
                BigDecimal quantite = (BigDecimal)tableResult.getValueAt(i,2);
                BigDecimal prix =(BigDecimal)tableResult.getValueAt(i,3);
                BigDecimal montant = (BigDecimal)tableResult.getValueAt(i,4);
                TableProduct p = new TableProduct();
                p.setName(designation);
                p.setSellPrice(prix.doubleValue());
                p.setQuantity(quantite.doubleValue());
                p.setUnitQuantity(quantite_unitaire);
                tm.add(p);
                table.setValueAt(montant.doubleValue(), i, 4);
            }
            table.setModel(tm);
        }else{
            JOptionPane.showMessageDialog(this, "Ce numero n'existe pas");
        }
    }

    @Override
    public void record() {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                head = new Header(arrayListHeader());
                //creating the buttom of the operation
                RecordOperation ro = new RecordOperation(TAB,OPE,head,table.getModel());
                ro.record_head();
                ro.recordAllButtoms();
            }
        });
    }
    
    private Object getIdOperator() {
        JDBCAdapter t = JDBCAdapter.connect();
        String sql ="SELECT id FROM " + 
                        this.operation.getOperator().getTableName()
                        + " WHERE NOM ='" + textField.getText() + "'";
        t.executeQuery(sql);
        return t.getValueAt(0, 0);
    }

    private Object getMode() {
        return mode;
    }

    public void print() {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                //envoyer les information de l'entete de page
                ArrayList hp = new ArrayList();
                hp.add(0, "BON " + operation.getFrameTitle() + 
                        "   : " + numeroLabel.getText());// parentFrame.getNumero());
                hp.add(1, "AHMED CONFESERIE 0662797468");
                hp.add(2, textField.getText());
                hp.add(3, dateLabel.getText());
                hp.add(4, " ");
                hp.add(5, " ");
                HeaderPrint     headerPrint = new HeaderPrint(hp);
                //envoyer les information du pied de page
                String[] buttomVariables = new String[5];
                buttomVariables [0] = totalTextField.getText();
                switch (mode){
                    case "ESPECE":
                        buttomVariables [1] = totalTextField.getText();
                        buttomVariables [3] = Double.toString(newCredit);
                    break;
                    case "CREDIT":
                        buttomVariables [1] = "0.00";
                        double t = Double.parseDouble(totalTextField.getText()) +
                                    Double.parseDouble(soldeTextField.getText());
                        buttomVariables [3] = Double.toString(t);
                    break;
                    case "VERSEMENT":
                        double tt = Double.parseDouble(totalTextField.getText()) +
                                    Double.parseDouble(soldeTextField.getText()) -
                                    getVersement();
                        buttomVariables [1] = Double.toString(getVersement());
                        buttomVariables [3] = Double.toString(tt);

                    break;
                }
                buttomVariables [2] = soldeTextField.getText();
                buttomVariables [4] = mode;

                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(new Pagination(table.getModel(),3,4,headerPrint, buttomVariables));
                boolean ok = job.printDialog();
                if (ok){
                    try{
                        job.print();
                    }catch (PrinterException ex){
                        JOptionPane.showMessageDialog(parentFrame,"l'impression "
                                + "est impossible/n verifier la connexion");
                    }    
                }
            }
        });
    }
    
    private double getVersement() {
        double result = 0.0;
        String sql_vers = "SELECT MONT FROM vers" + f() 
                    + " WHERE ida =" + numeroLabel.getText().substring(2);
        JDBCAdapter montant = JDBCAdapter.connect();
        System.out.println(sql_vers);
        montant.executeQuery(sql_vers);
        BigDecimal res = (BigDecimal)montant.getValueAt(0, 0);
        result = res.doubleValue();
        return result;
    }
    
    private void output() {
        switch(process){
            case CREATE:
                int n = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez vous enregistrer cette operation?",
                    "Confirmation d'enregistrement",
                    JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION){
                    record();
                }
                int nn = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez vous imprimer l'operation?",
                    "Confirmation d'impression",
                    JOptionPane.YES_NO_OPTION);
                if (nn == JOptionPane.YES_OPTION){
                    dispose();
                    print();
                }else{
                    dispose();
                }
            break;
            case DELETE :
                Object[] options = {"Non",
                                    "Oui"};
                int o = JOptionPane.showOptionDialog(this,
                    "voulez vous vraiment supprimer cette operation",
                    "Confirmation suppression",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
                if (o == JOptionPane.YES_OPTION){
                    dispose();
                }else{
                    delete();
                    dispose();
                }
            break;
            case MODIFY:
                int m = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez vous enregistrer les modifications \n de cette operation?",
                    "Confirmation d'enregistrement",
                    JOptionPane.YES_NO_OPTION);
                if (m == JOptionPane.YES_OPTION){
                    modify();
                }
                int mm = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez vous imprimer l'operation?",
                    "Confirmation d'impression",
                    JOptionPane.YES_NO_OPTION);
                if (mm == JOptionPane.YES_OPTION){
                    dispose();
                    print();
                }else{
                    dispose();
                }
            break;
            case RESTORE:
                int r = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez vous enregistrer les modifications \n de cette operation?",
                    "Confirmation d'enregistrement",
                    JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.YES_OPTION){
                    restore();
                }
                int rr = JOptionPane.showConfirmDialog(
                    this,
                    "Voulez vous imprimer l'operation?",
                    "Confirmation d'impression",
                    JOptionPane.YES_NO_OPTION);
                if (rr == JOptionPane.YES_OPTION){
                    dispose();
                    print();
                }else{
                    dispose();
                }
            break;
                    
        }
    }

    private void delete() { 
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                head = new Header(arrayListHeader());
                //creating the buttom of the operation
                RecordOperation ro = new RecordOperation(TAB,OPE,head,table.getModel());
                ro.record_head();
                ro.recordAllButtoms();   
            }
        });
    }

    private ArrayList arrayListHeader() {
        //Creating the header of the operation
        ArrayList al = new ArrayList();
        al.add(0,numeroLabel.getText().substring(2));//id operation
        al.add(1,this.getIdOperator());//id operateur
        if (mode.equals("VERSEMENT")){
            al.add(2,idVersement);
        }else{
            al.add(2,0);//id versement
        }
        al.add(3,DateAdapter.ConvertDateAdapter(dateLabel.getText().
                  substring(0, dateLabel.getText().indexOf('-'))));
                  //Date
        al.add(4, dateLabel.getText().
                  substring(dateLabel.getText().indexOf('-') 
                  + 2, dateLabel.getText().length()));
                //Time
        al.add(5, this.getMode());//Mode
        al.add(6,1);//util
        if (mode.equals("VERSEMENT")){
            al.add(7, idVersement);
        }else{
            al.add(7, "");//obs
        }
        al.add(8, 1);//id Util
        al.add(9, "PC");
        return al;
    }

    private void modify() {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                System.out.println("Suppression...");
                oldRecordOperation.deleteAllButtoms();
                if (mode.equals("VERSEMENT")){
                    oldRecordVersement.deletePayment();
                }
                //recording the operation
                head = new Header(arrayListHeader());
                RecordOperation ro = new RecordOperation(TAB,1,head,table.getModel());
                System.out.println("Enregistrement...");
                ro.recordAllButtoms();           
                if (mode.equals("VERSEMENT")){
                    RecordPayment rv = getRecordVersement();
                    rv.recordPayment();
                }
            }
        });
    }

    private void restore() {
        record();
    }

    private void formatSubTitleLabel(int r) {
        formatSubTitleLabel();
        numeroLabel.setText("N°" + r);
        
        
    }
        private void fillTable() {
        String query = "SELECT b.DESIG, a.QTEA, a.QTUA, a.PRIXA, "
                + "a.QTEA*a.QTUA*a.PRIXA FROM " +
                operation.getDetailTableName() + " a LEFT JOIN " +
                operation.getOperand().getTableName() + 
                " b ON a.IDP = b.IDP WHERE a.IDA=" + row;
        JDBCAdapter jdbc = JDBCAdapter.connect();
        jdbc.executeQuery(query);
        MyTableModel tm = (MyTableModel)table.getModel();
        tm.remove(0);
        for (int i = 0; i < jdbc.getRowCount(); i++){ 
            String designation = (String)jdbc.getValueAt(i,0);
            int quantite = (int)jdbc.getValueAt(i,1);
            BigDecimal quantite_unitaire = (BigDecimal)jdbc.getValueAt(i,2);
            BigDecimal prix =(BigDecimal)jdbc.getValueAt(i,3);
            BigDecimal montant = (BigDecimal)jdbc.getValueAt(i,4);
            TableProduct p = new TableProduct();
            p.setName(designation);
            p.setSellPrice(prix.doubleValue());
            p.setQuantity(quantite);
            p.setUnitQuantity(quantite_unitaire.doubleValue());
            tm.add(p);
            table.setValueAt(montant.doubleValue(), i, 4);
        }
        totalTextField.setText(Double.toString(tm.getSum()));
        if (DoubleAdapter.isFormatable(tm.getSum())){
            totalTextField.setText(DoubleAdapter.getFormatToString());
        }
    }

    private void fillOperator() {
        String sql = "SELECT b.NOM FROM " + operation.getTableName() +
                    " a LEFT JOIN " + operation.getOperator().getTableName() +
                    " b on a.id=b.id WHERE a.ida=" + row;
        JDBCAdapter result = JDBCAdapter.connect();
        result.executeQuery(sql);
        if (result.getRowCount() >0){
            textField.setText((String)result.getValueAt(0,0));
        }
    }

    private void fillAncientDate() {
       lastVisitTextField.setText(this.getLastVisit());
    }

    private void fillSolde() {
        this.soldeTextField.setText(this.getSoldeClient());
    }

    private void formatResultTable(WindowEvent evt) {
        ArrayList<Object> resultTable = productChoice.getResult();
                if (resultTable != null){
                    if (table.getSelectedRow() == -1){
                        table.changeSelection(0,table.getSelectedColumn(),false,false);
                    }
                    if (table.getSelectedColumn() == -1){
                        table.changeSelection(table.getSelectedRow(), 0, false, false);
                    }
                    if (resultTable.size() != 0){
                        String productName =(String) resultTable.get(0);
                        Number unitPrice = (Number) resultTable.get(1);
                        Number price = (Number) resultTable.get(2);
                        if (!productAlreadyInTable(productName)){
                            table.setValueAt(productName, rowTableSelected, 0);
                            table.setValueAt(unitPrice.doubleValue(), rowTableSelected, 2);
                            table.setValueAt(price.doubleValue(), rowTableSelected, 3);
                            table.changeSelection(rowTableSelected, columnTableSelected + 1, false, false);
                        }else {
                            resultTable = null;
                            table.changeSelection (getTableProducts().indexOf(productName),columnTableSelected,false,false);
                        }
                    }
                }else{
                    table.changeSelection(rowTableSelected, columnTableSelected , false, false);
                }
    }

    private void initModeLabel() {
        String sql = "SELECT mode FROM " + operation.getTableName()
                    + " WHERE ida=" + row;
        JDBCAdapter getMode = JDBCAdapter.connect();
        getMode.executeQuery(sql);
        mode = getMode.getValueAt(0, 0).toString();
        modeLabel.setText(mode);
    }

    private Object getNewIdVersement() {
        JDBCAdapter verser = JDBCAdapter.connect();
        String sql_idv = "SELECT (SELECT COALESCE(MAX(IDV),0)+1  FROM VERS" 
                    + f() + ") AS IDV";
        verser.executeQuery(sql_idv);
        idVersement = verser.getValueAt(0, 0);
        return idVersement;
    }
    
    private String f(){
        if (operation.getOperator().equals(Operation.CUSTOMER)){
            f = "C";
        }else if (operation.getOperator().equals(Operation.PROVIDER)){
            f = "F";
        }
        return f;
    }

    private void escapeKeyPressed() {
        Object [] Options = {"Annuler","OK"};
        int n = JOptionPane.showOptionDialog(parentFrame,
                "Voulez vous vraiment quitter sans enregistrer?",
                "Avertissement", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,
                Options,Options[0]);
        if (n == JOptionPane.NO_OPTION){
            dispose();
        }
    }
    
    private RecordPayment getRecordVersement(){
        RecordPayment r = new RecordPayment(
            OPE,                                                    //1
            TAB,                                                    //2
            getNewIdVersement(),                                       //3
            getIdOperator(),                                        //4
            DateAdapter.ConvertDateAdapter(dateLabel.getText().     //5
                substring(0, dateLabel.getText().indexOf('-'))),    //
            dateLabel.getText().substring(dateLabel.getText().      //6
                indexOf('-') + 2, dateLabel.getText().length()),    //
            mode,                                                   //7
            "",                                                     //8
            "",                                                     //9
            versement,                                              //10
            1,                                                      //11
            "",                                                     //12
            getNumero(),                                            //13
            "PC"                                                    //14
            );
        return r;
    }    

    /**
     * inner class that handels list selection events
     */
    class SharedListSelectionHandler implements ListSelectionListener {
        
        @Override
        public void valueChanged(ListSelectionEvent e) {
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();

            int firstIndex = e.getFirstIndex();
            int lastIndex = e.getLastIndex();
            boolean isAdjusting = e.getValueIsAdjusting(); 
            
            if (lsm.isSelectionEmpty()) {
                tableInfoLabel.setText("<none>/" + table.getRowCount());
            } else {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                        tableInfoLabel.setText( (i + 1) + "/" + table.getRowCount());
                    }
                }
            }
        }
    }
}
