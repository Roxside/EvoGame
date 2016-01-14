/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evospacegame;

import evospacegame.InputHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 * Main class for the game
 */
public class GameTutoria extends JFrame {

    public int[] map1x =new int[]{50,        250,       450,        150,        350,        600,        0,        550,       700,        800,        150,        300,       450,        650,        800,        0,        100, 250,        400,        550,        900,        0,        150,        300,        500,        600,        750,        900,        100,        200,        300,        400,        500,        600,        700,        850,        50,        150,        300,        450,        550,        700,        900,        50,        150,        300,        450,        550,        650,        800,        900,};
    public int[] map1y =new int[]{75,        150,        150,        225,        225,        225,        300,        300,        300,        300,        375,        375,        375,        375,        375,        450,        450,        450,        450,        450,        450,        525,        525,        525,        525,        525,        525,        525,        600,       600,        600,        600,        600,        600,        600,        600,        675,        675,        675,        675,        675,        675,        675,        750,        750,        750,        750,        750,        750,        750,        750,};
     public int[] map2x =new int[]{50,        250,       450,        150,        350,        600,        0,        550,       700,        800,        150,        300,       450,        650,        800,        0,        100, 250,        400,        550,        900,        0,        150,        300,        500,        600,        750,        900,        100,        200,        300,        350,        500,        600,        700,        850,        50,        150,        300,        450,        550,        700,        900,        50,        150,        300,        450,        550,        650,        800,        900,};
    public int[] map2y =new int[]{75,        150,        150,        225,        225,        225,        300,        300,        300,        300,        375,        375,        375,        375,        375,        450,        450,        450,        450,        450,        450,        525,        525,        525,        525,        525,        525,        525,        600,       600,        600,        600,        600,        600,        600,        600,        675,        675,        675,        675,        675,        675,        675,        750,        750,        750,        750,        750,        750,        750,        750,};
    
    public int[] blocksx ;
    public int[] blocksy ;
    
    public int[] arwidth  = new int[]{	50	,47	,44	,41	,38	,35	,32	,29	,26	,23	,20};
    public int[] arHeight = new int[]{	50	,47	,44	,41	,38	,35	,32	,29	,26	,23	,20};
    public int[] arDetect = new int[]{	10	,15	,20	,25	,30	,35	,40	,45	,50	,55	,60};
    public int[] arSpeed  = new int[]{	1	,2	,3	,4	,5	,6	,7	,8	,9	,10	,11};
     public int[] arLevel  = new int[]{	50	,45	,40	,35	,30	,25	,20	,15	,10	,5	,0 };
    long time =0;
    boolean isRunning = true;
    int fps = 30;
    int windowWidth = 1100;
    int windowHeight = 600;
    int pWidth =0;
    int pHeight=0;
    int pDetect=0;
    int pLevel=0;
    int pSpeed=0;
    
    BufferedImage backBuffer;
    Insets insets;
    InputHandler input;
    long sTime=0;
    int x = 5;
    int blockY = 0;

    public static void main(String[] args) {
        GameTutoria game = new GameTutoria();
        //Width= 3,Height=4 , DetectRange= 5,Speed= 9, Level =10
        
       game.initialize(3, 4,5, 9, 0);
        game.run();
        game.dispose();
        //System.exit(0);
    }

    /**
     * This method starts the game and runs it in a loop
     */
    public double  run() {
        
        sTime = System.currentTimeMillis();

        while (isRunning) {
            long time = System.currentTimeMillis();

            update();
            draw();

            //  delay for each frame  -   time it took for one frame 
            time = (1000 / fps) - (System.currentTimeMillis() - time);

            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (Exception e) {
                }
            }
        }
        double retval = (double) time;
        return retval;

//        setVisible(false);
    }

    /**
     * This method will set up everything need for the game to run
     */
    void initialize(int w,int h,int d,int s,int l) {
        setTitle("Game Tutorial");
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        //Width= 3,Height=4 , DetectRange= 5,Speed= 9, Level =10
        pWidth=arwidth[w];
        pHeight=arHeight[h];
        pDetect=arDetect[d];
        pSpeed=arSpeed[s];
        pLevel=arLevel[l];
        blocksx=map2x;
        blocksy=map1y;
//        pWidth=20;
//        pHeight=20;
//        pDetect=50;
//        pSpeed=11;
//        pLevel=50;
        x=500;
        insets = getInsets();
        setSize(insets.left + windowWidth + insets.right,
                insets.top + windowHeight + insets.bottom);

        backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
        input = new InputHandler(this);
    }

    /**
     * This method will check for input, move things around and check for win
     * conditions, etc
     */
    
    void gameOver(){
        isRunning=false;
        time= (System.currentTimeMillis()-sTime);
        
       // System.out.print ("Fitness "+time+" ms ");
       // System.out.println(" Attributes Width= "+pWidth+",Height="+pHeight+" , DetectRange= "+pDetect+",Speed= "+pSpeed+", Level ="+pLevel+"");
            
    }
    
    void update() {

        blockY += 5;
        //collision detection
        for (int i = 0; i < blocksx.length; i++) {
            int curY =blockY-blocksy[i];
            if ((curY<570-pLevel &&curY>570-pHeight-pLevel ) || (curY+50<570-pLevel &&curY+50>570-pHeight-pLevel )) {
                int curX=blocksx[i];
                if((x>=curX-1 && x<curX+50)||(x+pWidth>=curX+1 && x+pWidth<curX+50)){
                    //System.out.println("bitti blok"+(i+1));
                    gameOver();
                    break;
                }
            }
        }
        ///Auto detect
        for (int i = 0; i < blocksx.length; i++) {
            int curY =blockY-blocksy[i];
            if  (curY+50<570-pHeight-pLevel &&curY+50>=570-pHeight-pDetect-pLevel ) {
                int curX=blocksx[i];
                if((x>=curX && x<curX+50)||(x+pWidth>curX && x+pWidth<curX+50)){
                
                if(x+(pWidth/2) >= blocksx[i]+25  )
                x+=pSpeed;
                else
                x-=pSpeed;
            }    
                }
                
        }
        //finish
        if(blockY - 800>570-pHeight-pLevel){
            gameOver();
            
        }
        
        //manuel controller
//        if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
//            if (x < 950) {
//                x += 5;
//            }
//
//        }
//        if (input.isKeyDown(KeyEvent.VK_LEFT)) {
//            if (x > 5) {
//                x -= 5;
//            }
//
//        }
        //

    }

    /**
     * This method will draw everything
     */
    void draw() {

        Graphics g = getGraphics();

        Graphics bbg = backBuffer.getGraphics();

        bbg.setColor(Color.WHITE);
        bbg.fillRect(0, 0, windowWidth, windowHeight);

        bbg.setColor(Color.RED);
        for (int i = 0; i < blocksx.length; i++) {
            bbg.fillRect(blocksx[i], blockY - blocksy[i], 50, 50);

        }
        bbg.fillRect(0, blockY - 800, 970, 10);
        bbg.setColor(Color.BLACK);

        bbg.fillRect(x, 570-pHeight-pLevel, pWidth, pHeight);//playa
        bbg.drawString("width:"+pWidth, 1000, 100);//width
        bbg.drawString("height:"+pHeight, 1000, 115);//height
        bbg.drawString("speed:"+pSpeed, 1000, 130);//speed
        bbg.drawString("Fitness:"+(System.currentTimeMillis() - sTime), 1000, 145);//fitness
        bbg.drawRect(5, 0, 950, 570); //cerceve
        bbg.drawLine(0, 570-pLevel, 970,  570-pLevel);//level
        bbg.drawString("Level:"+pLevel, 1000,570-pLevel);//level
        
        bbg.drawLine(0, 570-pLevel-pHeight-pDetect, 970,  570-pLevel-pHeight-pDetect);//detect
        bbg.drawString("Detection:"+pDetect, 1000,570-pLevel-pHeight-pDetect);//detect
        g.drawImage(backBuffer, insets.left, insets.top, this);

    }
}
