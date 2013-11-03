package nl.garvelink.oss.slidingfragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by barend on 1-11-13.
 */
public class UnderFragment extends Fragment implements View.OnClickListener {

    public UnderFragment() {
        // Mandatory no-arg constructor.
    }

    private Callback callback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_under, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.under_button).setOnClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        this.callback = (Callback) activity;
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        this.callback = null;
        super.onDetach();
    }

    @Override
    public void onClick(View view) {
        callback.onButtonPressed();
    }

    public interface Callback {
        void onButtonPressed();
    }
}
