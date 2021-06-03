package id.B21_CAP0304.RecheckApps.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.B21_CAP0304.RecheckApps.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}