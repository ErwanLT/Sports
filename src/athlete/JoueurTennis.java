package athlete;

public class JoueurTennis extends Athlete {
	
	int nombreJeu;
	
	int nombreSet;

	public JoueurTennis() {
		super.setNom("");
		this.nombreJeu = 0;
		this.nombreSet = 0;
	}

	public JoueurTennis(String nom) {
		super(nom);
		this.nombreJeu = 0;
		this.nombreSet = 0;
	}

	public int getNombreJeu() {
		return nombreJeu;
	}

	public void setNombreJeu(int nombreJeu) {
		this.nombreJeu = nombreJeu;
	}

	public int getNombreSet() {
		return nombreSet;
	}

	public void setNombreSet(int nombreSet) {
		this.nombreSet = nombreSet;
	}

}
