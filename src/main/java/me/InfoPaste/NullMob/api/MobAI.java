package me.InfoPaste.NullMob.api;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class MobAI {

    public static void removeAI(Entity entity) {
        if (entity instanceof Player) {
            return;
        } else {
            setAI(entity, false);
        }
    }

    public static void addAI(Entity entity) {
        if (entity instanceof Player) {
            return;
        } else {
            setAI(entity, true);
        }
    }

    private static void setAI(Entity entity, boolean mobAI) {
        try {
            net.minecraft.server.v1_9_R1.Entity nmsEntity = ((org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity) entity).getHandle();

            net.minecraft.server.v1_9_R1.NBTTagCompound tag = new net.minecraft.server.v1_9_R1.NBTTagCompound();
            nmsEntity.c(tag);

            if (tag == null) {
                tag = new net.minecraft.server.v1_9_R1.NBTTagCompound();
            }

            nmsEntity.c(tag);
            if (mobAI) {
                tag.setInt("NoAI", 0);
            } else {
                tag.setInt("NoAI", 1);
            }
            nmsEntity.f(tag);

            /*
            Object nmsEntity = entity.getClass().getMethod("getHandle", new Class[0]).invoke(entity);

            Object tag = ReflectionUtil.getNmsClass("NBTTagCompound");

            nmsEntity.getClass().getMethod("c", new Class[0]).invoke(tag);

            nmsEntity.getClass().getMethod("setInt", new Class[0]).invoke("NoAI", 1);

            nmsEntity.getClass().getMethod("f", new Class[0]).invoke(tag);
            */


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred in NMS, are you using 1.9?");
        }
    }
}
