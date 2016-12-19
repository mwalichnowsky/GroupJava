/**
 * @author Matthew Walichnowsky | 200171919
 * Branden's Db - username gc200315409
 * password- ?8pDT38G
 * url -https://phpmyadmin.dreamhost.com/?hostname=sql.computerstudi.es
 */
package gui;

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
    
        private JComboBox 
            manufacturersEditComboBox = new JComboBox(), 
            employeeComboBox = new JComboBox(),
            productEditComboBox = new JComboBox(),
            productSalesComboBox = new JComboBox(),
            employeeCreateSalesComboBox = new JComboBox(),
            productCreateSalesComboBox = new JComboBox(),
            manCreateProductComboBox = new JComboBox()
        ;
         
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
            invNorthPanel = new JPanel(),
            
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
            createSaleButton = new JButton("Create"),
            clearSaleButton = new JButton("Clear"), 
            editEmployeeButton = new JButton("Edit"), 
            deleteEmployeeButton = new JButton("Delete"),
            editSalesButton = new JButton("Edit"), 
            deleteSalesButton = new JButton("Delete"),
            editInventoryButton = new JButton("Edit"), 
            deleteInventoryButton = new JButton("Delete"),
            searchSalesButton= new JButton("Search"),
            createManufactuerButton = new JButton("Create"),
            searchEmployeeButton = new JButton("Search")
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
        
        private String selection = "products", qry;
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
        JScrollPane manufacturerScrollPane = new JScrollPane(manufacturerSearchTable);
        JScrollPane productScrollPane = new JScrollPane(productSearchTable); 
        
       
        
    /////////////////////// End of Variables ///////////////////////////////////
    
    /* ----------------------- Constructor ---------------------------------- */
        
        public MainGui(boolean isAdmin)
        {
            super("Prestige Worldwide");
            this.isAdmin = isAdmin;
            System.out.println(isAdmin);
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
            buildSearchSalesPanel();
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
        }//End of buildEmployeeSelectionPanel

        
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
        }//End of buildCreateEmployeePanel
        
        
        /**
         * This method builds the human resources search panel.
         */
        private void buildSearchEmployeePanel()
        {
            g.border(searchEmployeePanel, "Search Employees:");
            
            // North
            JPanel searchEmpNorthPanel = new JPanel();
            searchEmpNorthPanel.setLayout(new GridLayout(1,1));
            searchEmpNorthPanel.add(new JLabel("Search Employees:"));
            searchEmpNorthPanel.add(textSearchEmployees); 
            
            // Center
            areaSearchResults.setEditable(false);
            searchEmployeePanel.setLayout(new FlowLayout());
            JScrollPane scrollPane = new JScrollPane(employeeSearchTable);
            
            // South
            JPanel searchEmpSouthPanel = new JPanel();
            searchEmpSouthPanel.setLayout(new FlowLayout());
            searchEmployeeButton.addActionListener(new SearchEmployeeButtonListener());
            searchEmpSouthPanel.add(searchEmployeeButton);
            
            searchEmployeePanel.add(searchEmpNorthPanel, BorderLayout.NORTH);
            searchEmployeePanel.add(scrollPane, BorderLayout.CENTER);
            searchEmployeePanel.add(searchEmpSouthPanel, BorderLayout.SOUTH);
        }//End of buildSearchEmployeePanel
        
        
        /**
         * This method builds the create product panel.
         */
        private void buildCreateProductPanel()
        {
            JPanel productNorthPanel = new JPanel();
            productNorthPanel.setLayout(new GridLayout(2,4));
            
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
                    catch(SQLException e1)
                    {
                        g.sqlError(e1, "Error");
                    }

                }
                // use a loop to store each item
                for (String string : itemNames) 
                {
                    manCreateProductComboBox.addItem(string);
                }
            }
            catch(SQLException e1)
            {
                g.sqlError(e1, "Error");
            }
            catch(Exception e1)
            {
                g.generalError(e1, "Error");
            }

            g.border(inventoryPanel, "Inventory Information");
            productNorthPanel.add(new JLabel("Name:"));
            productNorthPanel.add(textProductName);
            productNorthPanel.add(new JLabel("Code:"));
            productNorthPanel.add(textProductCode);
            productNorthPanel.add(new JLabel("Price:"));
            productNorthPanel.add(textProductPrice);
            productNorthPanel.add(new JLabel("Manufacturer:"));
            productNorthPanel.add(manCreateProductComboBox);
            
            // Setup product panel.
            createProductPanel.setLayout(new BorderLayout());
            createProductPanel.add(productNorthPanel, BorderLayout.NORTH);
            
            greaterProductPanel.add(createProductPanel, BorderLayout.CENTER);
            greaterProductPanel.add(productSouthPanel, BorderLayout.SOUTH);
        }


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
            
            createManufactuerButton.addActionListener(new CreateManufactuerButtonListener());
            
            // Setup manufacturing panel.
            createManufacturerPanel.setLayout(new BorderLayout());

            createManufacturerPanel.add(manNorthPanel, BorderLayout.NORTH);
            createManufacturerPanel.add(createManufactuerButton, BorderLayout.SOUTH);
        }
        

       /**
         * This creates the employee selection panel.
         * @param labelMessage 
         */
        private void buildSearchInventoryPanel()
        {
            
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
            
            searchInventoryPanel.add(invNorthPanel, BorderLayout.NORTH);
            searchInventoryPanel.add(searchInventorySouthPanel, BorderLayout.SOUTH);
            
            
        }//End of buildSearchInventoryPanel
        

        /**
         * This method creates the sales panels used for creating new orders.
         */
        private void buildCreateSalesPanel()
        {
            JPanel salesNorthPanel = new JPanel();
            salesNorthPanel.setLayout(new GridLayout(3,2));
            
            g.border(salesNorthPanel, "Sales Information");
            salesNorthPanel.add(new JLabel("Quantity:"));
            salesNorthPanel.add(textSalesQuantity);
            salesNorthPanel.add(new JLabel("Product:"));
            salesNorthPanel.add(productCreateSalesComboBox);
            salesNorthPanel.add(new JLabel("Employee:"));
            salesNorthPanel.add(employeeCreateSalesComboBox);
            
            JPanel salesSouthPanel = new JPanel();
            salesSouthPanel.setLayout(new FlowLayout());
            salesSouthPanel.add(createSaleButton);
            salesSouthPanel.add(clearSaleButton);
            
            // Action Listeners.
            createSaleButton.addActionListener(new CreateSalesButtonListener());
            clearSaleButton.addActionListener(new ClearSalesButtonListener());
            
            createSalesPanel.setLayout(new BorderLayout());
            createSalesPanel.add(salesNorthPanel, BorderLayout.NORTH);
            createSalesPanel.add(salesSouthPanel, BorderLayout.SOUTH);
        }//End of buildCreateSalesPanel

        
        /**
         * This method creates the edit sales panels used for making
         * changes to sales or deleting them.
         */
        private void buildEditSalesPanel()
        {
            JPanel editSalesNorthPanel = new JPanel();
            editSalesNorthPanel.setLayout(new GridLayout(3,2));
            
            populateSalesComboBox();
            
            g.border(editSalesNorthPanel, "Sales Information");
            editSalesNorthPanel.add(new JLabel("Sale:"));
            editSalesNorthPanel.add(productSalesComboBox);
            
            JPanel editSalesSouthPanel = new JPanel();
            editSalesSouthPanel.setLayout(new FlowLayout());
            editSalesSouthPanel.add(editSalesButton);
            editSalesSouthPanel.add(deleteSalesButton);
            
            // Action Listeners.
            editSalesButton.addActionListener(new EditButtonListener());
            deleteSalesButton.addActionListener
                                              (new DeleteSalesButtonListener());
            
            editSalesPanel.setLayout(new BorderLayout());
            editSalesPanel.add(editSalesNorthPanel, BorderLayout.NORTH);
            editSalesPanel.add(editSalesSouthPanel, BorderLayout.SOUTH);
        }//End of buildEditSalesPanel
        
        
        /**
         * This method creates the edit employees panels used for making
         * changes to employees or deleting them.
         */
        private void buildEditEmployeesPanel()
        {
            JPanel editEmpNorthPanel = new JPanel();
            editEmpNorthPanel.setLayout(new GridLayout(3,2));

            populateEmployeeComboBox();
            
            g.border(editEmpNorthPanel, "Employee Information");
            editEmpNorthPanel.add(new JLabel("Employee:"));
            editEmpNorthPanel.add(employeeComboBox);
            
            JPanel editEmpSouthPanel = new JPanel();
            g.border(editEmpSouthPanel, "Options");
            editEmpSouthPanel.setLayout(new FlowLayout());
            editEmpSouthPanel.add(editEmployeeButton);
            editEmpSouthPanel.add(deleteEmployeeButton);
            
            // Action Listeners.
            editEmployeeButton.addActionListener(new EditButtonListener());
            deleteEmployeeButton.addActionListener
                                           (new DeleteEmployeeButtonListener());
            
            editEmployeePanel.setLayout(new BorderLayout());
            editEmployeePanel.add(editEmpNorthPanel, BorderLayout.NORTH);
            editEmployeePanel.add(editEmpSouthPanel, BorderLayout.CENTER);
        }//End of buildEditEmployeesPanel
        
        
        /**
         * This method creates the edit inventory panel used for making
         * changes to inventory or deleting them.
         */
        private void buildEditInventoryPanel()
        {
            JPanel editInvNorthPanel = new JPanel();
            editInvNorthPanel.setLayout(new GridLayout(1,1));
            ButtonGroup editButtonGroup = new ButtonGroup();
            editButtonGroup.add(productButton);
            editButtonGroup.add(manufacturerButton);
            editInvNorthPanel.add(productButton);
            editInvNorthPanel.add(manufacturerButton);
            
            populateInventoryComboBox("products");
            
            JPanel editInvCenterPanel = new JPanel();
            editInvCenterPanel.setLayout(new BorderLayout());

            g.border(editInvCenterPanel, "Inventory Information");
            editInvCenterPanel.setLayout(new FlowLayout());
            editInvCenterPanel.add(inventoryLabel);
            editInvCenterPanel.add(productEditComboBox);
            
            JPanel editInvSouthPanel = new JPanel();
            editInvSouthPanel.setLayout(new FlowLayout());
            editInvSouthPanel.add(editInventoryButton);
            editInvSouthPanel.add(deleteInventoryButton);
            
            // Action Listeners.
            editInventoryButton.addActionListener(new EditButtonListener());
            deleteInventoryButton.addActionListener
                                          (new DeleteInventoryButtonListener());
            InvRadioButtonHandler rbHandler = new InvRadioButtonHandler();
            productButton.addItemListener(rbHandler);
            manufacturerButton.addItemListener(rbHandler);
            
            editInventoryPanel.setLayout(new BorderLayout());
            editInventoryPanel.add(editInvNorthPanel, BorderLayout.NORTH);
            editInventoryPanel.add(editInvCenterPanel, BorderLayout.CENTER);
            editInventoryPanel.add(editInvSouthPanel, BorderLayout.SOUTH);
        }//End of buildEditInventoryPanel
        
        
        /**
         * This method builds the human resources search panel.
         */
        private void buildSearchSalesPanel()
        {
            JPanel searchSalesNorthPanel = new JPanel();
            searchSalesNorthPanel.setLayout(new FlowLayout());
            g.border(searchSalesNorthPanel, "Search Employees:");
            
            searchSalesButton.addActionListener(new SearchSalesButtonListener());
            
            JPanel searchSalesSouthPanel = new JPanel();
            searchSalesSouthPanel.add(searchSalesButton);
            
            searchSalesNorthPanel.add(new JLabel("Search Sales:"));
            searchSalesNorthPanel.add(textSearchEmployees);          
            areaSearchResults.setEditable(false);
            searchSalesPanel.setLayout(new BorderLayout());
            JScrollPane scrollPane = new JScrollPane(employeeSearchTable);
            searchSalesPanel.add(searchSalesNorthPanel, BorderLayout.NORTH);
            searchSalesPanel.add(scrollPane, BorderLayout.CENTER);
            searchSalesPanel.add(searchSalesSouthPanel, BorderLayout.SOUTH);
        }//End of buildSearchEmployeePanel

        
        /**
         * This creates the panel for our button.
         */
        private void buildFooterPanel()
        {
            // Action Listeners.
            exitButton.addActionListener(new ExitButtonListener());

            // Add items to the panel.
            footerPanel.add(exitButton);
        }//End of buildFooterPanel
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
                    System.out.print(e);
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
                        System.out.print(e);
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
        private class CreateSalesButtonListener implements ActionListener
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
                    try
                    {
                        //add to database
                        insertSales();
                        
                        
                    } 
                    catch (Exception e) 
                    {
                        System.out.print(e);
                    } 
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
        private class DeleteEmployeeButtonListener implements ActionListener
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
                    String emp = employeeComboBox.getSelectedItem().toString();
                    
                    // Delete from database.
                    qry = g.deleteQuery("employee", "firstname", emp);
                    qry = "DELETE FROM employee WHERE firstname = '"+emp+"' ;";
                    System.out.println("Delete Query: " + "employee" 
                            + "firstname" + emp);
                    delete(qry);
                }
            }
        } // End of DeleteButtonListener inner class.
        
        
                /**
         * Private inner class for event handling.
         */
        private class DeleteInventoryButtonListener implements ActionListener
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
                    String emp = employeeComboBox.getSelectedItem().toString();
                    
                    // Delete from database.
                    qry = g.deleteQuery("employee", "firstname", emp);
                }
            }
        } // End of DeleteButtonListener inner class.
        
        
                /**
         * Private inner class for event handling.
         */
        private class DeleteSalesButtonListener implements ActionListener
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
                    String prod = productSalesComboBox.getSelectedItem().toString();
                    
                    // Delete from database.
                    //qry = g.deleteQuery("sales", "product", prod);
                    qry = "DELETE FROM sales WHERE product = '"+prod+"' ;";
                    delete(qry);
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
                //set the textfields to visible based on what radio button is selected
                
                if(productButton.isSelected())
                {
                    inventoryLabel.setText("Products");
                    populateInventoryComboBox("products");
                }
                // getting the manufactuer table
                else if(manufacturerButton.isSelected())
                {
                    inventoryLabel.setText("Manufacturers");
                    populateInventoryComboBox("manufacturers");
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
                //populateEmployeeComboBox();
            }
        } // End of SubmitButtonListener inner class.
        
        
/**fix**/private class SearchProductButtonListener implements ActionListener
        {
            @Override public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    insertProduct();
                } catch(SQLException e1)
                {
                    g.sqlError(e1, "Error");
                }
              
            }
        } // end of SearchProductButtonListener Listener
        
        
/**fix**/private class SearchSalesButtonListener implements ActionListener
        {
            @Override public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    insertProduct(); // Update to search method.
                } 
                catch(SQLException e1)
                {
                    g.sqlError(e1, "Error");
                }
              
            }
        } // end of SearchProductButtonListener Listener
        
        
        private class CreateManufactuerButtonListener implements ActionListener
        {
            @Override public void actionPerformed(ActionEvent e) 
            {
                try 
                {
                    insertManufacturer();
                } 
                catch (Exception e1) 
                {
                    g.generalError(e1, "Error");
                }
              
            }
        } // end of SearchProductButtonListener Listener
        
        
        private class SearchInventoryButtonListener implements ActionListener
        {
            @Override public void actionPerformed(ActionEvent event)
            {
                try
                {
                 searchProductOrManufacturer();
                 manufacturerScrollPane = new JScrollPane(manufacturerSearchTable);
                 productScrollPane = new JScrollPane(productSearchTable); 
                   
                    
                    if(chooseProdocutOrManufacturer == 0)
            {
                revalidate();
                repaint();

                searchInventoryPanel.add(productScrollPane, BorderLayout.CENTER);
               
                System.out.println(chooseProdocutOrManufacturer);

            }
                    
            // if statment to deciede what table to use
            else if(chooseProdocutOrManufacturer == 1)
            {
                revalidate();
                repaint();
                searchInventoryPanel.add(manufacturerScrollPane, BorderLayout.CENTER);
                System.out.println(chooseProdocutOrManufacturer);
            }
                    
                    
                }
                catch(Exception e1)
                {
                    g.generalError(e1, "Error");
                }
               
            }
        } // end of SearchInventoryButtonListener Listener
        
        
        private class SearchRadioButtonHandler implements ItemListener
        {
            @Override
            public void itemStateChanged(ItemEvent event)
            {
                // this will be used to determine what table is selected
                
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
                else
                {
                    
                }
            }
        }//end of handler
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
    }//End of buildTableModel

    
    public void buildTables()
    {
        
        final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
        String userName = "gc200315409";
        String password = "?8pDT38G";

        try
        {
            conn = DriverManager.getConnection(DB_URL, userName,password);
        }
        catch(SQLException e)
        {
            g.sqlError(e, "Error"); 
        }
        catch(Exception e1)
        {
            g.generalError(e1, "Error");
        }
        
        //Recently Added by Ross to ensure integrity. Remove
        //If errors arise.
        if(conn != null)
        {
            // Employee Search Table.
            try
            {
                final String QRY = "Select * FROM Employee";

                stat = conn.createStatement();
                rs = stat.executeQuery(QRY);

                employeeSearchTable.setModel(buildTableModel(rs));
            }
            catch (SQLException error) 
            { 
                g.sqlError(error, "Error"); 
            }
            catch(Exception e1)
            {
                g.generalError(e1, "Error");
            }

            // Product Search Table.
            try
            {
                final String QRY = "Select * FROM Products";

                stat = conn.createStatement();
                rs = stat.executeQuery(QRY);

                productSearchTable.setModel(buildTableModel(rs));
            }
            catch (SQLException error) 
            {
                g.sqlError(error, "Error"); 
            }
            catch(Exception e1)
            {
                g.generalError(e1, "Error");
            }

            // Manufacturer Search Table.
            try
            {
                final String QRY = "Select * FROM Manufacturer";

                stat = conn.createStatement();
                rs = stat.executeQuery(QRY);

                manufacturerSearchTable.setModel(buildTableModel(rs));
            }
            catch (SQLException error) 
            { 
                g.sqlError(error, "Error"); 
            }
            catch(Exception e1)
            {
                g.generalError(e1, "Error");
            }
        }
    }
    
    // inserting employee data into the database
    public  void insertEmployee()
            throws SQLException
    {       
        String userName = "gc200315409";
        String password = "?8pDT38G";
        
        final String DB_URL = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";

        try
        {
            conn = DriverManager.getConnection(DB_URL, userName,password);
        }
        catch(SQLException e)
        {
            g.sqlError(e, "Error"); 
        }
        catch(Exception e1)
        {
            g.generalError(e1, "Error");
        }
        
        if(conn != null)
        {
            // adding a employee to the database
            try 
            {
                stat = conn.createStatement();
                
                String SQL = "INSERT INTO employee"
                        
                    + " (`firstname`, `lastname`, `gender`, `age`, `address`, "
                    + "`dateofbirth`, `phonenumber`, `sin`, `datehired`, "
                    + "`position`, `status`, `hourlyrate`, `commissionrate`, "
                    + "`salaryrate`, `department`) "
                        
                    + "VALUES ('"+textFirstName.getText()+"', " + "'"
                    + textLastName.getText() + "', '" + textGender.getText()
                    + "', '" + textAge.getText() + "', '"
                    + textAddress.getText() + "', '" + textDateOfBirth.getText() 
                    + "', '" + textPhoneNumber.getText() + "', '" 
                    + textSIN.getText() + "', '" + textDateHired.getText()
                    + "', '" + textPosition.getText() + "', '" 
                    + textStatus.getText() + "', '" + textHourlyRate.getText()
                    + "', '" + textCommissionRate.getText() + "', '" 
                    + textSalary.getText() + "', '" + textDepartment.getText()
                    + "');";
                
                stat.executeUpdate(SQL);
            }
            catch(SQLException e)
            {
                g.sqlError(e, "Error"); 
            }
            catch(Exception e1)
            {
                g.generalError(e1, "Error");
            }
        }
    }//End of insertEmployee
    
    
    public void populateEmployeeComboBox()
    {
        try
        {
            final String qry = "SELECT * FROM employee ";
            
            conn = DriverManager.getConnection
            (
                g.getDb(), g.getDbUser(), g.getDbPass()
            );
            
            stat = conn.createStatement();
            
            rs = stat.executeQuery(qry);
            
            while (rs.next())
            {
                employeeComboBox.addItem(rs.getString("firstName"));
                employeeCreateSalesComboBox.addItem(rs.getString("firstName"));
            }
        }
        catch (SQLException e) 
        {
            g.sqlError(e, "");
        } 
    }
    
    
    public void populateInventoryComboBox(String selection)
    {
        try
        {
            conn = DriverManager.getConnection
            (
                g.getDb(), g.getDbUser(), g.getDbPass()
            );
            
            stat = conn.createStatement();
            
            if (selection == "products")
            {
                String qry = "SELECT * FROM products";
                rs = stat.executeQuery(qry);
                
                while (rs.next())
                {
                    productEditComboBox.addItem(rs.getString("name"));
                    productCreateSalesComboBox.addItem(rs.getString("name"));
                }
            } 
            else if (selection == "manufacturers")
            {
                String qry = "SELECT * FROM manufacturer";
                rs = stat.executeQuery(qry);
                
                while (rs.next())
                {
                    manufacturersEditComboBox.addItem(rs.getString("name"));
                    manCreateProductComboBox.addItem(rs.getString("name"));
                }
            }
        }
        catch (SQLException e) 
        {
            g.sqlError(e, "");
        } 
    }
    
    
    public void populateSalesComboBox()
    {
        try
        {
            final String qry = "SELECT * FROM sales ";
            
            conn = DriverManager.getConnection
            (
                g.getDb(), g.getDbUser(), g.getDbPass()
            );
            
            stat = conn.createStatement();
            
            rs = stat.executeQuery(qry);
            
            while (rs.next())
            {
                productSalesComboBox.addItem(rs.getString("product"));
            }
        }
        catch (SQLException e) 
        {
            g.sqlError(e, "");
        } 
    }
    
    
    // search for a Employee
    public void searchEmployee()
    {
        try 
        {
            String text = textSearchEmployees.getText();
            
            final String QRY = "SELECT * FROM employee "
                             + "WHERE firstname LIKE '%"+text+"%';";
            
            try
            {
                conn = DriverManager.getConnection
                (
                    g.getDb(), g.getDbUser(), g.getDbPass()
                );
                
                stat = conn.createStatement();
                rs = stat.executeQuery(QRY);
                ResultSetMetaData rsmd = rs.getMetaData();
                
                employeeSearchTable.setModel(buildTableModel(rs)); //Print one element of a row
                        
                rs.close();
                stat.close();
                conn.close();
            }
            catch(SQLException e)
            {
                g.sqlError(e, "Error"); 
            }
 
        }
        catch (NullPointerException e2) 
        {
            g.generalError(e2, "NullPointerException Error");
        }
        catch(Exception e1)
        {
            g.generalError(e1, "Error");
        }
    }
    
    
    // method to insert a product
    public void insertProduct()
            throws SQLException
    { 
        conn = DriverManager.getConnection
        (
            g.getDb(), 
            g.getDbUser(),
            g.getDbPass()
        );

        try 
        {

            String value = manCreateProductComboBox.getSelectedItem().toString();

            stat = conn.createStatement();
            
            String SQL = "INSERT INTO products"
                + " (`name`, `code`, `price`, `manufacturer`)"
                + "VALUES ('" + textProductName.getText() + "', '" 
                + textProductCode.getText() + "', '" 
                + textProductPrice.getText() + "', '" + value +  "');";
                
            //System.out.println(SQL);
            stat.executeUpdate(SQL);
        }
        catch(SQLException e)
        {
            g.sqlError(e, "Error"); 
        }
        catch(Exception e1)
        {
            g.generalError(e1, "Error");
        }
    }
    
    
    // A method to insert a new manufactuer
    public void insertManufacturer()
    {
        try 
        {
            conn = DriverManager.getConnection
            (
                g.getDb(), g.getDbUser(), g.getDbPass()
            );
            
            stat = conn.createStatement();
            
            qry = "INSERT INTO manufacturer"
                + " (`name`, `location`, `phonenumber`)"
                + "VALUES ('" + textManufacturerName.getText() + "', '"
                + textManufacturerLocation.getText() + "', '" 
                + textManufacturerPhoneNumber.getText() + "');";
                
            //System.out.println(SQL);
            stat.executeUpdate(qry);
        }
        catch(SQLException e)
        {
            g.sqlError(e, "Error"); 
        }
        catch(Exception e1)
        {
            g.generalError(e1, "Error");
        }
    }
    
    
    // a method to search for either product or manufactuer
    public void searchProductOrManufacturer()
    {
        String test = textSearchInventory.getText();
            
            // to get the user input for a Product
            if(chooseProdocutOrManufacturer == 0)
            {
                // getting the submited text
                final String QRY = "SELECT * FROM products WHERE name LIKE '%"+test+"%';";
            
                try
                {
                    conn = DriverManager.getConnection
                    (
                        g.getDb(), 
                        g.getDbUser(),
                        g.getDbPass()
                    );
                    stat = conn.createStatement();

                    rs = stat.executeQuery(QRY);
                    ResultSetMetaData rsmd = rs.getMetaData();

                    productSearchTable.setModel(buildTableModel(rs)); //Print one element of a row

                }//Begin catch
                catch(SQLException e)
                {
                    g.sqlError(e, "Error"); 
                }
                catch(NullPointerException e2)
                {
                    g.generalError(e2, "Error");
                }
                catch(Exception e1)
                {
                    g.generalError(e1, "Error");
                }
            }//End of if(chooseProdocutOrManufacturer == 0)
            
            // to select a manufactuer
            else if( chooseProdocutOrManufacturer == 1)
            {
                
                // select statement to get user inupt for a manufactuer
                final String QRY = "SELECT * FROM manufacturer WHERE name LIKE '%"+test+"%';";
            
                try
                {
                    conn = DriverManager.getConnection
                    (
                        g.getDb(), 
                        g.getDbUser(),
                        g.getDbPass()
                    );
                    stat = conn.createStatement();

                    rs = stat.executeQuery(QRY);
                    ResultSetMetaData rsmd = rs.getMetaData();
                    manufacturerSearchTable.setModel(buildTableModel(rs)); //Print one element of a row
                    
                }
                catch(SQLException e)
                {
                    g.sqlError(e, "Error"); 
                }
                catch(NullPointerException e2)
                {
                    g.generalError(e2, "Error");
                }
                catch(Exception e1)
                {
                    g.generalError(e1, "Error");
                }
            }
    }
    
    
    // a method to insert a sale
    public void insertSales()
    {
        try 
        {
            conn = DriverManager.getConnection
            (
                g.getDb(), 
                g.getDbUser(),
                g.getDbPass()
            );
            
            stat = conn.createStatement();
            
            qry = "INSERT INTO sales"
                + " (`quantity`, `product`, `employee`)"
                + "VALUES ('"+textSalesQuantity.getText()+"', '"
                + productCreateSalesComboBox.getSelectedItem().toString()+"', '"
                + employeeCreateSalesComboBox.getSelectedItem().toString()+"');";
            
            stat.executeUpdate(qry);
        
        } 
        catch(SQLException e)
        {
            g.sqlError(e, "Error"); 
        }
        catch(NullPointerException e2)
        {
            g.generalError(e2, "Error");
        }
        catch(Exception e1)
        {
            g.generalError(e1, "Error");
        }
    }//End of insertSales

    
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
    }//End of setupTabs

    
    public void delete(String qry)
    {
        try
        {
            System.out.println(qry);
            stat = g.getConn().createStatement();
            stat.executeUpdate(qry);
        }
        catch(SQLException e)
        {
            g.sqlError(e, "Error"); 
        }
        catch(NullPointerException e2)
        {
            g.generalError(e2, "Error");
        }
        catch(Exception e1)
        {
            g.generalError(e1, "Error");
        }
    } // End of delete.
        
        
} // End of MainGui class.