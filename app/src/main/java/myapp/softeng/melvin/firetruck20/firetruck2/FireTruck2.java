package myapp.softeng.melvin.firetruck20.firetruck2;

/**
 * Created by Melvin on 12/02/2016.
 * First to execute
 */

import myapp.softeng.melvin.firetruck20.framework.Screen;
import myapp.softeng.melvin.firetruck20.framework.impl.AndroidGame;

public class FireTruck2 extends AndroidGame
{
    public Screen getStartScreen()
    {
        return new LoadingScreen(this);
    }
}
