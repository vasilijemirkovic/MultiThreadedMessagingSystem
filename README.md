# MultiThreadedMessagingSystem

## Overview

This project demonstrates a multithreaded client-server application in Java. The server can handle multiple client connections concurrently, and clients send and receive serialized message objects.

## Project Structure

- `Client.java`: The client application that connects to the server, sends messages, and receives responses.
- `Server.java`: The server application that listens for client connections and processes each client's message in a separate thread.
- `Message.java`: A serializable class representing the message object used for communication between the client and server.