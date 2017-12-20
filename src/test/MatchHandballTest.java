package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import athlete.JoueurHandball;
import sports.MatchHandball;

public class MatchHandballTest {
	
	private MatchHandball match;
	
	@Test
	public void testMatch(){
		match = new MatchHandball();
		match.setEquipe1(createEquipe1());
		match.setEquipe2(createEquipe2());
		assertNotNull("l'équipe 1 ne doit pas être vide", match.getEquipe1());
		assertNotNull("l'équipe 2 ne doit pas être vide", match.getEquipe2());
		assertTrue("l'équipe 1 ne doit pas être vide", match.getEquipe1().size() != 0);
		assertTrue("l'équipe 2 ne doit pas être vide", match.getEquipe2().size() != 0);
	}
	
	@Test
	public void testGetScore(){
		match = new MatchHandball();
		assertEquals("Score equipe1 : 1 :: Score equipe2 : 3",match.getScore(1, 3));
	}

	private List<JoueurHandball> createEquipe2() {
		List<JoueurHandball> listeJoueur = new ArrayList<JoueurHandball>();
		
		JoueurHandball joueurHandball1 = new JoueurHandball("Alan", "Pivot", 3);
		JoueurHandball joueurHandball2 = new JoueurHandball("Marine", "Aillié", 4);
		
		listeJoueur.add(joueurHandball1);
		listeJoueur.add(joueurHandball2);
		
		return listeJoueur;
	}

	private List<JoueurHandball> createEquipe1() {
		List<JoueurHandball> listeJoueur = new ArrayList<JoueurHandball>();
		
		JoueurHandball joueurHandball1 = new JoueurHandball("Erwan", "Pivot", 1);
		JoueurHandball joueurHandball2 = new JoueurHandball("Amelie", "Aillié", 2);
		
		listeJoueur.add(joueurHandball1);
		listeJoueur.add(joueurHandball2);
		
		return listeJoueur;
	}

}
