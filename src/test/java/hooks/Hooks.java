package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Hooks {

    public static WebDriver driver;
    // Definimos el logger profesional en lugar de System.out.println
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario){
        log.info(">>> Iniciando escenario: "+ scenario.getName());
        if(driver == null){
            log.info("Configurando ChromeOptions en modo incógnito...");
            ChromeOptions options = new ChromeOptions(); /*   Objeto de configuración para Chrome y va a permitir
            cómo se abre el navegador, ya sea en incógnito, headless, desactivar extensión, etc.    */
            options.addArguments("--incognito");
            // El modo incognito provoca que no haya caché, cookies o sesiones previas; evita falsos positivos por datos guardados

            driver = new ChromeDriver(options); // Esta línea dice "Crea un navegador Chrome real, usnado estas configuraciones y guárdalo en la variable driver
            driver.manage().window().maximize();
            log.info("Navegador Chrome iniciado correctamente");
        }
    }
    @After
    public void tearDown(Scenario scenario){
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
