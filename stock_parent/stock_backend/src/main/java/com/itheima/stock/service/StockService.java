package com.itheima.stock.service;

import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.pojo.domain.StockBlockDomain;
import com.itheima.stock.vo.resp.R;

import java.util.List;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 08/02/2025 17:27
 * @Description : Stock Service Interface
 */

public interface StockService {
    /**
     * Get all inner market data
     * @return R<List<InnerMarketDomain>>
     */

    R<List<InnerMarketDomain>> getInnerIndexAll();

    /**
     * Get all sector data
     * @return R<List<StockBlockDomain>>
     */
    R<List<StockBlockDomain>> sectorAllLimit();
}
