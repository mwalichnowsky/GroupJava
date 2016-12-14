package factory;

/**
 * @author Created by: Matthew Walichnowsky | 200171919
 */
public class Product 
{
    /* ----------------------- Variables------------------------------------- */
        private String productName;
        private Manufacturer manufacturer;
        private int productNumber;
    /////////////////////// End of Variables ///////////////////////////////////

    /* ----------------------- Constructor ---------------------------------- */
        public Product
        (
            String productName,
            Manufacturer manufacturer,
            int productNumber
        )
        {
            setProductName(productName);
            setManufacturer(manufacturer);
            setProductNumber(productNumber);
        }
    /////////////////////// End of constructor /////////////////////////////////
        
    /* ----------------------- Get and Set Methods -------------------------- */
        /**
        * This method returns the products productName as a String.
        * @return productName
        */
        public String getProductName()
        {
            return this.productName;
        } // End of getProductName method.

        
        /**
         * This method sets the products productName as a String.
         * @param productName
         */
        private void setProductName(String productName)
        {
            this.productName = productName;
        } // End of setProductName method.
        
        
                /**
        * This method returns the products manufacturer as a Manufacturer.
        * @return manufacturer
        */
        public Manufacturer getManufactorer()
        {
            return this.manufacturer;
        } // End of getManufactorer method.

        
        /**
         * This method sets the products manufacturer as a Manufacturer.
         * @param manufacturer
         */
        private void setManufacturer(Manufacturer manufacturer)
        {
            this.manufacturer = manufacturer;
        } // End of setManufactorer method.
        
                /**
        * This method returns the products productNumber as a int.
        * @return productNumber
        */
        public int getProductNumber()
        {
            return this.productNumber;
        } // End of getProductName method.

        
        /**
         * This method sets the products productNumber as a String.
         * @param productNumber
         */
        private void setProductNumber(int productNumber)
        {
            this.productNumber = productNumber;
        } // End of setProductName method.
    /////////////////////// End of get and set methods. ////////////////////////

        
    /**
    * This method returns an product information as a toString().
    * @return toString
    */
    @Override
    public String toString()
    {
        return super.toString() + "\nProduct Name: " + getProductName()
                                + "\nManufactorer Name: " + manufacturer.toString()
                                + "\nProduct Number: " + getProductNumber();
    } // End of toString method.
    
} // End of Product class.
