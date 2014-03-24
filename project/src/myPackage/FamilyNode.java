package myPackage;

import java.util.ArrayList;

public class FamilyNode
{
	private String id;
	private MyDate marr = null;
	private MyDate div = null;
	private String str_husb;
	private String str_wife;
	
	private IndividualNode node_husb;
	private IndividualNode node_wife;
	private ArrayList<String> list_str_children = new ArrayList<String>();
	
	private ArrayList<IndividualNode> list_node_children = new ArrayList<IndividualNode>();
	private ArrayList<String> arr_notes;
	
	public FamilyNode(ArrayList<MyEachLine> arr_lines)
	{
		String flag = null;
		for (int i = 0; i < arr_lines.size(); i++)
		{
			if (arr_lines.get(i).getTag().toUpperCase().equals("FAM"))
			{
				this.id = arr_lines.get(i).getArg();
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("MARR"))
			{
				flag = "MARR";
				continue;
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("HUSB"))
			{
				this.str_husb = arr_lines.get(i).getArg();
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("WIFE"))
			{
				this.str_wife = arr_lines.get(i).getArg();
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("CHIL"))
			{
				this.list_str_children.add(arr_lines.get(i).getArg());
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("DIV"))
			{
				flag = "DIV";
				continue;
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("NOTE"))
			{
				this.arr_notes.add(arr_lines.get(i).getArg());
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("DATE"))
			{
				if (flag == "MARR")
				{
					this.marr = new MyDate(arr_lines.get(i).getArg());
				}
				else if (flag == "DIV")
				{
					this.div = new MyDate(arr_lines.get(i).getArg());
				}
			}
		}
	}
	
	public void setIndiNodes(IndividualList list_indi)
	{
		this.list_node_children = new ArrayList<IndividualNode>();
		
		for (int i = 0; i < list_indi.getSize(); i++)
		{
			//T07
			if (this.str_husb == null || this.str_wife == null)
			{
				//System.out.println("Missing Husb or Wife in Family: \"" + this.id + "\"");
				return;
			}
			
			if (this.str_husb.equals(list_indi.get(i).getID()))
			{
				this.node_husb = list_indi.get(i);
			}
			
			if (this.str_wife.equals(list_indi.get(i).getID()))
			{
				this.node_wife = list_indi.get(i);
			}
			
			for (int j = 0; j < this.list_str_children.size(); j++)
			{
				if(list_str_children.get(j).equals(list_indi.get(i).getID()))
				{
					this.list_node_children.add(list_indi.get(i));
				}
			}
		}
	}
	
	public String getID()
	{
		return this.id;
	}
	
	public MyDate getMARR()
	{
		return this.marr;
	}
	
	public MyDate getDIV()
	{
		return this.div;
	}
	
	public String getHusbStr()
	{
		return this.str_husb;
	}
	
	public String getWifeStr()
	{
		return this.str_wife;
	}
	
	public IndividualNode getHusbNode()
	{
		return this.node_husb;
	}
	
	public IndividualNode getWifeNode()
	{
		return this.node_wife;
	}
	
	public ArrayList<String> getChildrenStr()
	{
		return this.list_str_children;
	}
	
	public ArrayList<IndividualNode> getChildrenNode()
	{
		return this.list_node_children;
	}
}
