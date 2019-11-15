public class Staff extends User implements UserManagement
{
   private int hours;           //pr week
   private double salary;       //pr hour
   private int vacation;        //pr year
   
      //Constructer 
   
   public Staff () {} 
      
   public Staff (String firstName, String lastName, String cpr, String type, 
                 String[] address, int phoneNr, String password, int hours, 
                 double salary, int vacation) 
   {
      super(firstName, lastName, cpr, type, address, phoneNr, password);
      this.hours = hours;
      this.salary = salary;
      this.vacation = vacation; 
   }
   
      //Methods
      
      public String staffRepportToString () 
   {
      return "\tHours a week          : " + hours + 
             "\n\tSalary pr hour      : " + salary +
             "\n\tVacation days pr/y  : " + vacation + 
             "\n\tMonthly salary      : " + getSalaryMonth();
   }
   
   @Override
   public void getUserInformation(String ID)
   {
   
   }
   @Override
   public void printStaffReport(User user)
   {
      System.out.println(ID + " " + accessLevel + " " + hours + " " + salary + " " + vacation);
   }
   
   public String fileFormatString ()
   {
      return firstName + " " + lastName + " " + cpr + " " + type + " " + address[0] + " " +
             address[1] + " " + address[2] + " " + phoneNr + " " + password + " " + ID + " " + 
             accessLevel + " " + hours + " " + salary + " " + vacation; 
   } 
   
      //Setters
   
   public void setType (String type) 
   {
      this.type = type; 
   }
   
   public void setHours (int hours) 
   {
      this.hours = hours;
   }
   
   public void setSalary (double salary) 
   {
      this.salary = salary;
   }
   
   public void setVacation (int vacation) 
   {
      this.vacation = vacation;
   }
   
      //Getters
   
   public int getHours () 
   {
      return hours;
   }
   
   public double getSalary () 
   {
      return salary;
   }
   
   public double getSalaryMonth () 
   {
      return ((hours * salary) * 4);
   }
   
   public int getVacation () 
   {
      return vacation;
   }
   
   public String getID () 
   {
      return "S" + accessLevel + IDCounter;
   }
}