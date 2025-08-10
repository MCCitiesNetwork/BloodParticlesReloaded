package me.xelades.dev.blood;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Blood extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register the listener to listen to events
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (event.getEntity() == null || event.getDamager() == null) {
            return;
        }
    
        Location bloodLocation = event.getEntity().getLocation();
        bloodLocation.setY(bloodLocation.getY() + (event.getEntity().getHeight() / 1.5));
    
        double particleMultiplier = 5.0;
        double particleMax = 50.0;
        double particleRange = 0.4;
    
        double particleCount = Math.min(event.getFinalDamage() * particleMultiplier, particleMax);
        if (particleCount < 0) {
            particleCount = 0;
        }
    
        BlockData blockData = Material.REDSTONE_BLOCK.createBlockData();
    
        // Spawn BLOCK_CRACK particle
        event.getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, bloodLocation, (int) particleCount, particleRange, particleRange, particleRange, blockData);
    }

        // BlockData for REDSTONE_BLOCK particle
        BlockData blockData = Material.REDSTONE_BLOCK.createBlockData();

        // Use BLOCK_CRACK instead of BLOCK_DUST
        event.getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, bloodLocation, (int) particleCount, particleRange, particleRange, particleRange, blockData);
    }
}
