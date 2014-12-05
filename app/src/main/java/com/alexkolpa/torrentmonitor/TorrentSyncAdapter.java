package com.alexkolpa.torrentmonitor;

import android.accounts.Account;
import android.annotation.TargetApi;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;

/**
 * Handle the transfer of data between a server and an app, using the Android sync adapter framework.
 */
public class TorrentSyncAdapter extends AbstractThreadedSyncAdapter {
	// Global variables
	// Define a variable to contain a content resolver instance
	ContentResolver mContentResolver;

	/**
	 * Set up the sync adapter
	 */
	public TorrentSyncAdapter(Context context, boolean autoInitialize) {
		super(context, autoInitialize);
	}

	/**
	 * Set up the sync adapter. This form of the constructor maintains compatibility with Android 3.0 and later
	 * platform
	 * versions
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public TorrentSyncAdapter(
			Context context,
			boolean autoInitialize,
			boolean allowParallelSyncs) {
		super(context, autoInitialize, allowParallelSyncs);
	}

	@Override
	public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider,
			SyncResult syncResult) {

		
	}
}
