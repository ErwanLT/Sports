package selection;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SelectionFichier {
	
	public static File choixFichierTxt() {
		FileNameExtensionFilter filtre = new FileNameExtensionFilter("Fichiers texte.", "txt");
		JFileChooser dialogue = new JFileChooser(new File("."));
		dialogue.setFileFilter(filtre);
		dialogue.setAcceptAllFileFilterUsed(false);
		dialogue.setDialogTitle("Importation fichier texte match");
		if (dialogue.showOpenDialog(null)== 
				JFileChooser.APPROVE_OPTION) {
			return dialogue.getSelectedFile();				    
		}
		return null;
	}
}
