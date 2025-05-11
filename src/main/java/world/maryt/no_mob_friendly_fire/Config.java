package world.maryt.no_mob_friendly_fire;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = NoMobFriendlyFire.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue DEBUG = BUILDER.comment("Enable this for debugging purpose").define("DEBUG", false);

    private static final ForgeConfigSpec.ConfigValue<String> FRIENDLY_PAIR = BUILDER.comment("Friendly mob ID pairs.")
            .define(
                    "FRIENDLY_PAIR",
                    """
entityA;entityB;false
entityC;entityD;true
""");

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean debug;
    public static String friendlyPair;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        debug = DEBUG.get();
        friendlyPair = FRIENDLY_PAIR.get();
    }
}
