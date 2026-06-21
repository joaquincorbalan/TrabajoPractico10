package ejercicio.Modele;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
public class LogAspect {

    private static final String LOG_FILE = "metodos.log";

    @Pointcut("@annotation(ejercicio.Modele.Log)")
    public void logMethods() {
    }

    @After("logMethods()")
    public void logInvocation(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        String params;
        if (args.length == 0) {
            params = "sin parametros";
        } else {
            params = String.join("|",
                    java.util.Arrays.stream(args)
                            .map(Object::toString)
                            .toArray(String[]::new));
        }

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));

        String logLine = "\"" + methodName + "\", \"" + params + "\", \"" + timestamp + "\"";

        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            System.out.println("Aspect activo");
            out.println(logLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
