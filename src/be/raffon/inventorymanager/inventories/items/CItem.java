package be.raffon.inventorymanager.inventories.items;

import org.bukkit.inventory.ItemStack;

import be.raffon.inventorymanager.permissions.CPerm;

public class CItem {
	
	private ItemStack itemstack = null;
	private CLocation location = null;
	private ActionOnEvent onclick = null;
	private CPerm cperm = null;
	
	public CItem(ItemStack is, CLocation loc, ActionOnEvent onevent, CPerm permission) {
		this.itemstack = is;
		this.location = loc;
		this.onclick = onevent;
		this.cperm = permission;
	}
	
	public void onClick(CItem item) {
		if(IdentifyIs(item)) {
			this.onclick.execute();
		}
	}
	
	public Boolean equals(CItem it) {
		return (it.getIS().equals(this.itemstack) && it.getLoc().equals(this.location)&& it.getOnEvent().equals(this.location)&& it.getPerm().equals(this.cperm));
	}
	
	public ItemStack getIS() {
		return itemstack;
	}
	
	public CLocation getLoc() {
		return location;
	}
	
	public ActionOnEvent getOnEvent() {
		return onclick;
	}
	
	public CPerm getPerm() {
		return cperm;
	}
	
	private Boolean IdentifyIs(CItem it) {
		return it.equals(this);
	}

}
