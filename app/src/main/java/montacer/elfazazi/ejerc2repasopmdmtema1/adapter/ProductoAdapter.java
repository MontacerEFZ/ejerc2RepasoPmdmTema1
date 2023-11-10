package montacer.elfazazi.ejerc2repasopmdmtema1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import montacer.elfazazi.ejerc2repasopmdmtema1.R;
import montacer.elfazazi.ejerc2repasopmdmtema1.modelos.Producto;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductVH>   {

    private List<Producto> objects; //elementos a mostrar
    private int resource; //vista a mostrar
    private Context context; //donde se mostrar

    public ProductoAdapter(List<Producto> objects, int resource, Context context) {
        this.objects = objects;
        this.resource = resource;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(context).inflate(resource, null);

        productView.setLayoutParams(
                new RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        return new ProductVH(productView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductVH holder, int position) {
        Producto producto = objects.get(position);



        holder.lbNombreProducto.setText(producto.getNombre());
        holder.lbPrecioProducto.setText(String.valueOf(producto.getPrecio()));
        holder.lbCantidadProducto.setText(String.valueOf(producto.getCantidad()));


        holder.fila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDelete(producto).show();
            }
        });
    }

    private AlertDialog confirmDelete(Producto product){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("borrar producto");
        builder.setCancelable(false);

        builder.setNegativeButton("cancelar", null);
        builder.setPositiveButton("borrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int position = objects.indexOf(product);
                objects.remove(product);
                notifyItemRemoved(position); //position da error, poner holder.getAdapterPosition(), el notify redibuja la lista

            }
        });

        return builder.create();
    }

    @Override
    public int getItemCount() {
        return objects.size();

    }

    public class ProductVH extends RecyclerView.ViewHolder {

        TextView lbCantidadProductos, lbImporteTotalProductos, lbNombreProducto, lbCantidadProducto, lbPrecioProducto;
        CardView fila;

        public ProductVH(@NonNull View itemView) {
            super(itemView);

            lbCantidadProductos = itemView.findViewById(R.id.txtTotalProductosProductView);
            lbImporteTotalProductos = itemView.findViewById(R.id.txtTotalImporteProductosProductView);
            lbNombreProducto = itemView.findViewById(R.id.txtNombreProductView);
            lbCantidadProducto = itemView.findViewById(R.id.txtCantidadProductView);
            lbPrecioProducto = itemView.findViewById(R.id.txtPrecioProductView);
            fila = itemView.findViewById(R.id.cvFilaProductView);

        }
    }
}
