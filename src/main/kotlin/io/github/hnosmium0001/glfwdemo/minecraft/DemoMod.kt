package io.github.hnosmium0001.glfwdemo.minecraft

import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.minecraftforge.fml.common.Mod

@Mod(DemoMod.MODID)
object DemoMod {
    const val MODID = "glfwdemo"

    init {
        FMLKotlinModLoadingContext.get().modEventBus.register(RegistryHandler)
    }
}
