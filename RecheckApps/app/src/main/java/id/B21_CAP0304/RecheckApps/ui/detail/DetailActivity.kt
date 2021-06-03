package id.B21_CAP0304.RecheckApps.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.B21_CAP0304.RecheckApps.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}