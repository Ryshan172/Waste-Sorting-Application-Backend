# Waste Sorting SpringBoot REST API Application
### **About**
This is a Spring Boot application that provides a REST API for managing data related to waste categories, disposal guidelines, and recycling tips. The API enables clients to create, update, delete, and retrieve data using an in-memory H2 database.

### **Features**
- **REST API** for GET/POST/PUT/DELETE operations on:
    - Waste Categories
    - Disposal Guidelines
    - Recycling Tips
- **In-memory H2 database** for lightweight and fast data storage during runtime.
- **Database schema** documentation available in `docs/database-schema.dbml`.
- All code is contained in package **com.enviro.assessment.grad001.ryshanramlall**

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
4. H2 Database can be accessed via http://localhost:8080/h2-console
5. Database details are found in application.properties
---

### **Endpoints**
There are GET, POST, PUT and DELETE methods for each
### **Waste Categories**
- Can be access locally via http://localhost:8080/api/waste-categories
##### **Example POST Request body**
 ```
{
  "name": "Metal Waste",
  "description": "Waste materials made from metals like cans, sheets, and wires."
}
```
##### **Example GET Request response**
 ```
[
    {
        "id": 1,
        "name": "Metal Waste",
        "description": "Waste materials made from metals like cans, sheets, and wires."
    }
]
```

### **Disposal Guidelines**
- Can be access locally via http://localhost:8080/api/disposal-guidelines
##### **Example POST Request body**
 ```
{
  "guideline": "Dispose of batteries at your local hazardous waste facility.",
  "wasteCategoryId": 1
}
```
##### **Example GET Request response**
 ```
[
    {
        "id": 1,
        "guideline": "Dispose of batteries at your local hazardous waste facility.",
        "wasteCategoryId": 1,
        "wasteCategoryName": "Metal Waste"
    }
]
```

### **Recycling Tips**
- Can be access locally via http://localhost:8080/api/recycling-tips
##### **Example POST Request body**
 ```
{
  "tip": "Cans can be used for potplants.",
  "wasteCategoryId": 1
}
```
##### **Example GET Request response**
 ```
[
    {
        "id": 1,
        "tip": "Cans can be used for potplants.",
        "wasteCategoryId": 1,
        "wasteCategoryName": "Metal Waste"
    }
]
```
