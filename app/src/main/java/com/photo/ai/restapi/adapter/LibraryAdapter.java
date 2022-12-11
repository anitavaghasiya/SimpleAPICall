package com.photo.ai.restapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.photo.ai.restapi.R;
import com.photo.ai.restapi.model.Place;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    Context context;

    List<Place> libraryList;

    String count;



    String fromWhere;

    public LibraryAdapter(Context context, List<Place> libraryList, String fromWhere) {
        this.context = context;
        this.libraryList = libraryList;
        this.fromWhere = fromWhere;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_library, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_title_library.setText(libraryList.get(position).getPlaceName());

//        Glide.with(context).load(libraryList.get(position).getAfterImage()).addListener(new RequestListener<Drawable>() {
//            @Override
//            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                holder.iv_progressbar.setVisibility(View.GONE);
//                holder.rl.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down));
//                return false;
//            }
//
//            @Override
//            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                holder.iv_progressbar.setVisibility(View.GONE);
//                holder.rl.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down));
//                return false;
//            }
//        }).placeholder(R.color.gray).into(holder.iv_item_library);
//
//        holder.txt_title_library.setText(libraryList.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return libraryList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_title_library;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title_library = itemView.findViewById(R.id.txt_title_library);

        }
    }


}
