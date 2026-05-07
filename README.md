Este repositorio contiene la implementación de un framework de automatización de pruebas para una aplicación web, diseñado bajo buenas prácticas de QA Automation y principios de escalabilidad y mantenibilidad.

El proyecto está construido utilizando Java como lenguaje principal, integrando Cucumber para la definición de escenarios en lenguaje Gherkin bajo el enfoque BDD (Behavior Driven Development), lo que permite una comunicación clara entre el negocio, QA y desarrollo.

Los casos de prueba han sido escritos en Gherkin de tipo declarativo, priorizando el qué se valida sobre el cómo se ejecuta, facilitando la lectura y entendimiento de los escenarios sin necesidad de conocimientos técnicos avanzados.

A nivel de arquitectura, se ha implementado el patrón de diseño Page Object Model (POM), lo que permite:
- Separar la lógica de automatización de la lógica de negocio de las pruebas
- Reducir la duplicidad de código
- Validación de negocio a nivel de Step y no de Page.
- Mejorar el mantenimiento del framework ante cambios en la UI

Este enfoque permite construir un framework robusto, limpio y fácilmente extensible para futuras pruebas regresivas o nuevas funcionalidades.




