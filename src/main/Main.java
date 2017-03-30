package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import athlete.Athlete;
import athlete.JoueurTennis;
import selection.SelectionFichier;
import sports.MatchTennis;

public class Main {

	public static void main(String[] args) {
		
		FileInputStream fichierScoreEntree;

		JTextPane textPane = new JTextPane();

		creationFenetre(textPane);

		File fichier = SelectionFichier.choixFichierTxt();
		
		try{
			if(fichier != null){
				fichierScoreEntree = new FileInputStream(fichier);
				
				List<String> listJoueur = new ArrayList<>();
				MatchTennis matchTennis = new MatchTennis();
				Athlete athlete1 = new Athlete();
				Athlete athlete2 = new Athlete();

				InputStreamReader ipsr=new InputStreamReader(fichierScoreEntree);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;
				
				while((ligne=br.readLine())!=null){
					listJoueur.add(ligne);
				}
				Set<String> set = new HashSet<String>() ;
		        set.addAll(listJoueur) ;
		        ArrayList<String> distinctListNomJoueur = new ArrayList<String>(set) ;
		        if(distinctListNomJoueur.size() == 2){
		        	athlete1.setNom(distinctListNomJoueur.get(0).toString());
		        	athlete2.setNom(distinctListNomJoueur.get(1).toString());
		        }

				matchTennis.setJoueur1((JoueurTennis) athlete1);
				matchTennis.setJoueur2((JoueurTennis) athlete2);
				
				for(String nom : listJoueur){
					if(athlete1.getNom().equals(nom)){
						 matchTennis.setScoreJoueur1(matchTennis.getScoreJoueur1()+1);					
					} else if (athlete2.getNom().equals(nom)){
						matchTennis.setScoreJoueur2(matchTennis.getScoreJoueur2()+1);						
					}

					textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(), matchTennis.getScore(matchTennis.getScoreJoueur1(), matchTennis.getScoreJoueur2())+"\n", null);
				}
				br.close(); 
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

	}

	private static void creationFenetre(JTextPane textPane) {
		JFrame fenetre = new JFrame("Jeu de Tennis");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fenetre.add(textPane);
		fenetre.setSize(300, 500);
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);
		
		SimpleAttributeSet style_normal = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style_normal, "Calibri");
		StyleConstants.setFontSize(style_normal, 14);
	}

}
