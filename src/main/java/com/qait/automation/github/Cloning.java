package com.qait.automation.github;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Cloning extends NewTest
{
		WebDriver driver;
		
		
		public Cloning(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}

		@FindBy(css=".form-control.js-git-clone-help-field.url-field.js-zeroclipboard-target")
		public static WebElement url;
				
		
		public String clone_repo() 
		{ 
			String clone_url=url.getAttribute("value");
			return clone_url;
		}
		
}