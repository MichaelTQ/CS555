package myPackage;

import java.util.ArrayList;

public class IndividualNode
{
	private String id;
	private String name;
	private String sex;
	private MyDate birt;
	private MyDate deat;
	private ArrayList<String> arr_str_famc = new ArrayList<String>();//child in family
	private ArrayList<String> arr_str_fams = new ArrayList<String>();//spouse in family
	
	private ArrayList<FamilyNode> arr_node_famc = new ArrayList<FamilyNode>();
	private ArrayList<FamilyNode> arr_node_fams = new ArrayList<FamilyNode>();
	
	private ArrayList<String> arr_notes = new ArrayList<String>();
	
	public IndividualNode(ArrayList<MyEachLine> arr_lines)
	{
		String flag = null;
		for (int i = 0; i < arr_lines.size(); i++)
		{
			if (arr_lines.get(i).getTag().toUpperCase().equals("INDI"))
			{
				this.id = arr_lines.get(i).getArg();
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("NAME"))
			{
				this.name = arr_lines.get(i).getArg();
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("SEX"))
			{
				this.sex = arr_lines.get(i).getArg();
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("BIRT"))
			{
				flag = "BIRT";
				continue;
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("DEAT"))
			{
				flag = "DEAT";
				continue;
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("FAMC"))
			{
				this.arr_str_famc.add(arr_lines.get(i).getArg());
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("FAMS"))
			{
				this.arr_str_fams.add(arr_lines.get(i).getArg());
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("NOTE"))
			{
				this.arr_notes.add(arr_lines.get(i).getArg());
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("DATE"))
			{
				if (flag == "BIRT")
				{
					this.birt = new MyDate(arr_lines.get(i).getArg());
				}
				else if (flag == "DEAT")
				{
					this.birt = new MyDate(arr_lines.get(i).getArg());
				}
			}
		}
	}
	
	public void setFamilyNodes(ArrayList<FamilyNode> fam_list)
	{
		this.arr_node_famc = new ArrayList<FamilyNode>();
		this.arr_node_fams = new ArrayList<FamilyNode>();
		
		for (int i = 0; i < this.arr_str_famc.size(); i++)
		{
			for (int j = 0; j < fam_list.size(); j++)
			{
				if (fam_list.get(j).getID().equals(arr_str_famc.get(i)))
				{
					this.arr_node_famc.add(fam_list.get(j));
				}
				
				if (fam_list.get(i).getID().equals(arr_str_fams.get(i)))
				{
					this.arr_node_fams.add(fam_list.get(j));
				}
			}
		}
	}
	
	public String getID()
	{
		return this.id;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getSex()
	{
		return this.sex;
	}
	
	public MyDate getBIRT()
	{
		return this.birt;
	}
	
	public MyDate getDEAT()
	{
		return this.deat;
	}
	
	public ArrayList<String> getFamcStrArr()
	{
		return this.arr_str_famc;
	}
	
	public ArrayList<String> getFamsStrArr()
	{
		return this.arr_str_fams;
	}
}
