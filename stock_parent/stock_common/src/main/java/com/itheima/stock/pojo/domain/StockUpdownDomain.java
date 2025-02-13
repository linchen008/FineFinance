package com.itheima.stock.pojo.domain;

import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by itheima
 * @Date 2022/2/28
 * @Description 股票涨跌信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "StockUpdownDomain", description = "股票涨跌信息")
public class StockUpdownDomain {
    @ExcelProperty(value = {"股票代码"}, index = 0)
    private String code;

    @ExcelProperty(value = {"股票名称"}, index = 1)
    private String name;

    @Schema(description = "昨收价")
    @ExcelProperty(value = {"昨收价"}, index = 2)
    private BigDecimal preClosePrice;

    @Schema(description = "开盘价")
    @ExcelProperty(value = {"开盘价"}, index = 3)
    private BigDecimal tradePrice;

    @Schema(description = "涨跌额")
    @ExcelProperty(value = {"涨跌额"}, index = 4)
    private BigDecimal increase;

    @Schema(description = "涨跌幅")
    @ExcelProperty(value = {"涨跌幅"}, index = 5)
    private BigDecimal upDown;

    @Schema(description = "振幅")
    @ExcelProperty(value = {"振幅"}, index = 6)
    private BigDecimal amplitude;

    @Schema(description = "交易量")
    @ExcelProperty(value = {"交易量"}, index = 7)
    private Long tradeAmt;

    @Schema(description = "交易额")
    @ExcelProperty(value = {"交易额"}, index = 8)
    private BigDecimal tradeVol;

    /**
     * 日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm") // SpringMVC日期格式化
    @Schema(description = "日期")
    @ExcelProperty(value = {"日期"}, index = 9)
    @DateTimeFormat("yyyy-MM-dd HH:mm") // FastExcel日期格式化
    private Date curDate;
}