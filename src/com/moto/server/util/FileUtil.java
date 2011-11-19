package com.moto.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static boolean copy(String fileFrom, String fileTo) {  
        try {
            FileInputStream in = new java.io.FileInputStream(fileFrom);  
            
            FileOutputStream out = new FileOutputStream(fileTo);  
            byte[] bt = new byte[1024];  
            int count;  
            while ((count = in.read(bt)) > 0) {  
                out.write(bt, 0, count);  
            }  
            in.close();  
            out.close();  
            return true;  
        } catch (IOException ex) {  
            return false;  
        }  
    }  
    public static byte[] fileToByte(File file)
    {
    	FileInputStream fis=null;
		byte[] b = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(b);
			return b;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    }
}
