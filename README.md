## NoMobFriendlyFire

Simple mod to make A type of mob not to attack B, controlled by configuration file.

Define your rules like below:

```ini
minecraft:zombie;minecraft:skeleton;false
```

The last bool determine if the "friendly pair" is mutual.

For the example above, zombies will not attack skeletons, but the latter may still attack the former.

If you set the bool to "true", they will not attack each other. "True" can be omitted.

```ini
minecraft:zombie;minecraft:skeleton;true
minecraft:zombie;minecraft:skeleton
```
