package net.svichch.geekbrains.films.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import net.svichch.geekbrains.films.MainActivity
import net.svichch.geekbrains.films.databinding.FragmentFilmInfoBinding
import net.svichch.geekbrains.films.nework.api.ApiHolder
import net.svichch.geekbrains.films.nework.api.movie.Cast
import net.svichch.geekbrains.films.nework.api.movie.Result
import net.svichch.geekbrains.films.nework.api.retrofit.RetrofitFilms
import net.svishch.android.outerspace.mvp.model.image.GlideImageLoader


class FilmInfoFragment : Fragment() {

    private lateinit var fragment: FragmentFilmInfoBinding
    private lateinit var adapter: ActorsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment = FragmentFilmInfoBinding.inflate(inflater, container, false)
        loadData()
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonBackOn()
        loadData()
    }

    private fun addAdapter(results: List<Cast>) {
        adapter = ActorsAdapter(results, GlideImageLoader())
        fragment.listActors.adapter = adapter
    }
    private fun loadData() {

        GlideImageLoader().loadInto(RetrofitFilms.BASE_URL_IMG + film.backdropPath, fragment.ivImg)
        fragment.title.text = film.title
        fragment.releaseDate.text = film.releaseDate
        fragment.vote.text = film.voteAverage
        fragment.overview.text = film.overview

        getActors(film.id)
    }

    private fun getActors(movieID: Long) {
        RetrofitFilms(ApiHolder().api).getActors(movieID)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ actors ->
                actors.cast?.let {
                    addAdapter(it)
                }
            }, {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            })
    }

    private fun setButtonBackOn() {
        val actionBar = (requireActivity() as MainActivity).getSupportActionBar()
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        private lateinit var film: Result
        fun newInstance(filmin: Result): Fragment {
            film = filmin
            return FilmInfoFragment()
        }

    }
}