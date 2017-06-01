package sports;

public abstract class SportIndividuel implements SportInterface {
	

	public SportIndividuel() {

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
