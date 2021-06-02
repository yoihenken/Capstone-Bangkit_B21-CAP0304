package id.B21_CAP0304.RecheckApps.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.B21_CAP0304.RecheckApps.R
import id.B21_CAP0304.RecheckApps.databinding.ActivityMainBinding
import id.B21_CAP0304.RecheckApps.ui.newes.NewesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNew.setOnClickListener {
            startActivity(Intent(this, NewesActivity::class.java))
        }

    }
}