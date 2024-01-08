package daniil.pavelev.logsender.custom.bean_post_processor;

import daniil.pavelev.logsender.annotation.SendLog;
import daniil.pavelev.logsender.custom.invocation_handler.SenderLogInvocationHandler;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class SenderLogBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class> classMap = new HashMap<>();
    private final SenderLogInvocationHandler senderLogInvocationHandler;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(SendLog.class)) {
            classMap.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = classMap.get(beanName);
        if (beanClass != null) {
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                    System.out.println("Отправляю лог");

                    Object retVal = method.invoke(bean, args);

                    return retVal;
                }
            });

        }
        return bean;
    }
}