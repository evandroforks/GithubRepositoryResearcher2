package com.github.repository.researcher.model;

import java.util.ArrayList;

public class RepositoriesList {

  private String rateLimit;

  private int nextPage;

  private boolean hasMorePages;

  private ArrayList<String> repositories;

  public String getRateLimit() {
    return rateLimit;
  }

  public void setRateLimit(String rateLimit) {
    this.rateLimit = rateLimit;
  }

  public int getNextPage() {
    return nextPage;
  }

  public void setNextPage(int nextPage) {
    this.nextPage = nextPage;
  }

  public boolean isHasMorePages() {
    return hasMorePages;
  }

  public void setHasMorePages(boolean hasMorePages) {
    this.hasMorePages = hasMorePages;
  }

  public ArrayList<String> getRepositories() {
    return repositories;
  }

  public void setRepositories(ArrayList<String> repositories) {
    this.repositories = repositories;
  }
}
