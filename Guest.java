public class Guest extends User implements UserManagement 
{
   private int guestDays;
   private double moneySpent;
   
      //Constructor
   
   public Guest () {} 
      
   public Guest (String firstName, String lastName, String cpr, String type, 
                 String[] adress, String phoneNr, String password, int IDCounter)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.cpr = cpr;
      this.type = type;
      this.address = address;
      this.phoneNr = phoneNr;
      this.password = password;
      this.guestDays = 0;
      this.moneySpent = 0.0;
      this.ID = "G" + super.getAccessLevel() + IDCounter;
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
   public void printGuestReport(User user)
   {
      System.out.println( ID + " " + accessLevel + " " + guestDays + " " + moneySpent );
   }
   
   public String fileFormatString ()
   {
      return firstName + " " + lastName + " " + cpr + " " + type + " " + address[0] + " " +
             address[1] + " " + address[2] + " " + phoneNr + " " + password + " " + ID + " " + accessLevel
             + " " + guestDays + " " + moneySpent;
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
   
   public void setID (String ID)
   {
      this.ID = ID;
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
   
   public String getID () 
   {
      return ID;
   }
}