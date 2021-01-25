package be.raffon.inventorymanager;


import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import be.raffon.inventorymanager.json.JSONDB;

public class inventorymanager extends JavaPlugin{

	
	public String text = ChatColor.WHITE + "[" + ChatColor.RED + "InventoryManager" + ChatColor.WHITE + "] ";
	
	@Override
	public void onEnable() {
		JSONDB.onStart(this.getDataFolder());
		System.out.println("Staff plugin succefuly loaded !");
		System.setProperty("file.encoding", "UTF-8");
	}
}