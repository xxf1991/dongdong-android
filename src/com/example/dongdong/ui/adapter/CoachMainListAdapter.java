package com.example.dongdong.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.example.dongdong.R;
import com.example.dongdong.model.BaseHttpManager;
import com.example.dongdong.model.coachmain.CoachMain;
import com.example.dongdong.model.coachmain.CoachMainData;
import com.example.dongdong.util.ImageCacheManager;

/**
 * @author deofly
 * @since 1.0 2014/11/24
 */
public class CoachMainListAdapter extends BaseHttpAdapter<CoachMain, CoachMainData> {

    public CoachMainListAdapter(Context context) {
        super(context, R.layout.item_coach_main);
    }

    @Override
    protected BaseHttpManager<CoachMainData> getHttpManger(BaseHttpManager<CoachMainData> httpManager) {
        return null;
    }

    @Override
    protected View getView(CoachMain entry, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_coach_main, parent, false);

            holder = new ViewHolder();
            holder.image = (NetworkImageView) view.findViewById(R.id.image);
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.gender = (ImageView) view.findViewById(R.id.gender);
            holder.rating = (RatingBar) view.findViewById(R.id.rating);
            holder.comment = (TextView) view.findViewById(R.id.comment);
            holder.jobType = (TextView) view.findViewById(R.id.jobtype);
            holder.location = (TextView) view.findViewById(R.id.location);
            holder.distance = (TextView) view.findViewById(R.id.distance);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (entry != null) {
            holder.image.setImageUrl(entry.getAvatarUrl(), ImageCacheManager.getInstance().getImageLoader());
            holder.name.setText(entry.getName());
            switch (entry.getGender()) {
                case CoachMain.Gender.MALE:
                    holder.gender.setImageResource(R.drawable.ic_gender_male);
                    break;
                case CoachMain.Gender.FEMALE:
                    holder.gender.setImageResource(R.drawable.ic_gender_female);
                    break;
                default:
                    break;
            }
            holder.rating.setRating(entry.getRating());
            holder.comment.setText(entry.getCommentCount() + "条评论");
            if (entry.getJobType() == CoachMain.JobType.FULL_TIME) {
                holder.jobType.setText("全职");
            } else {
                holder.jobType.setText("兼职");
            }
            holder.location.setText(entry.getLocation());
            holder.distance.setText(entry.getDistance());
        }

        return view;
    }

    public static class ViewHolder {

        public static enum Gender {
            MALE, FEMALE
        }

        public static enum JobType {
            FULL_TIME, HALF_TIME
        }

        public NetworkImageView image;
        public TextView name;
        public ImageView gender;
        public RatingBar rating;
        public TextView comment;
        public TextView jobType;
        public TextView location;
        public TextView distance;
    }
}
