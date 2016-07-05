package com.qait.automation.github;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;

public class HomePage extends NewTest
{
WebDriver driver;
public HomePage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver,this);
	
}

	//To create repository
	@FindBy(css=".btn.btn-sm.btn-primary")
	public static WebElement click_button;
	
	public String click_on_repository()
	{
		click_button.click();
		return driver.getTitle();
	}
	
}
