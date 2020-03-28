package io.github.hnosmium0001.glfwdemo.minecraft.gui

import io.github.hnosmium0001.glfwdemo.glfw.GLFWApplication
import io.github.hnosmium0001.glfwdemo.glfw.JImGuiApplication
import net.minecraft.util.text.StringTextComponent
import powerlessri.harmonics.gui.debug.RenderEventDispatcher
import powerlessri.harmonics.gui.screen.WidgetScreen
import powerlessri.harmonics.gui.widget.IWidget
import powerlessri.harmonics.gui.widget.TextField
import powerlessri.harmonics.gui.window.AbstractWindow
import java.util.*

class ExampleGUI : WidgetScreen(StringTextComponent("test")) {
    override fun init() {
        super.init()
        primaryWindow = Window()
//        GLFWApplication.startApplication(GLFWApplication())
        JImGuiApplication.startApplication(JImGuiApplication())
    }

    class Window : AbstractWindow() {
        private val children: MutableList<IWidget> = ArrayList()
        // Size of vanilla GUI borders
        // Can be any positive number, depending on the need
        override fun getBorderSize(): Int = 4

        override fun getChildren(): List<IWidget> {
            return children
        }

        override fun render(mouseX: Int, mouseY: Int, particleTicks: Float) { // Event for before this window renders
            // Distribute render event of this window for inspection to work
            // technically optional, but very helpful when debugging
            // This also applies to all widgets, which we will introduce later
            RenderEventDispatcher.onPreRender(this, mouseX, mouseY)
            // Draw vanilla style background, with border size 4 of the size of this window
            renderVanillaStyleBackground()
            // Utility method for rendering all children widgets
            // which we have none, for now
            renderChildren(mouseX, mouseY, particleTicks)
            // Event for after this window renders
            RenderEventDispatcher.onPostRender(this, mouseX, mouseY)
        }

        init {
            // Width and height of this window
            setContents(100, 80)
            // Move the window to the center of the screen
            centralize()
            val textField = TextField(40, 14) // Width and height
            textField.attachWindow(this)
            children.add(textField)
        }
    }
}