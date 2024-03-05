package world.maryt.no_mob_friendly_fire.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import world.maryt.no_mob_friendly_fire.NoMobFriendlyFire;

import static world.maryt.no_mob_friendly_fire.config.ConfigParser.isFriendlyTo;

public class NoMobFriendlyFireHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTargetSet(LivingSetAttackTargetEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        Entity target = event.getTarget();
        if (NoMobFriendlyFire.DEBUG) NoMobFriendlyFire.LOGGER.info("entity {} selected target {}", entity, target);
        if (entity != null && target != null && !entity.world.isRemote) {
            if (isFriendlyTo(EntityList.getKey(entity), EntityList.getKey(target))) {
                ((EntityLiving)entity).setAttackTarget(null);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onLivingAttack(LivingAttackEvent event) {
        EntityLivingBase target = event.getEntityLiving();
        if (target != null) {
            Entity entity = event.getSource().getTrueSource();
            if (entity != null && !entity.world.isRemote && isFriendlyTo(EntityList.getKey(entity), EntityList.getKey(target))) {
                event.setCanceled(true);
                if (NoMobFriendlyFire.DEBUG) NoMobFriendlyFire.LOGGER.info("entity {} attack target {}, isFriendly: {}", entity, target, isFriendlyTo(EntityList.getKey(entity), EntityList.getKey(target)));
                return;
            }
            entity = event.getSource().getImmediateSource();
            if (entity != null && !entity.world.isRemote && isFriendlyTo(EntityList.getKey(entity), EntityList.getKey(target))) {
                event.setCanceled(true);
                if (NoMobFriendlyFire.DEBUG) NoMobFriendlyFire.LOGGER.info("entity {} attack target {}, isFriendly: {}", entity, target, isFriendlyTo(EntityList.getKey(entity), EntityList.getKey(target)));
            }
        }
    }
}
