/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author F3340791
 */
public class BookStoreFileReader {
    
    public static String readXmlFile(File file){
        
                BufferedReader br = null;
		FileReader fr = null;
                StringBuffer sb = null;
		try {
		      String sCurrentLine;
			br = new BufferedReader(new FileReader(file));
                        sb = new StringBuffer();
			while ((sCurrentLine = br.readLine()) != null) {
				sb.append(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
                               
				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
        
        
        return sb.toString();
    }
    
}
