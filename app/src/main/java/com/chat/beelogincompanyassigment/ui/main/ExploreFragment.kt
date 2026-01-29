package com.chat.beelogincompanyassigment.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chat.beelogincompanyassigment.Adapter.ExploreAdapter
import com.chat.beelogincompanyassigment.R
import com.chat.beelogincompanyassigment.ui.viewmodel.ExploreViewModel

class ExploreFragment : Fragment() {

    private lateinit var grid: RecyclerView
    private lateinit var adapter: ExploreAdapter

    private val viewModel: ExploreViewModel by viewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        val chipContainer = view.findViewById<ViewGroup>(R.id.chipContainer)
        grid = view.findViewById(R.id.exploreGrid)
        grid.layoutManager = GridLayoutManager(requireContext(), 2)

        /** Default Adapter */
        adapter = ExploreAdapter(listOf()) { openDetail(it) }
        grid.adapter = adapter

        /** Observe categories list */
        viewModel.categoriesLive.observe(viewLifecycleOwner) { categoryList ->
            chipContainer.removeAllViews()
            categoryList.forEach { category ->
                val chip = layoutInflater.inflate(R.layout.item_chip, chipContainer, false) as TextView
                chip.text = category

                chip.setOnClickListener {
                    viewModel.onCategorySelected(category)
                }

                chipContainer.addView(chip)
            }
        }

        /** Observe image list updates */
        viewModel.imageList.observe(viewLifecycleOwner) { images ->
            adapter.updateData(images)
        }

        return view
    }

    private fun openDetail(imageRes: Int) {
        val intent = Intent(requireContext(), ExploreDetailActivity::class.java)
        intent.putExtra("img", imageRes)
        startActivity(intent)
    }
}