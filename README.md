RETO ATLANTIC CITY (PARTE TEÓRICA)


Feature: Deposito mediante pasarela Pago con QR

Background:
  Given el usuario se encuentra autenticado en el casino

--Happy Path
@smoke @critical
Scenario: Deposito exitoso mediante Pago con QR
  When el usuario inicia un deposito usando Pago con QR
  And selecciona un monto valido
  Then el sistema genera un código QR válido
  And el usuario confirma la operación


--Alternative Path 
@regression
Scenario Outline: Deposito con montos permitidos
  When el usuario inicia un deposito usando Pago con QR
  And selecciona el monto "<monto>" valido permitido
  Then el sistema genera el código QR correctamente

Examples:
| monto |
| 250   |


--Negative Path
@critical
Scenario Outline: Deposito con monto inválido
  When el usuario inicia un deposito usando Pago con QR
  And  ingresa un monto "<monto>" fuera del rango permitido
  Then el sistema rechaza el deposito

Examples:
| monto |
| 9     |
| 501   |



--Exception Path
@critical
Scenario: Falla de comunicación con pasarela de pago
  When el usuario inicia un deposito usando Pago con QR
  And el sistema intenta generar el código QR
  And la pasarela de pago no responde
  Then el sistema muestra un mensaje de indisponibilidad
  

--Security Path
@security @critical 
Scenario: Prevención de reutilización del QR
  Given existe un QR previamente utilizado
  When el usuario inicia un deposito usando Pago con QR
  And el usuario intenta reutilizarlo
  Then el sistema informa que el código QR ya fue utilizado anteriormente

@security @critical 
Scenario: Validar integridad del monto del depósito frente a manipulación de request
  
  When el usuario inicia un deposito usando Pago con QR
  And  el monto enviado hacia la pasarela de pago es alterado durante la solicitud
  Then el sistema rechaza el deposito
  

--Performance Path
@performance @critical
Scenario: Generación eficiente del código QR

  When el usuario inicia un deposito usando Pago con QR
  And selecciona un monto valido
  Then el código QR es generado en menos de 2 segundos

@performance @critical
Scenario: Múltiples depósitos simultáneos
   
  When 100 usuarios realizan depósitos simultáneamente
  Then el tiempo de respuesta P.90 debe ser menor a 1.5 segundos
  And no debe haber mas de 5% de errores en las respuestas


Estrategia de Pruebas Funcional:

1. Validación del flujo principal de depósito
El objetivo es garantizar que el usuario pueda generar correctamente un código QR para realizar un depósito exitoso.
Aquí se estaría validando los siguientes puntos:
- Selección de montos
- Generación de QR
- Confirmación de operación

---> El riesgo de negocio que se estaría cubriendo es la imposibilidad de depositar dinero.

2. Validación de reglas de negocio
El objetivo de ello es verificar el cumplimiento de las restricciones funcionales definidas por negocio.
Aquí se estaría validando los siguientes puntos:
- Monto mínimo s/10
- Monto máximo s/500
- Formatos válidos
- Campo obligatorio para el depósito

Técnicas que se podrían usar en el diseño de casos de pruebas:
- Análisis de Valores Límites
- Partición de Equivalencia 

---> El riesgo de negocio que se estaría cubriendo es la generación de depósitos inválidos o inconsistentes.

3. Validación de integración con pasarela de pago QR
El objetivo es validar correctamente la comunicación entre el casino y proveedor de QR.
Aquí se estaría validando los siguientes puntos:
- Respuestas correctas API
- Manejo de timeout
- Correcto procesamiento transaccional
- Errores de integración

---> El riesgo de negocio que se estaría cubriendo sería la pérdida de transacciones financieras.


4. Validación de expiración y unicidad del código QR
El objetivo es garantizar que cada QR sea único y temporal.
Aquí se estaría validando los siguientes puntos:
- Expiración de QR
- Reutilización de QR
- QR Duplicados

---> El riesgo de negocio que se estaría cubriendo sería el fraude financiero.


5. Validación UX 
El objetivo es garantizar la claridad operativa para el usuario.
Aquí se estaría validando los siguientes puntos:
- Mensajes de error
- Mensajes de éxito
- Tiempos visibles
- Claridad del QR

---> El riesgo de negocio que se estaría cubriendo sería el abandono de depósitos.



Estrategia de Pruebas No Funcional:

1. Pruebas de Carga
El objeto es validar que la generación del código QR responda dentro de tiempos objetivos y medibles bajo condiciones normales y concurrentes.
Aquí se estaría validando los siguientes puntos:
- Tiempo de generación de QR
- Múltiples depósitos simultáneos
- Estabilidad bajo concurrencia

Métricas que se estarían evaluando:
- P90, P95
- Response Time
- Error Rate
- Throughput 


---> El riesgo de negocio que se estaría cubriendo es la lentitud de realizar depósitos.



2. Pruebas de Estrés 
El objetivo es determinar el punto de quiebre del sistema superando la carga esperada.
Aquí se estaría validando los siguientes puntos:
- Recuperación del sistema 
- Comportamiento extremo

---> El riesgo de negocio que se estaría cubriendo es la indisponibilidad del servicio.

3. Validación de integridad de datos 
El objetivo es validar que la información enviada al backend no pueda alterarse 
Aquí se estaría validando los siguientes puntos:
- Manipulación de parámetros enviados hacia backend
- Respuesta del backend

---> El riesgo de negocio que se estaría cubriendo sería las vulnerabilidades financieras.



¿Qué herramientas utilizar? 
1. Postman — Pruebas de servicios API
¿Qué se validaría?
- códigos de respuesta HTTP
- estructura de respuestas JSON
- tiempos de respuestas

--> Esto va a permitir validar tempranamente la capa de integración sin depender completamente de la interfaz gráfica.

2. Selenium — Automatización Web
Se utilizará para automatizar pruebas funcionales críticas del flujo “Pago con QR”, principalmente smoke test y pruebas de regresión.
¿Qué se validaría?
- flujo principal de depósito
- generación de QR
- validaciones funcionales UI
- mensajes visibles al usuario
- comportamiento end-to-end

--> Esto va a permitir validar el comportamiento funcional del sistema desde la perspectiva real del usuario final.

3. k6 o JMeter — Pruebas de Performance
Se utilizará para evaluar el rendimiento y estabilidad del sistema bajo carga concurrente durante la generación de códigos QR.
¿Qué se validaría?
- tiempos de respuesta
- concurrencia de usuarios
- throughput
- error rate

4. Burp Suite — Pruebas de Seguridad
Se utilizará para interceptar y analizar solicitudes HTTP/HTTPS relacionadas al flujo de depósito QR.
¿Qué se validaría?
- manipulación de requests
- alteración de montos
- validaciones backend

--> Esto va a permitir validar la integridad y seguridad del flujo financiero frente a posibles manipulaciones o fraudes.




