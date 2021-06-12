package flowcontrol.article.util;

public class HandyFunctions {

    public static boolean isEmpty(final String s) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }
}
