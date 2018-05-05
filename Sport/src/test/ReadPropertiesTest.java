package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import properties.ReadProperties;

public class ReadPropertiesTest {

	
	@Test
	public void readRootTest(){
		assertEquals("C:\\Users\\", ReadProperties.getRacineDownloadPath("propSport.properties"));
	}
	
	@Test
	public void readFolderTest(){
		assertEquals("\\Downloads\\", ReadProperties.getRacineDownloadFolder("propSport.properties"));
	}
	
	@Test(expected=NullPointerException.class)
	public void propertieFileDontExist(){
		ReadProperties.getRacineDownloadFolder("toto");
	}
	
	@Test(expected=NullPointerException.class)
	public void propertieFileDontExist2(){
		ReadProperties.getRacineDownloadPath("toto");
	}
	
}
