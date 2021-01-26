package be.raffon.inventorymanager.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import be.raffon.inventorymanager.inventorymanager;
import be.raffon.inventorymanager.inventories.items.CItem;

public class OnClose implements Listener {

    private CItem it;

	public OnClose(CItem it, Player player) {
		this.it = it;
	}
    
	public void execute() {
		
	}

    
}