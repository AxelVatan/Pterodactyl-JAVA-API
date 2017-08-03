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

import fr.Axeldu18.PterodactylAPI.Classes.Service;
import fr.Axeldu18.PterodactylAPI.Classes.ServiceAttributes;
import fr.Axeldu18.PterodactylAPI.Methods.GETMethods.Methods;

public class Services {

	private PterodactylAPI main;

	public Services(PterodactylAPI main){
		this.main = main;
	}
	
	/**
	 * @return Return all SERVICE with ATTRIBUTES
	 */
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

	/**
	 * @param id ID of the targeted service.
	 * @return Return the targeted SERVICE with ATTRIBUTES
	 */
	public Service getService(String id){
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(Methods.SERVICES_SINGLE_SERVICE, id));
		if(!jsonObject.has("data")){
			main.log(Level.SEVERE, jsonObject.toString());
			main.log(Level.SEVERE, "No SERVICE found with this ID");
			return new Service();
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
