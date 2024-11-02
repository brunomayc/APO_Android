package br.com.bruno.apo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Informacoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_informacoes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.informacoes), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configurar a Toolbar como a ActionBar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolBarInfo);
        setSupportActionBar(myToolbar);
        // Voltar para MainActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}