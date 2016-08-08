package myapp.softeng.melvin.firetruck20.framework.impl;

/**
 * Created by Melvin on 12/02/2016.
 */
import android.graphics.Bitmap;

import myapp.softeng.melvin.firetruck20.framework.Graphics.PixmapFormat;
import myapp.softeng.melvin.firetruck20.framework.Pixmap;

public class AndroidPixmap implements Pixmap {
    Bitmap bitmap;
    PixmapFormat format;

    public AndroidPixmap(Bitmap bitmap, PixmapFormat format) {
        this.bitmap = bitmap;
        this.format = format;
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getHeight() {
        return bitmap.getHeight();
    }

    public PixmapFormat getFormat() {
        return format;
    }

    public void dispose() {
        bitmap.recycle();
    }
}
