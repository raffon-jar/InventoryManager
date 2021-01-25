package be.raffon.inventorymanager;


import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import be.raffon.inventorymanager.json.JSONDB;

public class inventorymanager extends JavaPlugin{

	
	private String text = ChatColor.WHITE + "[" + ChatColor.RED + "InventoryManager" + ChatColor.WHITE + "] ";
	
	@Override
	public void onEnable() {
		JSONDB.onStart(this.getDataFolder());
		System.out.println("Inventory manager succefuly loaded !");
		System.setProperty("file.encoding", "UTF-8");
	}
	
	public String prefix() {
		return this.text;
	}
}