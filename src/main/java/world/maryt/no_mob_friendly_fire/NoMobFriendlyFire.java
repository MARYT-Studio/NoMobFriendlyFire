package world.maryt.no_mob_friendly_fire;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import world.maryt.no_mob_friendly_fire.handler.NoMobFriendlyFireHandler;

@Mod(NoMobFriendlyFire.MOD_ID)
public class NoMobFriendlyFire {

    public static final String MOD_ID = "no_mob_friendly_fire";
    public static final Logger LOGGER = LogUtils.getLogger();

    @SuppressWarnings("removal")
    public NoMobFriendlyFire() {
        // Event handlers' registrations must be in the constructor of the main class
        MinecraftForge.EVENT_BUS.register(NoMobFriendlyFireHandler.class);

        // Configuration building
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
