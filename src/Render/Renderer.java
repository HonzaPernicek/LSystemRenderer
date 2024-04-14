package Render;

import LSystem.Plant;

import static java.lang.Math.PI;
import static org.lwjgl.opengl.GL11.*;

public class Renderer extends AbstractRenderer {

    Plant plant;

    public Renderer() {
        super();
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void display() {
        Plant plant = new Plant("S", 1F, (float) (0.2*PI));

        glViewport(0, 0, width, height);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        // Set color for drawing lines (white)
        glColor3f(1.0f, 1.0f, 1.0f);

        glBegin(GL_LINES);

        glEnd();

    }

}
