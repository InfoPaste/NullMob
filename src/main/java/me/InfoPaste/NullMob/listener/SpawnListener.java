package me.InfoPaste.NullMob.listener;

import me.InfoPaste.NullMob.api.MobAI;
import me.InfoPaste.NullMob.core.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.List;

public class SpawnListener implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {

        CreatureSpawnEvent.SpawnReason spawnReason = event.getSpawnReason();
        List<String> disableAI = Config.config.getStringList("RemoveAI.SpawnReason");
        List<String> disableSpawn = Config.config.getStringList("StopMobSpawn.SpawnReason");

        if (!(disableSpawn == null) || !(disableSpawn.size() <= 0)) {
            for (String disable : disableSpawn) {
                if (disable.equalsIgnoreCase(spawnReason.toString())) {
                    event.setCancelled(true);
                    return;
                }
            }
        }

        if (!(disableAI == null) || !(disableAI.size() <= 0)) {
            for (String disable : disableAI) {
                if (disable.equalsIgnoreCase(spawnReason.toString())) {
                    MobAI.removeAI(event.getEntity());
                    return;
                }
            }
        }
    }

}
