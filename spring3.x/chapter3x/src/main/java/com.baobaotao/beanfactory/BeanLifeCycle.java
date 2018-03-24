package com.baobaotao.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BeanLifeCycle {
    private static void LifeCycleInBeanFactory() {
        Resource resource = new ClassPathResource("xml/beans1.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        (new MyBeanFactoryPostProcessor()).postProcessBeanFactory((XmlBeanFactory) beanFactory);
        ((ConfigurableBeanFactory) beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        beanFactory.getBean("car");
        ((XmlBeanFactory) beanFactory).destroySingletons();
    }

    public static void main(String[] args) {
        LifeCycleInBeanFactory();
    }
}
