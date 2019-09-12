# Read the preferences:
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            String username = prefs.getString("username", "Default NickName");
            boolean updates = prefs.getBoolean("applicationUpdates", false);
            String st = prefs.getString("downloadDetials", "1");
            int downloadDetialsNumber = 1;
            try {
                //int downloadDetialsNumber = prefs.getInt("downloadDetials", 1);
                downloadDetialsNumber = Integer.parseInt(st);
            }
            catch (Exception ex)
            {
                Toast.makeText(getBaseContext(), "Cannot convert " + st + " to int", Toast.LENGTH_SHORT).show();
            }

            StringBuilder builder = new StringBuilder();
            builder.append("Username: " + username + "\n");
            builder.append("Updates: " + updates+ "\n");
            builder.append("Download details: " + getResources().getStringArray(R.array.listArray)[downloadDetialsNumber - 1]);
            TextView sttv = findViewById(R.id.settingsTV);
            sttv.setText(builder.toString());
