package be.raffon.inventorymanager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import be.raffon.inventorymanager.inventorymanager;

public class OnDrag  implements Listener {

    private inventorymanager plugin;

    public OnDrag(inventorymanager plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onClick(InventoryClickEvent ev) {
    
    }
}
