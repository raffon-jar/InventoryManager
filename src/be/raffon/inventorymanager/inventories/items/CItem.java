package be.raffon.inventorymanager.inventories.items;

import org.bukkit.inventory.ItemStack;

public class CItem {
	
	private ItemStack itemstack = null;
	private CLocation location = null;
	private ActionOnClick onclick = null;
	
	public CItem(ItemStack is, CLocation loc, ActionOnClick onclick) {
		this.itemstack = is;
		this.location = loc;
		this.onclick = onclick;
	}
	
	public void onClick(CItem item) {
		if(IdentifyIs(item)) {
			onclick.execute();
		}
	}
	
	public Boolean equals(CItem it) {
		return (it.returnIS().equals(this.itemstack) && it.returnLoc().equals(this.location)&& it.returnLoc().equals(this.location));
	}
	
	public ItemStack returnIS() {
		return itemstack;
	}
	
	public CLocation returnLoc() {
		return location;
	}
	
	public ActionOnClick returnOnClick() {
		return onclick;
	}
	
	private Boolean IdentifyIs(CItem it) {
		return it.equals(this);
	}

}
