package myapp.softeng.melvin.firetruck20.firetruck2;

/**
 * Created by Melvin on 12/02/2016.
 * This is where all resources will be loaded into the app
 */

import myapp.softeng.melvin.firetruck20.framework.Game;
import myapp.softeng.melvin.firetruck20.framework.Graphics;
import myapp.softeng.melvin.firetruck20.framework.Screen;
import myapp.softeng.melvin.firetruck20.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen
{
    public LoadingScreen(Game game)
    {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.mainMenuBackground = g.newPixmap("mainmenubackground.png", PixmapFormat.RGB565);
        Assets.splashimage = g.newPixmap("splashimage.png", PixmapFormat.RGB565);
        Assets.road = g.newPixmap("roadgreen.png", PixmapFormat.RGB565);
        Assets.tips = g.newPixmap("tips1.png", PixmapFormat.RGB565);
        Assets.mainMenu = g.newPixmap("mainmenu.png", PixmapFormat.ARGB4444);
        Assets.about = g.newPixmap("about.png", PixmapFormat.ARGB4444);
        Assets.soundOn = g.newPixmap("soundon.png", PixmapFormat.ARGB4444);
        Assets.soundOff = g.newPixmap("soundoff.png", PixmapFormat.ARGB4444);
        Assets.helpscreen1 = g.newPixmap("helpscreen1.png", PixmapFormat.ARGB4444);
        Assets.helpscreen2 = g.newPixmap("helpscreen2.png", PixmapFormat.ARGB4444);
        Assets.helpscreen3 = g.newPixmap("helpscreen3.png", PixmapFormat.ARGB4444);
        Assets.helpscreen4 = g.newPixmap("helpscreen4.png", PixmapFormat.ARGB4444);
        Assets.exit = g.newPixmap("exit.png", PixmapFormat.ARGB4444);
        Assets.confirm = g.newPixmap("confirm.png", PixmapFormat.ARGB4444);
        Assets.pauselabel = g.newPixmap("pauselabel.png", PixmapFormat.ARGB4444);
        Assets.background = g.newPixmap("background.png", PixmapFormat.ARGB4444);
        Assets.numbers = g.newPixmap("numbers.png", PixmapFormat.ARGB4444);
        Assets.highscore = g.newPixmap("highscore.png", PixmapFormat.ARGB4444);
        Assets.pause = g.newPixmap("pause.png", PixmapFormat.ARGB4444);
        Assets.fire = g.newPixmap("fire.png", PixmapFormat.ARGB4444);
        Assets.next = g.newPixmap("next.png", PixmapFormat.ARGB4444);
        Assets.back = g.newPixmap("back.png", PixmapFormat.ARGB4444);
        Assets.no = g.newPixmap("no.png", PixmapFormat.ARGB4444);
        Assets.yes = g.newPixmap("yes.png", PixmapFormat.ARGB4444);
        Assets.resume = g.newPixmap("resume.png", PixmapFormat.ARGB4444);
        Assets.iteam = g.newPixmap("iteam.png", PixmapFormat.ARGB4444);
        Assets.version = g.newPixmap("version.png", PixmapFormat.ARGB4444);
        Assets.quit = g.newPixmap("quit.png", PixmapFormat.ARGB4444);
        Assets.truck = g.newPixmap("truck.png", PixmapFormat.ARGB4444);
        Assets.life = g.newPixmap("life.png", PixmapFormat.ARGB4444);
        Assets.click = game.getAudio().newSound("click.mp3");
        Assets.splash = game.getAudio().newSound("splash.mp3");
        Assets.gameover = game.getAudio().newSound("gameover.mp3");
        Assets.ready = g.newPixmap("ready.png", PixmapFormat.ARGB4444);
        Assets.gameOver = g.newPixmap("gameover.png", PixmapFormat.ARGB4444);
        Assets.retry = g.newPixmap("retry.png", PixmapFormat.ARGB4444);

        Settings.load(game.getFileIO());
        game.setScreen(new SplashScreen(game));
    }

    public void present(float deltaTime) {
    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}
