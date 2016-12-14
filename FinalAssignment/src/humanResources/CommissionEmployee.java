/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanResources;

import java.time.LocalDate;

/**
 *
 * @author mwalichnowsky - 200171919
 */
public class CommissionEmployee extends Employee 
{
    /* ----------------------- Variables------------------------------------- */
        private double grossSales, commissionRate;
    /////////////////////// End of Variables ///////////////////////////////////
        

    /* -----------------Constructor (Full Employee Setup)-------------------- */
        CommissionEmployee
        (
            String firstName, 
            String lastName,
            String gender,
            String address,
            LocalDate dateOfBirth, 
            String phoneNumber,
            int sin, 
            int employeeNumber,
            LocalDate dateHired, 
            String position,
            String status,
            String department,
            double grossSales, 
            double commissionRate            
        )
        {
            super
            (
                firstName, lastName, gender, address, dateOfBirth, phoneNumber, 
                sin, employeeNumber, dateHired, position, status, department
            );
            setGrossSales(grossSales);
            setCommissionRate(commissionRate);
        }
    /////////////////////// End of constructor /////////////////////////////////
        
        
    /*************************Get and Set Methods******************************/
        /**
        * This method returns the employees grossSales name as a double.
        * @return grossSales
        */
        public double getGrossSales()
        {
            return this.grossSales;
        } // End of getGrossSales method.

        
        /**
         * This method sets the employees grossSales as a double.
         * @param grossSales
         */
        private void setGrossSales(double grossSales)
        {
            this.grossSales = grossSales;
        } // End of setGrossSales method.

        
        /**
         * This method returns the employees commissionRate as a double.
         * @return commissionRate
         */
        public double getCommissionRate()
        {
            return this.commissionRate;
        } // End of getCommissionRate method.

        
        /**
         * This method sets the employees commissionRate as a double.
         * @param commissionRate
         */
        private void setCommissionRate(double commissionRate)
        {
            this.commissionRate = commissionRate;
        } // End of setCommissionRate method.
        
    /////////////////////// End of get and set methods. ////////////////////////
        
    /**
    * This method returns an employees information as a toString().
    * @return firstName
    */
    @Override
    public String toString()
    {
        return super.toString() + "\nGross Sales: " + getGrossSales() 
                                + "\nCommission Rate: " + getCommissionRate();
    } // End of toString method.
        
} // End of CommissionSalesEmployee Class.
