package be.raffon.inventorymanager.macros;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Macro {
	
	private ArrayList<Action> actions = null;
	private Player p = null;
	
	
	public Macro(ArrayList<Action> actions, Player p) {
		this.actions = actions;
		this.p = p;
	}
	
	public void execute() {
		for(int k=0; k<this.actions.size(); k++) {
			Action action = this.actions.get(k);
			action.Execute(this.p);
		}
	}

}
