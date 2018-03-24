package com.baobaotao.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

public class MailSendListener implements ApplicationListener<MailSendEvent> {

    private static final Logger logger = LoggerFactory.getLogger(MailSendListener.class);

    public void onApplicationEvent(MailSendEvent event) {
        MailSendEvent mse = (MailSendEvent) event;
        logger.info("MailSendListener:向{}发送完一封邮件", mse.getTo().toString());
    }
}
