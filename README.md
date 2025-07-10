# [Message Broker v2](https://message-broker.atai-mamytov.click/login)

## [Instructions](https://message-broker.atai-mamytov.click/instructions)

## PS: Use [this](https://github.com/coffee3333/test2/blob/main/.github/workflows/ci.yml) workflow

## Overview
Message Broker v2 is an advanced web application built with Spring Boot, designed to automate the generation of insightful GitHub discussion comments based on commit and push events. Leveraging the GitHub API and an external AI microservice (e.g., Gemini), this application enhances developer productivity by creating context-aware discussions. An upgrade from the single-user Broker.v1, v2 supports multiple users, customizable prompts, and a user-friendly web interface.

## Features
- Supports multiple users with individual accounts.
- Allows creation of unlimited apps per user with custom prompts.
- Integrates with the GitHub API to fetch commit and push event data.
- Processes data via an external AI microservice to generate meaningful comments.
- Provides a web interface for user management and app configuration.
- Includes clear instructions and comprehensive testing (unit and integration).
- Ensures secure authentication and session management with Spring Security.

## How It Works
1. **User Registration**: Users sign up and log in via the web interface.
2. **App Creation**: Users create apps with GitHub tokens and custom prompts.
3. **Event Trigger**: GitHub push events trigger the application via GitHub Actions.
4. **Data Processing**: The app fetches commit details and forwards them to the AI microservice using `RestTemplate`.
5. **Discussion Generation**: The AI processes the data and returns comments, which the app posts to GitHub discussions via GraphQL.

## Prerequisites
- **Java 17+**: Required to run the Spring Boot application.
- **Docker**: Needed to containerize and run the service.
- **GitHub Account**: For repository setup and GitHub Actions.
- **AI Microservice**: A running instance (e.g., Gemini-based service) at the specified endpoint.
- **Database**: An SQL database (e.g., PostgreSQL or H2 for testing).

## Future Enhancements
- **Scheduled Triggers**: Add customizable schedules for automated discussions.
- **Robust Error Handling**: Enhance stability with better error reporting.
- **Configuration UI**: Expand the web interface for advanced settings.
- **User Dashboard**: Provide a dashboard to manage generated discussions.
- **Customizable Templates**: Allow users to define comment styles.

### [Presentation](https://www.canva.com/design/DAGqz2GVIeY/7AOt1fszz_0VstqB0B4JZw/edit?utm_content=DAGqz2GVIeY&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)
