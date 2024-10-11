package com.dicoding.dicodingeventsubmission.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.dicodingeventsubmission.data.response.EventResponse
import com.dicoding.dicodingeventsubmission.data.retrofit.ApiConfig
import com.dicoding.dicodingeventsubmission.databinding.FragmentDashboardBinding
import com.dicoding.dicodingeventsubmission.ui.EventsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var eventsAdapter: EventsAdapter

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        getEvents()
    }

    private fun setupRecyclerView() {
        eventsAdapter = EventsAdapter()
        binding.rvEvents.adapter = eventsAdapter
        binding.rvEvents.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun getEvents() {
        showLoading(true)

        val apiService = ApiConfig.getApiService()

        apiService.getActiveEvents(0).enqueue(object : Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                showLoading(false)
                if (response.isSuccessful) {
                    response.body()?.let { eventResponse ->
                        eventsAdapter.submitList(eventResponse.listEvents)
                    } ?: run {
                        showLoading(isLoading = false)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<EventResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}