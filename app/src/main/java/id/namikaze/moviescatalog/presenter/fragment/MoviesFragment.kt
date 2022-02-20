package id.namikaze.moviescatalog.presenter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import id.namikaze.moviescatalog.BuildConfig
import id.namikaze.moviescatalog.adapter.MovieAdapter
import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.databinding.FragmentMoviesBinding
import id.namikaze.moviescatalog.domain.model.Movie
import id.namikaze.moviescatalog.presenter.viewmodel.MovieViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModel()

    private val args: MoviesFragmentArgs by navArgs()

    private var isLoadMore = false
    private var isLoading = false
    private var pageNumber = 1
    private var offset = 0
    private var limit = 20
    private var counter = 20

    private val recyclerViewAdapter by lazy {
        MovieAdapter(::navigateToMovieeDetail)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUi()
        setupRecyclerView()

        viewModel.movie.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    it.getLoadingStateIfNotHandled()?.let {}
                }
                is Resource.Success -> {
                    it.getSuccessStateIfNotHandled()?.let { data ->
                        setupResponseMovie(data)
                    }
                }
                is Resource.Error -> {
                    it.getErrorStateIfNotHandled()?.let { data ->
                        Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
                        binding.pbLoadmoreMovies.visibility = View.GONE
                    }
                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovieList(BuildConfig.API_KEY, args.withGenres.toInt(), pageNumber.toString(), limit, offset)
        }

    }

    private fun setupUi() {
        binding.tbMovies.title = args.nameGenres
        binding.tbMovies.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.nsvMovies.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == (v!!.getChildAt(0).measuredHeight - v.measuredHeight)) {
                if (!isLoading) {
                    isLoading = true
                    pageNumber += 1
                    offset += counter

                    binding.pbLoadmoreMovies.visibility = View.VISIBLE
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.getMovieList(BuildConfig.API_KEY, args.withGenres.toInt(), pageNumber.toString(), limit, offset)
                    }
                }
            }
        })
    }

    private fun setupResponseMovie(data: List<Movie>) {
        binding.pbLoadmoreMovies.visibility = View.GONE
        if (!isLoadMore){
            if (!data.isNullOrEmpty()){
                recyclerViewAdapter.setData(data, false)
                isLoadMore = true
            }else {
                binding.rvMovies.visibility = View.GONE
            }
        }else{
            if (isLoading) {
                isLoading = if (data.isNullOrEmpty()){
                    true
                }else{
                    recyclerViewAdapter.setData(data, true)
                    false
                }
            }
        }
    }

    private fun setupRecyclerView() = with(binding.rvMovies) {
        setHasFixedSize(true)
        layoutManager = GridLayoutManager(context, SPAN_COUNT_MOVIE_ITEM)
        adapter = recyclerViewAdapter
    }

    private fun navigateToMovieeDetail(movieId: String) {
        super.getView()?.findNavController()?.navigate(
            MoviesFragmentDirections.actionMoviesFragmentToDetailMovieFragment(movieId)
        )
    }

    companion object {
        const val SPAN_COUNT_MOVIE_ITEM = 3
    }

}