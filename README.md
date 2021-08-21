# Home Assignment - backend

Basic user managment project

including the use of basic authentication 

* API:
	* GET /users/displayAll - displays all the registered users in the system. [1]
	* POST /users - adds new users.
	* DELETE /users?id=<userId> - gets a user id and delete the user with this specific id from the system [1]
	* GET /users?email=<userEmail> - gets a user email and return its information [1]
	* GET /users?id=<userId> - gets a user id and return its information [1]
	* PUT /users?id=<userId> - gets a user id by query params and a user information by request body, the function updates the user spesific user (which has the id from the params) with the user information [1]
	* GET /health - return information about the server health state

[1] using a basic authentication - has to be invoked by a registered user

	
There is a postman file which contains all the api mapping with run example

postman file name: SonyHome-Assi.postman_collection.json


/users

# For building the server on local docker

run the command in the project directory: docker build -t home-assignment.jar . 

# For running the docker on local machine

In the project directory, you can run: docker run 8080:8080 home-assignment.jar

# Deploy to heroku

https://dashboard.heroku.com/apps/tranquil-peak-25178/deploy/github

Deployment via GitHub (as pipline), after pushing new version to the main branch 

press the "Deploy Branch" button and it should build and deploy the project

Deployment URL: https://tranquil-peak-25178.herokuapp.com

(Use Postman with the postman file attachecd for the server interactions tests) 
