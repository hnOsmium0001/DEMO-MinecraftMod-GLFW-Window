//package io.github.hnosmium0001.glfwdemo.minecraft.gui
//
//import imgui.ImGui
//import imgui.MINECRAFT_BEHAVIORS
//import imgui.classes.Context
//import imgui.impl.gl.ImplGL3
//import imgui.impl.glfw.ImplGlfw
//import net.minecraft.client.Minecraft
//import net.minecraft.client.gui.screen.Screen
//import net.minecraft.util.text.StringTextComponent
//import uno.glfw.GlfwWindow
//
//class ImGuiGUI : Screen(StringTextComponent("ImGui")) {
//    private val window: GlfwWindow
//    private val implGlfw: ImplGlfw
//    private val implGL3: ImplGL3
//    private val showDemoWindow = booleanArrayOf(false)
//
//    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
//        implGL3.newFrame()
//        implGlfw.newFrame()
//        with(ImGui) {
//            newFrame()
//            text("Hello, world!")
//            checkbox("Show demo window", showDemoWindow)
//            if (showDemoWindow[0]) {
//                showDemoWindow(showDemoWindow)
//            }
//            render()
//            implGL3.renderDrawData(drawData!!)
//        }
//    }
//
//    init {
//        MINECRAFT_BEHAVIORS = true
//        window = GlfwWindow.from(Minecraft.getInstance().mainWindow.handle)
//        window.makeContextCurrent()
//        Context()
//        implGlfw = ImplGlfw(window, false, null)
//        implGL3 = ImplGL3()
//    }
//}
