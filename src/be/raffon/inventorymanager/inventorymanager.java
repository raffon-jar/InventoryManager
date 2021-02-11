package be.raffon.inventorymanager;


import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import be.raffon.inventorymanager.creator.InventoryCreator;
import be.raffon.inventorymanager.inventories.CInventory;
import be.raffon.inventorymanager.inventories.InventoryManager;
import be.raffon.inventorymanager.inventories.items.CItem;
import be.raffon.inventorymanager.inventories.items.CLocation;
import be.raffon.inventorymanager.json.JSONDB;
import be.raffon.inventorymanager.listener.Events;
import be.raffon.inventorymanager.listener.OnClick;
@SuppressWarnings("static-access")
public class inventorymanager extends JavaPlugin implements Listener{

	
	private String text = ChatColor.WHITE + "[" + ChatColor.RED + "InventoryManager" + ChatColor.WHITE + "] ";
	
	@Override
	public void onEnable() {
		JSONDB.onStart(this.getDataFolder());
		System.out.println("Inventory manager succefuly loaded !");
		System.setProperty("file.encoding", "UTF-8");
		Bukkit.getPluginManager().registerEvents(this,this);
	}
	
	public String prefix() {
		return this.text;
	}
	
    @EventHandler
    public void onClick(InventoryClickEvent ev) {
    	Inventory inv = ev.getInventory();
    	int slot = ev.getSlot();
    	Player p = (Player) ev.getWhoClicked();
    	ItemStack item = ev.getWhoClicked().getInventory().getItem(slot);
    	CLocation loc = new CLocation(slot, slot%9, (int) Math.floor(slot/9));
    	CInventory cinv = InventoryManager.getInventory(inv);
    	CItem it = cinv.getCItem(item, loc);
    	new OnClick(it, p).execute();
    }
    
    
    @EventHandler
    public void onOpen(InventoryOpenEvent ev) {
    	Inventory inv = ev.getInventory();
    	CInventory cinv = InventoryManager.getInventory(inv);
    	Events events = cinv.getEvents(); 
    	events.executeOpen((Player) ev.getPlayer());
    }
    
    @EventHandler
    public void onClose(InventoryCloseEvent ev) {
    	Inventory inv = ev.getInventory();
    	CInventory cinv = InventoryManager.getInventory(inv);
    	Events events = cinv.getEvents(); 
    	events.executeClose((Player) ev.getPlayer());
    }
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Console can not use this plugin!");
            return true;
        }
        if(args.length == 0 || !isNumeric(args[0])) {
        	sender.sendMessage(ChatColor.RED + "The inventory is not valid usage: /inv <id> or /invedit <id> !");
        	return true;
        }        
        Player player = (Player) sender;
        
        if(alias.equalsIgnoreCase("inv")) {
        	new JSONDB().displayInv(Integer.parseInt(args[0]), player);
        } else if(alias.equalsIgnoreCase("invedit")) {
        	CInventory cinv = new JSONDB().getCInv(Integer.parseInt(args[0]), player);
        	new InventoryCreator(player).editInv(cinv);
        } else if(alias.equalsIgnoreCase("invcreate")) {
        	new InventoryCreator(player).createInv();
        }

		return true;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public static JSONObject serialize(ItemStack is) {
		JSONObject obj = new JSONObject();
		ItemMeta meta = is.getItemMeta();
		obj.put("name", meta.getDisplayName());
		obj.put("amount", String.valueOf(is.getAmount()));
		Map<Enchantment, Integer> ench = is.getEnchantments();
		Iterator it = ench.entrySet().iterator();
		JSONArray enchants = new JSONArray();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			JSONObject enchant = new JSONObject();
		  	enchant.put("name", ((Enchantment)pair.getKey()).getName());
		  	enchant.put("int", String.valueOf(pair.getValue()));
		  	enchants.add(enchant);
		}
		obj.put("enchantment", enchants);
		obj.put("durability", String.valueOf(is.getDurability()));
		String material = String.valueOf(is.getType());
		obj.put("material", material);
		return obj;
		
	}
	

	@SuppressWarnings("deprecation")
	public static ItemStack deserialize(JSONObject o) {
		ItemStack is = new ItemStack(Material.matchMaterial((String) o.get("material")), Integer.parseInt((String) o.get("amount")));
		is.setDurability(Short.valueOf((String) o.get("durability")));
		ItemMeta meta = is.getItemMeta();
		meta.setDisplayName((String) o.get("name"));
		JSONArray ar = (JSONArray) o.get("enchantment");
		for(int k=0; k<ar.size(); k++) {
			JSONObject en = (JSONObject) ar.get(k);
			meta.addEnchant(Enchantment.getByName((String) en.get("name")), Integer.parseInt((String) en.get("int")), true);
		}
		is.setItemMeta(meta);
		return is;
		
	}
    

}