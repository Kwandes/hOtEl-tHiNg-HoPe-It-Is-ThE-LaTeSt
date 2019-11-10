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
   private String date;
   
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
      this.logMessage = logType + ": " + message;
   }
   
   public String toString()
   {
      SimpleDateFormat formatter = new SimpleDateFormat("E yyyy/MM/dd HH:mm:SSS");
      date = formatter.format(new Date());
      
      return lineStart + date + ": " + logMessage;
      //return "fuck you";
   }
}