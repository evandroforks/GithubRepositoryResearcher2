package com.github.repository.researcher.model;

import java.util.ArrayList;

public class SearchResults {

  private String rateLimit;

  private String lastItemId;

  private boolean hasMorePages;

  private int repositoryCount;

  private ArrayList<Repository> repositories;

  public String getRateLimit() {
    return rateLimit;
  }

  public void setRateLimit(String rateLimit) {
    this.rateLimit = rateLimit;
  }

  public String getLastItemId() {
    return lastItemId;
  }

  public void setLastItemId(String lastItemId) {
    this.lastItemId = lastItemId;
  }

  public boolean isHasMorePages() {
    return hasMorePages;
  }

  public void setHasMorePages(boolean hasMorePages) {
    this.hasMorePages = hasMorePages;
  }

  public int getRepositoryCount() {
    return repositoryCount;
  }

  public void setRepositoryCount(int repositoryCount) {
    this.repositoryCount = repositoryCount;
  }

  public ArrayList<Repository> getRepositories() {
    return repositories;
  }

  public void setRepositories(ArrayList<Repository> repositories) {
    this.repositories = repositories;
  }
}
