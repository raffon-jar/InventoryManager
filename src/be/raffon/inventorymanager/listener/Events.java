package be.raffon.inventorymanager.listener;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import be.raffon.inventorymanager.macros.Action;

public class Events {
	
	private static ArrayList<Action> onOpen = null;
	private static ArrayList<Action> onClose = null;
	
	public Events(ArrayList<Action> onOpen, ArrayList<Action> onClose) {
		Events.onOpen = onOpen;
		Events.onClose = onClose;
	}
	
	public void executeOpen(Player p) {
		for(int k=0; k<onOpen.size(); k++) {
			Action action = onOpen.get(k);
			action.Execute(p);
		}
	}
	
	public void executeClose(Player p) {
		for(int k=0; k<onClose.size(); k++) {
			Action action = onClose.get(k);
			action.Execute(p);
		}
	}
}
