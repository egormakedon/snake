package by.makedon.snake.validator;

import by.makedon.snake.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yahor Makedon
 */
public final class Validator {
    public static void validateSnakeStartPosition(String snakeStartPosition, String[] snakeStartPositionArray) {
        final String ERROR_MESSAGE_TEMPLATE = String
                .format("%s=%s - %%s", Constants.SNAKE_START_POSITION, snakeStartPosition);

        if (snakeStartPositionArray.length < 4
                || snakeStartPositionArray.length % 2 != 0) {
            throw new IllegalArgumentException(
                    String.format(ERROR_MESSAGE_TEMPLATE, "Number of coordinates should be even (minimum 4). Delimiter '|'"));
        }

        List<Integer> coordinateList = new ArrayList<>(snakeStartPositionArray.length);
        try {
            for (String coordinateString : snakeStartPositionArray) {
                int coordinate = Integer.parseInt(coordinateString);

                if (coordinate < 0) {
                    throw new IllegalArgumentException(
                            String.format(ERROR_MESSAGE_TEMPLATE, "Coordinate should be >= 0"));
                }

                coordinateList.add(coordinate);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    String.format(ERROR_MESSAGE_TEMPLATE, "Coordinate should be integer"));
        }

        for (int i = 0; i + 3 < coordinateList.size(); i+=2) {
            int x1 = coordinateList.get(i);
            int y1 = coordinateList.get(i + 1);
            int x2 = coordinateList.get(i + 2);
            int y2 = coordinateList.get(i + 3);

            if (Math.abs((x1 + y1) - (x2 + y2)) != 1) {
                throw new IllegalArgumentException(
                        String.format(ERROR_MESSAGE_TEMPLATE, "Invalid snake coordinates configuration"));
            }
        }
    }

    private Validator() {
    }
}
