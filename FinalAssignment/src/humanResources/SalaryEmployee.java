package humanResources;

import java.time.LocalDate;

/**
 *
 * @author mwalichnowsky - 200171919
 */
public class SalaryEmployee extends Employee 
{
    /* ----------------------- Variables------------------------------------- */
        private double salaryRate;
    /////////////////////// End of Variables ///////////////////////////////////
        

    /* -----------------Constructor (Full Employee Setup)-------------------- */
        public SalaryEmployee
        (
            String firstName, 
            String lastName,
            String gender,
            int age,
            String address,
            LocalDate dateOfBirth, 
            String phoneNumber,
            int sin, 
            LocalDate dateHired, 
            String position,
            String status,
            double salary,
            String department
           
        )
        {
            super
            (
                firstName, lastName, gender, address, dateOfBirth, phoneNumber, 
                sin,  dateHired, position, status, department
            );
            setSalaryRate(salaryRate);
        }
    /////////////////////// End of constructor /////////////////////////////////
        
        
    /*************************Get and Set Methods******************************/
        /**
        * This method returns the employees salaryRate name as a double.
        * @return salaryRate
        */
        public double getSalaryRate()
        {
            return this.salaryRate;
        } // End of getSalaryRate method.

        
        /**
         * This method sets the employees salaryRate as a double.
         * @param salaryRate
         */
        private void setSalaryRate(double salaryRate)
        {
            this.salaryRate = salaryRate;
        } // End of setSalaryRate method.

        
    /////////////////////// End of get and set methods. ////////////////////////
        
    /**
    * This method returns an employees information as a toString().
    * @return firstName
    */
    @Override
    public String toString()
    {
        return super.toString() + "\nSalary Rate: " + getSalaryRate();
    } // End of toString method.
        
} // End of CommissionSalesEmployee Class.
