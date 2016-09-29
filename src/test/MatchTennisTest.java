package test;

import static org.junit.Assert.*;

import org.junit.Test;

import constantes.TennisConstantes;
import constantes.SportConstantes;

import sports.MatchTennis;

public class MatchTennisTest {
	


	@Test
	public void testGetScore() {
		MatchTennis match = new MatchTennis();
		assertEquals("Jeu gagné par : Djokovic",match.getScore(8, 6));
		assertEquals("Jeu gagné par : MacEnroe",match.getScore(4, 8));
		assertEquals(SportConstante.EGALITE,match.getScore(6, 6));
		assertEquals("Avantage MacEnroe",match.getScore(5, 6));
		assertEquals("Avantage Djokovic",match.getScore(6, 5));
		assertEquals("15A",match.getScore(1, 1));
		assertEquals("30A",match.getScore(2, 2));
		assertEquals("0 - 0",match.getScore(0, 0));
		assertEquals("15 - 0",match.getScore(1, 0));
		assertEquals("15 - 30",match.getScore(1, 2));
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
		assertEquals(TennisConstantes.DJOKOVIC, match.joueurAvecPlusHautScore(4, 2));
		assertEquals(TennisConstantes.MACENROE, match.joueurAvecPlusHautScore(1, 5));
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
