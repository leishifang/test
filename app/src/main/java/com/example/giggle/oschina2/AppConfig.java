package com.example.giggle.oschina2;

/**
 * Created by LeishiFang on 2015-10-29 20:20.
 */
@SuppressWarnings({"unused", "JavaDoc"})
public class AppConfig {
//
//    private final static String APP_CONFIG = "config";
//
//    public final static String CONF_COOKIE = "cookie";
//
//    public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";
//
//    public static final String KEY_LOAD_IMAGE = "KEY_LOAD_IMAGE";
//    public static final String KEY_NOTIFICATION_ACCEPT = "KEY_NOTIFICATION_ACCEPT";
//    public static final String KEY_NOTIFICATION_SOUND = "KEY_NOTIFICATION_SOUND";
//    public static final String KEY_NOTIFICATION_VIBRATION = "KEY_NOTIFICATION_VIBRATION";
//    public static final String KEY_NOTIFICATION_DISABLE_WHEN_EXIT = "KEY_NOTIFICATION_DISABLE_WHEN_EXIT";
//    public static final String KEY_CHECK_UPDATE = "KEY_CHECK_UPDATE";
//    public static final String KEY_DOUBLE_CLICK_EXIT = "KEY_DOUBLE_CLICK_EXIT";
//
//    public static final String KEY_TWEET_DRAFT = "KEY_TWEET_DRAFT";
//    public static final String KEY_NOTE_DRAFT = "KEY_NOTE_DRAFT";
//
//    public static final String KEY_FRITST_START = "KEY_FRIST_START";
//
//    public static final String KEY_NIGHT_MODE_SWITCH = "night_mode_switch";
//    // TODO: 2015-10-29 QQ登陆认证key
//    public static final String APP_QQ_KEY = "100942993";
//
//    // 默认存放图片的路径
//    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment
//            .getExternalStorageDirectory()
//            + File.separator
//            + "OSChina2"
//            + File.separator + "osc_img" + File.separator;
//
//    // 默认存放文件下载的路径
//    public final static String DEFAULT_SAVE_FILE_PATH = Environment
//            .getExternalStorageDirectory()
//            + File.separator
//            + "OSChina2"
//            + File.separator + "download" + File.separator;
//
//    private Context mContext;
//    private static AppConfig appConfig;
//
//    private AppConfig() {
//    }
//
//    public static AppConfig getAppConfig(Context context) {
//        if (appConfig == null) {
//            appConfig = new AppConfig();
//            appConfig.mContext = context;
//        }
//        return appConfig;
//    }
//
//    public static SharedPreferences getSharedPreferences(Context context) {
//        return PreferenceManager.getDefaultSharedPreferences(context);
//    }
//
//    // TODO: 2015-10-29 (3) proreties是干什么的
//    public String get(String key) {
//        Properties properties = get();
//        if (properties != null) {
//            return properties.getProperty(key);
//        }
//        return null;
//    }
//
//    public Properties get() {
//        Properties properties = new Properties();
//        FileInputStream fis = null;
//        File file = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
//        try {
//            fis = new FileInputStream(file.getPath() + File.separator + APP_CONFIG);
//            properties.load(fis);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (fis != null) {
//                    fis.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return properties;
//    }
//
//    private void setProps(Properties properties) {
//        File dir = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
//        File file = new File(dir, APP_CONFIG);
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(file);
//            properties.store(fos, null);
//            fos.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void set(Properties properties) {
//        Properties props = get();
//        props.putAll(properties);
//        setProps(props);
//    }
//
//    public void set(String key, String value) {
//        Properties props = get();
//        props.put(key, value);
//        set(props);
//    }
//
//    public void remove(String... keys) {
//        Properties props = get();
//        for (String key : keys) {
//            props.remove(key);
//        }
//        setProps(props);
//    }
}
