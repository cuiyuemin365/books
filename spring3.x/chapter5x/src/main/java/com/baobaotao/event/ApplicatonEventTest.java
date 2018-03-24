package com.baobaotao.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicatonEventTest {

    private static final Logger logger = LoggerFactory.getLogger(MailSendListener.class);

    public static void main(String[] args) {
        String resourceFile = "xml/event/beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(resourceFile);
        MailSender mailSender = ctx.getBean(MailSender.class);
        mailSender.sendMail("test send !");
        logger.info("done.");
    }
}
