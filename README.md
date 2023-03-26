# favourite-movies
A movie application which will use the OMDB api to record and modify the favourite movies for users<br /> 
Use `-Dspring.profiles.active=dev` to run locally with a valid api key set in [application-dev.properties](/src/main/resources/application-dev.properties)<br /> 
Use `maven clean install` and run the [FavouriteMoviesApplication.java](/src/main/java/dev/sharanggupta/favouritemovies/FavouriteMoviesApplication.java) as a java 17 application.
To run it on k8s, ensure you're in the base directory and execute `kubectl apply -f kubernetes/`

Currently deployed on a linode kubernetes cluster, try it at http://178.79.177.139:30000/movie/tt0103776
