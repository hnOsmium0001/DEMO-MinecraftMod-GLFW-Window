package io.github.hnosmium0001.glfwdemo.minecraft.block

import io.github.hnosmium0001.glfwdemo.minecraft.gui.ImGuiGUI
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.world.World

class ImGuiBlock(properties: Properties) : Block(properties) {
    override fun onBlockActivated(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, rayTraceResult: BlockRayTraceResult): Boolean {
        if (world.isRemote) {
            Minecraft.getInstance().displayGuiScreen(ImGuiGUI())
        }
        return true
    }
}
