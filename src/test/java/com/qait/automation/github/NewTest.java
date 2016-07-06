package com.qait.automation.github;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.SimpleFormatter;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class NewTest 
{
	WebDriver driver;
	Githubautomate obj;
	HomePage obj1;
	Makenewrepo obj3;
	Cloning obj4;
	Terminal_Launch obj5;
	Browser_Refresh obj6;
	Add_readme_and_validate obj7;
	Pull_readme_and_validate obj8;
	String url_clone;
	String final_word;
	List<String> clone_msg;
	List<String> clone_ms;
	private static final Logger logger = Logger.getLogger(NewTest.class.getName());
	Handler handler = null;

	@BeforeClass
	public void init_Pages() throws SecurityException, IOException 
	{
		if(System.getProperty("os.name").contains("Windows"))
		{
			File bi = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary ff = new FirefoxBinary(bi);
			FirefoxProfile fp = new FirefoxProfile();
			driver = new FirefoxDriver(ff, fp);
		}
		else
		{
			driver=new FirefoxDriver();
		}
		driver.get(TestData.getvalue("url"));
		driver.manage().window().maximize();
		obj = new Githubautomate(driver);
		obj1 = new HomePage(driver);
		obj3 = new Makenewrepo(driver);
		obj4 = new Cloning(driver);
		obj5 = new Terminal_Launch();
		obj6 = new Browser_Refresh(driver);
		obj7 = new Add_readme_and_validate(driver);
		obj8 = new Pull_readme_and_validate();
		String workDir = System.getProperty("user.dir");
		FileHandler  fh = new FileHandler(workDir +"/file.log");  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);

	}

	@Test
	public void Test01_Verify_User_Is_Able_To_Login_Into_Github() 
	{
		Assert.assertTrue(obj.log_in_home_page().equals("GitHub"));
		logger.info("user has successfully login into github account ");
	}

	@Test
	public void Test02_Verify_User_Is_Able_to_Create_New_Repositoy()
	{
		Assert.assertTrue(obj1.click_on_repository().equals("Create a New Repository"));
		logger.info("user is able to create a repository ");
	}

	@Test
	public void Test03_Verify_User_Has_Created_Repository() 
	{
		String name = obj3.create_repository();
		String title = obj3.Get_Title();
		Assert.assertTrue(title.contains(name));
		logger.info("user has successfully created a new repository ");
	}

	@Test
	public void Test04_User_Recieved_Url_Of_Repository() 
	{
		url_clone = obj4.clone_repo();
		logger.info("user has recieved a url of cloning ");
	}

	@Test
	public void Test05_Cloning_Is_Done_On_Local_Repository() throws IOException {
		final_word = obj5.File_create(url_clone);
		clone_msg = obj5.executeCommand();
		int j=0;
		while (clone_msg.get(j) != null)
		{
			if ((clone_msg.get(j).contains("Cloning into"))) 
			{
				Assert.assertTrue(true);
					break;
			} else
				j++;
		}
		logger.info("user has successfully clone a local repository ");
	}

	@Test
	public void Test06_Verify_File_Has_Been_Pushed() 
	{
		obj6.refresh_and_validate();
		boolean val = obj6.validate_file("code.sh");
		Assert.assertTrue(val);
		logger.info("file has been pushed to the github ");
	}

	@Test
	public void Test07_Readme_Created_And_pulled() 
	{
		obj7.edit_readme_file_github();
		logger.info("Readme has been created");
	}

	@Test
	public void Test08_Verify_Readme_pulled() throws IOException 
	{
		obj8.File_create(url_clone);
		int i = 0, count = 0;
		clone_ms = obj8.executeCommand();
		while (clone_ms.get(i) != null) 
		{
			if ((clone_ms.get(i).contains("file changed")) || (clone_ms.get(i).contains("Updating"))) 
			{
				Assert.assertTrue(true);
				count++;
				if (count == 2)
					break;
			} else
				i++;
		}
		logger.info("Readme has been pulled to the local repository");
	}
	@Test
	public void gitApi() throws InterruptedException
	{
		Thread.sleep(2000);
		GitHubAPI api=new GitHubAPI();
		api.gitApiMethod();
	}
}

