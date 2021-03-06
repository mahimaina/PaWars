package de.mavecrit.pawars.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import de.mavecrit.pawars.Main;
import de.mavecrit.pawars.booleans.GameBool;
import de.mavecrit.pawars.game.GameInstance;
import de.mavecrit.pawars.item.SpawnZombies;
import de.mavecrit.pawars.item.TeamSelect;
import de.mavecrit.pawars.item.Vote;
import de.mavecrit.pawars.location.LocationGetter;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		p.getInventory().clear();
		if(Main.getPlugin().getConfig().getBoolean("Items.JoinItems.TeamSelector.Enabled")){		
				p.getInventory().setItem(Main.getPlugin().getConfig().getInt("Items.JoinItems.TeamSelector.Slot"), TeamSelect.selector());		
		}
		if(Main.getPlugin().getConfig().getBoolean("General.Voting")){		
			p.getInventory().setItem(Main.getPlugin().getConfig().getInt("Items.JoinItems.Vote.Slot"), Vote.voter());		
	    }

		p.teleport(LocationGetter.GetLocation("lobby"));
		
		if(Bukkit.getOnlinePlayers().size() >= Main.getPlugin().getConfig().getInt("General.MinPlayers")){
			if(!GameBool.isEnough()){
		    GameBool.setEnough(true);
			GameInstance.onStart();
			}
		}
	}
	

}
