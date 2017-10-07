/**
MIT License

Copyright (c) 2017 Axel Vatan, Marc Sollie

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
package fr.Axeldu18.PterodactylAPI.Methods;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

import fr.Axeldu18.PterodactylAPI.PterodactylAPI;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class DELETEMethods {
	
	private PterodactylAPI main;
	
	@Getter
	private String lastError = "";
	
	public DELETEMethods(PterodactylAPI main){
		this.main = main;
	}
	
	public boolean delete(Methods method){
		if(method.getURL().contains("{params}")){
			main.log(Level.SEVERE, "The method '" + method.toString() + "'contains field {params}, please use 'get' withs params function for this");
			return false;
		}
		return call(main.getMainURL() + method.getURL());
	}
	
	public boolean delete(Methods method, String params){
		if(!method.getURL().contains("{params}")){
			main.log(Level.SEVERE, "The method '" + method.toString() + "' doesn't contains field {params}, please use 'get' function for this");
			return false;
		}
		return call(main.getMainURL() + method.getURL().replace("{params}", params));
	}
	
	private boolean call(String methodURL){
		try {
			URL url = new URL(methodURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			String hmac = main.getPublicKey() + "." + main.hmac(methodURL);
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("User-Agent", "Pterodactyl Java-API");
			connection.setRequestProperty("Authorization", "Bearer " + hmac.replaceAll("\n", ""));
			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
				return true;
			} else {
				this.lastError = main.readResponse(connection.getErrorStream()).toString();
				return false;
			}
		} catch (Exception e) {
			main.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@AllArgsConstructor
	public enum Methods{
		
		USER("api/admin/users/{params}"), //Returns information about a single user.
		SERVER("api/admin/servers/{params}"), //Lists information about a single server.
		NODE("api/admin/nodes/{params}"); //View data for a single node.		
		
		private @Getter String URL;
	}
}
