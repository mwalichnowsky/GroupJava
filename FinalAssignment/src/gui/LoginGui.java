package gui;

import general.Global;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author Created by: Matthew Walichnowsky | 200171919
 */
public class LoginGui extends JFrame
{
    /* ----------------------- Variables ------------------------------------ */
        private String 
            loginUser, loginPassword,  
            databaseUser = "GroupJava", 
            databasePassword = "h^dHd(j6^hmD#(mfjgSK;fkss"
        ;
        
        Global g = new Global();
    /////////////////////// End of Variables ///////////////////////////////////
    
        
    /**
     * This runs the login window for connecting to the application.
     */   
    public LoginGui()
    {
        // Items to hold selected data.
        JPanel loginPanel = new JPanel();
       
        // Database connection objects.
        Statement stat = null;
        ResultSet rs = null;
        Connection conn = null;
        
        final String DB_URL = "jdbc:mysql://72.139.8.122:3306/GroupJava";
String
        databaseUser = "GroupJava", 
            databasePassword = "h^dHd(j6^hmD#(mfjgSK;fkss";
                    
        final JTextField textUsername = new JTextField(15);
        final JPasswordField textPassword = new JPasswordField(15);

        // Add components to the panel.
        loginPanel.add(new JLabel("User Name:"));
        loginPanel.add(textUsername);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(textPassword);

        // Boolean variable for loop control.
        boolean databaseCredidentialCheck = false;

        int databaseLoopCounter = 0;

        do
        {
            String[] buttonOptions = new String[]{"OK", "Cancel"};

            JOptionPane.showOptionDialog
            (
                null,
                loginPanel,
                "Enter Password",
                JOptionPane.NO_OPTION, 
                JOptionPane.PLAIN_MESSAGE,
                null,
                buttonOptions,
                buttonOptions[0]
            );
            
            loginUser = textUsername.getText();
            loginPassword = textPassword.getPassword().toString();
            
            System.out.println(loginUser + loginPassword);
            
            // Try to to connect.
            /*try
            {
                conn = DriverManager.getConnection
                (
                    DB_URL, databaseUser, databasePassword
                );

                // Check Credidentials in the database.
                databaseCredidentialCheck = true;
            }

            // Invalid Login.
            catch (SQLException error)
            {
                g.sqlError(error, "You Database Credentials were invalid.\n"
                                                         + "Please try again.");

                databaseCredidentialCheck = false;

                databaseLoopCounter++;

                // Spam prevention.
                if (databaseLoopCounter > 2)
                {
                    // Sleeping thread (15 seconds).
                    JOptionPane.showMessageDialog
                    (
                        null, 
                        "Failure to authenticate to database. Too many attempts"
                        + "System will now exit."
                    );
                }
            }
            // General Error.
            catch (Exception error){ g.generalError(error, "General Error"); }*/

        } // End of do statement.
        while
        (
            databaseCredidentialCheck = false && 
            databaseLoopCounter < 3
        );

        if (conn != null)
        {
            boolean isAdmin = false; // Database 
            runGui(isAdmin);
        }
        
        boolean isAdmin = true; // Database 
            runGui(isAdmin);
    }
    /* ----------------------- Methods -------------------------------------- */
        /**
         * This method checks if the user is a Admin or not.
         * Then runs UserGui or MainGui.
         * @param isAdmin 
         */
        public void runGui(boolean isAdmin)
        {
            MainGui mainGui = new MainGui(isAdmin);
            mainGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainGui.pack();
            mainGui.setVisible(true);
        } // End of runGui.
    /////////////////////// End of methods /////////////////////////////////////
    
} // End of LoginGui class.
