# Binary Search Tree Visualizer

A web application that allows users to create, visualize, and manage Binary Search Trees (BSTs). The application provides both standard and balanced BST creation, along with the ability to save and view previously created trees.

This application uses a custom implementation of Binary Search Trees to store and visualize tree structures.

## Features

- **Tree Creation**: Create Binary Search Trees from a list of numbers
- **Balanced Trees**: Automatically create balanced BSTs from input numbers
- **Tree Visualization**: Interactive visualization of BST structure
- **Tree History**: Save and view previously created trees
- **Responsive Design**: Works on desktop and mobile devices

## System Requirements

- Java Development Kit (JDK) 17 or higher
- Node.js 14 or higher
- npm 6 or higher
- Maven 3.6 or higher
- MySQL 8.0 or higher

## Installation

1. Clone the repository:
   ```
   git clone https://github.com/SearchingSteve/bst-visualizer.git
   ```
2. Navigate to the project directory:
   ```
   cd bst-visualizer
   ```
3. Create the MySQL database:
   ```
   mysql -u root -p
   CREATE DATABASE bst_visualizer_db;
   exit;
   ```
4. Configure the database connection:
   - Open `backend/src/main/resources/application.properties`
   - Update the following properties with your MySQL credentials:
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/bst_visualizer_db
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```
   - Replace `your_username` and `your_password` with your actual MySQL username and password
5. Set up the backend:
   ```
   cd backend
   mvn clean install
   ```
6. Set up the frontend:
   ```
   cd ../frontend
   npm install
   ```

## Running the Application

1. Start the backend server:

   ```
   cd backend
   mvn spring-boot:run
   ```

   The backend will start on http://localhost:8080

2. In a new terminal, start the frontend development server:

   ```
   cd frontend
   npm start
   ```

   The frontend will start on http://localhost:3000

3. Open your browser and navigate to http://localhost:3000

## Usage

The application provides a user-friendly interface with the following features:

1. **Creating a Tree**:

   - Enter a comma-separated list of numbers in the input field
   - Choose between creating a standard BST or a balanced BST using the toggle
   - Click "Create Tree" to visualize the BST
   - The tree will be displayed with nodes as circles and edges connecting them

2. **Viewing Previous Trees**:

   - Navigate to the "Previous Trees" page using the navigation menu
   - View a list of all previously created trees
   - Each tree card shows the input numbers used to create it
   - Click "View Details" to see the full visualization of a saved tree

3. **Tree Visualization**:
   - The tree is displayed with nodes as circles and edges connecting them
   - Each node shows its value
   - The visualization is interactive and responsive
   - You can see the hierarchical structure of the BST

## Project Structure

The application is organized into two main components:

### Backend (Spring Boot)

- `controller`: Contains REST API endpoints
- `service`: Implements BST logic and business operations
- `model`: Defines data entities and DTOs
- `repository`: Handles data persistence

### Frontend (React)

- `components`: React components for the UI
- `services`: API service for backend communication
- `App.js`: Main application component
- `index.js`: Application entry point

## API Endpoints

- `POST /process-numbers`: Creates a standard BST from input numbers
- `POST /process-balanced-numbers`: Creates a balanced BST from input numbers
- `GET /previous-trees`: Retrieves all previously saved trees

## Technologies Used

### Backend

- Spring Boot
- Spring Data JPA
- MySQL Database
- Jackson for JSON processing

### Frontend

- React
- React Router
- Axios for API calls
- CSS for styling

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b new-feature`
3. Commit your changes: `git commit -am 'Add new feature'`
4. Push to the branch: `git push origin new-feature`
5. Submit a pull request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

- **Stephen Crocker**
- GitHub: [SearchingSteve](https://github.com/SearchingSteve)
