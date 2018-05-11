package ihm;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import constante.FichierConstante;
import constante.SportConstante;
import traitement.TraitementSport;

@SuppressWarnings("serial")
public class InterfaceUtilisateurMain extends JPanel implements ActionListener{

	
	
	static private final String NEWLINE = "\n";
	JButton openButton;
	JButton traitementButton;
	JButton exportButton;
	JTextArea log;
	JFileChooser choixFichier;
	JTextField cheminFichier;
	JComboBox<String> listeDeroulanteSport;
	File fichier;



	public InterfaceUtilisateurMain() throws IOException {
		super(new BorderLayout());

		//Create the log first, because the action listeners
		//need to refer to it.
		log = new JTextArea(15,30);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		//Create a file chooser
		choixFichier = new JFileChooser();
		choixFichier.setAcceptAllFileFilterUsed(false);
		choixFichier.setDialogTitle("Importation fichier match");

		cheminFichier = new JTextField();
		cheminFichier.setColumns(30);

		//Create the open button.
		openButton = new JButton("Ouvrir un fichier...");
		openButton.addActionListener(this);

		//For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel(); //use FlowLayout
		buttonPanel.add(cheminFichier);
		buttonPanel.add(openButton);
		
		listeDeroulanteSport = new JComboBox<>(SportConstante.listeSport);
		JPanel listeDeroulantePanel = new JPanel();
		listeDeroulantePanel.add(listeDeroulanteSport);
		
		traitementButton = new JButton("Calculer Score");
		traitementButton.addActionListener(this);
		exportButton = new JButton("export structure fichier");
		exportButton.addActionListener(this);
		JPanel panelTraitement = new JPanel();
		panelTraitement.add(traitementButton);
		panelTraitement.add(exportButton);

		//Add the buttons and the log to this panel.
		add(buttonPanel, BorderLayout.PAGE_START);
		add(listeDeroulantePanel, BorderLayout.LINE_START);
		add(panelTraitement);
		add(logScrollPane, BorderLayout.PAGE_END);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE); 
				try {
					creationEtRendreVisibleFenetre();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void creationEtRendreVisibleFenetre() throws IOException {
		//Create and set up the window.
		JFrame frame = new JFrame("Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add content to the window.
		frame.add(new InterfaceUtilisateurMain());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Handle open button action.
		if (e.getSource() == openButton) {
			actionOpen();
		} else if(e.getSource() == traitementButton){
			actionTraiter();			
		} else if(e.getSource() == exportButton){
			ExportStructureIHM.main(null);
		}
		

	}

	private void actionTraiter() {
		log.setText("");
		if(!cheminFichier.getText().equals("")){
			if(!listeDeroulanteSport.getSelectedItem().toString().equals("")){
				log.append("début traitement calcul des scores ..." + NEWLINE);
				lancerTraitement();
			} else {
				log.append("merci de selectionner un sport avant d'essayer de lancer un traitement" + NEWLINE);
			}
		} else {
			log.append("merci de selectionner un fichier"+NEWLINE);
		}
	}

	private void actionOpen() {
		int returnVal = choixFichier.showOpenDialog(InterfaceUtilisateurMain.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fichier = choixFichier.getSelectedFile();
			if(fichier.isDirectory()){
				log.append("Erreur, il s'agit d'un répetoire." + NEWLINE);
			} else {
				cheminFichier.setText(fichier.getPath());
			}				
		} else {
			log.append("Opération annulée par l'utilisateur." + NEWLINE);
		}
		log.setCaretPosition(log.getDocument().getLength());
	}

	private void lancerTraitement() {
		switch(listeDeroulanteSport.getSelectedItem().toString()){
			case "Tennis" :
				if(FichierConstante.TXT.equals(getExtensionFile())){
					log.append(TraitementSport.traitementTennis(fichier) + NEWLINE);
				} else {
					log.append("mauvais format de fichier, selectionn� un fichier .txt");
				}
				break;
			case "Handball":
				if(FichierConstante.XLS.equals(getExtensionFile()) || FichierConstante.XLSX.equals(getExtensionFile())){
					log.append(TraitementSport.traitementHandball(fichier) + NEWLINE);
				} else {
					log.append("mauvais format de fichier, selectionn� un fichier .xls ou .xlsx");
				}
				break;
			default :
				break;
		}
		
	}

	private Object getExtensionFile() {
		return fichier.getName().substring(fichier.getName().lastIndexOf('.'));
	}

}
