package daniil.pavelev.logsender.custom.invocation_handler;

import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Component
public class SenderLogInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Отправляю лог");
        Object retVal = method.invoke(proxy,args);

        return retVal;
    }
}
