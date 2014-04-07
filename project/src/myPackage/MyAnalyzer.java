package myPackage;

import java.util.ArrayList;

public class MyAnalyzer {
	private static double div_rate;
	private static double husb_avg_marr_age;
	private static double wife_avg_marr_age;
	private static double avg_kids_num;
	private static int div_num;
	private static int fam_num;
	
	public static int getMarrNum(FamilyList fam_list)
	{
		fam_num = fam_list.getSize();
		System.out.println("Marriage number is: "+fam_num);
		return fam_num;
	}
	
	public static int getDivNum(FamilyList fam_list)
	{
		for(int i=0; i<fam_list.getSize(); i++)
		{
			if(fam_list.get(i).getDIV()!=null)
			{
				div_num += 1;
			}
		}
		System.out.println("Divorce number is: "+div_num);
		return div_num;
	}
	
	public static double getDivRate()
	{
		div_rate = (double)(div_num)/(double)(fam_num)*100;
		System.out.println("Divorce rate is: "+div_rate+"%");
		return div_rate;
	}
	
	public static double getHusbAvgMarrAge(FamilyList fam_list)
	{
		for(int i=0; i<fam_list.getSize(); i++)
		{
			ArrayList<IndividualNode> list_node_husb = fam_list.get(i).getHusbNode();
			for(int j=0; j<list_node_husb.size(); j++)
			{
				int husb_birth_age = list_node_husb.get(j).getBIRT().getYear();
				int husb_marr_age = fam_list.get(i).getMARR().getYear();
				if(husb_birth_age<husb_marr_age)
				{
					husb_avg_marr_age += husb_marr_age - husb_birth_age;
				}
			}
		}
		husb_avg_marr_age /= fam_list.getSize();
		System.out.println("The average marriage age of male is "+husb_avg_marr_age+" year old.");
		return husb_avg_marr_age;
	}
	
	public static double getWifeAvgMarrAge(FamilyList fam_list)
	{
		for(int i=0; i<fam_list.getSize(); i++)
		{
			ArrayList<IndividualNode> list_node_wife = fam_list.get(i).getHusbNode();
			for(int j=0; j<list_node_wife.size(); j++)
			{
				int wife_birth_age = list_node_wife.get(j).getBIRT().getYear();
				int wife_marr_age = fam_list.get(i).getMARR().getYear();
				if(wife_birth_age<wife_marr_age)
				{
					wife_avg_marr_age += wife_marr_age - wife_birth_age;
				}
			}
		}
		wife_avg_marr_age /= fam_list.getSize();
		System.out.println("The average marriage age of female is "+wife_avg_marr_age+" year old.");
		return wife_avg_marr_age;
	}
	
	public static double getChildrenNum(FamilyList fam_list)
	{
		for(int i=0; i<fam_list.getSize(); i++)
		{
			ArrayList<IndividualNode> list_node_children = fam_list.get(i).getChildrenNode();
			avg_kids_num += list_node_children.size();
		}
		avg_kids_num /= fam_list.getSize();
		System.out.println("Each family approximately has "+avg_kids_num+" kids.");
		return avg_kids_num;
	}
	
//	public static double getMarriageReason(FamilyList fam_list)
//	{
//		
//	}
}
