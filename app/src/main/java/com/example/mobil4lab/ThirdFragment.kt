package com.example.mobil4lab

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ThirdFragment : Fragment(R.layout.fragment_third) {
    val data = arrayOf("First", "Second", "Third")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MaterialAlertDialogBuilder(requireContext())
            .setIcon(R.drawable.baseline_dataset_24)
            .setTitle("Title")
            //.setMessage("Message")
            .setItems(data) { _, pos ->
                Log.d("FirstFragment", "openSecondFragment: ${data[pos]}")
            }
            .setPositiveButton("OK") {
                    dialog, _ -> dialog.dismiss()
            }
            .setNegativeButton("Cancel") {
                    dialog, _ -> dialog.dismiss()
            }
            .show()
    }



    companion object {
        fun newInstance(): ThirdFragment {
            return ThirdFragment()
        }
    }


}