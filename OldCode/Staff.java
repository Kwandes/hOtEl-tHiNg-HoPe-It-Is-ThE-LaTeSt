public class Staff {

      // Attributes
      
   private String title;
   private String firstName;
   private String lastName;
   private double salary;
   private String teleNum;
   
      // Constructors
   
   public Staff () {
   
   }
 
   public Staff ( String title ) {
      this.title = title;
   }

   
   public Staff ( String title, String firstName, String lastName ) {
      this.title = title;
      this.firstName = firstName;
      this.lastName = lastName;
   }
   
   public Staff ( String title, String firstName, String lastName, double salary, String teleNum ) {
      this.title = title;
      this.firstName = firstName;
      this.lastName = lastName;
      this.salary = salary;
      this.teleNum = teleNum;
   }
   
      // Methods
      
      
      // Getters
      
   public String getTitle () {
      return title;
   }
   public String getFirstName () {
      return firstName;
   }
   public String getLastName () {
      return lastName;
   } 
   public double getSalary () {
      return salary;
   }
   public String getTeleNum () {
      return teleNum;
   }
      
      // Setters
      
   public void setTitle ( String title ) {
      this.title = title;
   }
   public void setFirstName ( String firstName ) {
      this.firstName = firstName;
   }
   public void setLastName ( String lastName ) {
      this.lastName = lastName;
   }
   public void setSalary ( double salary ) {
      this.salary = salary;
   }
   public void setTeleNum ( String teleNum ) {
      this.teleNum = teleNum;
   }
      
      // toString Method                                                                              
   
   public String toString () {
      return  "The Staff Member's Title is : " + title +
               "\nThe name is : " + firstName + " " + lastName +
               "\nSalary : " + salary + "kr." + 
               "\nPhone No. : " + teleNum;
   } 
   
}