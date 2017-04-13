package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import athlete.JoueurTennis;

public class JoueurTennisTest {

	@Test
	public void testNewJoueurTennis(){
		JoueurTennis joueur = new JoueurTennis();
		assertEquals("", joueur.getNom());
		assertEquals(0, joueur.getNombreJeu());
		assertEquals(0, joueur.getNombreSet());
		assertEquals("Le nom du sportif est : ", joueur.toString());
	}
	
	@Test
	public void testNewJoueurTennisAvecNom(){
		JoueurTennis joueur = new JoueurTennis("Erwan");
		assertEquals("Erwan", joueur.getNom());
		assertEquals(0, joueur.getNombreJeu());
		assertEquals(0, joueur.getNombreSet());
		assertEquals("Le nom du sportif est : Erwan", joueur.toString());
	}
	
	@Test
	public void testJoueurTennisAvecScore(){
		JoueurTennis joueur = new JoueurTennis("Erwan");
		joueur.setNombreJeu(2);
		joueur.setNombreSet(3);
		assertEquals("Erwan", joueur.getNom());
		assertEquals(2, joueur.getNombreJeu());
		assertEquals(3, joueur.getNombreSet());
		assertEquals("Le nom du sportif est : Erwan", joueur.toString());
		
	}

}
