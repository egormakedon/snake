package by.makedon.snake.domain;

import java.util.Objects;

/**
 * @author Yahor Makedon
 */
public class Pixel {
    private int x;
    private int y;
    private PixelType pixelType;

    public Pixel() {
    }

    public Pixel(int x, int y, PixelType pixelType) {
        this.x = x;
        this.y = y;
        this.pixelType = pixelType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return x == pixel.x &&
                y == pixel.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PixelType getPixelType() {
        return pixelType;
    }

    public void setPixelType(PixelType pixelType) {
        this.pixelType = pixelType;
    }
}
