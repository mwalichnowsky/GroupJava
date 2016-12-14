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
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
        
public class UserGui extends JFrame
{
    /* ----------------------- Variables------------------------------------- */
        // J Text Fields.
        private JTextField 
                
            /* Create Employee Work Info Fields */
            textSalary = new JTextField(15),
            textHourlyRate = new JTextField(15),
            textCommissionRate = new JTextField(15),
                
            /* Create Product Text Fields */
            textProductName, textProductNumber, textManufacturer, 
            textProductCode, textProductPrice,
                           
            /* Create Manufacturer Text Fields */
            textManufacturerName, textManufacturerLocation, 
            textManufacturerPhoneNumber, textManufacturerSalesAssociate,
                
            /* Search Text Field */
            textSearchEmployees, textSearchInventory,
                
            /* Sales Field */
            textQuantity, textSearchSales
        ;
        
        // J Text Ares.
        private JTextArea
                
            /* Search Text Field */
            textSearchResults, textSearchInventoryResults, textProductSummary,
            textManufacturerSummary
        ;
        
        // J Panels.
        final private JPanel 
            headerPanel = new JPanel(), footerPanel = new JPanel(),
                
            /* HR Panels */    
            employeePersonalInformation = new JPanel(), 
            employeeWorkInformation = new JPanel(), 
            searchEmployeePanel = new JPanel(), 
            inventorySearchSelectionPanel = new JPanel(),
            employeeSelectionPanel = new JPanel(), 
            searchEmployeeMasterPanel = new JPanel(), 
            createEmployeePanel = new JPanel(),
                
            /* Inventory Panels */    
            createProductPanel  = new JPanel(), inventoryPanel = new JPanel(),
            createManufacturerPanel = new JPanel(), 
            searchInventoryMasterPanel = new JPanel(),
            searchInventoryPanel = new JPanel(),
            createProductMasterPanel = new JPanel(),
            createManufacturerMasterPanel = new JPanel(),
                
            /* Sales Panels */
            salesMasterPanel = new JPanel(), createSalesPanel = new JPanel(), 
            searchSalesPanel = new JPanel()
        ;               
        
        // J Tables.
        final private JTable 
            employeeSearchTable = new JTable(),
            productSearchTable = new JTable(),
            manufacturerSearchTable = new JTable()
        ;
        
        // J Buttons.
        private JButton 
            exitButton = new JButton("Exit"), 
            submitButton = new JButton("Submit"), createButton, clearButton, 
            editButton, deleteButton
        ;
        
        // J Radio Buttons.
        final private JRadioButton 
            basePlusCommissionEmployee = new JRadioButton
                                       ("Base Plus Commission Employee", false), 
            commissionEmployee = new JRadioButton
                                                  ("Comission Employee", false), 
            hourlyEmployee = new JRadioButton("Hourly Employee", false), 
            salaryEmployee = new JRadioButton("Salary Employee", true)
        ;
        
        // JTabbed Interfaces.
        final private JTabbedPane 
            mainTabPane = new JTabbedPane(), // Main Tab.
            hrTabPane = new JTabbedPane(), // Human Resources Tab.
            inventoryTabPane = new JTabbedPane(), // Inventory Tab.
            subInventoryTabPane = new JTabbedPane(), // Sub Inventory Tab.
            salesTabPane = new JTabbedPane() // Sales Tab.
        ;
            
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
                
        Global g = new Global();
        
    /////////////////////// End of Variables ///////////////////////////////////
    
    /* ----------------------- Constructor ---------------------------------- */
        
        public UserGui()
        {
            super("Prestige Worldwide");
            setLayout(new BorderLayout());
            
            // Build Panels.
            buildHeaderPanel("Welcome to Management Box!");
            buildEmployeeSelectionPanel();
            buildCreateSalesPanel();
            buildCreateEmployeePanel();
            buildSearchEmployeePanel();
            buildSearchInventoryPanel();
            buildCreateProductPanel();
            buildCreateManufacturerPanel();
            buildFooterPanel();
            
            // Setup Panels.
            setupPanels();
            
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
        private void buildEmployeeSelectionPanel()
        {
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
            RadioButtonHandler rbHandler = new RadioButtonHandler();
            salaryEmployee.addItemListener(rbHandler);
            commissionEmployee.addItemListener(rbHandler);
            hourlyEmployee.addItemListener(rbHandler);
            basePlusCommissionEmployee.addItemListener(rbHandler);
        }


        /**
         * This creates the employee panel.
         */
        private void buildCreateEmployeePanel()
        {
            /* ----------- Employee Information Section --------------------- */

                employeePersonalInformation.setLayout(new GridLayout(4,2));

                // Create text boxes.
                JTextField
                    textFirstName = new JTextField(15),
                    textLastName = new JTextField(15),
                    textGender = new JTextField(15),
                    textAge = new JTextField(2),
                    textAddress = new JTextField(15),
                    textDateOfBirth = new JTextField(15),
                    textPhoneNumber = new JTextField(15),
                    textSIN = new JTextField(15)
                ;
                
                // Add Labels and Text to the panel.
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
                employeeWorkInformation.setLayout(new GridLayout(4,2));

                // Create text boxes.
                JTextField
                    textDateHired = new JTextField(15),
                    textPosition = new JTextField(15),
                    textStatus = new JTextField(15),
                    textDepartment = new JTextField(15)
                ;

                // Add Labels and Text to the panel.
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
        }

        
        /**
         * This method builds the human resources search panel.
         */
        private void buildSearchEmployeePanel()
        {
            searchEmployeePanel.setLayout(new FlowLayout());

            // Text.
            textSearchEmployees = new JTextField(15);
            textSearchResults = new JTextArea();
            textSearchResults.setEditable(false);
            
            // Panel.
            g.border(searchEmployeePanel, "Search Employees:");
            searchEmployeePanel.add(new JLabel("Search Employees:"));
            searchEmployeePanel.add(textSearchEmployees);
            searchEmployeePanel.add(textSearchResults);
        }


        /**
         * This method builds the create product panel.
         */
        private void buildCreateProductPanel()
        {
            createProductPanel.setLayout(new GridLayout(2,2));

            // Create text boxes.
            textProductName = new JTextField(15);
            textProductCode = new JTextField(15);
            textProductPrice = new JTextField(15);
            textProductSummary = new JTextArea("Summary / Notes Here");

            // Database Connection Here

            // Setup combo box for selecting manufacturers from the array.
            JComboBox manufacturers = new JComboBox();
            //manufacturers.setSelectedIndex(0);

            g.border(inventoryPanel, "Inventory Information");
            createProductPanel.add(new JLabel("Name:"));
            createProductPanel.add(textProductName);
            createProductPanel.add(new JLabel("Code:"));
            createProductPanel.add(textProductCode);
            createProductPanel.add(new JLabel("Price:"));
            createProductPanel.add(textProductPrice);
            createProductPanel.add(new JLabel("Manufacturer:"));
            createProductPanel.add(manufacturers);
        }


        /**
         * This method builds the create manufacturer panel.
         */
        private void buildCreateManufacturerPanel()
        {
            createManufacturerPanel.setLayout(new GridLayout(2,2));

            // Create text boxes.
            textManufacturerName = new JTextField(15);
            textManufacturerLocation = new JTextField(15);
            textManufacturerPhoneNumber = new JTextField(15);
            textManufacturerSalesAssociate = new JTextField(15);
            textManufacturerSummary = new JTextArea("Summary / Notes Here");

            createManufacturerPanel.add(new JLabel("Name:"));
            createManufacturerPanel.add(textManufacturerName);
            createManufacturerPanel.add(new JLabel("Location:"));
            createManufacturerPanel.add(textManufacturerLocation);
            createManufacturerPanel.add(new JLabel("Phone Number:"));
            createManufacturerPanel.add(textManufacturerPhoneNumber);
            createManufacturerPanel.add(new JLabel("Sales Associate:"));
            createManufacturerPanel.add(textManufacturerSalesAssociate);
        }


        /**
         * This creates the employee selection panel.
         * @param labelMessage 
         */
        private void buildSearchInventoryPanel()
        {
            searchInventoryPanel.setLayout(new FlowLayout());
            ButtonGroup inventorySearchSelectionGroup = new ButtonGroup();

            // Create text boxes.
            textSearchInventory = new JTextField(15);
            textSearchInventoryResults = new JTextArea();
            textSearchInventoryResults.setEditable(false);

            // Create buttons.
            JRadioButton
                searchProducts = new JRadioButton("Search Products", true),
                searchManufacturers = new JRadioButton
                                                  ("Search Manufacturer", false)
            ;

            inventorySearchSelectionGroup.add(searchProducts);
            inventorySearchSelectionGroup.add(searchManufacturers);

            g.border(searchInventoryPanel, "Search Inventory:");
            searchInventoryPanel.add(searchProducts);
            searchInventoryPanel.add(searchManufacturers);
            searchInventoryPanel.add(new JLabel("Search For:"));
            searchInventoryPanel.add(textSearchInventory);
        }


        private void buildCreateSalesPanel()
        {
            createSalesPanel.setLayout(new GridLayout(3,1));

            // Create text boxes.
            textQuantity = new JTextField(15);

             // Database Connection Here

            // Setup combo box for selecting manufacturers from the array.
            JComboBox products = new JComboBox();
            //products.setSelectedIndex(0);
            JComboBox employees = new JComboBox();
            //employees.setSelectedIndex(0);

            g.border(inventoryPanel, "Inventory Information");
            createSalesPanel.add(new JLabel("Quantity:"));
            createSalesPanel.add(textQuantity);
            createSalesPanel.add(new JLabel("Product:"));
            createSalesPanel.add(products);
            createSalesPanel.add(new JLabel("Employee:"));
            createSalesPanel.add(employees);
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
                // If Statement.
                if (JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to exit?",
                    "Exit?", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
                {
                    System.exit(0);
                }
                // ELSE: do nothing.
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
                // If Statement.
                if (JOptionPane.showConfirmDialog(null, 
                    "Are you sure you want to submit?",
                    "Exit?", JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)
                {
                    // Add to database.
                }
                // ELSE: do nothing.
            }
        } // End of SubmitButtonListener inner class.


        /**
         * Radio button handler listens for changes and updates text fields to
         * reflect what is available.
         */
        private class RadioButtonHandler implements ItemListener
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
    
    
    public void setupPanels()
    {
        // Build search table area.
        //buildTables();
        
        /* ---- Setup Employee Panel ---------------------------------------- */
            createEmployeePanel.setLayout(new BorderLayout());
            createEmployeePanel.add(employeeSelectionPanel, BorderLayout.NORTH);
            createEmployeePanel.add
                             (employeePersonalInformation, BorderLayout.CENTER);
            createEmployeePanel.add
                                  (employeeWorkInformation, BorderLayout.SOUTH);

            // Initialize defaults values.
            textCommissionRate.setEditable(false);
            textHourlyRate.setEditable(false);
            
            // Add the human resources search panels to the main search panel.
            searchEmployeeMasterPanel.setLayout(new BorderLayout());
            JScrollPane scrollHRPane = new JScrollPane(textSearchResults);
            searchEmployeeMasterPanel.add
                                      (searchEmployeePanel, BorderLayout.NORTH);
            searchEmployeeMasterPanel.add(scrollHRPane, BorderLayout.CENTER);
        /* ---- End of Setup Employee Panel --------------------------------- */
        
        /* ---- Setup Inventory Panel --------------------------------------- */
            // Setup search inventory panel.
            searchInventoryMasterPanel.setLayout(new BorderLayout());
            JScrollPane scrollInventoryPane
                                  = new JScrollPane(textSearchInventoryResults);
            searchInventoryMasterPanel.add
                                     (searchInventoryPanel, BorderLayout.NORTH);
            searchInventoryMasterPanel.add
                              (scrollInventoryPane, BorderLayout.CENTER);

            // Setup product panel.
            createProductMasterPanel.setLayout(new BorderLayout());
            JScrollPane scrollProductPane
                                          = new JScrollPane(textProductSummary);
            createProductMasterPanel.add
                                       (createProductPanel, BorderLayout.NORTH);
            createProductMasterPanel.add
                                       (scrollProductPane, BorderLayout.CENTER);

            // Setup manufacturing panel.
            createManufacturerMasterPanel.setLayout(new BorderLayout());
            JScrollPane scrollManufacturerPane
                                     = new JScrollPane(textManufacturerSummary);
            createManufacturerMasterPanel.add
                                  (createManufacturerPanel, BorderLayout.NORTH);
            createManufacturerMasterPanel.add
                                  (scrollManufacturerPane, BorderLayout.CENTER);
        /* ---- End of Setup Inventory Panel -------------------------------- */
    }
    
    
    public void setupTabs()
    {
        // Sub Inventory Tabs.
        subInventoryTabPane.addTab
                         ("Product", null, createProductMasterPanel, "Product");
        subInventoryTabPane.addTab
          ("Manufacturer", null, createManufacturerMasterPanel, "Manufacturer");

        // Sales Tab.
        salesTabPane.addTab("Create", null, createSalesPanel, "Create");
        salesTabPane.addTab("Search", null, searchSalesPanel, "Search");    

        // Inventory Tabs.
        inventoryTabPane.addTab("Create", null, subInventoryTabPane, "Create");
        inventoryTabPane.addTab
                         ("Search", null, searchInventoryMasterPanel, "Search");    

        // Human Resources Tabs.
        hrTabPane.addTab
              ("Create Employee", null, createEmployeePanel, "Create Employee");
        hrTabPane.addTab("Search", null, searchEmployeeMasterPanel, "Search");

        // Setup main tabs.
        mainTabPane.addTab("HR", null , hrTabPane, "HR");
        mainTabPane.addTab("Inventory", null, inventoryTabPane, "Inventory");
        mainTabPane.addTab("Sales", null, salesTabPane, "Sales");
    }
    
} // End of UserGui class.