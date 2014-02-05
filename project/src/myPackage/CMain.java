package myPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Main class, our program starts here
//to control file input, save the data into our own data structure,
//print the results to console.

public class CMain
{
	public static void main(String [] args)
	{
		String in_fname = null;
		String file_path = "./new_sample.ged";
		System.out.println("Type file name to choose file:\n(default file is \"./new_sample.ged\", press \"Enter\" to continue)");
		
		try
		{
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
			in_fname = bufferReader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Input Error:");
			e.printStackTrace();
		}
		
		if(in_fname.isEmpty() == false)
		{
			file_path = "./" + in_fname;
		}
		
		BufferedReader br = null;
		try
		{
			String currentLine = null;
			br = new BufferedReader(new FileReader(file_path));
			
			while((currentLine = br.readLine()) != null)
			{
				//Read each line from the .ged file.
				currentLine = currentLine.trim();
				if (currentLine.isEmpty())
				{
					continue;
				}
				
				MyEachLine tmp_line = new MyEachLine(currentLine);
				//tmp_line.myPrint();
				System.out.println(currentLine);
			}
			
		}
		catch(IOException e)
		{
			System.out.println("Cannot read file: " + file_path);
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (br!=null)
				{
					br.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
}
