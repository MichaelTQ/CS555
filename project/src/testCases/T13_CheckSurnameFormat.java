package testCases;

import static org.junit.Assert.*;
import myPackage.Checker;
import myPackage.IndividualList;

import org.junit.Test;

public class T13_CheckSurnameFormat
{
	FileReadingTest frt = new FileReadingTest();
	private static IndividualList indi_list = new IndividualList();
	String file_path = "./T17.ged";
	
	@Test
	public void test() {
		frt.check_file(file_path);
		indi_list = frt.getIList();
		
		new Checker();
		assertEquals(Checker.SurnameFormatCheck(indi_list), false);
	}

}