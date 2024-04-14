package LSystem;

import static java.lang.Math.PI;
import static org.lwjgl.opengl.GL11.*;

public class Plant {
    public String str;
    public float lenght;
    public float angle;

    public Plant(String str, float lenght, float angle){
        this.str = str;
        this.lenght = lenght;
        this.angle = angle;
    }

    public void draw(){
        for (int i = 0; i <str.length(); i++){
            switch (str.charAt(i)){
                case 'F':
                    glVertex2f(0,0);
                    glVertex2f(lenght,0);

                    glTranslatef(lenght,0,0);
                    break;
                case 'l':
                    glRotatef(angle,0,0,1);
                    break;
                case 'r':
                    glRotatef(angle,0,0,1);
                    break;
                case '[':
                    glPushMatrix();
                    break;
                case ']':
                    glPopMatrix();
                    break;
            }
        }
    }
    private void expand(){
        String newStr = "";

        for(int i = 0; i < str.length(); i++){
            switch (str.charAt(i)){
                case 'S':
                    newStr += "FB";
                    break;
                case 'F':
                    newStr += "FF";
                    break;
                case 'B':
                    newStr += "[lFB][rFB]";
                    break;
                default:
                    newStr += str.charAt(i);
                    break;
            }
        }
        str = newStr;
    }
}
