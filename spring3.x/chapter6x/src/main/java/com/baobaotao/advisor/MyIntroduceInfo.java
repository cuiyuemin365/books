package com.baobaotao.advisor;

import org.springframework.aop.support.IntroductionInfoSupport;

import com.baobaotao.introduce.Monitorable;

public class MyIntroduceInfo extends IntroductionInfoSupport{
   public MyIntroduceInfo(){
	   super();
	   super.publishedInterfaces.add(Monitorable.class);
   }
}
