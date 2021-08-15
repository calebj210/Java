import java.util.Arrays;

public class MyArray {
    private int[] array = new int[0];

    public MyArray(int size) {
        array = new int[size];
    }

    public int get(int index) {
        return array[index];
    }

    public void set(int index, int value) {
        if (index < array.length){
            array[index] = value;
        } else {
            int[] temp = new int[index + array.length];
            temp[index] = value;
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }

            array = temp;
        }
    }

    public int length() {
        return array.length;
    }

    public static void main(String[] args) {
        MyArray obj1 = new MyArray(10);

        // Define array
        for (int i = 0; i < 10; i++) {
            obj1.set(i,i);
        }

        MyArray obj2 = new MyArray(10);

        // Define array
        for (int i = 0; i < 10; i++) {
            obj2.set(i,i);
        }

        // Print array
        System.out.println("array 1 = " + obj1.toString());
        System.out.println("array 2 = " + obj2.toString());

        System.out.println("array 1 = array 2 is " + obj1.equals(obj2));
        System.out.println("length of array 1 is " + obj1.length());

        obj1.set(12, 12);
        System.out.println("Array 1 is now " + obj1.toString());
        System.out.println("Length of array 1 is now " + obj1.length());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyArray)) return false;
        MyArray myArray = (MyArray) o;
        return Arrays.equals(array, myArray.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
