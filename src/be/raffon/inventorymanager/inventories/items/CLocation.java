package be.raffon.inventorymanager.inventories.items;

public class CLocation {
	
	private Integer loc = null;
	private Integer row = null;
	private Integer column = null;

	public CLocation(int numberininv, int row, int column) {
		this.loc = numberininv;
		this.row = row;
		this.column = column;
	}
	
	public CLocation(int numberininv) {
		this.loc = numberininv;
		this.row = numberininv%9;
		this.column = (int) Math.floor(numberininv/9);
	}
	
	public Integer getLocation() {
		return loc;
	}
	
	public Integer getRow() {
		return row;
	}
	
	public Integer getColumn() {
		return column;
	}
	
}
