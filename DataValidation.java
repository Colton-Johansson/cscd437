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
		return printMessage(input,Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s)[0-9a-zA-Z!@#$%^&*();:'\",.<>\\-_=+\\\\|`~\\?]*$", input)); //Enter Password that is 10 or more characters long, contains AT LEAST 1 upper, AT LEAST one lower case, AT LEAST one number, and AT LEAST one punctuation

	}
	
	
	static boolean validateInteger(String input) 
	{
		Pattern pattern = null;
		return printMessage(input,pattern.matches("^[-+]?[0-9]\\d*$", input));
       
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
