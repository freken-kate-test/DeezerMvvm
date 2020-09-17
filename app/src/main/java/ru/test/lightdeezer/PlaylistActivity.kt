package ru.test.lightdeezer

import android.content.Intent
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.test.lightdeezer.databinding.ActivityMainBinding
import ru.test.lightdeezer.ui.playlist.PLaylistsViewModel
import ru.test.lightdeezer.ui.playlist.PlaylistItemClickListener
import ru.test.lightdeezer.utils.CustomFactory

class PlaylistActivity : AppCompatActivity(), PlaylistItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PLaylistsViewModel
    private var errorSnackbar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.trackList.layoutManager =
            GridLayoutManager(this, 3)

        viewModel = ViewModelProviders.of(this, CustomFactory(this, this))
            .get(PLaylistsViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel

    }

    override fun onPlaylistItemClicked(playlistCover: String, playlistId: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("PLAYLIST_COVER_URL", playlistCover)
        intent.putExtra("PLAYLIST_ID", playlistId)
        startActivity(intent)
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}