# Nuevo Sistema de Gestión Académica

En esta oportunidad nos encargaremos de diseñar e implementar parte del Nuevo Sistema de Gestión Académica para la Universidad. El trabajo que nos asignaron está dividido en iteraciones, las cuales seguiremos de forma secuencial, diseñando e implementando el sistema de forma iterativa e incremental.
Cabe destacar que las funcionalidades que desarrollaremos en este hito estarán relacionadas con las inscripciones de los alumnos a las materias y las asignaciones de los docentes a los cursos.

## Primera iteración: Diseño e implementación de entidades principales

En esta iteración nos encargaremos de modelar, diseñar e implementar las entidades principales del Nuevo Sistema de Gestión Académica.
Los requerimientos funcionales solicitados por nuestro equipo de analistas son:
- Los alumnos deben tener un nombre, apellido, fecha de nacimiento, legajo, año de ingreso a la universidad y género. Además, interesa conocer si tienen alguna discapacidad (puede tener varias).
- Los docentes deben tener un nombre, apellido, fecha de nacimiento, descripción de su título habilitante, año de dictado de la primera materia en la universidad, género y apodo. Además, interesa saber qué materias dicta, cuáles son de su interés que estaría dispuesto a dictar y cuál es su disponibilidad de días. También se debe conocer si tiene habilidades para el trato con personas con discapacidades.
- Un curso debe tener un docente a cargo, así como también puede tener ayudantes. Además, es necesario saber la materia que se dicta en el curso, el ciclo lectivo, si es cuatrimestral o anual y la cantidad de alumnos. Por último, se necesita conocer el día y la hora de inicio de cursada, así como también el turno.
- De los ayudantes interesa el conocer el nombre, apellido y si es alumno actual de la universidad o no.
- Los ciclos lectivos deben tener un año y una descripción.
- Se debe permitir que los alumnos se pre-inscriban a las materias. En la pre-inscripción debe figurar el día y horario de inicio de cursada, así como también el nombre de la materia y ciclo lectivo.

## Segunda iteración: Persistencia de entidades principales
En esta iteración nos encargaremos de persistir las entidades principales del dominio.
Como todavía no se ha decidido si se trabajará con una base de datos relacional o no relacional o con ambas, vamos a persistir nuestras entidades en la memoria principal.
Es por ello que debemos diseñar e implementar una solución que nos permita cambiar el medio persistente con facilidad.
Los medios persistentes que nuestro sistema debe soportar, por ahora, son 2:
- *Memoria*: las entidades serán guardadas en listas (colecciones) en memoria sin sufrir alteraciones.
- *Archivo*: por facilidad, solamente debemos permitir la persistencia de entidades en archivos con formato JSON. Cada entidad del dominio debería poder ser serializada a este formato.

## Tercera iteración: Diseño e implementación de Asignaciones Docentes
Ha llegado el momento de diseñar e implementar la asignación docente.
Nos han informado que existen varios algoritmos de asignación docente, cada uno con un criterio en particular. En un futuro pueden surgir nuevos.
Se debe dar la posibilidad de que la persona responsable de ejecutar este proceso elija qué algoritmo utilizar.
Sabemos que uno de los algoritmos actuales es “por menor cantidad de cursos dictados”. Este algoritmo toma en cuenta a todos los docentes que dictan la materia en cuestión y los ordena de menor a mayor según la cantidad de cursos que dictan (de esa materia o de otras). Se asignará como docente titular de un curso al primero de la lista resultante.
Por último, debemos tener en cuenta que cada algoritmo selecciona, elige y asigna un docente para un curso dado.

## Cuarta iteración: Diseño e implementación de reglas de negocio
Hemos llegado a la última iteración. En esta sección, debemos pensar en cómo cumpliremos las siguientes reglas de negocio, diseñándolas e implementándolas.
- Para poder abrir un curso se debe tener como mínimo 20 alumnos inscriptos.
- No se pueden tener cursos con más de 60 alumnos.
- Si la cantidad de alumnos que quieren entrar en un curso (por sus preinscripciones) supera el máximo establecido, se debe seleccionar e inscribir solamente la cantidad máxima. Nos han dicho que existen diferentes algoritmos de asignación de alumnos, como, por ejemplo, “el peso académico”.
- No se puede abrir un curso sin tener un docente asignado.
- No se puede abrir un curso que no tenga detallado:
	- Ciclo lectivo
	- Materia
	- Día
	- Turno
	
## Quinta iteración: Cambios en el diseño e implementación de Asignaciones Docentes
No han avisado, un poco tarde, que todos los algoritmos de asignación docente deben respetar algunos pasos:
- Buscar los docentes candidatos: los docentes candidatos para un curso serán aquellos que estén interesados en dictar la materia.
- Ordenar a los docentes: cada algoritmo decidirá la forma en que ordenará a los docentes candidatos. Por ejemplo, un algoritmo podría ordenarlos por orden alfabético según su apellido.
- Seleccionar al docente: cada algoritmo decidirá qué docente de los anteriormente ordenados seleccionará. Por ejemplo, un algoritmo podría seleccionar un docente al azar.
- Asignar al curso el docente que fue seleccionado
