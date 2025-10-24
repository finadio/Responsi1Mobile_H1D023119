package com.unsoed.responsi1mobile_h1d023119

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.unsoed.responsi1mobile_h1d023119.data.api.RetrofitClient
import com.unsoed.responsi1mobile_h1d023119.databinding.ActivityClubHistoryBinding

class ClubHistoryActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityClubHistoryBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClubHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Club History"
        
        loadClubHistory()
    }
    
    private fun loadClubHistory() {
        binding.progressBar.visibility = View.VISIBLE
        
        val call = RetrofitClient.apiService.getTeam(356)
        
        call.enqueue(object : retrofit2.Callback<com.unsoed.responsi1mobile_h1d023119.data.model.TeamResponse> {
            override fun onResponse(
                call: retrofit2.Call<com.unsoed.responsi1mobile_h1d023119.data.model.TeamResponse>,
                response: retrofit2.Response<com.unsoed.responsi1mobile_h1d023119.data.model.TeamResponse>
            ) {
                binding.progressBar.visibility = View.GONE
                
                if (response.isSuccessful && response.body() != null) {
                    val team = response.body()!!
                    
                    binding.ivHistoryImage.setImageResource(R.drawable.historylogo)
                    
                    binding.tvClubName.text = team.name
                    
                    val historyContent = """
                        SHEFFIELD UNITED FOOTBALL CLUB
                        
                        Sheffield United Football Club is a professional football club in Sheffield, South Yorkshire, England, which competes in the EFL Championship, the second tier of English football.
                        
                        FORMATION AND EARLY YEARS
                        The club was formed in 1889, as an offshoot of Sheffield United Cricket Club, and are nicknamed "The Blades" due to Sheffield's status as the world's foremost steel producer and exporter. The club played their first competitive match in October 1889, in the FA Cup.
                        
                        GOLDEN ERA
                        Sheffield United won the League Championship once in 1898, the FA Cup four times (1899, 1902, 1915, 1925), and enjoyed their most successful period under managers John Nicholson and Reg Freeman. They were FA Cup runners-up in 1899, 1901, 1925, and 2008.
                        
                        THE STEEL CITY DERBY
                        Sheffield United's traditional rivals are Sheffield Wednesday, with whom they contest the Steel City derby - one of the oldest and most fierce rivalries in English football. The two clubs are separated by only a few miles in the city of Sheffield.
                        
                        HOME GROUND
                        The club plays their home games at Bramall Lane, which has been their home since formation. Bramall Lane is the oldest major stadium in the world still hosting professional football matches, and has been Sheffield United's home since 1889.
                        
                        CLUB COLORS AND IDENTITY
                        The club's traditional home colours are red and white striped shirts with black shorts and socks. The red and white stripes have been a consistent feature throughout the club's history, making them instantly recognizable.
                        
                        MODERN ERA
                        After experiencing ups and downs through various divisions, Sheffield United has demonstrated resilience and determination. The club has a passionate fanbase and continues to compete at the highest levels of English football.
                        
                        CLUB INFORMATION
                        Founded: ${team.founded}
                        Home Venue: ${team.venue}
                        Address: ${team.address}
                        Club Colors: ${team.clubColors}
                        Website: ${team.website}
                        
                        Sheffield United remains an integral part of English football history and continues to write new chapters in their storied legacy.
                    """.trimIndent()
                    
                    binding.tvHistoryContent.text = historyContent
                    
                } else {
                    showError("Failed to load club history: ${response.code()}")
                }
            }
            
            override fun onFailure(
                call: retrofit2.Call<com.unsoed.responsi1mobile_h1d023119.data.model.TeamResponse>,
                t: Throwable
            ) {
                binding.progressBar.visibility = View.GONE
                showError("Error: ${t.message}")
            }
        })
    }
    
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
