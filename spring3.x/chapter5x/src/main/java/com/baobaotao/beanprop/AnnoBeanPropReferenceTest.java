package com.baobaotao.beanprop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnoBeanPropReferenceTest {

	public static void main(String[] args) throws Throwable {
		String resourceFile = "com/baobaotao/beanprop/beans1.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(resourceFile);
		ApplicationManager applicationManager = ctx.getBean(ApplicationManager.class);
		System.out.println(applicationManager);
	}
}
