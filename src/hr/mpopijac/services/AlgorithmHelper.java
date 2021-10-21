package hr.mpopijac.services;

import hr.mpopijac.data.CursorData;

import java.util.List;
import java.util.OptionalInt;

public final class AlgorithmHelper {

    private AlgorithmHelper() {
    }

    /**
     * Add cursor coordinate to list visitedPathFields.
     *
     * @param visitedPathFields
     * @param cursor
     * @return true if coordinates are added to visitedPathFields list. If coordinates are already in the list, then returns false.
     */
    public static boolean addVisitedField(List<String> visitedPathFields, CursorData cursor) {
        final String field = "[" + cursor.getX() + "][" + cursor.getY() + "]";
        if (!visitedPathFields.contains(field)) {
            visitedPathFields.add(field);
            return true;
        }
        return false;
    }

    public static int maxLineLength(List<String> lines) {
        final OptionalInt maxLineLength = lines.stream()
                .mapToInt(String::length)
                .max();
        return maxLineLength.orElse(0);
    }
}
