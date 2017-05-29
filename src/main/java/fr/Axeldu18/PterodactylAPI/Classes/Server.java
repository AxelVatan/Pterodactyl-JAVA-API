package fr.Axeldu18.PterodactylAPI.Classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Server {

	private @Getter @Setter String type;
	private @Getter @Setter int id;
	private @Getter @Setter ServerAttributes attributes;
}
