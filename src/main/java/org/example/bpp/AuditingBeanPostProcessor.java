package org.example.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class AuditingBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> auditingBean = new HashMap<>();
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Auditing.class)){
            auditingBean.put(beanName, bean.getClass());
        }
//        if (bean.getClass().isAnnotationPresent(Transaction.class)){
//            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
//                    (proxy, method, args) -> {
//                        System.out.println("Open transaction");
//                        try {
//                            return method.invoke(bean, args);
//                        }finally {
//                            System.out.println("Close transaction");
//                        }
//                    });
//        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = auditingBean.get(beanName);
        if (beanClass != null){
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("Audit method: " + method.getName());
                        var startTime = System.nanoTime();
                        try {
                            return method.invoke(bean, args);
                        }finally {
                            System.out.println("Time: " + (System.nanoTime() - startTime));
                        }
                    });
        }
        return bean;
    }
}
