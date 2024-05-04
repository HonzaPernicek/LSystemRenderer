package Render;

import lwjglutils.OGLTextRenderer;
import lwjglutils.OGLUtils;
import org.lwjgl.glfw.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class AbstractRenderer {

    private int pass;
    protected int width;
    protected int height;
    protected OGLTextRenderer textRenderer;

    public AbstractRenderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void init() {
        OGLUtils.printOGLparameters();
        OGLUtils.printLWJLparameters();
        OGLUtils.printJAVAparameters();
        OGLUtils.shaderCheck();
        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        textRenderer = new OGLTextRenderer(width, height);
    }

    public void display() {
        glViewport(0, 0, width, height);
        pass++;
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    }

    protected GLFWKeyCallback glfwKeyCallback = new GLFWKeyCallback() {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
        }
    };

    protected GLFWWindowSizeCallback glfwWindowSizeCallback = new GLFWWindowSizeCallback() {
        @Override
        public void invoke(long window, int w, int h) {
            if (w > 0 && h > 0) {
                width = w;
                height = h;
                //System.out.println("Windows resize to [" + w + ", " + h + "]");
                if (textRenderer != null) {
                    textRenderer.resize(width, height);
                }
            }
        }
    };

    protected GLFWMouseButtonCallback glfwMouseButtonCallback = new GLFWMouseButtonCallback() {

        @Override
        public void invoke(long window, int button, int action, int mods) {
        }

    };

    protected GLFWCursorPosCallback glfwCursorPosCallback = new GLFWCursorPosCallback() {
        @Override
        public void invoke(long window, double x, double y) {
            //System.out.println("Cursor position [" + x + ", " + y + "]");
        }
    };

    protected GLFWScrollCallback glfwScrollCallback = new GLFWScrollCallback() {
        @Override
        public void invoke(long window, double dx, double dy) {
            //System.out.println("Mouse wheel velocity " + dy);
        }
    };

    public GLFWKeyCallback getGlfwKeyCallback() {
        return glfwKeyCallback;
    }

    public GLFWWindowSizeCallback getGlfwWindowSizeCallback() {
        return glfwWindowSizeCallback;
    }

    public GLFWMouseButtonCallback getGlfwMouseButtonCallback() {
        return glfwMouseButtonCallback;
    }

    public GLFWCursorPosCallback getGlfwCursorPosCallback() {
        return glfwCursorPosCallback;
    }

    public GLFWScrollCallback getGlfwScrollCallback() {
        return glfwScrollCallback;
    }

    public void dispose() {

    }

}
