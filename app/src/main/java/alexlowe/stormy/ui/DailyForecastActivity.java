package alexlowe.stormy.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;

import alexlowe.stormy.R;
import alexlowe.stormy.adapters.DayAdapter;
import alexlowe.stormy.weather.Day;

public class DailyForecastActivity extends ListActivity {
    private Day[] mDays;
    //last section of this course has a video about using listviews w/o extending the ListActivity class
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_daily_forecast);
        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);


       DayAdapter adapter = new DayAdapter(this, mDays);
       setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String dayOfTheWeek = mDays[position].getDayOfTheWeek();
        String conditions = mDays[position].getSummary();
        String highTemp = mDays[position].getTemperatureMax() + "";
        String message = String.format("On %s, the high will be %s and it will be %s",dayOfTheWeek,highTemp,conditions);

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
