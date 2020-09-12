package com.github.repository.researcher.model;

import javax.persistence.Column;

public class Repository {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String shortDescription;

  @Column(nullable = false)
  private int starsCount;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public int getStarsCount() {
    return starsCount;
  }

  public void setStarsCount(int starsCount) {
    this.starsCount = starsCount;
  }
}
