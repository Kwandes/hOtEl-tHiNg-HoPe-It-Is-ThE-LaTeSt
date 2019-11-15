import java.io.*;
import java.util.*;

public class StaffUI extends CLI
{
   private  int spacerVariable = 40; 
     private  String firstName;
     private  String lastName;
     private  String fullName;
     private  String cpr;
     private  String[] address = new String[3];
     private  String phoneNumber;
     private  String password;
     private  String pass1;
     private  String pass2;
     private  int hours;
     private  double salary;
     private  int vacation;

   private  int headerLength = 100;

   public StaffUI(User user, String title)
   {
      this.title = title;
      this.screenNumber = 10;
      this.loggedUser = user.getLastName();
      //this.userAccessLevel = user.getAccessLevel();
      this.seperator = "----------------------------------------------------------------------------------------------------"; // 100 dashes
      this.running = true;
   }
      public StaffUI(User user, String title, int accessLevel)
   {
      this.title = title;
      this.screenNumber = 10;
      this.loggedUser = user.getLastName();
      this.userAccessLevel = accessLevel;
      this.seperator = "----------------------------------------------------------------------------------------------------"; // 100 dashes
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
               spacer();
               createGuest();
               returnQuit();
               break; 
            case 2:
               spacer();
               createStaff();
               returnQuit();
               break;
            case 3:
               spacer();
               createBooking();
               returnQuit();
               break;
            case 5:
               exit();
               break;
            case 10:
               mainMenu();
               break;
            default:
               print("invalid input, please try again.");
               System.out.println();
               System.out.println();
               mainMenu();
               break;
         }
      }  
   }
// public  void main(String[] args)
// {
//    createGuest();
//    //createStaff();
// }
   
   public  void createBooking()
   {
      
   }
   
   
   public  void createStaff()
   {
      this.screenNumber = 2;
      //Staff firstName, lastName, cpr, type, address, phoneNumber, password,int hours, double salary, int vacation)
      creationTemplate("Staff");
      print("How many hours will " + firstName + " be working weekly?");
      hours = intCheck();
      
      print("What is " + firstName + "'s hourly salary?");
      salary = doubleCheck();
      
      print("How many vacation days will " + firstName + "have yearly?");
      vacation = intCheck();
      
      Staff created = new Staff( firstName, lastName, cpr, "ST", address, phoneNumber, password, 0, hours, salary, vacation);
      returnQuit();
   }

   public  void createGuest() 
   {
      //this.screenNumber = 1;
      creationTemplate("Guest");
      Guest created = new Guest(firstName, lastName, cpr, "GU", address, phoneNumber, password, 0);
      System.out.println();
      returnQuit(); 
   }

   public  void creationTemplate(String type)
   {
      Scanner input = new Scanner(System.in);
      Scanner inputAddress = new Scanner(System.in);
      
      header("Create " + type);
      
      print("Please type the first name of the new " + type + ", in only one word.");
      firstName = nameFixer(input.next());
      
      print("Please type the last name of the new " + type +", in only one word.");
      lastName = nameFixer(input.next());
      
      print("Please type the CPR number of the " + type);
      cpr = cprCheck(input);
      
      
      System.out.println();
      print("Please type the " + type + "'s address in three parts.");
      System.out.println();
      print("First, please type the stree name of " + firstName + "'s residence");
      address[0] = nameFixer(inputAddress.nextLine());
      
      print("Next, please type the city name of " + firstName + "'s residence.");
      address[1] = nameFixer(inputAddress.nextLine());
      
      print("Lastly, please type the Postcode of " + firstName + "'s residence.");
      address[2] = nameFixer(inputAddress.nextLine());
      
      print("Please type the " + type + "'s phone number");
      phoneNumber = phoneNumberCheck(input);
      
      print("Please create a password for the new " +  type);           
      
      do //Shamelessly stole Teo's password code 
      { 
         print("Type the password.");
         pass1 = input.next();
         print("please type the same password a second time");
         pass2 = input.next();
         if ( !pass1.equals(pass2) ) 
         {
            print("The passwords do not match, please try again.");
         }
      } while ( !pass1.equals(pass2) );
      print("Password creation successful");
      password = pass1;
      System.out.println();  
   }
   
   
   public  double doubleCheck()
   {
      Scanner input = new Scanner(System.in);
      double number;
      
      while (!input.hasNextDouble())
      {
         String shit = input.next();
         print("Invalid input detected, please only type numbers.");
         
      }
      number = input.nextDouble();
      return number;
   }
   
   
   public  int intCheck()
   {
      Scanner input = new Scanner(System.in);
      int number;
      
      while (!input.hasNextInt())
      {
         String shit = input.next();
         print("Invalid input detected, please only type numbers without a comma.");
         
      }
      number = input.nextInt();
      return number;
   }
   
   public  String phoneNumberCheck(Scanner console)
   {
      String number;
      number = console.next();

      while (!(number.length() == 8)) //checking that number length is correct
      {
         if (number.length() > 8)
         {
            print("Invalid phone number, please input a correct phone number");
            print("Phone numbers should look something like this: 12345678\n");
            number = console.next();
         }
            
         if (number.length() < 8)
         {
            print("Invalid phone number, please input a correct phone number");
            print("Phone numbers should look something like this: 12345678\n");
            number = console.next();
         }
      }
      char intCheck;
      for (int i=0; i<8;i++)
      {
         intCheck = number.charAt(i);
         while (!Character.isDigit(intCheck))
         {
            print("Phone numbers must contain 8 numbers, and no country code.");
            print("For example '12345678'. Please try again.");
            number = console.next();
         
            while (!(number.length() == 8)) //checking that phone number length is corret.
            {
               if (number.length() > 8)
               {
                  print("Invalid phone number, please input a correct phone number");
                  print("Phone numbers should look something like this: 12345678\n");
                  number = console.next();
               }
                     
               if (number.length() < 8)
               {
                  print("Invalid phone number, please input a correct phone number");
                  print("Phone numbers should look something like this: 12345678\n");
                  number = console.next();
               }
            }
            intCheck = number.charAt(i);
         }
            
      }
      return number;
   }

   
   
   
   
   //Check to see that CPR is valid
   public  String cprCheck(Scanner console)
   {  
      String cpr = "";
      cpr = console.next();
      
      while (!(cpr.length() == 11)) //checking that CPR number is corret.
      {
         if (cpr.length() > 11)
         {
            print("CPR number is too long, please input a correct CPR number");
            print("Cpr should look something like this: 123456-1234\n");
            cpr = console.next();
         }
            
         if (cpr.length() < 11)
         {
            print("CPR number is too short, please input a correct CPR number");
            print("Cpr should look something like this: 123456-1234\n");
            cpr = console.next();
         }
      }
      while (cpr.charAt(6) != '-')
      {
         print("CPR numbers must contain 6 numbers, a dash and the remaining 4 numebers.");
         print("For example '123456-1234'. Please try again.");
         cpr = console.next();
      }
      char intCheck;
      for (int i=0; i<11;i++)
      {
         if (i==6)
         {
            continue;
         }
         intCheck = cpr.charAt(i);
         while (!Character.isDigit(intCheck))
         {
            print("CPR numbers must contain 6 numbers, a dash and the remaining 4 numebers.");
            print("For example '123456-1234'. Please try again.");
            cpr = console.next();
         
            while (!(cpr.length() == 11)) //checking that CPR number is corret.
            {
               if (cpr.length() > 11)
               {
                  print("CPR number is too long, please input a correct CPR number.");
                  print("Cpr should look something like this: 123456-1234\n");
                  cpr = console.next();
               }
                     
               if (cpr.length() < 11)
               {
                  print("CPR number is too short, please input a correct CPR number.");
                  print("Cpr should look something like this: 123456-1234\n");
                  cpr = console.next();
               }
            }
            while (cpr.charAt(6) != '-')
            {
               print("CPR numbers must contain 6 numbers, a dash and the remaining 4 numebers.");
               print("For example '123456-1234'. Please try again.");
               cpr = console.next();
            }
            intCheck = cpr.charAt(i);
         }
            
      }
      return cpr;
   }
   
   
   public  String nameFixer(String name)
   {
      String namePart;
      String fixedName = "";
      String tempString = "";
      char tempChar;
      Scanner nameConsole = new Scanner(name);   
         
      while (nameConsole.hasNext())
      {    
         namePart = nameConsole.next();
      
         for(int i=0;i<namePart.length();i++)
         {
            if (i== 0)
            {
               tempChar = namePart.charAt(i);
               tempString = String.valueOf(tempChar);
               tempString = tempString.toUpperCase();
               fixedName += tempString;
            } else 
            {
            
               tempChar = namePart.charAt(i);
               tempString = String.valueOf(tempChar);
               tempString = tempString.toLowerCase();
               fixedName += tempString;
            }
         }
         fixedName += " ";
      }
      return fixedName;  
   }
   
   
   
   
   public  void printLines()
   {
      for (int i = 0; i < headerLength; i++)
      {
      System.out.print("-");
      }
      System.out.println();
   }
     
   public  void print(String text)
   {
      System.out.println("\t>" + text);
   }
  
   public  void header(String text)
   {
      print("HOTEL PLAZA");
      print("@ " + "CURRENT STAFF");
      printLines();
      print(text);
      printLines();
      System.out.println();
      
   }

   public  void spacer()
   {
      for (int i=0; i<spacerVariable; i++)
      {
         System.out.println();
      }
   }

   public void returnQuit()
   {
      int choice;
      
      print("What would you like to do?");
      System.out.println();
      print("1 Return to main menu");
      print("2 Quit");
      choice = intCheck();
      switch (choice)
      {
         case 1:
            this.screenNumber = 10; //mainMenu's number
            break;
         case 2:
            this.running = false;
            break;
      }
   }
  
   
   public void mainMenu()
   {
      this.screenNumber = 10; 
      Scanner input = new Scanner(System.in);
      header("Main Menu");
      print("1 Create a guest");
      print("2 create a staff");
      print("3 create a booking");
      screenNumber = intCheck();
   }
   
   public void exit()
   {
      this.running = false;
   }
}  