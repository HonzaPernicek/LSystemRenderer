package Render;
import static org.lwjgl.opengl.GL11.*;
import LSystem.LSystem;

public class Renderer extends AbstractRenderer {
    private LSystem lSystem;
    private String generatedString;

    public Renderer() {
        super();
        this.lSystem = new LSystem("F");
        this.lSystem.addProductionRule('F', "-F-F");
        this.generatedString = this.lSystem.generate(3); // Adjust the number of iterations as needed
    }

    @Override
    public void display() {
        glViewport(0, 0, width, height);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        // Draw the generated L-system string
        double x = 0;
        double y = 0;
        double angle = 0;
        double stepSize = 0.05; // Adjust as needed

        glBegin(GL_LINES);
        glColor3f(1f, 1f, 1f);

        for (char c : generatedString.toCharArray()) {
            switch (c) {
                case 'F':
                    double newX = x + stepSize * Math.cos(Math.toRadians(angle));
                    double newY = y + stepSize * Math.sin(Math.toRadians(angle));
                    glVertex2d(x, y);
                    glVertex2d(newX, newY);
                    x = newX;
                    y = newY;
                    break;
                case '+':
                    angle += 5; // Adjust the angle increment as needed
                    break;
                case '-':
                    angle -= 5; // Adjust the angle decrement as needed
                    break;
                case '[':
                    glPushMatrix();
                    break;
                case ']':
                    glPopMatrix();
                    break;
                default:
                    // Ignore any other characters
                    break;
            }
        }
        glEnd();
    }
}
