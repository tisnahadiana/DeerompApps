package id.deeromptech.deerompapps.newsapp.view.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.deeromptech.deerompapps.GlideApp
import id.deeromptech.deerompapps.R
import id.deeromptech.deerompapps.databinding.ItemNewsBinding
import id.deeromptech.deerompapps.newsapp.model.news.News
import id.deeromptech.deerompapps.newsapp.view.detail.DetailNewsActivity
import id.deeromptech.deerompapps.utils.Constant
import id.deeromptech.deerompapps.utils.Constant.glideRequestOption

class NewsListAdapter (private val activity: FragmentActivity) : ListAdapter<News, NewsListAdapter.Holder>(MyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = getItem(position)

        with(holder.binding) {
            GlideApp.with(activity)
                .load(data.imageArticle)
                .apply(glideRequestOption(activity, R.drawable.image_empty))
                .into(imageNews)

            if (data.imageArticle == null){
                imageNews.visibility = View.GONE
            }

            textNewsName.text = data.title ?: "-"

            cardNews.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Constant.NEWS_TITLE, data.title.toString())
                bundle.putString(Constant.NEWS_CONTENT, data.content.toString())
                bundle.putString(Constant.NEWS_IMAGE, data.imageArticle.toString())

                val intent = Intent(activity, DetailNewsActivity::class.java)
                intent.putExtras(bundle)
                activity.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class MyDiffCallback : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    class Holder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)
}