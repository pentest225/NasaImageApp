package ci.orange.nasaimageapp.presentation.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ci.orange.nasaimageapp.R
import ci.orange.nasaimageapp.presentation.adapter.AsteroidAdapter
import ci.orange.nasaimageapp.databinding.FragmentMainBinding
import ci.orange.nasaimageapp.presentation.adapter.AsteroidListAdapter


class MainFragment : Fragment() {
    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity)

        ViewModelProvider(this, MainViewModel.Factory(activity.application))[MainViewModel::class.java]
    }
    private lateinit var binding : FragmentMainBinding
    private lateinit var adapter: AsteroidListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AsteroidListAdapter(onSelect = {asteroid ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(asteroid)
            findNavController().navigate(action)
        })
        binding.asteroidRecycler.adapter  = adapter
        viewModel.imagesOfWeek.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.show_saved_asteroid -> {
                viewModel.showAllSavedAsteroid()
            }
            R.id.show_today_asteroid -> {
                viewModel.showToDayAsteroid()
            }
            R.id.show_week_asteroid -> {
                viewModel.showWeekAsteroid()
            }
            else -> {
                viewModel.showToDayAsteroid()
            }
        }

        return true
    }
}
