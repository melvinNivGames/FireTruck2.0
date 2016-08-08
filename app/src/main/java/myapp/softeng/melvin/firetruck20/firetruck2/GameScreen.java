package myapp.softeng.melvin.firetruck20.firetruck2;

/**
 * Created by Melvin on 06/03/2016.
 * This is where the game operates
 */

import java.util.List;
import java.util.Random;

import myapp.softeng.melvin.firetruck20.framework.Game;
import myapp.softeng.melvin.firetruck20.framework.Graphics;
import myapp.softeng.melvin.firetruck20.framework.Input.TouchEvent;
import myapp.softeng.melvin.firetruck20.framework.Screen;

public class GameScreen extends Screen {
    Graphics g = game.getGraphics();
    Random rdm = new Random();
    int increment = -400;
    int toIncrement = 5;
    int fireIncrement = -10;
    int fireIncrement2 = -10;
    int fireIncrement3 = -10;
    int lives = 3;
    int intScore = 0;
    String stringScore = "0";
    int timerForFire2 = 250;
    int timerForFire3 = 500;
    boolean fire2Spawn = false;
    boolean fire3Spawn = false;
    boolean isGiven = false;
    int x;
    int rdmX;
    int generateRoad = rdm.nextInt((5-1 + 1) + 1);
    int generateTips = rdm.nextInt((3-1 + 1) + 1);

    // determines the state of the game if it is ready, running, paused or game is over
    enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }

    // the screen of the game will set the state into ready
    GameState state = GameState.Ready;

    public GameScreen(Game game) {
        super(game);
        checkRoad();
        checkTips();
    }

    // method for determining the state of the game
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        if(state == GameState.Ready)
            updateReady(touchEvents);
        if(state == GameState.Running)
            updateRunning(touchEvents);
        if(state == GameState.Paused)
            updatePaused(touchEvents);
        if(state == GameState.GameOver)
            updateGameOver(touchEvents);
    }

    // calls the method that draws all the images needed on each state of the game
    public void present(float deltaTime) {
        if(state == GameState.Ready)
            drawReadyUI();
        if(state == GameState.Running)
            drawRunningUI();
        if(state == GameState.Paused)
            drawPausedUI();
        if(state == GameState.GameOver)
            drawGameOverUI();
    }

    // ready state; tap interaction
    private void updateReady(List<TouchEvent> touchEvents) {
        if (touchEvents.size() > 0)
            state = GameState.Running;
    }

    // running state; tap interaction
    private void updateRunning(List<TouchEvent> touchEvents)
    {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++)
        {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                //Paused button
                if (event.x < 50 && event.y < 50)
                {
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    state = GameState.Paused;
                    return;
                }

                //Responsible for 1st lane of fire (Left Lane)
                if (inBounds(event, 0, fireIncrement - 40, 50, 80)) {
                    fireIncrement = randomX();
                    intScore = intScore + 10;
                    if (Settings.soundEnabled)
                        Assets.splash.play(1);
                    return;
                }

                //Responsible for 2nd lane of fire (Right Lane)
                if (inBounds(event, 250, fireIncrement2 - 40, 50, 80)) {
                    fireIncrement2 = randomX();
                    intScore = intScore + 10;
                    if (Settings.soundEnabled)
                        Assets.splash.play(1);
                    return;
                }

                //Responsible for 3rd lane of fire (Middle Lane)
                if (inBounds(event, 125, fireIncrement3 - 40, 50, 80))
                {
                    fireIncrement3 = randomX();
                    intScore = intScore + 10;
                    if (Settings.soundEnabled)
                        Assets.splash.play(1);
                    return;
                }
            }
        }
    }

    // paused state; tap interaction
    private void updatePaused(List<TouchEvent> touchEvents)
    {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++)
        {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP)
            {
                if (inBounds(event, 120, 150, 80, 30))
                {
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    state = GameState.Running;
                    return;
                }
                if (inBounds(event, 120, 190, 80, 30))
                {
                    game.setScreen(new MainMenuScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    // game over state; tap interaction
    private void updateGameOver(List<TouchEvent> touchEvents)
    {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++)
        {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP)
            {
                if(inBounds(event, 120, 150, 80, 30) )
                {
                    game.setScreen(new GameScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                if(inBounds(event, 120, 190, 80, 30) )
                {
                    game.setScreen(new MainMenuScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    // method responsible for tapping the screen
    private boolean inBounds(TouchEvent event, int x, int y, int width, int height)
    {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    // draws images in ready state
    private void drawReadyUI()
    {
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.ready, 70, 100);
        g.drawPixmap(Assets.tips, 0, 440);
    }

    // draws images in running state
    private void drawRunningUI()
    {
        if (intScore == 200)
            toIncrement = 6;
        else if (intScore == 400)
            toIncrement = 7;
        else if (intScore == 600)
            toIncrement = 8;
        else if (intScore == 800)
            toIncrement = 9;
        else if (intScore == 1000)
            toIncrement = 10;

        if (isGiven == false)
        {
            if (intScore == 500)
            {
                lives = 3;
                isGiven = true;
            }
        }

        timerForFire2 = timerForFire2 - 1;
        timerForFire3 = timerForFire3 - 1;

        if (increment < -25)
        {
            g.drawPixmap(Assets.road, 0, increment);
            g.drawPixmap(Assets.fire, 10, fireIncrement);

            if (timerForFire2 == 0)
                fire2Spawn = true;
            if (fire2Spawn == true)
                g.drawPixmap(Assets.fire, 260, fireIncrement2);

            if (timerForFire3 == 0)
                fire3Spawn = true;
            if (fire3Spawn == true)
                g.drawPixmap(Assets.fire, 135, fireIncrement3);

            if (lives > 2)
                g.drawPixmap(Assets.life, 250, 5);
            if (lives > 1)
                g.drawPixmap(Assets.life, 270, 5);
            if (lives > 0)
                g.drawPixmap(Assets.life, 290, 5);

            g.drawPixmap(Assets.pause, 10, 5);
            g.drawPixmap(Assets.truck, 137, 395);

            try
            {
                Thread.sleep(10);
                increment = increment + toIncrement;
                if (fireIncrement < 460)
                {
                    fireIncrement = fireIncrement + toIncrement;
                }
                else if (fireIncrement >= 460)
                {
                    fireIncrement = randomX();
                    lives = lives - 1;
                    if (lives == 0)
                    {
                        Settings.addScore(intScore);
                        Settings.save(game.getFileIO());
                        if (Settings.soundEnabled)
                        {
                            Assets.gameover.play(1);
                        }
                        state = GameState.GameOver;
                    }
                }
                if (fire2Spawn == true)
                {
                    if (fireIncrement2 < 460)
                    {
                        fireIncrement2 = fireIncrement2 + toIncrement;
                    }
                    else if (fireIncrement2 >= 460)
                    {
                        fireIncrement2 = randomX();
                        lives = lives - 1;
                        if (lives == 0)
                        {
                            Settings.addScore(intScore);
                            Settings.save(game.getFileIO());
                            if (Settings.soundEnabled)
                            {
                                Assets.gameover.play(1);
                            }
                            state = GameState.GameOver;
                        }
                    }
                }
                if (fire3Spawn == true)
                {
                    if (fireIncrement3 < 460)
                    {
                        fireIncrement3 = fireIncrement3 + toIncrement;
                    }
                    else if (fireIncrement3 >= 460)
                    {
                        fireIncrement3 = randomX();
                        lives = lives - 1;
                        if (lives == 0)
                        {
                            Settings.addScore(intScore);
                            Settings.save(game.getFileIO());
                            if (Settings.soundEnabled)
                            {
                                Assets.gameover.play(1);
                            }
                            state = GameState.GameOver;
                        }
                    }
                }
            }
            catch (InterruptedException e)
            {
            }
        }

        else if (increment >= -25)
        {
            g.drawPixmap(Assets.road, 0, increment);
            g.drawPixmap(Assets.fire, 10, fireIncrement);

            if (timerForFire2 == 0)
                fire2Spawn = true;
            if (fire2Spawn == true)
                g.drawPixmap(Assets.fire, 260, fireIncrement2);

            if (timerForFire3 == 0)
                fire3Spawn = true;
            if (fire3Spawn == true)
                g.drawPixmap(Assets.fire, 135, fireIncrement3);

            if (lives > 2)
                g.drawPixmap(Assets.life, 250, 5);
            if (lives > 1)
                g.drawPixmap(Assets.life, 270, 5);
            if (lives > 0)
                g.drawPixmap(Assets.life, 290, 5);

            g.drawPixmap(Assets.pause, 10, 5);
            g.drawPixmap(Assets.truck, 137, 395);

            try
            {
                Thread.sleep(10);
                increment = -400;
            }
            catch (InterruptedException e)
            {
            }
        }

        stringScore = "" + intScore;
        drawText(g, stringScore, g.getWidth() / 2 - stringScore.length() * 20 / 2, 5);
    }

    // draws images in paused state
    private void drawPausedUI() {
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.pauselabel, 85, 80);
        g.drawPixmap(Assets.resume, 120, 150);
        g.drawPixmap(Assets.quit, 120, 190);
    }

    // draws images in game over state
    private void drawGameOverUI() {
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.gameOver, 85, 80);
        g.drawPixmap(Assets.retry, 120, 150);
        g.drawPixmap(Assets.quit, 120, 190);
    }

    // draws images for score
    public void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);

            if (character == ' ') {
                x += 20;
                continue;
            }

            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    // method for generating different roads
    public void checkRoad()
    {
        if(generateRoad == 1)
            Assets.road = g.newPixmap("roadgreen.png", Graphics.PixmapFormat.RGB565);
        if(generateRoad == 2)
            Assets.road = g.newPixmap("roadgrass.png", Graphics.PixmapFormat.RGB565);
        if(generateRoad == 3)
            Assets.road = g.newPixmap("roadsand.png", Graphics.PixmapFormat.RGB565);
        if(generateRoad == 4)
            Assets.road = g.newPixmap("roadmine.png", Graphics.PixmapFormat.RGB565);
        if(generateRoad == 5)
            Assets.road = g.newPixmap("roadsnow.png", Graphics.PixmapFormat.RGB565);
        return;
    }

    // method for generating tips in ready state
    public void checkTips()
    {
        if(generateTips == 1)
            Assets.tips = g.newPixmap("tips1.png", Graphics.PixmapFormat.RGB565);
        if(generateTips == 2)
            Assets.tips = g.newPixmap("tips2.png", Graphics.PixmapFormat.RGB565);
        if(generateTips == 3)
            Assets.tips = g.newPixmap("tips3.png", Graphics.PixmapFormat.RGB565);
        return;
    }

    // method that randoms the spawn time of fires
    public int randomX()
    {
        x = rdm.nextInt((5-1 + 1) + 1);

        if(x==1)
            rdmX = -200;
        else if(x==2)
            rdmX = -400;
        else if(x==3)
            rdmX = -600;
        else if(x==4)
            rdmX = -800;
        else if(x==5)
            rdmX = -1000;

        return rdmX;
    }

    public void pause() {
        if(state == GameState.Running)
            state = GameState.Paused;
    }

    public void resume() {
    }

    public void dispose() {
    }
}
