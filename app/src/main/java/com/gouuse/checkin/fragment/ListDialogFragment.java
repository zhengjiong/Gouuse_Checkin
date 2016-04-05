package com.gouuse.checkin.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gouuse.checkin.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhengjiong on 15/10/30.
 */
public class ListDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener{

    String[] items;
    int selectedPosition;
    AdapterView.OnItemClickListener onItemClickListener;

    public ListDialogFragment() {
    }

    public ListDialogFragment(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Bind(R.id.listview)
    ListView listview;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (this.onItemClickListener != null) {
            onItemClickListener.onItemClick(parent, view, position, id);
            dismiss();
        }
    }

    class RepeatAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public String getItem(int position) {
            return items[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(getActivity()).inflate(R.layout.simple_list_item_green, null);
            TextView textView = (TextView) convertView.findViewById(R.id.item);
            if (position == selectedPosition) {
                textView.setTextColor(Color.parseColor("#ff7aa843"));
            } else {
                textView.setTextColor(Color.parseColor("#ff332e2e"));
            }
            textView.setText(items[position]);
            return convertView;
        }
    }


    static public ListDialogFragment newInstance(String[] items, int selectedPosition, AdapterView.OnItemClickListener onItemClickListener) {
        Bundle args = new Bundle();
        ListDialogFragment fragment = new ListDialogFragment(onItemClickListener);
        fragment.setArguments(args);

        args.putStringArray("items", items);
        args.putInt("selected_position", selectedPosition);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        //dialog.setTitle("title");
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.no_title_dialog);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.no_title_dialog);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //getDialog().setTitle("My Dialog Title");

        View view = inflater.inflate(R.layout.list_dialog_fragment, container);
        ButterKnife.bind(this, view);
        this.items = getArguments().getStringArray("items");
        this.selectedPosition = getArguments().getInt("selected_position", -1);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RepeatAdapter adapter = new RepeatAdapter();
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
