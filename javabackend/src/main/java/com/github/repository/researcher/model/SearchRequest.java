package com.github.repository.researcher.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class SearchRequest {

  @Column(nullable = false)
  private String query;

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }
}
