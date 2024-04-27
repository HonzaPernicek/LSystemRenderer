package Render;

import lwjglutils.OGLTextRenderer;
import lwjglutils.OGLUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import transforms.Camera;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public abstract class AbstractRenderer {

    private int pass;
    protected int width;
    protected int height;
    private boolean isEditing = false;
    private String text;
    protected OGLTextRenderer textRenderer;

    public AbstractRenderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public AbstractRenderer() {
        this.width = 600;
        this.height = 400;
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
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                // We will detect this in our rendering loop
                glfwSetWindowShouldClose(window, true);

            //KEY GUI
            if (action == GLFW_RELEASE) {
                if(key == GLFW_KEY_ENTER){
                    isEditing = false;
                }
                if(isEditing){
                    text += key;
                    System.out.println(text);
                }
            }
            if (action == GLFW_PRESS) {
                //System.out.println("Key pressed " + key);
            }
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
            //mouseButton1 = glfwGetMouseButton(window, GLFW_MOUSE_BUTTON_1) == GLFW_PRESS;
            DoubleBuffer xBuffer = BufferUtils.createDoubleBuffer(1);
            DoubleBuffer yBuffer = BufferUtils.createDoubleBuffer(1);
            glfwGetCursorPos(window, xBuffer, yBuffer);
            double x = xBuffer.get(0);
            double y = yBuffer.get(0);

            if (button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS) {
                //System.out.println("Mouse button 1 is pressed at cursor position [" + x + ", " + y + "]");
            }

            if (button == GLFW_MOUSE_BUTTON_1 && action == GLFW_RELEASE) {
                //System.out.println("Mouse button 1 is released at cursor position [" + x + ", " + y + "]");


                //GUI HERE
                if(x > 50 && x < 200 && y > 35 && y < 50){
                    System.out.println("button 1 pressed");
                    isEditing = true;
                } else if (x > 50 && x < 100 && y > 65 && y < 80) {
                    System.out.println("button 2 pressed");
                } else if (x > 50 && x < 100 && y > 95 && y < 110) {
                    System.out.println("button 3 pressed");
                } else if (x > 50 && x < 100 && y > 115 && y < 140) {
                    System.out.println("button 4 pressed");
                } else if (x > 50 && x < 100 && y > 155 && y < 170) {
                    System.out.println("button 5 pressed");
                } else if (x > 50 && x < 100 && y > 185 && y < 200) {
                    System.out.println("button 6 pressed");
                }
            }
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
