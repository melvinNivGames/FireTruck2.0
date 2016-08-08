package myapp.softeng.melvin.firetruck20.framework;

/**
 * Created by Melvin on 10/02/2016.
 */

import myapp.softeng.melvin.firetruck20.framework.Graphics.PixmapFormat;

public interface Pixmap
{
        public int getWidth();

        public int getHeight();

        public PixmapFormat getFormat();

        public void dispose();
}
