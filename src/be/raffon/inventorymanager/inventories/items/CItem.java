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
			onclick.execute();
		}
	}
	
	public Boolean equals(CItem it) {
		return (it.returnIS().equals(this.itemstack) && it.returnLoc().equals(this.location)&& it.returnLoc().equals(this.location)&& it.returnPerm().equals(this.cperm));
	}
	
	public ItemStack returnIS() {
		return itemstack;
	}
	
	public CLocation returnLoc() {
		return location;
	}
	
	public ActionOnEvent returnOnClick() {
		return onclick;
	}
	
	public CPerm returnPerm() {
		return cperm;
	}
	
	private Boolean IdentifyIs(CItem it) {
		return it.equals(this);
	}

}
