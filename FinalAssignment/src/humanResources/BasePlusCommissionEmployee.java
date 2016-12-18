package humanResources;

import java.time.LocalDate;

/**
 *
 * @author mwalichnowsky - 200171919
 */
public class BasePlusCommissionEmployee extends CommissionEmployee
{
    /* ----------------------- Variables------------------------------------- */
        private double salary;
    /////////////////////// End of Variables ///////////////////////////////////
        
    
    /* ----------------------- Constructor ---------------------------------- */    
        BasePlusCommissionEmployee
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
            double commissionRate,
            double salary
        )
        {
            super
            (
                firstName, lastName, gender, address, dateOfBirth, phoneNumber, 
                sin, employeeNumber, dateHired, position, status, department, 
                grossSales, commissionRate
            );
            setSalary(salary);
        }
    /////////////////////// End of constructor /////////////////////////////////
    
        
    /*************************Get and Set Methods******************************/
        /**
        * This method returns the employees salary name as a double.
        * @return salary
        */
        public double getSalary()
        {
            return this.salary;
        } // End of getSalary method.

        
        /**
         * This method sets the employees salary as a double.
         * @param salary
         */
        private void setSalary(double salary)
        {
            this.salary = salary;
        } // End of setSalary method.
        
    /////////////////////// End of get and set methods. ////////////////////////
        
    /**
    * This method returns an employees information as a toString().
    * @return firstName
    */
    @Override
    public String toString()
    {
        return super.toString() + "\nSalary: " + getSalary();
    } // End of toString method.
} // End of BasePlusCommissionSalesEmployee Class.
