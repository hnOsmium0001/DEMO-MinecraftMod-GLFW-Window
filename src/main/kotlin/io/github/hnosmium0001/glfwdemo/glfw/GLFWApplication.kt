package io.github.hnosmium0001.glfwdemo.glfw

import org.ice1000.jimgui.JImGui
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import org.lwjgl.opengl.GL30.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField

class GLFWApplication : Runnable {
    companion object {
        fun startApplication(app: GLFWApplication): Thread {
            return Thread(app).apply { start() };
        }
    }

    private var width = 0;
    private var height = 0

    override fun run() {
        glfwDefaultWindowHints()

        val window = glfwCreateWindow(600, 400, "Test Window", 0L, 0L)
        if (window == 0L) {
            throw RuntimeException("Failed to create GLFW window")
        }
        glfwMakeContextCurrent(window)
        GL.createCapabilities()

        glfwSetFramebufferSizeCallback(window, this::framebufferSizeCallback)
        glfwSetKeyCallback(window, this::keyCallback)
        glfwSetCursorPosCallback(window, this::cursorPosCallback)
        glfwSetMouseButtonCallback(window, this::mouseButtonCallback)
        glfwSetScrollCallback(window, this::cursorPosCallback)

        try {
            while (!glfwWindowShouldClose(window)) {
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
                GL11.glClearColor(0.2f, 0.4f, 0.4f, 1.0f)
                render(window)
                glfwSwapBuffers(window)
                glfwPollEvents()
            }
        } finally {
            glfwDestroyWindow(window)
        }
    }

    private fun render(window: Long) {
        GL11.glColor3f(1.0f, 1.0f, 1.0f)
        GL11.glBegin(GL11.GL_TRIANGLES)
        GL11.glVertex2f(0.0f, 1.0f)
        GL11.glVertex2f(-1.0f, -1.0f)
        GL11.glVertex2f(1.0f, -1.0f)
        GL11.glEnd()
    }

    private fun framebufferSizeCallback(window: Long, width: Int, height: Int) {
        this.width = width
        this.height = height
    }

    private fun keyCallback(window: Long, key: Int, scanCode: Int, action: Int, mods: Int) {
        println(String.format("Key: %d, scan code: %d, action: %d, mods: %d", key, scanCode, action, mods))
    }

    private fun cursorPosCallback(window: Long, x: Double, y: Double) {}

    private fun mouseButtonCallback(window: Long, button: Int, action: Int, mods: Int) {
        println(String.format("Mouse button: %d, action: %d, mods: %d", button, action, mods))
    }

    private fun scrollCallback(window: Long, xOff: Double, yOff: Double) {}
}

class JImGuiApplication : Runnable {
    companion object {
        fun startApplication(app: JImGuiApplication): Thread {
            return Thread(app).apply { start() };
        }
    }

    var renderOutput = 0;

    // JImGui uses DirectX as backend on Windows, therefore doesn't work with LWJGL which uses OpenGL
    // TODO try this on linux
    override fun run() {
        glfwDefaultWindowHints()
        val window = glfwCreateWindow(600, 400, "Test Window", 0L, 0L)
        if (window == 0L) {
            throw RuntimeException("Failed to create GLFW window")
        }

//        val gui = JImGui()
        val gui = JImGui.fromExistingPointer(window)
        gui.initBeforeMainLoop()

//        val field = JImGui::class.java.getDeclaredField("nativeObjectPtr")
//        field.isAccessible = true
//        val window = field.get(gui) as Long
        glfwMakeContextCurrent(window)
        GL.createCapabilities()

//        val fbo = glGenFramebuffers()
//        glBindFramebuffer(GL_FRAMEBUFFER, fbo)
//        glFramebufferTexture2D(GL_FRAMEBUFFER, GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, fbo, 0)
//        // Set (atomic) fbo after the settings are completed
//        renderOutput = fbo

        var clicks = 0
        while (!gui.windowShouldClose()) {
//            glBindFramebuffer(GL_FRAMEBUFFER, fbo)
//            run {
            gui.initNewFrame()
            gui.begin("Main window")
            run {
                if (gui.button("Click to add 1")) {
                    clicks++
                }
                gui.text("Clicks: $clicks")
            }
            JImGui.end()
            gui.showDemoWindow()
            gui.render()
//            }
//            glBindFramebuffer(fbo, 0)
        }
    }
}
