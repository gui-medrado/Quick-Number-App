package com.example.quicknumbers

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import com.example.quicknumbers.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_container) as? NavHostFragment
        navHostFragment?.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        with(binding) {
            mainTryButton.setOnClickListener {
                when(mainTryButton.text) {
                    getString(R.string.try_label) -> {
                        navController?.navigate(R.id.action_sortConfig_to_sortResult)
                        mainTryButton.apply {
                            text = getString(R.string.try_again)
                            setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_reload),null)
                        }
                    }
                    getString(R.string.try_again) -> {
                        navController?.popBackStack()
                        mainTryButton.apply {
                            text = getString(R.string.try_label)
                            setCompoundDrawablesWithIntrinsicBounds(null, null, AppCompatResources.getDrawable(this@MainActivity, R.drawable.ic_arrow),null)
                        }
                    }
                }

            }
        }
    }
}