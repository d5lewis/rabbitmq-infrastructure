# RabbitMQ Test App

The RabbitMQ Test App was built to explore the RabbitMQ framework and 
operates as a sender or reciever to/from a RabbitMQ message queue.  

By default, that message queue is a 'localhost', but can be configured
by modifying the constants in the src/main/java/utils/Constants.java

## Getting Started

To build and install the project follow the steps below:

    1) Clone the repo.
    2) Open Intellij, and then open the root directory of the cloned repo.
    3) The project should be setup as a gradle project with Java

### Prerequisites

This code is built to run on Java 11.  On some systems, the JAVA_HOME environment variable must be set to the Java 11 install.  Also, be sure
you have a current installation of RabbitMQ running.  

---
**NOTE**

Commands below are for Bash shells on *nix-based systems.  Mileage may vary on Powershell or Windows CMD. 

---

To start your RabbitMQ local host:
```shell
sudo rabbitmqctl start_app
```

### Usage

Run the app from the sender configuration with the command: 
```shell
./gradlew run --args='-s'
```
Run the app from in the receiver configuration with the command: 

```shell
./gradlew run --args='-r'
```
