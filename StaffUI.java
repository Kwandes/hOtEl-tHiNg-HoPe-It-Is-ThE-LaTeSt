import java.io.*;
import java.util.*;

public class StaffUI extends CLI
{
   private String firstName;
   private String lastName;
   private String fullName;
   private String cpr;
   private String[] address = new String[3];
   private String phoneNumber;
   private String password;
   private String pass1;
   private String pass2;
   private int hours;
   private double salary;
   private int vacation;
   private int headerLength = 100;
   
   private MainFrame mf;

   public StaffUI(Staff user, String title, MainFrame mfRef)
   {
      this.title = title;
      this.screenNumber = 0;
      this.loggedUser = user.getLastName();
      this.userAccessLevel = user.getAccessLevel();
      this.seperator = "----------------------------------------------------------------------------------------------------"; // 100 dashes
      this.running = true;
      this.mf = mfRef;
   }
   
   public StaffUI(Staff user, String title, MainFrame mfRef, int accessLevel)
   {
      this.title = title;
      this.screenNumber = 0;
      this.loggedUser = user.getLastName();
      this.userAccessLevel = accessLevel;
      this.seperator = "----------------------------------------------------------------------------------------------------"; // 100 dashes
      this.running = true;
      this.mf = mfRef;
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
            
            case 1: //Create a Guest
               createGuest();
               break; 
            case 2:
               createStaff();
               break;
            case 3:
               createBooking();
               break;
            case 98:
               mainMenu();
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
   
   public void main(String[] args)
   {
      createGuest();
      //createStaff();
   }
   
   public void createBooking()
   {
      
   }
   
   
   public void createStaff()
   {
      //this.screenNumber = 2;
      //Staff firstName, lastName, cpr, type, address, phoneNumber, password,int hours, double salary, int vacation)
      creationTemplate("Staff");
      print("How many hours will " + firstName + " be working weekly?");
      hours = intCheck();
      
      print("What is " + firstName + "'s hourly salary?");
      salary = doubleCheck();
      
      print("How many vacation days will " + firstName + "have yearly?");
      vacation = intCheck();
      
      Staff created = new Staff( firstName, lastName, cpr, "ST", address, phoneNumber, password, hours, salary, vacation);
   }

   public void createGuest() 
   {
      //this.screenNumber = 1;
      creationTemplate("Guest");
      Guest created = new Guest(firstName, lastName, cpr, "GU", address, phoneNumber, password, 0, 0.0);
      System.out.println(); 
   }

   public void creationTemplate(String type)
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
   
   public double doubleCheck()
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
   
   
   public int intCheck()
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
   
   public String phoneNumberCheck(Scanner console)
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
   public String cprCheck(Scanner console)
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
   
   public String nameFixer(String name)
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
   
   public void printLines()
   {
      for (int i = 0; i < headerLength; i++)
      {
      System.out.print("-");
      }
      System.out.println();
   }
     
   public void print(String text)
   {
      System.out.println("\t>" + text);
   }
  
   public void header(String text)
   {
      print("HOTEL PLAZA");
      print("@ " + "CURRENT STAFF");
      printLines();
      print(text);
      printLines();
      System.out.println();
      
   }  
   
   public void mainMenu()
   {
      this.screenNumber = 98;
      header("Main Menu");
   }
   
   public void exit()
   {
      this.running = false;
   }
   
   ////////// Setters & Getters //////////
   
   public void setMFRef(MainFrame mfRef)
   {
      this.mf = mfRef;
   }
}  