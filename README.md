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
