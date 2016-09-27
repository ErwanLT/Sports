package abstractClasse;


public interface SportInterface {
	
	
	abstract String getScore(int score1, int score2);
	
	abstract boolean estGagnant(int score1, int score2);
	
	abstract boolean estEgalite(int score1, int score2);
	
	abstract String traiterPoint(int point);

}
