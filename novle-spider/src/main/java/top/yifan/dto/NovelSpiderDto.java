package top.yifan.dto;

import top.yifan.entity.Novel;

import java.util.List;

/**
 * 数据传输层
 *
 * @author star
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
    public static String BASE_URL = "https://www.37zw.net/s/so.php";

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
