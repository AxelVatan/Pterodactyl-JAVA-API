package fr.Axeldu18.PterodactylAPI;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.Axeldu18.PterodactylAPI.Classes.Location;
import fr.Axeldu18.PterodactylAPI.Classes.LocationAttributes;
import fr.Axeldu18.PterodactylAPI.Methods.GETMethods.Methods;

public class Locations {

	private PterodactylAPI main;

	public Locations(PterodactylAPI main){
		this.main = main;
	}
	
	public HashMap<Integer, Location> getLocations(){
		HashMap<Integer, Location> locationsMap = new HashMap<Integer, Location>();
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(Methods.LOCATIONS_LIST_LOCATIONS));
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		for(int i=0;i<jsonArray.length();i++){
			JSONObject locationJSON = jsonArray.getJSONObject(i);
			Location server = new Location();
			server.setId(locationJSON.getInt("id"));
			server.setType(locationJSON.getString("type"));
			JSONObject locationAttributesJSON = locationJSON.getJSONObject("attributes");
			LocationAttributes locationAttributes = new LocationAttributes();
			locationAttributes.setShortName(locationAttributesJSON.getString("short"));
			locationAttributes.setLongName(locationAttributesJSON.getString("long"));
			locationAttributes.setCreatedAT(locationAttributesJSON.getString("created_at"));
			locationAttributes.setUpdatedAT(locationAttributesJSON.getString("updated_at"));
			server.setAttributes(locationAttributes);
			locationsMap.put(server.getId(), server);
		}
		return locationsMap;
	}
}
