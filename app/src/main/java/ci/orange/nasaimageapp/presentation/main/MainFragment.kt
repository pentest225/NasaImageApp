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


class MainFragment : Fragment() {
    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity)

        ViewModelProvider(this, MainViewModel.Factory(activity.application))[MainViewModel::class.java]
    }
    private lateinit var binding : FragmentMainBinding
    private lateinit var adapter: AsteroidAdapter

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

        viewModel.imagesOfWeek.observe(viewLifecycleOwner, Observer {
            adapter = AsteroidAdapter(it, onSelect = {asteroid ->
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(asteroid)

                findNavController().navigate(action)
            })
            binding.asteroidRecycler.adapter  = adapter
        })


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
