package id.namikaze.moviescatalog.presenter.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import id.namikaze.moviescatalog.BuildConfig
import id.namikaze.moviescatalog.R
import id.namikaze.moviescatalog.adapter.ReviewAdapter
import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.databinding.FragmentDetailMovieBinding
import id.namikaze.moviescatalog.domain.model.MovieDetail
import id.namikaze.moviescatalog.domain.model.Review
import id.namikaze.moviescatalog.domain.model.Trailer
import id.namikaze.moviescatalog.presenter.viewmodel.DetailMovieViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailMovieViewModel by viewModel()

    private val args: DetailMovieFragmentArgs by navArgs()

    private var isLoadMore = false
    private var isLoading = false
    private var pageNumber = 1
    private var offset = 0
    private var limit = 20
    private var counter = 20

    private val recyclerViewAdapter by lazy {
        ReviewAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbMoviesDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        setupRecyclerView()

        viewModel.movieDetail.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    it.getLoadingStateIfNotHandled()?.let {

                    }
                }
                is Resource.Success -> {
                    it.getSuccessStateIfNotHandled()?.let { data ->
                        binding.pbTeamDetail.visibility = View.GONE
                        setupUi(data)
                    }
                }
                is Resource.Error -> {
                    it.getErrorStateIfNotHandled()?.let {
                    }
                }
            }
        })

        viewModel.review.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    it.getLoadingStateIfNotHandled()?.let {
                    }
                }
                is Resource.Success -> {
                    it.getSuccessStateIfNotHandled()?.let { data ->
                        binding.pbLoadmoreMovieDetail.visibility = View.GONE
                        setupReview(data)
                    }
                }
                is Resource.Error -> {
                    it.getErrorStateIfNotHandled()?.let {
                        binding.pbLoadmoreMovieDetail.visibility = View.GONE
                    }
                }
            }
        })

        viewModel.trailer.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {
                    it.getLoadingStateIfNotHandled()?.let {

                    }
                }
                is Resource.Success -> {
                    it.getSuccessStateIfNotHandled()?.let { data ->
                        setupTrailer(data)
                    }
                }
                is Resource.Error -> {
                    it.getErrorStateIfNotHandled()?.let {
                    }
                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovieDetail(BuildConfig.API_KEY, args.movieId.toInt())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getReview(BuildConfig.API_KEY, args.movieId.toInt(), pageNumber.toString(), limit, offset)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getTrailer(BuildConfig.API_KEY, args.movieId.toInt())
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setupUi(data: MovieDetail) {
        binding.apply{
            Glide.with(this@DetailMovieFragment).load(BuildConfig.IMAGE_BG_URL + data.backdropPath).placeholder(R.drawable.ic_default_image).into(contentHeader.ivBackgroundMovieDetail)
            Glide.with(this@DetailMovieFragment).load(BuildConfig.IMAGE_URL + data.posterPath).placeholder(R.drawable.ic_default_cover_movie).into(contentHeader.ivCoverMovieDetail)
            contentHeader.tvTitleNameMovieDetail.text = data.title
            contentHeader.tvRatingMovieDetail.text = "${data.voteAverage}/10"
            contentHeader.tvOverviewMovieDetail.text = data.overview

            nsvMoviesDetail.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
                if (scrollY == (v!!.getChildAt(0).measuredHeight - v.measuredHeight)) {
                    if (!isLoading) {
                        isLoading = true
                        pageNumber += 1
                        offset += counter

                        pbLoadmoreMovieDetail.visibility = View.VISIBLE
                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.getReview(BuildConfig.API_KEY, args.movieId.toInt(), pageNumber.toString(), limit, offset)
                        }
                    }
                }
            })
        }
    }

    private fun setupReview(data: List<Review>) {
        if (!isLoadMore){
            if (!data.isNullOrEmpty()){
                recyclerViewAdapter.setData(data, false)
                isLoadMore = true
            }else {
                binding.contentReview.rvReviewMovieDetail.visibility = View.GONE
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

    private fun setupTrailer(data: Trailer) {
        if (data.site == "YouTube"){
            binding.ypTrailerMovieDetail.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                    val videoId = data.key.toString()
                    youTubePlayer.loadVideo(videoId, 0f)
                    youTubePlayer.pause()
                }
            })
        } else {
            binding.ypTrailerMovieDetail.visibility = View.GONE
        }
    }

    private fun setupRecyclerView() = with(binding.contentReview.rvReviewMovieDetail) {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        adapter = recyclerViewAdapter
    }
}