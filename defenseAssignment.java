import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.security.SecureRandom;
public class defenseAssignment
{
	public static void main(String[]args)
	{
		Scanner kb = new Scanner(System.in);//might as well pass around the same scanner, remembering to flush the buffer when needed.

		//String firstName = getFirstName(kb);
		//String lastName = getLastName(kb);
		//String val1 = getVal1(kb);
		//String val2 = getVal2(kb);
		//boolean fileIn = getInputFile(kb);
		//File fileOut = getOutputFile(kb);
		boolean savedPassword = setPassword(kb);// be sure to delete the reference in password ASAP after saving to file.
		boolean validPassword = checkPasswordHash(kb);
		//boolean wroteToFile = openOutputFile();
		
		//System.out.println("Name: " + firstName + " " + lastName);
		//System.out.println("Integer 1: "  + val1 + "\nInteger 2: " + val2);
		System.out.println("Password Saved? " + savedPassword);
		//System.out.println(("Read input file? " + fileIn));
		System.out.println("Valid Password? " + validPassword);
		kb.close();//be sure to close off scanner when program is done.		
	}
	
	private static boolean checkPasswordHash(Scanner kb) 
	{
		Scanner fScan = null;
		String passwordToCheck = "";
		System.out.println("Retype password: ");
		passwordToCheck = kb.nextLine();
		String hashToCheck="";
		try
		{
			String fileHashedPassword=  "";
			String fileSalt = "";
			File file = new File("passwordFile.txt");
			fScan = new Scanner(file);
			
			
			//doing it this way because Tom said user won't mess with open files while program is running. This way we can guarantee first line is hash and salt, second line is salt
			if(fScan.hasNextLine())
			{
				fileHashedPassword = fScan.nextLine();
				System.out.println(fileHashedPassword);
			}
			if(fScan.hasNextLine())
			{
				fileSalt = fScan.nextLine();
				System.out.println(fileSalt);
			}
			
			hashToCheck = passwordToCheck.hashCode() + fileSalt;//Concatenate new password with old salt
			
			System.out.println("hashToCheck: " + hashToCheck);
			System.out.println("fileSalt:" + fileSalt);
			System.out.println(passwordToCheck.hashCode());
			if(hashToCheck.equals(fileHashedPassword))
			{
				fScan.close();
				return true;
			}					
			else
			{
				fScan.close();
				return false;
			}
				
		}
		catch(IOException e)
		{
			e.printStackTrace();
			fScan.close();
			return false;
		}
		
	}

	private static File getOutputFile(Scanner kb)
	{
		// TODO Auto-generated method stub
		return null;
	}

	private static boolean getInputFile(Scanner kb) 
	{
		System.out.println("Enter an input file name followed by .txt: ");
		String fileName = "";
		fileName = kb.nextLine();
		Scanner fScan = null;//using a new scanner dedicated to reading the file.
		
		while(!DataValidation.validateInputFilename(fileName))//keep looping till user enters a 'valid' input file.
		{
			System.out.println("Please enter a valid input file name followed by .txt: ");
			fileName = kb.nextLine();
		}		
		try
		{
			File file = new File(fileName);
			fScan = new Scanner(file);
			while(fScan.hasNextLine())
			{
				String line = fScan.nextLine();
				System.out.println(line);				
			}
			return true;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		
		finally
		{
			fScan.close();//close off scanner when done.			
		}
	}

	private static boolean openOutputFile() 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	private static boolean savePasswordToFile(String hash, String saltString)
	{
		try
		{
			File file = new File("passwordFile.txt");
			file.createNewFile();//if file already exists it will do nothing.
			FileOutputStream fos = new FileOutputStream(file,false);//don't append to file, but overwrite the last password saved when program is ran again is why the false is there.
			OutputStreamWriter osw = new OutputStreamWriter(fos,"ASCII");// UTF-8 to specify the charset to be used, so we won't expect any weird chars
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(bw,true);//true here is saying yes to autoFlush, which will flush the output buffer.
			pw.write(hash);
			bw.newLine();
			pw.write(saltString);
			System.out.println("hash: " + hash + "\nSalt: " + saltString);
			pw.close();
			bw.close();
			return true;			
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	private static boolean setPassword(Scanner kb) 
	{
		System.out.println("Enter Password that is 10 or more characters long, contains AT LEAST 1 upper, AT LEAST one lower case, AT LEAST one number, and AT LEAST one punctuation: ");
		
		String password = "";
		password = kb.nextLine();
		while(!DataValidation.validatePassword(password))
		{
			System.out.println("Please enter a Password that is 10 or more characters long, contains AT LEAST 1 upper, AT LEAST one lower case, AT LEAST one number, and AT LEAST one punctuation: ");
			password = kb.nextLine();
		}
		//get plain text password
		//generate salt, save salt
		//append salt+password
		//send salt+password through hashFunction
		//save hash
		SecureRandom rng = new SecureRandom();
		Integer salt = rng.nextInt(1000);//0-999
		if(salt < 0)
		{
			salt = salt*(-1);//help ensure our salt is positive.
		}
		String saltString = salt.toString();
		String hash =password.hashCode() + saltString ;//get an acceptable password, then immedately send it through a hash function adding the salt to it, 
		password = "";//and do our best to delete any reference to the plain text password for security's sake.
		salt = null;		
		boolean wasWritten = savePasswordToFile(hash,saltString); 
		saltString = "";
		kb.nextLine();
		return wasWritten;
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
