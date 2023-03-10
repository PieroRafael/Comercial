package com.scing.erp;

import java.util.Locale;
import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@SuppressWarnings({ "checkstyle:FinalClass", "checkstyle:HideUtilityClassConstructor" })
public class BundleJavaApplication {

  public static void main(String[] args) {
    Locale.setDefault(Locale.US);
    TimeZone.setDefault(TimeZone.getTimeZone("Etc/UCT"));

    SpringApplication.run(BundleJavaApplication.class, args);
  }
}
