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

import fr.Axeldu18.PterodactylAPI.Classes.User;
import fr.Axeldu18.PterodactylAPI.Classes.UserAttributes;
import fr.Axeldu18.PterodactylAPI.Methods.DELETEMethods;
import fr.Axeldu18.PterodactylAPI.Methods.GETMethods;
import fr.Axeldu18.PterodactylAPI.Methods.POSTMethods;

public class Users {

	private PterodactylAPI main;

	public Users(PterodactylAPI main){
		this.main = main;
	}

	/**
	 * @return Return all USER with ATTRIBUTES
	 */
	public HashMap<Integer, User> getUsers(){
		HashMap<Integer, User> usersMap = new HashMap<Integer, User>();
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(GETMethods.Methods.USERS_LIST_USERS));
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

	/**
	 * @param id ID of the targeted user.
	 * @return Return the targeted USER with ATTRIBUTES.
	 */
	public User getUser(String id){
		JSONObject jsonObject = new JSONObject(main.getGetMethods().get(GETMethods.Methods.USERS_SINGLE_USER, id));
		if(!jsonObject.has("data")){
			main.log(Level.SEVERE, jsonObject.toString());
			main.log(Level.SEVERE, "No USER found with this ID");
			return new User();
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
	
	/**
	 * @param id ID of the targeted user.
	 * @return If the deletion was successful.
	 */
	public boolean deleteUser(String id){
		return main.getDeleteMethods().delete(DELETEMethods.Methods.USER, id);
	}
	
	/**
	 * @param email Mail of the new user.
	 * @param username Username of the new user.
	 * @param first_name First name of the new user.
	 * @param last_name Last name of the new user.
	 * @param password Password of the new user OPTIONAL. (Leave blank will be generated by the panel randomly)
	 * @param root_admin Set the root admin role of the new user.
	 * @return if success it return the USER class with ATTRIBUTES of the new user.
	 */
	public User createUser(String email, String username, String first_name, String last_name, String password, boolean root_admin){
		Validate.notEmpty(email, "The MAIL is required");
		Validate.notEmpty(username, "The USERNAME is required");
		Validate.notEmpty(first_name, "The FIRST_NAME is required");
		Validate.notEmpty(last_name, "The LAST_NAME is required");
		Validate.notNull(root_admin, "The ROOT_ADMIN Boolean is required");
		int admin = 0;
		if(root_admin){
			admin = 1;
		}
		JSONObject jsonObject = new JSONObject(main.getPostMethods().call(main.getMainURL() + POSTMethods.Methods.USERS_CREATE_USER.getURL(), 
				"email="+email+
				"&username="+username+
				"&name_first="+first_name+
				"&name_last="+last_name+
				"&password="+password+
				"&root_admin="+admin));
		if(!jsonObject.has("data")){
			main.log(Level.SEVERE, jsonObject.toString());
			return new User();
		}
		JSONObject userJSON = jsonObject.getJSONObject("data");
		User user = getUser(userJSON.get("id").toString());
		return user;
	}
}
