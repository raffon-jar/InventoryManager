package be.raffon.inventorymanager.macros;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Action {

	
	private String type = null;
	private String action = null;
	
	public Action(String type, String action) {
		this.type = type;
		this.action = action;
	}
	
	public void Execute(Player p) {
		this.action.replaceAll("%player-x%", String.valueOf(p.getLocation().getBlockX()));
		this.action.replaceAll("%player-y%", String.valueOf(p.getLocation().getBlockY()));
		this.action.replaceAll("%player-z%", String.valueOf(p.getLocation().getBlockZ()));
		this.action.replaceAll("%player-world%", p.getLocation().getWorld().toString());
		this.action.replaceAll("%player-name%", p.getName());
		if(type.equals("commandasPlayer")) {
			Bukkit.dispatchCommand(p, action);
		} else if(type.equals("commandasServer")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), action);
		}
	}
}
