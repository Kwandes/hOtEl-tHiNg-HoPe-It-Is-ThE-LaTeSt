   // PLACEHOLDER CRISTI

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class TestMain {

   private static final String FILEPATH = "C:/Users/crist/OneDrive/Documents/GitKraken/hOtEl-tHiNg-HoPe-It-Is-ThE-LaTeSt/Logs";
   private static int[] monthList = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static String[] monthName = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };
   
   public static void main ( String[] args ) 
                  throws FileNotFoundException, IOException {
      
      FileManagement fm = new FileManagement ( FILEPATH );
      
         // Room Testing 
         
      // Setting required values for room class to work.
      Room.setBasePrice(100);
      Room.setPricePerBed(200);
      Room.setFloorMultiplier(1.05);
         
      // Loading all rooms.
      ArrayList<Room> rArray = fm.loadRooms();
      
      // Creating room.
      Room r = new Room ( 306, 1 );
      int[] rCalendar = new int[365];
      rCalendar [0] = 10;
      rCalendar [1] = 2;
      rCalendar [2] = 2;
      r.setCalendar ( rCalendar );
      
      rArray.add ( r );
      
      // Saving rooms.
      fm.saveRooms ( rArray );
   }
   
      // Calendar Methods & Date Formatting
   
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
}     
