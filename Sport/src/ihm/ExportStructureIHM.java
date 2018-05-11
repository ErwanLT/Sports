package ihm;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import traitement.TraitementExport;

@SuppressWarnings("serial")
public class ExportStructureIHM extends JPanel implements ActionListener {
	
	JButton tennisButton;
	JButton handBallButton;
	JTextArea log;
	
	public ExportStructureIHM() {
		super(new BorderLayout());
		
		log = new JTextArea(10,30);
		log.setMargin(new Insets(5,5,5,5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		
		tennisButton = new JButton("fichier Tennis");
		tennisButton.addActionListener(this);
		handBallButton = new JButton("fichier Handball");
		handBallButton.addActionListener(this);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(handBallButton);
		buttonPanel.add(tennisButton);
		
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.PAGE_END);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				//Turn off metal's use of bold fonts
				UIManager.put("swing.boldMetal", Boolean.FALSE); 
				creationEtRendreVisibleFenetre();
			}
		});
	}

	private static void creationEtRendreVisibleFenetre() {
		//Create and set up the window.
		JFrame frame = new JFrame("Export fichiers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Add content to the window.
		frame.add(new ExportStructureIHM());

		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		log.setText("");
		if(action.getSource()==tennisButton){
			log.append(TraitementExport.exportTennis());
		} else if(action.getSource() == handBallButton){
			log.append(TraitementExport.exportHandball());
		}

	}

}
