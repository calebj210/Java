import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SortExamples {
    public void bubbleSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public int[] bubbleSort_nondestructive(int[] array) {
        int[] result = new int[array.length];
        System.arraycopy(array, 0, result, 0, array.length);

        for (int i = 0; i < result.length; i++) {
            for (int j = 1; j < result.length; j++) {
                if (result[j - 1] > result[j]) {
                    int temp = result[j - 1];
                    result[j - 1] = result[j];
                    result[j] = temp;
                }
            }
        }

        return result;
    }

    public void selectionSort(int[] array) {
        for (int j = 0; j < array.length; j++) {
            int minIndex = j;

            for (int i = j + 1; i < array.length; i++) {
                if (array[minIndex] > array[i]) {
                    minIndex = i;
                }
            }
            int temp = array[j];
            array[j] = array[minIndex];
            array[minIndex] = temp;
        }
    }

    public void insertionSort(int[] array) {
        for(int i = 1; i < array.length; i++) {
            int target = array[i];
            int j = i - 1;
            for( ; j >= 0 && array[j] > target; j--) {
                array[j+1] = array[j];
            }
            array[j+1] = target;
        }
    }

    /**
     *
     * @param list1 - SORTED list
     * @param list2 - SORTED list
     * @return
     */
    public ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> result = new ArrayList<>();

        while(!list1.isEmpty() && !list2.isEmpty()) {
            int val1 = list1.get(0);
            int val2 = list2.get(0);
            if( val1 <= val2) {
                result.add(val1);
                list1.remove(0);
            } else {
                result.add(val2);
                list2.remove(0);
            }
        }
        result.addAll(list1);
        result.addAll(list2);

        return result;
    }

    public void mergeSort(int[] array) {

    }

    public static void main(String[] args) {
        SortExamples sortExamples = new SortExamples();

        int[] array = {5, 4, 3, 2, 1};

        System.out.println("Initial array = " + Arrays.toString(array));
        sortExamples.insertionSort(array);
        System.out.println("Final array = " + Arrays.toString(array));
    }
}
