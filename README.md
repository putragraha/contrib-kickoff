# ContribKickoff
ContribKickoff application is a research application. Used to show list of GitHub Repository obtained from [GitHub API](https://docs.github.com/en/rest/) with condition `help-wanted-issues`. List generated can be used to find an issue in several repository which requires fixes, may help to start a journey as an open source project contributor

### Getting Started ###
- Create `credential.properties` in root folder of the project
- Fill the value below
```
USER_AGENT=[Your GitHub Username]
OAUTH_TOKEN=[Your GitHub OAuth Token]
```

## Tech Stack
- Kotlin DSL, for dependency management and build toolkit
- MVVM + Clean Architecture, for separating the business into layers
- Jetpack Navigation, apps built with Single Activity mode
- Retrofit + OkHttp, for HTTP request
- Jetpack Room, for storing data in local as cache, supporting offline capabilities
- Hilt, for dependency injection
- Glide, as image loader
- Jetpack Paging 3, for handling pagination