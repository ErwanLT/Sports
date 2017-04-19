package traitement;

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

1eimport org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import athlete.JoueurHandball;
import athlete.JoueurTennis;
import sports.MatchTennis;

eppublic class TraitementSport {

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultat;
	}

	public static String traitementHandball(File fichier) {
		String resultat = "";
		
		List<JoueurHandball> equipe1 = new ArrayList<JoueurHandball>();
		List<JoueurHandball> equipe2 = new ArrayList<JoueurHandball>();
		
		try{
			final Workbook classeurExcel = WorkbookFactory.create(fichier);
			final Sheet feuilleEquipe1 = classeurExcel.getSheet("Equipe_1");
			final Sheet feuilleEquipe2 = classeurExcel.getSheet("Equipe_2");
			final Sheet feuilleScore = classeurExcel.getSheet("Score");
			
			equipe1 = getEquipe(feuilleEquipe1);
			equipe2 = getEquipe(feuilleEquipe2);
			
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
	
	private static List<JoueurHandball> getEquipe(final sheet){
		List<JoueurHandball> equipe = new ArrayList<JoueurHandball>();
		int index = 1;
		Row lignesEquipe = sheet.getRow(index++);
		while (lignesEquipe != null) {
			final JoueurHandball joueur = rowToJoueur(lignesEquipe);
			equipe.add(joueur);
			lignesEquipe = sheet.getRow(index++);
	        }
		return equipe
	}
}
