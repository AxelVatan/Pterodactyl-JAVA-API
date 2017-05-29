package fr.Axeldu18.PterodactylAPI.Classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class UserAttributes{
	private @Getter @Setter String uuid;
	private @Getter @Setter String username;
	private @Getter @Setter String email;
	private @Getter @Setter String firstName;
	private @Getter @Setter String lastName;
	private @Getter @Setter String language;
	private @Getter @Setter int rootAdmin;
	private @Getter @Setter int usetotp;
	private @Getter @Setter int gravatar;
	private @Getter @Setter String createdAT;
	private @Getter @Setter String updatedAT;
}
