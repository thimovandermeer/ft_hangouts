docker build --build-arg JAR_FILE=build/libs/\*.jar -t ft_hangouts/server .
docker run -p 8080:8080 ft_hangouts/server
