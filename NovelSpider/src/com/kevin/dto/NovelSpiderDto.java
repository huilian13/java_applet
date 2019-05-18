package com.kevin.dto;

import com.kevin.entity.Chapter;
import com.kevin.entity.Novel;
import com.kevin.entity.UrlBean;

import java.util.List;

/**
 * 2018-06-14 14:26
 * 数据传输层
 *
 * @author kevin
 **/
public class NovelSpiderDto {
    /**
     * 小说对象
     */
    private List<Novel> novelList;

    /**
     * 小说章节数
     */
    private int chapterNumber;

    /**
     * 小说基地址
     */
    public static String BASE_URL="https://www.37zw.net/s/so.php";

    public List<Novel> getNovelList() {
        return novelList;
    }

    public void setNovelList(List<Novel> novelList) {
        this.novelList = novelList;
    }

    public int getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(int chapterNumber) {
        this.chapterNumber = chapterNumber;
    }
}
