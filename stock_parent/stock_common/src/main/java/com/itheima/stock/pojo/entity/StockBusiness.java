package com.itheima.stock.pojo.entity;

import lombok.Data;

import java.util.Date;

/**
 * 主营业务表
 * @TableName stock_business
 */
@Data
public class StockBusiness {
    /**
     *  股票编码
     */
    private String stockCode;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 股票所属行业|板块标识
     */
    private String blockLabel;

    /**
     * 行业板块名称
     */
    private String blockName;

    /**
     * 主营业务
     */
    private String business;

    /**
     * 更新时间
     */
    private Date updateTime;
}