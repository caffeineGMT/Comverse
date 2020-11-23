package edu.utcs.comicwiki.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import edu.utcs.comicwiki.R
import edu.utcs.comicwiki.model.Team
import edu.utcs.comicwiki.glide.Glide
import edu.utcs.comicwiki.ui.MainViewModel
import edu.utcs.comicwiki.ui.team.TeamFragment

class TeamListAdapter(private val viewModel: HomeViewModel) :
    RecyclerView.Adapter<TeamListAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var teamImage = itemView.findViewById<ImageView>(R.id.characterImage)
        private var deck = itemView.findViewById<TextView>(R.id.deck)

        init {
            itemView.setOnClickListener {
                val url = viewModel.getTeamListAt(adapterPosition)?.apiDetailURL
                if (url != null) {
                    viewModel.set_team_apiPath(url)
                }
                viewModel.netFetchTeam()

//                (itemView.context as FragmentActivity).supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.test, TeamFragment.newInstance())
//                    .addToBackStack(null)
//                    .commit()
            }
        }

        fun bind(item: Team?) {
            deck.text = item?.deck
            Glide.fetch(item?.image!!.thumbURL,item?.image.thumbURL, teamImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.row_team, parent, false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
       holder.bind(viewModel.getTeamListAt(position))
    }

    override fun getItemCount(): Int {
        return viewModel.getTeamListCount()
    }

}