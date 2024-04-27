// Renderer.java
package Render;

import LSystem.Plant;

import static org.lwjgl.opengl.GL11.*;

public class Renderer extends AbstractRenderer {

    Plant plant;
    int iterations = 8;
    String seed = "S";
    String ruleS = "FB";
    String ruleF = "FF";
    String ruleB1 = "[<-FB>][<++FB>][<--FB>]";
    String ruleB2 = "[<--FB>][<+FB>][<++FB>]";

    public Renderer() {
        super(800, 600);
    }

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
}
