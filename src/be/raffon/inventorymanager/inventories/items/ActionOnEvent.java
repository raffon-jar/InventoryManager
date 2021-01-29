package be.raffon.inventorymanager.inventories.items;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import be.raffon.inventorymanager.macros.Action;

public class ActionOnEvent {
	
	private ArrayList<Action> onclick = null;
	private ArrayList<Action> ondrag = null;

	
	public ActionOnEvent(ArrayList<Action> click,ArrayList<Action> drag) {
		this.onclick = click;
		this.ondrag = drag;
	}

	public void executeOnClick(Player p) {
		for(int k=0; k<onclick.size(); k++) {
			Action act = onclick.get(k);
			act.Execute(p);
		}
	}
	

}
