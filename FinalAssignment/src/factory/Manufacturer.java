/*
* Manufacturer Class
* Specifies the Manufacturor Object
*/
package factory;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Created by: Matthew Walichnowsky | 200171919
 */
public class Manufacturer 
{
    /* ----------------------- Variables------------------------------------- */
        private String 
            manufacturerName, manufacturerLocation, manufacturerPhoneNumber
        ;
    /////////////////////// End of Variables ///////////////////////////////////

    /* ----------------------- Constructor ---------------------------------- */
        public Manufacturer
        (
            String manufacturerName, 
            String manufacturerLocation,
            String manufacturerPhoneNumber
        )
        {
            setManufacturerName(manufacturerName);
            setManufacturerLocation(manufacturerLocation);
            setManufacturerPhoneNumber(manufacturerPhoneNumber);
        }
    /////////////////////// End of constructor /////////////////////////////////
        
    /* ----------------------- Get and Set Methods -------------------------- */
        /**
        * This method returns the manufacturer manufacturerName as a String.
        * @return manufacturerName
        */
        public String getManufacturerName()
        {
            return this.manufacturerName;
        } // End of getManufacturerName method.

        
        /**
         * This method sets the manufacturer manufacturerName as a String.
         * @param manufacturerName
         */
        private void setManufacturerName(String manufacturerName)
        {
            this.manufacturerName = manufacturerName;
        } // End of setManufactorName method.
        
        
        /**
        * This method returns the manufacturer manufacturerLocation as a String.
        * @return manufactorLocation
        */
        public String getManufacturerLocation()
        {
            return this.manufacturerLocation;
        } // End of getManufacturerLocation method.

        
        /**
         * This method sets the manufacturer manufacturerLocation as a String.
         * @param manufacturerLocation
         */
        private void setManufacturerLocation(String manufacturerLocation)
        {
            this.manufacturerLocation = manufacturerLocation;
        } // End of setManufactorLocationmethod.
        
        
        /**
        * This method returns the manufacturer manufacturerLocation as a String.
        * @return manufacturerPhoneNumber
        */
        public String getManufacturerPhoneNumber()
        {
            return this.manufacturerPhoneNumber;
        } // End of getManufacturerLocation method.

        
        /**
         * This method sets the manufacturer manufacturerLocation as a String.
         * @param manufacturerPhoneNumber
         */
        private void setManufacturerPhoneNumber(String manufacturerPhoneNumber)
        {
            this.manufacturerPhoneNumber = manufacturerPhoneNumber;
        } // End of setManufactorLocationmethod.
        
    /////////////////////// End of get and set methods. ////////////////////////

        
    /**
    * This method returns an employees information as a toString().
    * @return firstName
    */
    @Override
    public String toString()
    {
        return super.toString() + "\nManufacturer Name: " + manufacturerName
                                + "\nManufacturer Location: " 
                                + manufacturerLocation;
    } // End of toString method.
    
} // End of Manufacturer class.
