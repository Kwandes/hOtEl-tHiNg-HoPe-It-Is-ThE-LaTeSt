public class User 
{   
      //Attributes 
   
   private String firstName;
   private String lastName;
   private String cpr;            // maybe change for "String" depending on the '-'
   public String type;            // GU = guest, DI = Director etc.
   private String[] address = new String[3]; //0 = Street name, 1 = city, 2 = postcode
   private String phoneNr;          // max 8 digits.
   private String password;
   protected String ID;           // Type + 2 last digits from PhoneNr + initals ( first letter of each name)
   private int accessLevel;       // 0 - 6
   private final String[] TYPE = { "Guest", "Cleaner", "Receptionist", "Accountant", "Director" };
   
      //Constructor 
   
   public User () {}
     
   public User (String firstName, String lastName, String cpr, 
                String type, String[] address, String phoneNr, String password, int IDCounter) 
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.cpr = cpr;
      this.type = type;
      this.address = address;
      this.phoneNr = phoneNr;
      this.password = password;
      this.accessLevel = typeNr(type);
      this.ID = type + IDCounter;
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
      return "\tUser ID      : "   + ID + 
             "\n\tFull name    : " + firstName + " " + lastName + 
             "\n\tCpr          : " + cpr + 
             "\n\tUser status  : " + getType(type) +
             "\n\tAddress      : " + add(address) + 
             "\n\tPhonenumber  : " + phoneNumber;
   }
   
   public String fileFormatString () 
   {
      return ID + " " + firstName + " " + lastName + " " + cpr + " " + type + " " + accessLevel + " " + add(address) + " " + phoneNr;
   }
   
      //Setters
   
   public void setID (String ID) 
   {
      this.ID = ID;
   }
      
   public void setAccessLevel (int accessLevel) 
   {
      this.accessLevel = accessLevel;
   }
   
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
   
   public void setPhoneNr (String phoneNr)
   {
      this.phoneNr = phoneNr;
   }
   
   public void setPassword (String password) 
   {
      this.password = password;
   }
   
      //Getters
   
   public String getID () 
   {
      return ID;
   }
   
   public int getAccessLevel () 
   {
      return accessLevel;
   }
   
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