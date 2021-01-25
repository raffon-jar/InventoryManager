package be.raffon.inventorymanager.json;

import org.json.simple.JSONObject;

import be.raffon.inventorymanager.inventories.CInventory;

public class jsoninventory {
	
	private static JSONObject jsoninv = null;
	
	@SuppressWarnings("static-access")
	public jsoninventory(JSONObject inventory) {
		this.jsoninv = inventory;
	}
	
	public CInventory getCInventory() {
		return null;
	}

}
