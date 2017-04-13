package sports;

import athlete.JoueurTennis;

public abstract class SportIndividuel implements SportInterface {
	
	protected JoueurTennis joueur1;
	
	protected JoueurTennis joueur2;

	public SportIndividuel() {
		this.joueur1 = new JoueurTennis();
		this.joueur2 = new JoueurTennis();
	}
	
	protected abstract String joueurAvecPlusHautScore(int scoreJoueur1, int scoreJoueur2);

	@Override
	public String getNomSport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getScore(int score1, int score2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean estGagnant(int score1, int score2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estEgalite(int score1, int score2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String traiterPoint(int point) {
		// TODO Auto-generated method stub
		return null;
	}

}
