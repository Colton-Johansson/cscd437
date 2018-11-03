//Brandtly Strobeck, Colton Johansan, Joe Carlson
//Secure Coding Fall, Tom Capaul
//This program will attempt to be uncrashable from the command line using regex's, and other java implemented data screening methods
//It will ask the user for various inputs such as strings, ints, and files.
//NOTE: To set up the input file correctly, place it in the same folder as /src is so the program can find it. NOT IN /SRC, but the so they share a folder
//For security purposes, we wanted to limit which files it has access to read, that is why it is set up this way.

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

		String firstName = getFirstName(kb);
		String lastName = getLastName(kb);
		BigInteger val1 = getVal1(kb);
		BigInteger val2 = getVal2(kb);
		File fileIn = getInputFile(kb);
		File fileOut = getOutputFile(kb,fileIn);
		boolean savedPassword = setPassword(kb);
		boolean validPassword = checkPasswordHash(kb);
		openOutputFile(fileOut, firstName, lastName, val1, val2, fileIn);
		
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println("Integer 1: "  + val1 + "\nInteger 2: " + val2);
		System.out.println("Password Saved? " + savedPassword);
		System.out.println(("Read input file? " + fileIn));
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
			
			System.out.println("Hash and Salt To Check: " + hashToCheck);
			System.out.println("Salt stored in file:" + fileSalt);
			System.out.println("Hash generated from retyped password: "+passwordToCheck.hashCode());
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
			printError(e);
			fScan.close();
			return false;
		}
		
	}

	private static File getOutputFile(Scanner kb, File fileIn)
	{
		System.out.println("Enter an output file name followed by .txt: ");
		String fileName = "";
		fileName = kb.nextLine();
		while(!DataValidation.validateOutputFilename(fileName) || fileName.equals(fileIn.toString()))//to keep things from getting messy, we don't want the input and output files to be the ssame.
		{
			System.out.println("Enter a valid output file name followed by .txt. Input and Output files cannot be the same: ");
			fileName = kb.nextLine();
		}
		System.out.println(fileName +" is valid. Press Enter.");
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			printError(e);
		}
		kb.nextLine();
		return file;
	}

	private static File getInputFile(Scanner kb) 
	{
		System.out.println("Enter an input file name followed by .txt: ");
		String fileName = "";
		fileName = kb.nextLine();
				
		while(!DataValidation.validateInputFilename(fileName))//keep looping till user enters a 'valid' input file.
		{
			System.out.println("Please enter a valid input file name followed by .txt: ");
			fileName = kb.nextLine();
		}
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			printError(e);
		}
		kb.nextLine();
		return file;
	}

	private static void openOutputFile(File fileOut, String firstName, String lastName, BigInteger val1, BigInteger val2, File fileIn) 
	{
		try
		{
			BigInteger sum = val1.add(val2);
			BigInteger product = val1.multiply(val2);
			Scanner fScan = new Scanner(fileIn);
			FileOutputStream fos = new FileOutputStream(fileOut,false);//just keep appending to the file if they use the same one.
			OutputStreamWriter osw = new OutputStreamWriter(fos,"ASCII");
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(bw,true);
			pw.write(firstName + " " + lastName);
			bw.newLine();
			pw.write(sum.toString());
			bw.newLine();
			pw.write(product.toString());
			bw.newLine();
			pw.write("==Writing contents of input file==");
			bw.newLine();
			while(fScan.hasNextLine())
			{
				pw.write(fScan.nextLine());
				bw.newLine();
			}
			pw.close();
			bw.close();
		}
		catch(IOException e)
		{
			printError(e);
		}
	}
	

	private static boolean savePasswordToFile(String hash, String saltString)
	{
		try
		{
			File file = new File("passwordFile.txt");
			file.createNewFile();//if file already exists it will do nothing.
			FileOutputStream fos = new FileOutputStream(file,false);//don't append to file, but overwrite the last password saved when program is ran again is why the false is there.
			OutputStreamWriter osw = new OutputStreamWriter(fos,"ASCII");// ASCII to specify the charset to be used, so we won't expect any weird chars
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
			printError(e);
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
		
	private static BigInteger getVal2(Scanner kb) 
	{		
		System.out.println("Enter another Integer: ");
		
		String val2 = null;
		val2 = kb.nextLine();
		while(!DataValidation.validateInteger(val2))
		{
			System.out.println("Please enter another Integer: ");
			val2 = kb.nextLine();
		}
		BigInteger val = new BigInteger(val2);
		kb.nextLine();
		return val;
	}

	private static BigInteger getVal1(Scanner kb) 
	{		
		System.out.println("Enter an Integer: ");
		
		String val1 = null;
		val1 = kb.nextLine();
		while(!DataValidation.validateInteger(val1))
		{
			System.out.println("Please enter an Integer: ");
			val1 = kb.nextLine();
		}
		BigInteger val = new BigInteger(val1);
		kb.nextLine();		
		return val;
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
	
	private static void printError(IOException e)
	{
		try
		{
			File file = new File("errorLog.txt");
			file.createNewFile();//if file already exists it will do nothing.
			FileOutputStream fos = new FileOutputStream(file,true);//don't append to file, but overwrite the last password saved when program is ran again is why the false is there.
			OutputStreamWriter osw = new OutputStreamWriter(fos,"ASCII");// ASCII to specify the charset to be used, so we won't expect any weird chars
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(bw,true);//true here is saying yes to autoFlush, which will flush the output buffer.
			pw.write(e.toString());
			bw.newLine();
			pw.close();
			bw.close();			
		}
		catch(IOException e1)
		{
			e.printStackTrace();//if it messes up writing to error log then we're really in for it
		}
	}
}
