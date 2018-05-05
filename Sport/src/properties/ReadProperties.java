package properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

	public static void main(final String[] args) {

		final Properties prop = new Properties();
		InputStream path = null;
		
		try {
			path = ReadProperties.class.getResourceAsStream("propSport.properties");

			// load a properties file
			prop.load(path);

			// get the property value and print it out
			System.out.println(prop.getProperty("path.file.download"));
			
		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (path != null) {
				try {
					path.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
	public static String getRacineDownloadPath(String propertiesFileName){
		final Properties prop = new Properties();
		InputStream path = null;
		
		String root = "";
		try {
			path = ReadProperties.class.getResourceAsStream(propertiesFileName);

			// load a properties file
			prop.load(path);

			// get the property value and print it out
			root = prop.getProperty("path.file.download.root");
			
		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (path != null) {
				try {
					path.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return root;
	}
	
	public static String getRacineDownloadFolder(String propertiesFileName){
		final Properties prop = new Properties();
		InputStream path = null;
		
		String folder = "";
		try {
			path = ReadProperties.class.getResourceAsStream(propertiesFileName);

			// load a properties file
			prop.load(path);

			// get the property value and print it out
			folder = prop.getProperty("path.file.download.folder");
			
		} catch (final IOException ex) {
			ex.printStackTrace();
		} finally {
			if (path != null) {
				try {
					path.close();
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return folder;
	}
}
