package fr.Axeldu18.PterodactylAPI.Classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ServiceAttributes {
	private @Getter @Setter String author;
	private @Getter @Setter String name;
	private @Getter @Setter String description;
	private @Getter @Setter String folder;
	private @Getter @Setter String startup;
	private @Getter @Setter String indexFile;
	private @Getter @Setter String createdAT;
	private @Getter @Setter String updatedAT;
}
