package athlete;

public class JoueurHandball extends Athlete {
	
	private String poste;
	
	private int numero;

	public JoueurHandball() {
		super.setNom("");
		this.poste = "";
		this.numero = 0;
		
	}

	public JoueurHandball(String nom, String poste, int numero) {
		super(nom);
		this.poste = poste;
		this.numero = numero;
	}

	public String getPoste() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste = poste;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return super.toString() 
				+ "\nil occupe le poste : " + getPoste()
				+"\net à le maillot numero : " + getNumero();
	}
}
