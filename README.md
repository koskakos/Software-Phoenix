## Project Overview

This project implements a RESTful API using the Spring framework. Authentication is powered by JSON Web Tokens (JWT), featuring both access tokens and refresh tokens for token management. PostgreSQL is used for data persistence.


## Running with Docker Compose

You can run this project using Docker Compose. To do so, follow these steps:

1. Install Docker Engine and Docker Compose if you haven't already.
2. Clone the repository to your local machine and navigate to the project directory.

3. Open a terminal and run the following command to start the application using Docker Compose:

   ```bash
   docker-compose up
   ```

## API Endpoints

### Authentication

1. **POST /api/v1/auth/signin**
    - Description: Endpoint for user authentication.
    - Request Body:
      ```json
      {
          "login": "login",
          "password": "12345"
      }
      ```

2. **POST /api/v1/auth/signup**
    - Description: Endpoint for user registration.
    - Request Body:
      ```json
      {
          "login": "login",
          "password": "12345",
          "passwordConfirmation": "12345",
          "fullname": "fullname",
          "avatarUrl": "url"
      }
      ```

3. **POST /api/v1/auth/refresh**
    - Description: Endpoint for refreshing access tokens using refresh tokens.
    - Request: Cookie value `refreshToken`.

### User Management

4. **GET /api/v1/user**
    - Description: Get information about the authenticated user.

5. **GET /api/v1/user/orders**
    - Description: Get all orders belonging to the authenticated user.

6. **PUT /api/v1/user**
    - Description: Update the authenticated user's profile.
    - Request Body:
      ```json
      {
          "fullname": "login",
          "avatarUrl": "url"
      }
      ```

### Order Management

7. **POST /api/v1/order**
    - Description: Create a new order.
    - Request Body:
      ```json
      {
          "product": "product",
          "quantity": 54,
          "address": "address",
          "phoneNumber": "+615415145134"
      }
      ```

8. **GET /api/v1/order/{id}**
    - Description: Get an order by its ID.

9. **PUT /api/v1/order/{id}**
    - Description: Update an order, but only if it belongs to the authenticated user.
    - Request Body:
      ```json
      {
          "product": "product",
          "quantity": 54,
          "address": "address",
          "phoneNumber": "+615415145134"
      }
      ```

10. **DELETE /api/v1/order/{id}**
    - Description: Delete an order, but only if it belongs to the authenticated user.


## Main Stack

- **Spring Boot**: Framework for building Java-based web applications with simplified setup and configuration.
- **Spring Data JPA**: Simplifies database access and manipulation through JPA abstraction.
- **Spring Security**: Authentication and authorization framework for securing Spring applications.
- **Spring MVC (Web)**: Infrastructure for building web applications in the MVC architecture.
- **PostgreSQL**: Chosen database for persistence.
- **JWT (JSON Web Tokens)**: Standard for stateless authentication.
- **Apache Commons Lang**: Utility classes for common Java tasks.
- **Lombok**: Reduces boilerplate code by auto-generating methods during compilation.