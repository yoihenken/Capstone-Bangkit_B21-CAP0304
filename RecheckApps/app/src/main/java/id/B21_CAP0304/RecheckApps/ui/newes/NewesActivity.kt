package id.B21_CAP0304.RecheckApps.ui.newes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.B21_CAP0304.RecheckApps.databinding.ActivityNewesBinding

class NewesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.appBar.setNavigationOnClickListener {
            finish()
        }
    }
}