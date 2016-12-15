package general;

import java.awt.Font;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
        public Font getGreetFont()
        {
            return greetFont;
        }

        public Font getDefaultFont()
        {
            return defaultFont;
        }
    /////////////////////// End of get and set methods. ////////////////////////
        
    public void border(JPanel panel, String string)
    {
        panel.setBorder(BorderFactory.createTitledBorder(string));
    }
    
    
    public void sqlError(SQLException error, String errorString)
{
        System.err.println("Got an exception! ");
        System.err.println(error.getMessage());
        JOptionPane.showMessageDialog
                              (null, errorString + "\n\n" + error.getMessage());
    }
    
    
    public void generalError(Exception error, String errorString)
    {
        System.err.println("Got an exception! ");
        System.err.println(error.getMessage());
        JOptionPane.showMessageDialog
                              (null, errorString + "\n\n" + error.getMessage());
    }
    
    
    public void setTab(JTabbedPane pane, JPanel panel, String string)
    {
        pane.addTab(string, null, panel, string);
    }
} // End of Global class.
