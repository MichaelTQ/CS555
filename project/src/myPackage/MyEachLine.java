package myPackage;

import java.util.Arrays;

public class MyEachLine
{
	public int level;
	public String tag;
	public String arg;
	//check the tags T06
	private static String [] arr_tags = {"INDI", "SEX", "BIRT", "DEAT", "FAMC", "FAMS",
		"FAM", "MARR", "HUSB", "WIFE", "CHIL", "DIV", "DATE", "TRLR", "NOTE", "NAME"};
	
	public MyEachLine(String whole_line)
	{
		//parse every line into level, tag, argument...
		//0 @I1@ INDI ==> level:0, tag: INDI, arg:@I1@
		setValidLine(whole_line);
	}
	
	public boolean checkValid()
	{
		//System.out.print(this.arg + " ");
		//System.out.println(this.tag);
		if (this.level > 2 || this.level < 0)
		{
			return false;
		}
		if (this.tag == null)
		{
			return false;
		}
		if (this.arg == null)
		{
			if (this.level == 1)
			{
				if (Arrays.asList("BIRT", "DEAT", "DIV", "MARR").contains(this.tag) != true)
				{
					return false;
				}
			}
			else if (this.level == 0 && this.tag != "TRLR")
			{
				return false;
			}
		}
		
		// We can do a lot more here...
		
		return true;
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
				
				if (tmp_level == 0)
				{
					if (i+1 <= whole_line.length())
					{
						String tmp_str = whole_line.substring(i+1);
						if(tmp_str.equals("TRLR"))
						{
							this.arg = null;
							this.tag = "TRLR";
							break;
						}
					}
				}
				
				//System.out.println(tmp_level);
				for (int j = i+1; j < whole_line.length(); j++)
				{
					if(whole_line.charAt(j) == ' ')
					{
						if (tmp_level == 0)
						{
							tmp_arg = whole_line.substring(i + 1, j);
							if (tmp_arg.toUpperCase().equals("NOTE"))
							{
								tmp_tag = tmp_arg;
								tmp_arg = whole_line.substring(j + i, whole_line.length());
								break;
							}
						}
						else
						{
							tmp_tag = whole_line.substring(i + 1, j);
						}
						//System.out.println(tmp_tag);
						if (j+1 <= whole_line.length())
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
						break;
					}
					
					if (j == whole_line.length() - 1)
					{
						tmp_tag = whole_line.substring(i + 1);
						tmp_arg = null;
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
		
		this.level = tmp_level;
		this.tag = tmp_tag;
		this.arg = tmp_arg;
		
		return true;
	}
	
	//check tags T06
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
	
	public int getLevel()
	{
		return this.level;
	}
	
	public String getTag()
	{
		return this.tag;
	}
	
	public String getArg()
	{
		return this.arg;
	}
}
