package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ReadFile{
	public static byte[] readFile (String path) {
		
		File file = new File(path);
		FileInputStream fis;
		try {
			fis = new  FileInputStream(file);
			byte[] b;
			b = new byte[fis.available()];
			fis.read(b);
			return b;
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
}