package pl.ozodbek.multiviewtyperecyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import pl.ozodbek.multiviewtyperecyclerview.adapter.PostsRvAdapter
import pl.ozodbek.multiviewtyperecyclerview.adapter.PostsRvItem
import pl.ozodbek.multiviewtyperecyclerview.data.TopRecommendedsList
import pl.ozodbek.multiviewtyperecyclerview.databinding.ActivityMainBinding
import pl.ozodbek.multiviewtyperecyclerview.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val postsRvAdapter: PostsRvAdapter by lazy { PostsRvAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupUI()
    }

    private fun setupUI() {
        setupActionBar()
        setupRecyclerView()
    }

    private fun setupActionBar() {
        this.setSupportActionBar(binding.toolbar)
        this.title = "MultiViewTypeRecyclerView"
    }

    private fun setupRecyclerView() {
        binding.postsRecyclerview.adapter = postsRvAdapter

        postsRvAdapter.itemClickListener = { view, item, position ->
            when (item) {
                is PostsRvItem.TopPosts -> {
                    when (item.id) {
                        1 -> {
                            Snackbar.make(view, "1st Image clicked", Snackbar.LENGTH_SHORT).show()
                        }

                        2 -> {
                            Snackbar.make(view, "2st Image clicked", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }

                is TopRecommendedsList ->
                    Snackbar.make(view, "${item.id} master clicked", Snackbar.LENGTH_SHORT).show()
            }
        }

        postsRvAdapter.submitList(generateMultiViewTypeRecyclerview())
    }


    private fun generateMultiViewTypeRecyclerview(): List<PostsRvItem> {
        val items = mutableListOf<PostsRvItem>()

        items.add(
            PostsRvItem.Titles(1, "Top Posts")
        )

        items.addAll(generateTopPosts())

        items.add(PostsRvItem.EmptySpace(1))

        items.add(
            PostsRvItem.Titles(2, "Top Recommendeds")
        )

        items.addAll(generateTopMasters())

        items.add(PostsRvItem.EmptySpace(2))

        return items
    }

    private fun generateTopPosts(): List<PostsRvItem.TopPosts> {
        return listOf(
            PostsRvItem.TopPosts(1, "https://picsum.photos/130/300"),
            PostsRvItem.TopPosts(2, "https://picsum.photos/101/300")
        )

    }

    private fun generateTopMasters(): List<PostsRvItem.TopRecommendeds> {
        return listOf(
            TopRecommendedsList(1, "https://picsum.photos/100/300?category=people", "John1", "Doe1", "13"),
            TopRecommendedsList(2, "https://picsum.photos/200/460?category=people", "John2", "Doe2", "33"),
            TopRecommendedsList(3, "https://picsum.photos/300/400?category=people", "John3", "Doe3", "32"),
            TopRecommendedsList(4, "https://picsum.photos/400/400?category=people", "John4", "Doe4", "23"),
            TopRecommendedsList(5, "https://picsum.photos/500/400?category=people", "John5", "Doe5", "53"),
            TopRecommendedsList(6, "https://picsum.photos/300/400?category=people", "John6", "Doe6", "62"),
            TopRecommendedsList(7, "https://picsum.photos/200/400?category=people", "John7", "Doe7", "27"),
            TopRecommendedsList(8, "https://picsum.photos/200/400?category=people", "John7", "Doe7", "29"),
            TopRecommendedsList(9, "https://picsum.photos/200/400?category=people", "John7", "Doe7", "222"),
            TopRecommendedsList(11, "https://picsum.photos/200/400", "John7", "Doe7", "227"),
            TopRecommendedsList(12, "https://picsum.photos/200/400", "John7", "Doe7", "237"),
            TopRecommendedsList(13, "https://picsum.photos/200/400", "John7", "Doe7", "247"),
            TopRecommendedsList(14, "https://picsum.photos/200/400", "John7", "Doe7", "275"),
            TopRecommendedsList(15, "https://picsum.photos/200/400", "John7", "Doe7", "247")
        ).map { PostsRvItem.TopRecommendeds(it) }
    }
}