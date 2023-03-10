package com.scing.erp.sistema.authentication.resetpassword;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RestorePasswordJob {

  private static final Logger LOGGER = LoggerFactory.getLogger(RestorePasswordJob.class);

  private RestorePasswordService restorePasswordService;

  @Autowired
  public RestorePasswordJob(RestorePasswordService restorePasswordService) {
    this.restorePasswordService = restorePasswordService;
  }

  @Scheduled(cron = "${client.resetPasswordTokenClearJob}")
  public void reportCurrentTime() {
    LOGGER.info("Clear expired restore tokens");
    restorePasswordService.removeExpiredRestorePasswordTokens();
  }
}
