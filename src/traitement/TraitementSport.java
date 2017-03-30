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

import athlete.JoueurTennis;
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
			
			for(String nom : listJoueur){
				if(athlete1.getNom().equals(nom)){
					 matchTennis.setScoreJoueur1(matchTennis.getScoreJoueur1()+1);					
				} else if (athlete2.getNom().equals(nom)){
					matchTennis.setScoreJoueur2(matchTennis.getScoreJoueur2()+1);						
				}

				resultat = resultat +matchTennis.getScore(matchTennis.getScoreJoueur1(), matchTennis.getScoreJoueur2())+"\n";
			}
			br.close(); 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultat;
	}

}
