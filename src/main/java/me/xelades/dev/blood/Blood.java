package me.xelades.dev.blood;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Blood extends JavaPlugin implements Listener {
    
    // Configuration constants
    private static final int PARTICLE_COUNT = 15;
    private static final double PARTICLE_RANGE = 0.3;
    private static final double HEIGHT_OFFSET_DIVISOR = 1.5;

    @Override
    public void onEnable() {
        // Register the listener to listen to events
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Blood has been enabled!");
    }
    
    @Override
    public void onDisable() {
        getLogger().info("Blood has been disabled!");
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDamage(EntityDamageEvent event) {
        // Only trigger for players
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }
        
        // Only trigger if damage was actually applied
        if (event.getFinalDamage() <= 0) {
            return;
        }
        
        spawnBloodParticles(player);
    }
    
    private void spawnBloodParticles(Player player) {
        Location bloodLocation = player.getLocation();
        bloodLocation.setY(bloodLocation.getY() + (player.getHeight() / HEIGHT_OFFSET_DIVISOR));
    
        // Create redstone block data for red blood particles
        BlockData blockData = Material.REDSTONE_BLOCK.createBlockData();
    
        // Spawn BLOCK particles for realistic blood effect
        player.getWorld().spawnParticle(Particle.BLOCK, bloodLocation, PARTICLE_COUNT, PARTICLE_RANGE, PARTICLE_RANGE, PARTICLE_RANGE, blockData);
    }
}
