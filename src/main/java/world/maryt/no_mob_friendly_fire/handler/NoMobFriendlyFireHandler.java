package world.maryt.no_mob_friendly_fire.handler;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import world.maryt.no_mob_friendly_fire.Config;
import world.maryt.no_mob_friendly_fire.NoMobFriendlyFire;

import static world.maryt.no_mob_friendly_fire.config.ConfigParser.isFriendlyTo;

public class NoMobFriendlyFireHandler {
    @SuppressWarnings("resource")
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onTargetSet(LivingChangeTargetEvent event) {
        LivingEntity entity = event.getEntity();
        Entity target = event.getOriginalTarget();
        if (Config.debug) NoMobFriendlyFire.LOGGER.info("entity {} selected target {}", entity, target);
        if (entity != null && target != null && !entity.level().isClientSide()) {
            if (isFriendlyTo(EntityType.getKey(entity.getType()), EntityType.getKey(target.getType()))) {
                event.setNewTarget(null);
            }
        }
    }

    @SuppressWarnings("resource")
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onLivingAttack(LivingAttackEvent event) {
        LivingEntity target = event.getEntity();
        if (target != null) {
            Entity entity = event.getSource().getEntity();
            if (entity != null && !entity.level().isClientSide() && isFriendlyTo(EntityType.getKey(entity.getType()), EntityType.getKey(target.getType()))) {
                event.setCanceled(true);
                if (Config.debug) debugInfo(entity, target);
                return;
            }
            entity = event.getSource().getDirectEntity();
            if (entity != null && !entity.level().isClientSide() && isFriendlyTo(EntityType.getKey(entity.getType()), EntityType.getKey(target.getType()))) {
                event.setCanceled(true);
                if (Config.debug) debugInfo(entity, target);
            }
        }
    }

    private void debugInfo(Entity entity, LivingEntity target) {
        NoMobFriendlyFire.LOGGER.info("entity {} attack target {}, isFriendly: {}", entity, target, isFriendlyTo(EntityType.getKey(entity.getType()), EntityType.getKey(target.getType())));
    }
}
