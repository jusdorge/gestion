/*
 * la licence de ce projet est accorder 
 * a l'entreprise bbs benhaddou brother's software
 * marque deposer aupr�s des autorit�s responsable * 
 */
package CommerceApp;

import Adapters.JDBCAdapter;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author DELL
 */
public class Importation extends JPanel
                      implements TreeSelectionListener{
    private JTree tree;
    private JList list;
    private JTable table;
    
    public Importation(){
        initComponents();
    }
    private void createNodes(DefaultMutableTreeNode top){
        DefaultMutableTreeNode root = null;
        DefaultMutableTreeNode parent = null;
        DefaultMutableTreeNode child = null;
        JDBCAdapter table = JDBCAdapter.connect();
        JDBCAdapter sub_table = JDBCAdapter.connect();
        table.executeQuery("SHOW DATABASES;");
        for (int i = 0; i < table.getRowCount(); i++){
            String db = table.getValueAt(i,0).toString();
            root = new DefaultMutableTreeNode(db);
            sub_table.executeQuery("use " + db);
            sub_table.executeQuery("SHOW TABLES");
            for (int j = 0; j < sub_table.getRowCount(); j++){
                String tbl = sub_table.getValueAt(j,0).toString();
                parent = new DefaultMutableTreeNode(tbl);
                root.add(parent);
            }
            top.add(root);
        }
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Importation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new Importation());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }    

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                            tree.getLastSelectedPathComponent();
        if (node == null) return;
        DefaultMutableTreeNode nodeParent = (DefaultMutableTreeNode)node.getParent();
        if (nodeParent == null){
            return;
        }
        Object parentInfo = nodeParent.getUserObject();

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()){
            String tbl_name = (String)nodeInfo;
            String base_name = (String)parentInfo;
            String sql = "SELECT * FROM " + base_name + "." + tbl_name;
            JDBCAdapter table_model = JDBCAdapter.connect();
            table_model.executeQuery(sql);
            System.out.println(sql);
            table.setModel(table_model);
            table.repaint();
        }else if(node.isRoot()){
            System.out.println("aucune action a effectuer");
        }
    }

    private void initComponents() {
        //Create the nodes.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("Bases de données");
        createNodes(top);
        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);
        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);
        
        //Create a table ande add it to a scroll pane
        table = new JTable();
        JScrollPane listView = new JScrollPane();
        listView.setViewportView(table);
        
        GroupLayout jPanel1Layout = new GroupLayout(this);
        this.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treeView, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listView, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(treeView, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addComponent(listView, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(106, Short.MAX_VALUE))
        );       
    }
}
