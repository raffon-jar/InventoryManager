package be.raffon.inventorymanager.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import be.raffon.inventorymanager.inventories.CInventory;
import be.raffon.inventorymanager.inventories.items.CItem;
import be.raffon.inventorymanager.permissions.PermissionType;

public class OnClick implements Listener {

    private CItem it;
    private Player player;


	public OnClick(CItem it, Player player) {
		this.player = player;
		this.it = it;
	}
    
	public void execute(CInventory cinv, Integer pageindex) {
		if(it.getPerm().hasPermission(player, new PermissionType("click"))) {
			it.getOnEvent().executeOnClick(player,cinv,pageindex);
		}
		
	}

}
