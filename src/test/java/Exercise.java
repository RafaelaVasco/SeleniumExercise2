import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Exercise extends Base {
    @BeforeTest
    public void initialize() {
        driver = initializeDriver();
        driver.get("https://demoqa.com/buttons");
        removeAdds();
    }

    @Test
    public void test() {
        // >> Double click button
        Actions action = new Actions(driver);
        WebElement doubleClick_button = driver.findElement(By.id("doubleClickBtn"));
        action.doubleClick(doubleClick_button).perform();

        String expectedDoubleClick_message = "You have done a double click";
        String doubleClick_message = driver.findElement(By.id("doubleClickMessage")).getText();
        System.out.println("Is Double Click Message correct? -> " + doubleClick_message.equals(expectedDoubleClick_message));
        Assert.assertTrue(doubleClick_message.equals(expectedDoubleClick_message));

        // >> Right click button
        WebElement rightClick_button = driver.findElement(By.id("rightClickBtn"));
        action.contextClick(rightClick_button).perform();

        String expectedRightClick_message = "You have done a right click";
        String rightClick_message = driver.findElement(By.id("rightClickMessage")).getText();
        System.out.println("Is Right Click Message correct? -> " + rightClick_message.equals(expectedRightClick_message));
        Assert.assertTrue(rightClick_message.equals(expectedRightClick_message));

        // >> Click button
        WebElement click_button = driver.findElement(By.xpath("//*[text() = 'Click Me']"));
        click_button.click();

        String expectedClick_message = "You have done a dynamic click";
        String click_message = driver.findElement(By.id("dynamicClickMessage")).getText();
        System.out.println("Is Click Message correct? -> " + click_message.equals(expectedClick_message));
        Assert.assertTrue(click_message.equals(expectedClick_message));
    }

    private void removeAdds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelectorAll('iframe')\n" +
                "  .forEach(iframe => iframe.remove());");
        js.executeScript("document.querySelectorAll('[id^=' + 'google_ads_iframe_' + ']')\n" +
                "  .forEach(iframe => iframe.remove());");
        js.executeScript("document.querySelectorAll('[id^=' + 'adplus-anchor' + ']')\n" +
                "  .forEach(iframe => iframe.remove());");
        js.executeScript("document.getElementById('fixedban').remove();");
    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }
}
