# Pterodactyl-JAVA-API
 A java adaptation for the use of the Pterodactyl panel API

# How to use (VER:1.4)
For use you need to call the class PterodactylAPI like this:
```java 
PterodactylAPI api = new PterodactylAPI();
api.setMainURL("URL OF YOUR PANEL");
api.setPublicKey("PUBLIC API KEY");
api.setSecretKey("SECRET API KEY");
api.setSecureConection(true|false); //Set secure conection (default false)
```
Then to retrieve the list of servers on the system you need to call the function getServer() in Servers classes
```java
HashMap<Integer, Server> servers = this.getServers().getServers();
```

Now there is 5 Classes to get informations from the API
- Users
- Servers
- Nodes
- Locations
- Services

For create a new user you need to call the function like this: 
```java
User user = this.getUsers().createUser(email, username, first_name, last_name, password, root_admin);
System.out.println(user.toString());
```
It return the User class with all the attributes of the new user
