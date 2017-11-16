package adapters;

/**
 * Created by JuanGuillermoGallego on 8/11/2017.
 */
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.*;
import entities.*;
import java.util.*;
import android.view.*;

import com.sistemasempresariales.afinal.mbmhob.R;

public class LibrosListAdapter extends ArrayAdapter<Libros> {

    private Context context;
    private List<Libros>librosList;

    public LibrosListAdapter(@NonNull Context context, List<Libros> librosList) {
        super(context, R.layout.libros_layout, librosList);
        this.context=context;
        this.librosList=librosList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.libros_layout,parent,false);

        TextView textViewTitulo = (TextView) view.findViewById(R.id.textViewLibrosTitulo);
        textViewTitulo.setText(librosList.get(position).getTitulo());
        TextView textViewAutor = (TextView) view.findViewById(R.id.textViewLibrosAutor);
        textViewAutor.setText(librosList.get(position).getAutor());
        TextView textViewReseña = (TextView) view.findViewById(R.id.textViewLibroReseña);
        textViewReseña.setText(librosList.get(position).getReseña());

        return view;
    }
}
