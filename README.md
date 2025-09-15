# git-hub-users

## Project Description
This project forms a service that fetches the list of GitHub repositories for a given username. 
It gets all the repositories, filter them to have only not forked and presents information about Repository Name, 
Owner Login, and details for each branch including names and the last commit SHA.

## Installation

This project uses Java SDK version 21 and Spring Boot 3.

Clone the repository and import it to your preferred IDE.

## Usage

To get a list of user repositories, make a `GET` request to `/user/{username}/repos`.

The parts in `{}` like `{username}` must be replaced with actual values when using the APIs.

The server will respond in JSON format with the following fields:

1. Repository Name
2. Owner Login
3. Branch name and its last commit SHA for each branch.

```json
[
    {
        "repositoryName": "Hello-World",
        "ownerLogin": "octocat",
        "branches": [
            {
                "name": "master",
                "sha": "7fd1a60b01f91b314f59955a4e4d4e80d8edf11d"
            },
            {
                "name": "octocat-patch-1",
                "sha": "b1b3f9723831141a31a1a7252a213e216ea76e56"
            },
            {
                "name": "test",
                "sha": "b3cbd5bbd7e81436d2eee04537ea2b4c0cad4cdf"
            }
        ]
    },
]
```

If the GitHub user does not exist, the API will respond with a `404` status and a JSON like the following:

```json
{
    "status": 404,
    "message": "given message"
}
```

## External API
This project hinges on the GitHub API (v3). More about it can be learned [here](https://docs.github.com/en/rest?apiVersion=2022-11-28).

## License
[MIT](https://choosealicense.com/licenses/mit/)