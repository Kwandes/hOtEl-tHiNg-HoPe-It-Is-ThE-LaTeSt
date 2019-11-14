public class Staff extends User implements UserManagement
{
   private int hours;           //pr week
   private double salary;       //pr hour
   private int vacation;        //pr year
   
      //Constructer 
      
   public Staff (String firstName, String lastName, String cpr, String type, 
                 String[] address, String phoneNr, String password, int hours, 
                 double salary, int vacation) 
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.cpr = cpr;
      this.type = type;
      this.address = address;
      this.phoneNr = phoneNr;
      this.password = password;
      this.hours = hours;
      this.salary = salary;
      this.vacation = vacation; 
      this.accessLevel = calculateAccessLevel();
   }
   
   // Explicit access level, for that sweet security override and backdoor
   public Staff (String firstName, String lastName, String cpr, String type, 
                 String[] address, String phoneNr, String password, int hours, 
                 double salary, int vacation, int accessLevel) 
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.cpr = cpr;
      this.type = type;
      this.address = address;
      this.phoneNr = phoneNr;
      this.password = password;
      this.hours = hours;
      this.salary = salary;
      this.vacation = vacation;
      this.accessLevel = accessLevel;
   }
   
      //Methods
      
      
   
      //Setters
   public int calculateAccessLevel()
   {
      for(int i = 0; i < TYPE.length; i++)
      {
         if( TYPE[i].equals(this.type)) return i;
      }
      return 0;
   }
   
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
   
   public int getAccessLevel()
   {
      return accessLevel;
   }
      
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
   
   }
}