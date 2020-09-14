package com.github.repository.researcher.model;

import java.util.ArrayList;

public class SearchResults {

  private boolean hasMorePages;

  private int repositoryCount;

  private ArrayList<Repository> repositories;

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
