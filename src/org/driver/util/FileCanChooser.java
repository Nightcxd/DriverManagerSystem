package org.driver.util;

import java.io.File;

public class FileCanChooser {
	 public boolean accept(File file) {      
	       String name = file.getName();      
	       return(name.toLowerCase().endsWith(".gif")||   
	                name.toLowerCase().endsWith(".jpg")||   
	                name.toLowerCase().endsWith(".bmp")||   
	                name.toLowerCase().endsWith(".png")||   
	                name.toLowerCase().endsWith(".jpeg"));   
	   }   
	   public String getDescription() {   
	     return "图片文件：.gif、 .jpg、 .bmp、 .png、 .jpeg";   
	   }   
}
