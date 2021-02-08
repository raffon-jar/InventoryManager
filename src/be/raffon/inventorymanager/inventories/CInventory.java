package be.raffon.inventorymanager.inventories;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import be.raffon.inventorymanager.inventories.items.CItem;
import be.raffon.inventorymanager.inventories.items.CLocation;
import be.raffon.inventorymanager.listener.Events;
import be.raffon.inventorymanager.permissions.CPerm;

public class CInventory {
	
	private ArrayList<Page> ar = null;
	private Events ev = null;
	private CPerm perm = null;
	private Outlines out = null;
	
	public CInventory(ArrayList<Page> pages, Events events, CPerm openperm, Outlines outlines) {
		this.ar = pages;
		this.ev = events;
		this.perm = openperm;
		this.out = outlines;
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
	
	public void open(Player p) {
		
	}
	
	public Boolean matchInv(Inventory inv) {
		return null;
	}
}
