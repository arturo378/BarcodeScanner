package e.lotz3.barcodescanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends ArrayAdapter<Item> {

    private static final String TAG = "ItemListAdapter";

    private Context mContext;
    int mResource;

    /**
     * Default constructor for the PersonListAdapter
     *
     * @param context
     * @param resource
     * @param objects
     */
    public ItemListAdapter(Context context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String category = getItem(position).getCategory();
        String quantity = getItem(position).getQuantity();
        String model = getItem(position).getModel();

        Item item = new Item(category, quantity, model);


        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvQuantity = (TextView) convertView.findViewById(R.id.textView3);
        TextView tvCategory = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvModel = (TextView) convertView.findViewById(R.id.textView1);

        tvQuantity.setText(quantity);
        tvCategory.setText(category);
        tvModel.setText(model);

        return convertView;

    }
}
