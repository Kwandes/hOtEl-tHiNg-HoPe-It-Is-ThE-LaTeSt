import java.io.*;
import java.util.*;

public class User 
{   
      //Attributes 
   
   private Scanner in = new Scanner(System.in);
   private String firstName;
   private String lastName;
   private String cpr;          // maybe change for "String" depending on the '-'
   public String type;         // GU = guest, DI = Director etc.
   private String[] address = new String[3]; //0 = Street name, 1 = city, 2 = postcode
   private int phoneNr;         // max 8 digits.
   private String password;
   private String ID;           // Type + 2 last digits from PhoneNr + initals ( first letter of each name)
   private int accessLevel;     // 0 - 6
   private final String[] TYPE = { "Guest", "Cleaner", "Receptionist", "Accountant", "Director" };
   
      //Constructor 
   
   public User () {}
   
   public User (String firstName, String lastName, String cpr, String type, String[] address, int phoneNr, String password) 
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.cpr = cpr;
      this.type = type;
      this.address = address;
      this.phoneNr = phoneNr;
      this.password = password;
   }
   
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
             "\n\tPhonenumber  : " + phoneNr;
   }
   
   public String fileFormatString () 
   {
      return firstName + " " + lastName + " " + cpr + " " + type + " " + add(address) + " " + phoneNr;
   }
   
/*   public void createUser () 
   {
      System.out.print("First name : ");
      firstName = in.next();
      
      System.out.print("Last name  : ");
      lastName = in.next();
      
      System.out.print("Members cpr Nr : ");
      cpr = in.next();
      
      System.out.print("Address - Street name : ");
      address[0] = in.nextLine();
      
      System.out.print("Address - City : ");
      address[1] = in.nextLine();
      
      System.out.print("Address - Postcode : ");
      address[2] = in.nextLine();
      
      System.out.print("Phonenumber : ");
      phoneNr = in.nextInt();
            
      do {
         System.out.print("Create password : ");
         String pass1 = in.next();
         System.out.print("Verify password : ");
         String pass2 = in.next();
         if ( !pass1.equals(pass2) ) 
         {
            System.out.println("The password didnt match, Try again");
         }
      } while ( !pass1.equals(pass2) );
         
   }   */
      
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
   
   public int getPhoneNr () 
   {
      return phoneNr;
   }
   
   public String getPassword () 
   {
      return password;
   }
}