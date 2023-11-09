package montacer.elfazazi.ejerc2repasopmdmtema1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import montacer.elfazazi.ejerc2repasopmdmtema1.databinding.ActivityAddProductoBinding;
import montacer.elfazazi.ejerc2repasopmdmtema1.modelos.Producto;

public class AddProductoActivity extends AppCompatActivity {

    private ActivityAddProductoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddProductoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnCancelarAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto producto = crearProducto();

                if (producto == null){
                    Toast.makeText(AddProductoActivity.this, "faltan datos", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PRODUCTO", producto);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });
    }

    private Producto crearProducto() {
        if (binding.txtNombreAddProducto.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtPrecioAddProducto.getText().toString().isEmpty()){
            return null;
        }
        if (binding.txtCantidadAddProducto.getText().toString().isEmpty()){
            return null;
        }

        Producto producto = new Producto(binding.txtNombreAddProducto.getText().toString(),
                Integer.parseInt(binding.txtPrecioAddProducto.getText().toString()),
                Integer.parseInt(binding.txtCantidadAddProducto.getText().toString()));

        return producto;
    }
}