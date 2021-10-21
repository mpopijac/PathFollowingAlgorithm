package hr.mpopijac.services;

import hr.mpopijac.data.CursorData;
import hr.mpopijac.data.MoveDirection;
import hr.mpopijac.data.ResultData;
import hr.mpopijac.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathFollowingAlgorithmService {

    private static final Character START_CHARACTER = '@';

    private static final Character END_CHARACTER = 'x';

    private static final Character TURN_CHARACTER = '+';

    private static final String ALLOWED_NOT_LETTER_CHARACTERS = "@-+|";

    public ResultData findPath(List<String> fileLines) throws NoEndingCharacterException, NoStartingCharacterException, MultipleStartingCharacterException, MultiplePossiblePathsException, MultipleEndingCharacterException, NotAllowedCharacterException {
        final CursorData cursor = fillUpInitialCursorData(fileLines);
        final StringBuilder letters = new StringBuilder();
        final StringBuilder path = new StringBuilder();
        final List<String> visitedPathFields = new ArrayList<>();

        AlgorithmHelper.addVisitedField(visitedPathFields, cursor);
        path.append(cursor.getLetter());
        while (!END_CHARACTER.equals(cursor.getLetter())) {
            findNextLetter(cursor);
            path.append(cursor.getLetter());

            if (AlgorithmHelper.addVisitedField(visitedPathFields, cursor)
                    && Character.isLetter(cursor.getLetter())
                    && !END_CHARACTER.equals(cursor.getLetter())) {
                letters.append(cursor.getLetter());
            }
        }

        return new ResultData(letters.toString(), path.toString());
    }

    private void findNextLetter(CursorData cursor) throws NoEndingCharacterException, MultiplePossiblePathsException {
        if (cursor.getDirection() != null
                && cursor.getLetter() != TURN_CHARACTER
                && checkIfPathExist(cursor, cursor.getDirection())) {
            getCharacter(cursor, cursor.getDirection());
        } else if (isTurnCharacter(cursor)) {
            final List<MoveDirection> possibleDirections = Arrays.stream(MoveDirection.values())
                    .filter(direction -> !direction.equals(cursor.getDirection()))
                    .filter(direction -> !Objects.equals(direction.getOppositeDirection(), cursor.getDirection()))
                    .filter(direction -> checkIfPathExist(cursor, direction))
                    .collect(Collectors.toList());

            if (possibleDirections.size() == 1) {
                getCharacter(cursor, possibleDirections.get(0));
            } else if (possibleDirections.isEmpty()) {
                throw new NoEndingCharacterException("No ending character exception");
            } else {
                throw new MultiplePossiblePathsException("More than one possible path available.");
            }
        } else {
            throw new NoEndingCharacterException("No ending character exception");
        }
    }

    private void getCharacter(CursorData cursor, MoveDirection direction) {
        int x = cursor.getX() + direction.getX();
        int y = cursor.getY() + direction.getY();
        cursor.setDirection(direction);
        cursor.setX(x);
        cursor.setY(y);
        cursor.setLetter(cursor.getChars()[x][y]);
    }

    private boolean isTurnCharacter(CursorData cursor) {
        return cursor.getLetter() == START_CHARACTER
                || cursor.getLetter() == TURN_CHARACTER
                || Character.isLetter(cursor.getLetter());
    }

    private boolean checkIfPathExist(CursorData cursor, MoveDirection direction) {
        int x = cursor.getX() + direction.getX();
        int y = cursor.getY() + direction.getY();
        //Check if are coordinate out of the map
        if (x < 0 || x >= cursor.getChars().length || y < 0 || y >= cursor.getChars()[x].length) {
            return false;
        }

        char letter = cursor.getChars()[x][y];
        return Character.isLetter(letter)
                || ALLOWED_NOT_LETTER_CHARACTERS.contains(String.valueOf(letter));
    }

    private CursorData fillUpInitialCursorData(List<String> fileLines) throws NoStartingCharacterException, MultipleStartingCharacterException, MultipleEndingCharacterException, NotAllowedCharacterException {
        final char[][] charMap = new char[fileLines.size()][AlgorithmHelper.maxLineLength(fileLines)];
        final CursorData cursor = new CursorData();
        int numberOfEndChars = 0;
        for (int x = 0; x < fileLines.size(); x++) {
            char[] rowChars = fileLines.get(x).toCharArray();
            for (int y = 0; y < rowChars.length; y++) {
                final Character character = rowChars[y];
                if (Character.isWhitespace(character)) {
                    continue;
                }
                if (isNotAllowedCharacter(character)) {
                    throw new NotAllowedCharacterException("Found not allowed character " + character + " at position:[" + x + "][" + y + "]");
                }
                charMap[x][y] = character;

                if (START_CHARACTER.equals(character)) {
                    if (isStartPositionFound(cursor)) {
                        throw new MultipleStartingCharacterException("Multiple starting characters.");
                    }
                    cursor.setX(x);
                    cursor.setY(y);
                }

                if (END_CHARACTER.equals(character)) {
                    numberOfEndChars++;
                }
            }
        }
        if (!isStartPositionFound(cursor)) {
            throw new NoStartingCharacterException("No starting character.");
        }
        if (numberOfEndChars > 1) {
            throw new MultipleEndingCharacterException(numberOfEndChars + " ending characters.");
        }
        cursor.setChars(charMap);
        cursor.setLetter(cursor.getChars()[cursor.getX()][cursor.getY()]);
        return cursor;
    }

    private boolean isStartPositionFound(CursorData cursor) {
        return cursor.getX() != null && cursor.getY() != null;
    }

    private boolean isNotAllowedCharacter(Character character) {
        return !Character.isLetter(character)
                && !ALLOWED_NOT_LETTER_CHARACTERS.contains(String.valueOf(character));
    }

}
