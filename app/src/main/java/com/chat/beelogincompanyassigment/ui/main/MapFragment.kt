package com.chat.beelogincompanyassigment.ui.main

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.chat.beelogincompanyassigment.R
import com.chat.beelogincompanyassigment.ui.viewmodel.MapViewModel

class MapFragment : Fragment() {

    private val viewModel: MapViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val v = inflater.inflate(R.layout.fragment_map, container, false)
        val mapLayout = v.findViewById<ViewGroup>(R.id.fakeMap)

        observeViewModel(v, mapLayout)

        return v
    }

    private fun observeViewModel(root: View, mapLayout: ViewGroup) {

        // Observe markers list
        viewModel.markers.observe(viewLifecycleOwner) { markers ->

            mapLayout.removeAllViews()

            markers.forEach { marker ->
                val markerView = layoutInflater.inflate(
                    R.layout.item_map_marker, mapLayout, false
                )

                markerView.x = marker.x
                markerView.y = marker.y

                markerView.setOnClickListener {
                    viewModel.selectMarker(marker)
                }

                mapLayout.addView(markerView)
            }
        }

        // Observe selected marker popup
        viewModel.selectedMarker.observe(viewLifecycleOwner) { marker ->
            if (marker != null) showMarkerPopup(root, marker)
        }
    }

    private fun showMarkerPopup(root: View, marker: MapMarker) {
        val popup = root.findViewById<LinearLayout>(R.id.markerPopup)
        val title = root.findViewById<TextView>(R.id.popupTitle)
        val img = root.findViewById<ImageView>(R.id.popupImage)

        title.text = marker.title
        img.setImageResource(marker.image)

        popup.visibility = View.VISIBLE
    }
}

data class MapMarker(
    val title: String,
    val image: Int,
    val x: Float,
    val y: Float
)