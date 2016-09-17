package com.untamedears.JukeAlert.command.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import vg.civcraft.mc.civmodcore.command.PlayerCommand;

public class JaCommand extends PlayerCommand {
	public JaCommand() {
		super("Ja");
		setDescription("Displays Juke Alert Information");
		setUsage("/ja");
		setArguments(0,0);
		setIdentifier("ja");
	}
	@Override
    public boolean execute(CommandSender sender, String[] args) {
		if (sender instanceof Player){
			Player player = (Player) sender;
			player.sendMessage(ChatColor.AQUA+ "Juke Alert is a snitch plugin. \n To see other commands do /jahelp");
		}
		
		return true;
	}
	@Override
	public List<String> tabComplete(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
}
