package testng_package;

import org.testng.annotations.Test;

import testng_package.Amazon;
import testng_package.Flipkart;

public class PriceCompare {
	public static float convertFloat(String pstring) {
		pstring = pstring.replace(",", "");
		float price = Float.parseFloat(pstring);
		return price;
	}
	
  @Test
  public void compare() throws InterruptedException {
	  String iPhoneModelKey = "iPhone XR (64GB) - Yellow";
		//This program will only work for Chrome Driver. User need to modify the code to make it work with other browser
		String driverPath = "C:\\work\\";
		
		Amazon amazon_price = new Amazon();
		amazon_price.initWebDriver(driverPath);
		amazon_price.searchItem(iPhoneModelKey);
		// Pass amazon specific product name
		amazon_price.selectItem("Apple iPhone XR (64GB) - Yellow");
		// Pass amazon price filed class name to get the price
		String p1 = amazon_price.getPriceXpath(".//*[@class='a-size-medium a-color-price priceBlockBuyingPriceString']");
		amazon_price.endSession();
		
		p1 = p1.substring(2,p1.length());	
		float pr1 = convertFloat(p1);
		
		Flipkart f_price = new Flipkart();
	    f_price.initWebDriver(driverPath);
	    f_price.searchItem(iPhoneModelKey);
	    // Pass flipkart specific product name
	    f_price.selectItem("Apple iPhone XR (Yellow, 64 GB)");
	    String p2 = f_price.getPriceXpath(".//*[@class='_1vC4OE _3qQ9m1']");
	    f_price.endSession();
	    
		p2 = p2.substring(1,p2.length());
		float pr2 = convertFloat(p2);

	    if (pr1 < pr2) {
	    	System.out.println("Final price on the product: " + pr1);
	    } else {
	    	System.out.println("Final price on the product: " + pr2);
	    }
  }

}
