package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import athlete.JoueurTennis;
import constante.SportConstante;
import constante.TennisConstante;
import sports.MatchTennis;

public class MatchTennisTest {
	


	@Test
	public void testGetScore() {
		MatchTennis match = new MatchTennis();
		match.setJoueur1(new JoueurTennis("Erwan"));
		match.setJoueur2(new JoueurTennis("Amelie"));
		assertEquals("Le nom du sportif est : Erwan", match.getJoueur1().toString());
		assertEquals("Le nom du sportif est : Amelie", match.getJoueur2().toString());
		match.setScoreJoueur1(8);
		match.setScoreJoueur2(6);
		assertEquals("Jeu gagné par : Erwan\nScore : \n1 jeu à 0 pour Erwan\n",match.getScore(8, 6));
		match.setScoreJoueur1(4);
		match.setScoreJoueur2(8);
		assertEquals("Jeu gagné par : Amelie\nScore : \n1 jeu à 1 pour Amelie\n",match.getScore(4, 8));
		match.setScoreJoueur1(6);
		match.setScoreJoueur2(6);
		assertEquals(6, match.getScoreJoueur1());
		assertEquals(SportConstante.EGALITE,match.getScore(6, 6));
		match.setScoreJoueur1(5);
		match.setScoreJoueur2(6);
		assertEquals("Avantage Amelie",match.getScore(5, 6));
		match.setScoreJoueur1(6);
		match.setScoreJoueur2(5);
		assertEquals(5, match.getScoreJoueur2());
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
		match.setJoueur1(new JoueurTennis("Erwan"));
		match.setJoueur2(new JoueurTennis("Amelie"));
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
	
	@Test
	public void testNomSport(){
		MatchTennis match = new MatchTennis();
		assertEquals("Tennis", match.getNomSport());
	}
	
	@Test
	public void TestJoueur(){
		MatchTennis match = new MatchTennis();
		match.setJoueur1(new JoueurTennis("Erwan"));
		match.setJoueur2(new JoueurTennis("Amelie"));
		
		JoueurTennis joueur1Test = (JoueurTennis) match.getJoueur1();
		JoueurTennis joueur2Test = (JoueurTennis) match.getJoueur2();
		
		assertNotNull(joueur1Test);
		assertNotNull(joueur2Test);
		assertEquals("Erwan", joueur1Test.getNom());
		assertEquals("Amelie", joueur2Test.getNom());
		
	}
	
	@Test
	public void testMatchFini(){
		MatchTennis match1 = new MatchTennis();		
		JoueurTennis joueur1 = new JoueurTennis("Erwan");
		joueur1.setNombreJeu(6);
		joueur1.setNombreSet(3);
		JoueurTennis joueur2 = new JoueurTennis("Amelie");
		match1.setJoueur1(joueur1);
		match1.setJoueur2(joueur2);
		match1.setScoreJoueur1(4);
		match1.setScoreJoueur2(0);
		
		match1.getScore(match1.getScoreJoueur1(), match1.getScoreJoueur2());
		assertEquals(true, match1.isEstTermine());
		
		MatchTennis match2 = new MatchTennis();		
		JoueurTennis joueur1b = new JoueurTennis("Erwan");
		JoueurTennis joueur2b = new JoueurTennis("Amelie");
		joueur2b.setNombreJeu(6);
		joueur2b.setNombreSet(3);
		match2.setJoueur1(joueur1b);
		match2.setJoueur2(joueur2b);
		match2.setScoreJoueur1(0);
		match2.setScoreJoueur2(4);
		
		match2.getScore(match2.getScoreJoueur1(), match2.getScoreJoueur2());
		assertEquals(true, match2.isEstTermine());
		
	}

}
