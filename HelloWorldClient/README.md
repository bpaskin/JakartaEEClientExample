# HelloWorld EJB Client

This is a Liberty client application that demonstrates how to invoke remote EJB methods on the HelloWorldServer.

## Project Structure

```
HelloWorldClient/
├── pom.xml                                    # Maven configuration for client
├── src/main/java/com/ibm/example/
│   ├── HelloWorldRemote.java                 # Remote EJB interface
│   ├── HelloWorldClient.java                 # EJB client implementation
│   └── HelloWorldClientMain.java             # Main application class
├── src/main/liberty/config/
│   └── client.xml                            # Liberty client configuration
└── README.md                                 # This file
```

## Prerequisites

1. Java 21 or later
2. Maven 3.6 or later
3. HelloWorldServer must be running on localhost:2809

## Building the Client

```bash
cd HelloWorldClient
mvn clean compile
```

## Running the Client

### Option 1: Using Maven exec plugin
```bash
mvn exec:java
```

### Option 2: Using Liberty Maven plugin
```bash
mvn liberty:run-client
```

### Option 3: Manual execution
```bash
mvn compile
java -cp target/classes:target/dependency/* com.ibm.example.HelloWorldClientMain
```

## Expected Output

When the client runs successfully, you should see output similar to:

```
Starting HelloWorld EJB Client...
Initializing EJB client...
Successfully looked up HelloWorld EJB

=== Testing sayHello() ===
Result: Hello World from Jakarta EE 10 EJB!

=== Testing sayHello(String name) ===
Result: Hello Liberty Client from Jakarta EE 10 EJB!

=== Testing sayHello with empty name ===
Result: Hello World from Jakarta EE 10 EJB!

=== Testing sayHello with null name ===
Result: Hello World from Jakarta EE 10 EJB!

=== All tests completed successfully! ===
EJB client cleanup completed.
```

## Troubleshooting

### Common Issues

1. **Connection refused**: Make sure HelloWorldServer is running
2. **JNDI lookup fails**: Verify the server is accessible on localhost:2809
3. **ClassNotFoundException**: Ensure all dependencies are in the classpath

### Server Setup

Before running the client, start the HelloWorldServer:

```bash
cd ../HelloWorldServer
mvn liberty:run
```

The server should be accessible at `http://localhost:9080/HelloWorldServer/`

## Configuration

The client configuration is in [`src/main/liberty/config/client.xml`](src/main/liberty/config/client.xml):

- **ejbRemoteClient-1.0**: Enables EJB remote client functionality
- **appClientSupport-1.0**: Provides application client support
- **jndi-1.0**: Enables JNDI lookups

The IIOP connection is configured to connect to `localhost:2809` where the HelloWorldServer should be running.