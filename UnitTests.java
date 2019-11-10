// Unit testing class
// Calls Different functions and expects certain result

public class UnitTests
{
   private Assert as;
   private boolean testPassed;
   private String testName;
   private boolean printDebugInfo;
   private int testCount;
   private int testsPassedCount;
   
   public void runTests(boolean debug)
   {
      // Debugging means printing logs created in MainFrame etc to console
      // Enable to see what exactly is going on, Disable for simple Test Passed/Failed
      this.printDebugInfo = debug;
      if(printDebugInfo) System.out.println("Debugging messages ON");
      else System.out.println("Debugging messages OFF");
      System.out.println("----------");

      as = new Assert();
      testCount = 0;
      testsPassedCount = 0;
      
      try
      {
         testCount++;
         testName = "validateLogin()";
         validateLoginTest();
         System.out.println(testName + " | " + "Passed");
         testsPassedCount++;
      }
      catch (TestException e) { System.out.println(testName + " | " + e.getMessage()); }
      catch (Exception e) { System.out.println(testName + " | Failed: " + e); }
      
      try
      {
         testCount++;
         testName = "load Config";
         loadConfig();
         System.out.println(">>>" + testName + " | " + "Passed");
         testsPassedCount++;
      }
      catch (TestException e) { System.out.println(testName + " | " + e.getMessage());}
      catch (Exception e) { System.out.println(testName + " | Failed: " + e); }
      
      System.out.println("----------");
      System.out.println("Unit testing finished. Passed " + testsPassedCount + "/" + testCount + " tests\n\n");
   }
   
   ////////// unit tests //////////
   public void templateTes() throws TestException
   {
      // Arrange
      // Act
      // Assert
      as.assertEquals(null, null);
   }
   
   public void validateLoginTest() throws TestException
   {
      // Arrange
      MainFrame mf = new MainFrame(this.printDebugInfo);
      mf.init();
      String phoneNumber = "+69 420";
      String password = "yeet";
      User testUser = new User();
      mf.addUser(testUser);
      // Act
      User user = mf.validateLogin(phoneNumber, password);
      //Assert
      as.assertEquals(user.getUserID(), testUser.getUserID());
   }
   
   public void loadConfig() throws TestException
   {
      // Arrange
      Config cfg = new Config();
      // Act
      // Assert
      as.assertEquals(Boolean.toString(cfg.isLoaded()), "true");
   }
   
   
   ////////// Test handling classes //////////
   public class Assert extends TestException
   {
      public void assertEquals(String actual, String expected) throws TestException
      {
         if(!actual.equals(expected)) throw new TestException("Failed - Expected: " + expected + " | actual: " + actual);
      }
      
      public void assertNotNull(String actual, String expected) throws TestException
      {
         if(actual == null) throw new TestException("Failed - Expected: " + expected + " | actual: " + actual);
      }
   }
   
   public class TestException extends Exception
   {
      public TestException() { super(); }
      public TestException(String message) { super(message); }
      public TestException(String message, Throwable cause) { super(message, cause); }
      public TestException(Throwable cause) { super(cause); }
   }
}