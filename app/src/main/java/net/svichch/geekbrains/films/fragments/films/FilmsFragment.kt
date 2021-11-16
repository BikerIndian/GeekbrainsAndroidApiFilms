package net.svichch.geekbrains.films.fragments.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import net.svichch.geekbrains.films.MainActivity
import net.svichch.geekbrains.films.databinding.FragmentItemListBinding

import net.svichch.geekbrains.films.fragments.details.FilmInfoFragment
import net.svichch.geekbrains.films.nework.api.ApiHolder
import net.svichch.geekbrains.films.nework.api.retrofit.RetrofitFilms
import net.svichch.geekbrains.films.nework.api.movie.Result
import net.svishch.android.outerspace.mvp.model.image.GlideImageLoader

class FilmsFragment : Fragment(), FilmsAdapter.AdapterFilmsRecyclerFunctional {

    private var columnCount = 2
    private lateinit var fragment: FragmentItemListBinding
    private lateinit var adapter: FilmsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setButtonBackOff()
        fragment = FragmentItemListBinding.inflate(inflater, container, false)
        fragment.list.layoutManager = GridLayoutManager(context, columnCount)
        loadData()
        return fragment.root
    }

    fun loadData() {

        RetrofitFilms(ApiHolder().api).getFilms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ films ->
                films.results?.let { addAdapter(it) }
            }, {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })

    }

    private fun addAdapter(results: List<Result>) {
        adapter = FilmsAdapter(results, GlideImageLoader(), this)
        fragment.list.adapter = adapter
    }

    // Переход на фрагмент информации о фильме
    override fun toFilmInfoFragment(film: Result) {
        (requireActivity() as MainActivity).navigateTo(
            FilmInfoFragment.newInstance(film)
        )
    }

    private fun setButtonBackOff() {
        val actionBar = (requireActivity() as MainActivity).getSupportActionBar()
        actionBar?.setHomeButtonEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = FilmsFragment()
    }
}