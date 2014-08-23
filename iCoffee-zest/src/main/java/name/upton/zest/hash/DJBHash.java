package name.upton.zest.hash;


public class DJBHash{

    public static long DJB_HASH(String value) {
        long hash = 5381;

        for (int i = 0; i < value.length(); i++) {
            hash = ((hash << 5) + hash) + value.charAt(i);
        }

        return hash;
    }

    public static long hash(String routing) {
        return DJB_HASH(routing);
    }

    public static int hash(String type, String id) {
        long hash = 5381;

        for (int i = 0; i < type.length(); i++) {
            hash = ((hash << 5) + hash) + type.charAt(i);
        }

        for (int i = 0; i < id.length(); i++) {
            hash = ((hash << 5) + hash) + id.charAt(i);
        }

        return (int) hash;
    }
}
