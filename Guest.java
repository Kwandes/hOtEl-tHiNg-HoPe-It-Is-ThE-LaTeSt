public class Guest extends User implements UserManagement 
{
   private int guestDays;
   private double moneySpent;
   
      //Constructor
      
   public Guest (String firstName, String lastName, String cpr, String type, 
                 String[] adress, int phoneNr, String password, int guestDays, double moneySpent)
   {
      super(firstName, lastName, cpr, "GU", adress, phoneNr, password);
      this.guestDays = guestDays;
      this.moneySpent = moneySpent;
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
   
      //Setters
   
   public void setDays (int guestDays) 
   {
      this.guestDays = 0;
   }
   
   public void setMoney (double moneySpent) 
   {
      this.moneySpent = 0.0;
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
   
   public String guestRepportToString ()
   {
      return "Total days at hotel         : " + guestDays +
             "\nTotal amount of money spent : " + moneySpent;
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