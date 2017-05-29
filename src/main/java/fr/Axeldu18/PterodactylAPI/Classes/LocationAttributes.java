package fr.Axeldu18.PterodactylAPI.Classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class LocationAttributes {
	private @Getter @Setter String shortName;
	private @Getter @Setter String longName;
	private @Getter @Setter String createdAT;
	private @Getter @Setter String updatedAT;
}
