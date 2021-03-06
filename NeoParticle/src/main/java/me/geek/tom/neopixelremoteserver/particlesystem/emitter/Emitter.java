package me.geek.tom.neopixelremoteserver.particlesystem.emitter;

import me.geek.tom.neopixelremoteserver.particlesystem.particle.Particle;
import me.geek.tom.neopixelremoteserver.particlesystem.particle.ParticleManager;

import java.util.Random;

@SuppressWarnings("unused")
public class Emitter {

    private long start_time;
    private int age;

    private final Class<? extends Particle> particle;
    private final int probability;
    private final Random random = new Random();

    public Class<? extends Particle> getParticle() {
        return particle;
    }

    public Emitter(Class<? extends Particle> particle, int probability) {
        this.particle = particle;
        this.probability = probability;
        this.start_time = System.nanoTime() / 1000000;
        this.age = 0;
    }


    public void tick(ParticleManager manager) {

        this.age += (System.nanoTime() / 1000000) - start_time;

        if (random.nextInt(1001) <= probability && this.age >= 1000 / 20) {
            this.spawnParticle(manager);
            this.age = 0;
            this.start_time = System.nanoTime() / 1000000;
        }
    }

    private void spawnParticle(ParticleManager manager) {
        try {
            Particle newParticle = particle.newInstance();
            newParticle.setPos(random.nextInt(manager.getLength()));
            manager.spawnParticle(newParticle);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
