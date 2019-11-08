// Logging class
// Creates a log string based on input and returns it

import java.util.Date;
import java.text.SimpleDateFormat;

// For converting Exception into string
import java.io.StringWriter; 
import java.io.PrintWriter;

public class Log
{
   public enum Type
   {
      INFO,
      WARNING,
      ERROR
   }
   
   private String lineStart = ">>>";
   private Date date;
   
   private String logMessage;
   
   public Log(Exception e, Type logType)
   {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.printStackTrace(pw);
      this.logMessage = sw.toString();
   }
   
   public Log(String message, Type logType)
   {
      this.logMessage = message;
   }
   
   public String toString()
   {
      date = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      
      return lineStart + date + ": " + logMessage;
      //return "fuck you";
   }
}