package com.scing.erp.sistema.authentication;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "client")
public class PropertiesClient {

  private String url;
  private String resetPasswordTokenExpiration;
  private String resetPasswordTokenClearJob;

  public PropertiesClient() {
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getResetPasswordTokenExpiration() {
    return resetPasswordTokenExpiration;
  }

  public void setResetPasswordTokenExpiration(String resetPasswordTokenExpiration) {
    this.resetPasswordTokenExpiration = resetPasswordTokenExpiration;
  }

  public String getResetPasswordTokenClearJob() {
    return resetPasswordTokenClearJob;
  }

  public void setResetPasswordTokenClearJob(String resetPasswordTokenClearJob) {
    this.resetPasswordTokenClearJob = resetPasswordTokenClearJob;
  }

}
