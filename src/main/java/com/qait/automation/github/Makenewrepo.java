package com.qait.automation.github;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Makenewrepo extends NewTest 
{
	String name_of_repo;
	WebDriver driver;
	public Makenewrepo (WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	@FindBy(css="#repository_name")
	public static WebElement enterText;
	
	@FindBy(css=".btn.btn-primary.first-in-line")
	public static WebElement button_click;

	
	
	public String create_repository()
	{
		String name_of_repo="new_repo_"+System.currentTimeMillis();
		enterText.sendKeys(name_of_repo);
		button_click.click();
		return name_of_repo;
	}
	
	public String Get_Title()
	{
		return driver.getTitle();
	}

}
