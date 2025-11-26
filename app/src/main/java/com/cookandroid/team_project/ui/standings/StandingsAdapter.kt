package com.cookandroid.team_project.ui.standings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cookandroid.team_project.R
import com.cookandroid.team_project.data.model.StandingsRow

class StandingsAdapter : RecyclerView.Adapter<StandingsAdapter.VH>() {
    private val items: MutableList<StandingsRow> = mutableListOf()

    fun submit(list: List<StandingsRow>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_standing, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position])
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvRank: TextView = itemView.findViewById(R.id.tvRank)
        private val ivLogo: ImageView = itemView.findViewById(R.id.ivLogo)
        private val tvTeam: TextView = itemView.findViewById(R.id.tvTeam)
        private val tvPoints: TextView = itemView.findViewById(R.id.tvPoints)

        fun bind(row: StandingsRow) {
            tvRank.text = row.rank.toString()
            ivLogo.load(row.teamLogoUrl)
            tvTeam.text = row.teamName
            tvPoints.text = row.points.toString()
        }
    }
}


