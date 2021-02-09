package be.raffon.inventorymanager.inventories;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

import be.raffon.inventorymanager.inventories.items.CItem;
import be.raffon.inventorymanager.inventories.items.CLocation;

public class Page {
	
	private ArrayList<CItem> ar = null;
	private Integer number = 0;
	private Outlines outlines = null;
	private Integer row = null;
	
	public Page(ArrayList<CItem> items, Integer page, String grid, Outlines outlines, Integer row, String gap, String align) {
		this.ar = items;
		this.number = page;
		this.outlines = outlines;
		this.row = row;
		grid(grid, gap, align);
	}
	
	public Page(ArrayList<CItem> items, Integer page, String grid, Integer row, String gap, String align) {
		this.ar = items;
		this.number = page;
		this.row = row;
		grid(grid, gap, align);
	}
	
	private void grid(String grid, String gap, String align) {
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
		} else if(grid.equals("grid")) {
			Integer g = Integer.parseInt(gap);
			if(this.outlines == null || this.outlines.equals(new Outlines("full"))) {
				Integer slots = row*9-1;
				if(align.contains("top")) {
					for(int k=0; k<row; k++) {
						if(align.contains("right")) {
							for(int i=9; k<row; i--) {
								
								
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
		}
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
