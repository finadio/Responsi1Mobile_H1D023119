package com.unsoed.responsi1mobile_h1d023119.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsoed.responsi1mobile_h1d023119.data.model.Player
import com.unsoed.responsi1mobile_h1d023119.databinding.FragmentPlayerBinding
import com.unsoed.responsi1mobile_h1d023119.ui.adapter.PlayerAdapter

class PlayerFragment : Fragment() {
    
    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!
    
    private var players: ArrayList<Player> = arrayListOf()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        arguments?.let {
            players = it.getParcelableArrayList<Player>(ARG_PLAYERS) ?: arrayListOf()
        }
        
        setupRecyclerView()
    }
    
    private fun setupRecyclerView() {
        if (players.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
            binding.rvPlayers.visibility = View.GONE
        } else {
            binding.tvEmpty.visibility = View.GONE
            binding.rvPlayers.visibility = View.VISIBLE
            
            binding.rvPlayers.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = PlayerAdapter(players)
                setHasFixedSize(true)
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    
    companion object {
        private const val ARG_PLAYERS = "players"
        
        fun newInstance(players: List<Player>): PlayerFragment {
            return PlayerFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_PLAYERS, ArrayList(players))
                }
            }
        }
    }
}
