package com.itheima.stock.pojo.domain;

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
 * @Description 股票板块domain
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Stock Block Domain")
public class StockBlockDomain {
    /**
     * 公司数量
     */
    @Schema(description = "company number")
    private Integer companyNum;
    /**
     * 交易量
     */
    @Schema(description = "trade amount")
    private Long tradeAmt;
    /**
     * 板块编码
     */
    @Schema(description = "block code")
    private String code;
    /**
     * 平均价
     */
    @Schema(description = "average price")
    private BigDecimal avgPrice;
    /**
     * 板块名称
     */
    @Schema(description = "block name")
    private String name;
    /**
     * 当前日期
     */
    @Schema(description = "current date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date curDate;
    /**
     *交易金额
     */
    @Schema(description = "trade volume")
    private BigDecimal tradeVol;
	/**
     * 涨跌率
     */
    @Schema(description = "up-down rate")
    private BigDecimal updownRate;
}
