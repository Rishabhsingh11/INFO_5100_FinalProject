/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Inventory;

import Model.Consumer.InventoryOrder;
import Model.Consumer.CommodityOrder;
import Model.DB4OUtil.DB4OUtil;
import Model.Business.EcoSystem;
import Model.Employee.Employee;
import Model.Enterprise.Enterprise;
import Model.Enterprise.Commodity;
import Model.Enterprise.Inventory.InventoryItem;
import Model.Enterprise.Inventory.Inventory;
import Model.Enterprise.Inventory.Inventory.InventoryType;
import Model.Enterprise.Restaurant.Restaurant.RestaurantCategory;
import Model.Network.Network;
import Model.Organization.Organization;
import Model.Role.Role;
import Model.Role.Role.RoleType;
import Model.UserAccount.EmployeeAccount;
import Model.UserAccount.UserAccount;
import Model.WorkQueue.InventoryItemRequest;
import Model.WorkQueue.ReviewRequest;
import Model.WorkQueue.WorkRequest;
import Model.WorkQueue.WorkRequest.StatusEnum;
import UserInterface.SignInJFrame;
import UserInterface.Employee.CreateEmployeeJPanel;
import UserInterface.Employee.EditEmployeeJPanel;
import UserInterface.RestaurantSupervisor.SelectShipmentJFrame;
import java.awt.CardLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.EnumSet;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rutu
 */
public class InventoryManagerMainJPanel extends javax.swing.JPanel {

    private EcoSystem system;
    private JPanel panel;
    private Network network;
    private Enterprise enterprise;
    private EmployeeAccount employeeAccount;
    private Inventory inventory;
    private JFrame frame;
    private Role accessRole;
    private String path;
    private String originPath;
    private InventoryItemRequest itemRequest = null;
    private Employee employee;

    /**
     * Creates new form RestaurantMainJPanel
     */
    public InventoryManagerMainJPanel(EcoSystem system, JPanel panel, Network network, Enterprise enterprise,
            UserAccount userAccount, JFrame frame, Role accessRole) {
        initComponents();
        this.system = system;
        this.panel = panel;
        this.network = network;
        this.enterprise = enterprise;
        this.employeeAccount = (EmployeeAccount) userAccount;
        this.frame = frame;
        this.accessRole = accessRole;
        this.inventory = (Inventory) enterprise;
        this.originPath = this.inventory.getPath();
        this.path = this.inventory.getPath();
        this.employee = this.employeeAccount.getEmployee();

        if (accessRole.getRoleType().equals(RoleType.SystemAdmin)) {
            logoutButton.setVisible(false);
            jLabel5.setVisible(false);
            passwordField2.setVisible(false);
            jLabel13.setVisible(false);
            inventoryTabbed.removeTabAt(4);
        }

        populateInventoryItemTable();
        populateRequestTable();
        populateEmployeeTable(inventory.getOrganizationDirectory().getOrganizationDirectory());
        

        if (accessRole.getRoleType().equals(RoleType.InventoryManager)) {
            editButton.setVisible(false);
            saveButton.setVisible(false);
            uploadButton.setVisible(false);
            cancelButton.setVisible(false);
        }

        for (InventoryType c : EnumSet.allOf(InventoryType.class)) {
            categoryComboBox.addItem(c);
      }

        // Overview Panel
        editButton.setEnabled(true);
        saveButton.setEnabled(false);
        uploadButton.setEnabled(false);
        cancelButton.setEnabled(false);
        setOverviewFieldsEditable(false);
        setOverviewInfo();

        // Profile panel
        saveButton1.setEnabled(false);
        cancelButton2.setEnabled(false);
        editButton1.setEnabled(true);
        setProfileInfo();
        setProfileFieldsEditable(false);

        // Order Panel
        compayTextField.setEnabled(false);
        totalTextField.setEnabled(false);
        commentTextArea.setEnabled(false);
        deliveryButton.setEnabled(false);
        cancelOrderButton.setEnabled(false);

        // Review Panel
        
    }
    

    public void populateInventoryItemTable() 
    {
        DefaultTableModel dtm = (DefaultTableModel) inventoryItemTable.getModel();
        dtm.setRowCount(0);
        for (InventoryItem inventoryitem : inventory.getInventoryItem()) {
            Object row[] = new Object[2];
            row[0] = inventoryitem;
            row[1] = inventoryitem.getCom_price();
            dtm.addRow(row);
        }
        
        jButton2.setEnabled(false);
    }


    public void populateRequestTable() {
        DefaultTableModel dtm = (DefaultTableModel) requestTable.getModel();
        dtm.setRowCount(0);
        for (WorkRequest wr : inventory.getWorkQueue().getWorkRequestDirectory()) {
            InventoryItemRequest ir = (InventoryItemRequest) wr;
            Object row[] = new Object[5];
            row[0] = ir;
            row[1] = ir.getDeliveryName();
            row[2] = ir.getDeliveryPhone();
            row[3] = ir.getAmount();
            row[4] = ir.getStatus();
            dtm.addRow(row);
        }
    }

    public void populateDetailTable(InventoryItemRequest inventoryreq) {
        DefaultTableModel dtm = (DefaultTableModel) orderDetailTable.getModel();
        dtm.setRowCount(0);
        ArrayList<CommodityOrder> list = inventoryreq.getInventorOrder();
        for (CommodityOrder d : list) {
            Object row[] = new Object[5];
            row[0] = d;
            row[1] = d.getQuantity();
            dtm.addRow(row);
        }
        commentTextArea.setText(inventoryreq.getMessage());
        totalTextField.setText(inventoryreq.getAmount() + "");
        if (inventoryreq.getStatus().equals(StatusEnum.Processing)) {
            deliveryButton.setEnabled(true);
            cancelOrderButton.setEnabled(true);
        } else {
            deliveryButton.setEnabled(false);
            cancelOrderButton.setEnabled(false);
        }

        if (itemRequest.getCompany() == null) {
            compayTextField.setText("");
        } else {
            compayTextField.setText(itemRequest.getCompany().getOrg_name());
        }
    }

    private void setProfileFieldsEditable(boolean b) {
        emailTextField.setEnabled(b);
        firstNameTextField.setEnabled(b);
        lastNameTextField.setEnabled(b);
        phoneTextField1.setEnabled(b);
    }

    private void setOverviewFieldsEditable(boolean b) {
        nameTextField.setEnabled(b);
        phoneTextField.setEnabled(b);
        addressTextArea.setEnabled(b);
        descriptionTextArea.setEnabled(b);
        categoryComboBox.setEnabled(b);
    }

    private void setOverviewInfo() {
        nameLabel.setText(inventory.getOrg_name());
        nameTextField.setText(inventory.getOrg_name());
        phoneTextField.setText(inventory.getOut_phone());
        addressTextArea.setText(inventory.getOut_address());
        descriptionTextArea.setText(inventory.getOut_description());
        categoryComboBox.setSelectedItem(inventory.getInventype());
        ImageIcon image = new ImageIcon(originPath);
        image.setImage(image.getImage().getScaledInstance(250, 180, Image.SCALE_DEFAULT));
        imageLabel.setIcon(image);
    }

    private void setProfileInfo() {
        roleTextField.setText(this.employeeAccount.getRole().getRoleType().getValue());
        nameLabel.setText(employee.getFirstname());
        emailTextField.setText(employee.getEmail_id());
        firstNameTextField.setText(employee.getFirstname());
        lastNameTextField.setText(employee.getLastname());
        phoneTextField1.setText(employee.getMobile_no());
        usernameTextField.setText(employeeAccount.getUsername());
    }

    private void resetPasswordField() {
        passwordField.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        inventoryTabbed = new javax.swing.JTabbedPane();
        overviewPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        uploadButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        phoneTextField = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        addressTextArea = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        descriptionTextArea = new javax.swing.JTextArea();
        categoryComboBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        menuPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inventoryItemTable = new javax.swing.JTable();
        detailPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        createPanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        ordersPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        requestTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        orderDetailTable = new javax.swing.JTable();
        totalTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        deliveryButton = new javax.swing.JButton();
        cancelOrderButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        commentTextArea = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        compayTextField = new javax.swing.JTextField();
        employeePanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        workPanel = new javax.swing.JPanel();
        createButton1 = new javax.swing.JButton();
        profilePanel = new javax.swing.JPanel();
        roleTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cancelButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        saveButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        editButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        phoneTextField1 = new javax.swing.JTextField();
        usernameTextField = new javax.swing.JTextField();
        passwordPanel = new javax.swing.JPanel();
        passwordField1 = new javax.swing.JPasswordField();
        passwordField2 = new javax.swing.JPasswordField();
        cancelButton1 = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        submitButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();

        inventoryTabbed.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                inventoryTabbedStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel2.setText("Phone:");

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel3.setText("Category: ");

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel4.setText("Description: ");

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel1.setText("Address: ");

        uploadButton.setText("Upload Photo");
        uploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        editButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        saveButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        phoneTextField.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        addressTextArea.setColumns(20);
        jScrollPane4.setViewportView(addressTextArea);

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setLineWrap(true);
        jScrollPane5.setViewportView(descriptionTextArea);

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setText("Name:");

        nameTextField.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N

        javax.swing.GroupLayout overviewPanelLayout = new javax.swing.GroupLayout(overviewPanel);
        overviewPanel.setLayout(overviewPanelLayout);
        overviewPanelLayout.setHorizontalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPanelLayout.createSequentialGroup()
                .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(overviewPanelLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(overviewPanelLayout.createSequentialGroup()
                                .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(overviewPanelLayout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addComponent(uploadButton)))
                                .addGap(18, 18, 18)
                                .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addGroup(overviewPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(categoryComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(overviewPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(overviewPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(overviewPanelLayout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        overviewPanelLayout.setVerticalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overviewPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(overviewPanelLayout.createSequentialGroup()
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(uploadButton))
                    .addGroup(overviewPanelLayout.createSequentialGroup()
                        .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addGap(36, 36, 36))
        );

        inventoryTabbed.addTab("Overview", overviewPanel);

        inventoryItemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Item", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inventoryItemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inventoryItemTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(inventoryItemTable);

        detailPanel.setLayout(new java.awt.CardLayout());

        jButton1.setText("Insert Item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        createPanel.setLayout(new java.awt.CardLayout());

        jButton2.setText("Remove Item");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(detailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addComponent(detailPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );

        inventoryTabbed.addTab("Manage Inventory", menuPanel);

        requestTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Request Date", "Name", "Phone", "Amount", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        requestTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                requestTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(requestTable);

        orderDetailTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Dash", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(orderDetailTable);

        totalTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        totalTextField.setText("0.00");
        totalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTextFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel6.setText("Total: ");

        deliveryButton.setText("Confirm Order");
        deliveryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveryButtonActionPerformed(evt);
            }
        });

        cancelOrderButton.setText("Cancel Order");
        cancelOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelOrderButtonActionPerformed(evt);
            }
        });

        commentTextArea.setColumns(20);
        jScrollPane6.setViewportView(commentTextArea);

        jLabel18.setText("Delivery Company: ");

        compayTextField.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        compayTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout ordersPanelLayout = new javax.swing.GroupLayout(ordersPanel);
        ordersPanel.setLayout(ordersPanelLayout);
        ordersPanelLayout.setHorizontalGroup(
            ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ordersPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ordersPanelLayout.createSequentialGroup()
                        .addGroup(ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(deliveryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cancelOrderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel18)
                    .addComponent(compayTextField))
                .addGap(17, 17, 17))
        );
        ordersPanelLayout.setVerticalGroup(
            ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ordersPanelLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(ordersPanelLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(compayTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cancelOrderButton)
                                .addComponent(deliveryButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ordersPanelLayout.createSequentialGroup()
                                .addGroup(ordersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        inventoryTabbed.addTab("Manage Request", ordersPanel);

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Username", "Role", "Name", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        employeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeTableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(employeeTable);

        workPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        workPanel.setLayout(new java.awt.CardLayout());

        createButton1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        createButton1.setText("Create Employee");
        createButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout employeePanelLayout = new javax.swing.GroupLayout(employeePanel);
        employeePanel.setLayout(employeePanelLayout);
        employeePanelLayout.setHorizontalGroup(
            employeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeePanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(employeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(createButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(workPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        employeePanelLayout.setVerticalGroup(
            employeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeePanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(employeePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(employeePanelLayout.createSequentialGroup()
                        .addComponent(createButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(workPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        inventoryTabbed.addTab("Manage Employee", employeePanel);

        roleTextField.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Role:");

        cancelButton2.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        cancelButton2.setText("Cancel");
        cancelButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Username: ");

        saveButton1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        saveButton1.setText("Save");
        saveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Phone:");

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel9.setText("Email:");

        editButton1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        editButton1.setText("Edit");
        editButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButton1ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel12.setText("First Name: ");

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel16.setText("Last Name: ");

        usernameTextField.setEnabled(false);
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout profilePanelLayout = new javax.swing.GroupLayout(profilePanel);
        profilePanel.setLayout(profilePanelLayout);
        profilePanelLayout.setHorizontalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addContainerGap(299, Short.MAX_VALUE)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profilePanelLayout.createSequentialGroup()
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(profilePanelLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(profilePanelLayout.createSequentialGroup()
                                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(354, 354, 354))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, profilePanelLayout.createSequentialGroup()
                        .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(profilePanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(profilePanelLayout.createSequentialGroup()
                                .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(285, 285, 285))))
        );
        profilePanelLayout.setVerticalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(profilePanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(roleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(12, 12, 12)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(phoneTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton1)
                    .addComponent(saveButton1)
                    .addComponent(cancelButton2))
                .addContainerGap(200, Short.MAX_VALUE))
        );

        inventoryTabbed.addTab("My Profile", profilePanel);

        cancelButton1.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        cancelButton1.setText("Cancel");
        cancelButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButton1ActionPerformed(evt);
            }
        });

        submitButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel15.setText("Confirm Password:");

        jLabel14.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel14.setText("New Password:");

        jLabel13.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel13.setText("Old Password:");

        javax.swing.GroupLayout passwordPanelLayout = new javax.swing.GroupLayout(passwordPanel);
        passwordPanel.setLayout(passwordPanelLayout);
        passwordPanelLayout.setHorizontalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordPanelLayout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(passwordPanelLayout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(passwordPanelLayout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(passwordPanelLayout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(passwordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(passwordPanelLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(294, Short.MAX_VALUE))
        );
        passwordPanelLayout.setVerticalGroup(
            passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(passwordPanelLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(passwordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(passwordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitButton)
                    .addComponent(cancelButton1))
                .addContainerGap(275, Short.MAX_VALUE))
        );

        inventoryTabbed.addTab("Change Password", passwordPanel);

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel5.setText("Welcome, ");

        nameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        nameLabel.setText("<Name>");

        logoutButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inventoryTabbed)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel)
                .addGap(23, 23, 23)
                .addComponent(logoutButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inventoryTabbed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
       SignInJFrame lf = new SignInJFrame();
        this.frame.dispose();
        lf.setLocationRelativeTo(null);
        lf.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setOverviewFieldsEditable(false);
        setOverviewInfo();

        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        editButton.setEnabled(true);
        uploadButton.setEnabled(false);
        categoryComboBox.setEnabled(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        saveButton.setEnabled(true);
        cancelButton.setEnabled(true);
        editButton.setEnabled(false);
        uploadButton.setEnabled(true);
        categoryComboBox.setEnabled(true);

        setOverviewFieldsEditable(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (!phoneTextField.getText().equals("") && !addressTextArea.getText().equals("")
                && !descriptionTextArea.getText().equals("") && !nameTextField.getText().equals("")) {
            inventory.setOrg_name(nameTextField.getText());
            inventory.setOut_address(addressTextArea.getText());
            inventory.setOut_description(descriptionTextArea.getText());
            inventory.setInventype((InventoryType) categoryComboBox.getSelectedItem());
            inventory.setOut_phone(phoneTextField.getText());
        
            if (!path.equalsIgnoreCase(originPath)) {
                inventory.setPath(path);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Information can't be empty");
            return;
        }
        setOverviewFieldsEditable(false);
        setOverviewInfo();
        ImageIcon image = new ImageIcon(path);
        image.setImage(image.getImage().getScaledInstance(250, 180, Image.SCALE_DEFAULT));
        imageLabel.setIcon(image);
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        editButton.setEnabled(true);
        uploadButton.setEnabled(false);
        categoryComboBox.setEnabled(false);

        DB4OUtil.getInstance().storeSystem(system);
    }//GEN-LAST:event_saveButtonActionPerformed

    
    public void populateEmployeeTable(ArrayList<Organization> list) {
        ArrayList<EmployeeAccount> result = new ArrayList<>();

        result.addAll(this.enterprise.getUserAccountDirectory().toEmployeeAccounts());

        for (Organization org : list) {
            result.addAll(org.getUserAccountDirectory().toEmployeeAccounts());
        }

        DefaultTableModel dtm = (DefaultTableModel) employeeTable.getModel();
        dtm.setRowCount(0);
        for (EmployeeAccount e : result) {
            Object row[] = new Object[4];
            row[0] = e;
            row[1] = e.getRole();
            row[2] = e.getEmployee().getFullname();
            row[3] = e.getEmployee().getEmail_id();
            dtm.addRow(row);
        }
    }
    private void uploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = chooser.showOpenDialog(null);
        if (i == chooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().getAbsolutePath();
            inventory.setPath(path);
        } else {
            JOptionPane.showMessageDialog(null, "No file was selected");
        }
        ImageIcon image = new ImageIcon(path);
        image.setImage(image.getImage().getScaledInstance(250, 180, Image.SCALE_DEFAULT));
        imageLabel.setIcon(image);
    }//GEN-LAST:event_uploadButtonActionPerformed

    private void inventoryTabbedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_inventoryTabbedStateChanged
        int index = inventoryTabbed.getSelectedIndex();
        if (index != 0 && index != -1) {
            setOverviewInfo();
            setProfileInfo();
        }
        setProfileFieldsEditable(false);
        setOverviewFieldsEditable(false);
        resetPasswordField();

        uploadButton.setEnabled(false);
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        editButton.setEnabled(true);
    }//GEN-LAST:event_inventoryTabbedStateChanged

    private void cancelButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButton1ActionPerformed
        resetPasswordField();
    }//GEN-LAST:event_cancelButton1ActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        char[] passwordCharArray = passwordField2.getPassword();
        String password = String.valueOf(passwordCharArray);
        char[] passwordCharArray1 = passwordField.getPassword();
        String new1 = String.valueOf(passwordCharArray1);
        char[] passwordCharArray2 = passwordField1.getPassword();
        String new2 = String.valueOf(passwordCharArray2);

        if (password.equals(employeeAccount.getPassword())) {
            if (!new1.equals("")) {
                if (new1.equals(new2)) {
                    employeeAccount.setPassword(new1);
                    JOptionPane.showMessageDialog(null, "Password updated successfully!");
                    DB4OUtil.getInstance().storeSystem(system);
                    resetPasswordField();
                } else {
                    JOptionPane.showMessageDialog(null, "Passwords don't match!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Password can't be empty!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Password is not correct!");
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void inventoryItemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inventoryItemTableMouseClicked
        int index = inventoryItemTable.getSelectedRow();

        if (index >= 0) {
            InventoryItem inven = (InventoryItem) inventoryItemTable.getValueAt(index, 0);
            InventoryItemEditJPanel panel = new InventoryItemEditJPanel(this.system, this, this.detailPanel, inven);
            this.detailPanel.add(panel);
            CardLayout layout = (CardLayout) this.detailPanel.getLayout();
            layout.next(this.detailPanel);
            
            jButton2.setEnabled(true);
        }
    }//GEN-LAST:event_inventoryItemTableMouseClicked

    private void totalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalTextFieldActionPerformed

    }//GEN-LAST:event_totalTextFieldActionPerformed

    private void requestTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_requestTableMouseClicked
        int index = requestTable.getSelectedRow();

        if (index >= 0) {
            itemRequest = (InventoryItemRequest) requestTable.getValueAt(index, 0);
            populateDetailTable(itemRequest);
        }
    }//GEN-LAST:event_requestTableMouseClicked

    private void cancelOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelOrderButtonActionPerformed
        itemRequest.setStatus(StatusEnum.Cancelled);
        DB4OUtil.getInstance().storeSystem(system);
        populateRequestTable();
        populateDetailTable(itemRequest);
    }//GEN-LAST:event_cancelOrderButtonActionPerformed

    private void deliveryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryButtonActionPerformed
        SelectInventoryDeliveryJFrame f = new SelectInventoryDeliveryJFrame(this.system, this, this.network, this.inventory, this.itemRequest);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }//GEN-LAST:event_deliveryButtonActionPerformed

    private void cancelButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButton2ActionPerformed
        setProfileFieldsEditable(false);
        setProfileInfo();

        saveButton1.setEnabled(false);
        cancelButton2.setEnabled(false);
        editButton1.setEnabled(true);
    }//GEN-LAST:event_cancelButton2ActionPerformed

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        if (!emailTextField.getText().equals("") && !firstNameTextField.getText().equals("")
                && !lastNameTextField.getText().equals("") && !phoneTextField1.getText().equals("")) {
            this.employee.setEmail_id(emailTextField.getText());
            this.employee.setFirstname(firstNameTextField.getText());
            this.employee.setLastname(lastNameTextField.getText());
            this.employee.setMobile_no(phoneTextField1.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Information can't be empty");
            return;
        }
        setProfileFieldsEditable(false);
        saveButton1.setEnabled(false);
        cancelButton2.setEnabled(false);
        editButton1.setEnabled(true);

        DB4OUtil.getInstance().storeSystem(system);
    }//GEN-LAST:event_saveButton1ActionPerformed

    private void editButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButton1ActionPerformed
        saveButton1.setEnabled(true);
        cancelButton2.setEnabled(true);
        editButton1.setEnabled(false);

        setProfileFieldsEditable(true);
    }//GEN-LAST:event_editButton1ActionPerformed

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        InventoryCreateJPanel p = new InventoryCreateJPanel(system, this, createPanel, this.inventory);
        this.createPanel.add(p);
        CardLayout layout = (CardLayout) createPanel.getLayout();
        layout.next(createPanel);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int index = inventoryItemTable.getSelectedRow();

        if (index >= 0) {
            Inventory res = (Inventory) this.enterprise;
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure to remove this dash from the system?");
            if (choice == 0) {
                ArrayList<Commodity> result = res.getCommodity();
                result.remove(index);
                res.setCommodity(result);
                DB4OUtil.getInstance().storeSystem(system);
                
                populateInventoryItemTable();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void employeeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeTableMouseClicked
        int index = employeeTable.getSelectedRow();

        if (index >= 0) {
            EmployeeAccount selectedAccount = (EmployeeAccount) employeeTable.getValueAt(index, 0);

            EditEmployeeJPanel ep = new EditEmployeeJPanel(this.system, this, this.workPanel, this.enterprise, selectedAccount, this.employeeAccount, accessRole);
            this.workPanel.removeAll();
            this.workPanel.add(ep);
            CardLayout layout = (CardLayout) this.workPanel.getLayout();
            layout.next(this.workPanel);
        }
    }//GEN-LAST:event_employeeTableMouseClicked

    private void createButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButton1ActionPerformed
        this.workPanel.removeAll();
        CreateEmployeeJPanel ep = new CreateEmployeeJPanel(this.system, this, this.workPanel, this.enterprise, this.accessRole);
        this.workPanel.add(ep);
        CardLayout layout = (CardLayout) this.workPanel.getLayout();
        layout.next(this.workPanel);
    }//GEN-LAST:event_createButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addressTextArea;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton cancelButton1;
    private javax.swing.JButton cancelButton2;
    private javax.swing.JButton cancelOrderButton;
    private javax.swing.JComboBox<InventoryType> categoryComboBox;
    private javax.swing.JTextArea commentTextArea;
    private javax.swing.JTextField compayTextField;
    private javax.swing.JButton createButton1;
    private javax.swing.JPanel createPanel;
    private javax.swing.JButton deliveryButton;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JPanel detailPanel;
    private javax.swing.JButton editButton;
    private javax.swing.JButton editButton1;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JPanel employeePanel;
    private javax.swing.JTable employeeTable;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JTable inventoryItemTable;
    private javax.swing.JTabbedPane inventoryTabbed;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTable orderDetailTable;
    private javax.swing.JPanel ordersPanel;
    private javax.swing.JPanel overviewPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField passwordField1;
    private javax.swing.JPasswordField passwordField2;
    private javax.swing.JPanel passwordPanel;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JTextField phoneTextField1;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JTable requestTable;
    private javax.swing.JTextField roleTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton saveButton1;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField totalTextField;
    private javax.swing.JButton uploadButton;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JPanel workPanel;
    // End of variables declaration//GEN-END:variables
}
