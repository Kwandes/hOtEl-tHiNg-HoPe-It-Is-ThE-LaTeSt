import java.io.*;
import java.util.*;

public class Hotel_E {
  
      // GLOBAL VARIABLES
      
          // Guest Requirements
     
   static int[] availableRooms; // 0 - available | 1 - not available
   
  
      // Staff Object Arrays & Counters
     
   static Staff[] staffDir = new Staff[1]; // The Hotel will hire only one director.
   static Staff[] staffRec = new Staff[4]; // The Hotel will hire 4 receptionists ( one for each season ).
   static Staff[] staffCle = new Staff[5]; // The Hotel will require 5 cleaning ladies.
   static Staff[] staffAcc = new Staff[2]; // The Hotel will hire 2 accountants. 
   static int[] staffCount = new int[4];
   
      // Other
   
   static int[] MONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   static String[] nameMonth = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "okt", "nov", "dec" };
   static Scanner in = new Scanner (System.in);
   static Random rand = new Random();
   static String[] ERROR = { "\tDont try me mate", "\tGive me a real answer.", "\tYou think I'm that easy?",
                             "\tJust... don't. Answer \n\tthe question correctly", "\tInvalid answer. Try again \n\tplease.",
                             "\tIf you dont stop. \n\tWe will have a problem", "\tI will find you.\n\tAnd i will kill you.",
                             "\tWhat do you want?", "\tpls just pick something", "\twhy are you like this?" };
   static int roomID = 0;
   static int guestID = 0;
     
         //ACCOUNTING FILE 
     
   static Scanner account = new Scanner ( System.in );
   static int line = 0;
   static String[] acc = new String[3];
  
   public static void main(String[] args) throws FileNotFoundException {
     
         //ACCOUNTING FILE INITIALICE
         
     String info = "";  
     account = new Scanner ( new File ( "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/accounting.txt" ));
     while(account.hasNextLine()) {
         info = account.nextLine();
         acc[line++] = info;
     }
     line=1;
     
     roomID = Integer.parseInt ( acc[0] );
     guestID = Integer.parseInt ( acc[2] );

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
        System.out.println("\n\t> 2. EXTEND BOOKING ");
        System.out.println("\n\t> 3. CLEANING REQUEST ");
        System.out.println("\n\t> 4. CHECK ROOMS ");
        System.out.println("\n\t> 5. STAFF INFO ");
        System.out.println("\n\t> 6. OTHERS ");
        System.out.println("\n\t> 7. EXIT \n");
                
        choice = CHECK("\tCHOOSE FROM 1-7 : ", 0, 8);
        
        switch ( choice ) {
            case 1:
               BOOKING();
               break;
            case 2:
               EXTEND_BOOKING();
               break;
            case 3:
               CLEANING_REQUEST();
               break;
            case 4:
               CHECK_ROOMS();
               break;
            case 5:              
               STAFF_INFO();
               break;
            case 6:
               OTHERS();
               break;
            case 7:
               EXIT();
         } 
   }
   
   public static void BOOKING () throws FileNotFoundException {
      
      print('-', 0, 26); 
      System.out.println("\t      -  BOOKING  -");
      print('-', 0, 26);
      
      availableRooms = new int[roomID];
      
      CUSTOMER_REQUIREMENTS ();
      
      String placeDate = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms/date/";
      String placeRoom = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms/";
      int startDay = 0; 
      int startMonth = 0;
      int daysStay = 0;
      int START = 0;
      int[] rooms = new int[30];
      int roomNumber = 0;
      int[] roomOption = new int [30];
      int[] roomDayInfo = new int[365];
      
      startMonth = MONTH_CHECK(1);
      startDay = DAY_CHECK(startMonth, 1);
      
      print('-', 0, 26);
      System.out.println("\tYou chose : " + startDay + "/" + nameMonth[startMonth - 1] + " - 2019"); 
      int check = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
      
      if(check == 2) {
         BOOKING();
      }
      START = date(startMonth, startDay);                                     //DAY NUMBER 
      print('-', 0, 26);
      System.out.println("\nHow long would you like to stay?\n" +
                         "You'll save money the more days you stay!\n"); 
      daysStay = CHECK("\tCHOOSE FROM 1-30 days : ", 0, 31);                  //AMOUNT OF DAYS
      
         //CHECK IF ROOM IS AVAILABLE
            
      for(int i = 1; i <= roomID; i++) {
         if ( availableRooms[i-1] == 1 ) {
         Scanner scan = new Scanner ( new File ( placeDate + "Room" + i + "Date.txt" ));
         boolean yes = true;
         
         int num = 0;
         while(scan.hasNextInt()) {
            roomDayInfo[num] = scan.nextInt();
            num++;
         }
         
         for( int j = START; j < START + daysStay; j++) {
         
            if( roomDayInfo[j] == 0 ) {
               yes = true;  
                            
            } else if ( roomDayInfo[j] == 1 ) {
               yes = false;
               print('-', 0, 26);
               System.out.println("\tRoom nr. " + i + " is occupied.");
               break;
            }
         }
              //IF ROOM IS AVAILABLE PRINT ROOM 
         
         if( yes ) {
            Scanner file = new Scanner ( new File ( placeRoom + "Room" + i + ".txt" ));
            print('-', 0, 26);
            roomNumber++;
            roomOption[roomNumber] = i;
            System.out.println("\tOPTION NUMBER : " + roomNumber);
            print('-', 0, 26);
            System.out.println();
            while( file.hasNextLine() ) {
               String line = file.nextLine();
               System.out.println(line);
            }
            System.out.println();
         } 
        } 
      }
      
         //RETRIEVE INFO ON ROOM SELECTION
      
      int roomChoice = 0;
      roomChoice = CHECK("\tCHOOSE YOUR ROOM : ", 0, (roomNumber + 1));
      print('-', 0, 26);
      System.out.println("\tYou chose room number : " + roomOption[roomChoice]);
      print('-', 0, 26);
      String fileChoice = placeRoom + "Room" + roomOption[roomChoice] + ".txt";
      Scanner select = new Scanner ( new File ( fileChoice ));
      
         //PRINT SELECTED ROOM!
      
      System.out.println();
      while ( select.hasNextLine() ) {
         String line = select.nextLine();
         System.out.println(line);
      }
      System.out.println();
      
         //LOCATE PRICE IN FILE!
      
      Scanner SELECT = new Scanner ( new File ( fileChoice ));
      String op = SELECT.nextLine() + SELECT.nextLine() + SELECT.nextLine() + SELECT.next() + SELECT.next();
      double PriceLocation = SELECT.nextDouble();  

      BUY_INFO(daysStay, PriceLocation);   //CALCULATE AND PRINT INFO
      
      print('-', 0, 26);
      System.out.println("\nWould you like to accept the room?\n");  //CONFIRM
      int accept = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
      print('-', 0, 26);
      
      if ( accept == 1 ) {
         
         System.out.println("\nGreat! Your room has been booked.\n" +
                            "Please pay in reception. Have a nice\n" +
                            "day. See you the " + startDay + "/" + nameMonth[startMonth - 1] + ".\n");
         REGISTER_GUEST (roomOption[roomChoice]);
         
            //UPDATE ROOM DATE FILE 
         
         for( int j = START; j < START + daysStay; j++) {
            roomDayInfo[j] = 1;
            PrintStream out = new PrintStream ( new File ( placeDate + "Room" + roomOption[roomChoice] + "Date.txt" ));
            for ( int a = 0; a < 365; a++ ) {
               out.print(roomDayInfo[a] + " ");
            }
         }
         ENTER();
      } else if ( accept == 2 ) {
         System.out.println("\tFair enough.");
         ENTER();
         MENU();
      }
      
   for ( int i = 0; i < roomID; i ++ ) {
      availableRooms[i] = 0;
   }
   }
   
   public static void REGISTER_GUEST ( int room )
                     throws FileNotFoundException {
                     
      Guests gst = new Guests ();
      guestID ++;
      gst.setGuestID ( guestID );
                     
      print('-', 0, 26); 
      System.out.println("\t   -  REGISTER GUEST  -");
      print('-', 0, 26); 
      
         // Name
      
      System.out.println("\nPlease insert Guest's first name.\n");
      print('-', 0, 26); 
      System.out.print("\tWRITE NAME : ");    
      gst.setFirstName ( in.next() );
      print('-', 0, 26);
      System.out.println("\tFirst name : " + gst.getFirstName());
      
      print('-', 0, 26);
      System.out.println("\nPlease insert Guest's last name.\n");
      print('-', 0, 26);   
      System.out.print("\tWRITE NAME : ");   
      gst.setLastName ( in.next() );
      print('-', 0, 26);
      System.out.println("\tLast name : " + gst.getLastName());
      
          // Address
          
      String[] address = new String[4];
      print('-', 0, 26);
      System.out.println("\nPlease insert Guest's address.\n");
      print('-', 0, 26);  
      System.out.print("\tWRITE STREET : ");     
      address[0] = ( in.next() + in.nextLine() );
      System.out.print("\tWRITE POSTAL CODE : ");     
      address[1] = ( in.next() + in.nextLine() );
      System.out.print("\tWRITE CITY : ");     
      address[2] = ( in.next() + in.nextLine() );
      System.out.print("\tWRITE COUNTRY : ");     
      address[3] = ( in.next() + in.nextLine() );     
      print('-', 0, 26);
      System.out.println ( "\t Adress Set !" );
      gst.setAddress ( address );
                  
         // Telephone Number
         
      print('-', 0, 26);
      System.out.println("\nPlease insert Guest's telephone\nnumber.\n");
      print('-', 0, 26);  
      System.out.print("\tWRITE NUMBER : ");     
      gst.setTeleNum ( in.next() + in.nextLine() );
      print('-', 0, 26);
      System.out.println("\tNumber set to : " + gst.getTeleNum());
      
      String dir = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/guests/";
      String name = dir + "guest" + guestID;
      File gstFile = new File ( name );
      PrintStream out = new PrintStream ( gstFile );
      out.println ( gst.toString() );
      out.println ( "Room : " + room );
      
      print('-', 0, 26);
      System.out.println ( "\tGuest Info Saved !" );
          
      UPDATE ( guestID, 2 );
                     
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
      if ( !areAvailableRooms ) {
         System.out.println ( "\tThere are no available rooms." +
                              "\n\tWe are sorry for the " +
                              "\n\tinconvenience. " );
      }  
      
      
           
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

   
   public static void EXTEND_BOOKING () throws FileNotFoundException {
      
      print('-', 0, 26); 
      System.out.println("\t   -  EXTEND BOOKING  -");
      print('-', 0, 26);

      int[] roomDayInfo = new int[365];
      int[] Bookings = new int[30];
      int[] START_DAY = new int[30];
      
      String location = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms/";
      Scanner in = new Scanner ( System.in );
      
         //FIND ROOM WITH BOOKING 
         
      System.out.println("\nWhat hotelroom is your booking in?\n");
      
      int ROOM = CHECK("\tCHOOSE FROM 1-" + roomID + " : ", 0, roomID + 1);
      print('-', 0, 26);
      
      String dateFile = location + "date/Room" + ROOM + "Date.txt";
      String DATAFILE = location + "Room" + ROOM + ".txt";
      Scanner info = new Scanner ( new File ( dateFile ));
      
         //GET ROOM DATE INFO
          
      for ( int a = 0; a < 365; a++ ) {
         int number = info.nextInt();
         roomDayInfo[a] = number;
      }
            
         //SEE THE BOOKINGS FOR THE ROOM AND PRINT THEM
      
      int book = 1;
      for ( int i = 0; i < 365; i++ ) {
         int number = roomDayInfo[i];
         if ( number == 1 ) {
            int call = BOOKING_CHECK(i, roomDayInfo);
            System.out.println("\nBooking nr. " + reservation + " is " + call + " days long.");
            System.out.println("Date start is " + DATE_BOOKING(i) + "- 2019.\n");
            START_DAY[book] = i;    //Start date of reservation
            Bookings[book] = call;  //reservation duration
            book++;                 //choice of reservation 
            i += call;              //to skip the reservation
         }
      }
      
         //CHOOSE THE BOOKING YOU WANT TO CHANGE
      
      print('-', 0, 26);
      System.out.println("\nWhich reservartion would you like\nto change?\n");
      int RESERV = CHECK("\tCHOOSE FROM 1-" + (book - 1) + " : ", 0, book + 1);
      print('-', 0, 26);
            
      reservation = 0; //reset the reservation number.
      
         //SHOW HOW MANY DAYS ARE BOOKED.
      
      int dayStay = Bookings[RESERV];
      System.out.println("\nLooks like your room has " + dayStay + " days \n" +
                         "booked from the choosen date. How \n" +
                         "much would you like to extend your \n" +
                         "stay?\n");
      int option = CHECK("\tCHOOSE FROM 1-" + (30 - dayStay) + " : ", 0, (31 - dayStay));
      int EXTRA = option + dayStay;

      System.out.println();
      for ( int i = START_DAY[RESERV]; i < START_DAY[RESERV] + EXTRA; i++ ) {
         roomDayInfo[i] = 1;
      }
      
         //NEW PRICE INFO 
      
      Scanner SELECT = new Scanner ( new File ( DATAFILE ));
      String op = SELECT.nextLine() + SELECT.nextLine() + SELECT.nextLine() + SELECT.next() + SELECT.next();
      double PriceLocation = SELECT.nextDouble();  

      BUY_INFO(EXTRA, PriceLocation);   //CALCULATE AND PRINT INFO
      
         //CHECK IF THEY ACCEPT EXTENTION
      
      print('-', 0, 26);
      System.out.println("\nWould you like to accept the room?\n");
      int accept = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
      print('-', 0, 26);
      
         //UPDATE ROOM DATE FILE 
      
      PrintStream INFO = new PrintStream ( new File ( dateFile ));
      if ( accept == 1 ) {   
         for ( int i = 0; i < 365; i++ ) {
            INFO.print(roomDayInfo[i] + " ");
         }
         print('-', 0, 26);
         System.out.println("\tSucces, the changes have \n\tbeen made.");
      } else if ( accept == 2 ) {
         System.out.println("\tFair enough");
      } 
      ENTER();
      MENU();
   } 
   
   static int reservation = 0;
   
   public static int BOOKING_CHECK (int a, int[] roomDay) {
      reservation++;
      int days = 0;
      for ( int i = a; i < 365 - a; i++ ) {
         if ( roomDay[i] == 0 ) {
            break;
         }
         days++;
      }
      return days;
   }
   
   public static int BOOKING_DAY (int a, int[] roomDay) {
      
      int i;
      for ( i = a; i < 365 - a; i++ ) {
         if ( roomDay[i] == 0 ) {
            break;
         }
      }
      return i;
   }
   
   public static String DATE_BOOKING (int BOOKING) {
      
      int sum = MONTH[0];
      int call = 0;
      for (int i = 1; i < 12; i++ ) {
         int number = MONTH[i];
         if ( BOOKING > sum ) {
            sum += number;
            call++;
         } else {
            break;
         }
      }
      int days = 0;
      if( sum == MONTH[0] ) {
         days = BOOKING;
      } else {
         days = sum - BOOKING;
      }
      String monthDate = nameMonth[call] + "/" + days;
      return monthDate;
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
      } else {
         
         System.out.println ( "\nWhich room are you staying in?\n" );
         int roomNum = CHECK( "\tCHOOSE ROOM 1 - " + roomID + " : " , 0, roomID + 1 );         
         
         String[] cleaningTimes = { " at 11am tomorrow.", " at 2pm today.", 
                                    " in an hour.", " at 4pm today.", " at 9am tomorrow." };
         int cleID = rand.nextInt ( staffCount[2] ) + 1;
         File cleFile = new File ( "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/staff/Cleaning" + cleID + ".txt" );
         Scanner inFile = new Scanner ( cleFile );
         inFile.nextLine ();
         inFile.next(); inFile.next(); inFile.next(); inFile.next();
         String cleName = inFile.next();
         
         print('-', 0, 26);
         System.out.println ("\n" + cleName + " will clean room " + roomNum + 
                              cleaningTimes[rand.nextInt (cleaningTimes.length )] + "\n");
         ENTER();
      }   
   }
      
   public static void OTHERS () throws FileNotFoundException {
      
      print('-', 0, 26); 
      System.out.println("\t       -  OTHERS  -");
      print('-', 0, 26);
      
      int CHOICE = 0;
      
      System.out.println("\n\t> 1. CREATE NEW ROOM ");
      System.out.println("\n\t> 2. CHANGE ROOM ");
      System.out.println("\n\t> 3. HIRE STAFF ");
      System.out.println("\n\t> 4. RESET ROOM ");
      System.out.println("\n\t> 5. BACK \n");
            
      CHOICE = CHECK("\tCHOOSE FROM 1-5 : ", 0, 6);
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
            RESET_ROOM();
            break;
         case 5:
            MENU();
      }
   }
   
   public static void CREATE_ROOM () throws FileNotFoundException {
      
         //ATTRIBUTES
      
      String placement1 = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms/date/";
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
            
         //FLOOR
      
      print('-', 0, 26);
      System.out.println("\nWhat floor will the hotelroom be on?\n");
      floor = CHECK("\tCHOOSE FLOOR 1-10 : ", -1, 11);
      print('-', 0, 26);
      System.out.println("\tFloor set to : " + floor);
         
         //PRICE
         
      print('-', 0, 26);
      System.out.println("\nWhat will the price be for the hotel-\n" +
                         "room pr night?\n");
      price = CHECK("\tCHOOSE PRICE PR NIGHT\n\t500 - 10000 : ", 499, 10001);
      print('-', 0, 26);
      System.out.println("\tPrice set to : " + price);
            
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
            
         //Create schedule for room
         
         int[] DATE = new int[365];
         PrintStream date = new PrintStream ( new File ( placement1 + roomName + "Date.txt" ));
         for (int i = 0; i < 365; i++) {
            DATE[i] = 0;
            date.print(0 + " ");
         }
         
         //create new file for Room
         
         PrintStream out = new PrintStream ( new File ( placement + roomName + ".txt" ));
         out.println(RoomName.toString());
         print('-', 0, 26);
         System.out.println("\tRoom " + roomID + " created!");
         print('-', 0, 26);
         UPDATE(roomID, 0); //UPDATE ACCOUNTANT
         
         System.out.println("\nWould you like to create more rooms?\n");
         print('-', 0, 26);
         System.out.println("\n\t> 1. CREATE NEW ROOM ");
         System.out.println("\n\t> 2. BACK TO MENU \n");
         
         int after = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
         
         switch ( after ) {
            case 1:
               CREATE_ROOM();
               break;
            case 2:
               MENU();
         }
                  
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
                  
         //FLOOR
      
      print('-', 0, 26);
      System.out.println("\nWhat floor will the hotelroom be on?\n");
      floor = CHECK("\tCHOOSE FLOOR 1-10 : ", -1, 11);
      print('-', 0, 26);
      System.out.println("\tFloor set to : " + floor);
      print('-', 0, 26);
      System.out.println("\nCongratulations. You created a new Room!\n" +
                         "Here is the info on the room : \n");
      
         //PRICE
         
      print('-', 0, 26);
      System.out.println("\nWhat will the price be for the hotel-\nroom pr night?\n");
      price = CHECK("\tCHOOSE PRICE PR NIGHT\n\t100 - 5000 : ", 99, 5001);
      print('-', 0, 26);
      System.out.println("\tPrice set to : " + price);
            
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
   
   public static void RESET_ROOM () throws FileNotFoundException {
      
      String placeDate = "/C:/Users/crist/OneDrive/Documents/JGrasp/KEAHotelProject-master/Classes/rooms/date/";
      int[] roomDayInfo = new int[365];
      int startMonth1 = 0;
      int startDay1 = 0;
      int startMonth2 = 0;
      int startDay2 = 0;
      int START = 0;
      int END = 0;
      
      print('-', 0, 26);
      System.out.println("\nReset specific days of the room \n" +
                         "booking system. Here are the rooms \n" + 
                         "that you can select.\n");  //PRINT ROOM OPTIONS
      print('-', 0, 26);
      for ( int i = 1; i <= roomID; i++ ) {
         System.out.println("\t> Room " + i);
      }
      int option = 0;
      option = CHECK("\tCHOOSE ROOM 1-" + roomID + " : ", 0, roomID + 1);  //SELECT ROOM TO RESET
      print('-', 0, 26);
      
         //FIND DATES TO RESET
         
      startMonth1 = MONTH_CHECK(0);
      startDay1 = DAY_CHECK(startMonth1, 0);     //START
      print('-', 0, 26);
      START = date(startMonth1, startDay1);
      
      startMonth2 = MONTH_CHECK(2);
      startDay2 = DAY_CHECK(startMonth2, 2);     //END
      print('-', 0, 26);
      END = date(startMonth2, startDay2);
            
         //LOAD DATE FILE 
      
      Scanner scan = new Scanner ( new File ( placeDate + "Room" + option + "Date.txt" ));
      for ( int a = 0; a < 365; a++ ) {
         int number = scan.nextInt();
         roomDayInfo[a] = number;
      }
      
         //UPDATE DATE FILE 
      
      PrintStream out = new PrintStream ( new File ( placeDate + "Room" + option + "Date.txt" ));
      for ( int b = START; b < END; b++ ) {
         int number = 0;
         roomDayInfo[b] = number;
      }
      for ( int c = 0; c < 365; c++ ) {
         int number = roomDayInfo[c];
         out.print(number + " ");
      }
      
         //CONFIRM
      
      print('-', 0, 26);
      System.out.println("\tRoom number " + option + " has been reset.");
      ENTER();
      MENU();
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
                           "\n\t> CLEANING : " + staffCount[2] + 
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
         
            //WHY DOESNT THIS PART GET DISPLAYED!?!?!?
         
         print('-', 0, 26);
         System.out.println("\nDo you want to see other files?\n");
         int again = CHECK("\tCHOOSE YES: 1, NO: 2 : ", 0, 3);
         if(again == 1) {
            STAFF_INFO();
         } else if (again == 2) {
            MENU();
         }
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
               STAFF_TYPE_CREATE ( staffCle, 2, "Cleaning" );
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
      System.out.println("\nPlease insert Staff Member's telephone\nnumber.\n");
      print('-', 0, 26);  
      System.out.print("\tWRITE NUMBER : ");     
      stf.setTeleNum ( in.next() + in.nextLine() );
      print('-', 0, 26);
      System.out.println("\tNumber set to : " + stf.getTeleNum());
   }
   
   public static int date(int month, int date) {
      
      int day = 0;
      for(int i = 0; i < month - 1; i++) {
         day += MONTH[i];
      }
      day += date;
      return day;
   }
   
   public static void BUY_INFO(int day, double price) {
      
      double PRICE = 0; 
      double saved = 1.0;
      double procent = 0.05;
      double proAfter = 0.015;
      for(int i = 0; i < day; i++) {
         if ( day > 1 && day < 10 ) {
            procent -= 0.005;
            saved -= procent;
         } else if ( day == 1 ) {
         } else {
            saved -= proAfter;
         }
      }
      if (saved < 0.65) {
         saved = 0.65;
      }
      double originalPrice = day * price;
      PRICE = day * price * saved;
      double savings = originalPrice - PRICE;
      saved = (1 - saved) * 100; 
      print('-', 0, 26); 
      System.out.println("\nDays          : " + day +
                         "\nPrice pr day  : " + price);
      System.out.printf( "\nProcent saved : %.2f%% \nFinal price   : %.2f kr. " +
                         "\nMoney saved   : %.2f kr.\n\n",saved , PRICE, savings);
      
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
   
   public static int MONTH_CHECK (int pick) {
      
      int startMonth = 0;
      if ( pick == 2 ) {
         System.out.println("\nTo what month would you like to reset\n" +
                            "the hotelroom?\n");
      } else if ( pick == 1 ) {
         System.out.println("\nFrom what month would you like to book\n" +
                            "the hotelroom?\n"); 
      } else if ( pick == 0 ) {
         System.out.println("\nFrom what month would you like to reset\n" + 
                            "the hotelroom?\n");
      }
      startMonth = CHECK("\tCHOOSE FROM 1-12 : ", 0, 13);                     //MONTH
      print('-', 0, 26);
      return startMonth;

   }
   
   public static int DAY_CHECK (int startMonth, int pick) {
      
      int startDay = 0;
      if ( pick == 2 ) {
         System.out.println("\nTo what day would you like to reset \n" +
                            "the hotelroom?\n");
      } else if ( pick == 1 ) {
         System.out.println("\nWhat day would you like to book the\n" +
                            "hotelroom?\n"); 
      } else if ( pick == 0 ) {
         System.out.println("\nFrom what day would you like to reset\n" + 
                            "the hotelroom?\n");
      } 
      String question = "\tCHOOSE FROM 1-" + MONTH[startMonth - 1] + " : ";
      startDay = CHECK(question, 0, MONTH[startMonth - 1] + 1);               //DAY OF MONTH
      return startDay;
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
   
   public static void EXIT () {
     
      print('-', 0, 26); 
      System.out.println("\t        -  EXIT  -");
      print('-', 0, 26);
      System.exit ( 0 ); 
   }
}
