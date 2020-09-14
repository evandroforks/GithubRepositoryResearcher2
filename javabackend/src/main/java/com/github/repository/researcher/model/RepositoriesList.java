package com.github.repository.researcher.model;

import java.util.ArrayList;

public class RepositoriesList {

  private int nextPage;

  private boolean hasMorePages;

  private ArrayList<String> repositories;

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
