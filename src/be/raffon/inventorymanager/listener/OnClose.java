package be.raffon.inventorymanager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import be.raffon.inventorymanager.inventorymanager;

public class OnClose implements Listener {

    private inventorymanager plugin;

    public OnClose(inventorymanager plugin) {
        this.plugin = plugin;
    }
    
}