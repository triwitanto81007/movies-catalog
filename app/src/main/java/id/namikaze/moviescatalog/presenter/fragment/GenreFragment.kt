package id.namikaze.moviescatalog.presenter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.namikaze.moviescatalog.BuildConfig
import id.namikaze.moviescatalog.adapter.GenreAdapter
import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.databinding.FragmentGenreBinding
import id.namikaze.moviescatalog.presenter.viewmodel.GenreViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class GenreFragment : Fragment() {

    private var _binding: FragmentGenreBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GenreViewModel by viewModel()

    private val recyclerViewAdapter by lazy {
        GenreAdapter(::navigateToLeagueDetail)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.genre.observe(viewLifecycleOwner, {
            when (it) {
               is Resource.Loading -> {
                   it.getLoadingStateIfNotHandled()?.let {

                   }
               }
                is Resource.Success -> {
                    it.getSuccessStateIfNotHandled()?.let {
                        recyclerViewAdapter.setData(it)
                    }
                }
                is Resource.Error -> {
                    it.getErrorStateIfNotHandled()?.let {
                    }
                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getGenreList(BuildConfig.API_KEY)
        }
    }

    private fun setupRecyclerView() = with(binding.rvGenreMovies) {
        setHasFixedSize(true)
        layoutManager = GridLayoutManager(context, SPAN_COUNT_LEAGUE_ITEM)
        adapter = recyclerViewAdapter
    }

    private fun navigateToLeagueDetail(withGenres: String, nameGenres: String) {
        super.getView()?.findNavController()?.navigate(
            GenreFragmentDirections.actionGenreFragmentToMoviesFragment(withGenres, nameGenres)
        )
    }

    companion object {
        const val SPAN_COUNT_LEAGUE_ITEM = 2
    }
}