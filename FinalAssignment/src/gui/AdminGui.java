package gui;


/**
 * @author Matthew Walichnowsky | 200171919
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
        
public class AdminGui extends JFrame
{
    /* ----------------------- Variables------------------------------------- */
        // J Labels.    
        private JLabel labelGreeting,
                
            /* Personal Information Labels */   
            labelFirstName, labelLastName, labelGender, labelAge, labelAddress, 
            labelDateOfBirth, labelPhoneNumber, labelSIN,
                
            /* Work Information Labels */    
            labelEmployeeNumber, labelDateHired, labelPosition, labelStatus, 
            labelHourlyRate, labelCommissionRate, labelDepartment, labelSalary,
                
            /* Create Product Labels */
            labelProductName, labelProductNumber, labelManufacturer,
            labelProductCode, labelProductPrice,
                           
            /* Create Manufacturer Labels */
            labelManufacturerName, labelManufacturerLocation, 
            labelManufacturerSalesAssociate, labelManufacturerPhoneNumber,
                
            /* Search Employee Label */
            labelSearchEmployee,
                
            /* Search Inventory Label */
            labelSearchInventory,
                
            /* Radio Button Labels */
            labelHourlyEmployee, labelBasePlusCommission, labelSearchProduct,
            labelCommissionEmployee, labelSalaryEmployee, 
            labelSearchManufacturer
        ;
        
        // J Text Fields.
        private JTextField 
            
            /* Personal Information Text Fields */
            textFirstName, textLastName, textGender, textAge, textAddress,
            textDateOfBirth, textPhoneNumber, textSIN,
                
            /* Work Information Text Fields */    
            textEmployeeNumber, textDateHired, textPosition, textStatus, 
            textHourlyRate, textDepartment, textCommissionRate, textSalary,
                
            /* Create Product Text Fields */
            textProductName, textProductNumber, textManufacturer, 
            textProductCode, textProductPrice,
                           
            /* Create Manufacturer Text Fields */
            textManufacturerName, textManufacturerLocation, 
            textManufacturerPhoneNumber, textManufacturerSalesAssociate,
                
            /* Search Text Field */
            textSearchEmployees, textSearchInventory
        ;
        
        // J Text Ares.
        private JTextArea
                
            /* Search Text Field */
            textSearchResults, textSearchInventoryResults, textProductSummary,
            textManufacturerSummary
        ;
        
        // J Panels.
        private JPanel headerPanel, footerPanel,
                
            /* HR Panels */    
            employeePersonalInformation, employeeWorkInformation, 
            employeeSelectionPanel, inventorySearchSelectionPanel,
            searchEmployeePanel,
            searchEmployeeMasterPanel = new JPanel(), 
            createEmployeePanel = new JPanel(),
                
            /* Inventory Panels */    
            createProductPanel, createManufacturerPanel, 
            searchInventoryMasterPanel = new JPanel(),
            searchInventoryPanel = new JPanel(),
            createProductMasterPanel = new JPanel(),
            createManufacturerMasterPanel = new JPanel(),
            inventoryPanel = new JPanel(),
                
            /* Sales Panels */
            salesMasterPanel = new JPanel(), 
            createSalesPanel = new JPanel(), 
            searchSalesPanel = new JPanel()
                
            /* Admin Only Panel */
            
            
        ;               
        
        // J Buttons.
        private JButton exitButton, submitButton;
        
        // J Radio Buttons and button group.
        private JRadioButton basePlusCommissionEmployee, commissionEmployee, 
                             hourlyEmployee, salaryEmployee, searchProducts, 
                             searchManufacturers;
        private ButtonGroup  employeeSelectionGroup, 
                                                  inventorySearchSelectionGroup;
        
        
        // Font Setup.
        Font greetFont = new Font("Arial", Font.BOLD, 24);
        Font defaultFont = new Font("Arial", Font.BOLD, 24);
    /////////////////////// End of Variables ///////////////////////////////////
    
    /* ----------------------- Constructor ---------------------------------- */
        public MainGui()
        {
            super("Prestige Worldwide");
            setLayout(new BorderLayout());
            
            // Tabbed Interface.
            JTabbedPane mainTabPane = new JTabbedPane(); // Main Tab
            JTabbedPane hrTabPane = new JTabbedPane(); // HR Tab
            JTabbedPane inventoryTabPane = new JTabbedPane(); // Inventory Tab
            JTabbedPane subInventoryTabPane = new JTabbedPane(); // Create IN
            
            // Build Panels.
            buildHeaderPanel("Welcome to Management Box!");
            buildEmployeeSelectionPanel();
            buildCreateEmployeePanel();
            buildSearchEmployeePanel();
            buildSearchInventoryPanel();
            buildCreateProductPanel();
            buildCreateManufacturerPanel();
            buildFooterPanel();
            
            // Setup create employee panel.
            createEmployeePanel.setLayout(new BorderLayout());
            createEmployeePanel.add(employeeSelectionPanel, BorderLayout.NORTH);
            createEmployeePanel.add
                             (employeePersonalInformation, BorderLayout.CENTER);
            createEmployeePanel.add
                               (employeeWorkInformation, BorderLayout.SOUTH);
            
            // Initialize defaults values.
            textCommissionRate.setEditable(false);
            textHourlyRate.setEditable(false);
        
            //add the hr search panels to the main search panel
            searchEmployeeMasterPanel.setLayout(new BorderLayout());
            JScrollPane scrollHRPane = new JScrollPane(textSearchResults);
            searchEmployeeMasterPanel.add
                                      (searchEmployeePanel, BorderLayout.NORTH);
            searchEmployeeMasterPanel.add
                                       (scrollHRPane, BorderLayout.CENTER);
            
            // Setup search inventory panel.
            searchInventoryMasterPanel.setLayout(new BorderLayout());
            JScrollPane scrollInventoryPane = new JScrollPane(textSearchInventoryResults);
            searchInventoryMasterPanel.add
                                     (searchInventoryPanel, BorderLayout.NORTH);
            searchInventoryMasterPanel.add
                              (scrollInventoryPane, BorderLayout.CENTER);
            
            // Setup 
            createProductMasterPanel.setLayout(new BorderLayout());
            JScrollPane scrollProductPane = new JScrollPane(textProductSummary);
            createProductMasterPanel.add
                                       (createProductPanel, BorderLayout.NORTH);
            createProductMasterPanel.add
                                        (scrollProductPane, BorderLayout.CENTER);
            
            // Setup 
            createManufacturerMasterPanel.setLayout(new BorderLayout());
            JScrollPane scrollManufacturerPane = new JScrollPane(textManufacturerSummary);
            createManufacturerMasterPanel.add
                                  (createManufacturerPanel, BorderLayout.NORTH);
            createManufacturerMasterPanel.add
                                  (scrollManufacturerPane, BorderLayout.CENTER);
            
            
            // Setup Tabs.
            subInventoryTabPane.addTab
                         ("Product", null, createProductMasterPanel, "Product");
            subInventoryTabPane.addTab
          ("Manufacturer", null, createManufacturerMasterPanel, "Manufacturer");
            
            inventoryTabPane.addTab
                                ("Create", null, subInventoryTabPane, "Create");
            inventoryTabPane.addTab
            ("Search", null, searchInventoryMasterPanel, "Search");    
            
            hrTabPane.addTab("Create Employee", null, createEmployeePanel, 
                                                             "Create Employee");
            hrTabPane.addTab("Search", null, searchEmployeeMasterPanel, "Search");
            
            mainTabPane.addTab("HR", null , hrTabPane, "HR");
            mainTabPane.addTab
                             ("Inventory", null, inventoryTabPane, "Inventory");

            // Add the panels.
            add(headerPanel, BorderLayout.NORTH);
            add(mainTabPane, BorderLayout.CENTER);
            add(footerPanel, BorderLayout.SOUTH);
            
            pack();
        }
    /////////////////////// End of constructor /////////////////////////////////
        
        
    /* ----------------------- Get and Set Methods -------------------------- */

    /////////////////////// End of get and set methods. ////////////////////////
        
    
    /**
     * This creates a greetings panel.
     * @param labelMessage 
     */
    private void buildHeaderPanel(String labelMessage)
    {
        labelGreeting = new JLabel(labelMessage);
        labelGreeting.setFont(greetFont);
        headerPanel = new JPanel();
        headerPanel.add(labelGreeting);
        headerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
    }
    
    
    /**
     * This creates the employee selection panel.
     * @param labelMessage 
     */
    private void buildEmployeeSelectionPanel()
    {
        // Create labels.
        labelHourlyEmployee = new JLabel("Hourly Employee");
        labelBasePlusCommission
                              = new JLabel("Base Plus Commission Employee");
        labelCommissionEmployee = new JLabel("Commission Employee");
        labelSalaryEmployee = new JLabel("Salary Employee");
            
        employeeSelectionPanel = new JPanel();
        
        // Radio Buttons.
        employeeSelectionGroup = new ButtonGroup();
        hourlyEmployee = new JRadioButton("Hourly Employee", false);
        basePlusCommissionEmployee = new JRadioButton
                                       ("Base Plus Commission Employee", false);
        commissionEmployee = new JRadioButton("Comission Employee", false);
        salaryEmployee = new JRadioButton("Salary Employee", true);
        
        employeeSelectionGroup.add(hourlyEmployee);
        employeeSelectionGroup.add(basePlusCommissionEmployee);
        employeeSelectionGroup.add(commissionEmployee);
        employeeSelectionGroup.add(salaryEmployee);

        employeeSelectionPanel.setBorder(BorderFactory.createTitledBorder
                                                             ("Employee Type"));
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
        /* ----------- Employee Information Section ------------------------- */
            employeePersonalInformation = new JPanel();
            employeePersonalInformation.setLayout(new GridLayout(4,2));

            // Create labels.
            labelFirstName = new JLabel("First Name:");
            labelLastName = new JLabel("Last Name:");
            labelGender = new JLabel("Gender:");
            labelAge = new JLabel("Age:");
            labelAddress = new JLabel("Address:");
            labelDateOfBirth = new JLabel("Date of Birth:");
            labelPhoneNumber = new JLabel("Phone Number:");
            labelSIN = new JLabel("SIN:");

            // Create text boxes.
            textFirstName = new JTextField(15);
            textLastName = new JTextField(15);
            textGender = new JTextField(15);
            textAge = new JTextField(2);
            textAddress = new JTextField(15);
            textDateOfBirth = new JTextField(15);
            textPhoneNumber = new JTextField(15);
            textSIN = new JTextField(15);
            
            
            employeePersonalInformation.setBorder(BorderFactory.createTitledBorder
                                                      ("Personal Information"));
            employeePersonalInformation.add(labelFirstName);
            employeePersonalInformation.add(textFirstName);
            employeePersonalInformation.add(labelLastName);
            employeePersonalInformation.add(textLastName);
            employeePersonalInformation.add(labelGender);
            employeePersonalInformation.add(textGender);
            employeePersonalInformation.add(labelAge);
            employeePersonalInformation.add(textAge);
            employeePersonalInformation.add(labelAddress);
            employeePersonalInformation.add(textAddress);
            employeePersonalInformation.add(labelDateOfBirth);
            employeePersonalInformation.add(textDateOfBirth);
            employeePersonalInformation.add(labelPhoneNumber);
            employeePersonalInformation.add(textPhoneNumber);
            employeePersonalInformation.add(labelSIN);
            employeePersonalInformation.add(textSIN);
        ////////////// End of Main Panel Top ///////////////////////////////////
        
        /* ----------- Main Panel Bottom ------------------------------------ */
            employeeWorkInformation = new JPanel();
            employeeWorkInformation.setLayout(new GridLayout(4,2));

            // Create labels.
            labelDateHired = new JLabel("Date Hired:");
            labelPosition = new JLabel("Position:");
            labelStatus = new JLabel("Status:");
            labelHourlyRate = new JLabel("Hourly Rate:");
            labelCommissionRate = new JLabel("Commission Rate:");
            labelSalary = new JLabel("Salary Rate:");
            labelDepartment = new JLabel("Department:");

            // Create text boxes.
            textDateHired = new JTextField(15);
            textPosition = new JTextField(15);
            textStatus = new JTextField(15);
            textHourlyRate = new JTextField(15);
            textCommissionRate = new JTextField(15);
            textSalary = new JTextField(15);
            textDepartment = new JTextField(15);
            
            employeeWorkInformation.setBorder(BorderFactory.createTitledBorder
                                                          ("Work Information"));
            employeeWorkInformation.add(labelDateHired);
            employeeWorkInformation.add(textDateHired);
            employeeWorkInformation.add(labelPosition);
            employeeWorkInformation.add(textPosition);
            employeeWorkInformation.add(labelStatus);
            employeeWorkInformation.add(textStatus);
            employeeWorkInformation.add(labelHourlyRate);
            employeeWorkInformation.add(textHourlyRate);
            employeeWorkInformation.add(labelCommissionRate);
            employeeWorkInformation.add(textCommissionRate);
            employeeWorkInformation.add(labelSalary);
            employeeWorkInformation.add(textSalary);
            employeeWorkInformation.add(labelDepartment);
            employeeWorkInformation.add(textDepartment);
            
        ////////////// End of Main Panel Bottom ////////////////////////////////
    }
    
    
    /**
     * This method builds the human resources search panel.
     */
    private void buildSearchEmployeePanel()
    {
        searchEmployeePanel = new JPanel();
        searchEmployeePanel.setLayout(new FlowLayout());

        // Create labels.
        labelSearchEmployee = new JLabel("Search Employees:");

        // Create text boxes.
        textSearchEmployees = new JTextField(15);
        textSearchResults = new JTextArea();

        searchEmployeePanel.setBorder(BorderFactory.createTitledBorder
                                                         ("Search Employees:"));
        searchEmployeePanel.add(labelSearchEmployee);
        searchEmployeePanel.add(textSearchEmployees);
        searchEmployeePanel.add(textSearchResults);
        
        // Set text area to not editable.
        textSearchResults.setEditable(false);
    }
    
    /**
     * This method builds the create product panel.
     */
    private void buildCreateProductPanel()
    {
        createProductPanel = new JPanel();
        createProductPanel.setLayout(new GridLayout(2,2));

        // Create labels.
        labelProductName = new JLabel("Name:");
        labelProductCode = new JLabel("Code:");
        labelProductPrice = new JLabel("Price:");
        labelManufacturer = new JLabel("Manufacturer:");

        // Create text boxes.
        textProductName = new JTextField(15);
        textProductCode = new JTextField(15);
        textProductPrice = new JTextField(15);
        textProductSummary = new JTextArea("Summary / Notes Here");
        
        String[] manufacturersArray = { "Bird", "Cat"}; // Database Connection Here

        // Setup combo box for selecting manufacturers from the array.
        JComboBox manufacturers = new JComboBox(manufacturersArray);
        manufacturers.setSelectedIndex(0);

        inventoryPanel.setBorder(BorderFactory.createTitledBorder
                                                     ("Inventory Information"));
        createProductPanel.add(labelProductName);
        createProductPanel.add(textProductName);
        createProductPanel.add(labelProductCode);
        createProductPanel.add(textProductCode);
        createProductPanel.add(labelProductPrice);
        createProductPanel.add(textProductPrice);
        createProductPanel.add(labelManufacturer);
        createProductPanel.add(manufacturers);
    }
    
    
    /**
     * This method builds the create manufacturer panel.
     */
    private void buildCreateManufacturerPanel()
    {
        createManufacturerPanel = new JPanel();
        createManufacturerPanel.setLayout(new GridLayout(2,2));

        // Create labels.
        labelManufacturerName = new JLabel("Name:");
        labelManufacturerLocation = new JLabel("Location:");
        labelManufacturerPhoneNumber = new JLabel("Phone Number:");
        labelManufacturerSalesAssociate = new JLabel("Sales Associate:");

        // Create text boxes.
        textManufacturerName = new JTextField(15);
        textManufacturerLocation = new JTextField(15);
        textManufacturerPhoneNumber = new JTextField(15);
        textManufacturerSalesAssociate = new JTextField(15);
        textManufacturerSummary = new JTextArea("Summary / Notes Here");

        createManufacturerPanel.add(labelManufacturerName);
        createManufacturerPanel.add(textManufacturerName);
        createManufacturerPanel.add(labelManufacturerLocation);
        createManufacturerPanel.add(textManufacturerLocation);
        createManufacturerPanel.add(labelManufacturerPhoneNumber);
        createManufacturerPanel.add(textManufacturerPhoneNumber);
        createManufacturerPanel.add(labelManufacturerSalesAssociate);
        createManufacturerPanel.add(textManufacturerSalesAssociate);
    }
    
    
    /**
     * This creates the employee selection panel.
     * @param labelMessage 
     */
    private void buildSearchInventoryPanel()
    {
        // Create labels.
        labelSearchProduct = new JLabel("Search Products");
        labelSearchManufacturer = new JLabel("Search Manufacturer");
            
        searchInventoryPanel = new JPanel();
        searchInventoryPanel.setLayout(new FlowLayout());
        inventorySearchSelectionGroup = new ButtonGroup();
       
        // Create labels.
        labelSearchInventory = new JLabel("Search For:");
        
        // Create text boxes.
        textSearchInventory = new JTextField(15);
        textSearchInventoryResults = new JTextArea();
        
        // Create buttons.
        searchProducts = new JRadioButton("Search Products", true);
        searchManufacturers = new JRadioButton("Search Manufacturer", false);
        
        inventorySearchSelectionGroup.add(searchProducts);
        inventorySearchSelectionGroup.add(searchManufacturers);

        searchInventoryPanel.setBorder(BorderFactory.createTitledBorder
                                                         ("Search Inventory:"));
        searchInventoryPanel.add(searchProducts);
        searchInventoryPanel.add(searchManufacturers);
        searchInventoryPanel.add(labelSearchInventory);
        searchInventoryPanel.add(textSearchInventory);
        
        // Set text area to not editable.
        textSearchInventoryResults.setEditable(false);
    }
    
    
    /**
     * This creates the panel for our button.
     */
    private void buildFooterPanel()
    {
        // Creates values.
        footerPanel = new JPanel();
        submitButton = new JButton("Submit");
        exitButton = new JButton("Exit");
        
        // Action Listeners.
        submitButton.addActionListener(new SubmitButtonListener());
        exitButton.addActionListener(new ExitButtonListener());
        
        // Add items to the panel.
        footerPanel.add(submitButton);
        footerPanel.add(exitButton);
    }
    
    
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
     * This is the main method for running code.
     * @param args 
     */
    public static void main(String[] args) 
    {
        UserGui gui = new UserGui();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
    } // End of main method.

} // End of UserGui class.
