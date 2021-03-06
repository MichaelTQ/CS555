package testCases;

import static org.junit.Assert.*;
import myPackage.Checker;
import myPackage.FamilyList;
import myPackage.IndividualList;

import org.junit.Test;

public class T04_CheckFamilyExistence
{
	FileReadingTest frt = new FileReadingTest();
	private static FamilyList fam_list = new FamilyList();
	private static IndividualList indi_list = new IndividualList();
	String file_path = "./T04.ged";
	
	@Test
	public void test() {
		frt.check_file(file_path);
		fam_list = frt.getFList();
		indi_list = frt.getIList();
		
		new Checker();
		assertEquals(Checker.checkFamilyExistence(fam_list, indi_list), true);
	}
}
