/*
* Login GUI Class
*
*/
package gui;

import general.Global;
import java.sql.Statement;
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
            loginUser, loginPassword
        ;
        
        boolean isAdmin;
        
        Global g = new Global();
        
        // Database connection objects.
        Statement stat = null;
        ResultSet rs = null;
        Connection connection = null;
    /////////////////////// End of Variables ///////////////////////////////////
    
        
    /**
     * This runs the login window for connecting to the application.
     */   
    public LoginGui()
    {
        // Items to hold selected data.
        JPanel loginPanel = new JPanel();

        final String DB_URL 
                  = "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
        String
            databaseUser = "gc200315409", 
            databasePassword = "?8pDT38G"
        ;
                    
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
            loginPassword = new String(textPassword.getPassword());
            
            // Try to to connect.
            try
            {
                connection = DriverManager.getConnection
                (
                    DB_URL, databaseUser, databasePassword
                );

                if (connection != null)
                {
                    String 
                        sql = "SELECT * FROM users "
                            + "WHERE username = '"+loginUser+"' ",
                        rsUser = "", rsPassword = ""
                    ;
                    int rsId = 0;

                    stat = connection.createStatement();

                    ResultSet result = stat.executeQuery(sql);

                    while (result.next())
                    {
                        rsId = result.getInt("user_id");
                        rsUser = result.getString("username");
                        rsPassword = result.getString("password");
                        isAdmin = result.getBoolean("isAdmin");
                    }
                    
                    System.out.println("read: " + loginUser + loginPassword);
                    
                    System.out.println("readRS: " + rsId + rsUser + rsPassword
                    + isAdmin);
                    
                    if (loginUser.equals(rsUser))
                    {
                        if (loginPassword.equals(rsPassword))
                        {
                            // Check Credidentials in the database.
                            databaseCredidentialCheck = true;
                        }
                        else
                        {
                            System.out.println("Error, wrong password.");
                        }
                    }
                    else
                    {
                        System.out.println("Error, wrong username.");
                    }
                }
            } // End of try.

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
            catch (Exception error)
            {
                g.generalError(error, "General Error"); 
                
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

        } // End of do statement.
        while
        (
            databaseCredidentialCheck == false && 
            databaseLoopCounter < 3
        );
        
        if (databaseCredidentialCheck == true)
        {
            runGui(isAdmin);
        }
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
