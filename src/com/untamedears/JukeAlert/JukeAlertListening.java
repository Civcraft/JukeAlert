package com.untamedears.JukeAlert;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JukeAlertListening extends JavaPlugin implements Listener{
	
	public static Block snitch= null;
	public static Player player= null;
		  
	  public void placeSnitchBlock(BlockPlaceEvent bpe) {
		 
		  if (snitch.getType()== Material.JUKEBOX){
		  player= bpe.getPlayer();
		  player.sendMessage("You have created a Juke Snitch");
		  
			}
		  
		 
		 
	  }

	  private void registerEvents() {
		    getServer().getPluginManager().registerEvents(this, this);
		  }
}