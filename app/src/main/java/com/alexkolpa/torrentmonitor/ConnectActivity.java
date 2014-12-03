package com.alexkolpa.torrentmonitor;

import java.net.URISyntaxException;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

public class ConnectActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connect);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment())
					.commit();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.connect, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		return id == R.id.action_settings || super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		private static final String URI = "10.0.0.105:3000";
		Socket socket;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_connect, container, false);
		}

		@Override
		public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
			super.onViewCreated(view, savedInstanceState);

			try {
				socket = IO.socket(URI);
			}
			catch (URISyntaxException e) {
				Toast.makeText(getActivity(), "Failed to connect to socket at '" + URI + "'", Toast.LENGTH_SHORT)
						.show();
			}

			view.findViewById(R.id.socket_connect).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					socket.connect();

					Toast.makeText(getActivity(), "Connected to socket at '" + URI + "'", Toast.LENGTH_SHORT)
							.show();
				}
			});

			view.findViewById(R.id.sockect_disconnect).setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					socket.disconnect();

					Toast.makeText(getActivity(), "Disconnected from socket at '" + URI + "'", Toast.LENGTH_SHORT)
							.show();
				}
			});
		}
	}
}
