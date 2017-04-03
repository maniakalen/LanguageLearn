package com.maniakalen.languagelearn;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AlphabetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AlphabetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlphabetFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "letter";

    // TODO: Rename and change types of parameters
    private String[] mLetterString;

    private OnFragmentInteractionListener mListener;

    public AlphabetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment AlphabetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlphabetFragment newInstance(String param1) {
        AlphabetFragment fragment = new AlphabetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String str = getArguments().getString(ARG_PARAM1);
            if (str != null) {
                mLetterString = str.split("\\|");
            }
        }
    }
    public void loadBitmap(int resId, ImageView imageView) {
        if (BitmapWorkerTask.AsyncDrawable.cancelPotentialWork(resId, imageView)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView, getResources());
            final BitmapWorkerTask.AsyncDrawable asyncDrawable =
                    new BitmapWorkerTask.AsyncDrawable(getResources(), BitmapFactory.decodeResource(null, resId), task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(resId);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_alphabet, container, false);

        TextView text = (TextView)v.findViewById(R.id.alphabet_letter);
        text.setText(getLetter());

        TextView word = (TextView)v.findViewById(R.id.alphabet_word);
        word.setText(getWord());

        ImageView img = (ImageView)v.findViewById(R.id.alphafoto);
        loadBitmap(getImageId(), img);

        return v;
    }

    private String getLetter()
    {
        return this.mLetterString.length > 0?this.mLetterString[0]:"";
    }

    private String getWord()
    {
        return this.mLetterString.length>1?this.mLetterString[1]:"";
    }

    private int getImageId()
    {
        return this.mLetterString.length>2?getResources().getIdentifier(this.mLetterString[2], "drawable", ((Activity)mListener).getPackageName()):0;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
