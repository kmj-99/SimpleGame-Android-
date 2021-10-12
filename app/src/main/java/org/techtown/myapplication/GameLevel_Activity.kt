package org.techtown.myapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.myapplication.databinding.ActivityGameStartLevelBinding

class GameLevel_Activity : AppCompatActivity() {
    private lateinit var binding :ActivityGameStartLevelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityGameStartLevelBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var Start_Intent=Intent(this,MainActivity::class.java)

        binding.EasyButton.setOnClickListener {
            Start_Intent.putExtra("Level",1)
            startActivity(Start_Intent)
            finish()

        }

        binding.NormalButton.setOnClickListener {
            Start_Intent.putExtra("Level",2)
            startActivity(Start_Intent)
            finish()
        }

        binding.HardButton.setOnClickListener {
            Start_Intent.putExtra("Level",3)
            startActivity(Start_Intent)

            finish()
        }



    }

    override fun onStop() {
        super.onStop()

    }

}