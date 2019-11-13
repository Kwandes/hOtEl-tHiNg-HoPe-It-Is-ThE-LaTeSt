import java.util.*;
import java.io.*;

public class GuestUI extends CLI
{   
   private static int size = 100;
   private static String[] error = { addText("Too long", size), addText("Too short", size), 
                                     addText("Cannot contain spaces", size), addText("Only numbers", size), 
                                     addText("Only letters", size), addText("Invalid answer", size), 
                                     addText("Cannot contain numbers", size), addText("The password didnt match, Try again", size)};
   private static Scanner in = new Scanner(System.in);
   private static Scanner in2 = new Scanner(System.in); //bug issue with the scanners, had to make an extra.
   private static int IDCounter = 0;
   
   public GuestUI(User user, String title)
   {
      this.title = title;
      this.screenNumber = 0;
      this.loggedUser = user.getLastName();
      this.userAccessLevel = 0; // Cannot be more than 0 for security reasons
      this.seperator = print(size); 
      this.running = true;
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
               registerGuest();
               break;
            case 2:
               mainMenu();
               break;
            case 99:
               exit();
         }
      }  
   }
   
   public static void registerGuest () 
   {
      String firstName;
      String lastName;
      String cpr;
      String[] address = new String[3];
      int phoneNr;
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
      int streetNumber = check("\tPlease write Street number (ex. 41) : ", 0, 999);
      streetName += Integer.toString(streetNumber);
      System.out.println(streetName);
      address[0] = streetName;
      print();
      
      System.out.print("\tAddress - City : ");
      String city = checkName(9);
      address[1] = city;
      print();
      
      System.out.print("\tAddress - Postcode : ");
      int postCode = check("\tPlease write Postcode (ex. 2200) : ", 999, 9999);
      address[2] = Integer.toString(postCode);
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
      
      Guest Teo = new Guest (firstName, lastName, cpr, address, phoneNr, password, IDCounter);
      System.out.println("\n" + Teo.toString());
   }
   
   public static int check (String question, int min, int max)
   {
      int input = 0;
      boolean TEST = false;
      int a = 0;
      
      while ( !TEST ) 
      {
         TEST = true;
         if ( a == 1 )
         {
            System.out.print("Please write a number " + min + " - " + max + " : ");
         }
         else { a++; }
         if ( in.hasNextInt() ) 
         {
            input = in.nextInt();
            if ( input > min && input < max ) 
            {
               TEST = true; 
            } 
            else 
            {
               errorMessage(5);
               TEST = false;
            }
         } 
         else 
         {
            errorMessage(5);
            TEST = false;
            in.next();
         }
      }
      return input;
   }
   
   public static String checkAddress () 
   {
      String input = "";
      boolean TEST = false;
      int a = 0; 
      
      while ( !TEST ) 
      {
         TEST = true;
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
            TEST = false;
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
   
   public static String checkName (int a) //a is for printing the correct comment.
   {
      String input = "";
      boolean TEST = false;
      
      while ( !TEST ) 
      {
         TEST = true;
         if ( a == 1 ) 
         {
            System.out.print("\tPlease write your first name : ");
         }
         else if ( a == 2 ) 
         {
            System.out.print("\tPlease write your last name : ");
         } 
         else if ( a == 10 ) 
         {
            System.out.print("\tPlease write City name : ");
         }
         else if ( a == 7 )
         {
            System.out.print("\tPlease write street name : ");
         }
         else if ( a == 8 ) { a--; }
         else if ( a == 0 ) { a++; }
         else if ( a == 9 ) { a++; }
         else { a--; }
         
         if ( a == 9 || a == 10 )     //if this is removed there will be an issue with 
         {                            //getting the city name.
            input = in2.nextLine();   //i thik java created a bug that could not be fixed.
         }                            //so i made a completely new scanner to be sure it 
         else                         //hadnt obtained a value already.
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
            TEST = false;
         }
      }
      
      String inputWith = "";
      inputWith += Character.toUpperCase(input.charAt(0));  //This is just to make the first letter
      for ( int i = 1; i < input.length(); i++ )            //in the names UpperCase and the rest are
      {                                                     //LowerCase at the same time.
         inputWith += inputWith += Character.toLowerCase(input.charAt(i));
      }
      
      return inputWith;
   }
   
   public static String checkCpr () 
   {
      String input = "";
      boolean TEST = false;
      int a = 0;
      
      while ( !TEST ) 
      {
         TEST = true;
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
            TEST = false;
         }
      }
      return input;
   }
   
   public static void printText (String a, int b) 
   {
      int out = (b - a.length()) / 2;
      for(int i = 0; i < out; i++ ) {
         System.out.print(" ");
      }
      System.out.println(a);
   }
   
   public static String addText (String a, int b) 
   {
      String line = "";
      int out = (b - a.length()) / 2;
      for(int i = 0; i < out; i++ ) {
         line += " ";
      }
      line += a;
      return line;
   }
   
   public static String print (int b) 
   {
      String fullString = "";
      for(int i = 0; i < b; i++ ) {
         fullString += "-";
      }
      return fullString;
   }
   
   public static void print () 
   {
      System.out.println(print(size));
   }
   
   public static void errorMessage (int a) 
   {
      print();
      System.out.println(error[a]);
      print();
   }
   
   public void mainMenu()
   {
      this.screenNumber = 1;
   }
   
   public void exit()
   {
      this.running = false;
   }
}