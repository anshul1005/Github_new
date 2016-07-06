package com.qait.automation.github;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Terminal_Launch extends NewTest
{	
	File file;
	String source;
	String fn;
	String usr=TestData.getvalue("username")+File.separator;
	String os=System.getProperty("os.name");
	public String File_create(String url) throws IOException
	{ 
		String first = StringUtils.substringBefore(url, ".git");
		String final_word = StringUtils.substringAfter(first,usr);
		 if(os.contains("Linux"))
	        {
			 fn = (TestData.getvalue("file_name_linux"));
	        }
		 if(os.contains("Windows"))
	        {
			 fn = (TestData.getvalue("file_name_windows"));
	        }
		 
		file = new File(System.getProperty("user.dir")+File.separator+fn);
		FileWriter fileWritter = new FileWriter(file.getName(),true);
		file.setExecutable(true);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        if(os.contains("Linux"))
        {
        	url = url.replace("https://github.com/","git@github.com:");
        	source = System.getProperty("user.dir")+File.separator+(TestData.getvalue("file_name_linux"));
	        bufferWritter.write("#!/bin/sh");
	        bufferWritter.newLine();
	        bufferWritter.write("cd");
	        bufferWritter.newLine();
	        bufferWritter.write("git clone "+url);
	        bufferWritter.newLine();
	        bufferWritter.write("cp "+ source + " "+final_word);
	        bufferWritter.newLine();
	        bufferWritter.write("cd "+final_word+"/");
	        bufferWritter.newLine();
	        bufferWritter.write("git add -A");
	        bufferWritter.newLine();
	        bufferWritter.write("git commit -m \""+(TestData.getvalue("commitMessage"))+"\"");
	        bufferWritter.newLine();
	        bufferWritter.write("git push origin master");
	        bufferWritter.newLine();
	        bufferWritter.close();
        }
        if(os.contains("Windows"))
        {
        	url = url.replace("git@github.com:","git@github.com:");
        	source = System.getProperty("user.dir")+File.separator+(TestData.getvalue("file_name_windows"));
        	bufferWritter.write("cd \\");
	        bufferWritter.newLine();
	        bufferWritter.write("git clone "+url);
	        bufferWritter.newLine();
	        bufferWritter.write("copy "+ source+ " "+final_word);
	        bufferWritter.newLine();
	        bufferWritter.write("cd "+final_word);
	        bufferWritter.newLine();
	        bufferWritter.write("git add -A");
	        bufferWritter.newLine();
	        bufferWritter.write("git commit -m \""+(TestData.getvalue("commitMessage"))+"\"");
	        bufferWritter.newLine();
	        bufferWritter.write("git push origin master");
	        bufferWritter.newLine();
	        bufferWritter.close();
        }
        return final_word;
     }
	public List<String> executeCommand()
	{
		String line1 = null;
		 List<String> list = new ArrayList<String>();
		try
		{
			
			ProcessBuilder builder = new ProcessBuilder(source);
			builder.redirectErrorStream(true);
			Process process = builder.start();
			InputStream is = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				while ((line1=reader.readLine()) != null) 
				{
					list.add(line1)	;
				}
		}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			finally 
			{
				file.delete();
				return list;
	        }
      }
}

