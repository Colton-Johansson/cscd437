import java.math.BigInteger;
import java.util.regex.Pattern;

public class DataValidation 
{
	static boolean validateName(String input)
	{
		Pattern pattern = null;
		if(pattern.matches("^[a-zA-Z]{1,50}$", input)) //regex is looking for a char sequence between 1 and length, using capitals, lowercase, and nothing else, including any white space.
		{
			System.out.println(input+" is valid.");
			return true;
		}        				
        else 
        {
            System.out.println(input+ " is not valid.");
            return false;
        }
	}

	static boolean isEmpty(String input)//method to check whether the user entered an empty string or not.
	{
		if(input.length()==0)
			return true;
		return false;
	}

	static boolean validatePassword(String input) 
	{
		Pattern pattern = null;
		if(Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s)[0-9a-zA-Z!@#$%^&*();:'\",.<>\\-_=+\\\\|`~\\?]*$", input)) //Enter Password that is 10 or more characters long, contains AT LEAST 1 upper, AT LEAST one lower case, AT LEAST one number, and AT LEAST one punctuation
		{
			System.out.println(input+" is valid.");
			return true;
		}       	
        else
        {
        	System.out.println(input+ " is not valid.");
            return false;
        }
	}
	
	static boolean validateInteger(String input) 
	{
		Pattern pattern = null;
		if(pattern.matches("^[-+]?[0-9]\\d*(\\.\\d+)?$", input))
		{
			System.out.println(input+" is valid.");
			return true;
		}       	
        else
        {
        	System.out.println(input+ " is not valid.");
            return false;
        }
	}
	
}
