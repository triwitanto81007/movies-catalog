package id.namikaze.moviescatalog.presenter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import id.namikaze.moviescatalog.BuildConfig
import id.namikaze.moviescatalog.adapter.MovieAdapter
import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.databinding.FragmentMoviesBinding
import id.namikaze.moviescatalog.presenter.viewmodel.MovieViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModel()

    private val args: MoviesFragmentArgs by navArgs()

    private val recyclerViewAdapter by lazy {
        MovieAdapter(::navigateToLeagueDetail)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbMovies.title = args.nameGenres
        setupRecyclerView()

        viewModel.movie.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    it.getLoadingStateIfNotHandled()?.let {

                    }
                }
                is Resource.Success -> {
                    it.getSuccessStateIfNotHandled()?.let {
                        Log.d("JumlahItem", it.size.toString())
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
            viewModel.getMovieList(BuildConfig.API_KEY, args.withGenres.toInt())
        }

        binding.tbMovies.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun setupRecyclerView() = with(binding.rvMovies) {
        setHasFixedSize(true)
        layoutManager = GridLayoutManager(context, SPAN_COUNT_LEAGUE_ITEM)
        adapter = recyclerViewAdapter
    }

    private fun navigateToLeagueDetail(movieId: String) {
        super.getView()?.findNavController()?.navigate(
            MoviesFragmentDirections.actionMoviesFragmentToDetailMovieFragment(movieId)
        )
    }

    companion object {
        const val SPAN_COUNT_LEAGUE_ITEM = 3
    }

}