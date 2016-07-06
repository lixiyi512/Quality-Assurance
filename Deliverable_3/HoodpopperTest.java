import static org.junit.Assert.*;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;




public class HoodpopperTest {

	static WebDriver driver = new HtmlUnitDriver();
	//Trying to test the element which uses Javascript by FirefoxDirver
	//WebDriver driver = new FirefoxDriver();
	
	// Start at the page for hoodpopper for each test
	@Before
	public void setUp() throws Exception {
		driver.get("http://lit-bayou-7912.herokuapp.com/");
	}
	
	/**
	 * User Story 1
	 * As a programmer,
	 * I would like to tokenize my Ruby code,
	 * So that I can understand how tokenizer separates my code into tokens.
	 * @author Xiyi Li
	 *
	 */
	
	//Scenario 1
	//Given I am on the Main Page
	//When I want to write code
	//Then I should see that a textarea appears for me to write code
	@Test
	public void testTextarea(){
		try{
			driver.findElement(By.id("code_code"));
		} catch(NoSuchElementException nseex){
			fail();
		}
	}
	
	//Scenario 2
	//Given I am on the Main Page
	//When I enter an expression: 
	//a = 1 + 1
	//b = 3
	//In the textarea and click "tokenize"
	//Then I should see variables are tokenized into on_ident
	//Operators are tokenized into on_op
	//And newline is tokenized into on_nl
	@Test
	public void testTokenizeExpression(){
			WebElement enter = driver.findElement(By.id("code_code"));
			String code = "a = 1 + 1" + "\n" + "b = 3";
			enter.sendKeys(code);
			WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[1]"));
			tokenize.submit();
			try{
				WebElement tokens = driver.findElement(By.cssSelector("code"));
				String s = tokens.getText();
				assertTrue(s.contains("on_ident"));//Variable a and b are identifiers
				assertTrue(s.contains("on_op"));//"=" and "+" are operators
				assertTrue(s.contains("on_nl"));//Newline
			} catch(NoSuchElementException nseex){
				fail();
			}
	}
	
	//Scenario 3
	//Given I am on the Main Page
	//When I enter an expression:
	//puts "Hello World!"
	//In the textarea and click "tokenize"
	//Then I should see that "puts" is tokenized into on_indent 
	//And space is tokenized into on_sp
	@Test
	public void testTokenizeExpression2(){
			WebElement enter = driver.findElement(By.id("code_code"));
			String code = "puts \"Hello World!\"";
			enter.sendKeys(code);
			WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[1]"));
			tokenize.submit();
			try{
				WebElement tokens = driver.findElement(By.cssSelector("code"));
				String s = tokens.getText();
				assertTrue(s.contains("on_ident"));//Function puts is identifier
				assertTrue(s.contains("on_sp"));//Spaces 
			} catch(NoSuchElementException nseex){
				fail();
			}
	}

	//Scenario 4
	//Given I am on the Tokenize Operation page
	//When I click "Back" on the button left of the page
	//Then I should go back to the Main Page
//	@Test
//	public void testBack(){
//		try{
//		WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[1]"));
//		tokenize.submit();
//		((JavascriptExecutor)driver).executeScript("arguments[0].click", driver.findElement(By.linkText("Back")));
//		WebElement s3 = driver.findElement(By.cssSelector("html"));
//		System.out.println(s3.getText());
//		//If I am back to Main Page, I should see the textarea 
//		WebElement textarea = driver.findElement(By.id("code_code"));
//	    Assert.assertEquals(true, textarea.isDisplayed());
//		} catch(NoSuchElementException nseex){
//			fail();
//		}
//	}
	

	/**
	 * User Story 2
	 * As a programmer,
	 * I would like to parse my Ruby code,
	 * So that I can understand how parser puts tokens into an abstract syntax tree(AST).
	 * @author Xiyi Li
	 *
	 */
	//Scenario 1
	//Given I am on the Main Page
	//When I enter an expression: 
	//a = 5 
	//b = 3 * a
	//in the textarea and click "parse"
	//Then I should see the non-whitespace tokens(eg.a,*,b,5,3) should appear on the parse tree
	//And whitespace tokens should not appear
	@Test
	public void testParseExpression(){
		WebElement enter = driver.findElement(By.id("code_code"));
		String code = "a = 5" + "\n" + "b = 3 * a";
		enter.sendKeys(code);
		WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
		tokenize.submit();
		try{
			WebElement tokens = driver.findElement(By.cssSelector("code"));
			String s = tokens.getText();
			assertTrue(s.contains("a"));//non-whitespace token: a
			assertFalse(s.contains("[1, 1] "));//first whitespace shows up at first line, second position, whose position in the array is [1,1]	
		} catch(NoSuchElementException nseex){
			fail();
		}
	}
	
	//Scenario 2
	//Given I am on the Main Page
	//When I enter an expression: 
	//puts "Hello World!"
	//in the textarea and click "parse"
	//Then I should see "puts" appears on the parse tree
	@Test
	public void testParseExpression2(){
		WebElement enter = driver.findElement(By.id("code_code"));
		String code = "puts \"Hello World!\"";
		enter.sendKeys(code);
		WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
		tokenize.submit();
		try{
			WebElement tokens = driver.findElement(By.cssSelector("code"));
			String s = tokens.getText();
			assertTrue(s.contains("puts"));//puts is non-whitespace token
		} catch(NoSuchElementException nseex){
			fail();
		}
	}
	
	//Scenario 3
	//Given I am on the Main Page
	//When I click "parse"
	//I should see "Parse Operation" showing on the header
	@Test
	public void testParseHeader(){
		WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[2]"));
		tokenize.submit();
		try{
			WebElement tokens = driver.findElement(By.cssSelector("h1"));
			String s = tokens.getText();
			assertTrue(s.contains("Parse Operation"));//puts is non-whitespace token
		} catch(NoSuchElementException nseex){
			fail();
		}
	}
	/**
	 * User Story 3
	 * As a programmer,
	 * I would like to compile my Ruby code,
	 * So that I can understand how compiler writes my code into actual machine-level instruction.  
	 * @author Xiyi Li
	 *
	 */
	//Scenario 1
	//Given I am on the Main Page
	//When I enter an expression: 
	//puts "Hello World!"
	//in the textarea and click "compile"
	//Then I should see function "puts" compiled into putstring operation
	@Test
	public void testCompilePuts(){
		WebElement enter = driver.findElement(By.id("code_code"));
		String code = "puts \"Hello World!\"";
		enter.sendKeys(code);
		WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		tokenize.submit();
		try{
			WebElement tokens = driver.findElement(By.cssSelector("code"));
			String s = tokens.getText();
			assertTrue(s.contains("putstring"));//compile puts into putstring YARY operation
		} catch(NoSuchElementException nseex){
			fail();
		}
	}
	
	//Scenario 2
	//Given I am on the Main Page
	//When I enter an expression:
	//a = 1 + 1
	//in the textarea and click "compile"
	//Then I should see function "+" compiled into opt_plus operation
	//And putobject operation is used to put 1 and 1 together
	@Test
	public void testCompilePlus(){
		WebElement enter = driver.findElement(By.id("code_code"));
		String code = "a = 1 + 1";
		enter.sendKeys(code);
		WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		tokenize.submit();
		try{
			WebElement tokens = driver.findElement(By.cssSelector("code"));
			String s = tokens.getText();
			assertTrue(s.contains("opt_plus"));//compile "+" into opt_plus YARY operation
			assertTrue(s.contains("putobject"));//1
		} catch(NoSuchElementException nseex){
			fail();
		}
	}
	
	//Scenario 3
	//Given I am on the Main Page
	//When I enter an expression:
	//b = 5 - 4
	//in the textarea and click "compile"
	//Then I should see function "-" compiled into opt_minus operation
	@Test
	public void testCompileMinus(){
		WebElement enter = driver.findElement(By.id("code_code"));
		String code = "b = 5 - 4";
		enter.sendKeys(code);
		WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		tokenize.submit();
		try{
			WebElement tokens = driver.findElement(By.cssSelector("code"));
			String s = tokens.getText();
			assertTrue(s.contains("opt_minus"));//compile "-" into opt_minus YARY operation
		} catch(NoSuchElementException nseex){
			fail();
		}
	}
	
	//Scenario 4
	//Given I am on the Main Page
	//When I enter an expression:
	//a = 3 * 4
	//in the textarea and click "compile"
	//Then I should see function "*" compiled into opt_mult operation
	@Test
	public void testCompileMult(){
		WebElement enter = driver.findElement(By.id("code_code"));
		String code = "a = 3 * 4";
		enter.sendKeys(code);
		WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		tokenize.submit();
		try{
			WebElement tokens = driver.findElement(By.cssSelector("code"));
			String s = tokens.getText();
			assertTrue(s.contains("opt_mult"));//compile "*" into opt_mult YARY operation
		} catch(NoSuchElementException nseex){
			fail();
		}
	}
	
	//Scenario 5
	//Given I am on the Main Page
	//When I enter an expression:
	//a = 10 / 5
	//in the textarea and click "compile"
	//Then I should see function "/" compiled into opt_div operation
	@Test
	public void testCompileDiv(){
		WebElement enter = driver.findElement(By.id("code_code"));
		String code = "a = 10 / 5";
		enter.sendKeys(code);
		WebElement tokenize = driver.findElement(By.xpath("(//input[@name='commit'])[3]"));
		tokenize.submit();
		try{
			WebElement tokens = driver.findElement(By.cssSelector("code"));
			String s = tokens.getText();
			assertTrue(s.contains("opt_div"));//compile "/" into opt_div YARY operation
		} catch(NoSuchElementException nseex){
			fail();
		}
	}
}
