
public class SquareArray<T> {
    /**
     * clockwise rotation
     */
    enum Rotation {
        ZERO, NINETY, ONE_EIGHTY, TWO_SEVENTY;
    }

    T[][] mArray;
    Rotation mRotation = Rotation.ZERO;

    /**
     *
     * @param array 2D array with uniform width and height in [row][column] format
     */
    public SquareArray(T[][] array) {
        mArray = array;
    }

    /**
     * rotate clockwise by actually copying the values
     */
    public void rotateByCopy() {
        int start = 0;
        int end = mArray.length - 1;

        int j;
        while (start < end) {
            j = start;
            for (int i = start; i < end; i++) {
                T temp = mArray[j][i];
                mArray[j][i] = mArray[end - i][j];
                mArray[end - i][j] = mArray[end - j][end - i];
                mArray[end - j][end - i] = mArray[i][end - j];
                mArray[i][end - j] = temp;
            }
            start++;
            end--;
        }
    }

    public void rotate(boolean clockwise) {
        if (clockwise) {
            switch (mRotation) {
                case ZERO:
                    mRotation = Rotation.NINETY;
                    break;
                case NINETY:
                    mRotation = Rotation.ONE_EIGHTY;
                    break;
                case ONE_EIGHTY:
                    mRotation = Rotation.TWO_SEVENTY;
                    break;
                case TWO_SEVENTY:
                    mRotation = Rotation.ZERO;
                    break;
            }
        } else {
            switch (mRotation) {
                case ZERO:
                    mRotation = Rotation.TWO_SEVENTY;
                    break;
                case NINETY:
                    mRotation = Rotation.ZERO;
                    break;
                case ONE_EIGHTY:
                    mRotation = Rotation.NINETY;
                    break;
                case TWO_SEVENTY:
                    mRotation = Rotation.ONE_EIGHTY;
                    break;
            }
        }
    }

    public T get(int row, int column) {
        int last = mArray.length - 1;
        switch (mRotation) {
            case ZERO:
                return mArray[row][column];
            case NINETY:
                return mArray[last - column][row];
            case ONE_EIGHTY:
                return mArray[last - row][last - column];
            case TWO_SEVENTY:
                return mArray[column][last - row];
        }
        return null;
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < mArray.length; i++) {
            System.out.print(get(i, 0));
            for (int j = 1; j < mArray.length; j++) {
                System.out.print(", " + get(i, j));
            }
            System.out.println();
        }
        System.out.println("]");
    }
}
