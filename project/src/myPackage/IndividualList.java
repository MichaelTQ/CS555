package myPackage;

import java.util.ArrayList;

public class IndividualList
{
	private ArrayList<IndividualNode> arr_indi = new ArrayList<IndividualNode>();
	
	public IndividualList()
	{
		arr_indi = new ArrayList<IndividualNode>();
	}
	
	public void add(IndividualNode node)
	{
		arr_indi.add(node);
	}
	
	public int getSize()
	{
		return this.arr_indi.size();
	}
	
	public IndividualNode get(int i)
	{
		if (i < arr_indi.size())
		{
			return this.arr_indi.get(i);
		}
		else
		{
			return null;
		}
	}
	
	public IndividualNode getIndividualByID(String id)
	{
		for (int i = 0; i != this.arr_indi.size(); i++)
		{
			if(id.toUpperCase().equals(arr_indi.get(i).getID()))
			{
				return arr_indi.get(i);
			}
		}
		return null;
	}
}