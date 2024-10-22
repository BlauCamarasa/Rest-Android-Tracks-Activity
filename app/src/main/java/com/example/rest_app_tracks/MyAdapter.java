package com.example.rest_app_tracks;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.content.Intent;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<Track> results;
    private Context context;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Look up at row_layout and see we have 2 TextView for name of the song and artist and 1
        // ImageView for the photo of the song.
        public TextView SongText;
        public TextView SingerText;
        public View layout;
        public RelativeLayout itemcontainer;


        //View class is the superclass for all UI components like buttons, text fields, images, etc.
        // It provides the basic functionality for handling what you see on the screen and
        // interacts with user input, such as touch events.
        public ViewHolder(View v) {
            super(v);
            layout = v;
            //Save the id from row_layout.xml to the variables created
            SongText = (TextView) v.findViewById(R.id.Song);
            SingerText = (TextView) v.findViewById(R.id.Singer);
            itemcontainer = (RelativeLayout) v.findViewById(R.id.item_container);
        }
    }
    public void setData(List<Track> myDataset) {
        results = myDataset;
        //notify the associated UI component (like a RecyclerView or ListView) that the underlying
        // data has changed. This triggers the UI to refresh and update its displayed content
        // based on the new data.
        notifyDataSetChanged();
    }
    public void add(int position, Track newSong) {
        //This line adds the new item (Contributor) at the specified position in the values list.
        results.add(position, newSong);
        //inform the RecyclerView that a new item has been inserted at the specified position.
        //This method ensures that only the new item is inserted into the view, and the rest
        //of the list remains unchanged.
        notifyItemInserted(position);
    }

    public void remove(int position) {
        results.remove(position);
        notifyItemRemoved(position);
    }
    public MyAdapter(Context context){
        this.context=context;
        results = new ArrayList<>();
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Track t = results.get(position);
        final String title = t.title;
        final String singer = t.singer;
        final String id = t.getId();
        holder.SongText.setText("Song: "+ title);
        holder.SingerText.setText("Singer: "+ singer);
        holder.itemcontainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateTrack.class);
                intent.putExtra("keyForStringId", id);
                intent.putExtra("keyForStringTitle", title);
                intent.putExtra("keyForStringSinger", singer);
                context.startActivity(intent);

                String msg = "Funciona";
                Log.d("Click item: ",msg);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return results.size();
    }


}
