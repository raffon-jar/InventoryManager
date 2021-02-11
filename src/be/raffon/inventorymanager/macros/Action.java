package be.raffon.inventorymanager.macros;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import be.raffon.inventorymanager.inventories.CInventory;

public class Action {

	
	private String type = null;
	private String action = null;
	
	public Action(String type, String action) {
		this.type = type;
		this.action = action;
	}
	
	public void Execute(Player p, CInventory cinv, Integer pageindex) throws ScriptException {
		this.action.replaceAll("%player-x%", String.valueOf(p.getLocation().getBlockX()));
		this.action.replaceAll("%player-y%", String.valueOf(p.getLocation().getBlockY()));
		this.action.replaceAll("%player-z%", String.valueOf(p.getLocation().getBlockZ()));
		this.action.replaceAll("%player-world%", p.getLocation().getWorld().toString());
		this.action.replaceAll("%player-name%", p.getName());
		
		Integer startindex = 0;
	    ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    
		while(this.action.indexOf("${", startindex) != -1) {
		    String operation = this.action.substring(this.action.indexOf("${", startindex), this.action.indexOf("}", startindex));
		    String result = String.valueOf(engine.eval(operation));
		    this.action.replaceAll(operation, result);
		    startindex = this.action.indexOf("}", startindex);
		}

	    
		
		if(type.equals("commandasPlayer")) {
			Bukkit.dispatchCommand(p, action);
		} else if(type.equals("commandasServer")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), action);
		} else if(type.equals("nextpage")) {
			if(cinv.getPages().size() >= pageindex+1) {
				cinv.open(p, pageindex+1);
			} else {
				p.sendMessage("Unable to open the next page because this page is the last one.");
			}
		} else if(type.equals("previouspage")) {
			if(pageindex-1 >= 0) {
				cinv.open(p, pageindex-1);
			} else {
				p.sendMessage("Unable to open the previous page because this page is the first one.");
			}
		}
	}
}
