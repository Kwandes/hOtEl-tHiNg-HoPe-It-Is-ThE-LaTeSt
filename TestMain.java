import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class TestMain {

   private static final String FILEPATH = "C:/Users/crist/OneDrive/Documents/KEA Hotel Project/Logs";
   
   public static void main ( String[] args ) 
                  throws FileNotFoundException, IOException {
      
      FileManagement fm = new FileManagement ( FILEPATH );
      
      ArrayList<Booking> array = fm.loadBookings( false ); 
      Booking r = new Booking( 10, "G12229", 60, 70, 420, false );
      array.add ( r );
      fm.saveBookings ( array, false ); 
      //fm.appendToFile ("log2");
      
      
   }
}
