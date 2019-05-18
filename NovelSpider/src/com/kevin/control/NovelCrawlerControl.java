package com.kevin.control;

import com.kevin.dto.NovelSpiderDto;
import com.kevin.entity.Chapter;
import com.kevin.entity.Novel;
import com.kevin.entity.UrlBean;
import com.kevin.service.CrawlerService;
import com.kevin.service.Impl.ChapterCrawlerServiceImpl;
import com.kevin.service.Impl.ContentCrawlerServiceImpl;
import com.kevin.service.Impl.NovelCrawlerServiceImpl;
import com.kevin.ui.NovelFrame;
import com.kevin.ui.NovelPanel;
import com.kevin.ui.NovelSearchPanel;
import com.kevin.ui.ProgressFrame;
import com.kevin.util.IOUtil;

import javax.swing.*;
import java.io.File;
import java.util.*;

/**
 * 2018-06-14 15:44
 * 小说爬取控制器
 *
 * @author kevin
 **/
public class NovelCrawlerControl {

    /**
     * 小说主面板
     */
    private NovelPanel novelPanel;

    /**
     * 小说搜索面板
     */
    private NovelSearchPanel novelSearchPanel;

    /**
     * 小说爬取数据传输对象
     */
    private NovelSpiderDto novelSpiderDto;

    /**
     * 已下载的进度
     */
    private static int length=0;

    public NovelCrawlerControl() {
        //初始化数据传输对象
        this.novelSpiderDto=new NovelSpiderDto();
        //初始化面板
        this.novelPanel=new NovelPanel(novelSpiderDto,this);
        this.novelSearchPanel=new NovelSearchPanel(novelSpiderDto,this);
        //初始化窗口
        new NovelFrame(novelPanel,novelSearchPanel);
    }

    /**
     * 根据搜索名查找小说
     * @param novelName
     */
    public void searchNovel(String novelName) {
        //地址对象
        UrlBean urlBean=null;
        //创建小说爬取业务逻辑对象
        CrawlerService<List<Novel>> novelService=new NovelCrawlerServiceImpl();
        //创建请求参数
        HashMap<String, String> params = new HashMap<>();
        params.put("type", "articlename");
        params.put("s", novelName);
        //创建url对象（根据小说名查找小说）
        urlBean = new UrlBean(NovelSpiderDto.BASE_URL,params);
        //获取网页中的所有小说对象
        List<Novel> novelList =novelService.crawl(urlBean);
        //创建章节爬取业务逻辑对象
        CrawlerService<Map<String,Object>> chapterService=new ChapterCrawlerServiceImpl();
        //通过小说链接获取小说的章节
        for (Novel novel : novelList) {
            //创建单个小说地址
            urlBean=new UrlBean(novel.getNovelUrl());
            //根据小说地址获取小说所有章节
            Map<String,Object> chapterMap=chapterService.crawl(urlBean);
            //获取所有章节
            LinkedList<Chapter> chapters = (LinkedList<Chapter>) chapterMap.get("chapters");
            //获取小说状态
            String status = (String)chapterMap.get("status");
            novel.setChapters(chapters);
            novel.setStatus(status);
        }
        //将小说集合设置到数据传输对象
        this.novelSpiderDto.setNovelList(novelList);
    }

    /**
     * 显示表行
     */
    public void showTableRow(){
        //表格每列的数据
        Vector<String> dataVector = null;
        //获取所有小说
        List<Novel> novelList = this.novelSpiderDto.getNovelList();
        //序号
        int num=1;
        if(novelList!=null){
            for(Novel novel:novelList){
                dataVector = new Vector<>();
                //添加序号
                dataVector.add(num+"");
                //添加小说名
                dataVector.add(novel.getNovelName());
                //添加小说作者
                dataVector.add(novel.getNovelAuthor());
                //添加最新章节
                dataVector.add(novel.getLastChapter());
                //添加状态
                dataVector.add(novel.getStatus());
                //将信息添加到表格中
                this.novelPanel.tableModel.addRow(dataVector);
                this.novelPanel.table.setModel(this.novelPanel.tableModel);
                //更新
                this.novelPanel.table.updateUI();
                num++;
            }
        }
    }

    /**
     * 获取章节内容
     * @param chapterUrl
     * @return
     */
    public String getContent(String chapterUrl) {
        //获取章节内容业务逻辑对象
        ContentCrawlerServiceImpl contentCrawlerService = new ContentCrawlerServiceImpl();
        //获取章节内容
        String content = contentCrawlerService.crawl(new UrlBean(chapterUrl));

        return content;
    }

    /**
     * 根据小说名获取章节数目
     * @param novelName
     * @return
     */
    public int getChapterLength(String novelName) {
        Novel novel = this.getNovelByName(novelName);
        int length = novel.getChapters().size()-1;
        return length;
    }

    /**
     * 根据小说名获取小说对象
     * @return
     */
    public Novel getNovelByName(String novelName){
        //获取小说集合
        List<Novel> novelList = this.novelSpiderDto.getNovelList();
        if(novelList!=null){
            for (Novel novel:novelList){
                if(novel.getNovelName().equals(novelName)){
                    return novel;
                }
            }
        }
        return null;
    }

    /**
     * 下载章节内容
     */
    public void downloadChapterContent(String novelName,int leftRange,int rightRange) {
        //获取桌面路径
        String path = System.getProperty("user.home") + "\\Desktop";
        File desktopPath = new File(path);
        //获取小说地址
        Novel novel = this.getNovelByName(novelName);
        //创建小说文件夹
        File storeDirectory = new File(desktopPath, novel.getNovelName());
        if (!storeDirectory.exists()) {
            storeDirectory.mkdirs();
        }
        //获取小说的所有章节
        List<Chapter> chapters = novel.getChapters();
        //章节对象
        Chapter chapter=null;
        //小说章节文件
        File chapterFile=null;
        if(chapters!=null&&!chapters.isEmpty()){
            //分段下载文件
            for(int i=leftRange;i<=rightRange;i++){
                //获取章节对象
                chapter = chapters.get(i);
                //获取章节名
                String childFile = chapter.getTitle().replaceAll("[\\:/?*<>|]+", "");
                //创建章节文件
                chapterFile=new File(storeDirectory,childFile+".txt");
                //获取内容
                String content = getContent(novel.getNovelUrl() + chapter.getChapterUrl());
                System.out.println("正在下载："+chapter.getTitle() + "------->" + novel.getNovelUrl()+chapter.getChapterUrl());
                synchronized (NovelCrawlerControl.class) {
                    length++;
                    double progressValue = length * 1.0 * 100 / chapters.size();
                    ProgressFrame.getInstance().setProgress(progressValue, length * 100 / chapters.size());
                }
                //写出文件
                IOUtil.writeToFile(chapterFile,content);
            }

        }
        if(length==novel.getChapters().size()){
            ProgressFrame.stopProgressBar();
            JOptionPane.showMessageDialog(null, "下载完毕!", "提示", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
