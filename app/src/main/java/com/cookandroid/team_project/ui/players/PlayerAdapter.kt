package com.cookandroid.team_project.ui.players

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cookandroid.team_project.R
import com.cookandroid.team_project.data.model.PlayerRow

class PlayerAdapter(
    private val statLabel: String
) : RecyclerView.Adapter<PlayerAdapter.VH>() {
    private val items: MutableList<PlayerRow> = mutableListOf()

    fun submit(list: List<PlayerRow>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return VH(view, statLabel)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    class VH(itemView: View, private val statLabel: String) : RecyclerView.ViewHolder(itemView) {
        private val tvRank: TextView = itemView.findViewById(R.id.tvRank)
        private val ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvTeam: TextView = itemView.findViewById(R.id.tvTeam)
        private val tvValue: TextView = itemView.findViewById(R.id.tvValue)

        fun bind(row: PlayerRow) {
            tvRank.text = row.rank.toString()
            ivPhoto.load(row.playerPhotoUrl)
            tvName.text = row.playerName
            tvTeam.text = row.teamName ?: ""
            tvValue.text = "${row.statValue}"
            tvValue.contentDescription = "$statLabel ${row.statValue}"
        }
    }
}


