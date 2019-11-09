// Unit testing class
// Calls Different functions and expects certain result

public class UnitTests
{
   private Assert as;
   private boolean testPassed;
   private String testName;
   
   public void runTests(boolean debug)
   {
      if(debug) System.out.println("Debugging messages ON");
      as = new Assert();
      
      try
      {
         testName = "validateLogin";
         validateLoginTest();
         System.out.println(testName + " | " + "Passed");
      }
      catch (TestException e) { System.out.println(testName + " | " + e.getMessage()); }
      catch (Exception e) { System.out.println(testName + " | Failed: " + e); }
   }
   
   ////////// unit tests //////////
   public void validateLoginTest() throws TestException
   {
      // Arrange
      MainFrame mf = new MainFrame();
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