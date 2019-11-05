import java.io.*;
import java.util.*;

public class Hotel_E {
  
      // GLOBAL VARIABLES
  
      // Staff Object Arrays & Counters
     
   static Staff[] staffDir = new Staff[1]; // The Hotel will hire only one director.
   static Staff[] staffRec = new Staff[4]; // The Hotel will hire 4 receptionists ( one for each season ).
   static Staff[] staffCle = new Staff[5]; // The Hotel will require 5 cleaning ladies.
   static Staff[] staffAcc = new Staff[2]; // The Hotel will hire 2 accountants. 
   static int[] staffCount = new int[4];
   
      // Guest Requirements
     
   static int[] availableRooms; // 0 - available | 1 - not available
   
      // Other
   
   static Scanner in = new Scanner (System.in);
   static Random rand = new Random();
   static String[] ERROR = { "\tDont try me mate", "\tGive me a real answer.", "\tYou think I'm that easy?",
                             "\tJust... don't. \n\tAnswer the question correctly", "\tInvalid answer. Try again please.",
                             "\tIf you dont stop. \n\tWe will have a problem", "\tI will find you.\n\tAnd i will kill you.",
                             "\tWhat do you want?", "\tpls just pick something", "\twhy are you like this?" };
   static int roomID = 0;
     
         //ACCOUNTING FILE 
     
   static Scanner account = new Scanner ( System.in );
   static int line = 0;
   static String[] acc = new String[2];
  
   public static void main(String[] args) throws FileNotFoundException {
     
         //ACCOUNTING FILE INITIALIZE
     String info = "";  
     account = new Scanner ( new File ( "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/accounting.txt" ));
     while(account.hasNextLine()) {
         info = account.nextLine();
         acc[line++] = info;
     }
     line--;
     
     roomID = Integer.parseInt ( acc[0] );

     char[] numbersChar = { acc[line].charAt(1), acc[line].charAt(4), acc[line].charAt(7), acc[line].charAt(10) };
     for ( int i = 0; i < 4; i ++ ) {
        staffCount[i] = numbersChar[i] - '0';
     }

     
     print('-', 0, 26); 
     System.out.println("\t    -  HOTEL ROYAL   -");
     print('-', 0, 26); 
     
     System.out.println("\nWelcome to Hotel Royal, we hope \n" +
                        "you'll enjoy our services. Here \n" +
                        "you can choose the options you  \n" +
                        "prefer.\n");
     MENU();
     
   }
       
   public static void MENU () throws FileNotFoundException {
     
     boolean RUN = true;
     int choice = 0;
           
        print('-', 0, 26); 
        System.out.println("\t      -    MENU    -");
        print('-', 0, 26); 
        
        System.out.println("\n\t> 1. BOOKING ");
        System.out.println("\n\t> 2. RESERVATION ");
        System.out.println("\n\t> 3. EXTEND BOOKING ");
        System.out.println("\n\t> 4. CLEANING REQUEST ");
        System.out.println("\n\t> 5. CHECK ROOMS ");
        System.out.println("\n\t> 6. STAFF INFO ");
        System.out.println("\n\t> 7. OTHERS ");
        System.out.println("\n\t> 8. EXIT \n");
                
        choice = CHECK("\tCHOOSE FROM 1-8 : ", 0, 9);
        
        switch ( choice ) {
            case 1:
               BOOKING();
               break;
            case 2:
               RESERVATION();
               break;
            case 3:
               EXTEND_BOOKING();
               break;
            case 4:
               CLEANING_REQUEST();
               break;
            case 5:
               CHECK_ROOMS();
               break;
            case 6:              
               STAFF_INFO();
               break;
            case 7:
               OTHERS();
               break;
            case 8:
               EXIT();
         } 
   }
   
   public static void BOOKING ()
                     throws FileNotFoundException {
      
      print('-', 0, 26); 
      System.out.println("\t      -  BOOKING  -");
      print('-', 0, 26);
      
      availableRooms = new int[roomID];
      
      CUSTOMER_REQUIREMENTS ();   
      
      
   }
   
   public static void CUSTOMER_REQUIREMENTS ()
                     throws FileNotFoundException {
      
      File dir = new File ( "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms" );   
      File[] roomFiles = dir.listFiles( new FilenameFilter () {
         public boolean accept ( File dir, String name ) {
            return name.toLowerCase().endsWith( ".txt" );
         }
      } );
      Scanner inFile = new Scanner ( System.in );
  
      System.out.println ( "\tHow many beds should" +
                           "\n\tthe room have ?  " );
      int bedNum = CHECK( "\tCHOOSE BEDS 1 - 6 : " , 0, 7 );
      print('-', 0, 26);
      ROOM_CHECK_BEDS ( bedNum ); 
           
      System.out.println ( "\tWhould you like to" +
                           "\n\thave internet in" +
                           "\n\tthe room ?" );
      int internet = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
      ROOM_CHECK_INTERNET ( internet );      
      
      print('-', 0, 26);
      System.out.println ( "\t  -  AVAILABLE ROOMS  -" );
      print('-', 0, 26);
      
      boolean areAvailableRooms = false;
      int roomChoice, k = 0;
      int[] rooms = new int[roomID];
      for ( int i = 0; i < roomID; i ++ ) {
         if ( availableRooms[i] == 1 ) {
            areAvailableRooms = true;
            rooms[++k] = i;
            inFile = new Scanner ( roomFiles[i] );
            System.out.println ( "\t> " + k + ". " + roomFiles[i].getName() );
         }
      }
      if ( areAvailableRooms ) {
         roomChoice = CHECK( "\tCHOOSE ROOM 1 - " + k + " : " , 0, k + 1 );
         inFile = new Scanner ( roomFiles[rooms[roomChoice]] );
         print('-', 0, 26);
         while ( inFile.hasNextLine() ) {
            String line = inFile.nextLine();
            System.out.println ( line );
         }
      }
      else {
         System.out.println ( "\tThere are no available rooms." +
                              "\n\tWe are sorry for the " +
                              "\n\tinconvenience. " );
      }  
      
      
           
      for ( int i = 0; i < roomID; i ++ ) {
         availableRooms[i] = 0;
      }
      ENTER();
   }
   
   public static void ROOM_CHECK_BEDS ( int choice ) 
                     throws FileNotFoundException {
      File dir = new File ( "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms" );   
      File[] roomFiles = dir.listFiles( new FilenameFilter () {
         public boolean accept ( File dir, String name ) {
         return name.toLowerCase().endsWith( ".txt" );
         }
      } );
           
      Scanner inFile = new Scanner (System.in);
      for ( int i = 0; i < roomID; i ++ ) {
         inFile = new Scanner ( roomFiles[i] );
         inFile.nextLine(); inFile.next(); inFile.next();
         int beds = inFile.nextInt();
         if ( beds == choice ) {
            availableRooms[i] = 1;
         } 
      }
               
   }
   
   public static void ROOM_CHECK_INTERNET ( int choice ) 
                     throws FileNotFoundException {      
      File dir = new File ( "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms" );   
      File[] roomFiles = dir.listFiles( new FilenameFilter () {
         public boolean accept ( File dir, String name ) {
            return name.toLowerCase().endsWith( ".txt" );
         }
      } );

      Scanner inFile = new Scanner (System.in);
      for ( int i = 0; i < roomID; i ++ ) {
         if ( availableRooms[i] == 1 ) {
            inFile = new Scanner ( roomFiles[i] );
            inFile.nextLine();
            inFile.nextLine();
            int roomInternet;
            String line = inFile.nextLine();
            if ( line.contains ( " not " ) ) {
               roomInternet = 2;
            }
            else {
               roomInternet = 1; 
            }
            if ( choice != roomInternet ) {
               availableRooms[i] = 0;
            }
         } 
      }

   }
   
   public static void RESERVATION () {
      
      print('-', 0, 26); 
      System.out.println("\t    -  RESERVATION  -");
      print('-', 0, 26);
   }
   
   public static void EXTEND_BOOKING () {
      
      print('-', 0, 26); 
      System.out.println("\t   -  EXTEND BOOKING  -");
      print('-', 0, 26);
   }
   
   public static void CLEANING_REQUEST () throws FileNotFoundException {
      
      print('-', 0, 26); 
      System.out.println("\t  -  CLEANING REQUEST  -");
      print('-', 0, 26); 
      
      if ( staffCount[2] < 1 ) {
         System.out.println ( "\nWe are sorry, but there are no cleaning\n" +
                              "ladies available the moment. Please come\n" +
                              "back in a short while.\n" );
         print('-', 0, 26);
         ENTER();
      }
      else {
         
         System.out.println ( "\nWhich room are you staying in?\n" );
         
         int roomNum = CHECK( "\tCHOOSE ROOM 1 - " + roomID + " : " , 0, roomID + 1 );         
         
         String[] cleaningTimes = { " at 11am tomorrow.", " at 2pm today.", " in an hour.", " at 4pm today.", " by 9am tomorrow." };
         int cleID = rand.nextInt ( staffCount[2] ) + 1;
         File cleFile = new File ( "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/staff/Cleaning_Lady" + cleID + ".txt" );
         Scanner inFiles = new Scanner ( cleFile );
         inFiles.nextLine ();
         inFiles.next(); inFiles.next(); inFiles.next(); inFiles.next();
         String cleName = inFiles.next();
         
         print('-', 0, 26);
         System.out.println ("\n" + cleName + " will clean Room " + roomNum + 
                              cleaningTimes[rand.nextInt (cleaningTimes.length )] + "\n");
         ENTER();
      }   
   }
      
   public static void OTHERS () throws FileNotFoundException {
      
      print('-', 0, 26); 
      System.out.println("\t       -  OTHERS  -");
      print('-', 0, 26);
      
      int CHOICE = 0;
      
      System.out.println("\n\t> 1. CREATE ROOM ");
      System.out.println("\n\t> 2. CHANGE ROOM ");
      System.out.println("\n\t> 3. HIRE STAFF ");
      System.out.println("\n\t> 4. BACK \n");
            
      CHOICE = CHECK("\tCHOOSE FROM 1-4 : ", 0, 5);
      switch ( CHOICE ) {
      
         case 1:
            CREATE_ROOM();
            break;
         case 2:
            CHANGE_ROOM();
            break;
         case 3:
            HIRE_STAFF();
            break;
         case 4:
            MENU();
      }
   }
   public static void EXIT () {
     
      print('-', 0, 26); 
      System.out.println("\t        -  EXIT  -");
      print('-', 0, 26);
      System.exit ( 0 ); 
   }
   
   public static void CREATE_ROOM () throws FileNotFoundException {
      
         //ATTRIBUTES
      
      String placement = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms/";
      roomID++;
      int CHOICE = 0;
      int beds = 0;
      int inter = 0;
      int price = 0;
      int floor = 0;
      String roomName = "Room" + roomID;
      
      print('-', 0, 26); 
      System.out.println("\t     -  CREATE ROOM  -");
      print('-', 0, 26);
      
         //BEDS
         
      System.out.println("\nHow many available beds will there \n" +
                         "be in the hotelroom?\n");
      beds = CHECK("\tCHOOSE FROM 1-6 : ", 0, 7);
      print('-', 0, 26);
      System.out.println("\t" + beds + " beds chosen.");
      
         //INTERNET
      
      print('-', 0, 26);
      System.out.println("\nWill there be internet int the room?\n");
      inter = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
      boolean internet = true;
      if (inter == 1) {
         internet = true;
      } else if (inter == 2) {
         internet = false;
      }
      print('-', 0, 26);
      System.out.println( internet ? "\tAvailable!" : "\tNot available!");
            
         //PRICE
         
      print('-', 0, 26);
      System.out.println("\nWhat will the price be for the hotel-\n" +
                         "room pr night?\n");
      price = CHECK("\tCHOOSE PRICE PR NIGHT\n\t500 - 10000 : ", 499, 10001);
      print('-', 0, 26);
      System.out.println("\tPrice set to : " + price);
      
         //FLOOR
      
      print('-', 0, 26);
      System.out.println("\nWhat floor will the hotelroom be on?\n");
      floor = CHECK("\tCHOOSE FLOOR : ", -1, 11);
      print('-', 0, 26);
      System.out.println("\tFloor set to : " + floor);
      
         //Show information on created room
      
      print('-', 0, 26); 
      System.out.println("\t     -  FINAL ROOM  -");
      print('-', 0, 26);
      
      rooms RoomName = new rooms(roomID, beds, internet, price, floor);
      System.out.println("\n" + RoomName.toString() + "\n");
            
      print('-', 0, 26);
      System.out.println("\nWould you like to accept the room?\n");
      int accept = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
      if (accept == 1) {
            
         //create new file for Room
         
         PrintStream out = new PrintStream ( new File ( placement + roomName + ".txt" ));
         out.println(RoomName.toString());
         print('-', 0, 26);
         System.out.println("\tRoom " + roomID + " created!");
         
         UPDATE(roomID, 0); //UPDATE ACCOUNTANT
         ENTER();
         
      } else if (accept == 2) {
        
         print('-', 0, 26);
         System.out.println("\tFair enough.");
         roomID--;
         ENTER();
          
      }

   }
   public static void CHANGE_ROOM () throws FileNotFoundException {
      
      String availableFiles = "\tCHOOSE FILE 1 - " + roomID + " : ";
      String placement = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms/";
      int CHOICE = 0;
      int beds = 0;
      int inter = 0;
      int price = 0;
      int floor = 0;
      
      print('-', 0, 26); 
      System.out.println("\t     -  CHANGE ROOM  -");
      print('-', 0, 26);
 
         //SELECT ROOM
      
      System.out.println("\nWhat room would you like to change?\n");      
      CHOICE = CHECK(availableFiles, 0, roomID + 1);
      print('-', 0, 26); 
      String roomName = "Room" + CHOICE;
      System.out.println("\tYou choose : Room " + CHOICE);
      print('-', 0, 26);
      
         //BEDS      //POSSIBLY A BETTER WAY TO MAKE THIS! redundancy check
         
      System.out.println("\nHow many available beds will there \n" +
                         "be in the hotelroom?\n");
      beds = CHECK("\tCHOOSE FROM 1-6 : ", 0, 7);
      print('-', 0, 26);
      System.out.println("\t" + beds + " beds chosen.");
      
         //INTERNET
      
      print('-', 0, 26);
      System.out.println("\nWill there be internet int the room?\n");
      print('-', 0, 26);
      inter = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
      boolean internet = true;
      if (inter == 1) {
         internet = true;
      } else if (inter == 2) {
         internet = false;
      }
      print('-', 0, 26);
      System.out.println( internet ? "\tAvailable!" : "\tNot available!");
            
         //PRICE
         
      print('-', 0, 26);
      System.out.println("\nWhat will the price be for the hotel-\nroom pr night?\n");
      price = CHECK("\tCHOOSE PRICE PR NIGHT\n\t100 - 5000 : ", 99, 5001);
      print('-', 0, 26);
      System.out.println("\tPrice set to : " + price);
      
         //FLOOR
      
      print('-', 0, 26);
      System.out.println("\nWhat floor will the hotelroom be on?\n");
      floor = CHECK("\tCHOOSE FLOOR : ", -1, 11);
      print('-', 0, 26);
      System.out.println("\tFloor set to : " + floor);
      print('-', 0, 26);
      System.out.println("\nCongratulations. You created a new Room!\n" +
                         "Here is the info on the room : \n");
            
         //Print info on room
      
      rooms RoomName = new rooms(CHOICE, beds, internet, price, floor);
      print('-', 0, 26); 
      System.out.println("\t     -  FINAL ROOM  -");
      print('-', 0, 26);
      System.out.println("\n" + RoomName.toString() + "\n");
      
         //ACCEPT ROOM?
      
      print('-', 0, 26);
      System.out.println("\nWould you like to accept the room?\n");
      int accept = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
      if (accept == 1) {
          
         //create new file for Room
         
         PrintStream out = new PrintStream ( new File ( placement + roomName + ".txt" ));
         out.println(RoomName.toString());
         print('-', 0, 26);
         System.out.println("\tRoom " + CHOICE + " changed!");
         ENTER();
         
      } else if (accept == 2) {
         
         print('-', 0, 26);
         System.out.println("\tFair enough.");
         ENTER();
      }
   }
   
   public static void CHECK_ROOMS () throws FileNotFoundException {
      
      print('-', 0, 26);
      int i = 1;
      String placement = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms/";
      String file_name = placement + "Room" + i + ".txt";
      
      System.out.println();
      for(i = 1; i <= roomID; i++) {
         file_name = placement + "Room" + i + ".txt";
         Scanner read = new Scanner ( new File ( file_name ));
         while( read.hasNextLine() ) {
            String line = read.nextLine();
            System.out.println(line);
         }
         System.out.println();
         if ( i < roomID ) {
            print('-', 0, 26);
            System.out.println();
         }
      }
      ENTER();
   }
   
   public static void STAFF_INFO () throws FileNotFoundException {
                     
      String placement = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/staff/";
      File dir = new File ( placement );
      
         //SPECIAL. 
      
      File[] staffFiles = dir.listFiles( new FilenameFilter () {
         public boolean accept ( File dir, String name ) {
            return name.toLowerCase().endsWith( ".txt" );
         }
      } );
      
      print('-', 0, 26);
      System.out.println ("\t     -  STAFF DOCS  -");
      print('-', 0, 26);
      for ( int i = 0; i < staffFiles.length; i ++ ) { 
         System.out.println ( "\n\t> " + ( i + 1 ) + ". " + staffFiles[i].getName() );
      } 
      
      System.out.println ();                             
      print('-', 0, 26);
      System.out.println ( "\n\tThe Hotel currently has :" + 
                           "\n\t> DIRECTORS : " + staffCount[0] + 
                           "\n\t> RECEPTIONISTS : " + staffCount[1] + 
                           "\n\t> CLEANING LADIES : " + staffCount[2] + 
                           "\n\t> ACCOUNTANTS : " + staffCount[3] + "\n" );
      print('-', 0, 26);
     
      System.out.println ( "\nWould you like to view additional\n" +
                           "information about a member ?\n" );
      int answer = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
     
      if ( answer == 1 ) {
         int fileNum = CHECK("\tCHOOSE STAFF INFO 1-" + staffFiles.length + " : ", 0, staffFiles.length+1) - 1;
          
         print('-', 0, 26);
         System.out.println ("\t\t" + staffFiles[fileNum].getName() ); 
         print('-', 0, 26);
         Scanner inFile = new Scanner ( staffFiles[fileNum] );
         System.out.println();
         while ( inFile.hasNextLine() ) {
           String line = inFile.nextLine();
           System.out.println ( line );
         }
         System.out.println();
         ENTER();
      }
      else {
         ENTER();
      }
   }
   
   public static void HIRE_STAFF () throws FileNotFoundException {
      
      int CHOICE = 0;
      
      print('-', 0, 26); 
      System.out.println("\t      -  HIRE STAFF  -");
      print('-', 0, 26);
      
      System.out.println("\n\t> 1. DIRECTOR ");
      System.out.println("\n\t> 2. RECEPTIONIST ");
      System.out.println("\n\t> 3. CLEANING ");
      System.out.println("\n\t> 4. ACCOUNTANT ");
      System.out.println("\n\t> 5. BACK \n");
            
      CHOICE = CHECK("\tCHOOSE FROM 1-5 : ", 0, 6);
         switch ( CHOICE ) {
         
            case 1:
                  // Director Hiring
               STAFF_TYPE_CREATE ( staffDir, 0, "Director" );
               EXIT_BACK_STAFF ( ); 
               break;
            case 2:
                  // Receptionist Hiring
               STAFF_TYPE_CREATE ( staffRec, 1, "Receptionist" );
               EXIT_BACK_STAFF ( ); 
               break;
            case 3:
                  // Cleaning Lady Hiring
               STAFF_TYPE_CREATE ( staffCle, 2, "Cleaning_Lady" );
               EXIT_BACK_STAFF ( ); 
               break;
            case 4:
                  // Accountant Hiring
               STAFF_TYPE_CREATE ( staffAcc, 3, "Accountant" );
               EXIT_BACK_STAFF ( ); 
               break;
            case 5:
               OTHERS();
         }
   }
      
   public static void STAFF_TYPE_CREATE ( Staff[] stfarr, int staffType, String title ) 
                        throws FileNotFoundException {
      
      String placement = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/staff/";
      
      if ( staffCount[staffType] < stfarr.length ) {
    
         stfarr[staffCount[staffType]] = new Staff ( title );
         STAFF_CREATE ( stfarr[staffCount[staffType]] );
                  
         print('-', 0, 26); 
         System.out.println("\t- NEW STAFF MEMBER SHEET -");
         print('-', 0, 26);
         
         System.out.println ( "\n" + stfarr[staffCount[staffType]].toString () + "\n" );
         print('-', 0, 26);
            
         System.out.println("\tDo you accept staff info?");
         int accept = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
         if (accept == 1) {
            
               
            //create new file for Room
            
            PrintStream out = new PrintStream ( new File ( placement + title + ( staffCount[staffType] + 1 ) + ".txt" ));
            out.println(stfarr[staffCount[staffType]].toString());
            print('-', 0, 26);
            System.out.println("\t" + title + " " + ( staffCount[staffType] + 1 ) + " created!");
            
            staffCount[staffType]++;
            UPDATE(staffCount, 1); //UPDATE ACCOUNTANT
            
         } else if (accept == 2) {
           
            print('-', 0, 26);
            System.out.println("\tFair enough.");
         }
      } else {
               
         String errorText = "\nWe are sorry, Hotel Royale is not \n" +
                            "currently hiring a new " + title +
                            ".\nPlease come back at a later date.\n";
         STAFF_ERROR ( errorText );  
      }
   }
   
   public static void EXIT_BACK_STAFF () throws FileNotFoundException {
     
      print('-', 0, 26);                  
      System.out.println("\n\t> 1. HIRE MORE STAFF ");
      System.out.println("\n\t> 2. BACK TO MENU \n");

      int CHOICE = CHECK("\tCHOOSE FROM 1-2 : ", 0, 3);
                  
      switch ( CHOICE ) {
         case 1:
            HIRE_STAFF ();
            break;
         case 2:
            MENU();
      }
   }
   
   public static void STAFF_ERROR ( String errorText ) throws FileNotFoundException {
      
      print('-', 0, 26); 
      System.out.println("\t       -   ERROR   -");
      print('-', 0, 26);
      System.out.println(errorText);
      print('-', 0, 26);
                  
      System.out.println("\n\t> 1. BACK ");
      System.out.println("\n\t> 2. EXIT \n");

      int CHOICE = CHECK("\tCHOOSE FROM 1-2 : ", 0, 3);
                  
      switch ( CHOICE ) {
         case 1:
            OTHERS( );
            break;
         case 2:
            EXIT();
      }
   } 
   
   public static void STAFF_CREATE ( Staff stf ) {
   
      print('-', 0, 26); 
      System.out.println("\t   -  A STAFF MEMBER  -");
      print('-', 0, 26);
      
         // Name
      
      System.out.println("\nPlease insert Staff Member's first name.\n");
      print('-', 0, 26); 
      System.out.print("\tWRITE NAME : ");    
      stf.setFirstName ( in.next() );
      print('-', 0, 26);
      System.out.println("\tFirst name : " + stf.getFirstName());
      
      print('-', 0, 26);
      System.out.println("\nPlease insert Staff Member's last name.\n");
      print('-', 0, 26);   
      System.out.print("\tWRITE NAME : ");   
      stf.setLastName ( in.next() );
      print('-', 0, 26);
      System.out.println("\tLast name : " + stf.getLastName());

         // Salary
      
      print('-', 0, 26);
      System.out.println("\nPlease insert Staff Member's salary.\n");
      int salary = CHECK("\tCHOOSE FROM\n\t10.000-65.000 : ", 9999, 65001);     
      stf.setSalary ( salary );
      print('-', 0, 26);
      System.out.println("\tSalary set to : " + stf.getSalary());
      
         // Telephone Number
         
      print('-', 0, 26);
      System.out.println("\nPlease insert Staff Member's telephone number.\n");
      print('-', 0, 26);  
      System.out.print("\tWRITE NUMBER : ");     
      stf.setTeleNum ( in.next() + in.nextLine() );
      print('-', 0, 26);
      System.out.println("\tNumber set to : " + stf.getTeleNum());
   }
   
   public static int date(int month, int day) {
      return (month - 1) * 31 + day;
   }
   
   public static void UPDATE(int number, int line) throws FileNotFoundException {
      
      PrintStream ACC = new PrintStream ( new File ( "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/accounting.txt/" ));
      acc[line] = String.valueOf ( number );
      for(int i = 0; i < acc.length; i++) {
         ACC.println(acc[i]);
      }
   }
 
   public static void UPDATE(int[] numbers, int line) throws FileNotFoundException {
      
      PrintStream ACC = new PrintStream ( new File ( "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/accounting.txt/" ));
      acc[line] = Arrays.toString ( numbers );
      for(int i = 0; i < acc.length; i++) {
      ACC.println(acc[i]);
      }
   }
   
   public static int CHECK(String message, int from, int to) {
      
      boolean test = true;
      int CHOICE = 0;
      
      do {
         print('-', 0, 26);
         System.out.print(message);
         if(in.hasNextInt()) {
            CHOICE = in.nextInt();
            if(CHOICE > from && CHOICE < to) {
               test = true;
            } else {
               print('-', 0, 26);
               System.out.println("\n" + ERROR[rand.nextInt(ERROR.length - 1)] + "\n");
               test = false;
            }
         } else {
            print('-', 0, 26);
            System.out.println("\n" + ERROR[rand.nextInt(ERROR.length - 1)] + "\n");
            test = false;
            in.next();
         }
      } while (!test);
      return CHOICE;
   }
   
   public static void ENTER() throws FileNotFoundException{
     
      print('-', 0, 26);
      System.out.print("\t      -  CONTINUE  -");
      Scanner scanner = new Scanner(System.in);
      scanner.nextLine();
      MENU ();
   }
   
   public static void print(char a, int b, int c) {
   
      if(b == 0) {
       System.out.print("\t"); 
      }
      for(int i = 0; i < c; i++) {
         System.out.print(a);
      }
      if(b == 0) {
       System.out.println(); 
      }
   }
}