package com.baobaotao.beanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;


public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    private static final String preWord = "[容器]";
    private static final String classWord = "[InstantiationAwareBeanPostProcessor]";
    private static final String formatString = "级别%s,接口%s,备注[%s]方法[%s]";

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println(String.format(formatString, preWord, classWord, "实例化前", "postProcessBeforeInstantiation"));
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println(String.format(formatString, preWord, classWord, "实例化后", "postProcessAfterInstantiation"));
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println(String.format(formatString, preWord, classWord, "设置属性前", "postProcessPropertyValues"));
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(String.format(formatString, preWord, "[BeanPostProcessor]", "初始化前", "postProcessBeforeInitialization"));
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(String.format(formatString, preWord, "[BeanPostProcessor]", "初始化后", "postProcessAfterInitialization"));
        return bean;
    }
}
