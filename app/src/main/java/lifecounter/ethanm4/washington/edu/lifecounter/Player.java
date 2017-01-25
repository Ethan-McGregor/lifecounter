package lifecounter.ethanm4.washington.edu.lifecounter;

/**
 * Created by Ethan on 1/24/217.
 */

public class Player {
    private String name;
    private int lives;
    private boolean isAlive;

    public Player(int lives, int num) {
        this.lives = lives;
        this.name = "Player " + num;
        this.isAlive = true;
    }

    public String getName() {

        return name;
    }

    public boolean isAlive() {

        return isAlive;
    }

    public void dead() {
        isAlive = false;
        lives = 0;
    }

    public void updateLives(int n) {
        lives += n;
        if (lives < 0) {
            lives = 0;
        }
    }

    public int getLives() {
        return lives;
    }
}
