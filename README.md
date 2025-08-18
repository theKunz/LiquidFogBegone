# LiquidFogBegone
A Minecraft mod for customizing how dense the fog overlay is when in water or lava

Requires Fabric API and Cloth Config

## -- Build note --
In order to get the mod working from a build of the source code, build the project and in `build/libs` you should see three `.jar` files, two of which are `liquidfogbegone-1.2.0.jar` and `liquidfogbegone-1.2.0-all.jar`. 
Open the contents of both using any decompression tool (7zip, etc.). Inside `liquidfogbegone-1.2.0-all.jar` at the top level there will be a folder named "de". Take this folder and copy it and all its contents into `liquidfogbegone-1.2.0.jar` and the mod should then work.

This has to be done at present because I am having trouble with the Shadow Gradle Plugin including _all_ the dependcies when it just needs the one; the documentation on how to filter for specific dependencies was only mildly helpful and only caused new problems when I tried it.
