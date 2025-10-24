package com.unsoed.responsi1mobile_h1d023119.ui.adapter

import android.view.LayoutInflater
import android.graphics.Color
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.unsoed.responsi1mobile_h1d023119.R
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobile_h1d023119.data.model.Player
import com.unsoed.responsi1mobile_h1d023119.databinding.ItemPlayerBinding

class PlayerAdapter(private val players: List<Player>) : 
    RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }
    
    override fun getItemCount(): Int = players.size
    
    inner class PlayerViewHolder(
        private val binding: ItemPlayerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            val context = binding.root.context

            binding.tvPlayerName.text = player.name
            binding.tvPlayerNationality.text = player.nationality

            val pos = player.position?.uppercase() ?: ""
            
            val colorRes = when {
                pos.contains("GOALKEEPER") -> R.color.goalkeeper
                pos.contains("DEFENCE") || pos.contains("DEFENDER") || pos.contains("BACK") -> R.color.defender
                pos.contains("MIDFIELD") || pos.contains("MIDFIELDER") -> R.color.midfielder
                pos.contains("OFFENCE") || pos.contains("FORWARD") || pos.contains("ATTACK") || pos.contains("WINGER") || pos.contains("STRIKER") -> R.color.forward
                else -> R.color.goalkeeper
            }

            try {
                val colorInt = ContextCompat.getColor(context, colorRes)
                (binding.root).setCardBackgroundColor(colorInt)
            } catch (e: Exception) {
                binding.root.setCardBackgroundColor(Color.WHITE)
            }

            binding.root.setOnClickListener { view ->
                val bottomSheetDialog = BottomSheetDialog(context)
                val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_player_detail, null)

                val nameTv = dialogView.findViewById<TextView>(R.id.dialog_tv_name)
                val dobTv = dialogView.findViewById<TextView>(R.id.dialog_tv_birthdate)
                val natTv = dialogView.findViewById<TextView>(R.id.dialog_tv_nationality)
                val posTv = dialogView.findViewById<TextView>(R.id.dialog_tv_position)

                nameTv.text = player.name ?: "-"
                dobTv.text = player.dateOfBirth ?: "-"
                natTv.text = player.nationality ?: "-"
                posTv.text = player.position ?: "-"

                bottomSheetDialog.setContentView(dialogView)
                bottomSheetDialog.show()
            }
        }
    }
}
