package ru;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubscriberBeanPostProcessor implements BeanPostProcessor {
    protected static final Map<Method, Object> METHOD_2_BEAN = new HashMap<>();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getMethods())
                .filter(m -> m.isAnnotationPresent(Subscriber.class))
                .forEach(m -> METHOD_2_BEAN.put(m, bean));
        return bean;
    }
}
