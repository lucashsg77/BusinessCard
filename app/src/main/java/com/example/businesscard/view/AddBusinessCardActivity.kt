package com.example.businesscard.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.divyanshu.colorseekbar.ColorSeekBar
import com.example.businesscard.App
import com.example.businesscard.R
import com.example.businesscard.databinding.ActivityAddBusinessCardBinding
import com.example.businesscard.model.BusinessCard
import com.example.businesscard.viewmodel.BusinessCardViewModel
import com.example.businesscard.viewmodel.BusinessCardViewModelFactory

class AddBusinessCardActivity: AppCompatActivity() {

    private val binding by lazy { ActivityAddBusinessCardBinding.inflate(layoutInflater)}
    private val businessCardViewModel: BusinessCardViewModel by viewModels {
        BusinessCardViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        insertListeners()
    }

    private fun insertListeners(){
        var chosenColor: Int = 0
        binding.closeBtn.setOnClickListener{
            finish()
        }
        binding.colorBar.setOnColorChangeListener(object: ColorSeekBar.OnColorChangeListener {
            override fun onColorChangeListener(color: Int) {
                binding.root.rootView.setBackgroundColor(color)
                chosenColor = color
            }
        })
        binding.confirmBtn.setOnClickListener{
            val card = BusinessCard(
                name = binding.nameTil.editText?.text.toString() ,
                phone = binding.phoneTil.editText?.text.toString() ,
                email = binding.emailTil.editText?.text.toString() ,
                company = binding.companyTil.editText?.text.toString() ,
                color = chosenColor
            )
            businessCardViewModel.insert(card)
            Toast.makeText(this, getString(R.string.successToast),Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}