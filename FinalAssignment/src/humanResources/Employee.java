package humanResources;

/**
 * @author Created by: Matthew Walichnowsky | 200171919
 */
import java.time.LocalDate;
import java.time.Period;

public class Employee 
{
    /* ---------------------- Variables------------------------------------- */
        private String firstName, lastName, address, position, status,
                       gender, phoneNumber, department;
        private LocalDate dateOfBirth, dateHired, today;
        private int age, sin;
        private double payRate, earnings;
        private static int employeeCount;
    /////////////////////// End of Variables ///////////////////////////////////
        
        
    /* -----------------Constructor (Full Employlee Setup)----------------------
     * @param firstName = Joe
     * @param lastName = Blow
     * @param gender = Male
     * @param address = 32 Fake St.
     * @param dateOfBirth = December, 14, 1994
     * @param phoneNumber = 32 Fake St.
     * @param sin = 200500600
     * @param employeeNumber = 200
     * @param dateHired = September 2012
     * @param position = 
     * @param status =  
     * @param payRate =
     * @param earnings =
     * @param department = accounting
     */    
        public Employee
        (
            String firstName, 
            String lastName,
            String gender,
            String address,
            LocalDate dateOfBirth, 
            String phoneNumber,
            int sin,
            LocalDate dateHired, 
            String position,
            String status,
            String department
        )
        {
            setFirstName(firstName);
            setLastName(lastName);
            setGender(gender);
            setAddress(address);
            setDateOfBirth(dateOfBirth);
            setPhoneNumber(phoneNumber);
            setSin(sin);
            setDateHired(dateHired);
            setPosition(position);
            setStatus(status);
            setDepartment(department);
            employeeCount++;
        }
    /////////////////////// End of constructor /////////////////////////////////
        
        
    /* ---------------Constructor (Employee Info Only Setup)--------------------
     * @param firstName = Joe
     * @param lastName = Blow
     * @param address = 32 Fake St.
     * @param dateOfBirth = December, 14, 1994
     * @param phoneNumber = 705-955-0063
     * @param sin = 200500600
     */    
        public Employee
        (
            String firstName, 
            String lastName,
            String gender,
            String address,
            LocalDate dateOfBirth, 
            String phoneNumber,
            int sin          
        )
        {
            setFirstName(firstName);
            setLastName(lastName);
            setGender(gender);
            setAddress(address);
            setDateOfBirth(dateOfBirth);
            setPhoneNumber(phoneNumber);
            setSin(sin);
            employeeCount++;
        }
    /////////////////////// End of constructor /////////////////////////////////    


    /*************************Get and Set Methods******************************/
        /**
        * This method returns the employees first name as a String.
        * @return firstName
        */
        public String getFirstName()
        {
            return this.firstName;
        } // End of getFirstName method.

        
        /**
         * This method sets the employees first name as a String.
         * @param firstName
         */
        private void setFirstName(String firstName)
        {
            this.firstName = firstName;
        } // End of setFirstName method.

        
        /**
         * This method returns the employees last name as a String.
         * @return lastName
         */
        public String getLastName()
        {
            return this.lastName;
        } // End of getLastName method.

        
        /**
         * This method sets the employees last name as a String.
         * @param lastName
         */
        private void setLastName(String lastName)
        {
            this.lastName = lastName;
        } // End of setLastName method.
        
        
        /**
         * This method returns the employees gender as a String.
         * @return gender
         */
        public String getGender()
        {
            return this.gender;
        } // End of getGender method.

        
        /**
         * This method sets the employees gender as a String.
         * @param gender
         */
        private void setGender(String gender)
        {
            this.gender = gender;
        } // End of setGender method.
        
        
        /**
         * This method returns the employees address as a String.
         * @return address
         */
        public String getAddress()
        {
            return this.address;
        } // End of getAddress method.
        
        
        /**
         * This method sets the employees address as a String.
         * @param address
         */
        private void setAddress(String address)
        {
            this.address = address;
        } // End of setAddress.
        
         
        /**
         * This method returns the employees date of birth as a LocalDate.
         * @return dateOfBirth
         */
        public LocalDate getDateOfBirth()
        {
            return this.dateOfBirth;
        } // End of getDateOfBirth method.

        
        /**
         * This method sets the employees dateOfBirth as a LocalDate.
         * @param dateOfBirth
         */
        private void setDateOfBirth(LocalDate dateOfBirth)
        {
            this.dateOfBirth = dateOfBirth;
        } // End of setDateOfBirth method.
        
        
        /**
         * This method returns the employees phoneNumber as a String.
         * @return phoneNumber
         */
        public String getPhoneNumber()
        {
            return this.phoneNumber;
        } // End of getPhoneNumber method.

        
        /**
         * This method sets the employees phoneNumber as a String.
         * @param phoneNumber
         */
        private void setPhoneNumber(String phoneNumber)
        {
            this.phoneNumber = phoneNumber;
        } // End of setPhoneNumber method.
        
        
        /**
         * This method returns the employees sin as a int.
         * @return sin
         */
        public int getSin()
        {
            return this.sin;
        } // End of getSin method.

        
        /**
         * This method sets the employees sin as a int.
         * @param sin
         */
        private void setSin(int sin)
        {
            this.sin = sin;
        } // End of setSin method.

    //-------Personal Info Up--------Work Info Down---------------------------//    

        
        /**
         * This method returns the employees position as a String.
         * @return position
         */
        public String getPosition()
        {
            return this.position;
        } // End of getPosition method.

        
        /**
         * This method sets the employees position as a String.
         * @param position
         */
        private void setPosition(String position)
        {
            this.position = position;
        } // End of setPosition method.
        
        
        /***
         * This method returns the employees date of hire.
         * @return 
         */
        public LocalDate getDateHired()
        {
            return this.dateHired;
        } // End of getDateHired method.

        
        /**
         * This method sets the employees dateHired as a LocalDate.
         * @param dateHired
         */
        private void setDateHired(LocalDate dateHired)
        {
            this.dateHired = dateHired;
        } // End of setDateHired method.
                
                
        /**
         * This method returns the employees status as a String.
         * @return status
         */
        public String getStatus()
        {
            return this.status;
        } // End of getStatus method.

        
        /**
         * This method sets the employees status as a String.
         * @param status
         */
        private void setStatus(String status)
        {
            this.status = status;
        } // End of setStatus method.
        
        
        /**
         * This method returns the employees earnings as a String.
         * @return department
         */
        public String getDepartment()
        {
            return this.department;
        } // End of getDepartment method.

        
        /**
         * This method sets the employees earnings as a String.
         * @param department
         */
        private void setDepartment(String department)
        {
            this.department = department;
        } // End of setDepartment method.
        
    /////////////////////// End of get and set methods. ////////////////////////  
        
        
    /**************************** Other Methods *******************************/ 
     
        
        /**
         * This method calculates and returns the employees age as a int. 
         * It does this by comparing yearOfBirth to the current date.
         * @return 
         */
        public int getAge() 
        {
            // Update Current Date
            refreshLocalDateNow();

            // Compares today to birthday to determine age.
            Period age = Period.between(dateOfBirth, today);

            return age.getYears();
        } // End of getAge method. 
        
        
        /**
         * This returns the current date as a LocalDate.
         */
        public void refreshLocalDateNow()
        {
            //Current Date
            LocalDate today = LocalDate.now();
            
            this.today = today;
        } // End of refreshLocalDateNow method.
        
        
        /**
         * This method returns an employees information as a toString().
         * @return firstName
         */
        @Override
        public String toString()
        {
            String employeeInfo="";
        
            /*add the following info

            */
            employeeInfo+= "\nName:\t\t" + getFirstName() + getLastName();
            employeeInfo+= "\nAge:\t\t" + getAge();
            employeeInfo+= "\nPosition:\t" + getPosition();
            employeeInfo+= "\nHire Date:\t" + getDateHired();
        
            return employeeInfo;
        } // End of toString method.
        
} // End of Employee class.