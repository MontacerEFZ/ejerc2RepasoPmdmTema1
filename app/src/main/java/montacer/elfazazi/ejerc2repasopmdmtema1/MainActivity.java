package montacer.elfazazi.ejerc2repasopmdmtema1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import montacer.elfazazi.ejerc2repasopmdmtema1.adapter.ProductoAdapter;
import montacer.elfazazi.ejerc2repasopmdmtema1.databinding.ActivityMainBinding;
import montacer.elfazazi.ejerc2repasopmdmtema1.modelos.Producto;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> launcherAddProducto;
    private ArrayList<Producto> listaProductos;
    private ProductoAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ProductoAdapter(listaProductos, R.layout.product_view_tablamain, MainActivity.this); //adapter siempre despues de inicializar el arraylist
        //este es el contructor del ProductAdapter, le pasamos: la lista, que vista queremos mostrar q es el resource y donde queremos mostrarla que es en el main

        layoutManager = new GridLayoutManager(this, 1); //mostrara las cosas en columnas de 2

        binding.contentMain.container.setAdapter(adapter);
        binding.contentMain.container.setLayoutManager(layoutManager);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                launcherAddProducto.launch(new Intent(MainActivity.this, AddProductoActivity.class));
            }
        });

        listaProductos = new ArrayList<>();
        inicializarLaunchers();
}

    private void inicializarLaunchers() {
        launcherAddProducto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK){
                    if (result.getData() != null && result.getData().getExtras() != null){
                        Producto producto = (Producto) result.getData().getExtras().getSerializable("PRODUCTO");
                        listaProductos.add(0,producto);
                       adapter.notifyItemInserted(0);
                    }
                }else{
                    Toast.makeText(MainActivity.this, "accion cancelada", Toast.LENGTH_SHORT).show();
                }
            }
        });
     }
    }