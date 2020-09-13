package com.github.repository.researcher.model;

public class Repository {

  private String nameWithOwner;

  private String shortDescription;

  private int starsCount;

  public String getNameWithOwner() {
    return nameWithOwner;
  }

  public void setNameWithOwner(String name) {
    this.nameWithOwner = name;
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
