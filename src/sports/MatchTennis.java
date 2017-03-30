package sports;

import athlete.Athlete;
import athlete.JoueurTennis;
import constante.SportConstante;
import constante.TennisConstante;

public class MatchTennis extends SportIndividuel{
	
	public MatchTennis() {
		this.joueur1 = new JoueurTennis();
		this.joueur2 = new JoueurTennis();
	}

	private static final String NOM_SPORT = "Tennis";
	
	private JoueurTennis joueur1;
	
	private JoueurTennis joueur2;
	
	private int scoreJoueur1;
	
	private int scoreJoueur2;

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
			score.append("Jeu gagn� par : " + joueurAvecPlusHautScore(score1, score2)+"\n");
			score.append("Score : \n");
			score.append(this.joueur1.getNombreJeu() + " jeu � "+this.joueur2.getNombreJeu() +" pour "+joueurAvecPlusHautScore(score1, score2)+"\n");
			score.append(traitementSet());
			return score.toString();
		}
		if (score1 == score2 && score1 !=0){
			return traiterPoint(score1)+"A";
		}

		return traiterPoint(score1) + " - " + traiterPoint(score2);
	}

	private String traitementSet() {
		if(estSetGagneJoueur1(joueur1.getNombreJeu(), joueur2.getNombreJeu())){
			joueur1.setNombreJeu(0);
			joueur2.setNombreJeu(0);
			joueur1.setNombreSet(joueur1.getNombreSet()+1);
			return "Set gagn� par : " + joueur1.getNom();
		}
		if(estSetGagneJoueur2(joueur1.getNombreJeu(), joueur2.getNombreJeu())){
			joueur1.setNombreJeu(0);
			joueur2.setNombreJeu(0);
			joueur2.setNombreSet(joueur2.getNombreSet()+1);
			return "Set gagn� par : " + joueur2.getNom();
		}
		return "";
	}
	
	private boolean estSetGagneJoueur2(int nombreJeuJoueur1, int nombreJeuJoueur2) {
		if(nombreJeuJoueur2 >= 6 && nombreJeuJoueur2 >= nombreJeuJoueur1+2){
			return true;
		}
		return false;
	}

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
			throw new IllegalArgumentException("Param�tre invalide");
		}
	}
	
	
}
