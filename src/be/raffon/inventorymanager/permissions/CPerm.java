package be.raffon.inventorymanager.permissions;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class CPerm {

	private static PermissionType pt = null;
	private static Permission perm = null;
	
	@SuppressWarnings("static-access")
	public CPerm(PermissionType permt, Permission permission) {
		this.pt = permt;
		this.perm = permission;
	}
	
	public Boolean hasPermission(Player pl, PermissionType p) {
		return (pl.hasPermission(perm) && p.equals(pt));
	}
}
