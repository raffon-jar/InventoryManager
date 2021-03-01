package be.raffon.inventorymanager.inventories;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import be.raffon.inventorymanager.inventories.items.CItem;
import be.raffon.inventorymanager.inventories.items.CLocation;

public class Page {
	
	private ArrayList<CItem> ar = null;
	private Integer number = 0;
	private Outlines outlines = null;
	private Integer row = null;
	private String title = null;
	
	public Page(ArrayList<CItem> items, Integer page, String grid, Outlines outlines, Integer row, Integer gap, String align, String title) {
		this.ar = items;
		this.number = page;
		if(!outlines.equals("")) {
			this.outlines = outlines;
		}
		this.row = row;
		this.title = title;
		grid(grid, gap, align);
	}
	
	public Page(ArrayList<CItem> items, Integer page, String grid, Integer row, Integer gap, String align, String title) {
		this.ar = items;
		this.number = page;
		this.row = row;
		this.title = title;
		grid(grid, gap, align);
	}
	
	public Page(ArrayList<CItem> items, Integer page, Integer row, String title) {
		this.ar = items;
		this.number = page;
		this.row = row;
		this.title = title;
	}
	
	public Page(ArrayList<CItem> items, Integer page, Integer row, Outlines outlines, String title) {
		this.ar = items;
		this.number = page;
		this.row = row;
		this.title = title;
		if(!outlines.equals("")) {
			this.outlines = outlines;
		}
		
	}
	
	public Integer getInteger() {
		return number;
	}
	
	public Integer getRows() {
		return row;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Outlines getOutlines() {
		return outlines;
	}
	
	private void grid(String grid, Integer gap, String align) {
		ArrayList<CItem> sorted = new ArrayList<CItem>();
		for(int k=0; k<ar.size(); k++) {
			CItem it = ar.get(k);
			if(it.hasLoc()) {
				sorted.add(it);
			}
		}
		if(grid.equals("random")) {
			if(this.outlines == null || this.outlines.equals(new Outlines("full"))) {
				Integer slots = row*9-1; 
				for(int k=0; k<ar.size(); k++) {
					CItem it = ar.get(k);
					Boolean match = false;
					int random = 0;
					while(!match) {
						random = (int)((Math.random() * ((slots - 0) + 1)) + 0);
						Boolean nfound = false;
						for(int i=0; i<sorted.size(); i++) {
							CItem item = sorted.get(i);
							if(item.getLoc().getLocation() == random) {
								nfound = true;
							}
						}
						if(!nfound) match = true; 
					}
					it.setLoc(random, row, (int) Math.floor(random/9));
					sorted.add(it);
				}
			} else {
				Integer slots = (row-2)*(7);
				for(int k=0; k<row-2; k++) {
					for(int i=0; k<7; i++) {
						CItem it = ar.get(k);
						Boolean match = false;
						int random = 0;
						while(!match) {
							random = (int)((Math.random() * ((slots - 0) + 1)) + 0)+9;
							Boolean nfound = false;
							for(int g=0; g<sorted.size(); g++) {
								CItem item = sorted.get(g);
								if(item.getLoc().getLocation() == random) {
									nfound = true;
								}
							}
							if(!nfound) match = true; 
						}
						it.setLoc(random, row, (int) Math.floor(random/9));
						sorted.add(it);
					}

				}
			}
		} /*else if(grid.equals("grid")) {
			if(this.outlines == null || this.outlines.equals(new Outlines("full"))) {
				Integer slots = row*9-1;
				if(align.contains("top")) {
					Integer item = 0;
					for(int k=0; k<row; k++) {
						if(align.contains("right")) {
							for(int i=8; k>=0; i--) {
								CItem it = ar.get(item);
								while(it.hasLoc()) {
									it = ar.get(item+1);
								}
								it.setLoc(i*k, k, i);
								if(gap != 0) {
									i = i - gap;
								}
							}
						} else if(align.contains("left")) {
							for(int i=8; k>=0; i++) {
								CItem it = ar.get(item);
								while(it.hasLoc()) {
									it = ar.get(item+1);
								}
								it.setLoc(i*k, k, i);
								if(gap != 0) {
									i = i + gap;
								}
							}
						} else if(align.contains("center")) {
							for(int i=8; k>=0; i++) {
								CItem it = ar.get(item);
								while(it.hasLoc()) {
									it = ar.get(item+1);
								}
								it.setLoc(i*k, k, i);
								if(gap != 0) {
									i = i + gap;
								}
							}
						}
					}
				} else if(align.contains("center")) {
					for(int k=0; k<row; k++) {
						
					}
				} else if(align.contains("bottom")) {
					for(int k=0; k<row; k++) {
						
					}
				}


				
			} else {
				Integer slots = (row-2)*(7);
				for(int k=0; k<row-2; k++) {
					
				}
				
			}
		}*/
	}
	
	public Inventory generateInv() {
		Inventory inv = Bukkit.createInventory(null, 9*row, title);
		for(int k=0; k<ar.size(); k++) {
			CItem it = ar.get(k);
			inv.setItem(it.getLoc().getLocation(),it.getIS());
		}
		return inv;
	}
	
	public CItem getCItem(ItemStack is, CLocation loc) {
		for(int k=0; k<ar.size(); k++) {
			CItem item = ar.get(k);
			if(!item.hasLoc()) {System.out.println("ERROR: One of the item has no location, please verify your locations in the config.JSON");return null;}
			if(item.getIS().equals(is) && item.getLoc().equals(loc)) {
				return item;
			}
		}
		return null;
	}

}
