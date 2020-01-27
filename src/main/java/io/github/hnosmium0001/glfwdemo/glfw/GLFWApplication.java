package io.github.hnosmium0001.glfwdemo.glfw;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class GLFWApplication implements Runnable {

    public static Thread startApplication(GLFWApplication app) {
        Thread thread = new Thread(app);
        thread.start();
        return thread;
    }

    private int width, height;

    @Override
    public void run() {
        glfwDefaultWindowHints();

        long window = glfwCreateWindow(600, 400, "Test Window", 0L, 0L);
        if (window == 0L) {
            throw new RuntimeException("Failed to create GLFW window");
        }
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glfwSetFramebufferSizeCallback(window, this::framebufferSizeCallback);
        glfwSetKeyCallback(window, this::keyCallback);
        glfwSetCursorPosCallback(window, this::cursorPosCallback);
        glfwSetMouseButtonCallback(window, this::mouseButtonCallback);
        glfwSetScrollCallback(window, this::scrollCallback);

        try {
            while (!glfwWindowShouldClose(window)) {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glClearColor(0.2f, 0.4f, 0.4f, 1.0f);

                render(window);

                glfwSwapBuffers(window);
                glfwPollEvents();
            }
        } finally {
            glfwDestroyWindow(window);
        }
    }

    private void render(long window) {
        glColor3f(1.0f, 1.0f, 1.0f);
        glBegin(GL_TRIANGLES);
        glVertex2f(0.0f, 1.0f);
        glVertex2f(-1.0f, -1.0f);
        glVertex2f(1.0f, -1.0f);
        glEnd();
    }

    private void framebufferSizeCallback(long window, int width, int height) {
        this.width = width;
        this.height = height;
    }

    private void keyCallback(long window, int key, int scanCode, int action, int mods) {
        System.out.println(String.format("Key: %d, scan code: %d, action: %d, mods: %d", key, scanCode, action, mods));
    }

    private void cursorPosCallback(long window, double x, double y) {
    }

    private void mouseButtonCallback(long window, int button, int action, int mods) {
        System.out.println(String.format("Mouse button: %d, action: %d, mods: %d", button, action, mods));
    }

    private void scrollCallback(long window, double xOff, double yOff) {
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
