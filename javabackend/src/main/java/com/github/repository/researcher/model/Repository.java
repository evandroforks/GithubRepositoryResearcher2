package com.github.repository.researcher.model;

public class Repository {

  private String name;

  private String owner;

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }
}
