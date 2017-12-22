package dqt.iuh.loinhac;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import dqt.iuh.loinhac.R;

public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
    
    // luu thong tin da cai dat
	@Override
	protected void onResume(){
		super.onResume();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		updatePreferences();
	}

	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}    

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		updatePreference(key);
	}
	
	//update 
	private void updatePreferences() {
		updatePreference(RemindMe.TIME_OPTION);
		updatePreference(RemindMe.DATE_RANGE);
		updatePreference(RemindMe.DATE_FORMAT);
		updatePreference(RemindMe.RINGTONE_PREF);
	}

	private void updatePreference(String key){
		Preference pref = findPreference(key);

	    if (pref instanceof ListPreference) {
	        ListPreference listPref = (ListPreference) pref;
	        pref.setSummary(listPref.getEntry());
	        return;
	    }		
	
		
	    if (RemindMe.RINGTONE_PREF.equals(key)) {
	    	Uri ringtoneUri = Uri.parse(RemindMe.getRingtone());
	    	Ringtone ringtone = RingtoneManager.getRingtone(this, ringtoneUri);
	        if (ringtone != null) pref.setSummary(ringtone.getTitle(this));
	    }		
	}


}
