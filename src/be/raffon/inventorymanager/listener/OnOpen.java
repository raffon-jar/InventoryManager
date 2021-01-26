package be.raffon.inventorymanager.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import be.raffon.inventorymanager.inventories.items.CItem;

public class OnOpen implements Listener {
    private CItem it;

	public OnOpen(CItem it, Player player) {
		this.it = it;
	}
    
	public void execute() {
		
	}

}