package be.raffon.inventorymanager.listener;

import java.util.ArrayList;

import javax.script.ScriptException;

import org.bukkit.entity.Player;

import be.raffon.inventorymanager.inventories.CInventory;
import be.raffon.inventorymanager.macros.Action;

public class Events {
	
	private static ArrayList<Action> onOpen = null;
	private static ArrayList<Action> onClose = null;
	
	public Events(ArrayList<Action> onOpen, ArrayList<Action> onClose) {
		Events.onOpen = onOpen;
		Events.onClose = onClose;
	}
	
	public void executeOpen(Player p, CInventory cinv) {
		for(int k=0; k<onOpen.size(); k++) {
			Action action = onOpen.get(k);
			try {
				action.Execute(p, cinv, -1);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void executeClose(Player p, CInventory cinv) {
		for(int k=0; k<onClose.size(); k++) {
			Action action = onClose.get(k);
			try {
				action.Execute(p, cinv, -1);
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
