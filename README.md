# Netty JavaFX Chat

Preview:
<img width="1207" alt="image" src="https://github.com/overpathz/netty-javafx-chat/assets/72043323/18cd1da2-4707-4f8a-a4d9-c4d4240eae00">


### Features
**JavaFX User Interface**: Provides a user-friendly interface for chat interactions. The application has a welcome screen where users can enter their username and join the chat. Once connected, users can send and receive messages in real-time.

**Netty Networking**: Uses Netty for efficient network communication. The server listens for incoming client connections and broadcasts messages to all connected clients.

**Server & Client Architecture**: The project is divided into a server and a client module. The server handles incoming client connections and message broadcasting, while the client handles user interactions and displays messages.

### Quick Start
1) Start the server
Navigate to the server module and run the ChatServer class.
2) Start the client
Navigate to the client module and run the NettyChatApplication class.
Enter your username and join the chat (run as many clients as you want)

### Future Enhancements
- Add user authentication.
- Support private messaging between users.
- Implement chat history and message persistence.
