package aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;

@Aspect
public class Registracion {
    //@Before("execution(* aop..*.insultarA(..)) || execution(* aop..*.sacarTarjetaA(..))")
    @Before("execution(@Registrar * *(..))")
    public void registrarAntes(JoinPoint joinPoint) {
        String clase = joinPoint.getTarget().getClass().getSimpleName();
        String metodo = joinPoint.getSignature().getName();
        Object[] argumentos = joinPoint.getArgs();
        System.out.println("Antes de la invocación:");
        System.out.println("   -> Clase: " + clase);
        System.out.println("   -> Método: " + metodo);
        System.out.println("   -> Parámetros: " + Arrays.toString(argumentos));
        System.out.println("--------------------------------------------------");
    }

    //@After("execution(* aop..*.insultarA(..)) || execution(* aop..*.sacarTarjetaA(..))")
    public void registrarDespues(JoinPoint joinPoint) {
        String clase = joinPoint.getTarget().getClass().getSimpleName();
        String metodo = joinPoint.getSignature().getName();
        Object[] argumentos = joinPoint.getArgs();
        System.out.println("Despues de la invocación:");
        System.out.println("   -> Clase: " + clase);
        System.out.println("   -> Método: " + metodo);
        System.out.println("   -> Parámetros: " + Arrays.toString(argumentos));
        System.out.println("--------------------------------------------------");
    }

    //@Around("execution(* aop..*.insultarA(..)) || execution(* aop..*.sacarTarjetaA(..))")
    public void registrarEntre(ProceedingJoinPoint joinPoint) throws Throwable {
        String mensaje = (String) joinPoint.getArgs()[1];
        System.out.println("Antes de la invocación: " + mensaje);

        joinPoint.proceed();

        System.out.println("Después de la invocación: " + mensaje);
    }
}