package com.cn.gazelle.logistics.util;

import java.util.List;

/**
 * Created by CYH on 2015/12/2.
 */
public class PageUtil {
    private List datas;
    private int firstResult;            //当前页 的第一条
    private int maxResults = 10;        //每页显示条数
    private int previous;               //上一页 页码
    private int next;                    //下一页
    private int pageTotal;              //总页数
    private int recordTotal;            //总条数
    private int page = 1;                //当前页数

    //新增字段   显示具体码数
    private int startpage = 1;    //当前开始页码数
    private int endpage = 1;        //当前结束页码数
    private int maxPage = 5;        //最大页码数,最好是单数

    @SuppressWarnings("unused")
    private int start;        //分页查询的起始

    public int getStart() {
        return (page - 1) * maxResults;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public PageUtil() {

    }

    public PageUtil(int max) {
        this.maxResults = max;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public int getNext() {
        return next;
    }

    public int getPage() {
        return page;
    }

    public int getPageTotal() {
        if (pageTotal == 0) pageTotal = 1;
        return pageTotal;
    }

    public int getPrevious() {
        return previous;
    }

    public int getFirstResult() {

        return firstResult;
    }

    public int getRecordTotal() {
        return recordTotal;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
        if (page == 0) {
            page = 1;
        }
        if (((recordTotal % maxResults) > 0) || (recordTotal == 0)) {
            pageTotal = (recordTotal / maxResults) + 1;
        } else {
            pageTotal = (recordTotal / maxResults);
        }
        //pageTotal = (recordTotal - 1) / maxResults + 1;

        if (page > pageTotal) {
            page = pageTotal;
        }

        next = page + 1;
        previous = page - 1;

        if (previous <= 0) {
            previous = 1;
        }

        if (next >= pageTotal) {
            next = pageTotal;
        }

        firstResult = page * maxResults - maxResults;

        showpage();
    }

    /**
     * 获取页码显示
     */
    private void showpage() {
        //12345
        int pagelenth = maxPage / 2;    //以中间定点，求半径
        startpage = page - pagelenth;    //以当前页数为中间点、向上求开始页码
        endpage = page + pagelenth;

        //如果开始页码小于1，初始为1
        if (startpage < 1) {
            startpage = 1;
        }

        //如果结束页码大于最大页数，初始为最大页数
        if (endpage > pageTotal) {
            endpage = pageTotal;
        }


        //如果总页数小于或等于要显示的页数，默认显示所有页数
        if (pageTotal <= maxPage) {
            //如果结
            startpage = 1;

            endpage = pageTotal;


        } else {
            if (endpage < maxPage) {
                endpage = maxPage;
            }
        }


        int minpage = pageTotal - maxPage + 1 > 0 ? pageTotal - maxPage + 1 : 1;
        //如果开始页数 大于 页数2
        if (startpage > minpage) {
            startpage = minpage;
        }

    }


    public void setPrevious(int previous) {
        this.previous = previous;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getStartpage() {
        return startpage;
    }

    public void setStartpage(int startpage) {
        this.startpage = startpage;
    }

    public int getEndpage() {
        return endpage;
    }

    public void setEndpage(int endpage) {
        this.endpage = endpage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    @SuppressWarnings("unchecked")
    public List getDatas() {
        return datas;
    }

    @SuppressWarnings("unchecked")
    public void setDatas(List datas) {
        this.datas = datas;
    }
}