package com.github.repository.researcher.model;

public class SearchRequest {

  private String query;

  private int page;

  private int itemsPerPage;

  public SearchRequest() {
    this.page = 1;
    this.itemsPerPage = 10;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getItemsPerPage() {
    return itemsPerPage;
  }

  public void setItemsPerPage(int itemsPerPage) {
    this.itemsPerPage = itemsPerPage;
  }
}
