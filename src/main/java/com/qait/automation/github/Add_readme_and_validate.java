package com.qait.automation.github;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Add_readme_and_validate extends NewTest
{
	WebDriver driver;
	public Add_readme_and_validate(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".btn.btn-sm.btn-primary.flash-action")
	public static WebElement click_readme;
	
	@FindBy(css=".ace_text-input")
	public static WebElement edit_readme;
	
	@FindBy(css=".btn.btn-primary.js-blob-submit")
	public static WebElement click_commit_new_file;
	
	
	public void edit_readme_file_github()
	{
		click_readme.click();
		edit_readme.sendKeys(Keys.END);
		edit_readme.sendKeys(Keys.ENTER);
		edit_readme.sendKeys(TestData.getvalue("changes"));
		click_commit_new_file.click();
	}
	
}
