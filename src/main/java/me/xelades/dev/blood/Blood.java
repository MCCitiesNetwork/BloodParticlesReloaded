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
        // Ensure the event is dealing damage to an entity
        if (event.getEntity() == null || event.getDamager() == null) {
            return;
        }

        // Get the location where the blood particles will spawn
        Location bloodLocation = event.getEntity().getLocation();
        bloodLocation.setY(bloodLocation.getY() + (event.getEntity().getHeight() / 1.5));

        // Particle variables
        double particleMultiplier = 5.0;
        double particleMax = 50.0;
        double particleRange = 0.4;

        // Calculate particle count based on damage dealt
        double particleCount = Math.min(event.getFinalDamage() * particleMultiplier, particleMax);
        if (particleCount < 0) {
            particleCount = 0;
        }

        // BlockData for REDSTONE_BLOCK particle
        BlockData blockData = Material.REDSTONE_BLOCK.createBlockData();

        // Spawn the blood particle effect at the calculated location
        event.getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, bloodLocation, (int) particleCount, particleRange, particleRange, particleRange, blockData);
    }
}
