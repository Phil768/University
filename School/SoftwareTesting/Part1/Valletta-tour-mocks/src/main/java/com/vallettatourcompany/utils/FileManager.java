package com.vallettatourcompany.utils;

import com.vallettatourcompany.Tourist;

public interface FileManager {
    public void saveTouristToFile(Tourist tourist);
    public void loadTouristFromFile();

}
