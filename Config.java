// Configuration file
// Reads a config file and assigns properties

import java.util.Properties;

public class Config
{
   private Properties configFile;
   public boolean isLoaded;
   
   public Config()
   {
      configFile = new java.util.Properties();
      
      loadConfig();
   }
   
   public void loadConfig()
   {
      try
      {
         configFile.load(this.getClass().getClassLoader().getResourceAsStream("config.cfg"));
         isLoaded = true;  
      }
      catch (Exception e)
      {
         isLoaded = false;
      }
   }
   
   public boolean isLoaded()
   {
      return this.isLoaded;
   }
}