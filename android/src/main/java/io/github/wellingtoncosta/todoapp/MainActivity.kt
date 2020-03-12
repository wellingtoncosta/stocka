package io.github.wellingtoncosta.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.wellingtoncosta.todoapp.databinding.ActivityMainBinding
import io.github.wellingtoncosta.todoapp.shared.createGreeting

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.platformLabel.text = createGreeting()

        setContentView(binding.root)
    }

}
