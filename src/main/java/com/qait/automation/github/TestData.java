package com.qait.automation.github;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class TestData 
{
	
	public static String getvalue(String s)
	{
		
		final String fileName="src"+File.separator+"test"+File.separator+"java"+File.separator+"code.yml";
		Yaml yaml = new Yaml();
		Object obj;
		Map result = null;
		try 
		{
			InputStream ios = new FileInputStream(new File(fileName));

			// Parse the YAML file and return the output as a series of Maps and Lists
			obj =yaml.load(ios);
			result=(Map)obj;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		String str = result.get(s).toString();

		return str;
	}
}
