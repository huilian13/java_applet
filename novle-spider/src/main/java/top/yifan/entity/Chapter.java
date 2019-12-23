package top.yifan.entity;

/**
 * 小说的章节类
 *
 * @author star
 **/
public class Chapter {
    /**
     * 章节名
     */
    private String title;

    /**
     * 章节链接
     */
    private String chapterUrl;

    /**
     * 章节内容
     */
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
