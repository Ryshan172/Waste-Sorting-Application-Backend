# Waste Sorting SpringBoot REST API Application
### **About**
This is a Spring Boot application that provides a REST API for managing data related to waste categories, disposal guidelines, and recycling tips. The API enables clients to create, update, delete, and retrieve data while leveraging an in-memory H2 database.

### **Features**
- **REST API** for GET/POST/PUT/DELETE operations on:
    - Waste Categories
    - Disposal Guidelines
    - Recycling Tips
- **In-memory H2 database** for lightweight and fast data storage during runtime.
- **Database schema** documentation available in `docs/database-schema.dbml`.

---

### **Getting Started**

#### **Prerequisites**
- Java 17 or later.
- Maven for dependency management (optional if not using the prebuilt JAR).

#### **Running the Application**
1. Download or clone the repository.
2. Navigate to the project directory.
3. Run the JAR file using the command:
   ```bash  
   java -jar releases/waste-sorting-grad001ryshanramlall-0.0.1-SNAPSHOT.jar  