# Soccer Online Manager Game API

A RESTful API for managing fantasy soccer teams. Users can manage their teams, buy and sell players.

## Features
- **Team Management**: View and edit team information, including team name, country.
- **Player Management**: Add players to the transfer list, browse available players, and purchase players.
- **Financial Management**: Automatic updates to team budgets and values during transactions.
- **Authentication**: HTTP Basic Authentication using predefined users for easy testing.


## Tech Stack
- **Java 21**
- **Spring Boot**
- **PostgreSQL**
- **Hibernate**


## Prerequisites
1. **Java Development Kit (JDK) 21**
2. **PostgreSQL** installed and running.
3. **Maven** for dependency management and building the project.


## Database Setup

### Schema and Initial Data
The database schema and initial data are provided in the `resources` directory:
- **`schema.sql`**: Defines the database schema (tables, constraints, etc.).
- **`data.sql`**: Inserts predefined users, teams, and players.

### Steps
1. Create a PostgreSQL database.
2. Configure the connection in the `application.properties` file:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
   spring.datasource.username=your_username
   spring.datasource.password=your_password


### Notes:

- Encrypted password values are `password1`, `password2`, `password3`

## How to Run the Project

1. Run `mvn clean install`
2. Run `mvn spring-boot:run`


## API Usage

### Example usage of api

`curl --location 'localhost:8080/api/team' --header 'Authorization: Basic dXNlcjJAZXhhbXBsZS5jb206cGFzc3dvcmQy'`

### Notes:

- Authorization header: `Authorization: Basic base64encode(username:password)`