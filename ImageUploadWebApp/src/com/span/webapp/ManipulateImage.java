package com.span.webapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.codec.binary.Base64;






public class ManipulateImage {
	 public static byte[] convertStringtoImage(String encodedImageStr,    String fileName) {
		 byte[] imageByteArray = Base64.decodeBase64(encodedImageStr);;
	        try {
	            // Decode String using Base64 Class
	        	 // URL url = new URL("http://www.amrood.com/index.htm?language=en#j2se");
	        	//List<URL> urls = new ArrayList<URL>();
	            imageByteArray = Base64.decodeBase64(encodedImageStr); 
	 
	            // Write Image into File system - Make sure you update the path
	            FileOutputStream imageOutFile = new FileOutputStream("D:/AndroidStudioProjects/images/" + fileName);
	            imageOutFile.write(imageByteArray);
	 
	            imageOutFile.close();
	 
	            System.out.println("Image Successfully Stored");
	        } catch (FileNotFoundException fnfe) {
	            System.out.println("Image Path not found" + fnfe);
	        } catch (IOException ioe) {
	            System.out.println("Exception while converting the Image " + ioe);
	        }
	        return imageByteArray;
	 
	    }
	 public static String[]  display(){
		
		 File folder = new File("D:/AndroidStudioProjects/images/");
		 File[] listOfFiles = folder.listFiles();
		 String [] k = new String[listOfFiles.length];
		 Arrays.fill(k,"none");

		     for (int i = 0; i < listOfFiles.length; i++) {
		       if (listOfFiles[i].isFile()) {
		         System.out.println("File " + listOfFiles[i].getName());
		         k[i]="File " + listOfFiles[i].getName();
		       } else if (listOfFiles[i].isDirectory()) {
		         System.out.println("Directory " + listOfFiles[i].getName());
		       }
		      
		     }
		     return k;
	 }

}
