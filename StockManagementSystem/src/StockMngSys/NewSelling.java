
package StockMngSys;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.proteanit.sql.DbUtils;

public class NewSelling extends javax.swing.JInternalFrame {
    
    
    Connection conn =null;
    PreparedStatement pst =null;
    ResultSet rs = null;
    ResultSet qt = null;

 
    public NewSelling() {
        initComponents();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0,0,0,0));
        BasicInternalFrameUI ui=(BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
         conn=DbConnect.connect();
        SellStockTable();
        SellListTableLoad();
    }

     public void SellStockTable(){
        try {
            String sql = "SELECT Code,Name,Quantity FROM StockTable ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            PartsTable.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
//     public void ReduceQuantity(){
//         int r = PartsTable.getSelectedRow();
//         
//         String id = PartsTable.getValueAt(r, 0).toString();
//         String availablequantity = PartsTable.getValueAt(r, 2).toString();
//        String sellquantity = QuantityBox.getText();
//         
//         int idnum = Integer.parseInt(id);
//         int avaqunnum = Integer.parseInt(availablequantity);
//         int sellquntnum = Integer.parseInt(sellquantity);
//         
//         int result = (avaqunnum-sellquntnum);
//         JOptionPane.showMessageDialog(null, "result is: " + result);
//       
//     }
     public void SellTableData(){
        int r = PartsTable.getSelectedRow();
        
        String name=PartsTable.getValueAt(r, 1).toString();
        String code=PartsTable.getValueAt(r, 0).toString();
        String qnt=PartsTable.getValueAt(r, 2).toString();
  
        NameBox.setText(name);
        CodeBox.setText(code);
        DiscountBox.setText(qnt);
        
     } 
     
      public void SellListTableLoad(){
        try {
            String sql = "SELECT code,name,price,discount,quantity,total FROM SellListTable ";
            pst = conn.prepareStatement(sql);
            qt = pst.executeQuery();
            SellingTable.setModel(DbUtils.resultSetToTableModel(qt));
        } catch (Exception e) {
        }
      }
     public void SellListAdd (){
        String code=CodeBox.getText();
        String name=NameBox.getText();
        int price = Integer.parseInt(PriceBox.getText());
        int discount = Integer.parseInt(DiscountBox.getText());
        int quantity = Integer.parseInt(QuantityBox.getText());
        int total=((price-discount)*quantity);
        //Integer.parseInt(agebox.getText());
        int r = PartsTable.getSelectedRow();
        String id = PartsTable.getValueAt(r, 0).toString();
        String availablequantity = PartsTable.getValueAt(r, 2).toString();
         int idnum = Integer.parseInt(id);
         int avaqunnum = Integer.parseInt(availablequantity);
         
         int result = (avaqunnum - quantity);
         JOptionPane.showMessageDialog(null, "Number is: "+result);
        
        try {
            String sql ="INSERT INTO SellListTable (code,name,price,discount,quantity,total)VALUES ('"+code+"','"+name+"','"+price+"','"+discount+"','"+quantity+"','"+total+"')";
            pst=conn.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
         try {
             String sql = "UPDATE StockTable SET Quantity='"+result+"' WHERE Code='"+idnum+"'";
             pst=conn.prepareStatement(sql);
             pst.execute();
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e);
         }
    }
     
     public void SellSearch(){
        String srch = SearchBox.getText().toString();
        try {
            String sql = "SELECT * FROM StockTable WHERE Name LIKE '%"+srch+"%'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            PartsTable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
     
      public void delete(){
        int check=JOptionPane.showConfirmDialog(null, "Are you shuwar to delete this data?");
        
        int r=SellingTable.getSelectedRow();
        String code=SellingTable.getValueAt(r, 0).toString();
        if(check==0){
        
            try {
                String sql="DELETE FROM SellListTable WHERE Code='"+code+"'";
                pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data was successfully deleted!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
      }
      public void SellTableClear(){
    
        CodeBox.setText("");
        NameBox.setText("");
        PriceBox.setText("");
        DiscountBox.setText("");
        QuantityBox.setText("");
        SearchBox.setText("");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        PartsTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        SellingTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        SearchBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CodeBox = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        NameBox = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        PriceBox = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        DiscountBox = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        QuantityBox = new javax.swing.JTextField();
        ClearBt = new javax.swing.JButton();
        AddBt = new javax.swing.JButton();
        RemoveBt = new javax.swing.JButton();
        SellBt = new javax.swing.JButton();

        setBackground(new java.awt.Color(20, 240, 240));
        setForeground(java.awt.Color.blue);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setText("Parts");

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel2.setText("Selling");

        PartsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        PartsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PartsTableMouseClicked(evt);
            }
        });
        PartsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PartsTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(PartsTable);

        SellingTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(SellingTable);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Search");

        SearchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchBoxKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Item Code");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setText("Item Name");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setText("Price");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setText("Discount");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setText("Quantity");

        ClearBt.setText("Cear");
        ClearBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearBtActionPerformed(evt);
            }
        });

        AddBt.setText("Add");
        AddBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBtActionPerformed(evt);
            }
        });

        RemoveBt.setText("Remove");
        RemoveBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveBtActionPerformed(evt);
            }
        });

        SellBt.setText("Sell");
        SellBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SellBtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(ClearBt, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(AddBt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(199, 199, 199)
                        .addComponent(RemoveBt, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121)
                        .addComponent(SellBt, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel1)
                        .addGap(481, 481, 481)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(QuantityBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DiscountBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(PriceBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(SearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(52, 52, 52)
                                    .addComponent(NameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(116, 116, 116)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(370, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(SearchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CodeBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NameBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PriceBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DiscountBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(QuantityBox, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClearBt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(AddBt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RemoveBt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SellBt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(267, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBtActionPerformed
        // TODO add your handling code here:
        SellListAdd();
        SellListTableLoad();
        SellTableClear();
        //ReduceQuantity();
    }//GEN-LAST:event_AddBtActionPerformed

    private void RemoveBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveBtActionPerformed
        // TODO add your handling code here:
        delete();
        SellListTableLoad();
    }//GEN-LAST:event_RemoveBtActionPerformed

    private void ClearBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearBtActionPerformed
        // TODO add your handling code here:
        SellTableClear();
        SellListTableLoad();
    }//GEN-LAST:event_ClearBtActionPerformed

    private void PartsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PartsTableMouseClicked
        // TODO add your handling code here:
        SellTableData();
    }//GEN-LAST:event_PartsTableMouseClicked

    private void PartsTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PartsTableKeyReleased
        // TODO add your handling code here:
        SellTableData();
    }//GEN-LAST:event_PartsTableKeyReleased

    private void SearchBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchBoxKeyReleased
        // TODO add your handling code here:
        SellSearch();
    }//GEN-LAST:event_SearchBoxKeyReleased

    private void SellBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SellBtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SellBtActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddBt;
    private javax.swing.JButton ClearBt;
    private javax.swing.JTextField CodeBox;
    private javax.swing.JTextField DiscountBox;
    private javax.swing.JTextField NameBox;
    private javax.swing.JTable PartsTable;
    private javax.swing.JTextField PriceBox;
    private javax.swing.JTextField QuantityBox;
    private javax.swing.JButton RemoveBt;
    private javax.swing.JTextField SearchBox;
    private javax.swing.JButton SellBt;
    private javax.swing.JTable SellingTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
