package be.raffon.inventorymanager;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import be.raffon.inventorymanager.json.JSONDB;
import be.raffon.inventorymanager.listener.OnClick;
import be.raffon.inventorymanager.listener.OnClose;
import be.raffon.inventorymanager.listener.OnDrag;
import be.raffon.inventorymanager.listener.OnOpen;

public class inventorymanager extends JavaPlugin{

	
	private String text = ChatColor.WHITE + "[" + ChatColor.RED + "InventoryManager" + ChatColor.WHITE + "] ";
	
	@Override
	public void onEnable() {
		JSONDB.onStart(this.getDataFolder());
		System.out.println("Inventory manager succefuly loaded !");
		System.setProperty("file.encoding", "UTF-8");
		Bukkit.getPluginManager().registerEvents(new OnClick(this),this);
		Bukkit.getPluginManager().registerEvents(new OnClose(this),this);
		Bukkit.getPluginManager().registerEvents(new OnDrag(this),this);
		Bukkit.getPluginManager().registerEvents(new OnOpen(this),this);
	}
	
	public String prefix() {
		return this.text;
	}

}