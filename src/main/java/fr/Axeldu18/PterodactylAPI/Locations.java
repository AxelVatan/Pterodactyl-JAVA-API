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
