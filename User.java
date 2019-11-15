import java.io.*;
import java.util.*;

public class User 
{   
      //Attributes 
   
   protected Scanner in = new Scanner(System.in);
   protected String firstName;
   protected String lastName;
   protected String cpr;          // maybe change for "String" depending on the '-'
   public String type;         // GU = guest, DI = Director etc.
   protected String[] address = new String[3]; //0 = Street name, 1 = city, 2 = postcode
   protected String phoneNumber;         // max 8 digits.
   protected String password;
   protected String ID;           // Type + 2 last digits from PhoneNr + initals ( first letter of each name)
   protected int accessLevel;     // 0 - 6
   protected final String[] TYPE = { "Guest", "Cleaner", "Receptionist", "Accountant", "Director" };
   
   
      //Methods
      
   // If you need the title instead of the initials
   public String getType (String type) 
   {
      return TYPE[typeNr(type)];
   }

      //Methods WithinClass
   
   // For the toString (Prints user type)
   public int typeNr (String type)  
   {
      for ( int i = 0; i < 5; i++ ) 
      {
         String A = TYPE[i];
         A = A.toUpperCase();
         if ( A.contains(type) )
         {
            return i;
         }
      }
      return -1;
   }
   
   //For printing the adress propperly
   public String add (String[] address) 
   {
      return address[0] + "\n\t               " + address[1] + ", " + address[2];
   }
   
   public String toString () 
   {
      return "\tFull name    : "   + firstName + " " + lastName + 
             "\n\tCpr          : " + cpr + 
             "\n\tUser status  : " + getType(type) +
             "\n\tAddress      : " + add(address) + 
             "\n\tPhonenumber  : " + phoneNumber;
   }
   
   public String fileFormatString () 
   {
      return firstName + " " + lastName + " " + cpr + " " + type + " " + add(address) + " " + phoneNumber;
   }
      
      //Setters
   
   public void setFirstName (String firstName) 
   {
      this.firstName = firstName;
   }
   
   public void setLastName (String lastName) 
   {
      this.lastName = lastName;
   }
   
   public void setCpr (String cpr) 
   {
      this.cpr = cpr;
   }
   
   public void setType (String type) 
   {
      this.type = type;
   }
   
   public void setAddress (String[] address) 
   {
      this.address = address;
   }
   
   public void setPassword (String password) 
   {
      this.password = password;
   }
   
      //Getters
   
   public String getFirstName () 
   {
      return firstName;
   }
   
   public String getLastName () 
   {
      return lastName;
   }
   
   public String getCpr () 
   {
      return cpr;
   }
   
   public String getTypeInitials () 
   {
      return type;
   }
   
   public String[] getAddress () 
   {
      return address;
   }
   
   public String getPhoneNumber () 
   {
      return phoneNumber;
   }
   
   public String getPassword () 
   {
      return password;
   }
   
   public String getID () 
   {
      return ID;
   }
}