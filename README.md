### Escuela Colombiana de Ingeniería

### Arquitecturas de Software
## Laboratorio Componentes y conectores  Middleware- gestión de planos

## Integrantes 
* Carlos Andres Amorocho Amorocho
* Rubian Camilo Saenz Rodriguez

### Dependencias
* [Ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI-beta/DIP_DI-SPRING_JAVA-GRAMMAR_CHECKER).

### Descripción
En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

### Parte I.

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.
	* Agregar la configuración de Spring.
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.

```
Se agregaron las dependencias para el funcionamiento del mismo:
Se agregaron en la clase BlueprintsServices.java y BlueprintsPersistence.java, si es necesario agregar mas dependencias en un futuro se agregaran.
```

2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

Metodo getBlueprint:

<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/getBlueprint.PNG?raw=true">

Metodo getBluePrintByAuthor
<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/getBlueprintByAuthor.PNG?raw=true">

# Test:

Test getBlueprint y getBlueprintByAuthor:

<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/Test1.PNG?raw=true">

3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

Se agrega el archivo applicationContext en los recursos del lab.

<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/applicationContext.PNG?raw=true">

<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/puntoTres.PNG?raw=true">

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

Se agregan las respectivas clases, estas se implementan de una interfaz Filtro:

Clase Muestreo
<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/classMuestreo.PNG?raw=true">

Clase Redundancia
<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/classRedundancia.PNG?raw=true">

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B). 

Se crean las respectivas pruebas

<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/Test2.PNG?raw=true">

Para el funcionamiento individual de cada filtro, se modifica el identificador de la etiqueta @Qualifier por el filtro deseado

@Qualifier "Muestreo"

<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/Qualifier-Muestreo.PNG?raw=true">
<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/TestMuestreo.PNG?raw=true">

@Qualifier "Redundancia"

<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/Qualifier-Redundancia.PNG?raw=true">
<img src="https://github.com/camilosaenz/ARSW-LABORATORIO4/blob/master/img/TestRedundancia.PNG?raw=true">
