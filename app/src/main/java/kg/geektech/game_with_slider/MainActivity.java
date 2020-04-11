package kg.geektech.game_with_slider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random mRandom;
    private SeekBar seekBar;
    private Button button;
    private int progress, randomNumber,score = 0;
    private TextView scoreTextView, progressTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRandom = new Random();
        seekBar = findViewById(R.id.seek_bar);
        button = findViewById(R.id.image_button);
        scoreTextView = findViewById(R.id.score);
        progressTextView = findViewById(R.id.number);
        randomNumber = getRandomNumber();
        progress = seekBar.getProgress();
        setProgress();
    }

    private void setProgress() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressTextView.setText(String.valueOf(progress));
                Log.d("lol", " срабатывает при перетаскивании ползунка по шкале. \n" +
                        "Передаваемый в метод параметр progress позволяет получить новое значение ползунка,\n"
                        + "которое в данном случае передается в TextView для отображения на экране");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("lol", "срабатывает при начале перетаскивания ползунка по шкале");


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("lol", "срабатывает при завершении перетаскивания ползунка по шкале");

            }
        });
    }

    private void setScore(int points) {
        score +=points;

        Log.d("TAG", "setScore: " + score);
        scoreTextView.setText("Score:" + score);

    }

//    int sign(int i) {
//        if (i == 0) return 0;
//        if (i >> 31 != 0) return -1;
//        return +1;
//    }

    private Integer getRandomNumber() {
        return mRandom.nextInt(100) + 1;
    }

    public void onClick(View view) {
        startGame();
    }

    private void startGame() {
        randomNumber = getRandomNumber();
        progress = seekBar.getProgress();
        int difference = Math.abs(progress - randomNumber);
        if (difference == 0){
            setScore(100);
            Toast.makeText(this,"Unbelievable!!! random number was: and you re getting bonus of " + randomNumber,Toast.LENGTH_LONG).show() ;
        }else if (difference < 10){
            setScore(50);
            Toast.makeText(this,"Great!!! random number  was and you re getting bonus of " + randomNumber,Toast.LENGTH_LONG).show();
        }else if (difference > 10 && difference < 20 ){
            setScore(50);
            Toast.makeText(this,"Not Bad!!! random number was and you re getting bonus of "+ randomNumber,Toast.LENGTH_LONG).show();
        }else if (difference > 30){
            setScore(-50);
            Toast.makeText(this,"You lose!!! random number was : " + randomNumber,Toast.LENGTH_LONG).show();
        }

    }

}




