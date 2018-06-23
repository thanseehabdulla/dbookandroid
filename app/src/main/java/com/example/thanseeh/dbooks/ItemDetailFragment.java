package com.example.thanseeh.dbooks;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thanseeh.dbooks.dummy.DummyContent;
import com.example.thanseeh.dbooks.retrofit.UserPojo;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";


    /**
     * The dummy content this fragment is presenting.
     */
    private String mItem;
    private String name;
    private String username;
    private String email;
    private String address;
    private String phone;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey("id")) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getString("id");
            name = getArguments().getString("name");
            username = getArguments().getString("username");
            email = getArguments().getString("email");
            phone = getArguments().getString("phone");
            address = getArguments().getString("address");

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(name);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.username)).setText("username: "+username);
            ((TextView) rootView.findViewById(R.id.name)).setText("name: "+name);
            ((TextView) rootView.findViewById(R.id.email)).setText("email: "+email);
            ((TextView) rootView.findViewById(R.id.phone)).setText("phone: "+phone);
            ((TextView) rootView.findViewById(R.id.address)).setText("address: "+address);
        }

        return rootView;
    }
}
