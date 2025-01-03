package application.Model.Types;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*
 * Wrapper for processed powerpoint data
 */
public class PptData {
    private int slideWordCount;
    private int commentWordCount;
    private ArrayList<String> rawSlideTxt;
    private ArrayList<String> rawCommentTxt;
    private ArrayList<TextGroup> slideTxt;
    private ArrayList<TextGroup> commentTxt;
    private ArrayList<BufferedImage> images;

    public PptData(ArrayList<TextGroup> slideTxt,
                   ArrayList<TextGroup> commentTxt,
                   ArrayList<BufferedImage> images,
                   ArrayList<String> rawSlideTxt,
                   ArrayList<String> rawCommentTxt) {
        this.slideTxt = slideTxt;
        this.commentTxt = commentTxt;
        this.images = images;
        this.rawCommentTxt = rawCommentTxt;
        this.rawSlideTxt = rawSlideTxt;
    }
    public int getWordCount() {
        return getCommentWordCount() + getSlideWordCount();
    }
    public int getSlideWordCount() {
        int ret = 0;
        for (TextGroup i : slideTxt) {
            ret += i.getWords().size();
        }
        return ret;
    }

    public ArrayList<String> getRawSlideTxt() {
        return rawSlideTxt;
    }

    public void setRawSlideTxt(ArrayList<String> rawSlideTxt) {
        this.rawSlideTxt = rawSlideTxt;
    }

    public ArrayList<String> getRawCommentTxt() {
        return rawCommentTxt;
    }

    public void setRawCommentTxt(ArrayList<String> rawCommentTxt) {
        this.rawCommentTxt = rawCommentTxt;
    }

    public int getCommentWordCount() {
        int ret = 0;
        for (TextGroup i : commentTxt) {
            ret += i.getWords().size();
        }
        return ret;
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
