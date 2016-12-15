package gui;

/**
 * @author Matthew Walichnowsky | 200171919
 */

import general.Global;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
        
public class AdminGui extends JFrame
{
    /* ----------------------- Variables------------------------------------- */
        final private JTextField 
                
            /* Create Employee Personal Info Fields */
            textFirstName = new JTextField(15),
            textLastName = new JTextField(15),
            textGender = new JTextField(15),
            textAge = new JTextField(2),
            textAddress = new JTextField(15),
            textDateOfBirth = new JTextField(15),
            textPhoneNumber = new JTextField(15),
            textSIN = new JTextField(15),
        
            /* Create Employee Work Info Fields */
            textDateHired = new JTextField(15),
            textPosition = new JTextField(15),
            textStatus = new JTextField(15),
            textDepartment = new JTextField(15),
            textSalary = new JTextField(15),
            textHourlyRate = new JTextField(15),
            textCommissionRate = new JTextField(15),

            /* Create Product Text Fields */
            textProductName = new JTextField(15), 
            textProductCode = new JTextField(15), 
            textProductPrice = new JTextField(15),
            textProductCommission = new JTextField(15),
                           
            /* Create Manufacturer Text Fields */
            textManufacturerName = new JTextField(15),
            textManufacturerLocation = new JTextField(15), 
            textManufacturerPhoneNumber = new JTextField(15), 
            textManufacturerSalesAssociate = new JTextField(15),
                
            /* Search Text Field */
            textSearchEmployees = new JTextField(15),
            textSearchInventory = new JTextField(15),
                
            /* Sales Field */
            textSalesQuantity = new JTextField(15), 
            textSalesSearch = new JTextField(15),
                
            /* Edit / Delete */
            textEmployeeSelect = new JTextField(15),
            textInventorySelect = new JTextField(15),
            textSalesSelect = new JTextField(15)
        ;
        
        private JTextArea
                
            /* Search Text Field */
            areaSearchResults = new JTextArea(), areaSearchInventory, 
            areaProductSummary, areaManufacturerSummary
        ;
        
        final private JPanel 
            headerPanel = new JPanel(), footerPanel = new JPanel(),
                
            /* HR Panels */    
            createEmployeePanel = new JPanel(),
            searchEmployeePanel = new JPanel(),
            editEmployeePanel = new JPanel(),
            
            /* Inventory Panels */    
            inventoryPanel = new JPanel(),
            searchInventoryPanel = new JPanel(),
            editInventoryPanel = new JPanel(),
            createProductPanel  = new JPanel(), 
            createManufacturerPanel = new JPanel(), 
            
            /* Sales Panels */
            createSalesPanel = new JPanel(), searchSalesPanel = new JPanel(),
            editSalesPanel = new JPanel()
        ;               
        
        final private JTable 
            employeeSearchTable = new JTable(),
            productSearchTable = new JTable(),
            manufacturerSearchTable = new JTable()
        ;
        
        final private JButton 
            exitButton = new JButton("Exit"), 
            submitButton = new JButton("Submit"), 
            createButton = new JButton("Create"),
            clearButton = new JButton("Clear"), 
            editButton = new JButton("Edit"), 
            deleteButton = new JButton("Delete")
        ;
        
        final private JTabbedPane 
            mainTabPane = new JTabbedPane(), // Main Tab.
            hrTabPane = new JTabbedPane(), // Human Resources Tab.
            inventoryTabPane = new JTabbedPane(), // Inventory Tab.
            subInventoryTabPane = new JTabbedPane(), // Sub Inventory Tab.
            salesTabPane = new JTabbedPane() // Sales Tab.
        ;
                    
        final private JRadioButton 
            /* Employee Radio Buttons */    
            basePlusCommissionEmployee = new JRadioButton
                                       ("Base Plus Commission Employee", false), 
            commissionEmployee = new JRadioButton("Comission Employee", false), 
            hourlyEmployee = new JRadioButton("Hourly Employee", false), 
            salaryEmployee = new JRadioButton("Salary Employee", true),
                
            /* Inventory Radio Buttons */    
            productButton = new JRadioButton("Products:", true),
            manufacturerButton = new JRadioButton("Manufacturers:")
        ;
        
        JLabel inventoryLabel = new JLabel("Products:");
        
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
              
        // Set variable for calling our global methods.
        Global g = new Global();
        
    /////////////////////// End of Variables ///////////////////////////////////
    
    /* ----------------------- Constructor ---------------------------------- */
        
        public AdminGui()
        {
            super("Prestige Worldwide");
            setLayout(new BorderLayout());
            
            textCommissionRate.setEditable(false);
            textHourlyRate.setEditable(false);
            
            // Build Panels.
            buildHeaderPanel("Welcome to Management Box!");
            buildCreateEmployeePanel();
            buildSearchEmployeePanel();
            buildEditEmployeesPanel();
            buildSearchInventoryPanel();
            buildEditInventoryPanel();
            buildCreateProductPanel();
            buildCreateManufacturerPanel();
            buildCreateSalesPanel();
            buildEditSalesPanel();
            buildFooterPanel();
            
            
            // Setup Tabs.
            setupTabs();
            
            // Add the panels.
            add(headerPanel, BorderLayout.NORTH);
            add(mainTabPane, BorderLayout.CENTER);
            add(footerPanel, BorderLayout.SOUTH);
            
            pack();
            
            
        }
    /////////////////////// End of Constructor /////////////////////////////////
        
    
    /* ----------------------- Build Panels --------------------------------- */ 
        /**
         * This creates a greetings panel.
         * @param labelMessage 
         */
        private void buildHeaderPanel(String labelMessage)
        {
            JLabel labelGreeting = new JLabel(labelMessage);
            labelGreeting.setFont(g.getGreetFont());
            headerPanel.add(labelGreeting);
            headerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
        } // End of buildHeaderPanel method.


        /**
         * This creates the employee selection panel.
         * @param labelMessage 
         */
        private JPanel buildEmployeeSelectionPanel()
        {
            JPanel employeeSelectionPanel = new JPanel();
            
            // Add the buttons to the selection group.
            ButtonGroup employeeSelectionGroup = new ButtonGroup();
            employeeSelectionGroup.add(hourlyEmployee);
            employeeSelectionGroup.add(basePlusCommissionEmployee);
            employeeSelectionGroup.add(commissionEmployee);
            employeeSelectionGroup.add(salaryEmployee);
            
            // Set border.
            g.border(employeeSelectionPanel, "Employee Type");
            
            // Add the buttons to the panel.
            employeeSelectionPanel.add(hourlyEmployee);
            employeeSelectionPanel.add(basePlusCommissionEmployee);
            employeeSelectionPanel.add(commissionEmployee);
            employeeSelectionPanel.add(salaryEmployee);

            // RadioButton action listeners.
            EmpRadioButtonHandler rbHandler = new EmpRadioButtonHandler();
            salaryEmployee.addItemListener(rbHandler);
            commissionEmployee.addItemListener(rbHandler);
            hourlyEmployee.addItemListener(rbHandler);
            basePlusCommissionEmployee.addItemListener(rbHandler);
            
            return employeeSelectionPanel;
        }


        /**
         * This creates the employee panel.
         */
        private void buildCreateEmployeePanel()
        {
            /* ----------- Employee Information Section --------------------- */
                JPanel employeePersonalInformation = new JPanel(); 
                employeePersonalInformation.setLayout(new GridLayout(4,2));
                g.border(employeePersonalInformation, "Personal Information");
                employeePersonalInformation.add(new JLabel("First Name:"));
                employeePersonalInformation.add(textFirstName);
                employeePersonalInformation.add(new JLabel("Last Name:"));
                employeePersonalInformation.add(textLastName);
                employeePersonalInformation.add(new JLabel("Gender:"));
                employeePersonalInformation.add(textGender);
                employeePersonalInformation.add(new JLabel("Age:"));
                employeePersonalInformation.add(textAge);
                employeePersonalInformation.add(new JLabel("Address:"));
                employeePersonalInformation.add(textAddress);
                employeePersonalInformation.add(new JLabel("Date of Birth:"));
                employeePersonalInformation.add(textDateOfBirth);
                employeePersonalInformation.add(new JLabel("Phone Number:"));
                employeePersonalInformation.add(textPhoneNumber);
                employeePersonalInformation.add(new JLabel("SIN:"));
                employeePersonalInformation.add(textSIN);
            ////////////// End of Main Panel Top ///////////////////////////////

            /* ----------- Main Panel Bottom -------------------------------- */
                JPanel employeeWorkInformation = new JPanel();    
                employeeWorkInformation.setLayout(new GridLayout(4,2));
                g.border(employeeWorkInformation, "Work Information");
                employeeWorkInformation.add(new JLabel("Date Hired:"));
                employeeWorkInformation.add(textDateHired);
                employeeWorkInformation.add(new JLabel("Position:"));
                employeeWorkInformation.add(textPosition);
                employeeWorkInformation.add(new JLabel("Status:"));
                employeeWorkInformation.add(textStatus);
                employeeWorkInformation.add(new JLabel("Hourly Rate:"));
                employeeWorkInformation.add(textHourlyRate);
                employeeWorkInformation.add(new JLabel("Commission Rate:"));
                employeeWorkInformation.add(textCommissionRate);
                employeeWorkInformation.add(new JLabel("Salary Rate:"));
                employeeWorkInformation.add(textSalary);
                employeeWorkInformation.add(new JLabel("Department:"));
                employeeWorkInformation.add(textDepartment);
            ////////////// End of Main Panel Bottom ////////////////////////////
            
            JPanel employeeSelectionPanel = buildEmployeeSelectionPanel(); 
            
            createEmployeePanel.setLayout(new BorderLayout());
            createEmployeePanel.add(employeeSelectionPanel, BorderLayout.NORTH);
            createEmployeePanel.add
                             (employeePersonalInformation, BorderLayout.CENTER);
            createEmployeePanel.add
                                  (employeeWorkInformation, BorderLayout.SOUTH);
        }

        
        /**
         * This method builds the human resources search panel.
         */
        private void buildSearchEmployeePanel()
        {
            JPanel searchEmpNorthPanel = new JPanel();
            searchEmpNorthPanel.setLayout(new FlowLayout());
            g.border(searchEmployeePanel, "Search Employees:");
            searchEmpNorthPanel.add(new JLabel("Search Employees:"));
            searchEmpNorthPanel.add(textSearchEmployees);          
            areaSearchResults.setEditable(false);
            searchEmployeePanel.setLayout(new BorderLayout());
            JScrollPane scrollPane = new JScrollPane(areaSearchResults);
            searchEmployeePanel.add(searchEmpNorthPanel, BorderLayout.NORTH);
            searchEmployeePanel.add(scrollPane, BorderLayout.CENTER);
        }


        /**
         * This method builds the create product panel.
         */
        private void buildCreateProductPanel()
        {
            JPanel productNorthPanel = new JPanel();
            productNorthPanel.setLayout(new GridLayout(3,2));
            
            areaProductSummary = new JTextArea("Summary / Notes Here");
            
            // Database Connection Here
            JComboBox manufacturers = new JComboBox();
            //manufacturers.setSelectedIndex(0);

            g.border(inventoryPanel, "Inventory Information");
            productNorthPanel.add(new JLabel("Name:"));
            productNorthPanel.add(textProductName);
            productNorthPanel.add(new JLabel("Code:"));
            productNorthPanel.add(textProductCode);
            productNorthPanel.add(new JLabel("Price:"));
            productNorthPanel.add(textProductPrice);
            productNorthPanel.add(new JLabel("Manufacturer:"));
            productNorthPanel.add(manufacturers);
            productNorthPanel.add(new JLabel("Commission Rate:"));
            productNorthPanel.add(textProductCommission);
            
            // Setup product panel.
            createProductPanel.setLayout(new BorderLayout());
            JScrollPane scrollPane = new JScrollPane(areaProductSummary);
            createProductPanel.add(productNorthPanel, BorderLayout.NORTH);
            createProductPanel.add(scrollPane, BorderLayout.CENTER);
        }


        /**
         * This method builds the create manufacturer panel.
         */
        private void buildCreateManufacturerPanel()
        {
            JPanel manNorthPanel = new JPanel();
            manNorthPanel.setLayout(new GridLayout(2,2));
            areaManufacturerSummary = new JTextArea("Summary / Notes Here");
            manNorthPanel.add(new JLabel("Name:"));
            manNorthPanel.add(textManufacturerName);
            manNorthPanel.add(new JLabel("Location:"));
            manNorthPanel.add(textManufacturerLocation);
            manNorthPanel.add(new JLabel("Phone Number:"));
            manNorthPanel.add(textManufacturerPhoneNumber);
            manNorthPanel.add(new JLabel("Sales Associate:"));
            manNorthPanel.add(textManufacturerSalesAssociate);
            
            // Setup manufacturing panel.
            createManufacturerPanel.setLayout(new BorderLayout());
            JScrollPane scrollPane = new JScrollPane(areaManufacturerSummary);
            createManufacturerPanel.add(manNorthPanel, BorderLayout.NORTH);
            createManufacturerPanel.add(scrollPane, BorderLayout.CENTER);
        }


        /**
         * This creates the employee selection panel.
         * @param labelMessage 
         */
        private void buildSearchInventoryPanel()
        {
            JPanel invNorthPanel = new JPanel();
            invNorthPanel.setLayout(new FlowLayout());
            ButtonGroup inventorySearchSelectionGroup = new ButtonGroup();

            areaSearchInventory = new JTextArea();
            areaSearchInventory.setEditable(false);

            JRadioButton
                searchProducts = new JRadioButton("Search Products", true),
                searchManufacturers = new JRadioButton
                                                  ("Search Manufacturer", false)
            ;

            inventorySearchSelectionGroup.add(searchProducts);
            inventorySearchSelectionGroup.add(searchManufacturers);

            g.border(searchInventoryPanel, "Search Inventory:");
            invNorthPanel.add(searchProducts);
            invNorthPanel.add(searchManufacturers);
            invNorthPanel.add(new JLabel("Search For:"));
            invNorthPanel.add(textSearchInventory);
            
            // Setup search inventory panel.
            searchInventoryPanel.setLayout(new BorderLayout());
            JScrollPane scrollPane = new JScrollPane(areaSearchInventory);
            searchInventoryPanel.add(invNorthPanel, BorderLayout.NORTH);
            searchInventoryPanel.add(scrollPane, BorderLayout.CENTER);
        }

        /**
         * This method creates the sales panels used for creating new orders.
         */
        private void buildCreateSalesPanel()
        {
            JPanel salesNorthPanel = new JPanel();
            salesNorthPanel.setLayout(new GridLayout(3,2));

            // Database Connection Here
            JComboBox products = new JComboBox();
            //products.setSelectedIndex(0);
            JComboBox employees = new JComboBox();
            //employees.setSelectedIndex(0);
            
            g.border(salesNorthPanel, "Sales Information");
            salesNorthPanel.add(new JLabel("Quantity:"));
            salesNorthPanel.add(textSalesQuantity);
            salesNorthPanel.add(new JLabel("Product:"));
            salesNorthPanel.add(products);
            salesNorthPanel.add(new JLabel("Employee:"));
            salesNorthPanel.add(employees);
            
            JPanel salesSouthPanel = new JPanel();
            salesSouthPanel.setLayout(new FlowLayout());
            salesSouthPanel.add(createButton);
            salesSouthPanel.add(clearButton);
            
            // Action Listeners.
            createButton.addActionListener(new CreateButtonListener());
            clearButton.addActionListener(new ClearSalesButtonListener());
            
            createSalesPanel.setLayout(new BorderLayout());
            createSalesPanel.add(salesNorthPanel, BorderLayout.NORTH);
            createSalesPanel.add(salesSouthPanel, BorderLayout.SOUTH);
        }

        
        /**
         * This method creates the edit sales panels used for making
         * changes to sales or deleting them.
         */
        private void buildEditSalesPanel()
        {
            JPanel editSalesNorthPanel = new JPanel();
            editSalesNorthPanel.setLayout(new GridLayout(3,2));

            // Database Connection Here
            JComboBox products = new JComboBox();
            //products.setSelectedIndex(0);
            
            g.border(editSalesNorthPanel, "Sales Information");
            editSalesNorthPanel.add(new JLabel("Sale:"));
            editSalesNorthPanel.add(products);
            
            JPanel editSalesSouthPanel = new JPanel();
            editSalesSouthPanel.setLayout(new FlowLayout());
            editSalesSouthPanel.add(editButton);
            editSalesSouthPanel.add(deleteButton);
            
            // Action Listeners.
            editButton.addActionListener(new EditButtonListener());
            deleteButton.addActionListener(new DeleteButtonListener());
            
            editSalesPanel.setLayout(new BorderLayout());
            editSalesPanel.add(editSalesNorthPanel, BorderLayout.NORTH);
            editSalesPanel.add(editSalesSouthPanel, BorderLayout.SOUTH);
        }
        
        
        /**
         * This method creates the edit employees panels used for making
         * changes to employees or deleting them.
         */
        private void buildEditEmployeesPanel()
        {
            JPanel editEmpNorthPanel = new JPanel();
            editEmpNorthPanel.setLayout(new GridLayout(3,2));

            // Database Connection Here
            JComboBox products = new JComboBox();
            //products.setSelectedIndex(0);
            
            g.border(editEmpNorthPanel, "Employee Information");
            editEmpNorthPanel.add(new JLabel("Employee:"));
            editEmpNorthPanel.add(products);
            
            JPanel editEmpSouthPanel = new JPanel();
            editEmpSouthPanel.setLayout(new FlowLayout());
            editEmpSouthPanel.add(editButton);
            editEmpSouthPanel.add(deleteButton);
            
            // Action Listeners.
            editButton.addActionListener(new EditButtonListener());
            deleteButton.addActionListener(new DeleteButtonListener());
            
            editEmployeePanel.setLayout(new BorderLayout());
            editEmployeePanel.add(editEmpNorthPanel, BorderLayout.NORTH);
            editEmployeePanel.add(editEmpSouthPanel, BorderLayout.SOUTH);
        }
        
        
        /**
         * This method creates the edit inventory panel used for making
         * changes to inventory or deleting them.
         */
        private void buildEditInventoryPanel()
        {
            JPanel editInvNorthPanel = new JPanel();
            editInvNorthPanel.setLayout(new FlowLayout());
            ButtonGroup editButtonGroup = new ButtonGroup();
            editButtonGroup.add(productButton);
            editButtonGroup.add(manufacturerButton);
            editInvNorthPanel.add(productButton);
            editInvNorthPanel.add(manufacturerButton);
            
            
            JPanel editInvCenterPanel = new JPanel();
            editInvCenterPanel.setLayout(new GridLayout(3,2));

            // Database Connection Here
            JComboBox products = new JComboBox();
            //products.setSelectedIndex(0);
            
            g.border(editInvCenterPanel, "Inventory Information");
            editInvCenterPanel.add(inventoryLabel);
            editInvCenterPanel.add(products);
            
            JPanel editInvSouthPanel = new JPanel();
            editInvSouthPanel.setLayout(new FlowLayout());
            editInvSouthPanel.add(editButton);
            editInvSouthPanel.add(deleteButton);
            
            // Action Listeners.
            editButton.addActionListener(new EditButtonListener());
            deleteButton.addActionListener(new DeleteButtonListener());
            InvRadioButtonHandler rbHandler = new InvRadioButtonHandler();
            productButton.addItemListener(rbHandler);
            manufacturerButton.addItemListener(rbHandler);
            
            editInventoryPanel.setLayout(new BorderLayout());
            editInventoryPanel.add(editInvNorthPanel, BorderLayout.NORTH);
            editInventoryPanel.add(editInvCenterPanel, BorderLayout.CENTER);
            editInventoryPanel.add(editInvSouthPanel, BorderLayout.SOUTH);
        }
        

        /**
         * This creates the panel for our button.
         */
        private void buildFooterPanel()
        {
            // Action Listeners.
            submitButton.addActionListener(new SubmitButtonListener());
            exitButton.addActionListener(new ExitButtonListener());

            // Add items to the panel.
            footerPanel.add(submitButton);
            footerPanel.add(exitButton);
        }
    /////////////////////// End of Build Panels ////////////////////////////////
    
    /* --------- Action Listeners ------------------------------------------- */
        /**
         * Private inner class for event handling.
         */
        private class ExitButtonListener implements ActionListener
        {
            @Override 
            public void actionPerformed(ActionEvent event)
            {
                if 
                (
                    JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to exit?",
                    "Exit?", 
                    JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION
                )
                {
                    System.exit(0);
                }
            }
        } // End of ExitButtonListener inner class.


        /**
         * Private inner class for event handling.
         */
        private class SubmitButtonListener implements ActionListener
        {
            @Override 
            public void actionPerformed(ActionEvent event)
            {
                if 
                (
                    JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to submit?",
                    "Submit?", 
                    JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION
                )
                {
                    // Add to database.
                }
            }
        } // End of SubmitButtonListener inner class.

        
        /**
         * Private inner class for event handling.
         */
        private class ClearSalesButtonListener implements ActionListener
        {
            @Override 
            public void actionPerformed(ActionEvent event)
            {
                if 
                (
                    JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to clear?",
                    "Clear?", 
                    JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION
                )
                {
                    // Clear fields.
                    textSalesQuantity.setText("");
                }
            }
        } // End of ClearButtonListener inner class.
        

        /**
         * Private inner class for event handling.
         */
        private class CreateButtonListener implements ActionListener
        {
            @Override 
            public void actionPerformed(ActionEvent event)
            {
                if 
                (
                    JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to create?",
                    "Create?", 
                    JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION
                )
                {
                    // Add to database.
                }
            }
        } // End of CreateButtonListener inner class.
        
        
        /**
         * Private inner class for event handling.
         */
        private class EditButtonListener implements ActionListener
        {
            @Override 
            public void actionPerformed(ActionEvent event)
            {
                if 
                (
                    JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to save changes?",
                    "Update?", 
                    JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION
                )
                {
                    // Update the database.
                }
            }
        } // End of EditButtonListener inner class.
        
        
        /**
         * Private inner class for event handling.
         */
        private class DeleteButtonListener implements ActionListener
        {
            @Override 
            public void actionPerformed(ActionEvent event)
            {
                if 
                (
                    JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to delete?",
                    "Delete?", 
                    JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION
                )
                {
                    // Delete from database.
                }
            }
        } // End of DeleteButtonListener inner class.
        
        
        /**
         * Radio button handler listens for changes and updates text fields to
         * reflect what is available.
         */
        private class EmpRadioButtonHandler implements ItemListener
        {
            @Override
            public void itemStateChanged(ItemEvent event)
            {
                //set the textfields to visible based on what radio button is selected
                //set the salary text field to be visible
                if(salaryEmployee.isSelected())
                {
                    textSalary.setEditable(true);
                    textCommissionRate.setEditable(false);
                    textCommissionRate.setText("");
                    textHourlyRate.setEditable(false);
                    textHourlyRate.setText("");
                }
                //set the hourly text field to be visible
                else if(hourlyEmployee.isSelected())
                {
                    textSalary.setEditable(false);
                    textSalary.setText("");
                    textCommissionRate.setEditable(false);
                    textCommissionRate.setText("");
                    textHourlyRate.setEditable(true);
                }
                //set the commission text field to be visible
                else if(commissionEmployee.isSelected())
                {
                    textSalary.setEditable(false);
                    textSalary.setText("");
                    textCommissionRate.setEditable(true);
                    textHourlyRate.setEditable(false);
                    textHourlyRate.setText("");
                }
                //set the salary and commission text field to be visible
                else if(basePlusCommissionEmployee.isSelected())
                {
                    textSalary.setEditable(true);
                    textCommissionRate.setEditable(true);
                    textHourlyRate.setEditable(false);
                    textHourlyRate.setText("");
                }
            }
        }//end of handler
        
        
        /**
         * Radio button handler listens for changes and updates text fields to
         * reflect what is available.
         */
        private class InvRadioButtonHandler implements ItemListener
        {
            @Override
            public void itemStateChanged(ItemEvent event)
            {
                //set the textfields to visible based on what radio button is selected
                //set the salary text field to be visible
                if(productButton.isSelected())
                {
                    inventoryLabel.setText("Products");
                }
                //set the hourly text field to be visible
                else if(manufacturerButton.isSelected())
                {
                    inventoryLabel.setText("Manufacturers");
                }
            }
        }//end of handler
        
        
        /**
         * Private inner class for event handling.
         */
        private class ComboBoxListener implements ActionListener
        {
            @Override 
            public void actionPerformed(ActionEvent event)
            {
                //String selection = items.getSelectedItem().toString();
                
                //read(selection);
            }
        } // End of SubmitButtonListener inner class.
    /* --------- End of Action Listeners ------------------------------------ */
    
    /**
     * @param rs
     * @return
     * @throws SQLException 
     */
    public static DefaultTableModel buildTableModel (ResultSet rs)
            throws SQLException
    {
        ResultSetMetaData metaData = rs.getMetaData();
        
        // Get the column names and store them in a Vector.
        Vector<String> columnNames = new Vector<String>();
        
        // Column count.
        int columnCount = metaData.getColumnCount();
        
        // Loop to build the column names.
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
        {
            columnNames.add(metaData.getColumnName(columnIndex));
        }
        
        /**
         * Create the Vector to hold the data(Vector of Vectors)
         * This Vector will store all rows.
         */
        Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();
        
        // Go through the resultSet.
        while (rs.next())
        {
            // This will store each row.
            Vector<Object> rowVector = new Vector<Object>();
            
            // Loop through the result set and get each object.
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++)
            {
                rowVector.add(rs.getObject(columnIndex));
            }
        }
        
        // Return.        
        return new DefaultTableModel(tableData, columnNames);
    }

    
    public void buildTables()
    {
        // Employee Search Table.
        try
        {
            stat = conn.createStatement();
            result = stat.executeQuery(QRY);
            employeeSearchTable.setModel(buildTableModel(rs));
        }
        catch ( SQLException error ) { g.sqlError(error, "Error"); }
           
        // Product Search Table.
        try
        {
            stat = conn.createStatement();
            result = stat.executeQuery(QRY);
            productSearchTable.setModel(buildTableModel(rs));
        }
        catch ( SQLException error ) { g.sqlError(error, "Error"); }
        
        // Manufacturer Search Table.
        try
        {
            stat = conn.createStatement();
            result = stat.executeQuery(QRY);
            manufacturerSearchTable.setModel(buildTableModel(rs));
        }
        catch ( SQLException error ) { g.sqlError(error, "Error"); }
    }
    
    
    public void setupTabs()
    {
        // Human Resources Tabs.
        g.setTab(hrTabPane, createEmployeePanel, "Create");
        g.setTab(hrTabPane, searchEmployeePanel, "Search");
        g.setTab(hrTabPane, editEmployeePanel, "Edit/Delete");
        
        // Sub Inventory Tabs.
        g.setTab(subInventoryTabPane, createProductPanel, "Product");
        g.setTab(subInventoryTabPane, createManufacturerPanel, "Manufacturer");
        
        // Inventory Tabs.
        inventoryTabPane.addTab("Create", null, subInventoryTabPane, "Create");
        g.setTab(inventoryTabPane, searchInventoryPanel, "Search");
        g.setTab(inventoryTabPane, editInventoryPanel, "Edit/Delete");
        
        // Sales Tab.
        g.setTab(salesTabPane, createSalesPanel, "Create");
        g.setTab(salesTabPane, searchSalesPanel, "Search");
        g.setTab(salesTabPane, editSalesPanel, "Edit/Delete");
        
        // Setup main tabs.
        mainTabPane.addTab("HR", null , hrTabPane, "HR");
        mainTabPane.addTab("Inventory", null, inventoryTabPane, "Inventory");
        mainTabPane.addTab("Sales", null, salesTabPane, "Sales");
    }
    
} // End of UserGui class.