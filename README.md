# Connecting React Frontend and Java Backend
If you are not comfortable about how Java and React work together in building a dynamic web application, feel free to take a look at what I have done!

## How Backend and frontend work together - Project Structure
### Java - backend
* plain java objects/entities. `User` 
    * `User` has following properties
        * `id`, `name`, `pronouns`
* `UserService` has following methods
    * `addUser`, `getUsers`, `addUserPronouns`
* `loadData`
    * this will add a temporary user on the startup
* `UserController` - most important part
```java
@RestController
@CrossOrigin(origins = "http://localhost:3000") 
// port 3000 is where our frontend will run and make a request to this API
// without this we will run into cors issues
public class UserController {

    @Autowired
    private UserService userService;  
      
    @GetMapping("/users")
    public List<User> getUsers() {  
        return userService.getUsers();
    }

    @PostMapping("/users/{userId}/update")
    public void addUserPronouns(@PathVariable String userId, @RequestParam String pronouns) {
        fakeDataService.addUserPronouns(Long.valueOf(userId), pronouns);  
    }
  }
```
### React - frontend
- `App.js`
```jsx
import React, { useState, useEffect } from "react";
import axios from "axios";

const userAPI = "http://localhost:8080/users"; // making request to the API we created earlier

const Users = () => {
  const [users, setUsers] = useState([]);
  const [pronouns, setPronouns] = useState("");

  const fetchUsers = () => {
    axios.get(userAPI).then((res) => {
      setUsers(res.data);
    });
  };

  useEffect(() => {
    fetchUsers();
  }, [pronouns]);

  const handleChange = (e) => {
    setPronouns(e.target.value);
  };

  const handleSubmit = (userId) => (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append("pronouns", pronouns);
    axios.post(`${userAPI}/${userId}/update`, formData);
    setPronouns("");
  };

  return users.map((user, index) => {
    return (
      <div className="user" key={index}>
        <h3>{user.firstName}</h3>
        <p>{user.nationality}</p>
        <p>{user.pronouns}</p>
        <form onSubmit={handleSubmit(user.id)}>
          <input
            name="pronouns"
            value={pronouns}
            onChange={handleChange}
            type="text"
            placeholder="pronouns"
          />
          <button type="submit">Add Pronouns</button>
        </form>
      </div>
    );
  });
};

function App() {
  return <Users />;
}
```
    

## Deploying Java Backend Application to Azure

 - `mvn clean install`
    * creates a jar file
 - add azure web app plugin
```xml
<plugin> 
 <groupId>com.microsoft.azure</groupId>  
 <artifactId>azure-webapp-maven-plugin</artifactId>
 <version>1.7.0</version>
</plugin> 
```
 - `mvn azure-webapp:config`
 - change few configurations
```xml
 <plugin> 
   <groupId>com.microsoft.azure</groupId>  
   <artifactId>azure-webapp-maven-plugin</artifactId>  
   <version>1.7.0</version>  
   <!--   ServicePlan85e60b57-b475-44ea <- we want to use the same service plan for the front-end as well-->
   <configuration>
     <schemaVersion>V2</schemaVersion>
     <resourceGroup>connect-backend-to-frontend-rg</resourceGroup>
     <appName>connect-backend-to-frontend</appName>
     <pricingTier>B1</pricingTier>
     <region>westeurope</region>
     <!--          add this appSettings-->
     <appSettings>
       <property>
         <name>JAVA_OPTS</name>
         <value>-Dserver.port=80</value>
       </property>
     </appSettings>
     <runtime>
       <os>linux</os>
       <javaVersion>java11</javaVersion>
       <webContainer>java11</webContainer>
     </runtime>
     <deployment>
       <resources>
         <resource>
           <directory>${project.basedir}/target</directory>
           <includes>
             <include>*.jar</include>
           </includes>
         </resource>
       </resources>
     </deployment>
   </configuration>
 </plugin>  
```
 - `mvn azure-webapp:deploy`
 
 ## Deploying React Frontend Application to Azure
 
 - `npm run build`
    * creates a production ready folder
 - now we are ready to deploy an azure html application
 - `cd build`
 - `az webapp up --name front-end-example --plan ServicePlan85e60b57-b475-44ea --resource-group connect-frontend-to-backend-rg --location westeurope --html`
    - this takes entire content of the build folder, it creates an web application (using index.html) connected to the backend application
    - **only the service plan has to be the same** 
 
 **Web application is now deployed and ready for use!!!**