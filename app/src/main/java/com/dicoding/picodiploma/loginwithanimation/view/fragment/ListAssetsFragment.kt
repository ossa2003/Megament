package com.dicoding.picodiploma.loginwithanimation.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.loginwithanimation.R
import com.dicoding.picodiploma.loginwithanimation.data.response.ListStoryItem
import com.dicoding.picodiploma.loginwithanimation.databinding.FragmentListBinding
import com.dicoding.picodiploma.loginwithanimation.view.ViewModelFactory
import com.dicoding.picodiploma.loginwithanimation.view.assets.AssetsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListAssetsFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListAssetsModel by viewModels { ViewModelFactory.getInstance(requireContext()) }
    private lateinit var assetsAdapter: AssetsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchAllAssets()
        observeListAssets()
    }

    private fun fetchAllAssets() {
        lifecycleScope.launch(Dispatchers.Main) {
            try {
                viewModel.getAllStory()
            } catch (e: Exception) {
                Log.e("ListAssetsFragment", "Error: ${e.message}")
            }
        }
    }

    private fun observeListAssets() {
        viewModel.storyList.observe(viewLifecycleOwner) { assetsList ->
            if (assetsList.isNotEmpty()) {
                setupAssetsAdapter(assetsList)
            } else {
                showEmptyStoryToast()
                setupEmptyAssetsAdapter()
            }
        }
    }

    private fun setupAssetsAdapter(assetList: List<ListStoryItem>) {
        assetsAdapter = AssetsAdapter(assetList, object : AssetsAdapter.OnAdapterListener {
            override fun onClick(story: ListStoryItem) {
                navigateToDetailStory(story)
            }
        })
        binding.rvStory.apply {
            assetsAdapter
            LinearLayoutManager(requireContext())
        }
    }

    private fun setupEmptyAssetsAdapter() {
        assetsAdapter = AssetsAdapter(mutableListOf(), object : AssetsAdapter.OnAdapterListener {
            override fun onClick(story: ListStoryItem) {
                // Handle empty state
            }
        })
        binding.rvStory.apply {
            assetsAdapter
            LinearLayoutManager(requireContext())
        }
    }

    private fun showEmptyStoryToast() {
        Toast.makeText(requireContext(), getString(R.string.storyEmpty), Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetailStory(story: ListStoryItem) {
//        Intent(requireContext(), StoryDetailActivity::class.java).apply {
//            putExtra(StoryDetailActivity.EXTRA_PHOTO_URL, story.photoUrl)
//            putExtra(StoryDetailActivity.EXTRA_CREATED_AT, story.createdAt)
//            putExtra(StoryDetailActivity.EXTRA_NAME, story.name)
//            putExtra(StoryDetailActivity.EXTRA_DESCRIPTION, story.description)
//            putExtra(StoryDetailActivity.EXTRA_LON, story.lon)
//            putExtra(StoryDetailActivity.EXTRA_ID, story.id)
//            putExtra(StoryDetailActivity.EXTRA_LAT, story.lat)
//        }.also {
//            startActivity(it)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
