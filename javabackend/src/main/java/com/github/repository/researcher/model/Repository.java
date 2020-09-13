package com.github.repository.researcher.model;

public class Repository {

  private String nameWithOwner;

  private String shortDescription;

  private int stargazersCount;

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

  public int getStargazersCount() {
    return stargazersCount;
  }

  public void setStargazersCount(int stargazersCount) {
    this.stargazersCount = stargazersCount;
  }
}
