# Jakarta EE HelloWorld Client Example

A comprehensive Jakarta EE 10 example demonstrating remote EJB invocation using OpenLiberty. This project showcases modern Jakarta EE development patterns including EJB remote interfaces, CDI, Jakarta Faces, and client-server communication.

## 📋 Project Overview

This example consists of three main components:

- **HelloWorldServer**: A Jakarta EE web application with EJB services
- **HelloWorldClient**: A standalone EJB client application  
- **HelloWorldClientEAR**: An Enterprise Application Archive for the client

## 🏗️ Architecture

```
┌─────────────────────┐    IIOP/RMI     ┌─────────────────────┐
│   HelloWorldClient  │ ──────────────► │  HelloWorldServer   │
│                     │    Port 2814    │                     │
│  - Standalone JAR   │                 │  - WAR Application  │
│  - EJB Client       │                 │  - EJB Provider     │
│  - Liberty Client   │                 │  - Web Interface    │
└─────────────────────┘                 └─────────────────────┘
                                                │
                                                │ HTTP
                                                │ Port 9080
                                                ▼
                                        ┌─────────────────────┐
                                        │   Web Browser       │
                                        │                     │
                                        │  - Jakarta Faces    │
                                        │  - Interactive UI   │
                                        └─────────────────────┘
```

## 🚀 Features

### HelloWorldServer
- **EJB 4.0**: Stateless session bean with remote interface
- **Jakarta Faces 4.0**: Modern web UI with AJAX support
- **CDI 4.0**: Dependency injection and managed beans
- **Servlet 6.0**: RESTful endpoints and test servlets
- **OpenLiberty**: Lightweight Jakarta EE runtime

### HelloWorldClient
- **Remote EJB Invocation**: Demonstrates client-side EJB access
- **JNDI Lookup**: Traditional and modern EJB lookup patterns
- **Error Handling**: Comprehensive exception management
- **Liberty Client**: Integrated with OpenLiberty client runtime

### HelloWorldClientEAR
- **Enterprise Archive**: Packages client as deployable EAR
- **Liberty Configuration**: Client-side Liberty server setup
- **IIOP Configuration**: Remote communication setup

## 🛠️ Prerequisites

- **Java 21** or higher
- **Apache Maven 3.6+**
- **OpenLiberty 25.0.0.12** (automatically downloaded by Maven)

## 🚀 Quick Start

### 1. Build the Projects

```bash
# Build the server
cd HelloWorldServer
mvn clean package

# Build the client EAR
cd HelloWorldClientEAR
mvn clean package
```

### 2. Start the Server

```bash
cd HelloWorldServer
mvn liberty:run
```

The server will start on:
- **HTTP**: http://localhost:9080
- **IIOP**: localhost:2814

### 3. Test the Web Interface

Open your browser and navigate to:
- **Main UI**: http://localhost:9080/HelloWorldServer/
- **Servlet Test**: http://localhost:9080/HelloWorldServer/test

### 4. Run the Standalone Client

In a new terminal:

### 5. Run the EAR Client

```bash
<wlp>/bin/client create HelloWorldClient
cd HelloWorldClientEAR
cp target/helloworldclient-1.0.ear <wlp>/usr/clients/HelloWorldClient/apps/
cp liberty/config/client.xml  <wlp>/usr/clients/HelloWorldClient/
<wlp>/bin/client run HelloWorldClient
```

## 🧪 Testing

### Web Interface Tests
The Jakarta Faces interface (`index.xhtml`) provides:
- **Automatic EJB Tests**: Direct injection and JNDI lookup
- **Interactive Greeting**: Personalized message form
- **Real-time Results**: AJAX-powered updates

### Servlet Tests
Access the servlet endpoints:
```bash
# Basic test
curl http://localhost:9080/HelloWorldServer/test

# Parameterized test  
curl "http://localhost:9080/HelloWorldServer/test?name=Jakarta"
```

### Client Application Tests
The standalone client tests:
- EJB initialization and cleanup
- Basic `sayHello()` method
- Parameterized `sayHello(String name)` method
- Edge cases (null/empty parameters)

output should be similar to this when running the client:
```             
Launching HelloWorldClient (WebSphere Application Server 25.0.0.12/wlp-1.0.108.cl251220251117-0302) on OpenJDK 64-Bit Server VM, version 21.0.9+10-LTS (en_US)
[AUDIT   ] CWWKE0907I: The client HelloWorldClient has been launched.
[INFO    ] TRAS0018I: The trace state has been changed. The new trace state is *=info:IIOP=all:Naming=all:com.ibm.example.*=all:org.apache.yoko.*=all:yoko.verbose.*=all.
[INFO    ] CWWKE0002I: The kernel started after 1.052 seconds
[INFO    ] CWWKF0007I: Feature update started.
[AUDIT   ] CWWKZ0058I: Monitoring dropins for applications.
[INFO    ] Aries Blueprint packages not available. So namespaces will not be registered
[INFO    ] WELD-000900: 5.1.1 (SP2)
[INFO    ] CXF message logging feature disabled
[INFO    ] CWWKZ0018I: Starting application HellowWorldClient.
[INFO    ] CWWKZ0136I: The HellowWorldClient application is using the archive file at the /Applications/IBM/wlp/usr/clients/HelloWorldClient/apps/helloworldclient-1.0.ear location.
[AUDIT   ] CWWKZ0001I: Application HellowWorldClient started in 0.263 seconds.
[AUDIT   ] CWWKF0034I: The client installed the following features: [beanValidation-3.0, cdi-4.0, expressionLanguage-5.0, jakartaeeClient-10.0, jdbc-4.2, jndi-1.0, jsonb-3.0, jsonp-2.1, mail-2.1, managedBeans-2.0, messagingClient-3.0, persistence-3.1, persistenceContainer-3.1, xmlBinding-4.0].
[INFO    ] CWWKF0008I: Feature update completed in 1.993 seconds.
[AUDIT   ] CWWKF0035I: The client HelloWorldClient is running.
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
[AUDIT   ] CWWKE1103I: Waiting for up to 30 seconds for the client to quiesce.
[INFO    ] CWWKE1104I: Client quiesce complete.
[AUDIT   ] CWWKZ0009I: The application HellowWorldClient has stopped successfully.
[AUDIT   ] CWWKE0908I: The client HelloWorldClient stopped after 6.079 seconds.
```
