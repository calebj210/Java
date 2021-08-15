public class Square extends Rectangle {
    private LengthChangedHandler handler = null;

    @Override
    public void setWidth(int newWidth) {
        int oldWidth = getWidth();
        super.setWidth(newWidth);
        super.setHeight(newWidth);
        if (handler != null) {
            handler.lengthChanged(oldWidth, newWidth);
        }
    }

    @Override
    public void setHeight(int newHeight) {
        int oldHeight = getHeight();
        super.setWidth(newHeight);
        super.setHeight(newHeight);
        if (handler != null) {
            handler.lengthChanged(oldHeight, newHeight);
        }
    }

    public int getLength() {
        return getWidth();
    }

    public void setLength(int length) {
        setWidth(length);
    }


    public void setOnLengthChanged(LengthChangedHandler handler) {
        this.handler = handler;
    }
}
