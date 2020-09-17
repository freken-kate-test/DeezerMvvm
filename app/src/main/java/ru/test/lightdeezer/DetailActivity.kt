package ru.test.lightdeezer

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.test.lightdeezer.databinding.ActivityDetailBinding
import ru.test.lightdeezer.ui.playlist.detail.DetailActivityViewModel
import ru.test.lightdeezer.utils.DetailFactory

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailActivityViewModel
    private var errorSnackbar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.myRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val id = extras!!.getString("PLAYLIST_ID")
        viewModel = ViewModelProviders.of(this, DetailFactory(id!!))
            .get(DetailActivityViewModel::class.java)
        val playlistCover = extras!!.getString("PLAYLIST_COVER_URL")
        playlistCover?.let { viewModel.detailViewModel.bind(it) }
        binding.viewModel = viewModel

        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

}