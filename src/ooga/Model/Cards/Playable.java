package ooga.Model.Cards;

import javafx.scene.image.ImageView;

public interface Playable {
    void setID(int num);

    void setNumber(int num);

    void setFrontImageView(ImageView image);

    void setBackImageView(ImageView image);

    void setValue(String value);

    int getNumber();

    ImageView getFrontImageView();

    ImageView getBackImageView();

    String getValue();

    int getID();

}