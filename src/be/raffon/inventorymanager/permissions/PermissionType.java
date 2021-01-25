package be.raffon.inventorymanager.permissions;

public class PermissionType {
	//Types: drag-Click
	private static String type = "";
	
	@SuppressWarnings("static-access")
	public PermissionType(String t) {
		this.type = t;
	}
	
	public Boolean isType(String type) {
		return type.equals(this.type);
	}
}
