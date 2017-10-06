/**
MIT License

Copyright (c) 2017 Axel Vatan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package fr.Axeldu18.PterodactylAPI;

import java.util.HashMap;
import java.util.logging.Level;

import org.apache.commons.lang.Validate;
import org.json.JSONArray;
import org.json.JSONObject;

import fr.Axeldu18.PterodactylAPI.Classes.Node;
import fr.Axeldu18.PterodactylAPI.Classes.NodeAttributes;
import fr.Axeldu18.PterodactylAPI.Methods.GETMethods.Methods;
import fr.Axeldu18.PterodactylAPI.Methods.POSTMethods;

public class Nodes {

	private PterodactylAPI main;

	public Nodes(PterodactylAPI main){
		this.main = main;
	}
	
	/**
	 * Get all nodes registered in panel
	 * @return Return all the NODE with ATTRIBUTES
	 */
	public HashMap<Integer, Node> getNodes(){
		HashMap<Integer, Node> nodesMap = new HashMap<Integer, Node>();
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(Methods.NODES_LIST_NODES));
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		for(int i=0;i<jsonArray.length();i++){
			JSONObject nodeJSON = jsonArray.getJSONObject(i);
			Node node = new Node();
			node.setId(nodeJSON.getInt("id"));
			node.setType(nodeJSON.getString("type"));
			JSONObject nodeAttributesJSON = nodeJSON.getJSONObject("attributes");
			NodeAttributes nodeAttributes = new NodeAttributes();
			nodeAttributes.setPublicNode(nodeAttributesJSON.getInt("public"));
			nodeAttributes.setName(nodeAttributesJSON.getString("name"));
			nodeAttributes.setLocationID(nodeAttributesJSON.getInt("location_id"));
			nodeAttributes.setFqdn(nodeAttributesJSON.getString("fqdn"));
			nodeAttributes.setScheme(nodeAttributesJSON.getString("scheme"));
			nodeAttributes.setBehindProxy(nodeAttributesJSON.getBoolean("behind_proxy"));
			nodeAttributes.setMemory(nodeAttributesJSON.getInt("memory"));
			nodeAttributes.setMemoryOverallocate(nodeAttributesJSON.getInt("memory_overallocate"));
			nodeAttributes.setDisk(nodeAttributesJSON.getInt("disk"));
			nodeAttributes.setDiskOverallocate(nodeAttributesJSON.getInt("disk_overallocate"));
			nodeAttributes.setUploadSize(nodeAttributesJSON.getInt("upload_size"));
			nodeAttributes.setDeamonListen(nodeAttributesJSON.getInt("daemonListen"));
			nodeAttributes.setDeamonSFTP(nodeAttributesJSON.getInt("daemonSFTP"));
			nodeAttributes.setDeamonBase(nodeAttributesJSON.getString("daemonBase"));
			nodeAttributes.setCreatedAT(nodeAttributesJSON.getString("created_at"));
			nodeAttributes.setUpdatedAT(nodeAttributesJSON.getString("updated_at"));
			node.setAttributes(nodeAttributes);
			nodesMap.put(node.getId(), node);
		}
		return nodesMap;
	}

	/**
	 * Get a node using ID
	 * @param id ID of the targeted node.
	 * @return Return all the targeted NODE with ATTRIBUTES
	 */
	public Node getNode(String id){
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(Methods.NODES_SINGLE_NODE, id));
		if(!jsonObject.has("data")){
			main.log(Level.SEVERE, jsonObject.toString());
			main.log(Level.SEVERE, "No NODE found with this ID");
			return new Node();
		}
		JSONObject nodeJSON = jsonObject.getJSONObject("data");
		Node node = new Node();
		node.setId(nodeJSON.getInt("id"));
		node.setType(nodeJSON.getString("type"));
		JSONObject nodeAttributesJSON = nodeJSON.getJSONObject("attributes");
		NodeAttributes nodeAttributes = new NodeAttributes();
		nodeAttributes.setPublicNode(nodeAttributesJSON.getInt("public"));
		nodeAttributes.setName(nodeAttributesJSON.getString("name"));
		nodeAttributes.setLocationID(nodeAttributesJSON.getInt("location_id"));
		nodeAttributes.setFqdn(nodeAttributesJSON.getString("fqdn"));
		nodeAttributes.setScheme(nodeAttributesJSON.getString("scheme"));
		nodeAttributes.setBehindProxy(nodeAttributesJSON.getBoolean("behind_proxy"));
		nodeAttributes.setMemory(nodeAttributesJSON.getInt("memory"));
		nodeAttributes.setMemoryOverallocate(nodeAttributesJSON.getInt("memory_overallocate"));
		nodeAttributes.setDisk(nodeAttributesJSON.getInt("disk"));
		nodeAttributes.setDiskOverallocate(nodeAttributesJSON.getInt("disk_overallocate"));
		nodeAttributes.setUploadSize(nodeAttributesJSON.getInt("upload_size"));
		nodeAttributes.setDeamonListen(nodeAttributesJSON.getInt("daemonListen"));
		nodeAttributes.setDeamonSFTP(nodeAttributesJSON.getInt("daemonSFTP"));
		nodeAttributes.setDeamonBase(nodeAttributesJSON.getString("daemonBase"));
		nodeAttributes.setCreatedAT(nodeAttributesJSON.getString("created_at"));
		nodeAttributes.setUpdatedAT(nodeAttributesJSON.getString("updated_at"));
		node.setAttributes(nodeAttributes);
		return node;
	}
	
	/**
	 * Create a new node
	 * @param name NAME of the new NODE.
	 * @param location_id LOCATION_ID corresponding to the location this node should exist under.
	 * @param publicNode PUBLIC Should this node be public on the system (allows auto-allocation of servers) (0 or 1).
	 * @param fqdn FQDN or IP to use for this node.
	 * @param scheme SCHEME Should be https or http depending on the scheme to use when connecting to the node.
	 * @param behind_proxy If you are running the daemon behind a proxy such as Cloudflare set true
	 * @param memory MEMORY Total amount of memory in MB to be available for allocation on this node.
	 * @param memory_overallocate MEMORY_OVERALLOCATE Percentage of memory overallocation allowed.
	 * @param disk DISK Amount of disk space allowed for allocation.
	 * @param disk_overallocate DISK_OVERALLOCATE Percentage of disk overallocation allowed.
	 * @param daemonBase DAEMON_BASE Base directory for daemon files.
	 * @param daemonListen DAEMON_LISTEN Default listening port for daemon.
	 * @param daemonSFTP DAEMON_SFTP Default SFTP port for daemon.
	 * @return if success it return the NODE class with ATTRIBUTES of the new node.
	 */
	public Node createNode(String name, int location_id, boolean publicNode, String fqdn, String scheme, boolean behind_proxy, int memory, int memory_overallocate, int disk, int disk_overallocate, String daemonBase, int daemonListen, int daemonSFTP){
		Validate.notEmpty(name, "The NAME is required");
		Validate.notNull(location_id, "The LOCATION_ID is required");
		Validate.notNull(publicNode, "The PUBLIC variable is required");
		Validate.notEmpty(fqdn, "The FQDN is required");
		Validate.notEmpty(scheme, "The SCHEME is required");
		Validate.notNull(behind_proxy, "The BEHIND_PROXY is required");
		Validate.notNull(memory, "The MEMORY is required");
		Validate.notNull(memory_overallocate, "The MEMORY_OVERALLOCATE is required");
		Validate.notNull(disk, "The DISK is required");
		Validate.notNull(disk_overallocate, "The DISK_OVERALLOCATE is required");
		Validate.notEmpty(daemonBase, "The DAEMON_BASE is required");
		Validate.notNull(daemonListen, "The DAEMON_LISTEN is required");
		Validate.notNull(daemonSFTP, "The DAEMON_SFTP is required");
		int publicNodeInt = 0;
		if(publicNode){
			publicNodeInt = 1;
		}
		int behindProxyInt = 0;
		if(behind_proxy){
			behindProxyInt = 1;
		}
		JSONObject jsonObject = new JSONObject(main.getPostMethods().call(main.getMainURL() + POSTMethods.Methods.NODES_CREATE_NODE.getURL(), 
				"name="+name+
				"&location_id="+location_id+
				"&public="+publicNodeInt+
				"&fqdn="+fqdn+
				"&behind_proxy="+behindProxyInt+
				"&scheme="+scheme+
				"&memory="+memory+
				"&memory_overallocate="+memory_overallocate+
				"&disk="+disk+
				"&disk_overallocate="+disk_overallocate+
				"&daemonBase="+daemonBase+
				"&daemonListen="+daemonListen+
				"&daemonSFTP="+daemonSFTP));
		if(!jsonObject.has("data")){
			main.log(Level.SEVERE, jsonObject.toString());
			return new Node();
		}
		JSONObject nodeJSON = jsonObject.getJSONObject("data");
		Node node = getNode(nodeJSON.get("id").toString());
		return node;
	}
}
