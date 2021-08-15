public class ArraySwap {
    public static void main(String[] args) {
	// int[] list = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
	//     18, 19, 20};
	
	// for (int i = 0, j = list.length - 1; i < list.length/2; i++, j--) {
	//     // Swap list[i] with list[j]
	//     int temp = list[i];
	//     list[i] = list[j];
	//     list[j] = temp;
	// }


	// System.out.print("list = {" + list[0]);
        // for (int i = 1; i < list.length; i++) {
	//     System.out.print(", " + list[i]);
	// }
	// System.out.println("}");


	int[][] values = {{3, 4, 5, 1 }, {33, 6, 1, 2}};

	for (int row = 0; row < values.length; row++) {
	    java.util.Arrays.sort(values[row]);
	    for (int column = 0; column < values[row].length; column++)
		System.out.print(values[row][column] + " ");
	    System.out.println();
	}
    }
}
