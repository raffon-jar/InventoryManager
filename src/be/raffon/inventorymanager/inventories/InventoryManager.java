package be.raffon.inventorymanager.inventories;

import java.util.ArrayList;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import be.raffon.inventorymanager.inventorymanager;
import be.raffon.inventorymanager.inventories.items.ActionOnEvent;
import be.raffon.inventorymanager.inventories.items.CItem;
import be.raffon.inventorymanager.inventories.items.CLocation;
import be.raffon.inventorymanager.json.JSONDB;
import be.raffon.inventorymanager.listener.Events;
import be.raffon.inventorymanager.macros.Action;
import be.raffon.inventorymanager.permissions.CPerm;
import be.raffon.inventorymanager.permissions.PermissionType;

public class InventoryManager {

	public static CInventory getCInventory(JSONObject inv) {
		ArrayList<Page> ps = new ArrayList<Page>();
		JSONArray pages = (JSONArray) inv.get("pages");
		for(int k=0; k<pages.size(); k++) {
			JSONObject page = (JSONObject) pages.get(k);
			Integer rows = ((Long) page.get("rows")).intValue();
			Integer pagenum = ((Long) page.get("page")).intValue();
			String type = (String) page.get("grid-type");
			String grida = (String) page.get("grid-align");
			Integer gapa = ((Long) page.get("grid-gap")).intValue();
			String outlines = (String) page.get("outlines");
			String title = (String) page.get("title");
			
			if(title.startsWith("[InvManager]")) {
				System.out.println("ERROR: The inventory with the id "  + ((Long) inv.get("id")).intValue() + " can't be opened because the title of the page" + pagenum + " starts with [InvManager] and this prefix is reserved by the plugin !");
				return null;
			}
			
			ArrayList<CItem> citems = new ArrayList<CItem>();
			JSONArray items = (JSONArray) page.get("items");
			
			for(int i=0; i<items.size(); i++) {
				JSONObject citem = (JSONObject) items.get(i);
				Integer slot = ((Long) citem.get("slot")).intValue();
				ItemStack is = inventorymanager.deserialize((JSONObject) citem.get("itemstack"));
				CPerm perm = new CPerm(new PermissionType("onclick"), new Permission((String) citem.get("perm-onclick")));
				JSONArray onclick = (JSONArray) citem.get("onclick");
				JSONArray ondrag = (JSONArray) citem.get("ondrag");
				ArrayList<Action> onclickar = new ArrayList<Action>();
				ArrayList<Action> ondragar = new ArrayList<Action>();
				for(int o=0; o<onclick.size(); o++) {
					JSONObject action = (JSONObject) onclick.get(o);
					String typeu = (String) action.get("type");
					String ac = (String) action.get("action");
					Action act = new Action(typeu, ac);
					onclickar.add(act);
				}
				for(int o=0; o<onclick.size(); o++) {
					JSONObject action = (JSONObject) onclick.get(o);
					String typeu = (String) action.get("type");
					String ac = (String) action.get("action");
					Action act = new Action(typeu, ac);
					ondragar.add(act);
				}
				ActionOnEvent actio = new ActionOnEvent(onclickar, ondragar);
				
				CItem cite = new CItem(is, new CLocation(slot), actio, perm);
				
				citems.add(cite);
			}
			
			
			Page pag = new Page(citems, pagenum, type, new Outlines(outlines), rows, gapa, grida, title);
			
			ps.add(pag);
		}
		CPerm openperm = new CPerm(new PermissionType("open"), new Permission((String)inv.get("openperm")));
		JSONArray onOpen = (JSONArray) inv.get("onOpen");
		JSONArray onClose = (JSONArray) inv.get("onClose");
		ArrayList<Action> onopenar = new ArrayList<Action>();
		ArrayList<Action> onclosear = new ArrayList<Action>();
		for(int o=0; o<onOpen.size(); o++) {
			JSONObject action = (JSONObject) onOpen.get(o);
			String typeu = (String) action.get("type");
			String ac = (String) action.get("action");
			Action act = new Action(typeu, ac);
			onopenar.add(act);
		}
		for(int o=0; o<onClose.size(); o++) {
			JSONObject action = (JSONObject) onClose.get(o);
			String typeu = (String) action.get("type");
			String ac = (String) action.get("action");
			Action act = new Action(typeu, ac);
			onclosear.add(act);
		}
		Events ev = new Events(onopenar, onclosear);
		return new CInventory(ps, ev, openperm, ((Long) inv.get("id")).intValue());
	}
	
	public static CInventory getInventory(Inventory inv) {
		JSONDB db = new JSONDB();
		return JSONDB.verifyInventory(inv);
	}
	


}
