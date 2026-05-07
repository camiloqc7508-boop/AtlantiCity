@LoginExitoso
Feature: Login en Atlantic City

  Scenario Outline: Login con crendeciales validas
    Given el usuario se encuentra en la pagina de inicio de sesion Atlantic City
    When ingresa sesion con "<username>" y "<password>" correctas
    Then se redirecciona a su perfil de usuario
    Examples:
      |username               |password        |
      |Testcalimaco34         |Testcalimaco34  |


