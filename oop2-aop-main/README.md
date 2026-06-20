# Aspect Oriented Programming

Una alternativa para modularizar las incumbencias transversales (cross-cutting concerns).

- **Iteración 1**: Supongamos que modelamos una clase `Jugador`, una `Dirigente` y otra `Arbitro`. Jugador entiende
  el mensaje `insultarA(unArbitro, unInsulto)` que permite insultar a un árbitro.
- **Iteración 2**: Luego nos agregan que cuando un jugador o un dirigente insulte o grite quede persistido para generar
  un registro histórico.
- **Iteración 3**: Unos meses despues nos piden agregar quien insultó, lo cual implica modificar en varios lugares.
- Al resolver estos requerimientos si revisamos nuestra solución podemos notar que hay dos concerns independientes que
  estamos combinando en las mismas abstracciones: el problema funcional (el futbol) y el problema de la
  registración.
- Cuando diseñamos la solución, modelamos naturalmente una jerarquía de clases orientada a representar el dominio
  funcional en cuestión, en este caso el fútbol. Existen sin embargo una serie de **incumbencias transversales** (
  crosscutting concerts) a dicho modelo, como lo es el logging/registracón, que obligan a que las clases dedicadas a
  resolver cuestiones funcionales tengan que preocuparse también por cómo almacenar un mensaje en una base de datos. Los
  ejemplos de crosscutting concerns son requerimientos no funcionales típicos como seguridad, performance y logging.
- La implementación de crosscutting concerns provoca entonces dos características no deseables en
  el código fuente:
    - **Code Scattering**: El código correspondiente a una incumbencia, la registración en el ejemplo, no es encapsulado
      en un único módulo/clase, sino que aparece disperso por el programa (en Tecnico, Jugador, Dirigente, etc).
      Duplicando estas invocaciones a la registración hace que donde deba agregar un parámetro requiera de múltiples
      cambios.
    - **Code Tangling**: Dentro de un mismo módulo/clase encontramos código correspondiente a distintos concerns.
      En el ejemplo encontramos código que atañe al comportamiento funcional y código que corresponde a la
      registración del insulto. Una clase se ve impactada por razones diferentes, lo que hace más complejo el
      manteniemiento.
- **Solución usando Decorador**: Ahora al agregar parámetros en la registración, solo cambio en un lugar. Mucho mejor!
  Pero me queda la construcción del decorador como scattering. Y si necesito decorar otra jerarquia que no es
  PersonaDelFutbol? Ejemplo, tengo que registrar cuando un Arbitro le saca tarjeta a un jugador. No encaja
  `insultarA(Jugador)`... Ahora Arbitro entiende el mensaje `sacarTarjetaA(unJugador, color)`. Necesito una nueva
  jerarquía y un nuevo decorador.
- **Solución usando AOP**:
    - Su objetivo es modularizar las incumbencias transversales en una unidad de modularización llamada **Aspecto**.
    - Suponiendo que en el aspecto defino la registración, ¿cómo le indico desde que lugar debe invocarse sin
      generar tangling ni scattering?
    - Para esto es necesario definir cierto *puntos* en la ejecución del programa donde queremos que las
      incumbencias encapsultadas en el aspecto se invoquen.
    - Esto puntos son conocidos como **Join Points**: puntos en la ejecución de un programa, definidos en
      función de elementos sintácticos (llamados a métodos, retornos, lanzamientos de exceptions, etc).
    - ¿Quién realiza este proceso de entremezclado? Una extensión al compilador denominado **Weaving** (
      costura).
    - En el caso de Java necesitamos AspectJ (extiende el compilador de Java agregando el weaving).
    - **Pointcuts**: conjuntos de Join Points que permitirán luego especificar dónde corresponde aplicar un
      determinado aspecto.
    - **Advise**: Se denomina Advise al código a ejecutar para un Pointcut en particular.
        - Veamos entonces el módulo con la implementación utilizando aspectos.
          ```java
          @Aspect  //Registración es el Aspecto
          public class Registracion {
           //JoinPoint es la ejecución del método insultarA 
           //y mediante la siguiente anotación defino el PointCut.
            @Before("execution(* aop.Jugador.insultarA(..))") 
            public void registrarAntes(JoinPoint joinPoint) {
               Jugador unJugador = (Jugador) joinPoint.getThis();
               Arbitro unArbitro = (Arbitro) joinPoint.getArgs()[0];
               String insulto = (String) joinPoint.getArgs()[1];
                     
               // proceso con la registración ...
            }
          }
          ```

Necesitamos compilar con maven para que aplique el weaving y luego lo ejecutamos tambien con maven desde consola:

- Dentro del módulo `aop`:
    - `./mvnw clean compile` y luego,
    - `./mvnw exec:java`

Fuente: Explicación obtenida de la tesis de licenciatura de Rubén Altman y Alan Cyment llamada: "SetPoint - Un enfoque
semántico para la resolución de pointcuts".