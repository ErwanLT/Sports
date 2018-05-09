package sports;

import athlete.Athlete;
import athlete.JoueurTennis;
import constante.SportConstante;
import constante.TennisConstante;

/**
 * classe décrivant un match de tennis
 * @author Erwan
 *
 */
public class MatchTennis extends SportIndividuel{
	
	public MatchTennis() {
		super();
		setEstTermine(false);
	}

	private static final String NOM_SPORT = SportConstante.TENNIS;
	
	private int scoreJoueur1;
	
	private int scoreJoueur2;

	private boolean estTermine;
	
	protected JoueurTennis joueur1;
	
	protected JoueurTennis joueur2;

	public Athlete getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(JoueurTennis joueur1) {
		this.joueur1 = joueur1;
	}

	public Athlete getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(JoueurTennis joueur2) {
		this.joueur2 = joueur2;
	}

	public int getScoreJoueur1() {
		return scoreJoueur1;
	}

	public void setScoreJoueur1(int scoreJoueur1) {
		this.scoreJoueur1 = scoreJoueur1;
	}

	public int getScoreJoueur2() {
		return scoreJoueur2;
	}

	public void setScoreJoueur2(int scoreJoueur2) {
		this.scoreJoueur2 = scoreJoueur2;
	}

	public String getNomSport() {
		return NOM_SPORT;
	}
	
	public boolean isEstTermine() {
		return estTermine;
	}

	public void setEstTermine(boolean estTermine) {
		this.estTermine = estTermine;
	}
	
	
	@Override
	public String getScore(int score1, int score2) {
		if(estEgalite(score1, score2)){
			return SportConstante.EGALITE;
		}
		if(avantage(score1, score2)){
			return new StringBuilder().append(TennisConstante.AVANTAGE)
									  .append(" ")
									  .append(joueurAvecPlusHautScore(score1, score2)).toString();
		}
		if(estJeuGagnant(score1, score2)){
			StringBuilder score = new StringBuilder();
			
			if (score1 > score2) {
				this.scoreJoueur1 = 0;
				this.scoreJoueur2 = 0;
				this.joueur1.setNombreJeu(this.joueur1.getNombreJeu()+1);
			} else {
				this.scoreJoueur1 = 0;
				this.scoreJoueur2 = 0;
				this.joueur2.setNombreJeu(this.joueur2.getNombreJeu()+1);
			}
			score.append("Jeu gagné par : " + joueurAvecPlusHautScore(score1, score2)+"\n");
			score.append("Score : \n");
			score.append(this.joueur1.getNombreJeu() + " jeu à "+this.joueur2.getNombreJeu() +" pour "+joueurAvecPlusHautScore(score1, score2)+"\n");
			score.append(traitementSet());
			score.append(estMatchTermine());
			return score.toString();
		}
		if (score1 == score2 && score1 !=0){
			return traiterPoint(score1)+"A";
		}

		return traiterPoint(score1) + " - " + traiterPoint(score2);
	}

	/**
	 * méthode qui analyse si le match est terminé ou non
	 * @return si le match n'est pas terminé: chaine vide<br>
	 * si le match est terminé : le nom du gagnant
	 */
	private String estMatchTermine() {
		int nombreSetTotal = joueur1.getNombreSet() + joueur2.getNombreSet();
		if(nombreSetTotal >= 3 && joueur1.getNombreSet()>= joueur2.getNombreSet()+2){
			this.setEstTermine(true);
			return "\nmatch gagné par : " + joueur1.getNom();
		}
		if(nombreSetTotal >= 3 && joueur2.getNombreSet()>= joueur1.getNombreSet()+2){
			this.setEstTermine(true);
			return "\nmatch gagné par : " + joueur2.getNom();
		}
		return "";
	}

	/**
	 * traitement du set en cours
	 * @return si le set n'est pas terminé chaine vide<br>
	 * si le set est terminé le nom du joueur ayant gagné ce dernier
	 */
	private String traitementSet() {
		if(estSetGagneJoueur1(joueur1.getNombreJeu(), joueur2.getNombreJeu())){
			joueur1.setNombreJeu(0);
			joueur2.setNombreJeu(0);
			joueur1.setNombreSet(joueur1.getNombreSet()+1);
			return "Set gagné par : " + joueur1.getNom()+"\n";
		}
		if(estSetGagneJoueur2(joueur1.getNombreJeu(), joueur2.getNombreJeu())){
			joueur1.setNombreJeu(0);
			joueur2.setNombreJeu(0);
			joueur2.setNombreSet(joueur2.getNombreSet()+1);
			return "Set gagné par : " + joueur2.getNom()+"\n";
		}
		return "";
	}
	
	/**
	 * méthode permettant de savoir si le joueur 2 à gagné le set en cours
	 * @param nombreJeuJoueur1 nombre de jeu du set gagné par le joueur 1
	 * @param nombreJeuJoueur2 nombre de jeu du set gagné par le joueur 2
	 * @return true / false
	 */
	private boolean estSetGagneJoueur2(int nombreJeuJoueur1, int nombreJeuJoueur2) {
		if(nombreJeuJoueur2 >= 6 && nombreJeuJoueur2 >= nombreJeuJoueur1+2){
			return true;
		}
		return false;
	}

	/**
	 * méthode permettant de savoir si le joueur 1 à gagné le set en cours
	 * @param nombreJeuJoueur1 nombre de jeu du set gagné par le joueur 1
	 * @param nombreJeuJoueur2 nombre de jeu du set gagné par le joueur 2
	 * @return true / false
	 */
	private boolean estSetGagneJoueur1(int nombreJeuJoueur1, int nombreJeuJoueur2) {
		if(nombreJeuJoueur1 >= 6 && nombreJeuJoueur1 >= nombreJeuJoueur2+2){
			return true;
		}
		return false;
	}

	@Override
	public boolean estGagnant(int score1, int score2) {
		if(score2 >= 4 && score2 >= score1 + 2 
				|| score1 >= 4 && score1 >= score2 + 2){
			return true;
		}
		return false;
	}
	
	/**
	 * méthode qui permet de savoir si le jeu est gagnat
	 * @param score1 score du joueur 1
	 * @param score2 score du joueur 2
	 * @return true / false
	 */
	public boolean estJeuGagnant(int score1, int score2) {
		if(score2 >= 4 && score2 >= score1 + 2 
				|| score1 >= 4 && score1 >= score2 + 2){
			return true;
		}
		return false;
	}

	@Override
	public boolean estEgalite(int score1, int score2) {
		if(score1 >= 3 && score2 == score1){
			return true;
		}	
		return false;
	}

	/**
	 * permet de savoir si un des joueur à l'avantage
	 * @param score1 score du joueur 1
	 * @param score2 score du joueur 2
	 * @return true / false
	 */
	public boolean avantage(int score1, int score2) {
		if (score2 >= 4 && score2 == score1 + 1
				|| score1 >= 4 && score1 == score2 + 1){
			return true;
		}

		return false;
	}

	@Override
	public String joueurAvecPlusHautScore(int score1, int score2) {
		if (score1 > score2) {
			return joueur1.getNom();
		} else {
			return joueur2.getNom();
		}
	}

	/**
	 * match tennis : <br>
	 * 1 = 15<br>
	 * 2 = 30<br>
	 * 3 = 40
	 */
	@Override
	public String traiterPoint(int point) {
		switch (point) {
		case 0:
			return TennisConstante.ZERO;
		case 1:
			return TennisConstante.QUINZE;
		case 2:
			return TennisConstante.TRENTE;
		case 3:
			return TennisConstante.QUARANTE;
		default:
			throw new IllegalArgumentException("Paramètre invalide");
		}
	}	
}
