package com.github.repository.researcher.model;

import java.util.ArrayList;

public class RepositoriesList {

  private boolean hasMorePages;

  private ArrayList<String> repositories;

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
