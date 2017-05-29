package fr.Axeldu18.PterodactylAPI.Classes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class NodeAttributes {
	private @Getter @Setter int publicNode;
	private @Getter @Setter String name;
	private @Getter @Setter int locationID;
	private @Getter @Setter String fqdn;
	private @Getter @Setter String scheme;
	private @Getter @Setter boolean behindProxy;
	private @Getter @Setter int memory;
	private @Getter @Setter int memoryOverallocate;
	private @Getter @Setter int disk;
	private @Getter @Setter int diskOverallocate;
	private @Getter @Setter int uploadSize;
	private @Getter @Setter int deamonListen;
	private @Getter @Setter int deamonSFTP;
	private @Getter @Setter String deamonBase;
	private @Getter @Setter String createdAT;
	private @Getter @Setter String updatedAT;
}
