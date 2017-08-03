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
package fr.Axeldu18.PterodactylAPI.Methods;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

import fr.Axeldu18.PterodactylAPI.PterodactylAPI;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PUTMethods {

	private PterodactylAPI main;

	public PUTMethods(PterodactylAPI main){
		this.main = main;
	}
	
	public String call(String methodURL, String data){
		try {
			URL url = new URL(methodURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			String hmac = main.getPublicKey() + "." + main.hmac(methodURL+data);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("User-Agent", "Pterodactyl Java-API");
			connection.setRequestProperty("Authorization", "Bearer " + hmac.replaceAll("\n", ""));
			connection.setDoOutput(true);

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(data);
			wr.flush();
			wr.close();

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				return main.readResponse(connection.getInputStream()).toString();
			} else {
				return main.readResponse(connection.getErrorStream()).toString();
			}
		} catch (Exception e) {
			main.log(Level.SEVERE, e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@AllArgsConstructor
	public enum Methods{

		USERS_UPDATE_USER("api/admin/users/{params}"), //Update specified user
		
		SERVERS_DETAIL_SERVER("api/admin/servers/12/details"), //Get details of specified server
		SERVERS_CONTAINER_SERVER("api/admin/servers/{params}/container"),
		SERVERS_BUILD_SERVER("api/admin/servers/{params}/build"),
		SERVERS_STARTUP_SERVER("api/admin/server{params}/startup");
		
		private @Getter String URL;
	}
}
