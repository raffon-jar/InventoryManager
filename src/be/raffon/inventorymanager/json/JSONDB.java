package be.raffon.inventorymanager.json;

import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import be.raffon.inventorymanager.inventories.CInventory;
import be.raffon.inventorymanager.inventories.InventoryManager;

@SuppressWarnings("static-access")
public class JSONDB {
	
	static private File js = null;
	
	static public JSONObject json = null;
	
	public JSONDB() {
		try {
			JSONParser jsonparser = new JSONParser();
			Object parsed = null;
			parsed = jsonparser.parse(new FileReader(js.getPath()));
			JSONObject jsonObject = (JSONObject) parsed;
			this.json = jsonObject;
		}
		catch (IOException | ParseException e) {
			throw new RuntimeException("Exception problem ! Please contact the developper !", e);
		}	
		
	}

	
	
	public static CInventory verifyInventory(Inventory inventory) {
		if(json == null) {
			System.out.println("ERROR: the json database has not been initialized. Please make a `new JSONDB()` before trying to get the inventory.");
			return null; 
		}
		JSONArray inventories = (JSONArray) json.get("Inventories");
		Boolean found = false;
		for(int k=0; k<inventories.size();k++) {
			JSONObject inv = (JSONObject) inventories.get(k);
			String name = (String) inv.get("name");
			if(new InventoryManager().getCInventory(inv).matchInv(inventory)) {
				found = true;
				return new InventoryManager().getCInventory(inv);
			}
		}
		if(!found) {
			//System.out.println("ERROR: The inventory you are looking for doesn't exist.");
			return null;
		}
		return null;
	}
	
	public static void displayInv(Integer id, Player p) {
		if(json == null) {
			System.out.println("ERROR: the json database has not been initialized. Please make a `new JSONDB()` before trying to get the inventory.");
			return; 
		}
		JSONArray inventories = (JSONArray) json.get("Inventories");
		Boolean found = false;
		for(int k=0; k<inventories.size();k++) {
			JSONObject inv = (JSONObject) inventories.get(k);
			Integer i = (Integer) inv.get("id");
			if(i.equals(id)) {
				found = true;
				new InventoryManager().getCInventory(inv).open(p, 0);
			}
		}
		if(!found) {
			//p.sendMessage("ERROR: The inventory you are looking for doesn't exist.");
			return;
		}
		return;
		
	}
	
	public static CInventory getCInv(Integer id, Player p) {
		if(json == null) {
			System.out.println("ERROR: the json database has not been initialized. Please make a `new JSONDB()` before trying to get the inventory.");
			return null; 
		}
		JSONArray inventories = (JSONArray) json.get("Inventories");
		Boolean found = false;
		for(int k=0; k<inventories.size();k++) {
			JSONObject inv = (JSONObject) inventories.get(k);
			Integer i = (Integer) inv.get("id");
			if(i.equals(id)) {
				found = true;
				return new InventoryManager().getCInventory(inv);
			}
		}
		if(!found) {
			p.sendMessage("ERROR: The inventory you are looking for doesn't exist.");
			return null;
		}
		return null;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static void waitingFor(String string, Player p) {
		JSONArray ar = (JSONArray) json.get("waitingfor");
		Boolean found = false;
		for(int k=0; k<ar.size(); k++) {
			JSONObject wait = (JSONObject) ar.get(k);
			if(wait.get("player").equals(p.getDisplayName())) {
				wait.remove("wait");
				wait.put("wait", string);
				wait.put("complete", "false");
				found = true;
			}
		}
		if(!found) {
			JSONObject obj = new JSONObject();
			obj.put("wait", string);
			obj.put("complete", "false");
			obj.put("player", p.getDisplayName());
			ar.add(obj);
		}
		updateJSON();
	}
	
	
	public static Boolean hasresponded(Player p, String s) {
		JSONArray ar = (JSONArray) json.get("waitingfor");
		for(int k=0; k<ar.size(); k++) {
			JSONObject wait = (JSONObject) ar.get(k);
			if(wait.get("player").equals(p.getDisplayName())) {
				if(wait.get("complete").equals("true")) {
					return true;
				} else {
					return false;
				}
				
			}
		}
		return false;
	}
	
	public static String getResponse(Player p) {
		JSONArray ar = (JSONArray) json.get("waitingfor");
		for(int k=0; k<ar.size(); k++) {
			JSONObject wait = (JSONObject) ar.get(k);
			if(wait.get("player").equals(p.getDisplayName())) {
				if(wait.get("complete").equals("false")) {
					return (String) wait.get("wait");
				}
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject setJSON() {
		JSONObject obj = new JSONObject();
		obj.put("Inventories", new JSONArray());
		obj.put("backup", new JSONArray());
		obj.put("waitingfor", new JSONArray());
		return obj;
		
	}
	
	public static void onStart(File file) {
		File dir = file; // Get the parent directory
		dir.mkdirs();
		js = new File(file + "//" + "config.JSON");
		if(!js.exists()) {
			new File(file + "//").mkdirs();
			
			JSONObject o = JSONDB.setJSON();
			try {
				FileWriter file1 = new FileWriter(file + "//" + "config.JSON");
				file1.write(o.toJSONString());
				file1.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		js = new File(file, "//" + "config.JSON");
	}
	
	public static void updateJSON() {
		try {
			FileWriter file = new FileWriter(js);
			file.write(json.toJSONString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
