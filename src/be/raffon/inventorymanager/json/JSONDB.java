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
	
	static private JSONObject json = null;
	
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
			System.out.println("ERROR: The inventory you are looking for doesn't exist.");
			return null;
		}
		return null;
	}
	
	public static void displayInv(String s, Player p) {
		if(json == null) {
			System.out.println("ERROR: the json database has not been initialized. Please make a `new JSONDB()` before trying to get the inventory.");
			return; 
		}
		JSONArray inventories = (JSONArray) json.get("Inventories");
		Boolean found = false;
		for(int k=0; k<inventories.size();k++) {
			JSONObject inv = (JSONObject) inventories.get(k);
			String name = (String) inv.get("name");
			if(name.equals(s)) {
				found = true;
				new InventoryManager().getCInventory(inv).open(p);
			}
		}
		if(!found) {
			p.sendMessage("ERROR: The inventory you are looking for doesn't exist.");
			return;
		}
		return;
		
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject setJSON() {
		JSONObject obj = new JSONObject();
		obj.put("staff", new JSONArray());
		obj.put("backup", new JSONArray());
		obj.put("logs", new JSONArray());
		obj.put("vanish", new JSONArray());
		obj.put("fly", new JSONArray());
		obj.put("freeze", new JSONArray());
		obj.put("tempban", new JSONArray());
		obj.put("tempmute", new JSONArray());
		obj.put("reports", new JSONArray());
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
	

}
