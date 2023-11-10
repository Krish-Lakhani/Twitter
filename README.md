# Twitter Clone - Spring Boot Application 🐦🔄

Welcome to the Twitter Clone, a modern Spring Boot application designed to provide a seamless social networking experience. This project aims to replicate key features of popular microblogging platforms, allowing users to post, like, comment, follow, and more.

## Frameworks and Languages

The Twitter Clone is developed using the following frameworks and languages:

- **Java:** The programming language used for backend development.
- **Spring Boot:** A Java-based framework for building web applications.
- **Spring Data JPA:** Simplifying data access for a smoother user experience.
- **MySQL:** The chosen database management system.
- **Swagger:** Creating interactive API documentation.
- **Lombok:** Reducing boilerplate code, allowing a focus on innovative features.

---

## Project Structure

The project follows a standard Spring Boot MVC structure and consists of the following components:

- **Controller:** Contains the API endpoints and request mappings.
- **Service:** Implements business logic and interacts with the repository.
- **Repository:** Handles data access to the MySQL database.
- **DTO (Data Transfer Object):** Represents the data structure exchanged between the API and the client.
- **Model:** Represents the data models (entities) mapped to the MySQL database.

## Features and Functionality

### User Management API

- **Signup:**
  - Endpoint: `/user/signup`
  - Method: POST
  - Description: Sign up for a new account.

- **Login:**
  - Endpoint: `/user/login`
  - Method: GET
  - Description: Log in securely to your account.

- **Logout:**
  - Endpoint: `/user/logout`
  - Method: GET
  - Description: Log out of your account.

- **Create Post:**
  - Endpoint: `/user/post`
  - Method: POST
  - Description: Create a new post on the Twitter clone platform.

- **Show User Posts:**
  - Endpoint: `/user/posts/{username}`
  - Method: GET
  - Description: Retrieve and display all posts associated with the specified user's username.

- **Delete Post:**
  - Endpoint: `/user/post/delete/{postId}`
  - Method: DELETE
  - Description: Delete a specific post using the post ID.

- **Like Post:**
  - Endpoint: `/user/post/like/{postId}`
  - Method: POST
  - Description: Add a like to a post.

- **Total Likes:**
  - Endpoint: `/user/post/likes/{postId}`
  - Method: GET
  - Description: Retrieve the total number of likes for a specific post.

- **Total Comments:**
  - Endpoint: `/user/post/comments/{postId}`
  - Method: GET
  - Description: Retrieve the total number of comments for a specific post.

- **Follow User:**
  - Endpoint: `/user/follow/{follower}/{following}`
  - Method: POST
  - Description: Allow a user to follow another user.

- **Unfollow User:**
  - Endpoint: `/user/unfollow/{follower}/{following}`
  - Method: DELETE
  - Description: Allow a user to unfollow another user.

- **Add Comment:**
  - Endpoint: `/user/post/comment/{postId}`
  - Method: POST
  - Description: Add a comment to a post.

- **Remove Comment:**
  - Endpoint: `/user/post/comment/remove/{commentId}`
  - Method: DELETE
  - Description: Remove a comment from a post.

- **Reset Password:**
  - Endpoint: `/user/password/reset/{username}`
  - Method: POST
  - Description: Reset the password for a user.

- **Verify OTP:**
  - Endpoint: `/user/password/verify-otp/{username}`
  - Method: POST
  - Description: Verify the OTP (One-Time Password) sent to the user's email during the password reset process.

---


1. Clone the repository to your local machine:

   ```shell
      git clone https://github.com/Krish-Lakhani/Twitter.git
2. *Set up the required database (MySQL) and configure the database connection* in the `application.properties` file, and also you have to set up SMTP for email OTP.

3. *Build and run the application* using Maven or your preferred IDE.
  


## Contact

Feel free to connect with me to learn more or discuss the technology behind it:

- Email: krishlakhani92@email.com
- LinkedIn: https://www.linkedin.com/in/krish-lakhani

- ### The below video shows how the app works:
https://github.com/Krish-Lakhani/Twitter/assets/137669336/dc65c56d-4067-4ade-9ea5-b005287bf8a6
