# Toy Redis
Repositorio destinado a la resolución del desafío técnico WitBooking

## Starting Toy Redis
### Maven command
mvn clean install
mvn spring-boot:run

### Docker command
docker build --build-arg JAR_FILE=target/*.jar -t toy-redis .
docker run --publish 8080:8080 toy-redis

## List of shell commands
shell:> help

### AVAILABLE COMMANDS

#### Built-In Commands
* clear: Clear the shell screen.
* exit, quit: Exit the shell.
* help: Display help about available commands.
* history: Display or save the history of previously run commands
* script: Read and execute commands from a file.
* stacktrace: Display the full stacktrace of the last error.

#### Toy Redis Cli Impl
* SET: add with optional expiration in seconds
* GET: find by key
* DEL: delete by key
* DBSIZE: count the number of keys
* INCR: increment a value (numeric) by key
* ZADD: TO BE IMPLEMENTED
* ZCARD: TO BE IMPLEMENTED
* ZRANGE: TO BE IMPLEMENTED
* ZRANK: TO BE IMPLEMENTED

## Postman Integration Test
In order to test the rest implementation, I included a postman collection with api call examples into:
toy-redis.postman_collection.json