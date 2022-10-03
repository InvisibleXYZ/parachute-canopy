import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class pc extends Frame { //Inherit class Frame
    static Character[] word1 = new Character[8];
    static Character[] word2 = new Character[8];
    static Character[] word3 = new Character[8];
    static Character[] word4 = new Character[8];
    static Character[] word5 = new Character[8];

    public static void main(String[] args) {
        try {
            args[0] = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("At least one word");
            System.exit(0); //Exit the program
        }

        if (args.length > 5) {
            System.out.println("Too many words. Limit is 5");
            System.exit(0); //Exit the program
        }

        for (String arg : args) {
            if (arg.length() > 8) {
                System.out.println("The length of every word cannot be more then 8 characters");
                System.exit(0);
            }
            if (!arg.matches("[\\p{L}&&\\w]+")) { // \p{L} - only letters. \w - only latin characters. + - more than once;
                System.out.println("Only latin letters are allowed");
                System.exit(0); //Exit the program
            }
        }

        int size = 0;
        for (int i = 0; i < args.length; i++)
            for (int ii = 0; ii <= args[i].length(); ii++)
                if (!(i == args.length - 1 && ii == args[i].length())) //Always but not when the last word.
                    size++;

        Character[] characters = new Character[size];

        size = 0; //Now size helps to contain character in right place
        for (int i = 0; i < args.length; i++)
            for (int ii = 0; ii <= args[i].length(); ii++)
                if (ii < args[i].length()) {
                    characters[size] = new Character(); //NullPointerException
                    characters[size].setInfo(args[i].charAt(ii)); //Save the character
                    characters[size].setBinary(Integer.toBinaryString(args[i].charAt(ii))); //Binary of character
                    ++size;
                } else if (ii == args[i].length() && i != args.length - 1) {
                    characters[size] = new Character();
                    characters[size].setInfo(' ');
                    characters[size].setBinary(" ");
                    ++size;
                }

        for (Character character : characters)
            System.out.print(character.getInfo());
        System.out.println();

        for (Character character : characters) {
            if (!character.getBinary().equals(" "))
                System.out.print(character.getBinary() + ' ');
            else
                System.out.print("|separator| ");
        }
        System.out.println();

        new pc(characters);
    }

    public pc(Character[] characters) {
        this.setSize(800, 800);
        this.setVisible(true);

        int word_separator = 1;
        int size = 0;
        for (Character character : characters) {
            if (character.getInfo() == ' ') {
                ++word_separator;
                size = 0;
            } else {
                switch (word_separator) {
                    case 1 -> word1[size] = character;
                    case 2 -> word2[size] = character;
                    case 3 -> word3[size] = character;
                    case 4 -> word4[size] = character;
                    case 5 -> word5[size] = character;
                }
                ++size;
            }
        }
    }

    public void paint(Graphics g) { //Every arc is 5 degrees
        for (int i = 0; i < 5; i++) { //Number of words
            int angle = -1;
            for (int ii = 0; ii < 8; ii++) {//Number of up_sector
                for (int iii = 0; iii <9; iii++){
                    if(iii < 7) {
                        switch (i) {
                            case 0:
                                if (word5[ii] == null){
                                    g.setColor(Color.ORANGE);
                                }
                                else if(word5[ii].getBinary().charAt(iii) == '0'){
                                    g.setColor(Color.WHITE);
                                }
                                else
                                    g.setColor(Color.RED);
                                break;
                            case 1:
                                if (word4[ii] == null){
                                    g.setColor(Color.ORANGE);
                                }
                                else if(word4[ii].getBinary().charAt(iii) == '0'){
                                    g.setColor(Color.WHITE);
                                }
                                else
                                    g.setColor(Color.RED);
                                break;
                            case 2:
                                if (word3[ii] == null){
                                    g.setColor(Color.ORANGE);
                                }
                                else if(word3[ii].getBinary().charAt(iii) == '0'){
                                    g.setColor(Color.WHITE);
                                }
                                else
                                    g.setColor(Color.RED);
                                break;
                            case 3:
                                if (word2[ii] == null){
                                    g.setColor(Color.ORANGE);
                                }
                                else if(word2[ii].getBinary().charAt(iii) == '0'){
                                    g.setColor(Color.WHITE);
                                }
                                else
                                    g.setColor(Color.RED);
                                break;
                            case 4:
                                if (word1[ii] == null){
                                    g.setColor(Color.ORANGE);
                                }
                                else if(word1[ii].getBinary().charAt(iii) == '0'){
                                    g.setColor(Color.WHITE);
                                }
                                else
                                    g.setColor(Color.RED);
                                break;
                        }
                    }
                    else
                        g.setColor(Color.gray);
                    g.fillArc(50 + (70 * i),50 + (70 * i), 700 - (140 * i), 700 - (140 * i), 85 + (angle) * 5, 5);
                    --angle;
                }
            }
            g.setColor(Color.black);
            g.drawOval(50 + (70 * i),50 + (70 * i), 700 - (140 * i), 700 - (140 * i));
        }

        Line2D line = new Line2D.Double(400, 400, 400, 50);
        AffineTransform at;
        Graphics2D g2d = (Graphics2D)g;

        for (int i = 1; i <= 72; i++){
            at = AffineTransform.getRotateInstance(Math.toRadians(i * 5), line.getX1(), line.getY1());
            g2d.draw(at.createTransformedShape(line));
        }
    }
}

class Character{
    private char info;
    private String binary;

    public void setInfo(char info){this.info = info;}
    public void setBinary(String binary){this.binary = binary;}

    public char getInfo(){return info;}
    public String getBinary(){return binary;}
}