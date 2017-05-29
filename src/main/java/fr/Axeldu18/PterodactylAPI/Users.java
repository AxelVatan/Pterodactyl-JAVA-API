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

import fr.Axeldu18.PterodactylAPI.Classes.User;
import fr.Axeldu18.PterodactylAPI.Classes.UserAttributes;
import fr.Axeldu18.PterodactylAPI.Methods.GETMethods.Methods;

public class Users {

	private PterodactylAPI main;

	public Users(PterodactylAPI main){
		this.main = main;
	}

	public HashMap<Integer, User> getUsers(){
		HashMap<Integer, User> usersMap = new HashMap<Integer, User>();
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(Methods.USERS_LIST_USERS));
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		for(int i=0;i<jsonArray.length();i++){
			JSONObject userJSON = jsonArray.getJSONObject(i);
			User user = new User();
			user.setId(userJSON.getInt("id"));
			user.setType(userJSON.getString("type"));
			JSONObject userAttributesJSON = userJSON.getJSONObject("attributes");
			UserAttributes userAttributes = new UserAttributes();
			userAttributes.setUuid(userAttributesJSON.getString("uuid"));
			userAttributes.setUsername(userAttributesJSON.getString("username"));
			userAttributes.setEmail(userAttributesJSON.getString("email"));
			userAttributes.setFirstName(userAttributesJSON.get("name_first").toString());
			userAttributes.setLastName(userAttributesJSON.get("name_last").toString());
			userAttributes.setLanguage(userAttributesJSON.getString("language"));
			userAttributes.setRootAdmin(userAttributesJSON.getInt("root_admin"));
			userAttributes.setUsetotp(userAttributesJSON.getInt("use_totp"));
			userAttributes.setGravatar(userAttributesJSON.getInt("gravatar"));
			userAttributes.setCreatedAT(userAttributesJSON.getString("created_at"));
			userAttributes.setUpdatedAT(userAttributesJSON.getString("updated_at"));
			user.setAttributes(userAttributes);
			usersMap.put(user.getId(), user);
		}
		return usersMap;
	}

	public User getUser(String mail){
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(Methods.USERS_SINGLE_USER, mail));
		if(!jsonObject.has("data")){
			main.log(Level.SEVERE, jsonObject.toString());
			main.log(Level.SEVERE, "No USER found with this ID");
			return null;
		}
		JSONObject userJSON = jsonObject.getJSONObject("data");
		User user = new User();
		user.setId(userJSON.getInt("id"));
		user.setType(userJSON.getString("type"));
		JSONObject userAttributesJSON = userJSON.getJSONObject("attributes");
		UserAttributes userAttributes = new UserAttributes();
		userAttributes.setUuid(userAttributesJSON.getString("uuid"));
		userAttributes.setUsername(userAttributesJSON.getString("username"));
		userAttributes.setEmail(userAttributesJSON.getString("email"));
		userAttributes.setFirstName(userAttributesJSON.get("name_first").toString());
		userAttributes.setLastName(userAttributesJSON.get("name_last").toString());
		userAttributes.setLanguage(userAttributesJSON.getString("language"));
		userAttributes.setRootAdmin(userAttributesJSON.getInt("root_admin"));
		userAttributes.setUsetotp(userAttributesJSON.getInt("use_totp"));
		userAttributes.setGravatar(userAttributesJSON.getInt("gravatar"));
		userAttributes.setCreatedAT(userAttributesJSON.getString("created_at"));
		userAttributes.setUpdatedAT(userAttributesJSON.getString("updated_at"));
		user.setAttributes(userAttributes);
		return user;
	}
}
