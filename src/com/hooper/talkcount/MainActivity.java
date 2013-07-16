package com.hooper.talkcount;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	/*
	 * public String getFirst(Intent data) { List<String> results = data
	 * .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); if (results !=
	 * null && results.size() > 0) { return results.get(0); } return null; // or
	 * maybe: return ""; }
	 */

	private TextView mText;
	public TextView resulty;
	public TextView made;
	public TextView miss;
	public int shotsMade = 0;
	public int shotsMiss = 0;

	public String getFirst;
	private SpeechRecognizer sr;
	private static final String TAG = "MyStt3Activity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button speakButton = (Button) findViewById(R.id.btn_speak);
		mText = (TextView) findViewById(R.id.textView1);
		resulty = (TextView) findViewById(R.id.tvresulty);
		made = (TextView) findViewById(R.id.tvMake);
		miss = (TextView) findViewById(R.id.tvMiss);
		speakButton.setOnClickListener(this);
		sr = SpeechRecognizer.createSpeechRecognizer(this);
		sr.setRecognitionListener(new listener());
	}

	class listener implements RecognitionListener {
		public void onReadyForSpeech(Bundle params) {
			Log.d(TAG, "onReadyForSpeech");
		}

		public void onBeginningOfSpeech() {
			Log.d(TAG, "onBeginningOfSpeech");
		}

		public void onRmsChanged(float rmsdB) {
			Log.d(TAG, "onRmsChanged");
		}

		public void onBufferReceived(byte[] buffer) {
			Log.d(TAG, "onBufferReceived");
		}

		public void onEndOfSpeech() {
			Log.d(TAG, "onEndofSpeech");
		}

		public void onError(int error) {
			Log.d(TAG, "error " + error);
			mText.setText("error " + error);
		}

		public void onResults(Bundle results) {

			String str = new String();
			String first = "make";
			String first1 = "buckets";
			String first3 = "that's in";
			String second = "miss";
			String second2 = "I suck";
			// this is just a log that prints the results
			Log.d(TAG, "onResults " + results);

			ArrayList<String> data = results
					.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

			for (int i = 0; i < data.size(); i++) {
				Log.d(TAG, "result " + data.get(i));
				str += data.get(i);
			}
			;
			if ((data.contains(first)) || (data.contains(first1))
					|| (data.contains(first3))) {
				shotsMade++;
				resulty.setText("Yess!");
				made.setText("Makes: " + shotsMade);

			} else if ((data.contains(second)) || (data.contains(second2))) {
				shotsMiss++;
				resulty.setText("Booo");
				miss.setText("Misses: " + shotsMiss);
			}

			mText.setText("results: " + String.valueOf(data.size()));

			while (shotsMade < 10) {

			}

		}

		public void onPartialResults(Bundle partialResults) {
			Log.d(TAG, "onPartialResults");
		}

		public void onEvent(int eventType, Bundle params) {
			Log.d(TAG, "onEvent " + eventType);

		}
	}

	public void onClick(View v) {
		if (v.getId() == R.id.btn_speak) {

			Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
					RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
					"voice.recognition.test");

			intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
			sr.startListening(intent);
			Log.i("111111", "11111111");

		}
	}
}
