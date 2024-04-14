// Renderer.java
package Render;

import LSystem.Plant;

import static org.lwjgl.opengl.GL11.*;

public class Renderer extends AbstractRenderer {

    Plant plant;
    int iterations;

    public Renderer() {
        super(800, 600);
        iterations = 5;
    }

    @Override
    public void init() {
        super.init();
        plant = new Plant("S", 0.2f, (float) (0.2 * Math.PI)); // Initial length and angle
        expandPlant(); // Expand the plant initially
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
        // Set color for drawing lines (green)
        glColor3f(0.0f, 1.0f, 0.0f);

        glBegin(GL_LINES);
        glPushMatrix();

        plant.draw();

        glPopMatrix();

        glEnd();



        /*
        glVertex2f(0.1f,0f);
        glVertex2f(0.2f,-0.1f);
        glLoadIdentity();
        glTranslatef(-0.3f,0f,0f);
        */

    }
}
