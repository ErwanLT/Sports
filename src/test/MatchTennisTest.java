package test;

import static org.junit.Assert.*;

import org.junit.Test;

import athlete.Athlete;
import athlete.JoueurTennis;
import constante.SportConstante;
import constante.TennisConstante;

import sports.MatchTennis;

public class MatchTennisTest {
	


	@Test
	public void testGetScore() {
		MatchTennis match = new MatchTennis();
		match.setJoueur1((JoueurTennis) new Athlete("Erwan"));
		match.setJoueur2((JoueurTennis) new Athlete("Amelie"));
		match.setScoreJoueur1(8);
		match.setScoreJoueur2(6);
		assertEquals("Jeu gagné par : Erwan",match.getScore(8, 6));
		match.setScoreJoueur1(4);
		match.setScoreJoueur2(8);
		assertEquals("Jeu gagné par : Amelie",match.getScore(4, 8));
		match.setScoreJoueur1(6);
		match.setScoreJoueur2(6);
		assertEquals(SportConstante.EGALITE,match.getScore(6, 6));
		match.setScoreJoueur1(5);
		match.setScoreJoueur2(6);
		assertEquals("Avantage Amelie",match.getScore(5, 6));
		match.setScoreJoueur1(6);
		match.setScoreJoueur2(5);
		assertEquals("Avantage Erwan",match.getScore(6, 5));
		match.setScoreJoueur1(1);
		match.setScoreJoueur2(1);
		assertEquals("15A",match.getScore(1, 1));
		match.setScoreJoueur1(2);
		match.setScoreJoueur2(2);
		assertEquals("30A",match.getScore(2, 2));
		match.setScoreJoueur1(0);
		match.setScoreJoueur2(0);
		assertEquals("0 - 0",match.getScore(0, 0));
		match.setScoreJoueur1(1);
		match.setScoreJoueur2(0);
		assertEquals("15 - 0",match.getScore(1, 0));
		match.setScoreJoueur1(1);
		match.setScoreJoueur2(2);
		assertEquals("15 - 30",match.getScore(1, 2));
		match.setScoreJoueur1(3);
		match.setScoreJoueur2(2);
		assertEquals("40 - 30",match.getScore(3, 2));
	}

	@Test
	public void testEstGagnant() {
		MatchTennis match = new MatchTennis();
		assertTrue(match.estGagnant(4, 6));
		assertTrue(match.estGagnant(8, 6));
		assertFalse(match.estGagnant(4, 4));
	}

	@Test
	public void testJoueurAvecPlusHautScore() {
		MatchTennis match = new MatchTennis();
		match.setJoueur1((JoueurTennis) new Athlete("Erwan"));
		match.setJoueur2((JoueurTennis) new Athlete("Amelie"));
		match.setScoreJoueur1(4);
		match.setScoreJoueur2(2);
		assertEquals("Erwan", match.joueurAvecPlusHautScore(4, 2));
		match.setScoreJoueur1(1);
		match.setScoreJoueur2(5);
		assertEquals("Amelie", match.joueurAvecPlusHautScore(1, 5));
	}

	@Test
	public void testAvantage() {
		MatchTennis match = new MatchTennis();
		assertFalse(match.avantage(3, 3));
		assertFalse(match.avantage(1, 3));
		assertFalse(match.avantage(5, 5));
		assertTrue(match.avantage(5, 6));
		assertTrue(match.avantage(6, 5));
	}

	@Test
	public void testEstEgalite() {
		MatchTennis match = new MatchTennis();
		assertTrue(match.estEgalite(3, 3));
		assertFalse(match.estEgalite(1, 3));
		assertTrue(match.estEgalite(5, 5));
	}

	@Test
	public void testTraiterPoint() {
		MatchTennis match = new MatchTennis();
		for(int i=0; i<=5; i++){
			try{
				match.traiterPoint(i);
				if(i==0){
					assertEquals(TennisConstante.ZERO,match.traiterPoint(i));
				}else if(i==1){
					assertEquals(TennisConstante.QUINZE,match.traiterPoint(i));
				} else if(i==2){
					assertEquals(TennisConstante.TRENTE,match.traiterPoint(i));
				} else if(i==3){
					assertEquals(TennisConstante.QUARANTE,match.traiterPoint(i));
				}
			} catch (IllegalArgumentException e){
				assertEquals("Paramètre invalide", e.getMessage());
			}
			
		}
	}

}
