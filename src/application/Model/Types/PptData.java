package application.Model.Types;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
/*
* Wrapper for processed powerpoint data
 */
public class PptData {
    private ArrayList<TextGroup> slideTxt;
    private ArrayList<TextGroup> commentTxt;
    private ArrayList<BufferedImage> images;

    public PptData(ArrayList<TextGroup> slideTxt, ArrayList<TextGroup> commentTxt, ArrayList<BufferedImage> images) {
        this.slideTxt = slideTxt;
        this.commentTxt = commentTxt;
        this.images = images;
    }

    public ArrayList<BufferedImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<BufferedImage> images) {
        this.images = images;
    }

    public ArrayList<TextGroup> getSlideTxtGroup() {
        return slideTxt;
    }

    public ArrayList<String> getSlideTxt() {
        ArrayList<String> ret = new ArrayList<String>();
        for (int i = 0; i < slideTxt.size(); i++) {
            ret.addAll(slideTxt.get(i).getWords());
        }
        return ret;
    }

    public void setSlideTxt(ArrayList<TextGroup> slideTxt) {
        this.slideTxt = slideTxt;
    }

    public ArrayList<String> getCommentTxt() {
        ArrayList<String> ret = new ArrayList<String>();
        for (int i = 0; i < commentTxt.size(); i++) {
            ret.addAll(commentTxt.get(i).getWords());
        }
        return ret;
    }

    public ArrayList<TextGroup> getCommentTxtGroup() {
        return commentTxt;
    }

    public void setCommentTxt(ArrayList<TextGroup> commentTxt) {
        this.commentTxt = commentTxt;
    }

    @Override
    public String toString() {
        return "PptData{" +
                "slideTxt=" + slideTxt.toString() +
                ", commentTxt=" + commentTxt.toString() +
                '}';
    }
}
