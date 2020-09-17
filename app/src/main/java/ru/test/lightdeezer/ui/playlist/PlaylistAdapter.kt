package ru.test.lightdeezer.ui.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ru.test.lightdeezer.R
import ru.test.lightdeezer.data.models.Playlist
import ru.test.lightdeezer.databinding.PlaylistItemBinding

class PlaylistAdapter(listener: PlaylistItemClickListener) :
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {
    private lateinit var playlist: List<Playlist>
    private var itemClickListener = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistAdapter.ViewHolder {
        val binding: PlaylistItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.playlist_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlaylistAdapter.ViewHolder, position: Int) {
        holder.bind(playlist[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return if (::playlist.isInitialized) playlist.size else 0
    }

    fun updatePlaylist(playlist: List<Playlist>) {
        this.playlist = playlist
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: PlaylistItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PlaylistInfoViewModel()

        fun bind(playlist: Playlist, listener: PlaylistItemClickListener) {
            viewModel.bind(playlist)
            binding.viewModel = viewModel
            binding.playlistItemClick = listener
        }
    }

}

