package stepDefinitions;
import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import page.LoginPage;


public class LoginStep {

    private WebDriver driver;
    private LoginPage loginPage;


    @Given("el usuario se encuentra en la pagina de inicio de sesion Atlantic City")
    public void inicioSesionAtlantic() {
        this.driver = Hooks.driver;
        this.loginPage = new LoginPage(driver);
        loginPage.navegarLogin();
    }

    @When("ingresa sesion con {string} y {string} correctas")
    public void ingresoCredenciales(String username, String password){
        loginPage.login(username, password);
    }
    @Then("se redirecciona a su perfil de usuario")
    public void ingresoExitoso(){
        Assert.assertTrue("El usuario no pudo ingresar a su perfil de usuario", loginPage.ingresoExitoso());
    }


}
