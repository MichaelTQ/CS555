package testCases;

import static org.junit.Assert.*;
import myPackage.Checker;
import myPackage.FamilyList;

import org.junit.Test;

public class T102_CheckHusbWifeNumber
{
	FileReadingTest frt = new FileReadingTest();
	private static FamilyList fam_list = new FamilyList();
	String file_path = "./T102.ged";
	
	@Test
	public void test() {
		frt.check_file(file_path);
		fam_list = frt.getFList();
		
		new Checker();
		assertEquals(Checker.checkHusbWifeNumber(fam_list), true);
	}
}

