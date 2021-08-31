package com.example.businesscard.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.businesscard.App
import com.example.businesscard.R
import com.example.businesscard.databinding.ActivityMainBinding
import com.example.businesscard.util.Image
import com.example.businesscard.viewmodel.BusinessCardViewModel
import com.example.businesscard.viewmodel.BusinessCardViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val businessCardViewModel: BusinessCardViewModel by viewModels {
        BusinessCardViewModelFactory((application as App).repository)
    }
    private val adapter by lazy { BusinessCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpPermissions()
        insertAdapter()
        allBusinessCard()
        insertListeners()
    }

    private fun insertAdapter() {
        binding.cardRv.adapter = adapter
        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder , direction: Int) {
                val position = viewHolder.adapterPosition
                businessCardViewModel.cards.value?.get(position)?.let {
                    businessCardViewModel.delete(it)
                    Snackbar.make(binding.root, getString(R.string.cardDeletedSnackbar), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.snackBarAction)) { _ ->
                            businessCardViewModel.insert(it)
                        }
                        .show()
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.cardRv)
    }

    private fun setUpPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            1
        )
    }

    private fun insertListeners(){
        binding.addCardBtn.setOnClickListener{
           val intent = Intent(this@MainActivity, AddBusinessCardActivity::class.java)
           startActivity(intent)
        }
        adapter.listenerShare = {
            Image.share(this@MainActivity, it)
        }
    }

    private fun allBusinessCard(){
        businessCardViewModel.cards.observe(this, {
            adapter.submitList(it)
        })
    }
}