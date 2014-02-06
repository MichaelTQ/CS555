package myPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//Main class, our program starts here
//to control file input, save the data into our own data structure,
//print the results to console.

public class CMain
{
	private static ArrayList<MyEachLine> arr_lines = new ArrayList<MyEachLine>();
	private static FamilyList fam_list = new FamilyList();
	private static IndividualList indi_list = new IndividualList();
	
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
			
			int i_line_num = 0;
			while((currentLine = br.readLine()) != null)
			{
				//Read each line from the .ged file.
				currentLine = currentLine.trim();
				if (currentLine.isEmpty())
				{
					continue;
				}
				MyEachLine tmp_line = new MyEachLine(currentLine);
				if (tmp_line.checkValid())
				{
					arr_lines.add(tmp_line);
					//System.out.print(++i_line_num);
					//System.out.print("===> ");
					//System.out.println(currentLine);
				}
				else
				{
					System.out.println("Line: #" + new Integer(i_line_num).toString() + "is deleted.");
				}
			}
			
			convertLines();// to convert lines and add them into FamilyList and IndividualList
			
			myPrintLists();
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
	
	// make conversion from the list of MyEachLine to FamilyList and IndividualList
	private static void convertLines()
	{
		ArrayList<MyEachLine> tmp_lines = new ArrayList<MyEachLine>();
		
		int flag = -1;// 1 means "individual", 2 means "family"
		
		for (int i = 0;  i < arr_lines.size(); i++)
		{
			if (arr_lines.get(i).getLevel() == 0)
			{
				if (arr_lines.get(i).getTag().equals("TRLR"))
				{
					break;
				}
				
				if (tmp_lines.size() != 0 && flag == 1)
				{
					IndividualNode tmp_indi_node = new IndividualNode(tmp_lines);
					indi_list.add(tmp_indi_node);
					tmp_lines = new ArrayList<MyEachLine>();
				}
				else if(tmp_lines.size() != 0 && flag == 2)
				{
					FamilyNode tmp_fam_node = new FamilyNode(tmp_lines);
					fam_list.add(tmp_fam_node);
					tmp_lines = new ArrayList<MyEachLine>();
				}
				
				if (arr_lines.get(i).getTag().equals("INDI"))
				{
					flag = 1;
					tmp_lines.add(arr_lines.get(i));
				}
				else if (arr_lines.get(i).getTag().equals("FAM"))
				{
					flag = 2;
					tmp_lines.add(arr_lines.get(i));
				}
			}
			else if (flag != -1)
			{
				tmp_lines.add(arr_lines.get(i));
			}
		}
	}
	
	private static void myPrintLists()
	{
		System.out.println("==========");
		for (int i = 0; i < indi_list.getSize(); i++)
		{
			IndividualNode tmp_node = indi_list.get(i);
			System.out.println("ID: " + tmp_node.getID());
			System.out.println("Name: " + tmp_node.getName());
			System.out.println("Sex: " + tmp_node.getSex());
			System.out.println("BIRT: " + tmp_node.getBIRT().getDateStr());
			if (tmp_node.getDEAT() != null)
			{
				System.out.println("DEAT: " + tmp_node.getDEAT().getDateStr());
			}
			else
			{
				System.out.println("DEAT: null");
			}
			
			for (int j = 0; j < tmp_node.getFamcStrArr().size(); j++)
			{
				System.out.println("FAMC: " + tmp_node.getFamcStrArr().get(j));
			}
			
			for (int j = 0; j < tmp_node.getFamsStrArr().size(); j++)
			{
				System.out.println("FAMS: " + tmp_node.getFamsStrArr().get(j));
			}
		}
		System.out.println("==========");
		for (int i = 0; i < fam_list.getSize(); i++)
		{
			FamilyNode tmp_node = fam_list.get(i);
			System.out.println("ID: " + tmp_node.getID());
			System.out.println("MARR: " + tmp_node.getMARR().getDateStr());
			System.out.println("HUSB: " + tmp_node.getHusbStr());
			System.out.println("WIFE: " + tmp_node.getWifeStr());
			if(tmp_node.getDIV() != null)
			{
				System.out.println("DIV: " + tmp_node.getDIV().getDateStr());
			}
			else
			{
				System.out.println("DIV: null");
			}
		}
	}
}
