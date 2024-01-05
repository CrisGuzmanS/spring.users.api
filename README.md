## How to install & start the project?

1. install the dependencies:
`mvn install`

2. start the server:
`mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9090 -X`

3. Visit the url:
`http://localhost:9090`

4. Do the requests documented in `users.http` file.