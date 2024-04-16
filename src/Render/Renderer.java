// Renderer.java
package Render;

import LSystem.Plant;

import static org.lwjgl.opengl.GL11.*;

public class Renderer extends AbstractRenderer {

    Plant plant;
    int iterations;

    public Renderer() {
        super(800, 600);
        iterations = 8;
    }

    @Override
    public void init() {
        super.init();
        //Setting the starting seed for generating the plant
        plant = new Plant("S", 0.005f, (float) (Math.PI*5),5);
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

        glRotatef(0.2f,0f,1f,0f);

        glPushMatrix();

        glTranslatef(0f,-0.8f,0f);

        plant.draw();

        glPopMatrix();




    }
}
