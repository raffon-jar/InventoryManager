package be.raffon.inventorymanager;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import be.raffon.inventorymanager.json.JSONDB;

public class inventorymanager extends JavaPlugin implements Listener{

	
	private String text = ChatColor.WHITE + "[" + ChatColor.RED + "InventoryManager" + ChatColor.WHITE + "] ";
	
	@Override
	public void onEnable() {
		JSONDB.onStart(this.getDataFolder());
		System.out.println("Inventory manager succefuly loaded !");
		System.setProperty("file.encoding", "UTF-8");
		Bukkit.getPluginManager().registerEvents(this,this);
	}
	
	public String prefix() {
		return this.text;
	}
	
    @EventHandler
    public void onClick(InventoryClickEvent ev) {
    	Inventory inv = ev.getInventory();
    	
    }
    
    @EventHandler
    public void onOpen(InventoryOpenEvent ev) {
    
    }
    
    @EventHandler
    public void onClose(InventoryCloseEvent ev) {
    
    }
    

}