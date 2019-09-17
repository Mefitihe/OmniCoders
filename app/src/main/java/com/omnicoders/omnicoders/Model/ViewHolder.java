package com.omnicoders.omnicoders.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omnicoders.omnicoders.R;


public class ViewHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "DataActivity";

    private DatabaseReference mDatabase;

    public ViewHolder(View itemView) {
        super(itemView);

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    //set promotion details to Recycler view row

    public void setData(final String title, final String content) {

        TextView textTitle = itemView.findViewById(R.id.txt_view);


        //final ImageView img = itemView.findViewById(R.id.img_view);

        //set the title to TextView
        textTitle.setText(title);


        //Downloading image for offline use
        //Check network is available or not
       /* Picasso.with(c)
                .load(imgUrl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(img, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.d("Firebase", "Successfully downloaded firebase data");
                    }

                    @Override
                    public void onError() {
                        Picasso.with(c).load(imgUrl).into(img);
                    }
                });
*/
    }


}

