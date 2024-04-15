package LSystem;

import static java.lang.Math.PI;
import static java.lang.Math.random;
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
        for (int i = 0; i < str.length(); i++){
            switch (str.charAt(i)){
                case 'F':
                    glBegin(GL_LINES);
                    glVertex2f(0,0);
                    glVertex2f(0,lenght);
                    glEnd();

                    glTranslatef(0,lenght,0);
                    break;
                case 'l':
                    glRotatef(-angle, 0, 0, 1f);
                    break;
                case 'r':
                    glRotatef(angle, 0, 0, 1f);
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
    public void expand(){
        String newStr = "";

        //Setting the rules for generating
        for(int i = 0; i < str.length(); i++){
            switch (str.charAt(i)){
                case 'S':
                    newStr += "FB";
                    break;
                case 'F':
                    newStr += "FF";
                    break;
                case 'B':
                    if((random()*100)<50){
                        newStr += "[ulFBd][urrFBd]";
                    }else{
                        newStr += "[ullFBd][urFBd]";
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
