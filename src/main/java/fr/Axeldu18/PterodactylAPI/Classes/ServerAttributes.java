package fr.Axeldu18.PterodactylAPI.Classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ServerAttributes {
	private @Getter @Setter String uuid;
	private @Getter @Setter String uuidShort;
	private @Getter @Setter int nodeID;
	private @Getter @Setter String name;
	private @Getter @Setter String description;
	private @Getter @Setter boolean skipScripts;
	private @Getter @Setter int suspended;
	private @Getter @Setter int ownerID;
	private @Getter @Setter int memory;
	private @Getter @Setter int swap;
	private @Getter @Setter int disk;
	private @Getter @Setter int io;
	private @Getter @Setter int cpu;
	private @Getter @Setter int oomDisabled;
	private @Getter @Setter int allocationID;
	private @Getter @Setter int serviceID;
	private @Getter @Setter int optionID;
	private @Getter @Setter int packID;
	private @Getter @Setter String startup;
	private @Getter @Setter String image;
	private @Getter @Setter String username;
	private @Getter @Setter int installed;
	private @Getter @Setter String createdAT;
	private @Getter @Setter String updatedAT;
}
