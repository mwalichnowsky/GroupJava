/*
* Global Class
*/

package general;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Created by: Matthew Walichnowsky | 200171919
 */
public class Global 
{
    /* ----------------------- Variables------------------------------------- */
        // Font Setup.
        final Font greetFont = new Font("Arial", Font.BOLD, 24);
        final Font defaultFont = new Font("Arial", Font.BOLD, 12);
    /////////////////////// End of Variables ///////////////////////////////////

    /* ----------------------- Get and Set Methods -------------------------- */

    /**
     *
     * @return
     */
    public Font getGreetFont()
    {
        return greetFont;
    }//End of getGreetFont

    
    /**
     *
     * @return
     */
    public Font getDefaultFont()
    {
        return defaultFont;
    }//End of getDefaultFont
    /////////////////////// End of get and set methods. ////////////////////////
        
    /**
     *
     * @param panel
     * @param string
     */
    public void border(JPanel panel, String string)
    {
        panel.setBorder(BorderFactory.createTitledBorder(string));
    }//End of border
    
    /**
     *
     * @param pane
     * @param panel
     * @param string
     */
    public void setTab(JTabbedPane pane, JPanel panel, String string)
    {
        pane.addTab(string, null, panel, string);
    }//End of setTab
    
    /**
     *
     * @param error
     * @param errorString
     */
    public void sqlError(SQLException error, String errorString)
    {
        JavaIO io = new JavaIO();
        io.checkDirectory();
        
        try
        {
            io.appendToFile(
                "******************************************************"
                + "\n"+ LocalDateTime.now()
                + "\nError description: \n"
                + error.getMessage()
                + "\n*******************************************************");
        }
        catch(IOException oe)
        {
            System.err.println("Fatal System Error! ");
        }
        
        System.err.println("Got an exception! ");
        System.err.println(error.getMessage());
        JOptionPane.showMessageDialog
                              (null, errorString + "\n\n" + error.getMessage());
    }//End of sqlError
    
    
    /**
     *
     * @param error
     * @param errorString
     */
    public void generalError(Exception error, String errorString)
    {
        JavaIO io = new JavaIO();
        io.checkDirectory();
        
        try
        {
            io.appendToFile(
                    "******************************************************"
                    + "\n"+ LocalDateTime.now()
                    + "\nError description: \n"
                    + error.getMessage()
                    + "\n*******************************************************");
        }
        catch(IOException ioe)
        {
            System.err.println("Fatal System Error! ");
        }
        
        
        System.err.println("Got an exception! ");
        System.err.println(error.getMessage());
        JOptionPane.showMessageDialog
                              (null, errorString + "\n\n" + error.getMessage());
    }//End of generalError
    
    
    public String getDb()
    {
        return "jdbc:mysql://sql.computerstudi.es:3306/gc200315409";
    }
    
    
    public String getDbUser()
    {
        return "gc200315409";
    }
        
        
    public String getDbPass()
    {
        return "?8pDT38G";
    }
    
    
    public void delete(String delete)
    {
        
    }
} // End of Global class.
