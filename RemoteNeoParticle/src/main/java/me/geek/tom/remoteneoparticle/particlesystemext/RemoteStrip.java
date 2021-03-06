package me.geek.tom.remoteneoparticle.particlesystemext;

import com.github.mbelling.ws281x.Color;
import me.geek.tom.neopixelremoteserver.particlesystem.strip.BufferStrip;
import me.geek.tom.neopixelremoteserver.particlesystem.strip.IStrip;
import me.geek.tom.remoteneoparticle.networking.ClientNetworkingManager;
import me.geek.tom.remoteneoparticle.networking.packets.StripSetColourPacket;
import me.geek.tom.remoteneoparticle.networking.packets.StripShowPacket;

public class RemoteStrip implements IStrip {

    private int length;
    // private final int id;
    private BufferStrip bufferStrip;

    public RemoteStrip(int length) {
        this.length = length;
        // this.id = id;
        this.bufferStrip = new BufferStrip(this);
    }

    @Override
    public void setPixel(int pixel, Color color) {
        this.bufferStrip.setPixel(pixel, color);
    }

    @Override
    public void setPixel(int pixel, int r, int g, int b) {
        this.bufferStrip.setPixel(pixel, r, g, b);
    }

    @Override
    public void show() {
        System.out.println("show");
        for (int i = 0; i < this.length; i++) {
            ClientNetworkingManager.getInstance().sendStripPacket(new StripSetColourPacket(i, this.bufferStrip.getPixel(i)));
            System.out.println("setting pixel: " + i);
        }
        System.out.println("Send show packet");
        ClientNetworkingManager.getInstance().sendStripPacket(new StripShowPacket());
    }

    @Override
    public int getLength() {
        return this.length;
    }
}
