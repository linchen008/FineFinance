package com.itheima.stock.controller;

import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.pojo.domain.StockBlockDomain;
import com.itheima.stock.service.StockService;
import com.itheima.stock.vo.resp.PageResult;
import com.itheima.stock.vo.resp.R;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 08/02/2025 17:22
 * @Description :
 */

@RestController
@RequestMapping("/api/quot")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/index/all")
    public R<List<InnerMarketDomain>> getInnerMarket() {
        return stockService.getInnerIndexAll();
    }

    @GetMapping("/sector/all")
    public R<List<StockBlockDomain>> sectorAll() {
        return stockService.sectorAllLimit();
    }

    @GetMapping("/stock/all")
    public R<PageResult> getStockPageInfo(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        return stockService.getStockPageInfo(page, pageSize);
    }

    @GetMapping("/stock/increase")
    public R<List> getStockIncrease() {
        return stockService.getStockIncrease();
    }

    /**
     * Get the number of stocks that have increased and decreased
     *
     * @return R<Map>
     */
    @GetMapping("/stock/updown/count")
    public R<Map> getStockUpdownCount() {
        return stockService.getStockUpdownCount();
    }

    /**
     * Export stock data
     *
     * @param response response
     * @param page     当前页
     * @param pageSize 每页显示条数
     */
    @GetMapping("/stock/export")
    public void stockExport(HttpServletResponse response,
                            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
        stockService.stockExport(response, page, pageSize);
    }
}
