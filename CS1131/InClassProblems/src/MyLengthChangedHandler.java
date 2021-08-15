public class MyLengthChangedHandler implements LengthChangedHandler {
    @Override
    public void lengthChanged(int oldLength, int newLength) {
        System.out.println("Length was changed from " + oldLength + " to " + newLength);
    }
}
