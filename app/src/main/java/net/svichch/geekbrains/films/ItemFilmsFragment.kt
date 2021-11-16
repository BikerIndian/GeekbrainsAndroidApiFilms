package net.svichch.geekbrains.films

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import net.svichch.geekbrains.films.databinding.FragmentItemListBinding
import net.svichch.geekbrains.films.nework.api.ApiHolder
import net.svichch.geekbrains.films.nework.api.retrofit.RetrofitFilms
import net.svichch.geekbrains.films.nework.api.movie.Result
import net.svishch.android.outerspace.mvp.model.image.GlideImageLoader

class ItemFilmsFragment : Fragment() {

    private var columnCount = 2
    private lateinit var fragment: FragmentItemListBinding
    private lateinit var adapter: FilmsRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragment = FragmentItemListBinding.inflate(inflater, container, false)
        fragment.list.layoutManager =  GridLayoutManager(context, columnCount)
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
        adapter = FilmsRecyclerAdapter(results, GlideImageLoader())
        fragment.root.adapter = adapter
    }

    companion object {

        const val ARG_COLUMN_COUNT = "films"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFilmsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}