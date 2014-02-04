package myPackage;

import java.util.Arrays;
import java.util.Date;
import java.util.Calendar;

public class MyDate
{
	private String raw_str;
	
	private int day;
	private String month;
	private int year;
	//if input is wrong, day and year will be -1 and month will be null
	
	private static String [] array_month = {"JAN", "FEB", "MAR", 
			"APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT",
			"NOV", "DEC"};
	
	private int cur_day;
	private String cur_month;
	private int cur_year;
	
	private void setCurrentDate()
	{
		Date date_now = new Date();
		Calendar cal_now = Calendar.getInstance();
		cal_now.setTime(date_now);
		int year_now = cal_now.get(Calendar.YEAR);
		int month_now = cal_now.get(Calendar.MONTH);
		int day_now = cal_now.get(Calendar.DAY_OF_MONTH);
		
		this.cur_day = day_now;
		this.cur_month = array_month[month_now];
		this.cur_year = year_now;
	}
	
	public MyDate()
	{
		setCurrentDate();
		this.day = -1;
		this.month = null;
		this.year = -1;
		this.raw_str = null;
	}
	
	public MyDate(String new_raw_str)
	{
		setCurrentDate();
		this.raw_str = new_raw_str;
	}
	
	public MyDate(int new_day, String new_month, int new_year)
	{
		setCurrentDate();
		setDate(new_day, new_month, new_year);
	}
	
	public void myPrintCurDate()
	{
		System.out.println("==============");
		String result_str = "Date now:\n";
		result_str += "Year: ";
		result_str += new Integer(this.cur_year).toString() + "\n";
		result_str += "Month: " + this.cur_month + "\n";
		result_str += "Date: " + new Integer(this.cur_day).toString();
		System.out.println(result_str);
	}
		
	//To check if the date is valid.
	//very stupid way, probably should use "dateFormat"
	public boolean setDate(int new_day, String new_month, int new_year)
	{
		new_month = new_month.toUpperCase();
		this.setCurrentDate();
		int new_month_num = -1;
		int cur_month_num = Arrays.asList(array_month).indexOf(cur_month);
		if(Arrays.asList(array_month).contains(new_month) != true)
		{
			return false;
		}
		else
		{
			new_month_num = Arrays.asList(array_month).indexOf(new_month);
		}
		
		if(new_year > 1000 && new_year <= this.cur_year)
		{
			if(new_year == cur_year)
			{
				if(new_month_num <= cur_month_num)
				{
					if(new_day <= cur_day)
					{
						this.day = new_day;
						this.month = new_month.toUpperCase();
						this.year = new_year;
						return true;
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				if(new_day >= 1 && new_day <=31)
				{
					this.day = new_day;
					this.month = new_month.toUpperCase();
					this.year = new_year;
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return true;
		}
	}
	
	//I don't really know whether or not to keep the setFucntions.
	@SuppressWarnings("unused")
	private boolean setDay(int new_day)
	{
		if(setDate(new_day, this.month, this.year) != false)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int getDay()
	{
		return this.day;
	}
	
	
	// set
	@SuppressWarnings("unused")
	private boolean setMonth(String new_month)
	{
		if(setDate(this.day, new_month, this.year) != false)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//return "JAN", "FEB"...
	public String getMonth()
	{
		return this.month;
	}
	
	//return the month number 1 ~ 12
	public int getMonthNumber()
	{
		if(Arrays.asList(array_month).contains(this.month) == true)
		{
			return Arrays.asList(array_month).indexOf(this.month) - 1;
		}
		else
		{
			return -1;
		}
	}
	
	@SuppressWarnings("unused")
	private boolean setYear(int new_year)
	{
		if(setDate(this.day, this.month, new_year) != false)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean selfCheck()
	{
		if(this.month == null)
		{
			return false;
		}
		if(setDate(this.day, this.month, this.year))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
