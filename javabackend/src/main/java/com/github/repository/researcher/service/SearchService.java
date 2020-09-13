package com.github.repository.researcher.service;

import com.github.repository.researcher.model.Repository;
import com.github.repository.researcher.model.SearchRequest;
import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.http.response.JsonResponse;
import org.springframework.stereotype.Service;

import javax.json.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

  public ArrayList<Repository> get(SearchRequest searchRequest) throws IOException {
    final Github github = new RtGithub();

    final List<JsonObject> repositoresRaw =
        github
            .entry()
            .uri()
            .path("/search/repositories")
            .queryParam("q", searchRequest.getQuery())
            .back()
            .fetch()
            .as(JsonResponse.class)
            .json()
            .readObject()
            .getJsonArray("items")
            .getValuesAs(JsonObject.class);

    ArrayList<Repository> repositories = new ArrayList<>();
    for (final JsonObject item : repositoresRaw) {
      Repository repository = new Repository();

      String repositoryName = item.getString("name");
      String repositoryOwner = item.getJsonObject("owner").getString("login");
      String repositoryNameWithOwner = item.getString("full_name");
      System.out.println(String.format("repository found: %s/%s", repositoryName, repositoryOwner));

      repository.setName(repositoryName);
      repository.setOwner(repositoryOwner);
      repository.setNameWithOwner(repositoryNameWithOwner);
      repository.setShortDescription(item.getString("description"));
      repository.setStargazersCount(item.getInt("stargazers_count"));

      repositories.add(repository);
      break;
    }
    return repositories;
  }
}
