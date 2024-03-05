package world.maryt.no_mob_friendly_fire.config;

import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.HashSet;

public class ConfigParser {

    public static HashMap<ResourceLocation, HashSet<ResourceLocation>> oneSidedFriendlyPairs = new HashMap<>();

    public static void parse(String[] configList) {
        for (String ruleEntry: configList) {
            String[] ruleArray = ruleEntry.split(";");
            if (ruleArray.length == 2) {
                // isMutual is ignored, must be mutual pair
                ResourceLocation from = parseResourceLocation(ruleArray[0]);
                ResourceLocation to = parseResourceLocation(ruleArray[1]);
                if (from != null && to != null) {
                    if (oneSidedFriendlyPairs.containsKey(from)) {
                        oneSidedFriendlyPairs.get(from).add(to);
                    } else {
                        HashSet<ResourceLocation> newEntry = new HashSet<>();
                        newEntry.add(to);
                        oneSidedFriendlyPairs.put(from, newEntry);
                    }
                    // And vise versa
                    if (oneSidedFriendlyPairs.containsKey(to)) {
                        oneSidedFriendlyPairs.get(to).add(from);
                    } else {
                        HashSet<ResourceLocation> newEntry = new HashSet<>();
                        newEntry.add(from);
                        oneSidedFriendlyPairs.put(to, newEntry);
                    }
                }
            }
            if (ruleArray.length == 3) {
                // isMutual is not ignored
                ResourceLocation from = parseResourceLocation(ruleArray[0]);
                ResourceLocation to = parseResourceLocation(ruleArray[1]);
                if (from != null && to != null) {
                    if (Boolean.parseBoolean(ruleArray[2])) {
                        if (oneSidedFriendlyPairs.containsKey(from)) {
                            oneSidedFriendlyPairs.get(from).add(to);
                        } else {
                            HashSet<ResourceLocation> newEntry = new HashSet<>();
                            newEntry.add(to);
                            oneSidedFriendlyPairs.put(from, newEntry);
                        }
                        // And vise versa
                        if (oneSidedFriendlyPairs.containsKey(to)) {
                            oneSidedFriendlyPairs.get(to).add(from);
                        } else {
                            HashSet<ResourceLocation> newEntry = new HashSet<>();
                            newEntry.add(from);
                            oneSidedFriendlyPairs.put(to, newEntry);
                        }
                    } else {
                        if (oneSidedFriendlyPairs.containsKey(from)) {
                            oneSidedFriendlyPairs.get(from).add(to);
                        } else {
                            HashSet<ResourceLocation> newEntry = new HashSet<>();
                            newEntry.add(to);
                            oneSidedFriendlyPairs.put(from, newEntry);
                        }
                    }
                }
            }
        }
    }

    public static ResourceLocation parseResourceLocation(String str) {
        String[] parsed = str.split(":");
        if (parsed.length == 2) {
            return new ResourceLocation(parsed[0],parsed[1]);
        } else {
            return null;
        }
    }
    public static boolean isFriendlyTo(ResourceLocation from, ResourceLocation to) {
        return oneSidedFriendlyPairs.containsKey(from) && oneSidedFriendlyPairs.get(from).contains(to);
    }
}
