# NoMobFriendlyFire

![logo](src/main/resources/logo.png)

___________

## Overview

Simple mod to make A type of mob not to attack B, controlled by configuration file.

Define your rules like below:

```ini
minecraft:zombie;minecraft:skeleton;false
```

The last bool determine if the "friendly pair" is mutual.

For the example above, zombies will not attack skeletons, but the latter may still attack the former.

If you set the bool to "true", they will not attack each other. "True" can be omitted, like below.

```ini
minecraft:zombie;minecraft:skeleton;true
minecraft:zombie;minecraft:skeleton
```

## Known Issue

Wither do not obey this mod's rule and will still shoot skulls to other mobs.

May be fixed some day.

## Credits

Inspired by FriendlyMobs mod ([CurseForge link](https://github.com/kegare/FriendlyMobs)). Thanks to its original author.

Presented by MARYT Studio.
