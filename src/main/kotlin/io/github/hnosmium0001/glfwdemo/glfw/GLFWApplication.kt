package io.github.hnosmium0001.glfwdemo.glfw

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11

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
