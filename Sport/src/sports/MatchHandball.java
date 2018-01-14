package sports;

import java.util.ArrayList;
import java.util.List;

import athlete.JoueurHandball;
import constante.SportConstante;

public class MatchHandball extends SportEquipe {
	
	private static final String NOM_SPORT = SportConstante.HANDBALL;
	
	private int scoreEquipe1;
	
	private int scoreEquipe2;
	
	protected List<JoueurHandball> equipe1;
	
	protected List<JoueurHandball> equipe2;

	public List<JoueurHandball> getEquipe1() {
		return equipe1;
	}

	public void setEquipe1(List<JoueurHandball> equipe1) {
		this.equipe1 = equipe1;
	}

	public List<JoueurHandball> getEquipe2() {
		return equipe2;
	}

	public void setEquipe2(List<JoueurHandball> equipe2) {
		this.equipe2 = equipe2;
	}
	
	public String getNomSport() {
		return NOM_SPORT;
	}

	public MatchHandball() {
		super();
	}

	@Override
	protected String equipeAvecPlusHautScore(int scoreJoueur1, int scoreJoueur2) {
		if (scoreEquipe1 > scoreEquipe2) {
			return "l'�quipe 1 � le plus haut score";
		} else {
			return "l'�quipe 2 � le plus haut score";
		}
	}

	public int getScoreEquipe1() {
		return scoreEquipe1;
	}

	public void setScoreEquipe1(int scoreEquipe1) {
		this.scoreEquipe1 = scoreEquipe1;
	}

	public int getScoreEquipe2() {
		return scoreEquipe2;
	}

	public void setScoreEquipe2(int scoreEquipe2) {
		this.scoreEquipe2 = scoreEquipe2;
	}
	
	@Override
	public String getScore(int score1, int score2) {
		return("Score equipe1 : " + score1 + " :: Score equipe2 : " + score2);
	}
	

	public List<String> getListJoueurEquipe1() {
		List<String> list = new ArrayList<>();
		for (JoueurHandball joueurHandball : equipe1) {
			list.add(joueurHandball.getNom());
		}
		
		return list;
	}
	
	public List<String> getListJoueurEquipe2() {
		List<String> list = new ArrayList<>();
		for (JoueurHandball joueurHandball : equipe2) {
			list.add(joueurHandball.getNom());
		}
		
		return list;
	}

}
