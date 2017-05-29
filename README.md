# Pterodactyl-JAVA-API
 A java adaptation for the use of the Pterodactyl panel API

# How to use
For use you need to call the class PterodactylAPI like this:
```java 
PterodactylAPI api = new PterodactylAPI();
api.setMainURL("URL OF YOUR PANEL");
api.setPublicKey("PUBLIC API KEY");
api.setSecretKey("SECRET API KEY");
```


Then to retrieve the list of servers on the system you need to call the function GET
```java
api.getGetMethods().get(Methods.SERVERS_LIST_SERVERS);
```
it return all of server on the system in a String in JSON Format

In the GET Function you have this elements:
```java
USERS_LIST_USERS("api/admin/users"), //Returns all users currently existing on the system as a paginated result.
USERS_SINGLE_USER("api/admin/users/{params}"), //Returns information about a single user.
		
SERVERS_LIST_SERVERS("api/admin/servers"), //List all servers on the system.
SERVERS_SINGLE_SERVER("api/admin/servers/{params}"), //Lists information about a single server.
	
NODES_LIST_NODES("api/admin/nodes"), //Lists all nodes in the system.
NODES_SINGLE_NODE("api/admin/nodes/{params}"), //View data for a single node.
NODES_NODE_CONFIG("api/admin/nodes/{params}/config"), //Returns the config file contents for the node daemon.
	
LOCATIONS_LIST_LOCATIONS("api/admin/locations"), //Returns a list of all locations on the system and associated nodes.
		
SERVICES_LIST_SERVICES("api/admin/services"), //Returns a listing of all services on the system.
SERVICES_SINGLE_SERVICE("api/admin/services/{params}"); //Returns detailed information about a single service on the system.
```

When there is {params} you need to use the function:
```java
public String get(Methods method, String params){
```
