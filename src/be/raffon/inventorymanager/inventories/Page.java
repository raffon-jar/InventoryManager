package be.raffon.inventorymanager.inventories;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

import be.raffon.inventorymanager.inventories.items.CItem;
import be.raffon.inventorymanager.inventories.items.CLocation;

public class Page {
	
	private ArrayList<CItem> ar = null;
	private Integer number = 0;
	
	public Page(ArrayList<CItem> items, Integer page) {
		this.ar = items;
		this.number = page;
	}
	
	public CItem getCItem(ItemStack is, CLocation loc) {
		return null;
	}

}
