package com.herpingdo.gunstick;

import net.minecraft.server.v1_5_R1.EntityLiving;
import net.minecraft.server.v1_5_R1.EntitySmallFireball;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_5_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_5_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class GunStick extends JavaPlugin implements Listener {
	@Override
	public void onEnable() 
	{
		getServer().getPluginManager().registerEvents(this, this);
	}
	public void fireball(Player player)
	{
		CraftPlayer craftPlayer = (CraftPlayer) player;
		EntityLiving playerEntity = craftPlayer.getHandle();
		Vector lookat = player.getLocation().getDirection().multiply(10);
		Location loc = player.getLocation();
		EntitySmallFireball fball = new EntitySmallFireball(((CraftWorld) player.getWorld()).getHandle(), playerEntity, lookat.getX(), lookat.getY(), lookat.getZ());
		fball.locX = loc.getX() + (lookat.getX()/5.0) + 0.25;
		fball.locY = loc.getY() + (player.getEyeHeight()/2.0) + 0.5;
		fball.locZ = loc.getZ() + (lookat.getZ()/5.0);
		((CraftWorld) player.getWorld()).getHandle().addEntity(fball);
	}
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e)
    {
		Material material = e.getMaterial();
		if(material == Material.STICK)
		{
			boolean hasAmmo = false;
			boolean takeAmmo = true;
			if (e.getAction() == Action.RIGHT_CLICK_AIR || (e.getAction() == Action.RIGHT_CLICK_BLOCK)) //So it does not shoot when you "hit"
			{
				Player p = e.getPlayer();
				if (p.hasPermission("gunstick.shoot") || p.isOp())
				{
					int normInvSize = p.getInventory().getSize();
	           		for (int i = 0; i < normInvSize; i++) 
	        		{
	            			ItemStack item = p.getInventory().getItem(i);
	            			if (item.toString().toLowerCase().contains("sulphur"))
	            			{
	            				hasAmmo = true;
	            				break;
	            			}
	           		}
	           		if (p.hasPermission("gunstick.infinite") || p.isOp())
	           		{
	            			hasAmmo = true;
	            			takeAmmo = false;
			        }
	           		if (hasAmmo)
			        {
	            			if (takeAmmo)
			            	{
			            		p.getInventory().removeItem(new ItemStack(Material.SULPHUR, 1));
	            				p.getInventory().setContents(p.getInventory().getContents());
	            				p.updateInventory();
	            			}
	            			fireball(p);
	            			hasAmmo = false;
	           		}
			else
			{
				p.sendMessage("[GS] You may not shoot guns!");
			}
		}
    }
}
    }   }
