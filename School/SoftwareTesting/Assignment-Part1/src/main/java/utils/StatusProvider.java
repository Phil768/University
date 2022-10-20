package utils;

public interface StatusProvider {
    public static int ONLINE = 0;
    public static int OFFLINE = 1;

    public int getStatusProvider();
}
