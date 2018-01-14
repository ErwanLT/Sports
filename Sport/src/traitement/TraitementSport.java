package traitement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import athlete.JoueurHandball;
import athlete.JoueurTennis;
import sports.MatchHandball;
import sports.MatchTennis;

public class TraitementSport {

	public static String traitementTennis(File fichier) {

		
		StringBuilder resultat = new StringBuilder();
		try {
			
			List<String> fileContent = FileUtils.readLines(fichier);

			MatchTennis matchTennis = new MatchTennis();
			JoueurTennis athlete1 = new JoueurTennis();
			JoueurTennis athlete2 = new JoueurTennis();
			
			Set<String> set = new HashSet<String>() ;
	        set.addAll(fileContent) ;
	        ArrayList<String> distinctListNomJoueur = new ArrayList<String>(set) ;
	        if(distinctListNomJoueur.size() == 1){
	        	resultat.append("Il n'y a qu'un seul joueur ayant marqué des points").append("\nLe deuxième joueur sera donc nommé Perdant");
	        	athlete1.setNom(distinctListNomJoueur.get(0).toString());
	        	athlete2.setNom("Perdant");
	        }else if(distinctListNomJoueur.size() == 2){
	        	athlete1.setNom(distinctListNomJoueur.get(0).toString());
	        	athlete2.setNom(distinctListNomJoueur.get(1).toString());
	        }else if(distinctListNomJoueur.size() > 2){
	        	return resultat.append("revoyer le format du ficheir, il y'a plus de 2 nom différents").toString();
	        }

			matchTennis.setJoueur1(athlete1);
			matchTennis.setJoueur2(athlete2);
			
			match : for(String nom : fileContent){
				if(athlete1.getNom().equals(nom)){
					 matchTennis.setScoreJoueur1(matchTennis.getScoreJoueur1()+1);					
				} else if (athlete2.getNom().equals(nom)){
					matchTennis.setScoreJoueur2(matchTennis.getScoreJoueur2()+1);						
				}

				resultat = resultat.append(matchTennis.getScore(matchTennis.getScoreJoueur1(), matchTennis.getScoreJoueur2())).append("\n");
				if(matchTennis.isEstTermine()){
					break match;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultat.toString();
	}

	public static String traitementHandball(File fichier) {
		String resultat = "";
		
		int scoreEquipe1 = 0;
		int scoreEquipe2 = 0;
		
		List<JoueurHandball> equipe1 = new ArrayList<JoueurHandball>();
		List<JoueurHandball> equipe2 = new ArrayList<JoueurHandball>();
		List<String> listePoint = new ArrayList<String>();
		
		try{
			final Workbook classeurExcel = WorkbookFactory.create(fichier);
			final Sheet feuilleEquipe1 = classeurExcel.getSheet("Equipe_1");
			final Sheet feuilleEquipe2 = classeurExcel.getSheet("Equipe_2");
			final Sheet feuilleScore = classeurExcel.getSheet("Score");
			
			int index = 1;
			equipe1 = feuilleToEquipe(feuilleEquipe1);
			equipe2 = feuilleToEquipe(feuilleEquipe2);
			
			Row lignePoint = feuilleScore.getRow(index++);
			while(lignePoint != null){
				listePoint.add(lignePoint.getCell(0).getStringCellValue());
				lignePoint = feuilleScore.getRow(index++);
			}
			
			MatchHandball match = new MatchHandball();
			match.setEquipe1(equipe1);
			match.setEquipe2(equipe2);
			
			List<String> listJoueurEquipe1 = match.getListJoueurEquipe1();
			List<String> listJoueurEquipe2 = match.getListJoueurEquipe2();
			
			for (String joueurMarquant : listePoint) {
				if (listJoueurEquipe1.contains(joueurMarquant)) {
					scoreEquipe1++;
				} else if(listJoueurEquipe2.contains(joueurMarquant)) {
					scoreEquipe2++;
				}
			}
			StringBuilder rslt = new StringBuilder("Le résultat du match est de :\n")
					.append(match.getScore(scoreEquipe1, scoreEquipe2));
			resultat = rslt.toString();
			
		} catch (InvalidFormatException | IOException e) {
	        e.printStackTrace();
	    }
		
		return resultat;
	}

	private static JoueurHandball rowToJoueur(final Row row) {
		
		JoueurHandball joueur = new JoueurHandball();
		joueur.setNom(row.getCell(0).getStringCellValue());
		joueur.setPoste(row.getCell(1).getStringCellValue());
		joueur.setNumero((int) row.getCell(2).getNumericCellValue());
		
		return joueur;
	    
	}
	
	
	
	private static List<JoueurHandball> feuilleToEquipe(final Sheet sheet){
		List<JoueurHandball> equipe = new ArrayList<JoueurHandball>();
		
		for(Row row : sheet) {
	         equipe.add(rowToJoueur(row));
		}
		
		return equipe;
	 
	}
}
