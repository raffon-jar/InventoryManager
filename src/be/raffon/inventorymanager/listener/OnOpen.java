package be.raffon.inventorymanager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

import be.raffon.inventorymanager.inventorymanager;

public class OnOpen implements Listener {

    private inventorymanager plugin;

    public OnOpen(inventorymanager plugin) {
        this.plugin = plugin;
    }
    

}