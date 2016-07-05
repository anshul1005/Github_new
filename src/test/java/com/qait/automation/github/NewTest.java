package com.qait.automation.github;

import com.qait.automation.github.Add_readme_and_validate;
import com.qait.automation.github.Browser_Refresh;
import com.qait.automation.github.Cloning;
import com.qait.automation.github.Githubautomate;
import com.qait.automation.github.HomePage;
import com.qait.automation.github.Makenewrepo;
import com.qait.automation.github.Pull_readme_and_validate;
import com.qait.automation.github.Terminal_Launch;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

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
	static Logger logs=Logger.getLogger(NewTest.class);
	
		@BeforeClass
		public void init_Pages() 
		{
			driver=new FirefoxDriver();
			driver.get(TestData.getvalue("url"));
			driver.manage().window().maximize();
			obj=new Githubautomate(driver);
			obj1=new HomePage(driver);
			obj3=new Makenewrepo(driver);
			obj4=new Cloning(driver);
			obj5 = new Terminal_Launch();
			obj6=new Browser_Refresh(driver);
			obj7=new Add_readme_and_validate(driver);
			obj8= new Pull_readme_and_validate();
		}
  
		  @Test
		  public void Test01_Verify_User_Is_Able_To_Login_Into_Github()
		  {
			  Assert.assertTrue(obj.log_in_home_page().equals("GitHub"));
			 
		  }
		  
		  @Test
		  public void Test02_Verify_User_Is_Able_to_Create_New_Repositoy() 
		  {
				Assert.assertTrue(obj1.click_on_repository().equals("Create a New Repository"));
		  }
		  
		  @Test
		  public void Test03_Verify_User_Has_Created_Repository()
		  {
			  String name=obj3.create_repository();
			  String title=obj3.Get_Title();
			  Assert.assertTrue(title.contains(name));
		  }
		  @Test
		  public void Test04_User_Recieved_Url_Of_Repository()
		  {
			  url_clone=obj4.clone_repo();
		  }
		  
		  @Test
		  public void Test05_Cloning_Is_Done_On_Local_Repository() throws IOException
		  {
			  final_word=obj5.File_create(url_clone);
			  clone_msg=obj5.executeCommand();
			  Assert.assertTrue(clone_msg.get(0).contains("Cloning into "));
			  Assert.assertTrue(clone_msg.get(1).contains("[new branch]      master -> master"));
		  }
		  
		  @Test
		  public void Test06_Verify_File_Has_Been_Pushed()
		  {
			  boolean val = obj6.validate_file("code.sh");
			  Assert.assertTrue(val);
		  }
		  
		 @Test
		  public void Test07_Readme_Created_And_pulled()
		  {
			  obj7.edit_readme_file_github();
		  }
		  
		  @Test
		  public void Test08_Verify_Readme_pulled() throws IOException
		  {
			  obj8.File_create(url_clone);
			  int i=0,count=0;
			  clone_ms=obj8.executeCommand();
			  while(clone_ms.get(i)!=null)
			  {
				  if((clone_ms.get(i).contains("file changed")) || (clone_ms.get(i).contains("Updating")))
				  {
					  Assert.assertTrue(true);
					  count++;
					  if(count==2)
						  break;
			      }
				  else
					  i++;
		      }
		  }
		  public void log_info()
		  {
			  logs.debug("hi");
			  logs.info("done");
		  }
}
