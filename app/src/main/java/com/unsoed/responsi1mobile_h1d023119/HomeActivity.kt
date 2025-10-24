package com.unsoed.responsi1mobile_h1d023119

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unsoed.responsi1mobile_h1d023119.data.api.RetrofitClient
import com.unsoed.responsi1mobile_h1d023119.data.model.TeamResponse
import com.unsoed.responsi1mobile_h1d023119.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityHomeBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.ivClubLogo.visibility = View.INVISIBLE
        binding.tvClubName.visibility = View.INVISIBLE
        binding.tvClubDescription.visibility = View.INVISIBLE
        
        loadTeamData()
        
        binding.layoutHistory.setOnClickListener {
            startActivity(Intent(this, ClubHistoryActivity::class.java))
        }
        
        binding.layoutCoach.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("SHOW_COACH", true)
            startActivity(intent)
        }
        
        binding.layoutSquad.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("SHOW_PLAYERS", true)
            startActivity(intent)
        }
    }
    
    private fun loadTeamData() {
        val call = RetrofitClient.apiService.getTeam(356)
        
        call.enqueue(object : Callback<TeamResponse> {
            override fun onResponse(call: Call<TeamResponse>, response: Response<TeamResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val team = response.body()!!
                    displayTeamData(team)
                }
            }
            
            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    
    private fun displayTeamData(team: TeamResponse) {
        binding.tvClubName.text = "356. ${team.name}"
        
        binding.tvClubDescription.text = """
            Sheffield United Football Club is a professional football club in Sheffield, South Yorkshire, England, which competes in the EFL Championship. 
            
            They formed in 1889, as an offshoot of Sheffield United Cricket Club, and are nicknamed The Blades due to Sheffield's status as the world's foremost steel producer and exporter. The club plays at Bramall Lane, which has been their home since formation.
        """.trimIndent()
        
        Glide.with(this)
            .load(team.crest)
            .placeholder(R.drawable.logoclub)
            .error(R.drawable.logoclub)
            .into(binding.ivClubLogo)
        
        binding.ivClubLogo.visibility = View.VISIBLE
        binding.tvClubName.visibility = View.VISIBLE
        binding.tvClubDescription.visibility = View.VISIBLE
    }
}
