package com.github.repository.researcher.controller;

import com.github.repository.researcher.model.Repository;
import com.github.repository.researcher.model.SearchResults;
import com.github.repository.researcher.model.SearchRequest;
import com.github.repository.researcher.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class SearchController {

  private SearchService searchService;

  public SearchController(SearchService searchService) {
    this.searchService = searchService;
  }

  /**
   * {@code POST} end point to run a search request.
   *
   * <p>It receives a full {@link SearchRequest} object as a JSON representation, respecting the
   * required field from {@link SearchRequest}. Example of {@code POST} request body:
   *
   * <pre>{@code
   * {
   *     "query": "language:javascript sort:stars"
   * }
   * }</pre>
   *
   * <p>Returns a JSON listing all repositories found. See {@link Repository} for a list of
   * attributes.
   */
  @PostMapping("/searchRequest")
  @ResponseStatus(HttpStatus.CREATED)
  public SearchResults runSearchRequest(@RequestBody SearchRequest searchRequest)
      throws IOException {
    ArrayList<Repository> repositories = searchService.get(searchRequest);
    SearchResults searchResults = new SearchResults();
    searchResults.setRepositories(repositories);
    searchResults.setRateLimit("");
    return searchResults;
  }
}
