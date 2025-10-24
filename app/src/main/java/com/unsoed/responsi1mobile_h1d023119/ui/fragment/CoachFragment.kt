package com.unsoed.responsi1mobile_h1d023119.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.unsoed.responsi1mobile_h1d023119.R
import com.unsoed.responsi1mobile_h1d023119.data.api.RetrofitClient
import com.unsoed.responsi1mobile_h1d023119.data.model.TeamResponse
import com.unsoed.responsi1mobile_h1d023119.databinding.FragmentCoachBinding

class CoachFragment : Fragment() {
    
    private var _binding: FragmentCoachBinding? = null
    private val binding get() = _binding!!
    
    private var teamData: TeamResponse? = null
    private var autoNavigateToPlayers = false
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoachBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        autoNavigateToPlayers = arguments?.getBoolean(ARG_AUTO_NAVIGATE, false) ?: false
        
        loadTeamData()
        
        binding.btnShowPlayers.setOnClickListener {
            navigateToPlayers()
        }
    }
    
    private fun navigateToPlayers() {
        teamData?.let { team ->
            val fragment = PlayerFragment.newInstance(team.squad)
            val transaction = parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
            
            if (!autoNavigateToPlayers) {
                transaction.addToBackStack(null)
            }
            
            transaction.commit()
        }
    }
    
    private fun loadTeamData() {
        binding.progressBar.visibility = View.VISIBLE
        
        val call = RetrofitClient.apiService.getTeam(356)
        
        call.enqueue(object : retrofit2.Callback<TeamResponse> {
            override fun onResponse(
                call: retrofit2.Call<TeamResponse>,
                response: retrofit2.Response<TeamResponse>
            ) {
                binding.progressBar.visibility = View.GONE
                
                if (response.isSuccessful && response.body() != null) {
                    teamData = response.body()
                    displayTeamData(teamData!!)
                    
                    // Auto navigate to players if requested
                    if (autoNavigateToPlayers) {
                        navigateToPlayers()
                    }
                } else {
                    showError("Failed to load team data: ${response.code()}")
                }
            }
            
            override fun onFailure(call: retrofit2.Call<TeamResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                showError("Error: ${t.message}")
            }
        })
    }
    
    private fun displayTeamData(team: TeamResponse) {
        with(binding) {
            ivClubLogo.setImageResource(R.drawable.coach)
            
            team.coach?.let { coach ->
                tvCoachName.text = coach.name ?: "${coach.firstName ?: ""} ${coach.lastName ?: ""}".trim()
                tvCoachDob.text = coach.dateOfBirth ?: "N/A"
                tvCoachNationality.text = coach.nationality ?: "N/A"
            }
        }
    }
    
    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object {
        private const val ARG_AUTO_NAVIGATE = "auto_navigate"
        
        fun newInstance(autoNavigateToPlayers: Boolean = false) = CoachFragment().apply {
            arguments = Bundle().apply {
                putBoolean(ARG_AUTO_NAVIGATE, autoNavigateToPlayers)
            }
        }
    }
}
