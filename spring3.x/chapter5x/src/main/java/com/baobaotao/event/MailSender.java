package com.baobaotao.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MailSender implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(MailSender.class);

    private ApplicationContext ctx;

    public void setApplicationContext(ApplicationContext ctx)
            throws BeansException {
        this.ctx = ctx;

    }

    public void sendMail(String to) {
        logger.info("MailSender:模拟发送邮件...");
        MailSendEvent mse = new MailSendEvent(this.ctx, to);
        ctx.publishEvent(mse);
    }
}
