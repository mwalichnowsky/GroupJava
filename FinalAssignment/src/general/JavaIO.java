/*
 * ******************************************************
 * ********* Send exceptions to a file
 * ********* 12/5/2016
 * ********* Created By Ross Keddy.             *********
 * ******************************************************
 */
package general;
import java.io.*;
import java.util.*;

/**
 *
 * @author Ross
 */
public class JavaIO {

    /**
     * @param args the command line arguments
     */
    Scanner read = new Scanner(System.in);
    BufferedWriter bufferedWriter = null;
    
    public void checkDirectory()
    {
        String dirName = "C:/JavaApp/Error/";
            
        File directory = new File("C:/JavaApp/Error/");
            
        if(!directory.exists())
        {
            System.out.println("Creating Directory... " + dirName);
            
            try
            {
                directory.mkdirs();
            }
            catch(SecurityException error)
            {
                System.out.println("Directory Crashed");
            }
            catch(Exception error)
            {
                System.out.println("General Error");
            }
        }
        else
        {
            System.out.println("Directory Exists.");
        }
    }//End of chkdir
    
    public void appendToFile(String message)
            throws IOException
    {
        checkDirectory();
        
        File file = new File("C:/JavaApp/Error/errorlog.txt");
        
        if(!file.exists())
        {
            System.out.println("No such file");
        }
        
        //System.out.println("Enter new data to append");
        //message = read.nextLine();
        
        FileWriter fileWriter = new FileWriter("C:/JavaApp/Error/errorlog.txt", true);
        
        bufferedWriter = new BufferedWriter(fileWriter);
        
        bufferedWriter.newLine();
        bufferedWriter.write(message);
        
        bufferedWriter.close();
    }//End of appendToFile
}//End of Class
