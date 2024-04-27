// Renderer.java
package Render;

import LSystem.Plant;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.opengl.GL11.*;

public class Renderer extends AbstractRenderer {

    Plant plant;
    int iterations = 8;
    String seed = "S";
    String ruleS = "FB";
    String ruleF = "FF";
    String ruleB1 = "[<-FB>][<++FB>][<--FB>]";
    String ruleB2 = "[<--FB>][<+FB>][<++FB>]";
    private boolean isEditing = false;
    private String text;
    private int field;

    public Renderer() {
        super(800, 600);
    }

    private GLFWKeyCallback keyCallback = new GLFWKeyCallback() {
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
                    text += "F";
                    System.out.println(text);
                }
            }
            if (action == GLFW_PRESS) {
                //System.out.println("Key pressed " + key);
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
                    System.out.println("Seed");
                    isEditing = true;
                    field = 1;
                } else if (x > 50 && x < 100 && y > 65 && y < 80) {
                    System.out.println("Iterations");
                    isEditing = true;
                    field = 2;
                } else if (x > 50 && x < 100 && y > 95 && y < 110) {
                    System.out.println("Rule S");
                    isEditing = true;)
                    field = 3;
                } else if (x > 50 && x < 100 && y > 115 && y < 140) {
                    System.out.println("Rule F");
                    isEditing = true;
                    field = 4;
                } else if (x > 50 && x < 100 && y > 155 && y < 170) {
                    System.out.println("Rule B1");
                    isEditing = true;
                    field = 5;
                } else if (x > 50 && x < 100 && y > 185 && y < 200) {
                    System.out.println("Rule B2");
                    isEditing = true;
                    field = 6;
                }
            }
        }

    };

    @Override
    public void init() {
        super.init();
        //Setting the starting seed for generating the plant
        plant = new Plant(seed, 0.005f, (float) (Math.PI*5),10, ruleS, ruleF, ruleB1, ruleB2);
        expandPlant();
    }

    private void expandPlant() {
        for (int i = 0; i < iterations; i++) {
            plant.expand();
        }
    }

    @Override
    public void display() {
        glViewport(0, 0, width, height);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glMatrixMode(GL_MODELVIEW);

        glRotatef(0.2f,0f,1f,0f);

        glPushMatrix();

        glTranslatef(0f,-0.8f,0f);

        plant.draw();

        glPopMatrix();

        glViewport(0,0,width,height);
        textRenderer.clear();
        textRenderer.setScale(1d);
        textRenderer.addStr2D(50, 50, "Seed: " + seed);
        textRenderer.addStr2D(50, 80, "Iterations: " + iterations);
        textRenderer.addStr2D(50, 110, "Rule 1 : S = " + ruleS);
        textRenderer.addStr2D(50, 140, "Rule 2 : F = " + ruleF);
        textRenderer.addStr2D(50, 170, "Rule 3a : B1 = " + ruleB1);
        textRenderer.addStr2D(50, 200, "Rule 3b : B2 = " + ruleB2);

        textRenderer.addStr2D(width - 200, height - 15, " (c) Jan Mejtřík : Zápočtový projekt");
        textRenderer.draw();
    }

    public GLFWMouseButtonCallback getGlfwMouseButtonCallback() {
        return glfwMouseButtonCallback;
    }

    public GLFWKeyCallback getGlfwKeyCallback() {
        return glfwKeyCallback;
    }
}
