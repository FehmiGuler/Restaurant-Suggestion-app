# Restaurant-Suggestion-app

This project is a web service for restaurant recommendations based on data provided by Solr. It offers recommendations to users based on the review and distance indexes of restaurants. The project is developed using Java Spring Boot.

![](https://i.imgur.com/JJWuFZi.png)

## Installation

1. Clone the project source code to your computer:

    ```bash
    git clone https://github.com/FehmiGuler/Restaurant-Suggestion-app.git
    ```

2. Navigate to the project directory:

    ```bash
    cd Restaurant-Suggestion-app
    ```

3. Use Maven to install project dependencies:

    ```bash
    mvn clean install
    ```

4. Set up and run the Solr server. 

## Usage

1. Run the project start command:

   ### Note: I have dockerized the project but some connection issue occured between postgres and other services. So You need to run projects one by one.

    ```bash
    mvn spring-boot:run
    ```

3. Once the all services are successfully started, you can access the user_service by sending an HTTP request to `http://localhost:8080/swagger-ui/index.html`.

## Endpoints
### User Controller

The User Controller manages user data and recommendations.

- **GET /api/v1/users**: Retrieve all users.
- **GET /api/v1/users/{id}**: Retrieve a user by ID.
- **GET /api/v1/users/with-name/{name}**: Retrieve a user by name.
- **POST /api/v1/users**: Save a new user.
- **PUT /api/v1/users/{id}**: Update a user.
- **DELETE /api/v1/users/{id}**: Delete a user.
- **PATCH /api/v1/users/{id}/activate**: Activate a user.
- **GET /api/v1/users/{id}/recommend-restaurant**: Recommend restaurants to a user based on distance.

### User Review Controller

The User Review Controller manages user reviews for restaurants.

- **GET /api/v1/user-reviews/with-user-id/{userId}**: Retrieve all user reviews by user ID.
- **GET /api/v1/user-reviews/with-restaurant-id/{restaurantId}**: Retrieve all user reviews by restaurant ID.
- **POST /api/v1/user-reviews**: Save a new user review.
- **PATCH /api/v1/user-reviews/{id}/edit-comment**: Edit the comment of a user review.
- **PATCH /api/v1/user-reviews/{id}/edit-rate**: Edit the rate of a user review.
- **DELETE /api/v1/user-reviews/{id}**: Delete a user review by ID.

### Restaurant Controller

The Restaurant Controller manages restaurant data and nearby restaurants.

- **GET /api/v1/restaurants**: Retrieve all restaurants.
- **GET /api/v1/restaurants/{id}**: Retrieve a restaurant by ID.
- **POST /api/v1/restaurants**: Save a new restaurant.
- **GET /api/v1/restaurants/nearby**: Retrieve nearby restaurants based on latitude, longitude, and distance.


## Contributing

1. Fork this project.
2. Add new features or fix bugs.
3. Name your changes descriptively and provide clear explanations.
4. Submit a pull request.

## License

This project is licensed under the MIT License. For more information, see the `LICENSE` file.
