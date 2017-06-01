package traitement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

		
		String resultat = new String();
		InputStreamReader ipsr;
		try {
			ipsr = new InputStreamReader(new FileInputStream(fichier));
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			
			List<String> listJoueur = new ArrayList<>();
			MatchTennis matchTennis = new MatchTennis();
			JoueurTennis athlete1 = new JoueurTennis();
			JoueurTennis athlete2 = new JoueurTennis();
			
			Set<String> set = new HashSet<String>() ;
			while((ligne=br.readLine())!=null){
				listJoueur.add(ligne);
			}
	        set.addAll(listJoueur) ;
	        ArrayList<String> distinctListNomJoueur = new ArrayList<String>(set) ;
	        if(distinctListNomJoueur.size() == 2){
	        	athlete1.setNom(distinctListNomJoueur.get(0).toString());
	        	athlete2.setNom(distinctListNomJoueur.get(1).toString());
	        }

			matchTennis.setJoueur1(athlete1);
			matchTennis.setJoueur2(athlete2);
			
			match : for(String nom : listJoueur){
				if(athlete1.getNom().equals(nom)){
					 matchTennis.setScoreJoueur1(matchTennis.getScoreJoueur1()+1);					
				} else if (athlete2.getNom().equals(nom)){
					matchTennis.setScoreJoueur2(matchTennis.getScoreJoueur2()+1);						
				}

				resultat = resultat +matchTennis.getScore(matchTennis.getScoreJoueur1(), matchTennis.getScoreJoueur2())+"\n";
				if(matchTennis.isEstTermine()){
					break match;
				}
			}
			br.close(); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultat;
	}

	public static String traitementHandball(File fichier) {
		String resultat = "";
		
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
		int index = 1;
		Row lignesEquipe = sheet.getRow(index++);
		
		while (lignesEquipe != null) {

            final JoueurHandball joueur = rowToJoueur(lignesEquipe);
            equipe.add(joueur);

            lignesEquipe = sheet.getRow(index++);
        }
		
		return equipe;
	 
	}
}
