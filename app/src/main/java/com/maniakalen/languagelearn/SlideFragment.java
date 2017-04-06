package com.maniakalen.languagelearn;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SlideFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SlideFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SlideFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "letter";

    // TODO: Rename and change types of parameters
    private String[] mStrings;

    private OnFragmentInteractionListener mListener;

    public SlideFragment() {
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
    public static SlideFragment newInstance(String param1) {
        SlideFragment fragment = new SlideFragment();
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
                mStrings = str.split("\\|");
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
        View v = inflater.inflate(R.layout.fragment_slide, container, false);

        TextView text = (TextView)v.findViewById(R.id.slide_char);
        text.setText(getChar());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak((String)((TextView)v).getText());
            }
        };
        text.setOnClickListener(listener);

        TextView word = (TextView)v.findViewById(R.id.slide_word);
        word.setText(getWord());
        word.setOnClickListener(listener);

        ImageView img = (ImageView)v.findViewById(R.id.slide_pic);
        loadBitmap(getImageId(), img);

        return v;
    }

    private String getChar()
    {
        return this.mStrings.length > 0?this.mStrings[0]:"";
    }

    private String getWord()
    {
        return this.mStrings.length>1?this.mStrings[1]:"";
    }

    private int getImageId()
    {
        return this.mStrings.length>2?getResources().getIdentifier(this.mStrings[2], "drawable", ((Activity)mListener).getPackageName()):0;
    }

    public void speak(String text){
        mListener.speak(text);
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
        void speak(String text);
    }
}
