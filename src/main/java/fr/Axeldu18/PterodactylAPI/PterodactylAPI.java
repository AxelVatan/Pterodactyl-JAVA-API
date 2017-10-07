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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import fr.Axeldu18.PterodactylAPI.Methods.DELETEMethods;
import fr.Axeldu18.PterodactylAPI.Methods.GETMethods;
import fr.Axeldu18.PterodactylAPI.Methods.POSTMethods;
import fr.Axeldu18.PterodactylAPI.Methods.PUTMethods;
import lombok.Getter;
import lombok.Setter;

public class PterodactylAPI {

	private Logger logger;
	private @Getter GETMethods getMethods;
	private @Getter POSTMethods postMethods;
	private @Getter PUTMethods putMethods;
	private @Getter DELETEMethods deleteMethods;
	private @Getter Users users;
	private @Getter Servers servers;
	private @Getter Nodes nodes;
	private @Getter Locations locations;
	private @Getter Services services;
	//CONFIGURATION
	private @Getter String mainURL;
	private @Getter @Setter String publicKey;
	private @Getter @Setter String secretKey;

	public static void main(String[] args){
		new PterodactylAPI();
	}
	
	public PterodactylAPI(){
		this.logger = Logger.getLogger("PterodactylAPI");
		this.getMethods = new GETMethods(this);
		this.postMethods = new POSTMethods(this);
		this.putMethods = new PUTMethods(this);
		this.deleteMethods = new DELETEMethods(this);
		this.users = new Users(this);
		this.servers = new Servers(this);
		this.nodes = new Nodes(this);
		this.locations = new Locations(this);
		this.services = new Services(this);
	}

	public void setMainURL(String url) {
		if(!url.endsWith("/")) {
			url += "/";
		}
		this.mainURL = url;
	}
	
	public void log(Level level, String msg){
		this.logger.log(level, "[PterodactylAPI] " + msg);
	}
	
	public String hmac(String url) throws Exception {
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(url.getBytes()));
			return hash;
		}
		catch (Exception e){
			log(Level.SEVERE, " HMAC Error");
			return null;
		}
	}

	public StringBuffer readResponse(InputStream in) {
		try {
			BufferedReader idn = new BufferedReader(new InputStreamReader(in));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = idn.readLine()) != null){
				response.append(inputLine);
			}
			idn.close();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}