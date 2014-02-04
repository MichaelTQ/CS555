package myPackage;

//Main class, our program starts here
//to control file input, save the data into our own data structure,
//print the results to console.

public class CMain
{
	public static void main(String [] args)
	{
		MyDate test1 = new MyDate();
		System.out.println(test1.selfCheck());
		
		MyDate test3 = new MyDate(31, "Mar", 1989);
		System.out.println(test3.selfCheck());
		
		
	}
}
