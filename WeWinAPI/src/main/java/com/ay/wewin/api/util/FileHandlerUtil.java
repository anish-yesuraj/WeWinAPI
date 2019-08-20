package com.ay.wewin.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileHandlerUtil {
	
	@Autowired
	private CommonProps properties;
	
	Logger log = LoggerFactory.getLogger(FileHandlerUtil.class);

	public String readImageFromServer (String imageName)
	{
		String imageSrc = "";
		
		log.trace("readImageFromServer >>> : "+imageName);
		
		if (Util.isNotEmpty(imageName))
		{
			String imageFileNameWithPath = giveMeImageFileNameWithPath(imageName);
			
			FileInputStream inFile = null;
					
			try 
			{
				File imageFile = new File(imageFileNameWithPath);
				
				if (imageFile.exists())
				{
					// Reading a Image file from file system
					inFile = new FileInputStream(imageFile);
					byte imageData[] = new byte[(int) imageFile.length()];
					inFile.read(imageData);
					
					// Converting Image byte array into Base64 String
					imageSrc = "data:image/jpg;base64,".concat(Base64.getEncoder().encodeToString(imageData));
				}
			} catch (FileNotFoundException e) {
				log.error("FileNotFoundException while reading file ("+imageFileNameWithPath+") from disk!");
				e.printStackTrace();
			} catch (IOException e) {
				log.error("IOException while reading file ("+imageFileNameWithPath+") from disk!");
				e.printStackTrace();
			} finally {
		        if (inFile != null) {
		            try {
		            	inFile.close();
		            } catch (Exception e) {
		            	log.warn("Cannot close inputStream after reading file from disk!");
		            	e.printStackTrace();
		            }
		        }
			}
		}
		
		return imageSrc;
	}
	
	public void createOrBackupImageInServer(String imageSrc, String imageName)
	{
		log.trace("imageSrc : "+imageSrc);
		log.trace("imageName : "+imageName);
		if (Util.isNotEmpty(imageSrc) && Util.isNotEmpty(imageName))
		{
			FileOutputStream outFile = null;
			
			imageSrc = imageSrc.substring(imageSrc.indexOf(",")+1);
			
			byte[] decodedBytes = Base64.getDecoder().decode(imageSrc.getBytes());
			
			String imageFileNameWithPath = giveMeImageFileNameWithPath(imageName);
			
			try 
			{
				File imageFile = new File(imageFileNameWithPath);
				
				if (imageFile.exists())
				{
					backupFile(imageFile, imageName);
				} 
				else 
				{
					checkAndCreateParentDirs(imageFile);
				}
				
				imageFile.createNewFile();
				
				outFile = new FileOutputStream(imageFile, false);
				outFile.write(decodedBytes);
				
			} catch (FileNotFoundException e) {
				log.error("FileNotFoundException while writing file ("+imageFileNameWithPath+") to disk!");
				e.printStackTrace();
			} catch (IOException e) {
				log.error("IOException while writing file ("+imageFileNameWithPath+") to disk!");
				e.printStackTrace();
			} finally {
		        if (outFile != null) {
		            try {
		            	outFile.close();
		            } catch (Exception e) {
		            	log.warn("Cannot close outputStream after writing file to disk!");
		            	e.printStackTrace();
		            }
		        }
			}
		}
		else if (Util.isEmpty(imageSrc) && Util.isNotEmpty(imageName)) /** To backup the file if the image is removed during update. **/
		{
			String imageFileNameWithPath = giveMeImageFileNameWithPath(imageName);
			File imageFile = new File(imageFileNameWithPath);
			if (imageFile.exists())
			{
				backupFile(imageFile, imageName);
			} 			
		}
			
	}
	
	
	private void backupFile (File imageFile, String imageName)
	{
		File backupFile = new File(giveMeImageFileNameWithBackupPath(imageName));
		checkAndCreateParentDirs(backupFile);
		imageFile.renameTo(backupFile);		
	}
	
	private void checkAndCreateParentDirs(File inFile)
	{
		if (!inFile.getParentFile().exists())
		{
			inFile.getParentFile().mkdirs();
		}		
	}
	
	public String giveMeImageFileNameWithPath(String imageName)
	{
		String imagePath = "";
		
		if (Util.isNotEmpty(imageName))
		{
			if (imageName.startsWith("Q"))
			{
				//TODO introduce logic to pick folder according to the filename range
				imagePath = properties.getQuestionImagePath().concat(imageName).concat(properties.getImageExtension());
			}
			else if (imageName.startsWith("A"))
			{
				//TODO introduce logic to pick folder according to the filename range
				imagePath = properties.getAnswerImagePath().concat(imageName).concat(properties.getImageExtension());
			}
		}
		return imagePath;
	}

	public String giveMeImageFileNameWithBackupPath(String imageName)
	{
		String imagePath = "";
		
		if (Util.isNotEmpty(imageName))
		{
			if (imageName.startsWith("Q"))
			{
				imagePath = properties.getQuestionImageBackupPath().concat(imageName).concat(properties.getImageExtension().concat("_"+(new Date()).getTime()+"_BK").concat(properties.getImageExtension()));
			}
			else if (imageName.startsWith("A"))
			{
				imagePath = properties.getAnswerImageBackupPath().concat(imageName).concat(properties.getImageExtension().concat("_"+(new Date()).getTime()+"_BK").concat(properties.getImageExtension()));
			}
		}
		return imagePath;
	}
}
