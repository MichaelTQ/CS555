package myPackage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Checker
{
	//T04 T18
	public static boolean checkFamilyExistence(FamilyList fam_list, IndividualList indi_list)
	{
		boolean return_flag = true;
		for (int i = 0; i < indi_list.getSize(); i++)
		{
			if (indi_list.get(i).getFamcNodeArr().size() != indi_list.get(i).getFamcStrArr().size())
			{
				return_flag = false;
				//System.out.println(indi_list.get(i).getFamcNodeArr().size() + "||" + indi_list.get(i).getFamcStrArr().size());
				//System.out.println(fam_list.getSize());
				System.out.println(indi_list.get(i).getID() + ": FAMC error!");
			}
			if (indi_list.get(i).getFamsNodeArr().size() != indi_list.get(i).getFamsStrArr().size())
			{
				return_flag = false;
				System.out.println(indi_list.get(i).getID() + ": FAMS error!");
			}
		}
		return return_flag;
	}
	
	//T24 T18 T19 T22
	public static boolean checkParentsChildrenBDay(FamilyList fam_list)
	{
		boolean return_flag = true;
		for (int i = 0; i < fam_list.getSize(); i++)
		{
			IndividualNode husb = fam_list.get(i).getHusbNode();
			IndividualNode wife = fam_list.get(i).getWifeNode();
			
			if (husb == null || wife == null)
			{
				//T22
				System.out.println("Husband or Wife in Family \"" + fam_list.get(i).getID() + "\" does not exist!");
				return false;
			}
			else
			{
				int husb_year = fam_list.get(i).getHusbNode().getBIRT().getYear();
				int wife_year = fam_list.get(i).getWifeNode().getBIRT().getYear();
				
				for (int j = 0; j < fam_list.get(i).getChildrenNode().size(); j++)
				{
					int child_year = fam_list.get(i).getChildrenNode().get(j).getBIRT().getYear();
					if (child_year < husb_year || child_year < wife_year)
					{
						//T19
						System.out.println("Parent is younger than his/her child in Family \"" + fam_list.get(i).getID() + "\"");
						return false;
					}
					else if ((child_year - husb_year) >= 100 || (child_year - wife_year) >= 100)
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
		int date = my_date.getDate();
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
}
