package mate.pracainz.calendario;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView monthTextView;
    private EditText yearEditText;
    private GridView calendarGridView;
    private CalendarAdapter calendarAdapter;
    private Calendar currentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthTextView = findViewById(R.id.monthTextView);
        yearEditText = findViewById(R.id.yearEditText);
        calendarGridView = findViewById(R.id.calendarGridView);

        currentDate = Calendar.getInstance();

        // Ustawienie tekstu dla etykiety miesiąca
        updateMonthLabel();

        // Inicjalizacja adaptera i ustawienie go dla GridView
        calendarAdapter = new CalendarAdapter(this, currentDate);
        calendarGridView.setAdapter(calendarAdapter);

        // Obsługa kliknięcia na element GridView
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Date selectedDate = (Date) calendarAdapter.getItem(position);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String dateText = sdf.format(selectedDate);
                // Tutaj możesz dodać logikę obsługi kliknięcia na konkretny dzień kalendarza
            }
        });
    }

    private void updateMonthLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        String monthYear = sdf.format(currentDate.getTime());
        monthTextView.setText(monthYear);
    }

    public void previousMonth(View view) {
        currentDate.add(Calendar.MONTH, -1);
        updateMonthLabel();
        calendarAdapter = new CalendarAdapter(this, currentDate);
        calendarGridView.setAdapter(calendarAdapter);
    }

    public void nextMonth(View view) {
        currentDate.add(Calendar.MONTH, 1);
        updateMonthLabel();
        calendarAdapter = new CalendarAdapter(this, currentDate);
        calendarGridView.setAdapter(calendarAdapter);
    }

    public void goToMonth(View view) {
        String yearString = yearEditText.getText().toString();
        if (!yearString.isEmpty()) {
            int year = Integer.parseInt(yearString);

            currentDate.set(Calendar.YEAR, year);
            updateMonthLabel();
            calendarAdapter = new CalendarAdapter(this, currentDate);
            calendarGridView.setAdapter(calendarAdapter);
        }
    }
}




