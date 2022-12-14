/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.ShipmentCompany.Courier;

import Model.DB4OUtil.DB4OUtil;
import Model.Business.EcoSystem;
import Model.Employee.Employee;
import Model.Enterprise.Enterprise;
import Model.Enterprise.Inventory.Inventory;
import Model.Enterprise.Outlet;
import Model.Enterprise.Outlet.OutletType;
import static Model.Enterprise.Outlet.OutletType.Inventory;
import Model.Network.Network;
import Model.Organization.Organization;
import Model.Role.Role;
import Model.UserAccount.EmployeeAccount;
import Model.UserAccount.UserAccount;
import Model.WorkQueue.InventoryDeliveryRequest;
import Model.WorkQueue.InventoryItemRequest;
import Model.WorkQueue.ShipmentRequest;
import Model.WorkQueue.OrderRequest;
import Model.WorkQueue.ReviewRequest;
import Model.WorkQueue.WorkRequest;
import Model.WorkQueue.WorkRequest.StatusEnum;
import UserInterface.SignInJFrame;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rutu
 */
public class CourierMainJPanel extends javax.swing.JPanel {

    private EcoSystem system;
    private JPanel container;
    private Enterprise en;
    private EmployeeAccount account;
    private JFrame frame;
    private Role role;
    private Outlet res;
    private Employee employee;
    private ShipmentRequest selectedRequest = null;
    private InventoryDeliveryRequest selectedRequest1 = null;

    /**
     * Creates new form DeliveryManMainJPanel
     */
    public CourierMainJPanel(EcoSystem system, JPanel container, Enterprise en, UserAccount userAccount, 
            JFrame frame, Role role) {
        initComponents();
        this.system = system;
        this.container = container;
        this.en = en;
        this.account = (EmployeeAccount) userAccount;
        this.frame = frame;
        this.employee = this.account.getEmployee();
        this.role = role;
        
        // view of system manager
        if (role.getRoleType().equals(Role.RoleType.SystemAdmin)) {
            logoutButton.setVisible(false);
            jLabel5.setText("");
        }

        // Profile Tab
        setInfo();
        editButton.setEnabled(true);
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        setFieldsEditable(false);
        
        // Task Tab
        deliveryButton.setEnabled(false);
        pickupButton.setEnabled(false);
        deliveredButton.setEnabled(false);
        
        populateOrderTable(getAllDeliveryRequest());
    }
    
    private ArrayList<WorkRequest> getAllDeliveryRequest() {
        ArrayList<WorkRequest> list = new ArrayList<>();
        list.addAll(this.en.getWorkQueue().getWorkRequestDirectory());
        list.addAll(this.account.getWorkQueue().getWorkRequestDirectory());
        return list;
    }

    private void populateOrderTable(ArrayList<WorkRequest> list) {
        DefaultTableModel dtm = (DefaultTableModel) orderTable.getModel();
        dtm.setRowCount(0);
        for (WorkRequest wr : list) 
        {
            if(wr.getEnterprise() instanceof Inventory)
            {
               InventoryDeliveryRequest dr = (InventoryDeliveryRequest) wr;
               Object row[] = new Object[4];
               row[0] = dr.getInventoryOrder().getInventoryOrderId();
               row[1] = dr;
               row[2] = (Outlet) dr.getEnterprise();
               row[3] = dr.getStatus();
               dtm.addRow(row); 
            }
            else
            {
            ShipmentRequest dr = (ShipmentRequest) wr;
            Object row[] = new Object[4];
            row[0] = dr.getOrder().getOrderrequest_id();
            row[1] = dr;
            row[2] = (Outlet) dr.getEnterprise();
            row[3] = dr.getStatus();
            dtm.addRow(row);
            }
        }
    }

//    private void populateDetails() {
//        Outlet res = (Outlet) selectedRequest.getEnterprise();
//        pickupAddressTextArea.setText(res.getOut_address());
//        pickupNameTextField2.setText(res.getOrg_name());
//        pickupPhoneTextField.setText(res.getOut_phone());
//        OrderRequest or = (OrderRequest) selectedRequest.getOrder();
//        deliveryAddressTextArea.setText(or.getDeliveryAddress());
//        deliveryNameTextField.setText(or.getDeliveryName());
//        deliveryPhoneTextField.setText(or.getDeliveryPhone());
//    }
//
    private void setInfo() {
        roleTextField.setText(this.account.getRole().getRoleType().getValue());
        nameLabel.setText(employee.getFirstname());
        emailTextField.setText(employee.getEmail_id());
        firstNameTextField.setText(employee.getFirstname());
        lastNameTextField.setText(employee.getLastname());
        phoneTextField.setText(employee.getMobile_no());
        usernameTextField.setText(account.getUsername());
    }

    private void resetPasswordField() {
        passwordField.setText("");
        passwordField1.setText("");
        passwordField2.setText("");
    }

    private void setFieldsEditable(boolean b) {
        emailTextField.setEnabled(b);
        firstNameTextField.setEnabled(b);
        lastNameTextField.setEnabled(b);
        phoneTextField.setEnabled(b);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        firstNameTextField = new javax.swing.JTextField();
        editButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        phoneTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        roleTextField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        deliveryButton = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        pickupNameTextField2 = new javax.swing.JTextField();
        pickupPhoneTextField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        pickupAddressTextArea = new javax.swing.JTextArea();
        jLabel21 = new javax.swing.JLabel();
        deliveryNameTextField = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        deliveryAddressTextArea = new javax.swing.JTextArea();
        deliveryPhoneTextField = new javax.swing.JTextField();
        pickupButton = new javax.swing.JButton();
        deliveredButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        passwordField1 = new javax.swing.JPasswordField();
        passwordField2 = new javax.swing.JPasswordField();
        cancelButton1 = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        submitButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 204));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        jLabel5.setText("Welcome to OneStopShop");

        nameLabel.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        nameLabel.setText("<Name>");

        logoutButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        editButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel9.setText("Last Name: ");

        usernameTextField.setEnabled(false);
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel12.setText("First Name: ");

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel7.setText("Email:");

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel11.setText("Phone:");

        saveButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Username: ");

        cancelButton.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel10.setText("Role:");

        roleTextField.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(roleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(354, 354, 354))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(285, 285, 285))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(roleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Profile", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Date", "Restaurant", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(orderTable);

        deliveryButton.setText("Start Delivery");
        deliveryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveryButtonActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel16.setText("Name:");

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel17.setText("Phone:");

        usernameTextField.setEnabled(false);
        pickupNameTextField2.setEditable(false);

        pickupPhoneTextField.setEditable(false);

        jLabel19.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel19.setText("Pickup Restaurant Address:");

        pickupAddressTextArea.setEditable(false);
        pickupAddressTextArea.setColumns(20);
        jScrollPane3.setViewportView(pickupAddressTextArea);

        jLabel21.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel21.setText("Delivery Address:");

        usernameTextField.setEnabled(false);
        deliveryNameTextField.setEditable(false);

        jLabel22.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel22.setText("Phone:");

        jLabel23.setFont(new java.awt.Font("Lucida Grande", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel23.setText("Name:");

        deliveryAddressTextArea.setEditable(false);
        deliveryAddressTextArea.setColumns(20);
        jScrollPane5.setViewportView(deliveryAddressTextArea);

        deliveryPhoneTextField.setEditable(false);

        pickupButton.setText("Picked up");
        pickupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickupButtonActionPerformed(evt);
            }
        });

        deliveredButton.setText("Delivered");
        deliveredButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveredButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(deliveryButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pickupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deliveredButton))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel21)
                                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pickupPhoneTextField))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(pickupNameTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(60, 60, 60))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deliveryNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deliveryPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(57, 57, 57))))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(pickupNameTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pickupPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(deliveryNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deliveryPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pickupButton)
                            .addComponent(deliveryButton)
                            .addComponent(deliveredButton))))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Task Board", jPanel4);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(passwordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(295, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(passwordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(passwordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitButton)
                    .addComponent(cancelButton1))
                .addContainerGap(241, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Change Password", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    UserAccount check_user (String username)
    {      
            for (Network net : system.getNetworkDirectory()) {
                for (Enterprise en : net.getEnterpriseDirectory().getEnterpriseDirectory()) {
                        for (Organization or : en.getOrganizationDirectory().getOrganizationDirectory()) 
                        {
                           for( UserAccount ua: or.getUserAccountDirectory().getUserAccountDirectory()){
                               if(ua.getUsername().equalsIgnoreCase(username)){
                                   return ua;
                               }
                           }
            
                            
    }
                }
            }
            return null;
    }
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        SignInJFrame lf = new SignInJFrame();
        this.frame.dispose();
        lf.setLocationRelativeTo(null);
        lf.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void orderTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderTableMouseClicked
        int index = orderTable.getSelectedRow();
        res = (Outlet)(orderTable.getValueAt(index, 2));
        if (index >= 0) 
        {
            if ((orderTable.getValueAt(index, 2)) instanceof Inventory)
            {
                selectedRequest1 = (InventoryDeliveryRequest) orderTable.getValueAt(index, 1);
                pickupAddressTextArea.setText(res.getOut_address());
                pickupNameTextField2.setText(res.getOrg_name());
                pickupPhoneTextField.setText(res.getOut_phone()); 
                
                InventoryItemRequest ir = (InventoryItemRequest) selectedRequest1.getInventoryOrder();
                deliveryAddressTextArea.setText(ir.getDeliveryAddress());
                deliveryNameTextField.setText(ir.getDeliveryName());
                deliveryPhoneTextField.setText(ir.getDeliveryPhone());
            if (selectedRequest1.getStatus().equals(StatusEnum.Ready)) {
                deliveryButton.setEnabled(true);
                pickupButton.setEnabled(false);
                deliveredButton.setEnabled(false);
            }
            if (selectedRequest1.getStatus().equals(StatusEnum.WaitForPickup)) {
                deliveryButton.setEnabled(false);
                pickupButton.setEnabled(true);
                deliveredButton.setEnabled(false);
            }
            if (selectedRequest1.getStatus().equals(StatusEnum.OnTheWay)) {
                deliveryButton.setEnabled(false);
                pickupButton.setEnabled(false);
                deliveredButton.setEnabled(true);
            }
            if (selectedRequest1.getStatus().equals(StatusEnum.Completed) ||
                    selectedRequest1.getStatus().equals(StatusEnum.Cancelled)) {
                deliveryButton.setEnabled(false);
                pickupButton.setEnabled(false);
                deliveredButton.setEnabled(false);
            }
            }
            else
            {    
            selectedRequest = (ShipmentRequest) orderTable.getValueAt(index, 1);
            pickupAddressTextArea.setText(res.getOut_address());
                pickupNameTextField2.setText(res.getOrg_name());
                pickupPhoneTextField.setText(res.getOut_phone());
            if (selectedRequest.getStatus().equals(StatusEnum.Ready)) {
                deliveryButton.setEnabled(true);
                pickupButton.setEnabled(false);
                deliveredButton.setEnabled(false);
            }
            if (selectedRequest.getStatus().equals(StatusEnum.WaitForPickup)) {
                deliveryButton.setEnabled(false);
                pickupButton.setEnabled(true);
                deliveredButton.setEnabled(false);
            }
            if (selectedRequest.getStatus().equals(StatusEnum.OnTheWay)) {
                deliveryButton.setEnabled(false);
                pickupButton.setEnabled(false);
                deliveredButton.setEnabled(true);
            }
            if (selectedRequest.getStatus().equals(StatusEnum.Completed) ||
                    selectedRequest.getStatus().equals(StatusEnum.Cancelled)) {
                deliveryButton.setEnabled(false);
                pickupButton.setEnabled(false);
                deliveredButton.setEnabled(false);
            }
//            populateDetails();
            }
        }
         else 
         {
            deliveryButton.setEnabled(false);
         }
    }//GEN-LAST:event_orderTableMouseClicked

    private void deliveryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryButtonActionPerformed
        if (res instanceof Inventory)
        {
            selectedRequest1.setStatus(StatusEnum.WaitForPickup);
            selectedRequest1.setAccount(this.account);
            selectedRequest1.getInventoryOrder().setStatus(StatusEnum.WaitForPickup);
            String request_username = selectedRequest1.getInventoryOrder().getAccount().getUsername();
            UserAccount request_account = check_user(request_username);
            InventoryItemRequest or = request_account.getWorkQueue().getinventoryOderById(selectedRequest1.getInventoryOrder().getInventoryOrderId());
            or.setStatus(StatusEnum.WaitForPickup);
            system.getEnterpriseById(selectedRequest1.getInventoryOrder().getEnterprise().getShipcom_id()).getWorkQueue().
                getinventoryOderById(selectedRequest1.getInventoryOrder().getInventoryOrderId()).setStatus(StatusEnum.WaitForPickup);
       
            Outlet model = (Outlet)system.getEnterpriseById(selectedRequest1.getInventoryOrder().getEnterprise().getShipcom_id());
        
            model.getWorkQueue().getinventoryOderById(selectedRequest1.getInventoryOrder().getInventoryOrderId()).setStatus(StatusEnum.WaitForPickup);
            en.getWorkQueue().getWorkRequestDirectory().remove(selectedRequest1);
            this.account.getWorkQueue().getWorkRequestDirectory().add(selectedRequest1);
            DB4OUtil.getInstance().storeSystem(system);
            populateOrderTable(getAllDeliveryRequest());
//            populateDetails();
            deliveryButton.setEnabled(false);
            pickupButton.setEnabled(true);
            deliveredButton.setEnabled(false); 
        }
        else
        {
        selectedRequest.setStatus(StatusEnum.WaitForPickup);
        selectedRequest.setAccount(this.account);
        selectedRequest.getOrder().setStatus(StatusEnum.WaitForPickup);
        system.getCustomerAccountByUsername(selectedRequest.getOrder().getAccount().getUsername()).
                getWorkQueue().getOderById(selectedRequest.getOrder().getOrderrequest_id()).setStatus(StatusEnum.WaitForPickup);
        system.getEnterpriseById(selectedRequest.getOrder().getEnterprise().getShipcom_id()).getWorkQueue().
                getOderById(selectedRequest.getOrder().getOrderrequest_id()).setStatus(StatusEnum.WaitForPickup);
       
        Outlet model = (Outlet)system.getEnterpriseById(selectedRequest.getOrder().getEnterprise().getShipcom_id());
        
        model.getWorkQueue().getOderById(selectedRequest.getOrder().getOrderrequest_id()).setStatus(StatusEnum.WaitForPickup);
        en.getWorkQueue().getWorkRequestDirectory().remove(selectedRequest);
        this.account.getWorkQueue().getWorkRequestDirectory().add(selectedRequest);
        DB4OUtil.getInstance().storeSystem(system);
        populateOrderTable(getAllDeliveryRequest());
//        populateDetails();
        deliveryButton.setEnabled(false);
        pickupButton.setEnabled(true);
        deliveredButton.setEnabled(false);
        }
    }//GEN-LAST:event_deliveryButtonActionPerformed

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

        if (password.equals(account.getPassword())) {
            if (!new1.equals("")) {
                if (new1.equals(new2)) {
                    account.setPassword(new1);
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

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        int index = jTabbedPane1.getSelectedIndex();
        if (index != 0 && index != -1) {
            setInfo();
        }
        setFieldsEditable(false);
        resetPasswordField();

        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        editButton.setEnabled(true);
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        saveButton.setEnabled(true);
        cancelButton.setEnabled(true);
        editButton.setEnabled(false);

        setFieldsEditable(true);
    }//GEN-LAST:event_editButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        if (!emailTextField.getText().equals("") && !firstNameTextField.getText().equals("")
                && !lastNameTextField.getText().equals("") && !phoneTextField.getText().equals("")) {
            this.employee.setEmail_id(emailTextField.getText());
            this.employee.setFirstname(firstNameTextField.getText());
            this.employee.setLastname(lastNameTextField.getText());
            this.employee.setMobile_no(phoneTextField.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Information can't be empty");
            return;
        }
        setFieldsEditable(false);
        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        editButton.setEnabled(true);

        DB4OUtil.getInstance().storeSystem(system);
    }//GEN-LAST:event_saveButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setFieldsEditable(false);
        setInfo();

        saveButton.setEnabled(false);
        cancelButton.setEnabled(false);
        editButton.setEnabled(true);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void pickupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickupButtonActionPerformed
        if (res instanceof Inventory)
        {
        selectedRequest1.setStatus(StatusEnum.OnTheWay);
        selectedRequest1.getInventoryOrder().setStatus(StatusEnum.OnTheWay);
        String request_username = selectedRequest1.getInventoryOrder().getAccount().getUsername();
        UserAccount request_account = check_user(request_username);
        InventoryItemRequest or = request_account.getWorkQueue().getinventoryOderById(selectedRequest1.getInventoryOrder().getInventoryOrderId());
        or.setStatus(StatusEnum.OnTheWay);
        system.getEnterpriseById(selectedRequest1.getInventoryOrder().getEnterprise().getShipcom_id()).getWorkQueue().
                getinventoryOderById(selectedRequest1.getInventoryOrder().getInventoryOrderId()).setStatus(StatusEnum.OnTheWay);
        DB4OUtil.getInstance().storeSystem(system);
        populateOrderTable(getAllDeliveryRequest());
//        populateDetails();
        deliveryButton.setEnabled(false);
        pickupButton.setEnabled(false);
        deliveredButton.setEnabled(true);
        }
        else
        {
        selectedRequest.setStatus(StatusEnum.OnTheWay);
        selectedRequest.getOrder().setStatus(StatusEnum.OnTheWay);
        system.getCustomerAccountByUsername(selectedRequest.getOrder().getAccount().getUsername()).
                getWorkQueue().getOderById(selectedRequest.getOrder().getOrderrequest_id()).setStatus(StatusEnum.OnTheWay);
        system.getEnterpriseById(selectedRequest.getOrder().getEnterprise().getShipcom_id()).getWorkQueue().
                getOderById(selectedRequest.getOrder().getOrderrequest_id()).setStatus(StatusEnum.OnTheWay);
        DB4OUtil.getInstance().storeSystem(system);
        populateOrderTable(getAllDeliveryRequest());
//        populateDetails();
        deliveryButton.setEnabled(false);
        pickupButton.setEnabled(false);
        deliveredButton.setEnabled(true);
        }
    }//GEN-LAST:event_pickupButtonActionPerformed

    private void deliveredButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveredButtonActionPerformed
        if (res instanceof Inventory)
        {
        selectedRequest1.setStatus(StatusEnum.Completed);
        selectedRequest1.getInventoryOrder().setStatus(StatusEnum.Completed);
        String request_username = selectedRequest1.getInventoryOrder().getAccount().getUsername();
        UserAccount request_account = check_user(request_username);
        InventoryItemRequest or = request_account.getWorkQueue().getinventoryOderById(selectedRequest1.getInventoryOrder().getInventoryOrderId());
        
        ReviewRequest rr = new ReviewRequest(selectedRequest1.getEnterprise(), 
                selectedRequest1.getInventoryOrder().getAccount());
        or.setStatus(StatusEnum.Completed);
        system.getEnterpriseById(selectedRequest1.getInventoryOrder().getEnterprise().getShipcom_id()).getWorkQueue().
                getinventoryOderById(selectedRequest1.getInventoryOrder().getInventoryOrderId()).setStatus(StatusEnum.Completed);
        or.setReview(rr);
        selectedRequest1.getInventoryOrder().setReview(rr);
        DB4OUtil.getInstance().storeSystem(system);
        populateOrderTable(getAllDeliveryRequest());
        //populateDetails();
        deliveryButton.setEnabled(false);
        pickupButton.setEnabled(false);
        deliveredButton.setEnabled(false);
        }
        else
        {
        selectedRequest.setStatus(StatusEnum.Completed);
        selectedRequest.getOrder().setStatus(StatusEnum.Completed);
        try
      {
        String ACCOUNT_SID = "AC14e5000f99586319c09d4e67d9d34ac4";
        String Auth_Token = "530c68c7e01398c374d88527c889f850";
        
        Twilio.init(ACCOUNT_SID, Auth_Token);
        
        Message message = Message.creator(new com.twilio.type.PhoneNumber(deliveryPhoneTextField.getText()), 
                new com.twilio.type.PhoneNumber("+15642095234"), 
                "Your Order has been delivered successfully of Thank you for Shopping with us").create();
        message.getSid();
      }
      catch(Exception e)
      {
          JOptionPane.showMessageDialog(null, "Error Message "+e);
      }
        
        ReviewRequest rr = new ReviewRequest(selectedRequest.getEnterprise(), 
                selectedRequest.getOrder().getAccount());
        system.getCustomerAccountByUsername(selectedRequest.getOrder().getAccount().getUsername()).
                getWorkQueue().getOderById(selectedRequest.getOrder().getOrderrequest_id()).setStatus(StatusEnum.Completed);
        system.getEnterpriseById(selectedRequest.getOrder().getEnterprise().getShipcom_id()).getWorkQueue().
                getOderById(selectedRequest.getOrder().getOrderrequest_id()).setStatus(StatusEnum.Completed);
        system.getCustomerAccountByUsername(selectedRequest.getOrder().getAccount().getUsername()).
                getWorkQueue().getOderById(selectedRequest.getOrder().getOrderrequest_id()).setReview(rr);
        selectedRequest.getOrder().setReview(rr);
        DB4OUtil.getInstance().storeSystem(system);
        populateOrderTable(getAllDeliveryRequest());
//        populateDetails();
        deliveryButton.setEnabled(false);
        pickupButton.setEnabled(false);
        deliveredButton.setEnabled(false);
        }
    }//GEN-LAST:event_deliveredButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton cancelButton1;
    private javax.swing.JButton deliveredButton;
    private javax.swing.JTextArea deliveryAddressTextArea;
    private javax.swing.JButton deliveryButton;
    private javax.swing.JTextField deliveryNameTextField;
    private javax.swing.JTextField deliveryPhoneTextField;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTable orderTable;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPasswordField passwordField1;
    private javax.swing.JPasswordField passwordField2;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JTextArea pickupAddressTextArea;
    private javax.swing.JButton pickupButton;
    private javax.swing.JTextField pickupNameTextField2;
    private javax.swing.JTextField pickupPhoneTextField;
    private javax.swing.JTextField roleTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
