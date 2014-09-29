Redstone Armory [![Build Status](http://tehnut.info/jenkins/job/RedstoneArmory/badge/icon)](http://tehnut.info/jenkins/job/RedstoneArmory/)
==============

Enhancing the Redstone (Flux) revolution!


##Branch Information

###If you are creating a pull request, keep these in mind:

* Latest release = Master (__Never PR this. It will be denied immediately.__)
* 1.6.4 dev = 1.6.4
* 1.7.10 dev =  1.7.10

##Dev setup

1. Clone and run ___gradlew setupDecompWorkspace___ then (if you use Eclipse) ___gradlew eclipse___ or (if you use IDEA) ___gradlew idea___.
2. Open in IDE of your choice.
3. Set the libs folder as an external library. Add ___-Dfml.coreMods.load=cofh.asm.LoadingPlugin___ to your launch settings.
4. You should be all setup now.
