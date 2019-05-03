## Minecraft Murder Mod
A Minecraft mod designed for the Minecraft Murder staff team. 

### Example
Upon pressing the tilda `~` key, you'll be greeted with this GUI. It i highly customizable, and immediately sends important messages and links in chat based on the context of each button.
![Demo](https://i.imgur.com/0xubmty.png)
 

### Contributing
Note: there is some basic protection which only allows official staff members to use this mod, however it is modifyable for personal use.

1. Decompile Minecraft 1.12.2 using the [Minecraft Mod Coder Pack 940](http://www.modcoderpack.com/) and by following [these instructions](https://gist.github.com/Pokechu22/97bf5bd528eeadef09dcbae8a15b009f)
2. Clone this repo to `mcp940/src/minecraft/mcmstaffmod`
3. Add the code `if (AuthUtil.canUse()) ModMain.instance().gameLoop();` anywhere within the `updateTimer()` method of the `net.minecraft.util.Timer` class
4. For textures to work, copy the `assets` folder from this repository into `mcp940/jars/versions/1.12.2.jar`
5. Change the value of `mcmstaffmod.ModMain.debug` to `true`

Now you should be able to run the game with the mod! Be sure when exporting to...

6. Change the value of `mcmstaffmod.ModMain.debug` to `true`
7. Recompile and reobfuscate Minecraft using the MCP tools (e.g. `./recompile.sh` then `./reobfuscate.sh`)
8. Be sure to package the `assets` folder with the mod `.class` files

And you're done! You've compiled and exported the mod!
