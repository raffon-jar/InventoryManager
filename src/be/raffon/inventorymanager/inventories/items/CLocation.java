package be.raffon.inventorymanager.inventories.items;

public class CLocation {
	
	private Integer loc = null;
	private Integer thepage = null;

	public CLocation(int numberininv, int page) {
		this.loc = numberininv;
		this.thepage = page;
	}
	
	public Integer getLocation() {
		return loc;
	}
	
	public Integer getPage() {
		return thepage;
	}
	
	
	
}
