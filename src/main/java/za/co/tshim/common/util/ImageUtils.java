/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.tshim.util;

import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author F3340791
 */
public class ImageUtils {
    
    public static byte[] getByteArray(String fileName){
          //File file = new File("C:\test.png");
          File file = new File(fileName);
          byte[] imageData = new byte[(int) file.length()];
 
          try {
                  FileInputStream fileInputStream = new FileInputStream(file);
                  fileInputStream.read(imageData);
                  fileInputStream.close();
          } catch (Exception e) {
                  e.printStackTrace();
          }
 
          return imageData;

    }
    
}
