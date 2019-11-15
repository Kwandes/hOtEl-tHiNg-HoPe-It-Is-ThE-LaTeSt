   // PLACEHOLDER CRISTI
   // Jan> Pliz remove. it doesn't even work

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class TestMain {
   
      // File Path required for FileManagement.
   private static final String FILEPATH = "C:/Users/crist/OneDrive/Documents/GitKraken/hOtEl-tHiNg-HoPe-It-Is-ThE-LaTeSt/Logs";
   
      // File Manager
   private static FileManagement fm;
   
      // Arrays used by the date formatting methods.
   private static int[] monthList = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static String[] monthName = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };
   
      // ArrayLists
   private static ArrayList<Booking> bookingList = new ArrayList<Booking>();
   private static ArrayList<Room> roomList = new ArrayList<Room>();
   
   
      // MAIN METHOD
   public static void main ( String[] args ) 
                  throws FileNotFoundException, IOException {
      
      fm = new FileManagement ( FILEPATH );
      
      bookingList = fm.loadBookings( false );
      roomList = fm.loadRooms();
      
      
         // Room Testing    
      // Setting required values for room class to work.
      Room.setBasePrice(100);
      Room.setPricePerBed(200);
      Room.setFloorMultiplier(1.05);
         
      //resetCalendar ( 511 );
      
      /*
         // Booking Testing
      int startDate = 1;
      int endDate = 5;
      int daysExtend = 3;
      boolean hasInternet = true;
      int bookingCount = 1;
     
         // Booking.
      System.out.println ( "Is bookable : " + isBookable ( 511, startDate, endDate ) );
      
      if ( isBookable ( 511, startDate, endDate ) )
      {
         createBooking ( bookingCount, "G.1.001", 511, startDate, endDate, hasInternet );
      }
      
         // Extending.
      int bookingPos = findBooking ( bookingCount );
      int roomID = bookingList.get(bookingPos).getRoomID();
      int roomPos = findRoom ( roomID );
      
      int[] calendar = roomList.get(roomPos).getCalendar();
      
      System.out.println ( "Is Extendable : " + bookingList.get(bookingPos).isExtendable ( daysExtend, calendar ) );
      
      if ( bookingList.get(bookingPos).isExtendable ( daysExtend, calendar ) ) 
      {
         extendBooking ( bookingCount, roomID, daysExtend );
      }
      */
      
      fm.saveBookings(bookingList, false);
      fm.saveRooms(roomList);
      
   }
   
      // Booking Related Methods
      
   // Creates a booking.
   public static void createBooking ( int bookingCount, String userID, int roomID,
                                      int startDate, int endDate, boolean hasInternet ) // May have too many arguments (?)
                     throws FileNotFoundException
   {
      int roomPos = findRoom ( roomID ); 
      int roomPrice = roomList.get(roomPos).getPrice();
      int[] calendar = roomList.get(roomPos).getCalendar();
      
      for ( int i  = startDate - 1; i < endDate; i ++ )
      {
         calendar[i] = bookingCount;
      }
      
      roomList.get(roomPos).setCalendar( calendar );
      
      Booking booking = new Booking ( bookingCount, roomID, userID, startDate, endDate, roomPrice, hasInternet );
      
      bookingList.add( booking ); 
      
      fm.saveRooms(roomList);
      fm.saveBookings(bookingList, false);
   }
   
   // Extends a booking.
   public static void extendBooking ( int bookingCount, int roomID, int days )
                     throws FileNotFoundException   
   {
      int roomPos = findRoom ( roomID );
      int bookingPos = findBooking ( bookingCount );
      
      int[]calendar = roomList.get(roomPos).getCalendar();
      int startDate = bookingList.get(bookingPos).getEndDate();
      int endDate = startDate + days;
      
      for ( int i = startDate; i < endDate; i ++ )
      {
         calendar[i] = bookingCount;
      } 
      
      bookingList.get(bookingPos).setEndDate(endDate);
      bookingList.get(bookingPos).setIsExtended(true);
      roomList.get(roomPos).setCalendar(calendar); 
      
      fm.saveRooms(roomList);
      fm.saveBookings(bookingList, false);
   }
      
   // Checks if a room is available for a given period of time.  
   public static boolean isBookable ( int roomID, int startDate, int endDate ) 
   {
      int roomPos = findRoom ( roomID );
      int[] calendar = roomList.get(roomPos).getCalendar();
      for ( int i = startDate - 1; i < endDate; i ++ ) {
         if ( calendar[i] != 0 )
         {
            return false;
         }
      }
      return true;
   }
   
         // Calendar Methods & Date Formatting
   
   // Resets the calendar of a given room.
   public static void resetCalendar ( int roomID ) 
                     throws FileNotFoundException
   {
      int roomPos = findRoom ( roomID );
      int[] calendar = new int[365];
      roomList.get(roomPos).setCalendar(calendar);
      fm.saveRooms(roomList);
   }
   
   public static int dateNumber ( int month, int day )
   {
      int days = 0;
      for ( int i = 0; i < month - 1; i ++ ) 
      {
         days += monthList[i];
      }
      days += day;
      return days;
   } 
   
   public static String dateFormat ( int days ) 
   {
      int sum = monthList[0];
      int monthNumber = 0;
      int number = 0;
      for ( int i = 1; i < 12; i ++ )
      {
         number = monthList[i];
         if ( days > sum )
         {
            sum += number;
            monthNumber ++;
         }
         else 
         {
            break;  
         }
      }
      number = monthList[monthNumber];
      int dayNumber;
      if ( monthNumber == 0 )
      {
         dayNumber = days;
      }
      else 
      {
         dayNumber = number - (sum - days);
      }
      return dayNumber + "/" + monthName[monthNumber]; 
   }
   
      // Helper Methods - perhaps in information class ? 
      
   // Finds a room by ID.
   public static int findRoom ( int roomID ) 
   {
      int roomPos = -1;
      for ( int i = 0; i < roomList.size(); i ++ )
      {
         if ( roomList.get(i).getRoomID() == roomID )
         {
            roomPos = i;
            break;
         }
      }
      return roomPos;
   }
   // Find a booking by ID/Count
   public static int findBooking ( int bookingID ) 
   {
      int bookingPos = -1;
      for ( int i = 0; i < bookingList.size(); i ++ )
      {
         if ( bookingList.get(i).getBookingID() == bookingID )
         {
            bookingPos = i;
            break;
         }
      }
      return bookingPos;
   }

}     
