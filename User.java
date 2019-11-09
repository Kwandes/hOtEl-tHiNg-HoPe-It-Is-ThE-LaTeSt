// placeholder class

public class User
{
   private String phoneNumber;
   private String password;
   private String userID = "EA42";
   
   public String getPhoneNumber()
   {
      return "+69 420";
   }
   public String getPassword()
   {
      return "yeet";
   }
   
   public String getUserID()
   {
      return this.userID;
   }
   
   public String toString()
   {
      return userID + " | " + phoneNumber;
   }

}