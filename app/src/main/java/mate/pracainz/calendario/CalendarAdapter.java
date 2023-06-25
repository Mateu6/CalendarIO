package mate.pracainz.calendario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Date> calendarDays;
    private Calendar currentDate;

    public CalendarAdapter(Context context, Calendar currentDate) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.currentDate = currentDate;

        // Utworzenie listy dat dla danego miesiąca
        calendarDays = new ArrayList<>();
        Calendar calendar = (Calendar) currentDate.clone();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int monthMaxDate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= monthMaxDate; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            calendarDays.add(calendar.getTime());
        }
    }

    @Override
    public int getCount() {
        return calendarDays.size();
    }

    @Override
    public Object getItem(int position) {
        return calendarDays.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_calendar, parent, false);
            holder = new ViewHolder();
            holder.dateTextView = convertView.findViewById(R.id.dateTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Date date = calendarDays.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        holder.dateTextView.setText(String.valueOf(dayOfMonth));

        return convertView;
    }

    private static class ViewHolder {
        TextView dateTextView;
    }
}

