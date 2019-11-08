public class Guests {
   
      // Attributes
      
   private int guestID;
   private String firstName;
   private String lastName;
   private String[] address = new String[4];
   private String teleNum;
      
      // Constructors
      
   public Guests () {
   
   }
   
   public Guests ( int guestID, String firstName, String lastName ) {
      this.guestID = guestID;
      this.firstName = firstName;
      this.lastName = lastName;
   }
   
   public Guests ( int guestID, String firstName, String lastName, String[] address, String teleNum ) {
      this.guestID = guestID;
      this.firstName = firstName;
      this.lastName = lastName;
      this.address = address;
      this.teleNum = teleNum;
   }
      
      // Methods
      
   public void displayAddress () {
   
      System.out.println ( "/같같같같�\\" );
      System.out.println ( "| Address |" );
      System.out.println ( "\\같같같같�/" );
      
      for ( int i = 0; i < address.length; i ++ ) {
         System.out.println ( address[i] );
      }
      
      System.out.println ( "*=========*" );
       
   }   
      
      // Getters
      
   public int getGuestID () {
      return guestID;
   }
   public String getFirstName () {
      return firstName;
   }
   public String getLastName () {
      return lastName;
   }
   public String[] getAddress () {
      return address;
   }
   public String getTeleNum () {
      return teleNum;
   }
      
      // Setters
      
   public void setGuestID ( int guestID ) {
      this.guestID = guestID;
   }
   public void setFirstName ( String firstName ) {
      this.firstName = firstName;
   }
   public void setLastName ( String lastName ) {
      this.lastName = lastName;
   }
   public void setAddress ( String[] address ) {
      this.address = address;
   }
   public void setTeleNum ( String teleNum ) {
      this.teleNum = teleNum;
   }
      
      // toString Method
      
   public String toString () {
      
   
      
      return "The Guest's ID is : " + guestID +
              "\nThe name is : " + firstName + " " + lastName +
              "\nPhone No. : " + teleNum +
              "\nAddress : " + "\n" + address[0]
              + "\n" + address[1] + "\n" + 
              address[2] + "\n" + address[3] ;
   }
}