package com.baobaotao.beanprop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

public class BeanPropReferenceTest {

	public static void main(String[] args) throws Throwable {
		String resourceFile = "com/baobaotao/beanprop/beans.xml";
		ApplicationContext ctx = new ClassPathXmlApplicationContext(resourceFile);
		DataSource ds = ctx.getBean(DataSource.class);
		Connection conn = ds.getConnection();
		System.out.println("conn is not null:"+(conn!=null));
		
	}
}
