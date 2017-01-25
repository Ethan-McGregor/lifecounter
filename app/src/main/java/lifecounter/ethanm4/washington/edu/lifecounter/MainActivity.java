package lifecounter.ethanm4.washington.edu.lifecounter;

//Ethan McGregor
//1-25-17
//Life Counter for magic the gathering

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private int playerCount;
    private List<Player> allPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerCount = 4;
        if (savedInstanceState != null) {
            createPlayers(savedInstanceState.getIntegerArrayList("playerLives"));
            for (int i = 0; i < allPlayers.size(); i++) {
                changeLifeText(i, allPlayers.get(i));
            }
        } else {
            createPlayers(null);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        ArrayList<Integer> lives = new ArrayList<Integer>();
        for (Player each : allPlayers) {
            lives.add(each.getLives());
        }

        savedInstanceState.putIntegerArrayList("playerLives", lives);
    }

    //creates players from an existing instance or makes a new one.
    public void createPlayers(List<Integer> lives) {
        if(lives != null) {
            allPlayers = new ArrayList<Player>();
            for (int i = 0; i < lives.size(); i++) {
                allPlayers.add(new Player(lives.get(i), i + 1));
            }
            int count = 0;
            for(Player each: allPlayers){

                if (each.getLives() <= 0){
                    updateDead(count,each);
                }
                count ++;
            }
        }else{

            allPlayers = new ArrayList<Player>();

            for (int i = 0; i < playerCount; i++) {
                allPlayers.add(new Player(20, i + 1));
            }
        }
    }

    // waits for a click from one of the buttons controlling health increase or decrease
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sub5_0:
                updatePlayer(0, -5);
                break;
            case R.id.sub_0:
                updatePlayer(0, -1);
                break;
            case R.id.add_0:
                updatePlayer(0, 1);
                break;
            case R.id.add5_0:
                updatePlayer(0, 5);
                break;
            case R.id.sub5_1:
                updatePlayer(1, -5);
                break;
            case R.id.sub_1:
                updatePlayer(1, -1);
                break;
            case R.id.add_1:
                updatePlayer(1, 1);
                break;
            case R.id.add5_1:
                updatePlayer(1, 5);
                break;
            case R.id.sub5_2:
                updatePlayer(2, -5);
                break;
            case R.id.sub_2:
                updatePlayer(2, -1);
                break;
            case R.id.add_2:
                updatePlayer(2, 1);
                break;
            case R.id.add5_2:
                updatePlayer(2, 5);
                break;
            case R.id.sub5_3:
                updatePlayer(3, -5);
                break;
            case R.id.sub_3:
                updatePlayer(3, -1);
                break;
            case R.id.add_3:
                updatePlayer(3, 1);
                break;
            case R.id.add5_3:
                updatePlayer(3, 5);
                break;
            default:
                break;
        }
    }

    // changes text displaying players total life
    public void changeLifeText(int playerNumber, Player current) {
        TextView countTxt = null;
        switch (playerNumber) {
            case 0:
                countTxt = (TextView) findViewById(R.id.life_0);
                break;
            case 1:
                countTxt = (TextView) findViewById(R.id.life_1);
                break;
            case 2:
                countTxt = (TextView) findViewById(R.id.life_2);
                break;
            case 3:
                countTxt = (TextView) findViewById(R.id.life_3);
                break;
            default:
                break;
        }
        if (countTxt != null) {
            countTxt.setText ("Lives: " + current.getLives());
        }
    }

    //updates players life
    public void updatePlayer(int playerNumber, int lifeChange) {
        Player current = allPlayers.get(playerNumber);
        current.updateLives(lifeChange);
        if (current.getLives() >= 0) {
            if(current.isAlive()){
                changeLifeText(playerNumber, current);
            }
            if (allPlayers.get(playerNumber).getLives() <= 0) {
                updateDead(playerNumber,current);
                current.dead();
            }
        }

    }
    //if player dies, updates players name
    public void updateDead(int playerNumber,Player current){
        TextView dead = null;
        switch (playerNumber) {
            case 0:
                dead = (TextView) findViewById(R.id.playerName0);
                break;
            case 1:
                dead = (TextView) findViewById(R.id.playerName1);
                break;
            case 2:
                dead = (TextView) findViewById(R.id.playerName2);
                break;
            case 3:
                dead = (TextView) findViewById(R.id.playerName3);
                break;
            default:
                break;
        }
        dead.setText ("" + current.getName() + " IS DEAD");
    }


}
