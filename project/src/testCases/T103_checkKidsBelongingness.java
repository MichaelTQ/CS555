package testCases;

import static org.junit.Assert.*;
import myPackage.Checker;
import myPackage.FamilyList;

import org.junit.Test;

public class T103_checkKidsBelongingness
{
	FileReadingTest frt = new FileReadingTest();
	private static FamilyList fam_list = new FamilyList();
	String file_path = "./T103.ged";
	
	@Test
	public void test() {
		frt.check_file(file_path);
		fam_list = frt.getFList();
		
		new Checker();
		assertEquals(Checker.checkKidsBelongingness(fam_list), true);
	}
}

