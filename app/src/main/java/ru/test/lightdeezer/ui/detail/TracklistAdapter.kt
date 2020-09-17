package ru.test.lightdeezer.ui.playlist.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.test.lightdeezer.R
import ru.test.lightdeezer.data.models.Track
import ru.test.lightdeezer.databinding.TracklistItemBinding

class TracklistAdapter() : RecyclerView.Adapter<TracklistAdapter.ViewHolder>()  {
    private lateinit var tracklist: List<Track>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: TracklistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.tracklist_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tracklist[position])
    }
    override fun getItemCount(): Int {
        return if (::tracklist.isInitialized) tracklist.size else 0
    }

    fun updateTracklist(tracklist: List<Track>) {
        this.tracklist = tracklist
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: TracklistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = TracklistInfoViewModel()

        fun bind(track: Track) {
            viewModel.bind(track)
            binding.viewModel = viewModel
        }

    }

}