package me.geek.tom.remoteneoparticle.server;

import com.github.mbelling.ws281x.Color;
import me.geek.tom.neopixelremoteserver.particlesystem.strip.BufferStrip;
import me.geek.tom.neopixelremoteserver.particlesystem.strip.IStrip;
import me.geek.tom.remoteneoparticle.networking.ServerNetworkingManager;
import me.geek.tom.remoteneoparticle.networking.packets.StripSetColourPacket;
import me.geek.tom.remoteneoparticle.networking.packets.StripShowPacket;

import java.util.logging.Logger;

@SuppressWarnings("WeakerAccess")
public class ConnectedStrip implements IStrip {

    private BufferStrip buf;
    private static Logger LOGGER = Logger.getLogger(ConnectedStrip.class.getName());

    public ConnectedStrip(IStrip dest) {
        this.buf = new BufferStrip(dest);
        ServerNetworkingManager.getInstance().addHandler((packet) -> {
            if (packet instanceof StripSetColourPacket) {
                StripSetColourPacket p = (StripSetColourPacket) packet;
                LOGGER.info("set pixel " + p.getPos());
                this.setPixel(p.getPos(), p.getR(), p.getG(), p.getB());
            } else if (packet instanceof StripShowPacket) {
                // StripShowPacket p = (StripShowPacket) packet;
                LOGGER.info("show strip!");
                this.show();
            }
        });
    }

    @Override
    public void setPixel(int pixel, Color color) {
        this.buf.setPixel(pixel, color);
    }

    @Override
    public void setPixel(int pixel, int r, int g, int b) {
        this.buf.setPixel(pixel, r, g, b);
    }

    @Override
    public void show() {
        this.buf.show();
    }

    @Override
    public int getLength() {
        return this.buf.getLength();
    }
}
