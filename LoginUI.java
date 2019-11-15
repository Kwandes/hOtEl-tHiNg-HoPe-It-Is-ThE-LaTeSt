import java.util.*;
import java.io.*;

public class LoginUI extends CLI
{   
   private  int size = 100;
   private  String[] error = { addText("Too long", size), addText("Too short", size), 
                                     addText("Cannot contain spaces", size), addText("Only numbers", size), 
                                     addText("Only letters", size), addText("Invalid answer", size), 
                                     addText("Cannot contain numbers", size), addText("The password didnt match, Try again", size)};
   private  Scanner in = new Scanner(System.in);
   private  Scanner in2 = new Scanner(System.in); //bug issue with the scanners, had to make an extra.
   private  int IDCounter = 0;
   
   private Guest guest;
   private Staff staff;
   private MainFrame mf;
   
   public LoginUI(String title, MainFrame mfRef)
   {
      this.title = title;
      this.screenNumber = 1;  // MainMenu
      this.seperator = print(size); 
      this.running = true;
      this.mf = mfRef;
      
      guest = null;
      staff = null;
   }
   
   public void display()
   {
      while(running)
      {
         switch(screenNumber)
         {
            // actual display
            // listen to inputs, show different screens depending on input
            // each "screen" has a specific screen Number
            // screens choosing example below
            case 1:
               mainMenu();
               break;
            case 2:
               login();
               break;
            case 3:
               registerGuest();
               break;
            case 99:
               exit();
               break;
            default:
               mainMenu();
               break;
         }
      }  
   }
   
   public void mainMenu()
   {
      Scanner input = new Scanner(System.in);
      
      print();
      printText(title, size);
      print();
      System.out.println(">What would you like to do");
      System.out.println("(1) Log In \n(2) Register \n(3) Exit");
      
      String temp  = input.nextLine();
      switch(temp)
      {
         case "1":
            this.screenNumber = 2;
            break;
         case "2":
            this.screenNumber = 3;
            break;
         case "3":
            this.screenNumber = 99;
            break;
         default:
            this.screenNumber = 1;
            break;
      }
   }
   
   public void login()
   {  
      Scanner input = new Scanner(System.in);
      
      print();
      printText(title, size);
      printText("Log In", size);
      print();
      
      System.out.print(">Username: ");
      
      String userName  = input.nextLine();
      
      System.out.print(">Password: ");
      
      String password  = input.nextLine();
      
      printText("All good bro, ain't even gonna check", size);
      
      // Placeholder user init
      String[] arr = new String[3];
      arr[0] = "Yeet";
      arr[1] = "Yote";
      arr[2] = "yoten";
      this.guest = new Guest ("Faisal", "Boolyan", "1234561234", "GU", arr, "12345678", "passwd", 0, 0.0);   // Double check the contructors, right now IDCounter is passed for a guestDays parameter in Guest
      this.staff = null;
      // Implement proper user creation based on array gotten from MF
      
      this.screenNumber = 99; // Exit loginUI
   }
   
   public  void registerGuest () 
   {
      String firstName;
      String lastName;
      String cpr;
      String[] address = new String[3];
      String phoneNr;
      String password;
      String pass1;
      String pass2;
      IDCounter++; 
      
      print();
      printText("- REGISTER GUEST -", size);
      print();
      
      System.out.print("\tFirst name : ");
      firstName = checkName(0);
      print();
      
      System.out.print("\tLast name  : ");
      lastName = checkName(3);
      print();
      
      System.out.print("\tMembers cpr Nr : ");
      cpr = checkCpr();
      System.out.println(cpr);
      print();
      
      System.out.print("\tAddress - Street name : ");
      String streetName = checkAddress();
      System.out.println(streetName);
      print();
      
      System.out.print("\tAddress - Street number : ");
      String streetNumber = check("\tPlease write Street number (ex. 41) : ", 0, 999);
      streetName += streetNumber;
      System.out.println(streetName);
      address[0] = streetName;
      print();
      
      System.out.print("\tAddress - City : ");
      String city = checkName(9);
      address[1] = city;
      print();
      
      System.out.print("\tAddress - Postcode : ");
      String postCode = check("\tPlease write Postcode (ex. 2200) : ", 999, 9999);
      address[2] = postCode;
      System.out.println(address[2]);
      print();
      
      System.out.print("\tPhonenumber : ");
      phoneNr = check("\tPlease write your Phonenumber : ", 1000000, 99999999);
      print();
                 
      do {
         System.out.print("\tCreate password : ");
         pass1 = in.next();
         System.out.print("\tVerify password : ");
         pass2 = in.next();
         if ( !pass1.equals(pass2) ) 
         {
            errorMessage(7);
         }
      } while ( !pass1.equals(pass2) );
      print();
      password = pass1;
      
      this.guest = new Guest (firstName, lastName, cpr, "GU", address, phoneNr, password, IDCounter, 1.0);   // Double check the contructors, right now IDCounter is passed for a guestDays parameter in Guest
      //System.out.println("\n" + Teo.toString());
   }
   
   public  String check (String question, int min, int max)
   {
      String input = null;
      boolean isValid = false;
      int a = 0;
      
      while ( !isValid ) 
      {
         isValid = true;
         if ( a == 1 )
         {
            System.out.print("Please write a number " + min + " - " + max + " : ");
         }
         else { a++; }
         if ( in.hasNextInt() ) 
         {
            input = in.next();
            if ( Integer.parseInt(input) > min && Integer.parseInt(input) < max ) 
            {
               isValid = true; 
            } 
            else 
            {
               errorMessage(5);
               isValid = false;
            }
         } 
         else 
         {
            errorMessage(5);
            isValid = false;
            in.next();
         }
      }
      return input;
   }
   
   public  String checkAddress () 
   {
      String input = "";
      boolean isValid = false;
      int a = 0; 
      
      while ( !isValid ) 
      {
         isValid = true;
         if ( a == 1 ) 
         {
            System.out.print("\tPlease write street name : ");
         }
         else { a++; }
         
         input = in.nextLine();
         input = input.trim();
         
         if ( input.length() < 5 )
         {
            errorMessage(1);
            input = "";
         }
         
         for ( int i = 0; i < input.length(); i++ ) 
         {
            if ( Character.isDigit(input.charAt(i)) )
            {
               errorMessage(6);
               input = "";
            }
         }
         if ( input.length() < 3 ) 
         {
            isValid = false;
         }
      }
      
      String inputWith = "";
      inputWith += Character.toUpperCase(input.charAt(0));  //This is just to make the first letter
      for ( int i = 1; i < input.length(); i++ )            //in the names UpperCase.
      {  
         inputWith += Character.toLowerCase(input.charAt(i));
      }
      inputWith += " ";
      
      return inputWith;
   }
   
   public  String checkName (int commentNumber) 
   {
      String input = "";
      boolean isValid = false;
      
      while ( !isValid ) 
      {
         isValid = true;
         
         switch ( commentNumber )
         {
            case 0:
               commentNumber++;
               break;
            case 1:
               System.out.print("\tPlease write your first name : ");
               break;
            case 2:
               System.out.print("\tPlease write your last name : ");
               break;
            case 3:
               commentNumber--;
               break;
            case 7:  
               System.out.print("\tPlease write street name : ");
               break;
            case 8:
               commentNumber--;
               break;
            case 9:
               commentNumber++;
               break;
            case 10:
               System.out.print("\tPlease write City name : ");
         }
         
         if ( commentNumber == 9 || commentNumber == 10 )  //if this is removed there will be an issue with 
         {                                                 //getting the city name.
            input = in2.nextLine();                        //i thik java created a bug that could not be fixed.
         }                                                 //so i made a completely new scanner to be sure it 
         else                                              //hadnt obtained a value already.
         {
            input = in.nextLine();
         }
         input = input.trim();
         
         System.out.println(input);
         
         if ( input.contains(" ") )
         {
            input = "";
            errorMessage(2);
         } 
         else if ( input.length() > 11 ) 
         {
            errorMessage(0);
            input = "";
         }
         else if ( input.length() < 3 )
         {
            errorMessage(1);
            input = "";
         }
                  
         for ( int i = 0; i < input.length(); i++ )
         {
            if ( Character.isDigit(input.charAt(i)) )
            {
               errorMessage(4);
               input = "";
            }
         }
         if ( input.length() < 3 ) 
         {
            isValid = false;
         }
      }
      
      String inputWith = "";
      inputWith += Character.toUpperCase(input.charAt(0));  //This is just to make the first letter
                                                            //in the names UpperCase and the rest are
      for ( int i = 1; i < input.length(); i++ )            //LowerCase at the same time.
      {
         inputWith += Character.toLowerCase(input.charAt(i));
      }
      
      return inputWith;
   }
   
   public  String checkCpr () 
   {
      String input = "";
      boolean isValid = false;
      int a = 0;
      
      while ( !isValid ) 
      {
         isValid = true;
         if ( a == 1 ) 
         {
            System.out.print("\tPlease write your CPR (ex. 2208972041) : ");
         }
         else { a++; } 
         
         input = in.nextLine();
         input = input.trim();
         
         if ( input.contains(" ") )
         {
            input = "";
            errorMessage(2);
         } 
         else if ( input.length() == 10 )
         {
            for ( int i = 0; i < 10; i++ ) 
            {
               if ( !Character.isDigit(input.charAt(i)) )
               {
                  errorMessage(3);
                  input = "";
                  break;
               }
            }
         } 
         else if ( input.length() < 10 )
         {
            errorMessage(1);
            input = "";
         }
         else 
         {
            errorMessage(0);
            input = "";
         }
         
         if ( input.length() < 3 ) 
         {
            isValid = false;
         }
      }
      return input;
   }
   
   public  void printText (String text, int numberOfSpaces) 
   {
      int out = (numberOfSpaces - text.length()) / 2;
      for(int i = 0; i < out; i++ ) {
         System.out.print(" ");
      }
      System.out.println(text);
   }
   
   public  String addText (String text, int numberOfSpaces) 
   {
      String line = "";
      int out = (numberOfSpaces - text.length()) / 2;
      for(int i = 0; i < out; i++ ) {
         line += " ";
      }
      line += text;
      return line;
   }
   
   public  String print (int numberOfChar) 
   {
      String fullString = "";
      for(int i = 0; i < numberOfChar; i++ ) {
         fullString += "-";
      }
      return fullString;
   }
   
   public  void print () 
   {
      System.out.println(print(size));
   }
   
   public  void errorMessage (int message) 
   {
      print();
      System.out.println(error[message]);
      print();
   }
   
   public void exit()
   {
      this.running = false;
      //System.exit(0);
   }
   
   ////////// Getters and Setters //////////
   
   public Guest getGuest()
   {
      return this.guest;
   }
   
   public Staff getStaff()
   {
      return this.staff;
   }
}