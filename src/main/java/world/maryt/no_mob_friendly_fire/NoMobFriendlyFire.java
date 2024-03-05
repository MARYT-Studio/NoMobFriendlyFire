package world.maryt.no_mob_friendly_fire;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import world.maryt.no_mob_friendly_fire.handler.NoMobFriendlyFireHandler;

import java.io.File;

import static world.maryt.no_mob_friendly_fire.config.ConfigParser.parse;

@Mod(modid = NoMobFriendlyFire.MOD_ID, name = NoMobFriendlyFire.NAME, version = NoMobFriendlyFire.VERSION)
public class NoMobFriendlyFire {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String NAME = "NoMobFriendlyFire";
    public static final String VERSION = Tags.VERSION;

    public static Logger LOGGER = LogManager.getLogger(NAME);

    public static String[] FRIENDLY_MOB_LIST;
    public static boolean DEBUG = false;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent preEvent) {
        Configuration config = new Configuration(new File(Loader.instance().getConfigDir(), MOD_ID + ".cfg"));
        try {
            config.load();
            {
                Property property = config.get(Configuration.CATEGORY_GENERAL, "FriendlyMobList", new String[]{});
                property.setComment("Format: <Mob A's ID>;<Mob B's ID>;[isMutual, true or false, optional]. Example: minecraft:zombie;minecraft:skeleton;false will make zombie friendly to skeleton, but not vise versa. minecraft:creeper;minecraft:zombie;true will make creeper and zombie not attack each other.");
                NoMobFriendlyFire.FRIENDLY_MOB_LIST = property.getStringList();
                property.setShowInGui(false);
            }
            {
                Property property = config.get("Debug", "Debug", false);
                property.setComment("Enable this for debugging purpose");
                NoMobFriendlyFire.DEBUG = property.getBoolean();
                property.setShowInGui(false);
            }
            parse(FRIENDLY_MOB_LIST);
            LOGGER.info("No Mob Friendly Fire - configuration loaded.");
        } finally {
            config.save();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new NoMobFriendlyFireHandler());
    }
}
