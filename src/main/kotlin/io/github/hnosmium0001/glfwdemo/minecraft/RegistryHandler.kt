package io.github.hnosmium0001.glfwdemo.minecraft

import io.github.hnosmium0001.glfwdemo.minecraft.block.ExampleBlock
import io.github.hnosmium0001.glfwdemo.minecraft.block.ImGuiBlock
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

object RegistryHandler {
    var exampleBlock: ExampleBlock? = null
    var imGuiBlock: ImGuiBlock? = null

    @SubscribeEvent
    fun onBlockRegister(event: RegistryEvent.Register<Block>) {
        exampleBlock = ExampleBlock(Block.Properties.create(Material.ROCK))
        exampleBlock!!.registryName = ResourceLocation(DemoMod.MODID, "example_block")
        imGuiBlock = ImGuiBlock(Block.Properties.create(Material.ROCK))
        imGuiBlock!!.registryName = ResourceLocation(DemoMod.MODID, "imgui_block")
        event.registry.register(exampleBlock)
    }

    @SubscribeEvent
    fun onItemRegister(event: RegistryEvent.Register<Item>) {
        event.registry.register(BlockItem(exampleBlock!!, Item.Properties()).setRegistryName(exampleBlock?.registryName))
        event.registry.register(BlockItem(imGuiBlock!!, Item.Properties()).setRegistryName(imGuiBlock?.registryName))
    }
}