package com.unsoed.responsi1mobile_h1d023119

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.unsoed.responsi1mobile_h1d023119.data.api.RetrofitClient
import com.unsoed.responsi1mobile_h1d023119.data.model.TeamResponse
import com.unsoed.responsi1mobile_h1d023119.databinding.ActivityMainBinding
import com.unsoed.responsi1mobile_h1d023119.ui.fragment.CoachFragment
import com.unsoed.responsi1mobile_h1d023119.ui.fragment.PlayerFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        if (savedInstanceState == null) {
            val showPlayers = intent.getBooleanExtra("SHOW_PLAYERS", false)
            val showCoach = intent.getBooleanExtra("SHOW_COACH", true)
            
            if (showPlayers) {
                supportActionBar?.title = "Team Squad"
                loadTeamDataForPlayers()
            } else if (showCoach) {
                supportActionBar?.title = "Head Coach"
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, CoachFragment.newInstance())
                    .commit()
            }
        }
    }
    
    private fun loadTeamDataForPlayers() {
        val call = RetrofitClient.apiService.getTeam(356)
        
        call.enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val team = response.body()!!
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, PlayerFragment.newInstance(team.squad))
                        .commit()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load team data: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }
            
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}