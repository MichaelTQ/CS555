package testCases;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import myPackage.FamilyList;
import myPackage.FamilyNode;
import myPackage.IndividualList;
import myPackage.IndividualNode;
import myPackage.MyEachLine;

import org.junit.Test;

public class FileReadingTest {
	private static ArrayList<MyEachLine> arr_lines = new ArrayList<MyEachLine>();
	private static FamilyList fam_list = new FamilyList();
	private static IndividualList indi_list = new IndividualList();
	String file_path = "./new_sample.ged";
	
	@Test
	public void test()
	{
		check_file(file_path);
		assertEquals(fam_list.getSize(), 3);
		assertEquals(indi_list.getSize(), 10);
	}
	
	public FamilyList getFList()
	{
		return FileReadingTest.fam_list;
	}
	
	public IndividualList getIList()
	{
		return FileReadingTest.indi_list;
	}
	
	public void check_file(String file_path)
	{
		BufferedReader br = null;
		try
		{
			
			String currentLine;
			br = new BufferedReader(new FileReader(file_path));
			int i_line_num = 0;
			while((currentLine = br.readLine()) != null)
			{
				currentLine = currentLine.trim();
				if (currentLine.isEmpty())
				{
					continue;
				}
				MyEachLine tmp_line = new MyEachLine(currentLine);
				if (tmp_line.checkValid())
				{
					arr_lines.add(tmp_line);
				}
				else
				{
					System.out.println("Line: #" + new Integer(i_line_num++).toString() + "is deleted.");
				}
			}
			
			convertLines();
			for (int i = 0; i < indi_list.getSize(); i++)
			{
				indi_list.get(i).setFamilyNodes(fam_list);
			}
			for (int i = 0; i < fam_list.getSize(); i++)
			{
				fam_list.get(i).setIndiNodes(indi_list);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(br != null)
				{
					br.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		/****************************************************/
		/**************Above is the first test***************/
		/****************************************************/
	}
	
	private static void convertLines()
	{
		ArrayList<MyEachLine> tmp_lines = new ArrayList<MyEachLine>();
		
		int flag = -1;// 1 means "individual", 2 means "family"
		
		for (int i = 0;  i < arr_lines.size(); i++)
		{
			if (arr_lines.get(i).getLevel() == 0)
			{
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
}
