package LSystem;

import static java.lang.Math.random;
import static org.lwjgl.opengl.GL11.*;

public class Plant {
    public String seed;
    public float length;
    public float angle;
    public float width;
    public String ruleS;
    public String ruleF;
    public String ruleB1;
    public String ruleB2;
    public String startingSeed;
    public int iterations;

    private float green = 0.1f;

    public Plant(String seed, String startingSeed, float length, float angle, float width, String ruleS, String ruleF, String ruleB1, String ruleB2, int iterations){
        this.seed = seed;
        this.startingSeed = startingSeed;
        this.length = length;
        this.angle = angle;
        this.width = width;
        this.ruleS = ruleS;
        this.ruleF = ruleF;
        this.ruleB1 = ruleB1;
        this.ruleB2 = ruleB2;
        this.iterations = iterations;
    }

    public void draw(){
        for (int i = 0; i < seed.length(); i++){
            switch (seed.charAt(i)){
                case 'F':
                    glLineWidth(width);
                    glColor3f(0.2f, green, 0.0f);

                    glBegin(GL_LINES);
                    glVertex2f(0,0);
                    glVertex2f(0, length);
                    glEnd();

                    glTranslatef(0, length,0);
                    break;
                case '-':
                    glRotatef(-angle, 0, 0, 1f);
                    glRotatef(angle*5,0f,1f,0f);
                    break;
                case '+':
                    glRotatef(angle, 0, 0, 1f);
                    glRotatef(angle*5,0f,1f,0f);
                    break;
                case '[':
                    glPushMatrix();
                    break;
                case ']':
                    glPopMatrix();
                    break;
                case '>': //up the size
                    width *= (float) (1./0.7);
                    green -= 0.1f;
                    break;
                case '<': //down the size
                    width *= 0.7F;
                    green += 0.1f;
                    break;
            }
        }
    }
    public void expand() {
        for (int n = 0; n < iterations; n++) {
            StringBuilder newStr = new StringBuilder();

            //Setting the rules for generating
            for (int i = 0; i < seed.length(); i++) {
                switch (seed.charAt(i)) {
                    case 'S':
                        newStr.append(ruleS);
                        break;
                    case 'F':
                        newStr.append(ruleF);
                        break;
                    case 'B':
                        if ((random() * 10) < 5) {
                            newStr.append(ruleB1);
                        } else {
                            newStr.append(ruleB2);
                        }
                        break;
                    default:
                        newStr.append(seed.charAt(i));
                        break;
                }
            }
            seed = newStr.toString();
            System.out.println(seed);
        }
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public void setStartingSeed(String startingSeed){
        this.startingSeed = startingSeed;
    }

    public String getStartingSeed(){
        return startingSeed;
    }

    public void setRuleS(String ruleS) {
        this.ruleS = ruleS;
    }

    public void setRuleF(String ruleF) {
        this.ruleF = ruleF;
    }

    public void setRuleB1(String ruleB1) {
        this.ruleB1 = ruleB1;
    }

    public void setRuleB2(String ruleB2) {
        this.ruleB2 = ruleB2;
    }

    public void setIterations(int iterations){
        this.iterations = iterations;
    }


    public String getRuleS() {
        return ruleS;
    }

    public String getRuleF() {
        return ruleF;
    }

    public String getRuleB1() {
        return ruleB1;
    }

    public String getRuleB2() {
        return ruleB2;
    }

    public int getIterations(){
        return iterations;
    }

    public void clear(){
        seed = startingSeed;
    }
}


