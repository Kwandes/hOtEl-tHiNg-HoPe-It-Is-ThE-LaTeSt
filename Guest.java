public class Guest extends User  
{
   private int guestDays;
   private double moneySpent;
   
      //Constructor
   
   public Guest () {}
     
   public Guest (String firstName, String lastName, String cpr, 
                 String[] adress, long phoneNr, String password, int IDCounter)
   {
      super(firstName, lastName, cpr, "GU", adress, phoneNr, password, IDCounter);
      this.guestDays = 0;
      this.moneySpent = 0.0;
   }  
   
      //AddToMethods
   
   public void setGuestDays (int guestDays) 
   {
      this.guestDays += guestDays;
   }
   
   public void setMoneySpent (double moneySpent) 
   {
      this.moneySpent += moneySpent;
   }
   
      //Getters
   
   public int getGuestDays () 
   {
      return guestDays;
   }  
   
   public double getMoneySpent () 
   {
      return moneySpent;
   }
   
      //Methods
   
   public String guestReportToString ()
   {
      return "\tDays at hotel   : " + guestDays +
             "\n\tMoney spent     : " + moneySpent;
   }
   
   public String guestReportFileToString ()
   {
      return guestDays + " " + moneySpent;
   }
}