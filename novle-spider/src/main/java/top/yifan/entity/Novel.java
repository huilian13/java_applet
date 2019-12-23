package top.yifan.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 小说对象
 *
 * @author star
 **/
public class Novel implements Serializable {
    /**
     * 小说名
     */
    private String novelName;

    /**
     * 小说链接
     */
    private String novelUrl;

    /**
     * 小说作者
     */
    private String novelAuthor;

    /**
     * 最新章节
     */
    private String lastChapter;

    /**
     * 章节数
     */
    private LinkedList<Chapter> chapters;

    /**
     * 状态
     */
    private String status;

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getNovelAuthor() {
        return novelAuthor;
    }

    public void setNovelAuthor(String novelAuthor) {
        this.novelAuthor = novelAuthor;
    }

    public String getLastChapter() {
        if (chapters != null && chapters.size() != 0) {
            lastChapter = chapters.getLast().getTitle();
        }
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(LinkedList<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getNovelUrl() {
        return novelUrl;
    }

    public void setNovelUrl(String novelUrl) {
        this.novelUrl = novelUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        Novel novel = (Novel) obj;
        return this.novelName.equals(novel.novelName);
    }
}
