package com.capgemini.devonfw.module.reporting.common.impl;

import java.util.HashMap;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */

@ConfigurationProperties(prefix = "devon.reporting")
@Named
public class ReportingProperties {

  @Value("${devon.reporting.txtConfig.CharWidth}")
  private String CharWidth;

  private HashMap<String, String> txtConfig;

  /**
   * @return txtConfig
   */
  public HashMap<String, String> getTxtConfig() {

    return this.txtConfig;
  }

  /**
   * @param txtConfig new value of {@link #gettxtConfig}.
   */
  public void setTxtConfig(HashMap<String, String> txtConfig) {

    this.txtConfig = txtConfig;
  }

}
