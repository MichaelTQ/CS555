package myPackage;

import java.util.ArrayList;

public class FamilyList
{
	private ArrayList<FamilyNode> arr_fam = new ArrayList<FamilyNode>();
	
	public FamilyList()
	{
		arr_fam = new ArrayList<FamilyNode>();
	}
	
	public void add(FamilyNode node)
	{
		arr_fam.add(node);
	}
	
	public int getSize()
	{
		return this.arr_fam.size();
	}
	
	public FamilyNode get(int i)
	{
		if (i < arr_fam.size())
		{
			return arr_fam.get(i);
		}
		else
		{
			return null;
		}
	}
	
	public FamilyNode getFamilyByID(String id)
	{
		for (int i = 0; i != this.arr_fam.size(); i++)
		{
			if (id.toUpperCase().equals(arr_fam.get(i).getID()))
			{
				return arr_fam.get(i);
			}
		}
		return null;
	}
}
