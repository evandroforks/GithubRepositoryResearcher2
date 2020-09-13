package com.github.repository.researcher.model;

import java.util.ArrayList;

public class DetailResults {

  private int openIssuesCount;

  private String mainLanguage;

  private String createdAt;

  public int getOpenIssuesCount() {
    return openIssuesCount;
  }

  public void setOpenIssuesCount(int openIssuesCount) {
    this.openIssuesCount = openIssuesCount;
  }

  public String getMainLanguage() {
    return mainLanguage;
  }

  public void setMainLanguage(String mainLanguage) {
    this.mainLanguage = mainLanguage;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }
}
