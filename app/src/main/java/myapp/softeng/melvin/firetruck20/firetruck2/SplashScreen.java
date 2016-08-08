package myapp.softeng.melvin.firetruck20.firetruck2;

/**
 * Created by Melvin on 06/03/2016.
 * SPLASH SCREEN!
 */

import myapp.softeng.melvin.firetruck20.framework.Game;
import myapp.softeng.melvin.firetruck20.framework.Graphics;
import myapp.softeng.melvin.firetruck20.framework.Screen;

public class SplashScreen extends Screen {
    public SplashScreen (Game game)
    {
        super(game);
    }

    public void update(float deltaTime) {
        intro();
        game.setScreen(new MainMenuScreen(game));
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.splashimage, 0, 0);
    }

    public void intro(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}
