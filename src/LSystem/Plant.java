package LSystem;

import static java.lang.Math.PI;
import static java.lang.Math.random;
import static org.lwjgl.opengl.GL11.*;

public class Plant {
    public String str;
    public float lenght;
    public float angle;
    public float width;


    public Plant(String str, float lenght, float angle, float width){
        this.str = str;
        this.lenght = lenght;
        this.angle = angle;
        this.width = width;
    }

    public void draw(){
        for (int i = 0; i < str.length(); i++){
            switch (str.charAt(i)){
                case 'F':
                    glLineWidth(width);
                    glColor3f(0.0f, 1.0f, 0.0f);
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
                    width *= 1./0.8;
                    break;
                case '<': //down the size
                    width *= 0.8;
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
                        newStr += "[<-FB>][<++FB>][<--FB>]";
                    }else{
                        newStr += "[<--FB>][<+FB>][<++FB>]";
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

//TODO: Barevnost, interpolace, GUI
