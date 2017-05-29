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
