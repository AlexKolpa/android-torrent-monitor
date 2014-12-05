package com.alexkolpa.torrentmonitor;

import java.net.URISyntaxException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class ConnectActivity extends ActionBarActivity {

	// Constants
	// The authority for the sync adapter's content provider
	public static final String AUTHORITY = "com.example.android.datasync.provider";
	// An account type, in the form of a domain name
	public static final String ACCOUNT_TYPE = "baft.asuscomm.com";
	// The account name
	public static final String ACCOUNT = "dummyaccount";
	// Instance fields
	Account mAccount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connect);

		mAccount = createSyncAccount(this);

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
	 * Create a new dummy account for the sync adapter
	 *
	 * @param context The application context
	 */
	private static Account createSyncAccount(Context context) {
		// Create the account type and default account
		Account newAccount = new Account(
				ACCOUNT, ACCOUNT_TYPE);
		// Get an instance of the Android account manager
		AccountManager accountManager =
				(AccountManager) context.getSystemService(
						ACCOUNT_SERVICE);
        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
		if (accountManager.addAccountExplicitly(newAccount, null, null)) {
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call context.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */
			return newAccount;
		} else {
            /*
             * The account exists or some other error occurred. Log this, report it,
             * or handle it internally.
             */
			Log.e("ConnectActivity", "Account already exists");
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_connect, container, false);
		}
	}
}
