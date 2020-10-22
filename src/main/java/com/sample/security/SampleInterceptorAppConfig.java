package com.sample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class SampleInterceptorAppConfig extends WebMvcConfigurerAdapter {
	
   @Autowired
   SampleInterceptor sampleInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(sampleInterceptor);
   }
}
