package be.raffon.inventorymanager;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import be.raffon.inventorymanager.inventories.CInventory;
import be.raffon.inventorymanager.inventories.InventoryManager;
import be.raffon.inventorymanager.inventories.items.CItem;
import be.raffon.inventorymanager.inventories.items.CLocation;
import be.raffon.inventorymanager.json.JSONDB;
import be.raffon.inventorymanager.listener.Events;
import be.raffon.inventorymanager.listener.OnClick;

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
    	int slot = ev.getSlot();
    	Player p = (Player) ev.getWhoClicked();
    	ItemStack item = ev.getWhoClicked().getInventory().getItem(slot);
    	CLocation loc = new CLocation(slot, slot%9, (int) Math.floor(slot/9));
    	CInventory cinv = InventoryManager.getInventory(inv);
    	CItem it = cinv.getCItem(item, loc);
    	new OnClick(it, p).execute();
    }
    
    
    @EventHandler
    public void onOpen(InventoryOpenEvent ev) {
    	Inventory inv = ev.getInventory();
    	CInventory cinv = InventoryManager.getInventory(inv);
    	Events events = cinv.getEvents();
    }
    
    @EventHandler
    public void onClose(InventoryCloseEvent ev) {
    
    }
    

}