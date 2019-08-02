package me.geek.tom.NeoPixelRemoteServer.particle.strip;

import com.github.mbelling.ws281x.Color;
import com.github.mbelling.ws281x.Ws281xLedStrip;

public class HWStrip implements IStrip {

    public int getLength() {
        return length;
    }

    private int length;
    private Ws281xLedStrip strip;

    public HWStrip(Ws281xLedStrip strip) {
        this.strip = strip;
        this.length = strip.getLedsCount();
    }

    public void setPixel(int pixel, Color color) {
        this.strip.setPixel(pixel, color);
    }

    public void setPixel(int pixel, int r, int g, int b) {
        this.strip.setPixel(pixel, r, g, b);
    }

    public void show() {
        this.strip.render();
    }
}
