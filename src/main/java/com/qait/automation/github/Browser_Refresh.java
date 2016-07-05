package com.qait.automation.github;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Browser_Refresh extends NewTest
{
	String text;
	String file;
	WebDriver driver;
	public Browser_Refresh(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void refresh_and_validate()
	{
		driver.navigate().refresh();
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FindBy(css=".num.text-emphasized")
	public static WebElement commit;
			
	@FindBy(css=".js-navigation-open")
	public static WebElement file_present;
	
	public boolean validate_file(String file_name) 
	{ 
		
		try
		{
			Thread.sleep(2000);
			driver.navigate().refresh();
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		text=commit.getText();
		file=commit.getText();
		if(text.isEmpty() && !file.equals(file_name) )
		{
			
			return false;
		}
		else 
		{
			
			return true;
		}	
	}
}
