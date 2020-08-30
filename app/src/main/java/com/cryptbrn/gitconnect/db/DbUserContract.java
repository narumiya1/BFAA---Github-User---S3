package com.cryptbrn.gitconnect.db;

import android.net.Uri;
import android.provider.BaseColumns;
import static com.cryptbrn.gitconnect.db.DbUserContract.UserColumns.TABLE_NAME;

public class DbUserContract {

    public static final String AUTHORITY = "com.cryptbrn.gitconnect";
    public static final String SCHEME = "content";

    public static final class UserColumns implements BaseColumns{
        public static final String TABLE_NAME = "fav_user";
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String URLREPOS = "urlrepos";
        public static final String AVATAR = "avatar";
        public static final String TYPE = "type";

    }
    public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

}
