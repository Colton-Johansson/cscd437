
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
		BigInteger val1 = new BigInteger(getVal1(kb));
		BigInteger val2 = new BigInteger(getVal2(kb));
		File fileIn = getInputFile(kb);
		File fileOut = getOutputFile(kb, fileIn);
		String password = getPassword(kb);
		//boolean wroteToFile = openOutputFile();
		
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println("Integer 1: "  + val1 + "\nInteger 2: " + val2);
      System.out.println("Integer 1 + Integer 2 = " + val1.add(val2));
      System.out.println("Integer 1 x Integer 2 = " + val1.multiply(val2));
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
   
   private static File getInputFile(Scanner kb) 
	{
		System.out.println("Enter the Input filename: ");
		
		String val1 = null;
		val1 = kb.nextLine();
		while(!DataValidation.validateInputFilename(val1))
		{
			System.out.println("Please enter the Input filename: ");
			val1 = kb.nextLine();
		}		
      
		kb.nextLine();
		return new File(val1);
	}
   
	private static File getOutputFile(Scanner kb, File inputFile) 
	{
		System.out.println("Enter the Output filename: ");
		
		String val1 = null;
		val1 = kb.nextLine();
		while(!DataValidation.validateOutputFilename(val1, inputFile.toString()))
		{
			System.out.println("Please enter the Output filename: ");
			val1 = kb.nextLine();
		}		
		kb.nextLine();
		return new File(val1);
	}

	private static String getLastName(Scanner kb) 
	{
		String lastName = "";
		String last = "Last";
		return getName(kb,last);
	}

	private static String getFirstName(Scanner kb) 
	{
		String firstName = "";
		String first = "First";
		return getName(kb,first);
	}
	
	private static String getName(Scanner kb, String whichName)
	{
		System.out.println("Enter " + whichName + " Name: ");		
		String returnName = "";
		returnName = kb.nextLine();
		while(!DataValidation.validateName(returnName))// we don't want it to be empty, and we do want it to pass our regex
		{
			System.out.println("Please enter a " + whichName+" Name: ");
			returnName = kb.nextLine();
		}		
		kb.nextLine();
		return returnName;
	}
}
