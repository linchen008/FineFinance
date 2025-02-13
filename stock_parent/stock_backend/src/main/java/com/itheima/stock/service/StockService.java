package com.itheima.stock.service;

import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.pojo.domain.StockBlockDomain;
import com.itheima.stock.vo.resp.PageResult;
import com.itheima.stock.vo.resp.R;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

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

    /**
     * Get stock page information
     * @param page
     * @param pageSize
     * @return R<PageResult>
     */
    R<PageResult> getStockPageInfo(Integer page, Integer pageSize);

    /**
     * Get stock increase information
     * @return R<List>
     */
    R<List> getStockIncrease();

    /**
     * Get the number of stocks that have increased and decreased
     * @return R<Map>
     */
    R<Map> getStockUpdownCount();

    /**
     * Export stock data
     * @param response
     * @param page
     * @param pageSize
     */
    void stockExport(HttpServletResponse response, Integer page, Integer pageSize);
}
