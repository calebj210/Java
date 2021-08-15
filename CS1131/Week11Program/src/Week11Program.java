import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Read files into buffer queue and display the file.
 *
 * Date Last Modified: November 28, 2020
 * @author Caleb Jacobs
 *
 * CS1131 Fall 2020
 * Lab Section: L03
 */
public class Week11Program {
    /**
     * Display and empty queue
     *
     * @param queue - queue to be emptied
     */
    public static void emptyQueue(Queue<Character> queue) throws QueueEmptyException {
        while (!queue.isEmpty()) {
            System.out.print(queue.dequeue());
        }
        System.out.print("\n");
    }

    /**
     * Driver for printing files using buffer queue
     *
     * @param args - file to print
     */
    public static void main(String[] args) throws QueueFullException, QueueEmptyException {
        File inputFile = new File(args[0]);
        try (Scanner input = new Scanner(inputFile)) {
            input.useDelimiter("");

            // Get queue size
            String tmp = "" + input.next() + input.next();
            int sizeLimit = Integer.parseInt(tmp);

            // Initialize character queue
            Queue<Character> queue = new Queue<>(sizeLimit);

            // Read and print characters in file
            while (input.hasNext()) {
                if (queue.size() < sizeLimit) {
                    queue.enqueue(input.next().charAt(0));
                } else {
                    // Print and empty queue
                    emptyQueue(queue);

                    // Check if queue is still full
                    if (queue.isFull()) {
                        throw new QueueFullException();
                    }
                }
            }

            // Print remaining characters in file
            emptyQueue(queue);

        } catch (FileNotFoundException e) {
            System.out.println("File, " + inputFile.toString() + ", not found\n");
            e.printStackTrace();
        }
    }
}
