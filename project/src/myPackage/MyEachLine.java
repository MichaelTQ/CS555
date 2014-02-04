package myPackage;

public class MyEachLine
{
	public int level;
	public String tag;
	public String arg;
	
	public MyEachLine(String whole_line)
	{
		//parse every line into level, tag, argument...
		//0 @I1@ INDI ==> level:0, tag: INDI, arg:@I1@ 
	}
	
	public boolean checkValidLine(String whole_line)
	{
		int tmp_level = -1;
		String tmp_tag;
		String tmp_arg;
		for(int i = 0; i < whole_line.length(); i++)
		{
			if(whole_line.charAt(i) == ' ')
			{
				tmp_level = Integer.parseInt(whole_line.substring(0, i));
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
						if(j+1 < whole_line.length())
						{
							if()
							tmp_arg = whole_line.substring(j+1, whole_line.length());
							System.out.println("argument=====>" + tmp_arg);
						}
						break;
					}
				}
				break;
			}
		}
		return true;
	}
}
