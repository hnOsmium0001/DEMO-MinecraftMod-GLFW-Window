package io.github.hnosmium0001.glfwdemo.minecraft

import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import org.ice1000.jimgui.JImGui
import org.ice1000.jimgui.util.JniLoader

@Mod(DemoMod.MODID)
object DemoMod {
    const val MODID = "glfwdemo"
//    val gui: JImGui

    init {
        FMLKotlinModLoadingContext.get().modEventBus.register(RegistryHandler)

        JniLoader.load()
//        gui = JImGui.fromExistingPointer(Minecraft.getInstance().mainWindow.handle);
//        gui.initBeforeMainLoop()
    }
}
