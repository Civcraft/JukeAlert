package com.untamedears.JukeAlert.gui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import vg.civcraft.mc.civmodcore.inventorygui.DecorationStack;
import vg.civcraft.mc.civmodcore.inventorygui.IClickable;
import vg.civcraft.mc.civmodcore.inventorygui.MultiPageView;
import vg.civcraft.mc.civmodcore.itemHandling.ISUtils;

import com.untamedears.JukeAlert.JukeAlert;
import com.untamedears.JukeAlert.model.LoggedAction;
import com.untamedears.JukeAlert.model.Snitch;
import com.untamedears.JukeAlert.model.SnitchAction;

public class SnitchLogGUI {

	private Player player;
	private Snitch snitch;

	public SnitchLogGUI(Player p, Snitch snitch) {
		this.player = p;
		this.snitch = snitch;
	}

	public void showScreen() {
		MultiPageView view = new MultiPageView(player, constructContent(), snitch.getName().substring(0, Math.min(32, snitch.getName().length())), true);
		view.showScreen();
	}

	public List <IClickable> constructContent() {
		List <SnitchAction> actions = JukeAlert.getInstance().getJaLogger().getAllSnitchLogs(snitch);
		List <IClickable> clicks = new ArrayList<IClickable>();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		for(SnitchAction action : actions) {
			ItemStack is;
			switch (action.getAction()) {
			case BLOCK_BREAK:
				is = new ItemStack(action.getMaterial());
				ISUtils.setName(is, ChatColor.GOLD + action.getMaterial().toString() + " broken by " + action.getInitiateUser());
				break;
			case BLOCK_BURN:
				is = new ItemStack(action.getMaterial());
				ISUtils.setName(is, ChatColor.GOLD + action.getMaterial().toString() + " was destroyed by fire");
				break;
			case BLOCK_PLACE:
				is = new ItemStack(action.getMaterial());
				ISUtils.setName(is, ChatColor.GOLD + action.getMaterial().toString() + " placed by " + action.getInitiateUser());
				break;
			case BLOCK_USED:
				is = new ItemStack(action.getMaterial());
				ISUtils.setName(is, ChatColor.GOLD + action.getMaterial().toString() + " used by " + action.getInitiateUser());
				break;
			case BUCKET_EMPTY:
			case BUCKET_FILL:
				String bucketName;
				if (action.getMaterial() == null) {
					is = new ItemStack(Material.WATER_BUCKET);
					bucketName = "Bucket";
				}
				else {
					is = new ItemStack(action.getMaterial());
					bucketName = action.getMaterial().toString();
				}
				String bucketAction = action.getAction() == LoggedAction.BUCKET_EMPTY ? "emptied" : "filled";
				ISUtils.setName(is, ChatColor.GOLD + bucketName + " " + bucketAction + " by " + action.getInitiateUser());
				break;
			case ENTITY_DISMOUNT:
			case ENTITY_MOUNT:
				is = new ItemStack(Material.SADDLE);
				String horseAction = action.getAction() == LoggedAction.ENTITY_MOUNT ? "mounted" : "dismounted";
				ISUtils.setName(is, ChatColor.GOLD + action.getVictim() + " " + horseAction + " by " + action.getInitiateUser());
				break;
			case ENTRY:
				is = new ItemStack(Material.COMPASS);
				ISUtils.setName(is, ChatColor.GOLD + action.getInitiateUser() + " entered");
				break;
			case EXCHANGE:
				is = new ItemStack(Material.CHEST);
				ISUtils.setName(is, ChatColor.GOLD + action.getInitiateUser() + " used an ItemExchange");
				break;
			case IGNITED:
				is = new ItemStack(Material.FLINT_AND_STEEL);
				ISUtils.setName(is, ChatColor.GOLD + action.getMaterial().toString() + " ignited by " + action.getInitiateUser());
				break;
			case KILL:
				is = new ItemStack(Material.DIAMOND_SWORD);
				ISUtils.setName(is, ChatColor.GOLD + action.getVictim().toString() + " killed by " + action.getInitiateUser());
				break;
			case LOGIN:
				is = new ItemStack(Material.EYE_OF_ENDER);
				ISUtils.setName(is, ChatColor.GOLD + action.getInitiateUser() + " logged in");
				break;
			case LOGOUT:
				is = new ItemStack(Material.EYE_OF_ENDER);
				ISUtils.setName(is, ChatColor.GOLD + action.getInitiateUser() + " logged out");
				break;
			case UNKNOWN:
			case USED:
				//currently unused
				continue;
			case VEHICLE_DESTROY:
				is = new ItemStack(Material.MINECART);
				ISUtils.setName(is, ChatColor.GOLD + action.getInitiateUser() + " broke " + action.getVictim());
				break;
			default:
				continue;			
			}
			ISUtils.addLore(is, ChatColor.AQUA + "At " + action.getX() + ", "+ action.getY() + ", " + action.getZ());
			ISUtils.addLore(is, ChatColor.YELLOW + "Time: " + dateFormat.format(action.getDate()));
			clicks.add(new DecorationStack(is));
		}
		return clicks;
	}
}
