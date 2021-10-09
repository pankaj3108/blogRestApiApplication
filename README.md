# blogRestApiApplication
This is a REST API backend application built using Java, Spring boot which gives functionality to add post, delete post, update post, add comment and delete comment in post.

It contains a JAR file in src/target folder.
open that folder and copy that jar in your local system.

now open command prompt and enter below command:
<imp> java -jar myBlog-0.0.1-SNAPSHOT.jar </imp>

important thing, this is running on 8081 port, make sure other process is not using 8081 port.

Now hitting the previous command, application server will run.

Now you can use/test this application in postman.

below are the Api's used in the application.

** To view All posts stored in the database
method: GET
URL: http://localhost:8081/blogApplication/posts

** To view specific post 
method: GET
URL: http://localhost:8081/blogApplication/posts/{id}

** To view All posts created by a certain user
method: GET
URL: http://localhost:8081/blogApplication/posts/users/{id}

** To add a user in the system
method: POST
URL: http://localhost:8081/blogApplication/addUser

Request Body :
{
    "userId" : ${userId},
    "firstName" : ${firstName},
    "lastName" : ${lastName},
    "gender" : ${true/false}, /* true - male, false - female */
    "email" : ${email ID},
    "streetName" : ${street Name},
    "city" : ${city},
    "state" : ${state},
    "country" : ${country},
    "premiumUser" : ${true/false}, /* true - is premium user, false - not a premium user */
    "dateOfBirth" : ${date of birth}
}

** To add a post in the database by a user already registered
method: POST
URL: http://localhost:8081/blogApplication/addPost/{userid}

Request Body:
{
    "postId" : ${postId},
    "title" : ${title},
    "description" : ${description},
    "body" : ${body of the post},
    "type" : ${TECH/FASHION/EDUCATION/ADVENTURE}
}

** To add comment in a post
method: POST
URL: http://localhost:8081/blogApplication/posts/{id}/comment

Request Body :
{
    "commentId" : ${commentId},
    "userId" : ${userID},
    "text" : ${comment body}
}

** To retrieve comments of a certain post
method: GET
URL: http://localhost:8081/blogApplication/posts/{id}/comments

** To update user profile
method: PUT
URL: http://localhost:8081/blogApplication/users/user/{id}

Request Body :
{
    "userId" : ${userId},
    "firstName" : ${firstName},
    "lastName" : ${lastName},
    "gender" : ${true/false}, /* true - male, false - female */
    "email" : ${email ID},
    "streetName" : ${street Name},
    "city" : ${city},
    "state" : ${state},
    "country" : ${country},
    "premiumUser" : ${true/false}, /* true - is premium user, false - not a premium user */
    "dateOfBirth" : ${date of birth}
}

** To delete a post 
method: DELETE
URL: http://localhost:8081/blogApplication/posts/{id}
