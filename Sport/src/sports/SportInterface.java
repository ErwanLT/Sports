package sports;

public interface SportInterface {
	
	abstract String getNomSport();
	
	/**
	 * méthode qui permet de remonter la chaine de caractères correspondant au score
	 * @param score1 score du joueur 1
	 * @param score2 score du joueur 1
	 * @return une chaine de caractère
	 */
	abstract String getScore(int score1, int score2);
	
	/**
	 * méthode permettant de savoir si un des joueur à gagné
	 * @param score1 score du joueur 1
	 * @param score2 score du joueur 2
	 * @return true ou false
	 */
	abstract boolean estGagnant(int score1, int score2);
	
	/**
	 * méthode permettant de savoir si les joueurs sont à égalité
	 * @param score1 score du joueur 1
	 * @param score2 score du joueur 2
	 * @return true / false
	 */
	abstract boolean estEgalite(int score1, int score2);
	
	/**
	 * méthode permettant si besoin de transformer le point en fonction du sport selectionné au départ
	 * @param point
	 * @return la transco du point en fonction du sport
	 */
	abstract String traiterPoint(int point);

}
