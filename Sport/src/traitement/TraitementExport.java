package traitement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import constante.FichierConstante;
import properties.ReadProperties;

public class TraitementExport {

	public static String exportTennis(){

		String pathToFile = getPathDownload();

		File fichierTennis = new File(pathToFile+"tennis_"+ getDateJour()+FichierConstante.TXT);
		try {
			fichierTennis.createNewFile();
			List<String> lines = ligneFichierTennis();
			FileUtils.writeLines(fichierTennis, lines);
		} catch (IOException e) {
			return "erreur à la création du fichier";
		}

		return fichierTennis.getPath();
	}



	private static List<String> ligneFichierTennis() {
		List<String> contenuFichier = new ArrayList<>();

		contenuFichier.add("***********************************************************************");
		contenuFichier.add("*                       Fichier Tennis                                *");
		contenuFichier.add("*                cette partie sera à supprimer                        *");
		contenuFichier.add("*   Sur chaque ligne indiqué le nom du joueur ayant marqué le point   *");
		contenuFichier.add("***********************************************************************");
		contenuFichier.add("exemple :");
		contenuFichier.add("nom joueur 1");
		contenuFichier.add("nom joueur 1");
		contenuFichier.add("nom joueur 2");
		contenuFichier.add("nom joueur 1");
		contenuFichier.add("celà se traduira à l'écran par :");
		contenuFichier.add("15-0");
		contenuFichier.add("30-0");
		contenuFichier.add("30-15");
		contenuFichier.add("40-15");
		contenuFichier.add("Jeu gagné par : nom joueur 1");
		contenuFichier.add("***********************************************************************");
		return contenuFichier;
	}

	public static String exportHandball(){

		String pathToFile = getPathDownload();

		File fichierHand = new File(pathToFile+"Handball_"+ getDateJour()+FichierConstante.XLSX);
		
		XSSFWorkbook wb = new XSSFWorkbook();
		Sheet feuille1 = wb.createSheet("Equipe_1");
		Sheet feuille2 = wb.createSheet("Equipe_2");
		Sheet feuille3 = wb.createSheet("score");
		
		List<Sheet> listfeuilleEquipe = new ArrayList<>();
		listfeuilleEquipe.add(feuille1);
		listfeuilleEquipe.add(feuille2);
		
		remplirfeuilleEquipe(listfeuilleEquipe);
		
		FileOutputStream fo;
		try{
			fo = new FileOutputStream(fichierHand);
			wb.write(fo);
			fo.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}

		return fichierHand.getPath();
	}

	private static void remplirfeuilleEquipe(List<Sheet> listfeuilleEquipe) {
		
		for (Sheet feuilleEquipe : listfeuilleEquipe) {
			for(int i=0; i<5; i++){
				Row row = feuilleEquipe.createRow(i);
				Cell cell = row.createCell(0);
				cell.setCellValue("nom joueur");
				Cell cell2 = row.createCell(1);
				cell2.setCellValue("poste joueur");
				Cell cell3 = row.createCell(2);
				cell3.setCellValue("numero joueur");
			}
		}		
	}



	private static String getPathDownload() {

		return ReadProperties.getRacineDownloadPath("propSport.properties") + System.getProperty("user.name") + ReadProperties.getRacineDownloadFolder("propertieFileName");
	}

	private static String getDateJour(){

		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Date dateJour = new Date();

		return formatDate.format(dateJour);		
	}

}
