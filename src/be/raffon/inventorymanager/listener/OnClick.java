package be.raffon.inventorymanager.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import be.raffon.inventorymanager.inventories.items.CItem;

public class OnClick implements Listener {

    private CItem it;


	public OnClick(CItem it, Player player) {
		this.it = it;
	}
    
	public void execute() {
		
	}

}
