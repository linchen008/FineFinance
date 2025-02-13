package com.itheima.stock.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.idev.excel.FastExcel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.stock.mapper.StockBlockRtInfoMapper;
import com.itheima.stock.mapper.StockMarketIndexInfoMapper;
import com.itheima.stock.mapper.StockRtInfoMapper;
import com.itheima.stock.pojo.domain.InnerMarketDomain;
import com.itheima.stock.pojo.domain.StockBlockDomain;
import com.itheima.stock.pojo.domain.StockUpdownDomain;
import com.itheima.stock.pojo.vo.StockInfoConfig;
import com.itheima.stock.service.StockService;
import com.itheima.stock.utils.DateTimeUtil;
import com.itheima.stock.vo.resp.PageResult;
import com.itheima.stock.vo.resp.R;
import com.itheima.stock.vo.resp.ResponseCode;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 08/02/2025 17:31
 * @Description :
 */
@Slf4j
@Service("stockService")
public class StockServiceImpl implements StockService {
    @Autowired
    private StockInfoConfig stockInfoConfig;

    @Autowired
    private StockMarketIndexInfoMapper stockMarketIndexInfoMapper;

    @Autowired
    private StockBlockRtInfoMapper stockBlockRtInfoMapper;

    @Autowired
    private StockRtInfoMapper stockRtInfoMapper;

    /**
     * Get all real-time inner market data
     *
     * @return R<List < InnerMarketDomain>>
     */
    @Override
    public R<List<InnerMarketDomain>> getInnerIndexAll() {
        //TODO: 1. get all inner stock id
        List<String> inners = stockInfoConfig.getInner();

//        //TODO: 2. get latest stock transaction data
//        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();
//
//        //TODO: 3. mock data for testing
        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                        .withZone(DateTimeZone.forID("Asia/Shanghai"));
        Date lastDate = DateTime.parse("2022-01-02 09:30:00", formatter).toDate();

        // 验证时间格式（调试用）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println("DEBUG lastDate: " + sdf.format(lastDate));
        // 应输出 2022-01-02 09:30:00

        //TODO: get data from database
        List<InnerMarketDomain> list = stockMarketIndexInfoMapper.getMarketInfo(inners, lastDate);

        //TODO: return data
        return R.ok(list);
    }

    /**
     * Get all sector data
     *
     * @return R<List < StockBlockDomain>>
     */
    @Override
    public R<List<StockBlockDomain>> sectorAllLimit() {
        //TODO: 1. get latest stock transaction date
        Date lastDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();

        //mock date for testing
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(DateTimeZone.forID("Asia/Shanghai"));
        lastDate = DateTime.parse("2021-12-21 14:30:00", formatter).toDate();

        //TODO: 2. get all sector data and assemble them
        List<StockBlockDomain> infos = stockBlockRtInfoMapper.sectorAllLimit(lastDate);

        if (CollectionUtil.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }

        //TODO: 3. return data
        return R.ok(infos);
    }

    @Override
    public R<PageResult> getStockPageInfo(Integer page, Integer pageSize) {
        //1. setting page info
        PageHelper.startPage(page, pageSize);

        //2. get latest stock transaction date
        Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();

        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                        .withZone(DateTimeZone.forID("Asia/Shanghai"));

        curDate = DateTime
                .parse("2021-12-21 10:30:00", formatter)
                .toDate();

        //3. call mapper to get data
        List<StockUpdownDomain> infos = stockRtInfoMapper.getNewestStockInfo(curDate);
        if (CollectionUtil.isEmpty(infos)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }

        //4. Filling in paging information
        PageInfo<StockUpdownDomain> pageInfo = new PageInfo<>(infos);
        PageResult<StockUpdownDomain> pageResult = new PageResult<>(pageInfo);

        //PageResult<StockUpdownDomain> pageResult = new PageResult<>(new PageInfo<>(infos));

        //5. return data
        return R.ok(pageResult);
    }

    @Override
    public R<List> getStockIncrease() {
        //1. get latest stock transaction date
        Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();

        DateTimeFormatter formatter =
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                        .withZone(DateTimeZone.forID("Asia/Shanghai"));

        curDate = DateTime
                .parse("2021-12-21 10:30:00", formatter)
                .toDate();
        //2. get data from database
        List<StockUpdownDomain> increaseStock = stockRtInfoMapper.getIncreaseStockInfo(curDate);

        if (CollectionUtil.isEmpty(increaseStock)) {
            return R.error(ResponseCode.NO_RESPONSE_DATA.getMessage());
        }

        //3. return data
        return R.ok(increaseStock);
    }

    @Override
    public R<Map> getStockUpdownCount() {
        //1. get latest stock transaction date range
        //1.1 get start date
        DateTime curDateTime = DateTimeUtil.getLastDate4Stock(DateTime.now());
        Date curTime = curDateTime.toDate();
        curTime = DateTime.parse("2022-01-06 14:25:00",
                        DateTimeFormat
                                .forPattern("yyyy-MM-dd HH:mm:ss")
                                .withZone(DateTimeZone.forID("Asia/Shanghai")))
                .toDate();

        //1.2 get open date
        DateTime openDate = DateTimeUtil.getOpenDate(curDateTime);
        Date openTime = openDate.toDate();
        //TODO
        openTime = DateTime.parse("2022-01-06 09:30:00",
                        DateTimeFormat
                                .forPattern("yyyy-MM-dd HH:mm:ss")
                                .withZone(DateTimeZone.forID("Asia/Shanghai")))
                .toDate();

        //2. get data from database
        //1: up, 0: down
        //2.1 get up count
        List<Map> upCounts = stockRtInfoMapper.getStockUpdownCount(openTime, curTime, 1);
        //2.2 get down count
        List<Map> downCounts = stockRtInfoMapper.getStockUpdownCount(openTime, curTime, 0);

        //3. assemble data
        HashMap<String, List> mapInfo = new HashMap<>();
        mapInfo.put("upList", upCounts);
        mapInfo.put("downList", downCounts);

        //4. return data
        return R.ok(mapInfo);
    }

    /**
     * Export stock data
     *
     * @param response response
     * @param page     current page
     * @param pageSize number of items per page
     */
    @Override
    public void stockExport(HttpServletResponse response, Integer page, Integer pageSize) {
        //1.get latest transaction date
        Date curDate = DateTimeUtil.getLastDate4Stock(DateTime.now()).toDate();

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(DateTimeZone.forID("Asia/Shanghai"));
        curDate = DateTime.parse("2022-01-05 09:47:00", dateTimeFormatter).toDate();

        //2. set page info
        PageHelper.startPage(page, pageSize);

        //3. get data from database
        List<StockUpdownDomain> infos = stockRtInfoMapper.getNewestStockInfo(curDate);
        response.setCharacterEncoding("utf-8");

        //check data
        try {
            if (CollectionUtil.isEmpty(infos)) {
                //响应提示信息
                R<Object> error = R.error(ResponseCode.NO_RESPONSE_DATA);
                //将提示信息转换为json格式
                String jsonData = new ObjectMapper().writeValueAsString(error);
                //设置响应数据格式, 告知浏览器
                response.setContentType("application/json");
                //将json数据写入到响应中
                response.getWriter().write(jsonData);
                return;
            }
            //3. call fastexcel to export data
            response.setContentType("application/vnd.ms-excel");
            val fileName = URLEncoder.encode("stock_data", "utf-8");
            //set default file name for excel download
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //export data
            FastExcel.write(response.getOutputStream(), StockUpdownDomain.class).sheet("stock_data").doWrite(infos);
        } catch (IOException e) {
            log.error("Export time: {}, current page: {}, export data amount: {}, exception information: {}",
                    curDate, page, pageSize, e.getMessage());
        }
    }
}
