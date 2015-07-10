package alexlowe.stormy.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import alexlowe.stormy.R;
import alexlowe.stormy.weather.Hour;

/**
 * Created by Alex on 6/22/2015.
 */
public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {
   private Hour[] mHours;
   private Context mContext; //context added to use in the toast, also used in constructor below

   // constructor, will let us create it in the activity and then set it's data
        public HourAdapter(Context context, Hour[] hours){
            mHours = hours;
            mContext = context;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hourly_list_item, parent, false);
        HourViewHolder viewHolder = new HourViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.bindHour(mHours[position]);
    }

    @Override
    public int getItemCount() {
        return mHours.length;
    }

    public class HourViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        public TextView mTimeLabel;
        public TextView mSummaryLabel;
        public TextView mTemperatureLabel;
        public ImageView mIconImageView;

        public HourViewHolder(View itemView) {
            super(itemView);

            mTimeLabel =(TextView) itemView.findViewById(R.id.timeLabel);
            mSummaryLabel =(TextView) itemView.findViewById(R.id.summaryLabel);
            mTemperatureLabel =(TextView) itemView.findViewById(R.id.temperatureLabel);
            mIconImageView =(ImageView) itemView.findViewById(R.id.iconImageView);

            itemView.setOnClickListener(this); //has to be done with recycler views
        }

        public void bindHour(Hour hour){
            mTimeLabel.setText(hour.getHour());
            mSummaryLabel.setText(hour.getSummary());
            mTemperatureLabel.setText(hour.getTemperature() + "");
            mIconImageView.setImageResource(hour.getIconId());

        }

        @Override
        public void onClick(View v) {
            String time = mTimeLabel.getText().toString();
            String temp = mTemperatureLabel.getText().toString();
            String summary = mSummaryLabel.getText().toString();
            String message = String.format("At %s, the high will be %s and it will be %s",time, temp, summary);

            Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
        }
    }
}
