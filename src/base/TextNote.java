package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TextNote extends Note{
	
	private String content;
	
	public TextNote(String title)
	{
		super(title);
	}
	
	public TextNote(String title, String content)
	{
		super(title);
		this.content = new String(content);
	}
	
	public TextNote(File f)
	{
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath)
	{
		String result = "";
		try
		{
			FileInputStream fis = new FileInputStream(absolutePath);
			InputStreamReader isr = new InputStreamReader(fis);
			int c = 0;
			while((c = isr.read())!=-1)
			{
				result = result + String.valueOf((char)c);
			}
			isr.close();
			fis.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder)
	{
		if(pathFolder.equals(""))
		{
			pathFolder = ".";
		}
		
		String fileName = this.getTitle();
		fileName = fileName.replace(' ', '_');
		fileName = fileName.concat(".txt");
		try {
			File file = new File(pathFolder + File.separator+ fileName);
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);		
			bw.close();
			fw.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String getContent()
	{
		return content;
	}
	
	public void setContent(String newContent)
	{
		content = newContent;
	}
}
