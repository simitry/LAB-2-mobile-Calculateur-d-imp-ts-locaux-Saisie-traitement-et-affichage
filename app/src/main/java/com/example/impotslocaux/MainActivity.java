package com.example.impotslocaux;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText surfaceInput;
    private EditText piecesInput;
    private CheckBox piscineCheckbox;
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceInput = findViewById(R.id.input_surface);
        piecesInput = findViewById(R.id.input_pieces);
        piscineCheckbox = findViewById(R.id.checkbox_piscine);
        resultView = findViewById(R.id.result);

        findViewById(R.id.button_calcul).setOnClickListener(v -> calculer());
    }

    private void calculer() {
        String surfaceText = surfaceInput.getText().toString().trim();
        String piecesText = piecesInput.getText().toString().trim();

        if (surfaceText.isEmpty() || piecesText.isEmpty()) {
            resultView.setText(R.string.input_error);
            return;
        }

        double surface;
        int pieces;

        try {
            surface = Double.parseDouble(surfaceText.replace(',', '.'));
            pieces = Integer.parseInt(piecesText);
        } catch (NumberFormatException exception) {
            resultView.setText(R.string.format_error);
            return;
        }

        boolean piscine = piscineCheckbox.isChecked();

        double impotBase = surface * 2;
        double supplement = pieces * 50 + (piscine ? 100 : 0);
        double total = impotBase + supplement;

        resultView.setText(String.format(Locale.FRANCE, getString(R.string.result_format), total));
    }
}
