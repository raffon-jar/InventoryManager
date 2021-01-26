package be.raffon.inventorymanager.inventories;

import java.util.ArrayList;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import be.raffon.inventorymanager.inventories.items.CItem;
import be.raffon.inventorymanager.inventories.items.CLocation;
import be.raffon.inventorymanager.listener.Events;

public class CInventory {
	
	private ArrayList<Page> ar = null;
	private Events ev = null;
	private Inventory iv = null;
	
	public CInventory(ArrayList<Page> pages, Events events, Inventory inv) {
		this.ar = pages;
		this.ev = events;
		this.iv = inv;
	}
	
	public Inventory getInventory() {
		return null;
	}
	
	public Events getEvents() {
		return ev;
	}
	
	public CItem getCItem(ItemStack is, CLocation loc) {
		for(int k=0; k<ar.size(); k++) {
			Page page = ar.get(k);
			if(page.getCItem(is, loc) != null) {
				return page.getCItem(is, loc);
			}
		}
		return null;
	}
}
