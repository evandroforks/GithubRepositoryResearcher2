package com.github.repository.researcher.controller;

import com.github.repository.researcher.model.DetailRequest;
import com.github.repository.researcher.model.DetailResults;
import com.github.repository.researcher.model.RepositoriesList;
import com.github.repository.researcher.model.Repository;
import com.github.repository.researcher.model.SearchResults;
import com.github.repository.researcher.model.SearchRequest;
import com.github.repository.researcher.model.UserRequest;
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

  /**
   * {@code POST} end point to get more details about a repository.
   *
   * <p>It receives a full {@link DetailRequest} object as a JSON representation, respecting the
   * required field from {@link DetailRequest}. Example of {@code POST} request body:
   *
   * <pre>{@code
   * {
   *     "nameWithOwner": "actions/cache"
   * }
   * }</pre>
   *
   * <p>Returns a JSON detailing the repository found. See {@link Repository} for a list of
   * attributes.
   */
  @PostMapping("/detailRepository")
  @ResponseStatus(HttpStatus.CREATED)
  public DetailResults runDetailRepository(@RequestBody DetailRequest detailRequest)
      throws IOException {
    return searchService.detail(detailRequest);
  }


  /**
   * {@code POST} end point to list all repositories from a user.
   *
   * <p>It receives a full {@link UserRequest} object as a JSON representation, respecting the
   * required field from {@link UserRequest}. Example of {@code POST} request body:
   *
   * <pre>{@code
   * {
   *     "name": "actions"
   * }
   * }</pre>
   *
   * <p>Returns a JSON Array with all repositories found. See {@link Repository} for a list of
   * attributes.
   * @return
   */
  @PostMapping("/listRepositories")
  @ResponseStatus(HttpStatus.CREATED)
  public RepositoriesList runListRepositories(@RequestBody UserRequest userRequest)
      throws IOException {
    RepositoriesList list = searchService.list(userRequest);
    list.setRateLimit("");
    return list;
  }
}
