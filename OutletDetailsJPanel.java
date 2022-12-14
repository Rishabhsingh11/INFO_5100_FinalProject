/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Consumer;

import Model.Consumer.OrderMenu;
import Model.Consumer.CommodityOrder;
import Model.Consumer.ProductOrder;
import Model.DB4OUtil.DB4OUtil;
import Model.Business.EcoSystem;
import Model.Consumer.InventoryOrder;
import Model.Enterprise.Commodity;
import Model.Enterprise.Inventory.Inventory;
import Model.Enterprise.Inventory.InventoryItem;
import Model.Enterprise.Restaurant.Menu;
import Model.Enterprise.Restaurant.Restaurant;
import Model.Enterprise.Outlet;
import Model.Enterprise.Outlet.OutletType;
import Model.Enterprise.Mart.Product;
import Model.Enterprise.Mart.Mart;
import Model.Network.Network;
import Model.UserAccount.ConsumerAccount;
import Model.UserAccount.EmployeeAccount;
import Model.WorkQueue.OrderRequest;
import Model.WorkQueue.ReviewRequest;
import Model.WorkQueue.WorkRequest;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author risha
 */
public class OutletDetailsJPanel extends javax.swing.JPanel {

    private EcoSystem system;
    private Outlet outlet;
    private ConsumerAccount consumeracc;
    private EmployeeAccount empaccount;
    private Network network;
    private OutletType outletype;

    /**
     * Creates new form RestaurantDetailsJPanel
     */
    public OutletDetailsJPanel(EcoSystem system, Outlet outlet, ConsumerAccount account, Network net, OutletType type) {
        initComponents();
        this.system = system;
        this.outlet = outlet;
        this.consumeracc = account;
        this.network = net;
        this.outletype = type;

        if (!type.equals(OutletType.Restaurant)) {
            this.outletDetailTab.setTitleAt(1, "Product");
        }

        showImage();
        populateTable();

        if (outlet.getRate() == -1) {
            rateLabel.setText("N/A");
        } else {
            rateLabel.setText(outlet.getRate() + "");
        }
        addressTextArea.setText(outlet.getOut_address());
        addressTextArea.setEnabled(false);
        descriptionTextArea.setText(outlet.getOut_description());
        descriptionTextArea.setEnabled(false);
        phoneLabel.setText(outlet.getOut_phone());

        // Review tab
        populateReviewTable();
    }
    
    public OutletDetailsJPanel(EcoSystem system, Outlet outlet, EmployeeAccount empaccount, Network net, OutletType type) {
        initComponents();
        this.system = system;
        this.outlet = outlet;
        this.empaccount = empaccount;
        this.network = net;
        this.outletype = type;

        if (!type.equals(OutletType.Restaurant)) {
            this.outletDetailTab.setTitleAt(1, "Product");
        }

        showImage();
        populateTable();

        if (outlet.getRate() == -1) {
            rateLabel.setText("N/A");
        } else {
            rateLabel.setText(outlet.getRate() + "");
        }
        addressTextArea.setText(outlet.getOut_address());
        addressTextArea.setEnabled(false);
        descriptionTextArea.setText(outlet.getOut_description());
        descriptionTextArea.setEnabled(false);
        phoneLabel.setText(outlet.getOut_phone());

        // Review tab
        populateReviewTable();
    }

    public void populateReviewTable() {
        DefaultTableModel dtm = (DefaultTableModel) reviewTable.getModel();
        dtm.setRowCount(0);
        for (WorkRequest wr : outlet.getWorkQueue().getWorkRequestDirectory()) {
            OrderRequest or = (OrderRequest) wr;
            if (or.isReviewed()) {
                Object row[] = new Object[4];
                row[0] = or.getReview();
                row[1] = or.getAccount().getUsername();
                row[2] = or.getReview().getRating();
                row[3] = or.getReview().getMessage();
                dtm.addRow(row);
            }

        }
    }

    private void populateTable() {
        DefaultTableModel dtm = (DefaultTableModel) menuTable.getModel();
        dtm.setRowCount(0);
        if (outletype.equals(OutletType.Restaurant)) {
            Restaurant res = (Restaurant) outlet;
            if (res.getCategoryString() != null) {
                categoryLabel.setText(res.getCategoryString());
            }
            
            for (Menu menu : res.getMenu()) {
                Object row[] = new Object[2];
                row[0] = menu;
                row[1] = menu.getCom_price();
                dtm.addRow(row);
            }
        }
        if (outletype.equals(OutletType.Mart)) {
            Mart mart = (Mart) outlet;
            if (mart.getCategoryString() != null) {
                categoryLabel.setText(mart.getCategoryString());
            }
            for (Product p : mart.getGoods()) {
                Object row[] = new Object[2];
                row[0] = p;
                row[1] = p.getCom_price();
                dtm.addRow(row);
            }
        }
        if (outletype.equals(OutletType.Inventory)) {
            Inventory inv = (Inventory) outlet;
            if (inv.getCategoryString() != null) {
                categoryLabel.setText(inv.getCategoryString());
            }
            for (InventoryItem p : inv.getInventoryItem()) {
                Object row[] = new Object[2];
                row[0] = p;
                row[1] = p.getCom_price();
                dtm.addRow(row);
            }
        }
    }

    private void showImage() {
        ImageIcon image = null;
        if (outletype.equals(OutletType.Restaurant)) {
            Restaurant res = (Restaurant) outlet;
            image = new ImageIcon(res.getPath());
        }
        if (outletype.equals(OutletType.Mart)) {
            Mart store = (Mart) outlet;
            image = new ImageIcon(store.getPath());
        }
        if (outletype.equals(OutletType.Inventory)) {
            Inventory inv = (Inventory) outlet;
            image = new ImageIcon(inv.getPath());
        }
        image.setImage(image.getImage().getScaledInstance(250, 180, Image.SCALE_DEFAULT));
        imageLabel.setIcon(image);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        outletDetailTab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        categoryLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        addressTextArea = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rateLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menuTable = new javax.swing.JTable();
        addcartButton = new javax.swing.JButton();
        quantitySpinner = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        reviewTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        commentTextArea = new javax.swing.JTextArea();

        outletDetailTab.setBackground(new java.awt.Color(255, 255, 255));
        outletDetailTab.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel2.setText("Mobile No:");

        phoneLabel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        phoneLabel.setText("<Phone>");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setText("Category: ");

        categoryLabel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        categoryLabel.setText("N/A");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setText("Description: ");

        addressTextArea.setBackground(new java.awt.Color(238, 238, 238));
        addressTextArea.setColumns(20);
        addressTextArea.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        addressTextArea.setLineWrap(true);
        addressTextArea.setBorder(null);
        addressTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(addressTextArea);

        descriptionTextArea.setBackground(new java.awt.Color(238, 238, 238));
        descriptionTextArea.setColumns(20);
        descriptionTextArea.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        descriptionTextArea.setForeground(new java.awt.Color(238, 238, 238));
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setBorder(null);
        descriptionTextArea.setCaretColor(new java.awt.Color(238, 238, 238));
        descriptionTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jScrollPane3.setViewportView(descriptionTextArea);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setText("Address: ");

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel5.setText("Rating :");

        rateLabel.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        rateLabel.setText("<Rate>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(categoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jLabel2)
                            .addComponent(phoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(phoneLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryLabel)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        outletDetailTab.addTab("Details", jPanel1);

        menuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(menuTable);

        addcartButton.setText("Add to Cart");
        addcartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addcartButtonActionPerformed(evt);
            }
        });

        quantitySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(addcartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addcartButton)
                .addContainerGap(246, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        outletDetailTab.addTab("Menu", jPanel2);

        reviewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Date", "Customer", "Rate", "Comment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reviewTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reviewTableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(reviewTable);

        commentTextArea.setColumns(20);
        commentTextArea.setRows(5);
        jScrollPane5.setViewportView(commentTextArea);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        outletDetailTab.addTab("Reviews", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outletDetailTab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outletDetailTab, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addcartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addcartButtonActionPerformed
        int selectedRow = menuTable.getSelectedRow();
        if(consumeracc instanceof ConsumerAccount)
        {
        if (selectedRow >= 0) {
            Commodity item = (Commodity) menuTable.getValueAt(selectedRow, 0);
            int quantity = (int) quantitySpinner.getValue();

            CommodityOrder line = null;
            if (this.outletype.equals(OutletType.Restaurant)) {
                line = new OrderMenu(this.outlet, item, quantity);
            }
            if (this.outletype.equals(OutletType.Mart)) {
                line = new ProductOrder(this.outlet, item, quantity);
            }
            if (!this.consumeracc.getBasket().isBasketEmpty()) {
                for (CommodityOrder or : this.consumeracc.getBasket().getCommodityDirectory()) {
                    if (!or.getShopModel().equals(this.outlet)) {
                        int choice = JOptionPane.showConfirmDialog(null, "You alreay have dashes from other restaurant in shopping cart. \n"
                                + "Adding this dash will remove all previous dashes in shopping cart.\n" + "Do you want to continue?",
                                "Restaurant Conflicts", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            this.consumeracc.getBasket().clearBasket();
                            break;
                        } else {
                            return;
                        }
                    }
                    if (or.getShopModel().equals(this.outlet) && or.getItem().equals(item)) {
                        line.setQuantity(or.getQuantity() + quantity);
                        this.consumeracc.getBasket().getCommodityDirectory().remove(or);
                        break;
                    }
                }
            }
            this.consumeracc.getBasket().addToBasket(line);

            JOptionPane.showMessageDialog(null, "Dash has been successfully added to Shopping Cart");
            DB4OUtil.getInstance().storeSystem(system);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a dash.");
        }
        }
        else
        {
            if (selectedRow >= 0) {
            Commodity item = (Commodity) menuTable.getValueAt(selectedRow, 0);
            int quantity = (int) quantitySpinner.getValue();

            InventoryOrder line = null;
            if (this.outletype.equals(OutletType.Inventory)) {
                line = new InventoryOrder(this.outlet, item, quantity);
            }
            if (!this.empaccount.getBasket().isBasketEmpty()) {
                for (CommodityOrder or : this.empaccount.getBasket().getCommodityDirectory()) {
                    if (!or.getShopModel().equals(this.outlet)) {
                        int choice = JOptionPane.showConfirmDialog(null, "You alreay have dashes from other restaurant in shopping cart. \n"
                                + "Adding this dash will remove all previous dashes in shopping cart.\n" + "Do you want to continue?",
                                "Restaurant Conflicts", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            this.empaccount.getBasket().clearBasket();
                            break;
                        } else {
                            return;
                        }
                    }
                    if (or.getShopModel().equals(this.outlet) && or.getItem().equals(item)) {
                        line.setQuantity(or.getQuantity() + quantity);
                        this.empaccount.getBasket().getCommodityDirectory().remove(or);
                        break;
                    }
                }
            }
            this.empaccount.getBasket().addToBasket(line);

            JOptionPane.showMessageDialog(null, "Dash has been successfully added to Shopping Cart");
            DB4OUtil.getInstance().storeSystem(system);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a dash.");
        }
        }
    }//GEN-LAST:event_addcartButtonActionPerformed

    private void reviewTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reviewTableMouseClicked
        int index = reviewTable.getSelectedRow();

        if (index >= 0) {
            ReviewRequest rr = (ReviewRequest) reviewTable.getValueAt(index, 0);
            commentTextArea.setText(rr.getMessage());
        }
    }//GEN-LAST:event_reviewTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addcartButton;
    private javax.swing.JTextArea addressTextArea;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JTextArea commentTextArea;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable menuTable;
    private javax.swing.JTabbedPane outletDetailTab;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JLabel rateLabel;
    private javax.swing.JTable reviewTable;
    // End of variables declaration//GEN-END:variables
}
