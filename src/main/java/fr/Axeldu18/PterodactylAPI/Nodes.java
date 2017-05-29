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

import org.json.JSONArray;
import org.json.JSONObject;

import fr.Axeldu18.PterodactylAPI.Classes.Node;
import fr.Axeldu18.PterodactylAPI.Classes.NodeAttributes;
import fr.Axeldu18.PterodactylAPI.Methods.GETMethods.Methods;

public class Nodes {

	private PterodactylAPI main;

	public Nodes(PterodactylAPI main){
		this.main = main;
	}
	
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

	public Node getNode(String id){
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(Methods.NODES_SINGLE_NODE, id));
		if(!jsonObject.has("data")){
			main.log(Level.SEVERE, jsonObject.toString());
			main.log(Level.SEVERE, "No NODE found with this ID");
			return null;
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
}
