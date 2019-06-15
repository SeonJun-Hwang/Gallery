package gfriend_yerin.textblurgallery.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gfriend_yerin.textblurgallery.R
import gfriend_yerin.textblurgallery.data.ValueObject.PhotoVO
import gfriend_yerin.textblurgallery.databinding.GalleryItemBinding
import kotlinx.android.synthetic.main.gallery_item.view.*

class GalleryAdapter(
    private val mContext: Context,
    private val photoList: List<PhotoVO>,
    private val layout: Int
) : RecyclerView.Adapter<GalleryAdapter.PhotoViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = DataBindingUtil.inflate<GalleryItemBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return PhotoViewHolder(view)
    }

    override fun getItemCount() = photoList.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    @BindingAdapter("bind_item")
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) = holder.bind(photoList[position], position)

    inner class PhotoViewHolder(private val view: GalleryItemBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: PhotoVO, position: Int) {
            view.apply {
                galleryPhotoDeco.visibility = if (item.selected) View.VISIBLE else View.INVISIBLE
                galleryPhotoItem
                galleryItemDecoSelected
                galleryItemDecoShowOrigin

                Glide.with(mContext).load(item.imagePath).dontAnimate().centerCrop().into(galleryPhotoItem)

                root.setOnClickListener {
                    if (::itemClickListener.isInitialized) itemClickListener.OnItemClick(
                        this@PhotoViewHolder,
                        position
                    )
                }
            }
        }
    }
}