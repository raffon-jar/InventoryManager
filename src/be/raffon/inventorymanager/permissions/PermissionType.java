package be.raffon.inventorymanager.permissions;

@SuppressWarnings("static-access")
public class PermissionType {
	//Types: drag-Click
	private static String type = "";
	
	
	public PermissionType(String t) {
		this.type = t;
	}
	
	public String getType() {
		return this.type;
	}
	
	public Boolean isType(PermissionType type) {
		return type.getType().equals(this.type);
	}
}
