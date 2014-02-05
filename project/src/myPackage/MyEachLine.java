package myPackage;

import java.util.Arrays;

public class MyEachLine
{
	public int level;
	public String tag;
	public String arg;
	
	private static String [] arr_tags = {"INDI", "SEX", "BIRT", "DEAT", "FAMC", "FAMS",
		"FAM", "MARR", "HUSB", "WIFE", "CHIL", "DIV", "DATE", "TRLR", "NOTE"};
	
	public MyEachLine(String whole_line)
	{
		//parse every line into level, tag, argument...
		//0 @I1@ INDI ==> level:0, tag: INDI, arg:@I1@
		setValidLine(whole_line);
	}
	
	public boolean setValidLine(String whole_line)
	{
		int tmp_level = -1;
		String tmp_tag = null;
		String tmp_arg = null;
		for(int i = 0; i < whole_line.length(); i++)
		{
			if(whole_line.charAt(i) == ' ')
			{
				try
				{
					tmp_level = Integer.parseInt(whole_line.substring(0, i));
				}
				catch (NumberFormatException e)
				{
					System.out.println("Format error in level number: " + tmp_level);
					return false;
				}
				//System.out.println(tmp_level);
				for (int j = i+1; j < whole_line.length(); j++)
				{
					if(whole_line.charAt(j) == ' ')
					{
						if (tmp_level == 0)
						{
							tmp_arg = whole_line.substring(i + 1, j);
						}
						else
						{
							tmp_tag = whole_line.substring(i + 1, j);
						}
						//System.out.println(tmp_tag);
						if(j+1 <= whole_line.length())
						{
							if (tmp_level == 0)
							{
								tmp_tag = whole_line.substring(j+1, whole_line.length());
							}
							else
							{
								tmp_arg = whole_line.substring(j+1, whole_line.length());
							}
								
						}
						System.out.println("tag===>" + tmp_tag);
						System.out.println("arg===>" + tmp_arg);
						break;
					}
				}
				break;
			}
		}
		
		//check the tag
		if (!checkTag(tmp_tag))
		{
			return false;
		}
		else if (tmp_tag == "INDI" || tmp_tag == "FAM" || tmp_tag == "TRLR" || tmp_tag == "NOTE")
		{
			if (tmp_level != 0)
			{
				return false;
			}
		}
		else if (tmp_tag == "DATE")
		{
			if (tmp_level != 2)
			{
				return false;
			}
		}
		else if(tmp_level != 1)
		{
			return false;
		}
		
		this.level = tmp_level;
		this.tag = tmp_tag;
		this.arg = tmp_arg;
		
		return true;
	}
	
	private boolean checkTag(String tmp_tag)
	{
		if (Arrays.asList(arr_tags).contains(tmp_tag))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void myPrint()
	{
		System.out.println("===========");
		System.out.println("Level: " + new Integer(this.level).toString());
		System.out.println("Tag: " + this.tag);
		System.out.println("Argument: " + this.arg);
	}
	
}
