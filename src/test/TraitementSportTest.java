package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import traitement.TraitementSport;

public class TraitementSportTest {
	
	private String fichierTennisTest3 = "C://Users//Erwan//workspace//Sport//src//test//testResources//matchTennisComplet.txt";
	
	private String fichierTennisTest1 = "C://Users//Erwan//workspace//Sport//src//test//testResources//testJeu.txt";
	
	private String fichierTennisTest4 = "C://Users//Erwan//workspace//Sport//src//test//testResources//testSetComplet.txt";
	
	private String fichierHandballTest = "C://Users//Erwan//workspace//Sport//src//test//testResources//matchHandball.xlsx";
	
	private String fichierTennisTestResult1 = "C://Users//Erwan//workspace//Sport//src//test//testResources//testJeuResult.txt";
	private String fichierTennisTestResult2 = "C://Users//Erwan//workspace//Sport//src//test//testResources//testSetResult.txt";
	private String fichierTennisTestResult3 = "C://Users//Erwan//workspace//Sport//src//test//testResources//testMatchResult.txt";
	
	@Test
	public void testTraitementHandball(){
		String resultat = TraitementSport.traitementHandball(new File(fichierHandballTest));
		assertEquals("Le résultat du match est de :\nScore equipe1 : 2 :: Score equipe2 : 1",resultat);
	}

	@Test
	public void testTraitementTennisJeu() throws IOException{
		String resultat = TraitementSport.traitementTennis(new File(fichierTennisTest1));
		File file = new File(fichierTennisTestResult1);	
		String expectedResult = FileUtils.readFileToString(file);
	
		assertEquals(expectedResult, resultat);		
	}

	@Test
	public void testTraitementTennisSet() throws IOException{
		String resultat = TraitementSport.traitementTennis(new File(fichierTennisTest4));
		File file = new File(fichierTennisTestResult2);		
		String expectedResult = FileUtils.readFileToString(file);
	
		assertEquals(expectedResult, resultat);
	}
	
	@Test
	public void testTraitementTennisMatchComplet() throws IOException{
		String resultat = TraitementSport.traitementTennis(new File(fichierTennisTest3));
		File file = new File(fichierTennisTestResult3);		
		String expectedResult = FileUtils.readFileToString(file);
	
		assertEquals(expectedResult, resultat);
	}

}
