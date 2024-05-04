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

    private final GLFWKeyCallback glfwKeyCallback = new GLFWKeyCallback() {
        @Override
        public void invoke(long window, int key, int scancode, int action, int mods) {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                // We will detect this in our rendering loop
                glfwSetWindowShouldClose(window, true);

            //KEY GUI
            if (action == GLFW_RELEASE) {
                if(key == GLFW_KEY_ENTER){
                    isEditing = false;
                    System.out.println("Applying change of rules");
                    switch (field){
                        case 1:
                            plant.setSeed(seed);
                            plant.clear();
                            plant.expand();
                            return;
                        case 2:
                            plant.setIterations(Integer.parseInt(text));
                            plant.clear();
                            plant.expand();
                            return;
                        case 3:
                            plant.setRuleS(text);
                            plant.clear();
                            plant.expand();
                            return;
                        case 4:
                            plant.setRuleF(text);
                            plant.clear();
                            plant.expand();
                            return;
                        case 5:
                            plant.setRuleB1(text);
                            plant.clear();
                            plant.expand();
                            return;
                        case 6:
                            plant.setRuleB2(text);
                            plant.clear();
                            plant.expand();
                            return;
                    }
                } else if (isEditing){
                    System.out.println(key);
                    switch (key){
                        //LETTERS
                        case GLFW_KEY_F:
                            text += "F";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_S:
                            text += "S";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_B:
                            text += "B";
                            System.out.println(text);
                            return;

                        //SYMBOLS
                        case GLFW_KEY_KP_SUBTRACT:
                            text += "-";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_KP_ADD:
                            text += "+";
                            System.out.println(text);
                            return;
                        case 91:
                            text += "[";
                            System.out.println(text);
                            return;
                        case 93:
                            text += "]";
                            System.out.println(text);
                            return;
                        case 44:
                            text += "<";
                            System.out.println(text);
                            return;
                        case 46:
                            text += ">";
                            System.out.println(text);
                            return;

                        //NUMBERS
                        case GLFW_KEY_0:
                            text += "0";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_1:
                            text += "1";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_2:
                            text += "2";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_3:
                            text += "3";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_4:
                            text += "4";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_5:
                            text += "5";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_6:
                            text += "6";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_7:
                            text += "7";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_8:
                            text += "8";
                            System.out.println(text);
                            return;
                        case GLFW_KEY_9:
                            text += "9";
                            System.out.println(text);
                            return;

                        //BACKSPACE
                        case GLFW_KEY_BACKSPACE:
                            text = removeLastChar(text);
                            System.out.println(text);
                            return;
                    }
                }
            }
            if (action == GLFW_PRESS) {
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
                    System.out.println(plant.getSeed());
                    isEditing = true;
                    field = 1;
                    plant.clear();
                    text = plant.getSeed();
                    System.out.println(text);
                } else if (x > 50 && x < 200 && y > 65 && y < 80) {
                    System.out.println("Iterations");
                    System.out.println(plant.getIterations());
                    isEditing = true;
                    field = 2;
                    text = Integer.toString(plant.getIterations());
                    System.out.println(text);
                } else if (x > 50 && x < 200 && y > 95 && y < 110) {
                    System.out.println("Rule S");
                    System.out.println(plant.getRuleS());
                    isEditing = true;
                    field = 3;
                    text = plant.getRuleS();
                    System.out.println(text);
                } else if (x > 50 && x < 200 && y > 115 && y < 140) {
                    System.out.println("Rule F");
                    System.out.println(plant.getRuleF());
                    isEditing = true;
                    field = 4;
                    text = plant.getRuleF();
                } else if (x > 50 && x < 200 && y > 155 && y < 170) {
                    System.out.println("Rule B1");
                    System.out.println(plant.getRuleB1());
                    isEditing = true;
                    field = 5;
                    text = plant.getRuleB1();
                } else if (x > 50 && x < 200 && y > 185 && y < 200) {
                    System.out.println("Rule B2");
                    System.out.println(plant.getRuleB2());
                    isEditing = true;
                    field = 6;
                    text = plant.getRuleB2();
                }
            }
        }

    };

    @Override
    public void init() {
        super.init();
        //Setting the starting seed for generating the plant
        plant = new Plant(seed, 0.005f, (float) (Math.PI*5),10, ruleS, ruleF, ruleB1, ruleB2, iterations);
        plant.expand();
    }

    private static String removeLastChar(String s) {
        return (s == null || s.isEmpty())
                ? null
                : (s.substring(0, s.length() - 1));
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
        textRenderer.addStr2D(50, 50, "Seed: " + plant.getSeed());
        textRenderer.addStr2D(50, 80, "Iterations: " + plant.getIterations());
        textRenderer.addStr2D(50, 110, "Rule 1 : S = " + plant.getRuleS());
        textRenderer.addStr2D(50, 140, "Rule 2 : F = " + plant.getRuleF());
        textRenderer.addStr2D(50, 170, "Rule 3a : B1 = " + plant.getRuleB1());
        textRenderer.addStr2D(50, 200, "Rule 3b : B2 = " + plant.getRuleB2());
        textRenderer.addStr2D(50,250, "text console");
        textRenderer.addStr2D(50,265, text);

        textRenderer.addStr2D(width - 350, height - 15, "L System renderer v 1.0 :Jan Mejstřík : Zápočtový projekt");
        textRenderer.draw();
    }

    public GLFWMouseButtonCallback getGlfwMouseButtonCallback() {
        return glfwMouseButtonCallback;
    }

    public GLFWKeyCallback getGlfwKeyCallback() {
        return glfwKeyCallback;
    }
}
