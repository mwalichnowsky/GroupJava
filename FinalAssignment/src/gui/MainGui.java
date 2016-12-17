package gui;

/**
 * @author Matthew Walichnowsky | 200171919
 * Brandens Db - username gc200315409
 * password- ?8pDT38G
 * url -https://phpmyadmin.dreamhost.com/?hostname=sql.computerstudi.es
 */

import general.Global;
import humanResources.Employee;
import humanResources.SalaryEmployee;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
        
public class MainGui extends JFrame
{
    /* ----------------------- Variables------------------------------------- */
    
         private JComboBox manufacturers = new JComboBox();
         
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
            greaterEmployeePanel = new JPanel(),
            
            /* Inventory Panels */    
            inventoryPanel = new JPanel(),
            searchInventoryPanel = new JPanel(),
            editInventoryPanel = new JPanel(),
            createProductPanel  = new JPanel(), 
            createManufacturerPanel = new JPanel(), 
            greaterProductPanel = new JPanel(),
            
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
            manufacturerButton = new JRadioButton("Manufacturers:"),
            searchProducts = new JRadioButton("Search Products", true),
            searchManufacturers = new JRadioButton("Search Manufacturer", false)
        ;
        
        JLabel inventoryLabel = new JLabel("Products:");
        
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
              
        ArrayList<Employee> myEmployees = new ArrayList(); // store all the employees in here 
        // Set variable for calling our global methods.
        Global g = new Global();
        
        boolean isAdmin;
        private int chooseEmployeeType;
        private int chooseProdocutOrManufacturer;
        
       
        
    /////////////////////// End of Variables ///////////////////////////////////
    
    /* ----------------------- Constructor ---------------------------------- */
        
        public MainGui(boolean isAdmin)
        {
            super("Prestige Worldwide");
            this.isAdmin = isAdmin;
            setLayout(new BorderLayout());
            
            textCommissionRate.setEditable(false);
            textHourlyRate.setEditable(false);
            
            // Build Panels.
            buildHeaderPanel("Welcome to Management Box!");
            buildCreateEmployeePanel();
            buildSearchEmployeePanel();
            buildSearchInventoryPanel();
            buildCreateProductPanel();
            buildCreateManufacturerPanel();
            buildCreateSalesPanel();
            buildFooterPanel();
            
            if (isAdmin)
            {
                buildEditEmployeesPanel();
                buildEditInventoryPanel();
                buildEditSalesPanel();
            }

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
            
            greaterEmployeePanel.setLayout(new BorderLayout());
            
            JPanel employeeButtonPanel = new JPanel();
            JButton submitEmployeeButton = new JButton("Submit");
            submitEmployeeButton.addActionListener(new SubmitEmployeeButtonListener());
            employeeButtonPanel.add(submitEmployeeButton);
            
            createEmployeePanel.setLayout(new BorderLayout());
            createEmployeePanel.add(employeeSelectionPanel, BorderLayout.NORTH);
            createEmployeePanel.add(employeePersonalInformation, BorderLayout.CENTER);
            createEmployeePanel.add(employeeWorkInformation, BorderLayout.SOUTH);
            
            greaterEmployeePanel.add(createEmployeePanel, BorderLayout.NORTH);
            greaterEmployeePanel.add(employeeButtonPanel, BorderLayout.SOUTH);
        }

        
        /**
         * This method builds the human resources search panel.
         */
        private void buildSearchEmployeePanel()
        {
            JPanel searchEmpNorthPanel = new JPanel();
            searchEmpNorthPanel.setLayout(new FlowLayout());
            g.border(searchEmployeePanel, "Search Employees:");
            
            JButton searchEmployeeButton = new JButton("Search");
            searchEmployeeButton.addActionListener(new SearchEmployeeButtonListener());
            
            JPanel searchEmpSouthPanel = new JPanel();
            searchEmpSouthPanel.add(searchEmployeeButton);
            
            searchEmpNorthPanel.add(new JLabel("Search Employees:"));
            searchEmpNorthPanel.add(textSearchEmployees);          
            areaSearchResults.setEditable(false);
            searchEmployeePanel.setLayout(new BorderLayout());
            JScrollPane scrollPane = new JScrollPane(employeeSearchTable);
            searchEmployeePanel.add(searchEmpNorthPanel, BorderLayout.NORTH);
            searchEmployeePanel.add(scrollPane, BorderLayout.CENTER);
            searchEmployeePanel.add(searchEmpSouthPanel, BorderLayout.SOUTH);
        }
        
        


        /**
         * This method builds the create product panel.
         */
        private void buildCreateProductPanel()
        {
            JPanel productNorthPanel = new JPanel();
            productNorthPanel.setLayout(new GridLayout(3,2));
            
            greaterProductPanel.setLayout(new BorderLayout());
            JButton searchProductButton = new JButton("Submit");
            searchProductButton.addActionListener(new SearchProductButtonListener());
            
            JPanel productSouthPanel = new JPanel();
            productSouthPanel.add(searchProductButton);
            
            areaProductSummary = new JTextArea("Summary / Notes Here");
            
            final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
         // query string
        final String QRY = "Select name FROM manufacturer";
        
        Connection conn = null;
        Statement stat = null;
        ResultSet result = null;
        
          try
            {
            conn = DriverManager.getConnection(DB_URL, "gc200315409", "?8pDT38G");
            stat = conn.createStatement();
            result = stat.executeQuery(QRY);
                
            // getting the food names and putting them in a array.
            ArrayList<String> itemNames = new ArrayList<String>();

            while(result.next())
            {
                try
                {
                    itemNames.add(result.getString("name" ));
                    System.out.println(itemNames.toString());
                } 
                catch (SQLException e) 
                {
                    System.out.println(e);
                }

            }
            
            // use a loop to store each item
            for (String string : itemNames) 
            {
                manufacturers.addItem(string);
      
            }
    }
         catch(SQLException e1)
         {
             System.out.println(e1);
         }

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
            createProductPanel.add(productNorthPanel, BorderLayout.NORTH);
            
            greaterProductPanel.add(createProductPanel, BorderLayout.CENTER);
            greaterProductPanel.add(productSouthPanel, BorderLayout.SOUTH);
        }
        
        private class SearchProductButtonListener implements ActionListener
        {
            @Override public void actionPerformed(ActionEvent e) 
            {
                try {
                    insertProduct();
                } catch (SQLException e1) {
                    System.out.println(e1);
                }
              
            }
        } // end of SearchProductButtonListener Listener


        /**
         * This method builds the create manufacturer panel.
         */
        private void buildCreateManufacturerPanel()
        {
            JPanel manNorthPanel = new JPanel();
            manNorthPanel.setLayout(new GridLayout(4,2));
            areaManufacturerSummary = new JTextArea("Summary / Notes Here");
            manNorthPanel.add(new JLabel("Name:"));
            manNorthPanel.add(textManufacturerName);
            manNorthPanel.add(new JLabel("Location:"));
            manNorthPanel.add(textManufacturerLocation);
            manNorthPanel.add(new JLabel("Phone Number:"));
            manNorthPanel.add(textManufacturerPhoneNumber);
            
             JButton createManufactuerButton = new JButton("Submit");
            createManufactuerButton.addActionListener(new CreateManufactuerButtonListener());
          

            
            // Setup manufacturing panel.
            createManufacturerPanel.setLayout(new BorderLayout());
            

            createManufacturerPanel.add(manNorthPanel, BorderLayout.NORTH);
            createManufacturerPanel.add(createManufactuerButton, BorderLayout.SOUTH);

        }
        
        private class CreateManufactuerButtonListener implements ActionListener
        {
            @Override public void actionPerformed(ActionEvent e) 
            {
                try {
                    insertManufacturer();
                } catch (Exception e1) {
                    System.out.println(e1);
                }
              
            }
        } // end of SearchProductButtonListener Listener


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
                  
            SearchRadioButtonHandler rbHandler = new SearchRadioButtonHandler();
            searchProducts.addItemListener(rbHandler);
            searchManufacturers.addItemListener(rbHandler);
            
            JButton searchInventoryButton = new JButton("Search");
            searchInventoryButton.addActionListener(new SearchInventoryButtonListener());
            JPanel searchInventorySouthPanel = new JPanel();
            searchInventorySouthPanel.add(searchInventoryButton);
            
            inventorySearchSelectionGroup.add(searchProducts);
            inventorySearchSelectionGroup.add(searchManufacturers);
            
            g.border(searchInventoryPanel, "Search Inventory:");
            invNorthPanel.add(searchProducts);
            invNorthPanel.add(searchManufacturers);
            invNorthPanel.add(new JLabel("Search For:"));
            invNorthPanel.add(textSearchInventory);
            
            // Setup search inventory panel.
            searchInventoryPanel.setLayout(new BorderLayout());
            if(chooseProdocutOrManufacturer ==0)
            {
            searchInventoryPanel.validate();
            searchInventoryPanel.repaint();
            JScrollPane scrollPane = new JScrollPane(productSearchTable); 
            searchInventoryPanel.add(invNorthPanel, BorderLayout.NORTH);
            searchInventoryPanel.add(scrollPane, BorderLayout.CENTER);
            searchInventoryPanel.add(searchInventorySouthPanel, BorderLayout.SOUTH);
            }
            else if(chooseProdocutOrManufacturer ==1)
            {
            searchInventoryPanel.validate();
            searchInventoryPanel.repaint();
            JScrollPane scrollPane = new JScrollPane(manufacturerSearchTable);
            searchInventoryPanel.add(invNorthPanel, BorderLayout.NORTH);
            searchInventoryPanel.add(scrollPane, BorderLayout.CENTER);
            searchInventoryPanel.add(searchInventorySouthPanel, BorderLayout.SOUTH);
            }
            
            
        }
        
        private class SearchInventoryButtonListener implements ActionListener
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                try
                {
                searchProductOrManufacturer();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                    
            }
        } // end of SearchInventoryButtonListener Listener
        
        private class SearchRadioButtonHandler implements ItemListener
        {
            @Override
            public void itemStateChanged(ItemEvent event)
            {
                chooseProdocutOrManufacturer = -1;
                //set the textfields to visible based on what radio button is selected
                
                if(searchProducts.isSelected())
                {
                    
                    // a way to determine what to search for
                    chooseProdocutOrManufacturer = 0;
                    
                    
                }
                // getting the manufactuer table
                else if(searchManufacturers.isSelected())
                {
                    chooseProdocutOrManufacturer = 1;
                    
                }
            }
        }//end of handler

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
            JComboBox employees = new JComboBox();
            //products.setSelectedIndex(0);
            
            g.border(editEmpNorthPanel, "Employee Information");
            editEmpNorthPanel.add(new JLabel("Employee:"));
            editEmpNorthPanel.add(employees);
            
            JPanel editEmpSouthPanel = new JPanel();
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
            editInvCenterPanel.setLayout(new BorderLayout());

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
            exitButton.addActionListener(new ExitButtonListener());

            // Add items to the panel.
            footerPanel.add(exitButton);
        }
    /////////////////////// End of Build Panels ////////////////////////////////
    
    /* --------- Action Listeners ------------------------------------------- */
        /**
         * Private inner class for event handling.
         */
        
        private class SearchEmployeeButtonListener implements ActionListener
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                // Add to database.
                    try 
                    {
                     searchEmployee();    
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        } // End of SearchEmployeeButtonListener inner class.
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
        private class SubmitEmployeeButtonListener implements ActionListener
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
                    try 
                    {
                    insertEmployee();    
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    
                    
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
                    //add to database
                    insertSales();
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
                chooseEmployeeType =-1;
                //set the textfields to visible based on what radio button is selected
                //set the salary text field to be visible
                if(salaryEmployee.isSelected())
                {
                    textSalary.setEditable(true);
                    textCommissionRate.setEditable(false);
                    textCommissionRate.setText("");
                    textHourlyRate.setEditable(false);
                    textHourlyRate.setText("");
                    chooseEmployeeType = 0;
                }
                //set the hourly text field to be visible
                else if(hourlyEmployee.isSelected())
                {
                    textSalary.setEditable(false);
                    textSalary.setText("");
                    textCommissionRate.setEditable(false);
                    textCommissionRate.setText("");
                    textHourlyRate.setEditable(true);
                    chooseEmployeeType=1;
                }
                //set the commission text field to be visible
                else if(commissionEmployee.isSelected())
                {
                    textSalary.setEditable(false);
                    textSalary.setText("");
                    textCommissionRate.setEditable(true);
                    textHourlyRate.setEditable(false);
                    textHourlyRate.setText("");
                    chooseEmployeeType=2;
                }
                //set the salary and commission text field to be visible
                else if(basePlusCommissionEmployee.isSelected())
                {
                    textSalary.setEditable(true);
                    textCommissionRate.setEditable(true);
                    textHourlyRate.setEditable(false);
                    textHourlyRate.setText("");
                    chooseEmployeeType=3;
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
                chooseProdocutOrManufacturer = -1;
                //set the textfields to visible based on what radio button is selected
                
                if(productButton.isSelected())
                {
                    inventoryLabel.setText("Products");
                    
                    
                    
                    
                }
                // getting the manufactuer table
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
    public static DefaultTableModel buildTableModel (ResultSet result)
            throws SQLException
    {
        ResultSetMetaData metaData = result.getMetaData();
        
        Vector<String> colNames = new Vector<String>();
        
        for( int column = 1; column<=metaData.getColumnCount();column++)
        {
            colNames.add(metaData.getColumnName(column));
        }
        
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        
        while(result.next())
        {
            Vector<Object> rowData = new Vector<Object>();
            
            for(int columnIndex = 1; columnIndex<=metaData.getColumnCount();columnIndex++)
            {
                rowData.add(result.getObject(columnIndex));
            }
            data.add(rowData);
        }
        return new DefaultTableModel(data,colNames);
        
    }

    
    public void buildTables()
    {
        
         final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
        String userName = "gc200315409";
        String password = "?8pDT38G";
        
        try 
        {
            conn = DriverManager.getConnection(DB_URL, userName,password);
        } catch (Exception e) {
        }

        // Employee Search Table.
        try
        {
            final String QRY = "Select * FROM Employee";
            stat = conn.createStatement();
            rs = stat.executeQuery(QRY);
            employeeSearchTable.setModel(buildTableModel(rs));
        }
        catch ( SQLException error ) { g.sqlError(error, "Error"); }
           
        // Product Search Table.
        try
        {
            final String QRY = "Select * FROM Products";
            stat = conn.createStatement();
            rs = stat.executeQuery(QRY);
            productSearchTable.setModel(buildTableModel(rs));
        }
        catch ( SQLException error ) { g.sqlError(error, "Error"); }
        
        // Manufacturer Search Table.
        try
        {
            final String QRY = "Select * FROM Manufacturer";
            stat = conn.createStatement();
            rs = stat.executeQuery(QRY);
            manufacturerSearchTable.setModel(buildTableModel(rs));
        }
        catch ( SQLException error ) { g.sqlError(error, "Error"); }
    }
    
    // inserting employee data into the database
    public  void insertEmployee()
            throws SQLException
    {       
        String userName = "gc200315409";
        String password = "?8pDT38G";
        
        final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
        conn = DriverManager.getConnection(DB_URL, userName,password);

        // adding a employee to the database
        try {
              stat = conn.createStatement();
              String SQL = "INSERT INTO employee"
                + " (`firstname`, `lastname`, `gender`, `age`, `address`, `dateofbirth`, `phonenumber`, `sin`, `datehired`, `position`, "
                + "`status`, `hourlyrate`, `commissionrate`, `salaryrate`, `department`)"
                + "VALUES ('"+textFirstName.getText()+"', '"+ textLastName.getText()+"', '"+ textGender.getText()+ "', '" 
                + textAge.getText()+"', '"+textAddress.getText()+"', '"+textDateOfBirth.getText() +"', '"+ textPhoneNumber.getText()+"', '"
                + textSIN.getText()+ "', '"+ textDateHired.getText()+ "', '"+ textPosition.getText() + "', '"+ textStatus.getText()+"', '" 
                + textHourlyRate.getText()+"', '"+ textCommissionRate.getText()+ "', '"+ textSalary.getText()+"', '"+textDepartment.getText()+ "');";
          
              System.out.println(SQL);
        stat.executeUpdate(SQL);
        } catch (Exception e) { 
            System.out.println(e);
        }
       
    }
    
    
    public void searchEmployee()
    {
        try {
            String userName = "gc200315409";
            String password = "?8pDT38G";

            final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
            
        
            String test = textSearchEmployees.getText().toString();
            final String QRY = "SELECT * FROM employee WHERE firstname LIKE '%"+test+"%';";
            
            Connection conn = null;
            Statement stat = null;
            ResultSet result = null;
            
            try
            {
                conn = DriverManager.getConnection(DB_URL, userName, password);
                stat = conn.createStatement();
                
                result = stat.executeQuery(QRY);
                ResultSetMetaData rsmd = result.getMetaData();

                
                int columnsNumber = rsmd.getColumnCount();
                ArrayList<Integer> resultList = new ArrayList<Integer>();
                while (result.next()) 
                {
                    //Print one row          
                    for(int i = 1 ; i <= columnsNumber; i++)
                    {

                    System.out.print(result.getString(i) + " "); //Print one element of a row
                    employeeSearchTable.setModel(buildTableModel(result)); //Print one element of a row
                     System.out.println(QRY);
                    }          
                }
                result.close();
                stat.close();
                conn.close();

            }
            catch(SQLException error)
            {
                System.out.println(error);
            }
            
            
 
        }catch (NullPointerException e1) {
            System.out.println(e1);
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertProduct()
            throws SQLException
    { String userName = "gc200315409";
        String password = "?8pDT38G";
        
        final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
        conn = DriverManager.getConnection(DB_URL, userName,password);

        try {
            stat = conn.createStatement();
            String SQL = "INSERT INTO products"
                + " (`name`, `code`, `price`, `manufacturer`)"
                + "VALUES ('"+textProductName.getText()+"', '"+ textProductCode.getText()+"', '"+ textProductPrice.getText()+"', '"+manufacturers.getSelectedIndex()+ "');";
                
            
            System.out.println("hi");
              System.out.println(SQL);
        stat.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e);
        }
        catch(Exception e1)
        {
            System.out.println(e1);
        }
   
    }
    
    public void insertManufacturer()
    {
        String userName = "gc200315409";
        String password = "?8pDT38G";
        
        final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
        

        try {
            conn = DriverManager.getConnection(DB_URL, userName,password);
            stat = conn.createStatement();
            String SQL = "INSERT INTO manufacturer"
                + " (`name`, `location`, `phonenumber`)"
                + "VALUES ('"+textManufacturerName.getText()+"', '"+ textManufacturerLocation.getText()+"', '"+ textManufacturerPhoneNumber.getText()+"');";

        System.out.println(SQL);
        stat.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e);
        }
        catch(Exception e1)
        {
            System.out.println(e1);
        }
    }
    
    public void searchProductOrManufacturer()
    {
        
            String userName = "gc200315409";
            String password = "?8pDT38G";

            final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
                            System.out.println(chooseProdocutOrManufacturer);

            String test = textSearchInventory.getText().toString();
            
            if(chooseProdocutOrManufacturer == 0)
            {
                final String QRY = "SELECT * FROM products WHERE name LIKE '%"+test+"%';";
            
            Connection conn = null;
            Statement stat = null;
            ResultSet result = null;
            
            try
            {
                conn = DriverManager.getConnection(DB_URL, userName, password);
                stat = conn.createStatement();
                
                result = stat.executeQuery(QRY);
                ResultSetMetaData rsmd = result.getMetaData();

                int columnsNumber = rsmd.getColumnCount();
                while (result.next()) {
                //Print one row          
                for(int i = 1 ; i <= columnsNumber; i++){

                  System.out.print(result.getString(i) + " "); //Print one element of a row
                   productSearchTable.setModel(buildTableModel(result)); //Print one element of a row
                    System.out.println(QRY);

                 }             

             }

            }
            catch(SQLException error)
            {
                System.out.println(error);
            }
            catch (NullPointerException e1) 
            {
                 System.out.println(e1);
             } 
             catch (Exception e) 
             {
                 System.out.println(e);
             }
          }
            else if( chooseProdocutOrManufacturer == 1)
            {
                System.out.println(chooseProdocutOrManufacturer);
                final String QRY = "SELECT * FROM manufacturer WHERE name LIKE '%"+test+"%';";
            
            Connection conn = null;
            Statement stat = null;
            ResultSet result = null;
            
            try
            {
                conn = DriverManager.getConnection(DB_URL, userName, password);
                stat = conn.createStatement();
                
                result = stat.executeQuery(QRY);
                ResultSetMetaData rsmd = result.getMetaData();

                int columnsNumber = rsmd.getColumnCount();
                while (result.next()) {
                //Print one row          
                for(int i = 1 ; i <= columnsNumber; i++){

                  System.out.print(result.getString(i) + " "); //Print one element of a row
                  manufacturerSearchTable.setModel(buildTableModel(result)); //Print one element of a row
                    System.out.println(QRY);

                 }             

             }

            }
            catch(SQLException error)
            {
                System.out.println(error);
            }
            catch (NullPointerException e1) 
            {
                 System.out.println(e1);
             } 
             catch (Exception e) 
             {
                 System.out.println(e);
             }
            }
    }
    
    public void insertSales()
    {
        String userName = "gc200315409";
        String password = "?8pDT38G";
        
        final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
        

        try {
            conn = DriverManager.getConnection(DB_URL, userName,password);
            stat = conn.createStatement();
            String SQL = "INSERT INTO sales"
                + " (`quantity`, `product`, `employee`)"
                + "VALUES ('"+textSalesQuantity.getText()+"', '"+ textManufacturerLocation.getText()+"', '"+ textManufacturerPhoneNumber.getText()+"');";

        System.out.println(SQL);
        stat.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e);
        }
        catch(Exception e1)
        {
            System.out.println(e1);
        }
        
    }
    
    public void setupTabs()
    {
        // Human Resources Tabs.
        g.setTab(hrTabPane, greaterEmployeePanel, "Create");
        g.setTab(hrTabPane, searchEmployeePanel, "Search");
        
        // Sub Inventory Tabs.
        g.setTab(subInventoryTabPane, greaterProductPanel, "Product");
        g.setTab(subInventoryTabPane, createManufacturerPanel, "Manufacturer");
        
        // Inventory Tabs.
        inventoryTabPane.addTab("Create", null, subInventoryTabPane, "Create");
        g.setTab(inventoryTabPane, searchInventoryPanel, "Search");
        
        // Sales Tab.
        g.setTab(salesTabPane, createSalesPanel, "Create");
        g.setTab(salesTabPane, searchSalesPanel, "Search");
        
        // Admin tabs.
        if (isAdmin == true)
        {
            g.setTab(hrTabPane, editEmployeePanel, "Edit/Delete");
            g.setTab(inventoryTabPane, editInventoryPanel, "Edit/Delete");
            g.setTab(salesTabPane, editSalesPanel, "Edit/Delete");
        }
        
        // Setup main tabs.
        mainTabPane.addTab("HR", null , hrTabPane, "HR");
        mainTabPane.addTab("Inventory", null, inventoryTabPane, "Inventory");
        mainTabPane.addTab("Sales", null, salesTabPane, "Sales");
    }
    
} // End of UserGui class.