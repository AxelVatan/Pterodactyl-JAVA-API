package fr.Axeldu18.PterodactylAPI;

import java.util.HashMap;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.Axeldu18.PterodactylAPI.Classes.Service;
import fr.Axeldu18.PterodactylAPI.Classes.ServiceAttributes;
import fr.Axeldu18.PterodactylAPI.Methods.GETMethods.Methods;

public class Services {

	private PterodactylAPI main;

	public Services(PterodactylAPI main){
		this.main = main;
	}
	
	public HashMap<Integer, Service> getServices(){
		HashMap<Integer, Service> servicesMap = new HashMap<Integer, Service>();
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(Methods.SERVICES_LIST_SERVICES));
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		for(int i=0;i<jsonArray.length();i++){
			JSONObject serviceJSON = jsonArray.getJSONObject(i);
			Service service = new Service();
			service.setId(serviceJSON.getInt("id"));
			service.setType(serviceJSON.getString("type"));
			JSONObject serviceAttributesJSON = serviceJSON.getJSONObject("attributes");
			ServiceAttributes serviceAttributes = new ServiceAttributes();
			serviceAttributes.setAuthor(serviceAttributesJSON.getString("author"));
			serviceAttributes.setName(serviceAttributesJSON.getString("name"));
			serviceAttributes.setDescription(serviceAttributesJSON.getString("description"));
			serviceAttributes.setFolder(serviceAttributesJSON.getString("folder"));
			serviceAttributes.setStartup(serviceAttributesJSON.getString("startup"));
			serviceAttributes.setIndexFile(serviceAttributesJSON.getString("index_file"));
			serviceAttributes.setCreatedAT(serviceAttributesJSON.getString("created_at"));
			serviceAttributes.setUpdatedAT(serviceAttributesJSON.getString("updated_at"));
			service.setAttributes(serviceAttributes);
			servicesMap.put(service.getId(), service);
		}
		return servicesMap;
	}

	public Service getService(String id){
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(Methods.SERVICES_SINGLE_SERVICE, id));
		if(!jsonObject.has("data")){
			main.log(Level.SEVERE, jsonObject.toString());
			main.log(Level.SEVERE, "No SERVICE found with this ID");
			return null;
		}
		JSONObject serviceJSON = jsonObject.getJSONObject("data");
		Service service = new Service();
		service.setId(serviceJSON.getInt("id"));
		service.setType(serviceJSON.getString("type"));
		JSONObject serviceAttributesJSON = serviceJSON.getJSONObject("attributes");
		ServiceAttributes serviceAttributes = new ServiceAttributes();
		serviceAttributes.setAuthor(serviceAttributesJSON.getString("author"));
		serviceAttributes.setName(serviceAttributesJSON.getString("name"));
		serviceAttributes.setDescription(serviceAttributesJSON.getString("description"));
		serviceAttributes.setFolder(serviceAttributesJSON.getString("folder"));
		serviceAttributes.setStartup(serviceAttributesJSON.getString("startup"));
		serviceAttributes.setIndexFile(serviceAttributesJSON.getString("index_file"));
		serviceAttributes.setCreatedAT(serviceAttributesJSON.getString("created_at"));
		serviceAttributes.setUpdatedAT(serviceAttributesJSON.getString("updated_at"));
		service.setAttributes(serviceAttributes);
		return service;
	}
}
