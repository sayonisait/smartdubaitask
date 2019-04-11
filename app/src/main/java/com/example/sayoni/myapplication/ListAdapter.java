package com.example.sayoni.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sayoni.myapplication.network.ArticleListResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>  {

    private ArrayList<ArticleListResponse.Result> items;
    private onItemSelectionInterface mListener;
    private Activity context;
   public interface onItemSelectionInterface
   {
       void onItemClick(ArticleListResponse.Result selectedItem);
   }


    public ListAdapter(ArrayList<ArticleListResponse.Result> items,
                       onItemSelectionInterface listener,
                        Activity context) {
        this.items=items;
        this.mListener=listener;
        this.context=context;
    }





    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return
                new ListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.search_result_list_row, parent,false))  ;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAdapter.ViewHolder viewHolder, final int position) {

        final ArticleListResponse.Result item = items.get(viewHolder.getAdapterPosition());
        viewHolder.txtTitle.setText(item.title);
        viewHolder.txtAuthor.setText(item.byline);
        viewHolder.txtDate.setText(item.published_date);

        Picasso.get().load(item.media.get(0).list.get(0).url).fit()
                .placeholder(R.drawable.ic_list) .error(R.drawable.ic_list).noFade()
                .into(viewHolder.imageView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(item);
            }
        });

        }





    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {

        TextView txtTitle, txtAuthor, txtDate ;
        ImageView imageView;
        LinearLayout addToCartLayout;
        TextView txtBuy;
        ProgressBar progressBar;
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtTitle =  itemLayoutView.findViewById(R.id.text_title);
            imageView =  itemLayoutView.findViewById(R.id.img);
            txtAuthor =  itemLayoutView.findViewById(R.id.text_author);
            txtDate =  itemLayoutView.findViewById(R.id.text_date);

        }
    }
}
