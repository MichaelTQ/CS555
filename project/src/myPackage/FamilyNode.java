package myPackage;

import java.util.ArrayList;

public class FamilyNode
{
	private String id;
	private MyDate marr = null;
	private MyDate div = null;
//	private String str_husb;
//	private String str_wife;
	
//	private IndividualNode node_husb;
//	private IndividualNode node_wife;
	private ArrayList<String> list_str_children = new ArrayList<String>();
	private ArrayList<String> list_str_husb = new ArrayList<String>();
	private ArrayList<String> list_str_wife = new ArrayList<String>();
	
	private ArrayList<IndividualNode> list_node_children = new ArrayList<IndividualNode>();
	private ArrayList<IndividualNode> list_node_husb = new ArrayList<IndividualNode>();
	private ArrayList<IndividualNode> list_node_wife = new ArrayList<IndividualNode>();
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
				this.list_str_husb.add(arr_lines.get(i).getArg());
			}
			else if (arr_lines.get(i).getTag().toUpperCase().equals("WIFE"))
			{
				this.list_str_wife.add(arr_lines.get(i).getArg());
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
		this.list_node_husb = new ArrayList<IndividualNode>();
		this.list_node_wife = new ArrayList<IndividualNode>();
		for (int i = 0; i < list_indi.getSize(); i++)
		{
			for (int j = 0; j < this.list_str_husb.size(); j++)
			{
				if(list_str_husb.get(j).equals(list_indi.get(i).getID()))
				{
					this.list_node_husb.add(list_indi.get(i));
				}
			}
			
			for (int j = 0; j < this.list_str_wife.size(); j++)
			{
				if(list_str_wife.get(j).equals(list_indi.get(i).getID()))
				{
					this.list_node_wife.add(list_indi.get(i));
				}
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
	
	public boolean MARRCheck(){
		for(int i=0; i<list_node_husb.size(); i++)
		{
			if((marr.getYear()<list_node_husb.get(i).getBIRT().getYear())) {System.out.println("MARR Error!");return false;}
			else{
				if((marr.getYear()==list_node_husb.get(i).getBIRT().getYear())&&(marr.getMonthNumber()<list_node_husb.get(i).getBIRT().getMonthNumber())){System.out.println("MARR Error!");return false;}
				else{
					if((marr.getYear()==list_node_husb.get(i).getBIRT().getYear())&&(marr.getMonthNumber()<list_node_husb.get(i).getBIRT().getMonthNumber())&&(marr.getDay()<list_node_husb.get(i).getBIRT().getDay())){
						System.out.println("MARR Error!");return false;}
				}
			}

			if((marr.getYear()<list_node_wife.get(i).getBIRT().getYear())) {System.out.println("MARR Error!");return false;}
			else{
				if((marr.getYear()==list_node_wife.get(i).getBIRT().getYear())&&(marr.getMonthNumber()<list_node_wife.get(i).getBIRT().getMonthNumber())){System.out.println("MARR Error!");return false;}
				else{
					if((marr.getYear()==list_node_wife.get(i).getBIRT().getYear())&&(marr.getMonthNumber()<list_node_wife.get(i).getBIRT().getMonthNumber())&&(marr.getDay()<list_node_wife.get(i).getBIRT().getDay())){
						System.out.println("MARR Error!");return false;}
				}
			}
		}
			
//		if((marr.getYear()<list_node_husb.get(i).getBIRT().getYear())||(marr.getYear()<node_wife.getBIRT().getYear())) {System.out.println("MARR Error!");return false;}
//		else{
//			if((marr.getYear()==node_husb.getBIRT().getYear())&&(marr.getMonthNumber()<node_husb.getBIRT().getMonthNumber())){System.out.println("MARR Error!");return false;}
//			else{
//				if((marr.getYear()==node_husb.getBIRT().getYear())&&(marr.getMonthNumber()<node_husb.getBIRT().getMonthNumber())&&(marr.getDay()<node_husb.getBIRT().getDay())){
//					System.out.println("MARR Error!");return false;}
//				else{
//					if((marr.getYear()==node_wife.getBIRT().getYear())&&(marr.getMonthNumber()<node_wife.getBIRT().getMonthNumber())){System.out.println("MARR Error!");return false;}
//					else{
//						if((marr.getYear()==node_wife.getBIRT().getYear())&&(marr.getMonthNumber()<node_wife.getBIRT().getMonthNumber())&&(marr.getDay()<node_wife.getBIRT().getDay())){
//							System.out.println("MARR Error!");return false;}
//							}
//						}
//					}
//				}
		return true;
	}
	
	public boolean SurnameCheck(){
		if(list_node_husb.size()!=1)
		{
			System.out.println("Surname error!");
			return false;
		}
		else
		{
			String husb_surname=list_node_husb.get(0).getSurname();
			String check;
			for (int i=0;i<this.list_node_children.size();i++){
				check=this.list_node_children.get(i).getSurname();
				if(!check.equals(husb_surname)) {
					System.out.println("Surname Error! Daddy and kids don't share the same surname!");
					System.out.println("Daddy's surname is: "+husb_surname+", while kids' surname is:"+check+"!");
					return false;
				}
			}
		}
		return true;
	}
	public boolean MarriageDateCheck(){
		MyDate M=this.getMARR();
		MyDate D=this.getDIV();
		if (M.getYear()>D.getYear()){System.out.println("Marrige Date is Error");
		return false;}
		else{if ((M.getYear()==D.getYear())&&(M.getMonthNumber()>D.getMonthNumber())){
			System.out.println("Marrige Date is Error");
			return false;
		}
		else{
			if ((M.getYear()==D.getYear())&&(M.getMonthNumber()==D.getMonthNumber())&&(M.getDay()>D.getDay())){
				System.out.println("Marrige Date is Error");
				return false;
		}
		else return true;
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
	
//	public String getHusbStr()
//	{
//		return this.str_husb;
//	}
//	
//	public String getWifeStr()
//	{
//		return this.str_wife;
//	}
	public ArrayList<String> getHusbStr()
	{
		return this.list_str_husb;
	}
	
	public ArrayList<IndividualNode> getHusbNode()
	{
//		return this.node_husb;
		return this.list_node_husb;
	}
	
	public ArrayList<String> getWifeStr()
	{
		return this.list_str_wife;
	}
	
	public ArrayList<IndividualNode> getWifeNode()
	{
		return this.list_node_wife;
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
