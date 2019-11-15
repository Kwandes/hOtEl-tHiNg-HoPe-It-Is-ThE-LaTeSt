public class Staff extends User
{
   private int hours;           //pr week
   private double salary;       //pr hour
   private int vacation;        //pr year
   
      //Constructer 
   
   public Staff () {}
      
   public Staff (String firstName, String lastName, String cpr, String type, 
                 String[] address, long phoneNr, String password, int IDCounter, int hours, 
                 double salary, int vacation) 
   {
      super(firstName, lastName, cpr, type, address, phoneNr, password, IDCounter);
      this.hours = hours;
      this.salary = salary;
      this.vacation = vacation; 
   }
   
      //Methods
      
      
   
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
      
   public String staffReportToString () 
   {
      return "\tStaff ID            : " + ID + 
             "\n\tHours a week        : " + hours + 
             "\n\tSalary pr hour      : " + salary +
             "\n\tVacation days pr/y  : " + vacation + 
             "\n\tMonthly salary      : " + getSalaryMonth();
   }
   
   public String staffReportFileToString () 
   {
      return ID + " " + hours + " " + salary + " " + vacation + " " + getSalaryMonth();
   }
}