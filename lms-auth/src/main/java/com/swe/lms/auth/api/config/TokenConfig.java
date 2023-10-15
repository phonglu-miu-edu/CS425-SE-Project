package com.swe.lms.auth.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Configuration
@RefreshScope
@EnableAutoConfiguration
public class TokenConfig {
	
	@NotBlank
	@Value("${token.issuer}")
	private String issuer;
	
	@NotBlank
	@Value("${token.key}")
	private String key;

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
