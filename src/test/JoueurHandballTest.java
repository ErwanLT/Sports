package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import athlete.JoueurHandball;

public class JoueurHandballTest {

	@Test
	public void testNewJoueurHandball(){
		JoueurHandball joueur = new JoueurHandball();
		assertEquals("", joueur.getNom());
		assertEquals("", joueur.getPoste());
		assertEquals(0, joueur.getNumero());
		assertEquals("Le nom du sportif est : "+
				"\nil occupe le poste : "+
				"\net à le maillot numero : 0", joueur.toString());
	}

	@Test
	public void testNewJoueurHandballComplet(){
		JoueurHandball joueur = new JoueurHandball("Erwan", "Ailié droit", 10);
		assertEquals("Erwan", joueur.getNom());
		assertEquals("Ailié droit", joueur.getPoste());
		assertEquals(10, joueur.getNumero());
		assertEquals("Le nom du sportif est : Erwan"+
				"\nil occupe le poste : Ailié droit"+
				"\net à le maillot numero : 10", joueur.toString());
		
	}
	
	@Test
	public void testMethodeAccess(){
		JoueurHandball joueur = new JoueurHandball();
		joueur.setNom("Erwan");
		joueur.setPoste("Pivot");
		joueur.setNumero(22);
		assertEquals("Erwan", joueur.getNom());
		assertEquals("Pivot", joueur.getPoste());
		assertEquals(22, joueur.getNumero());
		assertEquals("Le nom du sportif est : Erwan"+
				"\nil occupe le poste : Pivot"+
				"\net à le maillot numero : 22", joueur.toString());
	}
}
