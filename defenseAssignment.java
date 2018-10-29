
import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Pattern;
public class defenseAssignment
{
	public static void main(String[]args)
	{
		Scanner kb = new Scanner(System.in);//might as well pass around the same scanner, remembering to flush the buffer when needed.

		String firstName = getFirstName(kb);
		String lastName = getLastName(kb);
		String val1 = getVal1(kb);
		String val2 = getVal2(kb);
		//File fileIn = getInputFile(kb);
		//File fileOut = getOutputFile(kb);
		String password = getPassword(kb);
		//boolean wroteToFile = openOutputFile();
		
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println("Integer 1: "  + val1 + "\nInteger 2: " + val2);
		System.out.println("Password: " + password);
		
	}

	private static boolean openOutputFile() 
	{
		// TODO Auto-generated method stub
		return false;
	}

	private static String getPassword(Scanner kb) 
	{
		System.out.println("Enter Password that is 10 or more characters long, contains AT LEAST 1 upper, AT LEAST one lower case, AT LEAST one number, and AT LEAST one punctuation: ");
		
		String password = "";
		password = kb.nextLine();
		while(!DataValidation.validatePassword(password))
		{
			System.out.println("Please enter a Password that is 10 or more characters long, contains AT LEAST 1 upper, AT LEAST one lower case, AT LEAST one number, and AT LEAST one punctuation: ");
			password = kb.nextLine();
		}		
		kb.nextLine();
		return password;
	}
	

	private static String getVal2(Scanner kb) 
	{
		
		System.out.println("Enter another Integer: ");
		
		String val2 = null;
		val2 = kb.nextLine();
		while(!DataValidation.validateInteger(val2))
		{
			System.out.println("Please enter another Integer: ");
			val2 = kb.nextLine();
		}		
		kb.nextLine();
		return val2;
	}

	private static String getVal1(Scanner kb) 
	{
		
		System.out.println("Enter an Integer: ");
		
		String val1 = null;
		val1 = kb.nextLine();
		while(!DataValidation.validateInteger(val1))
		{
			System.out.println("Please enter an Integer: ");
			val1 = kb.nextLine();
		}		
		kb.nextLine();
		return val1;
	}

	private static File getOutputFile(Scanner kb) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	private static File getInputFile(Scanner kb) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	private static String getLastName(Scanner kb) 
	{
		System.out.println("Enter Last Name: ");
		
		String lastName = "";
		lastName = kb.nextLine();
		while(!DataValidation.validateName(lastName))// we don't want it to be empty, and we do want it to pass our regex
		{
			System.out.println("Please enter a Last Name: ");
			lastName = kb.nextLine();
		}		
		kb.nextLine();
		return lastName;
	}

	private static String getFirstName(Scanner kb) 
	{
		System.out.println("Enter First Name: ");
		
		String firstName = "";
		firstName = kb.nextLine();
		while(!DataValidation.validateName(firstName))// we don't want it to be empty, and we do want it to pass our regex
		{
			System.out.println("Please enter a First Name: ");
			firstName = kb.nextLine();
		}		
		kb.nextLine();
		return firstName;
	}
	
	

}
