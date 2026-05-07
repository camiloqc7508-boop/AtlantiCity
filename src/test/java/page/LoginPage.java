package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "login")
    private WebElement buttonLogin;
    @FindBy(name = "user")
    private WebElement inputUser;
    @FindBy(name = "password")
    private WebElement inputPass;
    @FindBy(xpath = "//button[normalize-space()='INICIAR SESIÓN']")
    private WebElement buttonIniciarSesion;
    @FindBy(xpath = "//button[.//*[@data-testid='PersonIcon']]")
    private WebElement iconoUser;
    @FindBy(xpath = "//p[normalize-space()='Testcalimaco34']")
    private WebElement usuarioLogo;


    public void navegarLogin(){
        driver.get("https://www.casinoatlanticcity.com/");
    }

    public void login(String username, String password){

        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
            WebElement popup =
                    wait.until(ExpectedConditions.elementToBeClickable(By.id("optiRealclosePopupImage")));
            popup.click();

            System.out.println("Popup cerrado correctamente.");

        } catch (TimeoutException e) {
            System.out.println("Popup no apareció. El flujo continúa.");
        }

        // Click botón LOGIN usando JS
        wait.until(ExpectedConditions.visibilityOf(buttonLogin));
        js.executeScript("arguments[0].click();", buttonLogin);


        inputUser.sendKeys(username);
        inputPass.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(buttonIniciarSesion));
        buttonIniciarSesion.click();


        wait.until(ExpectedConditions.elementToBeClickable(iconoUser));
        iconoUser.click();
    }

    public boolean ingresoExitoso(){
        wait.until(ExpectedConditions.visibilityOf(usuarioLogo));
        return usuarioLogo.isDisplayed();
    }






}
