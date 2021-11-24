# hotel-reservation-java-app
This is a hotel reservation java application for udacity java nano degree. 
Here are some brief descriptions:
## Main Components of the App
1. **CLI for the User Interface.** Users Interact with this application using Command Line.
2. **Business Logic.** The application handles users' commands from the interface here.
3. **Java Collections.** The application uses Java collections for in-memory storage.
![components](/img/components.png)
## Application Architecture
1. **User Interface (UI).** UI includes a main menu for the customers, and an admin menu for administrative functions.
2. **Resources.** Resources act as Application Programming Interface (API) to the UI.
3. **Services.** Services communicte with resources, and each other, to build the business logic necessary to provide feedback to the UI.
4. **Data models** Data models are used to represent the domain that are used within the system (e.g., rooms, reservations, and customers).
![architecture](/img/architecture.png)
## Layers
Layers design is used in this app to support modularization and decoupling for the conviencne of further development. It is achieved by ensuring there are no cross-communication calls from one layer to another.
