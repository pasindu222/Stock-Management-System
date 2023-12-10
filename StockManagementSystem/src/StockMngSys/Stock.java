
package StockMngSys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;


public class Stock extends javax.swing.JInternalFrame {
        
         Connection conn=null;
        PreparedStatement pst=null;
        ResultSet rs = null;


    
    public Stock() {
        initComponents();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui=(BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        
         conn=DbConnect.connect();
         StockTableLoad();

    }
    public void StockTableLoad(){
    
        
        try {
            String sql = "SELECT Code as 'Item Code',Name as 'Item Name',Bprice as 'Buying Price',sprice as 'Selling Price',Quantity FROM StockTable";
            pst=conn.prepareStatement(sql);
            rs = pst.executeQuery();
            StockTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void StockTableData (){
    
        int r=StockTable.getSelectedRow();
        
        String code=StockTable.getValueAt(r, 0).toString();
        String name=StockTable.getValueAt(r, 1).toString();
        String bprice=StockTable.getValueAt(r, 2).toString();
        String sprice=StockTable.getValueAt(r, 3).toString();
        String quantity=StockTable.getValueAt(r, 4).toString();
        
        codebox.setText(code);
        namebox.setText(name);
        buyingbox.setText(bprice);
        sellingbox.setText(sprice);
        quantitybox.setText(quantity);
    }
    
    public void clear(){
    
        searchbox.setText("");
        codebox.setText("Auto");
        namebox.setText("");
        buyingbox.setText("");
        sellingbox.setText("");
        quantitybox.setText("");
    }
    public void search(){
    
        String srch=searchbox.getText();
        
        try {
            String sql="SELECT * FROM StockTable WHERE Name LIKE '%"+srch+"%' OR Code LIKE '%"+srch+"%'";
            pst=conn.prepareStatement(sql);
            rs = pst.executeQuery();
            StockTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void update(){
        
        int check=JOptionPane.showConfirmDialog(null, "Are you shuwar to apply this changes");
        if(check==0){
        String code = codebox.getText();
        String name = namebox.getText();
        String bprice = buyingbox.getText();
        String sprice = sellingbox.getText();
        String quantity = quantitybox.getText();
        
        try {
            String sql = "UPDATE StockTable SET Name='"+name+"',Bprice='"+bprice+"',Sprice='"+sprice+"',Quantity='"+quantity+"' WHERE Code='"+code+"'";
            pst=conn.prepareStatement(sql);
            pst.execute();
            
            JOptionPane.showMessageDialog(null, "Data Sccessfully Updated!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }
    public void delete(){
        int check=JOptionPane.showConfirmDialog(null, "Are you shuwar to delete this data?");
        
        String code=codebox.getText();
        if(check==0){
        
            try {
                String sql="DELETE FROM StockTable WHERE Code='"+code+"'";
                pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data was successfully deleted!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        StockTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        codebox = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        namebox = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        quantitybox = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        sellingbox = new javax.swing.JTextField();
        buyingbox = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        searchbox = new javax.swing.JTextField();
        addbt = new javax.swing.JButton();
        deletebt = new javax.swing.JButton();
        updatebt = new javax.swing.JButton();
        clearbt = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 204));

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));

        jLabel1.setFont(new java.awt.Font("MS UI Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 153));
        jLabel1.setText("Stock");

        StockTable.setModel(new javax.swing.table.DefaultTableModel(
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
        StockTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StockTableMouseClicked(evt);
            }
        });
        StockTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                StockTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(StockTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(473, 473, 473)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 153));
        jLabel2.setText("Item Code");

        codebox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        codebox.setText("Auto");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText("Item Name");

        namebox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 153));
        jLabel5.setText("Quantity");

        quantitybox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 153));
        jLabel6.setText("Buying Price");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setText("Selling Price");

        sellingbox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        buyingbox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Search", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        searchbox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchboxKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchbox, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(searchbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        addbt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        addbt.setForeground(new java.awt.Color(0, 0, 153));
        addbt.setText("Add");
        addbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtActionPerformed(evt);
            }
        });

        deletebt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deletebt.setForeground(new java.awt.Color(0, 0, 153));
        deletebt.setText("Delete");
        deletebt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtActionPerformed(evt);
            }
        });

        updatebt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        updatebt.setForeground(new java.awt.Color(0, 0, 153));
        updatebt.setText("Update");
        updatebt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtActionPerformed(evt);
            }
        });

        clearbt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        clearbt.setForeground(new java.awt.Color(0, 0, 153));
        clearbt.setText("Clear");
        clearbt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addComponent(codebox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel4)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(namebox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantitybox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buyingbox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sellingbox, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(93, 93, 93)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deletebt, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(addbt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updatebt, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(clearbt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(325, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(addbt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updatebt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deletebt, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clearbt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(codebox)
                                    .addComponent(jLabel2)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(namebox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(quantitybox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buyingbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sellingbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))))))
                .addContainerGap(155, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deletebtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtActionPerformed
        // TODO add your handling code here:
        delete();
        StockTableLoad();
    }//GEN-LAST:event_deletebtActionPerformed

    private void addbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtActionPerformed
        // TODO add your handling code here:
        
        String name=namebox.getText();
        String quantity=quantitybox.getText();
        String buyingprice=buyingbox.getText();
        String sellingprice=sellingbox.getText();
        
        try {
            String sql ="INSERT INTO StockTable(Name,Bprice,Sprice,Quantity)VALUES('"+name+"','"+buyingprice+"','"+sellingprice+"','"+quantity+"')";
            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Inserted Successfully!");
            StockTableLoad();
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_addbtActionPerformed

    private void StockTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StockTableMouseClicked
        // TODO add your handling code here:
        StockTableData();
    }//GEN-LAST:event_StockTableMouseClicked

    private void StockTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StockTableKeyReleased
        // TODO add your handling code here:
        StockTableData();
    }//GEN-LAST:event_StockTableKeyReleased

    private void clearbtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtActionPerformed
        // TODO add your handling code here:
        clear();
        StockTableLoad();
    }//GEN-LAST:event_clearbtActionPerformed

    private void searchboxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchboxKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_searchboxKeyReleased

    private void updatebtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtActionPerformed
        // TODO add your handling code here:
        
        update();
        StockTableLoad();
    }//GEN-LAST:event_updatebtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable StockTable;
    private javax.swing.JButton addbt;
    private javax.swing.JTextField buyingbox;
    private javax.swing.JButton clearbt;
    private javax.swing.JLabel codebox;
    private javax.swing.JButton deletebt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField namebox;
    private javax.swing.JTextField quantitybox;
    private javax.swing.JTextField searchbox;
    private javax.swing.JTextField sellingbox;
    private javax.swing.JButton updatebt;
    // End of variables declaration//GEN-END:variables
}
