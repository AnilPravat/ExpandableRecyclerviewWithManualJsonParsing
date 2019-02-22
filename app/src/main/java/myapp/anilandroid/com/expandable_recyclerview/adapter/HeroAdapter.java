package myapp.anilandroid.com.expandable_recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import myapp.anilandroid.com.expandable_recyclerview.R;
import myapp.anilandroid.com.expandable_recyclerview.model.Hero;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {

    private List<Hero> heroList;
    private Context context;


    private static int currentPosition=0;

    public HeroAdapter(List<Hero> heroList, Context context) {
        this.heroList = heroList;
        this.context = context;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.list_layout_heroes,parent,false );
        return new HeroViewHolder( view );
    }

    @Override
    public void onBindViewHolder(HeroViewHolder holder, final int position) {

        Hero hero=heroList.get( position );

        holder.name.setText( hero.getName() );
        holder.realname.setText( hero.getRealname() );
        holder.team.setText( hero.getTeam() );
        holder.firstappearance.setText( hero.getFirstappearance() );
        holder.createdby.setText( hero.getCreatedby() );
        holder.publisher.setText( hero.getPublisher() );
        holder.bio.setText( hero.getBio().trim() );

        Glide.with(context).load(hero.getImageurl()).into(holder.imageView);


        holder.linearLayout.setVisibility( View.GONE );

        //if the position is equals to the item position which is to be expanded.
        if (currentPosition==position){
            Animation slideDown= AnimationUtils.loadAnimation( context,R.anim.anim_design );
            //toggling visibility
            holder.linearLayout.setVisibility( View.VISIBLE );

            //adding sliding effect
            holder.linearLayout.startAnimation( slideDown );

        }

        holder.name.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting the position of the item to expand it
                currentPosition=position;

                //reading the list..
                notifyDataSetChanged();

            }
        } );


    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public class HeroViewHolder extends RecyclerView.ViewHolder {

        TextView name,realname,team,firstappearance,createdby,publisher,bio;

        ImageView imageView;
        LinearLayout linearLayout;
        public HeroViewHolder(View itemView) {
            super( itemView );

            name=(TextView) itemView.findViewById( R.id.textviewname );
            realname=(TextView) itemView.findViewById( R.id.tv_realname);
            team=(TextView) itemView.findViewById( R.id.tv_realname );
            firstappearance=(TextView) itemView.findViewById( R.id.tv_firstapperance );
            createdby=(TextView) itemView.findViewById( R.id.tv_createdby );
            publisher=(TextView) itemView.findViewById( R.id.tv_publisher );
            bio=(TextView) itemView.findViewById( R.id.tv_bio );
            imageView=(ImageView)itemView.findViewById( R.id.imageview );

            linearLayout=(LinearLayout) itemView.findViewById( R.id.Linear_layout );
        }
    }
}
