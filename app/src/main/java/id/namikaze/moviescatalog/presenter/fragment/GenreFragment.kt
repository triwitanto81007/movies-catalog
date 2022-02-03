package id.namikaze.moviescatalog.presenter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import id.namikaze.moviescatalog.data.Resource
import id.namikaze.moviescatalog.databinding.FragmentGenreBinding
import id.namikaze.moviescatalog.presenter.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class GenreFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private var _binding: FragmentGenreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recipe.observe(viewLifecycleOwner, {
            when (it) {
               is Resource.Loading -> {
                   it.getLoadingStateIfNotHandled()?.let {

                   }
               }
                is Resource.Success -> {
                    it.getSuccessStateIfNotHandled()?.let {
                    }
                }
                is Resource.Error -> {
                    it.getErrorStateIfNotHandled()?.let {
                    }
                }
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getRecipesList()
        }
    }
}