import React from "react";
import { Repository, prettyPrintError, extendArray } from "./Utils";

interface RepositoryDetails {
  createdAt: string,
  openIssuesCount: number,
  mainLanguage: string,
}

interface UserRepository {
  hasMorePages: false,
  nextPage: number,
  repositories: Array<{
    name: string,
  }>,
}

interface RepositoryItemProps {
  index: number,
  getBackEndUrl: Function,
  marginBottom: number,
  repository: Repository,
  pageOffSet: number,
  isBookmarked: Function,
  toogleBookmarks: Function,
}

interface RepositoryItemState {
  hasMorePages: boolean,
  nextPage: number,
  errorMessage: string,
  userRepositories: Array<string>,
  repositoryDetails: RepositoryDetails | null,
  isShowingDetails: boolean,
  isLoadingDetails: boolean,
  isLoadingRepositories: boolean,
}

export class Content extends React.Component<RepositoryItemProps, RepositoryItemState>
{
  constructor(props: RepositoryItemProps) {
    super(props);
    this.toggleDetails = this.toggleDetails.bind(this)
    this.loadMoreDetails = this.loadMoreDetails.bind(this)
    this.loadRepositoryDetails = this.loadRepositoryDetails.bind(this)
    this.loadUserRepositories = this.loadUserRepositories.bind(this)

    this.state = {
      isShowingDetails: false,
      errorMessage: "",
      hasMorePages: true,
      nextPage: 1,
      userRepositories: [],
      repositoryDetails: null,
      isLoadingDetails: false,
      isLoadingRepositories: false,
    }
  }

  render() {
    let isBookmarked: string = this.props.isBookmarked(this.props.repository.nameWithOwner) ? "Remove from Bookmarks" : "Add to Bookmarks"

    return (
      <div key={this.props.index} style={{ marginBottom: this.props.marginBottom }}>
        {this.state.errorMessage.length > 0 &&
          <div dangerouslySetInnerHTML={{ __html: this.state.errorMessage }} />
        }

        <h5 style={{ marginBottom: 0 }}>
          {this.props.index + this.props.pageOffSet + 1}. { }
          {this.props.repository.nameWithOwner.replace("/", " / ")} { }
          ({this.props.repository.stargazersCount.toLocaleString()} stars)
        </h5>

        <p>{this.props.repository.shortDescription}</p>
        {this.state.repositoryDetails != null && this.state.isShowingDetails && (
          <div>
            <b>Created at</b>: {this.state.repositoryDetails?.createdAt.replace(/(?<=-\d\d)T|(?<=:\d\d)Z/g, ", ")}
            <b>Open Issues count</b>: {this.state.repositoryDetails?.openIssuesCount}, { }
            <b>Top Language</b>: {this.state.repositoryDetails?.mainLanguage}, { }
            <b>User Repositories</b>: {this.state.userRepositories.map((name: string, index: number) => {
                return (
                  <span key={name + index}><b>{index + 1})</b> { } {name}, </span>
                )
              }
            )}
          </div>
        )}

        <button type="button"
          onClick={this.loadMoreDetails}
          style={{ minWidth: "120px" }}
          disabled={!this.state.hasMorePages}>
          {this.state.isLoadingDetails || this.state.isLoadingRepositories ? "Loading more..." : "More details..."}
        </button>
        <button type="button"
          onClick={this.toggleDetails}
          style={{ minWidth: "120px" }}
          disabled={this.state.repositoryDetails == null}>
          {this.state.isShowingDetails ? "Hide details" : "Show Details"}
        </button>
        <button
            color="info"
            style={{ minWidth: "120px" }}
            onClick={(args: any) => { return this.props.toogleBookmarks(this.props.repository.nameWithOwner) }}
            key={isBookmarked}
          >{isBookmarked}</button>
      </div>
    );
  }

  toggleDetails() {
    this.setState({ isShowingDetails: !this.state.isShowingDetails } )
  }

  loadMoreDetails() {
    if(this.state.repositoryDetails == null) {
      this.setState({ isLoadingDetails: true })
      this.loadRepositoryDetails()
    }

    if(this.state.hasMorePages) {
      this.setState({ isLoadingRepositories: true })
      this.loadUserRepositories()
    }

    this.setState( { isShowingDetails: true } )
  }

  loadRepositoryDetails() {
    // console.log("Sending loadMoreDetails for", this.props.repository.nameWithOwner)

    fetch(
      this.props.getBackEndUrl() + "/detailRepository",
      {
        method: 'POST',
        body: JSON.stringify(
          {
            nameWithOwner: this.props.repository.nameWithOwner,
          }
        ),
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
      }).then(
        response => {
          // console.log('Server response', response);

          if (response.ok) {
            var repositories_response = response.json()
            // console.log( 'Server response OK:', repositories_response );

            repositories_response.then(
              (response: RepositoryDetails) => {
                // console.log( 'Server response:', response );

                this.setState({
                  repositoryDetails: response,
                  isLoadingDetails: false,
                });
              }).catch(this.setError)
          }
          else {
            response.text().then(
              text => {
                throw new Error('Could not get the server response after sending the request!\n'
                  + response.statusText
                  + '\n'
                  + text);
              }).catch(this.setError)
          }
      }).catch(this.setError)
  }

  loadUserRepositories() {
    // console.log("Sending loadUserRepositories for", this.props.repository.nameWithOwner)

    fetch(
      this.props.getBackEndUrl() + "/listRepositories",
      {
        method: 'POST',
        body: JSON.stringify(
          {
            name: this.props.repository.owner,
            page: this.state.nextPage,
            // itemsPerPage: 100,
          }
        ),
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
      }).then(
        response => {
          // console.log('Server response', response);

          if (response.ok) {
            var repositories_response = response.json()
            // console.log( 'Server response OK:', repositories_response );

            repositories_response.then(
              (response: UserRepository) => {
                // console.log('Server response:', response);

                let userRepositories = this.state.userRepositories
                extendArray(userRepositories, response.repositories)

                this.setState({
                  userRepositories: userRepositories,
                  nextPage: this.state.nextPage + 1,
                  hasMorePages: response.hasMorePages,
                  isLoadingRepositories: false,
                });
              }).catch(this.setError)
          }
          else {
            response.text().then(
              text => {
                throw new Error('Could not get the server response after sending the request!\n'
                  + response.statusText
                  + '\n'
                  + text);
              }).catch(this.setError)
          }
      }).catch(this.setError)
  }

  setError(error: Error) {
    let message: string = prettyPrintError(error);
    alert(message);

    message = message.split(" ").join("&nbsp;");
    message = message.split("\n").join("<br/>");
    this.setState({ errorMessage: message })
  }
};

export default Content;
