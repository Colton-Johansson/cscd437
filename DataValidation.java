
import java.io.*;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class DataValidation 
{
	
	static boolean isEmpty(String input)//method to check whether the user entered an empty string or not.
	{
		if(input.length()==0)
			return true;
		return false;
	}
	
	static boolean validateName(String input)
	{
		Pattern pattern = null;
		return printMessage(input,pattern.matches("^[a-zA-Z]{1,50}$",input)); //regex is looking for a char sequence between 1 and length, using capitals, lowercase, and nothing else, including any white space.
	}

	static boolean validatePassword(String input) 
	{
		Pattern pattern = null;
		return printMessage(input,Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s)[0-9a-zA-Z!@#$%^&*();:'\",.<>\\-_=+\\\\|`~\\?]{10,}$", input)); //Enter Password that is 10 or more characters long, contains AT LEAST 1 upper, AT LEAST one lower case, AT LEAST one number, and AT LEAST one punctuation

	}
	
   static boolean validateInputFilename(String input)
   {
      Pattern pattern = null;
      if(pattern.matches("^[a-zA-Z0-9_]{1,50}\\.txt$",input))//regex is looking for a char sequence between 1 and 50, using capitals, lowercase, numbers, and/or _'s followed by '.txt'
      {
         File inputFile = new File(input);
         
         if(inputFile.exists())
         {
            return printMessage(input,true); 
         }
         else
         {
            System.out.println("Input file must exist");
            return printMessage(input,false); 
         }
      }
      else
      {
         return printMessage(input,false); 
      }
   }
   
   static boolean validateOutputFilename(String input, String fileIn)
   {
      Pattern pattern = null;
      if(input.equals(fileIn))
         return printMessage(input, false);
      return printMessage(input,pattern.matches("^[a-zA-Z0-9_]{1,50}\\.txt$",input)); //regex is looking for a char sequence between 1 and 50, using capitals, lowercase, numbers, and/or _'s followed by '.txt'
   }
	
	static boolean validateInteger(String input) 
	{
		Pattern pattern = null;
      
      BigInteger inputInt = null;
      
      BigInteger maxInt = new BigInteger("2147483647");
      BigInteger minInt = new BigInteger("-2147483648");
		if(pattern.matches("^[-+]?[0-9]\\d*$", input))
      {
         inputInt = new BigInteger(input);
         if(inputInt.max(maxInt).equals(inputInt) || inputInt.min(minInt).equals(inputInt))
         {
            System.out.println("Please enter an integer between -2147483648 and 2147483647");
            return printMessage(input,false);
         }
         else
            return printMessage(input,true);
      }
      else
      {
         return printMessage(input,false);
      }
       
	}
	
	static boolean printMessage(String input, boolean result)
	{
		if(result)
		{
			System.out.println(input +" is valid. Press Enter.");
			return true;
		}
			
		else
		{
			System.out.println(input+" is not valid. Try again: ");
			return false;
		}
	}
}
