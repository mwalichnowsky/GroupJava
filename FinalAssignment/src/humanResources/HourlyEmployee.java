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
public class HourlyEmployee extends Employee 
{
    /* ----------------------- Variables------------------------------------- */
        private double hourlyRate;
    /////////////////////// End of Variables ///////////////////////////////////
        

    /* -----------------Constructor (Full Employee Setup)-------------------- */
        public HourlyEmployee
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
            double hourlyRate,
            String department,
            double grossSales, 
            double commissionRate            
        )
        {
            super
            (
                firstName, lastName, gender, address, dateOfBirth, phoneNumber, 
                sin, dateHired, position, status, department
            );
            setHourlyRate(hourlyRate);
        }
    /////////////////////// End of constructor /////////////////////////////////
        
        
    /*************************Get and Set Methods******************************/
        /**
        * This method returns the employees hourlyRate name as a double.
        * @return hourlyRate
        */
        public double getHourlyRate()
        {
            return this.hourlyRate;
        } // End of getHourlyRate method.

        
        /**
         * This method sets the employees hourlyRate as a double.
         * @param hourlyRate
         */
        public void setHourlyRate(double hourlyRate)
        {
            this.hourlyRate = hourlyRate;
        } // End of setHourlyRate method.

        
    /////////////////////// End of get and set methods. ////////////////////////
        
    /**
    * This method returns an employees information as a toString().
    * @return firstName
    */
    @Override
    public String toString()
    {
        return super.toString() + "\nHourly Rate: " + getHourlyRate();
    } // End of toString method.
        
} // End of CommissionSalesEmployee Class.
