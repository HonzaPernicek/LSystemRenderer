package LSystem;

import static java.lang.Math.random;
import static org.lwjgl.opengl.GL11.*;

public class Plant {
    public String str;
    public float lenght;
    public float angle;
    public float width;
    public String ruleS;
    public String ruleF;
    public String ruleB1;
    public String ruleB2;

    private float green = 0.1f;


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

    public Plant(String str, float lenght, float angle, float width, String ruleS, String ruleF, String ruleB1, String ruleB2){
        this.str = str;
        this.lenght = lenght;
        this.angle = angle;
        this.width = width;
        this.ruleS = ruleS;
        this.ruleF = ruleF;
        this.ruleB1 = ruleB1;
        this.ruleB2 = ruleB2;
    }

    public void draw(){
        for (int i = 0; i < str.length(); i++){
            switch (str.charAt(i)){
                case 'F':
                    glLineWidth(width);
                    glColor3f(0.2f, green, 0.0f);

                    glBegin(GL_LINES);
                    glVertex2f(0,0);
                    glVertex2f(0,lenght);
                    glEnd();

                    glTranslatef(0,lenght,0);
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
                    width *= 1./0.7;
                    green -= 0.1f;
                    break;
                case '<': //down the size
                    width *= 0.7;
                    green += 0.1f;
                    break;
            }
        }
    }
    public void expand(){
        String newStr = "";

        //Setting the rules for generating
        for(int i = 0; i < str.length(); i++){
            switch (str.charAt(i)){
                case 'S':
                    newStr += ruleS;
                    break;
                case 'F':
                    newStr += ruleF;
                    break;
                case 'B':
                    if((random()*10)<5){
                        newStr += ruleB1;
                    }else{
                        newStr += ruleB2;
                    }
                    break;
                default:
                    newStr += str.charAt(i);
                    break;
            }
        }
        str = newStr;
        System.out.println(str);
    }
}

//TODO: GUI
