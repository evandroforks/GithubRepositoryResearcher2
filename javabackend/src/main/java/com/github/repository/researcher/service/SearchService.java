package com.github.repository.researcher.service;

import com.github.repository.researcher.model.DetailRequest;
import com.github.repository.researcher.model.DetailResults;
import com.github.repository.researcher.model.RepositoriesList;
import com.github.repository.researcher.model.Repository;
import com.github.repository.researcher.model.SearchRequest;
import com.github.repository.researcher.model.SearchResults;
import com.github.repository.researcher.model.UserRequest;
import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.http.Response;
import com.jcabi.http.response.JsonResponse;
import org.springframework.stereotype.Service;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Controls the GitHub Search business logic.
 *
 * @see <a
 *     href="https://www.tutorialspoint.com/spring_boot/spring_boot_service_components.htm">Spring
 *     Boot - Service Components</a>.
 */
@Service
public class SearchService {

  public SearchService() {}

  public SearchResults get(SearchRequest searchRequest) throws IOException {
    final Github github = new RtGithub();
    SearchResults searchResults = new SearchResults();

    final Response repositoresRawRequest =
        github
            .entry()
            .uri()
            .path("/search/repositories")
            .queryParam("q", searchRequest.getQuery())
            .queryParam("page", searchRequest.getPage())
            .queryParam("per_page", searchRequest.getItemsPerPage())
            .back()
            .fetch();

    final JsonObject repositoriesRawJson =
        repositoresRawRequest
            .as(JsonResponse.class)
            .json()
            .readObject();

    final List<JsonObject> repositoriesRaw =
        repositoriesRawJson
            .getJsonArray("items")
            .getValuesAs(JsonObject.class);

    final int repositoriesTotalCount =
        repositoriesRawJson
            .getInt("total_count");

    ArrayList<Repository> repositories = new ArrayList<>();
    for (final JsonObject item : repositoriesRaw) {
      Repository repository = new Repository();

      String repositoryName = item.getString("name");
      String repositoryOwner = item.getJsonObject("owner").getString("login");
      String repositoryNameWithOwner = item.getString("full_name");

      repository.setName(repositoryName);
      repository.setOwner(repositoryOwner);
      repository.setNameWithOwner(repositoryNameWithOwner);
      repository.setShortDescription(item.getString("description"));
      repository.setStargazersCount(item.getInt("stargazers_count"));

      repositories.add(repository);
    }

    searchResults.setHasMorePages(this.hasMorePages(repositoresRawRequest));
    searchResults.setRepositories(repositories);
    searchResults.setRepositoryCount(repositoriesTotalCount);
    return searchResults;
  }

  public DetailResults detail(DetailRequest detailRequest) throws IOException {
    final Github github = new RtGithub();
    DetailResults detailResults = new DetailResults();

    final JsonObject repositoryInfo =
        github
            .entry()
            .uri()
            .path("/repos/" + detailRequest.getNameWithOwner())
            .back()
            .fetch()
            .as(JsonResponse.class)
            .json()
            .readObject();

    detailResults.setCreatedAt(repositoryInfo.getString("created_at"));
    detailResults.setOpenIssuesCount(repositoryInfo.getInt("open_issues"));

    final JsonObject repositoryLanguagesJson =
        github
            .entry()
            .uri()
            .path("/repos/" + detailRequest.getNameWithOwner() + "/languages")
            .back()
            .fetch()
            .as(JsonResponse.class)
            .json()
            .readObject();

    Set<String> repositoryLanguagesSet = repositoryLanguagesJson.keySet();
    detailResults.setMainLanguage(repositoryLanguagesSet.iterator().next());
    return detailResults;
  }

  public RepositoriesList list(UserRequest userRequest) throws IOException {
    final Github github = new RtGithub();
    ArrayList<String> repositories = new ArrayList<>();
    RepositoriesList repositoriesList = new RepositoriesList();

    final Response userRepositoriesRequest =
        github
            .entry()
            .uri()
            .path("/users/" + userRequest.getName() + "/repos")
            .queryParam("page", userRequest.getPage())
            .back()
            .fetch();

    final JsonArray userRepositories =
        userRepositoriesRequest
            .as(JsonResponse.class)
            .json()
            .readArray();

    for (final JsonValue item : userRepositories) {
      JsonObject repository = (JsonObject)item;
      repositories.add(repository.getString("name"));
    }

    repositoriesList.setRepositories(repositories);
    repositoriesList.setHasMorePages(this.hasMorePages(userRepositoriesRequest));
    return repositoriesList;
  }

  private boolean hasMorePages(Response userRepositoriesRequest) {
    Map<String, List<String>> headers = userRepositoriesRequest.headers();
    String link = headers.get("Link").toString();
    return link.contains("last");
  }
}
