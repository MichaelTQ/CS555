package testCases;

import static org.junit.Assert.*;
import myPackage.Checker;
import myPackage.IndividualList;

import org.junit.Test;

public class T100_CheckBD
{
	FileReadingTest frt = new FileReadingTest();
	private static IndividualList indi_list = new IndividualList();
	String file_path = "./T100.ged";
	
	@Test
	public void test() {
		frt.check_file(file_path);
		indi_list = frt.getIList();
		
		new Checker();
		assertEquals(Checker.checkBD(indi_list), true);
	}
}
