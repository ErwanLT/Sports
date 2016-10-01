package sports;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import abstractClasse.SportIndividuel;
import abstractClasse.SportInterface;
import constantes.SportConstantes;
import constantes.TennisConstantes;
import fichiers.SelectionFichier;

public class MatchTennis extends SportIndividuel implements SportInterface {

	public MatchTennis() {

	}

	@Override
	public String getScore(int pointDjokovic, int pointMacEnroe){
		if(estEgalite(pointDjokovic, pointMacEnroe)){
			return SportConstantes.EGALITE;
		}
		if(avantage(pointDjokovic, pointMacEnroe)){
			return new StringBuilder().append(TennisConstantes.AVANTAGE)
									  .append(" ")
									  .append(joueurAvecPlusHautScore(pointDjokovic, pointMacEnroe)).toString();
		}
		if(estGagnant(pointDjokovic, pointMacEnroe)){
			return "Jeu gagné par : " + joueurAvecPlusHautScore(pointDjokovic, pointMacEnroe);			
		}
		if (pointDjokovic == pointMacEnroe && pointDjokovic !=0){
			return traiterPoint(pointDjokovic)+"A";
		}

		return traiterPoint(pointDjokovic) + " - " + traiterPoint(pointMacEnroe);
	}

	@Override
	public boolean estGagnant(int pointDjokovic, int pointMacEnroe) {
		if(pointMacEnroe >= 4 && pointMacEnroe >= pointDjokovic + 2 
				|| pointDjokovic >= 4 && pointDjokovic >= pointMacEnroe + 2){
			return true;
		}
		return false;
	}

	@Override
	public boolean estEgalite(int pointDjokovic, int pointMacEnroe) {
		if(pointDjokovic >= 3 && pointMacEnroe == pointDjokovic){
			return true;
		}	
		return false;
	}

	public boolean avantage(int pointDjokovic, int pointMacEnroe) {
		if (pointMacEnroe >= 4 && pointMacEnroe == pointDjokovic + 1
				|| pointDjokovic >= 4 && pointDjokovic == pointMacEnroe + 1){
			return true;
		}

		return false;
	}

	@Override
	public String joueurAvecPlusHautScore(int pointDjokovic, int pointMacEnroe) {
		if (pointDjokovic > pointMacEnroe) {
			return TennisConstantes.DJOKOVIC;
		} else {
			return TennisConstantes.MACENROE;
		}
	}

	@Override
	public String traiterPoint(int point) {
		switch (point) {
		case 0:
			return TennisConstantes.ZERO;
		case 1:
			return TennisConstantes.QUINZE;
		case 2:
			return TennisConstantes.TRENTE;
		case 3:
			return TennisConstantes.QUARANTE;
		default:
			throw new IllegalArgumentException("Paramètre invalide");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int pointMacEnroe = 0;
		int pointDjokovic = 0;

		FileInputStream fichierScoreEntree;

		JTextPane textPane = new JTextPane();

		JFrame fenetre = new JFrame("Jeu de Tennis");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		fenetre.add(textPane);
		fenetre.setSize(300, 500);
		fenetre.setLocationRelativeTo(null);
		fenetre.setVisible(true);

		SimpleAttributeSet style_normal = new SimpleAttributeSet();
		StyleConstants.setFontFamily(style_normal, "Calibri");
		StyleConstants.setFontSize(style_normal, 14);
		SportInterface match = new MatchTennis();

		File fichier = SelectionFichier.choixFichierTxt();

		try {

			if(fichier != null){
				fichierScoreEntree = new FileInputStream(fichier);

				InputStreamReader ipsr=new InputStreamReader(fichierScoreEntree);
				BufferedReader br=new BufferedReader(ipsr);
				String ligne;

				textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(), "Djokovic - MacEnroe\n", style_normal);
				System.out.println("Djokovic" + " - " + "MacEnroe");
				while ((ligne=br.readLine())!=null){					
					if(TennisConstantes.DJOKOVIC.equals(ligne)){
						pointDjokovic ++;					
					} else if (TennisConstantes.MACENROE.equals(ligne)){
						pointMacEnroe ++;						
					}

					textPane.getStyledDocument().insertString(textPane.getStyledDocument().getLength(), match.getScore(pointDjokovic, pointMacEnroe)+"\n", style_normal);

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

}
