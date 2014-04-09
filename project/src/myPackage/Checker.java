package myPackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Checker
{
	//T04 T18
	public static boolean checkFamilyExistence(FamilyList fam_list, IndividualList indi_list)
	{
		boolean return_flag = true;
		for (int i = 0; i < indi_list.getSize(); i++)
		{
			if(indi_list.get(i).getFamcNodeArr().size()==0 && !indi_list.get(i).getFamcStrArr().isEmpty())
			{
				return_flag=false;
				System.out.println(indi_list.get(i).getID()+": Family " + indi_list.get(i).getFamcStrArr().get(0).toString() + " does not exist.");

				return_flag = false;
				//System.out.println(indi_list.get(i).getFamcNodeArr().size() + "||" + indi_list.get(i).getFamcStrArr().size());
				//System.out.println(fam_list.getSize());
				System.out.println(indi_list.get(i).getID() + ": FAMC error!");

			}
			else if(indi_list.get(i).getFamcNodeArr().size()==0 && indi_list.get(i).getFamcStrArr().isEmpty())
			{
				return_flag=true;
				System.out.println(indi_list.get(i).getID()+": FAMC is not mentioned.");
			}
			
			if(indi_list.get(i).getFamsNodeArr().size()==0 && !indi_list.get(i).getFamsStrArr().isEmpty())
			{
				return_flag=false;
				System.out.println(indi_list.get(i).getID()+": Family " + indi_list.get(i).getFamsStrArr().get(0).toString() + " does not exist.");
			}
			else if(indi_list.get(i).getFamsNodeArr().size()==0 && indi_list.get(i).getFamsStrArr().isEmpty())
			{
				return_flag=true;
				System.out.println(indi_list.get(i).getID()+": FAMS is not mentioned.");
			}
		}
		return return_flag;
	}
	
//	//T24 T18 T19 T22
//	public static boolean checkParentsChildrenBDay(FamilyList fam_list)
//	{
//		boolean return_flag = true;
//		for (int i = 0; i < fam_list.getSize(); i++)
//		{
//			IndividualNode husb = fam_list.get(i).getHusbNode();
//			IndividualNode wife = fam_list.get(i).getWifeNode();
//			
//			if (husb == null || wife == null)
//			{
//				//T22
//				System.out.println("Husband or Wife in Family \"" + fam_list.get(i).getID() + "\" does not exist!");
//				return false;
//			}
//			else
//			{
//				int husb_year = fam_list.get(i).getHusbNode().getBIRT().getYear();
//				int wife_year = fam_list.get(i).getWifeNode().getBIRT().getYear();
//				
//				for (int j = 0; j < fam_list.get(i).getChildrenNode().size(); j++)
//				{
//					int child_year = fam_list.get(i).getChildrenNode().get(j).getBIRT().getYear();
//					if (child_year < husb_year || child_year < wife_year)
//					{
//						//T19
//						System.out.println("Parent is younger than his/her child in Family \"" + fam_list.get(i).getID() + "\"");
//						return false;
//					}
//					else if ((child_year - husb_year) >= 100 || (child_year - wife_year) >= 100)
//					{
//						//T24
//						System.out.println("Parents are more than 100 years older than one of their children in Family \"" + fam_list.get(i).getID() + "\"");
//						return false;
//					}
//				}
//			}
//		}
//		return return_flag;
//	}
	
	//T24 T18 T19 T22
	public static boolean checkParentsChildrenBDay(FamilyList fam_list)
	{
		boolean return_flag = true;
		for (int i = 0; i < fam_list.getSize(); i++)
		{
			ArrayList<IndividualNode> husb = fam_list.get(i).getHusbNode();
			ArrayList<IndividualNode> wife = fam_list.get(i).getWifeNode();
			
			if (husb == null || wife == null)
			{
				//T22
				System.out.println("Husband or Wife in Family \"" + fam_list.get(i).getID() + "\" does not exist!");
				return false;
			}
			else
			{
				List<Integer> husb_year = new ArrayList<Integer>();
				List<Integer> wife_year = new ArrayList<Integer>();
			
				for(int j = 0; j < fam_list.get(i).getHusbNode().size(); j++)
				{
					husb_year.add(fam_list.get(i).getHusbNode().get(j).getBIRT().getYear());
				}
				
				for(int j = 0; j < fam_list.get(i).getWifeNode().size(); j++)
				{
					wife_year.add(fam_list.get(i).getWifeNode().get(j).getBIRT().getYear());
				}
				
				Arrays.sort(husb_year.toArray());
				Arrays.sort(wife_year.toArray());
				
				for (int j = 0; j < fam_list.get(i).getChildrenNode().size(); j++)
				{
					int child_year = fam_list.get(i).getChildrenNode().get(j).getBIRT().getYear();
					if (child_year < husb_year.get(husb_year.size()-1) || child_year < wife_year.get(wife_year.size()-1))
					{
						//T19
						System.out.println("Parent is younger than his/her child in Family \"" + fam_list.get(i).getID() + "\"");
						return false;
					}
					else if ((child_year - husb_year.get(husb_year.size()-1)) >= 100 || (child_year - wife_year.get(wife_year.size()-1)) >= 100)
					{
						//T24
						System.out.println("Parents are more than 100 years older than one of their children in Family \"" + fam_list.get(i).getID() + "\"");
						return false;
					}
				}
			}
		}
		return return_flag;
	}
	
	//T23
	public static boolean dateFormatChecker(FamilyList fam_list, IndividualList indi_list)
	{
		boolean return_flag = true;
		for (int i = 0; i < fam_list.getSize(); i++)
		{
			MyDate marr = fam_list.get(i).getMARR();
			MyDate div = fam_list.get(i).getDIV();
			if (marr != null && !checkDate(marr))
			{
				System.out.println(fam_list.get(i).getID() + " Date Error!====>" + marr.getDateStr());
				return_flag =  false;
			}
			if (div != null && !checkDate(div))
			{
				System.out.println(fam_list.get(i).getID() + " Date Error!====>" + div.getDateStr());
				return_flag = false;
			}
		}
		
		for (int i = 0; i < indi_list.getSize(); i++)
		{
			MyDate birt = indi_list.get(i).getBIRT();
			MyDate deat = indi_list.get(i).getDEAT();
			if (birt != null && !checkDate(birt))
			{
				System.out.println(indi_list.get(i).getID() + " Date Error!====>" + birt.getDateStr());
				return_flag = false;
			}
			if (deat != null && !checkDate(deat))
			{
				System.out.println(indi_list.get(i).getID() + " Date Error!====>" + birt.getDateStr());
				return_flag = false;
			}
		}
		
		return return_flag;
	}
	
	private static boolean checkDate(MyDate my_date)
	{
		int year = my_date.getYear();
		int month = my_date.getMonthNumber();
		int date = my_date.getDay();
		String date_str = year + "/" + month + "/" + date;
		try
		{
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
			df.setLenient(false);
			df.parse(date_str);
		}
		catch (ParseException e)
		{
			return false;
		}
		return true;
	}
	
	//check if children belong to only one certain family
	//T103
	public static boolean checkKidsBelongingness(FamilyList fam_list)
	{
		boolean return_flag = true;
		List<String> ChildrenList = new ArrayList<String>();
		for(int i = 0; i < fam_list.getSize(); i++)
		{
			for(int j = 0; j < fam_list.get(i).getChildrenNode().size(); j++)
			{
				ChildrenList.add(fam_list.get(i).getChildrenNode().get(j).getID().toString());
			}
		}
		List<String> duplicateChildrenlist = new ArrayList<String>(ChildrenList);
		for (String duplicatechildren: new HashSet<String>(duplicateChildrenlist)){
			duplicateChildrenlist.remove(duplicatechildren);
		}
		
		if(duplicateChildrenlist.size()==0)
			return return_flag;
		else
		{
			return_flag = false;
			for(int i = 0; i < duplicateChildrenlist.size(); i++)
			{
				System.out.println(duplicateChildrenlist.get(i)+" stays in different families.");
			}
			return return_flag;
		}
	}
	
	//check if a family hold only one couple
	//T102
	public static boolean checkHusbWifeNumber(FamilyList fam_list)
	{
		boolean return_flag = true;
		for(int i = 0; i < fam_list.getSize(); i++)
		{
			if(fam_list.get(i).getHusbNode().size() > 1)
			{
				return_flag = false;
				System.out.println("Family "+fam_list.get(i).getID()+" has more than one husband.");
			}
			if(fam_list.get(i).getWifeNode().size() > 1)
			{
				return_flag = false;
				System.out.println("Family "+fam_list.get(i).getID()+" has more than one wife.");
			}
		}
		return return_flag;
	}
	
	//check if the marriage stays correct. The date of divorce should goes after the date of marriage.
	//T101
	public static boolean checkDivorce(FamilyList fam_list)
	{
		boolean return_flag = true;
		
		for (int i = 0; i < fam_list.getSize(); i++)
		{
			MyDate marr = fam_list.get(i).getMARR();
			MyDate div = fam_list.get(i).getDIV();
			if(div!=null && marr!=null)
			{
				int divDay = div.getDay();
				int divMonth = div.getMonthNumber();
				int divYear = div.getYear();
				int marrDay = marr.getDay();
				int marrMonth = marr.getMonthNumber();
				int marrYear = marr.getYear();
				if(marrYear > divYear)
				{
					return_flag = false;
				}
				else 
				{
					if(marrMonth > divMonth)
					{
						return_flag = false;
					}
					else if(marrMonth == divMonth)
					{
						if(marrDay < divDay)
						{
							return_flag = true;
						}
						else
						{
							return_flag = false;
						}
					}
					else
					{
						return_flag = true;
					}
				}
				
				if(return_flag == false)
				{
					System.out.println("Family "+fam_list.get(i).getID()+" has a marrage issue.");
				}
			}
		}
		return return_flag;
	}
	
	//T100
	public static boolean checkBD(IndividualList indi_list)
	{
		boolean return_flag = true;
		
		for(int i=0; i<indi_list.getSize(); i++)
		{			
			if(indi_list.get(i).getBIRT()!=null&&indi_list.get(i).getDEAT()!=null)
			{
				int birthYear = indi_list.get(i).getBIRT().getYear();
				int birthMonth = indi_list.get(i).getBIRT().getMonthNumber();
				int birthDay = indi_list.get(i).getBIRT().getDay();
				int deathYear = indi_list.get(i).getDEAT().getYear();
				int deathMonth = indi_list.get(i).getDEAT().getMonthNumber();
				int deathDay = indi_list.get(i).getDEAT().getDay();
				
				if(birthYear>deathYear)
				{
					System.out.println("INDI "+indi_list.get(i).getID()+"'s Birth and death date error!");
					return_flag = false;
				}
				else
				{
					if(birthMonth>deathMonth)
					{
						System.out.println("INDI "+indi_list.get(i).getID()+"'s Birth and death date error!");
						return_flag = false;
					}
					else if(birthMonth==deathMonth)
					{
						if(birthDay>deathDay)
						{
							System.out.println("INDI "+indi_list.get(i).getID()+"'s Birth and death date error!");
							return_flag = false;
						}
						else
						{
							return_flag = true;
						}
					}
				}
			}
		}
		return return_flag;
	}
}
