Redstone Armory
==============

Enhancing the Redstone (Flux) revolution!


##Branch Information

###If you are creating a pull request, keep these in mind:

* Latest stable release = Master (__Never PR this. It will be denied immediately.__)
* 1.6.4 dev = 1.6.4
* 1.7.10 dev =  1.7.10

##Dev setup

###Workspace Setup
1. Clone and run `gradlew setupDecompWorkspace` then (if you use Eclipse) `gradlew eclipse` or (if you use IDEA) `gradlew idea`.
2. Open in IDE of your choice.
3. Set the libs folder as an external library. Add `-Dfml.coreMods.load=cofh.asm.LoadingPlugin` to your launch settings.
4. You should be all setup now.

###Dependencies
All of these (must be deobfuscated) are required for the dev workspace to work correctly:

* CoFHLib (Found on the CoFH CurseForge)
* CoFHCore (Found on the CoFH CurseForge)
* Redstone Arsenal (Found on the CoFH CurseForge)
* Thermal Foundation (Found on the CoFH CurseForge)
* ttCore (Automagically added to workspace with Maven)