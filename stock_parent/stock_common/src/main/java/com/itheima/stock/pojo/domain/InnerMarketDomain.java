package com.itheima.stock.pojo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author by itheima
 * @Date 2022/1/9
 * @Description 定义封装多内大盘数据的实体类
 */
@Data
@Schema(description = "Inner Market Domain")
public class InnerMarketDomain {

    /**
     * 大盘编码
     */
    @Schema(description = "stock code")
    private String code;
    /**
     * 大盘名称
     */
    @Schema(description = "stock name")
    private String name;
    /**
     * 开盘点
     */
    @Schema(description = "open point")
    private BigDecimal openPoint;
    /**
     * 当前点
     */
    @Schema(description = "current point")
    private BigDecimal curPoint;
    /**
     * 前收盘点
     */
    @Schema(description = "pre-close point")
    private BigDecimal preClosePoint;
    /**
     * 交易量
     */
    @Schema(description = "trade amount")
    private Long tradeAmt;
    /**
     * 交易金额
     */
    @Schema(description = "trade volume")
    private Long tradeVol;
    /**
     * 涨跌值
     */
    @Schema(description = "up-down value")
    private BigDecimal upDown;
    /**
     * 涨幅
     */
    @Schema(description = "rise")
    private BigDecimal rose;

    /**
     * 振幅
     */
    @Schema(description = "amplitude")
    private BigDecimal amplitude;
    /**
     * 当前时间
     */
    @Schema(description = "current time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curTime;
}
